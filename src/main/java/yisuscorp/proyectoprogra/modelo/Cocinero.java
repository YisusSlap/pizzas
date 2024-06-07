/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.modelo;

/**
 *
 * @author jesus
 */
import yisuscorp.proyectoprogra.vista.PantallaAnimacion;
import yisuscorp.proyectoprogra.modelo.Constantes.constantesCocinero;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.Instant;
import javax.imageio.ImageIO;

public class Cocinero extends Entidad implements Runnable {
    private Inventario inventarioTaco;
    private int numeroOrden = 0;
    private int tickCocinar = 0;
    private BufferedImage[][] hojaDeAnimacion;
    private int tickAnimacion;
    private int indiceAnimacion;
    private final int velocidadAnimacion = 10;
    private int accionActual = constantesCocinero.INACTIVO;
    private final int anchoHoja = 105 * 1;
    private final int alturaHoja = 80 * 1;
    private int xVolteado = ancho;
    private int anchoVolteado = -1;
    private int tacosElaborados = 0;

    public Cocinero(float x, float y, int w, int h, int filas, int columnas) {
        super(x, y, w * 4, h * 4);
        inventarioTaco = new Inventario(1);
        cargarAnimaciones(filas, columnas, w, h);
        inventarioTaco.cargarImagenInventario();
        setAccionActual(constantesCocinero.INACTIVO);
    }

    @Override
    public void run() {
        while (true) {
            logicaCocinero();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void logicaCocinero() {
        actualizarHitbox();
        actualizarFrameAnimacion();

        synchronized (PantallaAnimacion.getInvTacos()) {
            if (inventarioTaco.hayEspacioParaTacos()) {
                crearPizza();
                setAccionActual(constantesCocinero.COCINAR);
            } else {
                setAccionActual(constantesCocinero.INACTIVO);
            }

            if (PantallaAnimacion.getInvTacos().hayEspacioParaTacos() && !inventarioTaco.isEmpty()) {
                PantallaAnimacion.getInvTacos().push(inventarioTaco.pop());
            }
        }
    }

    public void crearPizza() {
        SecureRandom n = new SecureRandom();
        int vidaPizza = n.nextInt(200) + 50;
        int precioPizza = vidaPizza + 50;
        tickCocinar++;

        if (tickCocinar == 200) {
            synchronized (PantallaAnimacion.getInvTacos()) {
                inventarioTaco.push(new Taco(0, 10, 20, 20, vidaPizza, precioPizza, numeroOrden));
                numeroOrden++;
                String fecha = String.valueOf(Instant.now());
                tickCocinar = 0;

                incrementarTacosElaborados();
            }
        }
    }

    public void incrementarTacosElaborados() {
        tacosElaborados++;
    }

    public int getTacosElaborados() {
        return tacosElaborados;
    }

    public void renderizarCocinero(Graphics g) {
        g.drawImage(hojaDeAnimacion[accionActual][indiceAnimacion], (int) x + xVolteado, (int) y, anchoHoja * anchoVolteado, alturaHoja, null);
        inventarioTaco.renderizarInventario(g, 750);
    }

    private void actualizarFrameAnimacion() {
        tickAnimacion++;
        if (tickAnimacion >= velocidadAnimacion) {
            tickAnimacion = 0;
            indiceAnimacion++;
            if (indiceAnimacion >= 2) {
                indiceAnimacion = 0;
            }
        }
    }

    public void setAccionActual(int accionActual) {
        this.accionActual = accionActual;
    }

    public void cargarAnimaciones(int filas, int columnas, int anchoMarco, int altoMarco) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/cocinero.png"));
            hojaDeAnimacion = new BufferedImage[filas][columnas];
            for (int y = 0; y < filas; y++) {
                for (int x = 0; x < columnas; x++) {
                    hojaDeAnimacion[y][x] = img.getSubimage(x * anchoMarco, y * altoMarco, anchoMarco, altoMarco);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}