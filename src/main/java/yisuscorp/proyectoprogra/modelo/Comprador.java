/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.modelo;

/**
 *
 * @author jesus
 */
import yisuscorp.proyectoprogra.modelo.Entidad;
import yisuscorp.proyectoprogra.vista.PantallaAnimacion;
import static yisuscorp.proyectoprogra.modelo.Constantes.constantesComprador;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Comprador extends Entidad implements Runnable {
    private float velocidadMovimiento;
    private int xVolteado = 0;
    private int anchoVolteado = 1;
    private BufferedImage[][] hojaDeAnimacion;
    public HashMap<Integer, Boolean> estadoDeEntradas = new HashMap<>();
    private float velocidad;
    private boolean estaMoviendose = false, corriendo = false;
    private int tickAnimacion;
    private final int velocidadAnimacion = 9;
    private int indiceAnimacion;
    private int accionActual = constantesComprador.INACTIVO;
    private float x, y;
    private int width, height;
    private Inventario inventarioTacos;
    private int ticksParaPagar = 0;
    private int ticksParaConsumir = 0;
    private int tacosConsumidos = 0;

    final int anchoHoja = 32 * 2;
    final int alturaHoja = 41 * 2;

    public Comprador(float x, float y, int w, int h, int filas, int columnas, float floatVel) {
        super(x, y, w * 4, h * 4);
        this.x = x;
        this.y = y;
        this.width = w * 3;
        this.height = h * 3;
        cargarAnimaciones(filas, columnas, w, h);
        velocidadMovimiento = floatVel;
        velocidad = velocidadMovimiento;
        this.inventarioTacos = new Inventario(1);

        estadoDeEntradas.put(KeyEvent.VK_A, false);
        estadoDeEntradas.put(KeyEvent.VK_D, false);
    }

    @Override
    public void run() {
        while (true) {
            logicaComprador(new Rectangle(650, 320, 500, 200));
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void logicaComprador(Rectangle inventarioBounds) {
        actualizarPosicion();
        actualizarHitbox();
        checarMovimientosPlayer();
        actualizarFrameAnimacion();
        setAccionActual();
        synchronized (PantallaAnimacion.getInvTacos()) {
            if (colisionaConInventario(inventarioBounds)) {
                if (inventarioTacos.hayEspacioParaTacos()) {
                    comprarProducto();
                }
            }
            if (!inventarioTacos.isEmpty()) {
                consumir();
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public boolean colisionaConInventario(Rectangle inventarioBounds) {
        return this.getBounds().intersects(inventarioBounds);
    }

    public void comprarProducto() {
        ticksParaPagar++;
        if (ticksParaPagar == 30) {
            synchronized (PantallaAnimacion.getInvTacos()) {
                if (!PantallaAnimacion.getInvTacos().isEmpty()) {
                    inventarioTacos.push(PantallaAnimacion.getInvTacos().getInventarioTacos().pop());
                }
            }
            ticksParaPagar = 0;
        }
    }

    public void consumir() {
        ticksParaConsumir++;
        inventarioTacos.peek().cantidadDeUso--;
        if (ticksParaConsumir == 50) {
            if (inventarioTacos.peek().cantidadDeUso <= 0) {
                inventarioTacos.getInventarioTacos().pop();
                incrementarTacosConsumidos();
                System.out.println(getTacosConsumidos());
            }
            ticksParaConsumir = 0;
        }
    }

    private void actualizarPosicion() {
        estaMoviendose = false;
    }

    private void checarMovimientosPlayer() {
        if (estadoDeEntradas.get(KeyEvent.VK_A) && !estadoDeEntradas.get(KeyEvent.VK_D)) {
            xVolteado = ancho;
            anchoVolteado = -1;
            x -= velocidadMovimiento;
            estaMoviendose = true;
        } else if (estadoDeEntradas.get(KeyEvent.VK_D) && !estadoDeEntradas.get(KeyEvent.VK_A)) {
            xVolteado = 0;
            anchoVolteado = 1;
            x += velocidadMovimiento;
            estaMoviendose = true;
        }
    }

    public void resetearMovimiento() {
        estadoDeEntradas.replace(KeyEvent.VK_A, false);
        estadoDeEntradas.replace(KeyEvent.VK_D, false);
    }

    private void actualizarFrameAnimacion() {
        tickAnimacion++;
        if (!estaMoviendose) {
            corriendo = false;
            velocidadMovimiento = 1;
        }
        if (tickAnimacion >= velocidadAnimacion) {
            tickAnimacion = 0;
            indiceAnimacion++;
            if (indiceAnimacion >= constantesComprador.longitudAccion(accionActual)) {
                indiceAnimacion = 0;
                if (estaMoviendose && !corriendo) {
                    corriendo = true;
                    velocidadMovimiento = velocidad;
                }
            }
        }
    }

    public void renderizarComprador(Graphics g) {
        g.drawImage(hojaDeAnimacion[accionActual][indiceAnimacion], (int) x + xVolteado, (int) y, anchoHoja * anchoVolteado, alturaHoja, null);
        inventarioTacos.renderizarInventario(g, x);
    }

    private void setAccionActual() {
        int animacionInicio = accionActual;
        if (estaMoviendose) {
            if (corriendo) {
                accionActual = constantesComprador.CORRER;
            } else {
                accionActual = constantesComprador.ENCARRERARSE;
            }
        } else {
            accionActual = constantesComprador.INACTIVO;
        }
        if (animacionInicio != accionActual) {
            resetearAnimacion();
        }
    }

    private void resetearAnimacion() {
        tickAnimacion = 0;
        indiceAnimacion = 0;
    }

    public void incrementarTacosConsumidos() {
        tacosConsumidos++;
    }

    public int getTacosConsumidos() {
        return tacosConsumidos;
    }

    public void cargarAnimaciones(int filas, int columnas, int anchoMarco, int altoMarco) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/comprador.png"));
            hojaDeAnimacion = new BufferedImage[filas][columnas];
            for (int y = 0; y < filas; y++) {
                for (int x = 0; x < columnas; x++) {
                    hojaDeAnimacion[y][x] = img.getSubimage(x * anchoMarco, y * altoMarco, anchoMarco, altoMarco);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RasterFormatException e) {
            e.printStackTrace();
            System.out.println("Error al cargar subimágenes. Verifique las dimensiones de la imagen.");
        }
    }
}