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

public class Comprador extends Entidad {
    // Variables de instancia
    private float velocidadMovimiento;
    private int xVolteado = 0;
    private int anchoVolteado = 1;
    private BufferedImage[][] hojaDeAnimacion;
    public HashMap<Integer, Boolean> estadoDeEntradas = new HashMap<>();
    private float velocidad;
    private boolean estaMoviendose = false, corriendo = false;
    private int tickAnimacion;
    private int velocidadAnimacion = 9;
    private int indiceAnimacion;
    private int accionActual = constantesComprador.INACTIVO;
    private float x, y;
    private int width, height;
    private Inventario inventarioPizzas;
    private int ticksParaPagar = 0;
    private int ticksParaConsumir = 0;
    private int pizzasConsumidas = 0; // Contador para las pizzas consumidas
    
    final int anchoHoja = 32 * 2;
    final int alturaHoja = 41 * 2;

    // Constructor
    public Comprador(float x, float y, int w, int h, int filas, int columnas, float floatVel) {
        super(x, y, w * 4, h * 4);
        this.x = x;
        this.y = y;
        this.width = w * 4;
        this.height = h * 4;
        cargarAnimaciones(filas, columnas, w, h);
        velocidadMovimiento = floatVel;
        velocidad = velocidadMovimiento;
        this.inventarioPizzas = new Inventario(1);

        // Inicialización del estado de las entradas
        estadoDeEntradas.put(KeyEvent.VK_A, false);
        estadoDeEntradas.put(KeyEvent.VK_D, false);
    }

    // Método principal para la lógica del comprador
    public void logicaComprador(Rectangle inventarioBounds) {
        actualizarPosicion();
        actualizarHitbox();
        checarMovimientosPlayer();
        actualizarFrameAnimacion();
        setAccionActual();
        if (colisionaConInventario(inventarioBounds)) {
            //System.out.println("Collision");
            if (inventarioPizzas.hayEspacioParaPizza()) {
                comprarProducto();    
            }
        }
        if (!inventarioPizzas.isEmpty()) {
            consumir();    
        }
    }
    
    // Método para obtener el área de colisión del comprador
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    // Método para detectar colisión con el inventario
    public boolean colisionaConInventario(Rectangle inventarioBounds) {
        return this.getBounds().intersects(inventarioBounds);
    }
    
    // Lógica para comprar producto
    public void comprarProducto() {
        ticksParaPagar++;
        if (ticksParaPagar == 40) {
            if (PantallaAnimacion.getInvPizza().isEmpty() == false) {
                inventarioPizzas.push(PantallaAnimacion.getInvPizza().getInventarioPizzas().pop());    
            }
            ticksParaPagar = 0;            
        }
    }

    // Lógica para consumir producto
    public void consumir() {
        ticksParaConsumir++;
        inventarioPizzas.peek().cantidadDeUso--;
        if (ticksParaConsumir == 50) {
            if (inventarioPizzas.peek().cantidadDeUso <= 0) {
                inventarioPizzas.getInventarioPizzas().pop();
                incrementarPizzasConsumidas();
                System.out.println(getPizzasConsumidas());
            }
            ticksParaConsumir = 0;
        }
    }
    
    // Actualiza la posición del comprador
    private void actualizarPosicion() {
        estaMoviendose = false;
    }

    // Verifica los movimientos del jugador
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

    // Reinicia el movimiento
    public void resetearMovimiento() {
        estadoDeEntradas.replace(KeyEvent.VK_A, false);
        estadoDeEntradas.replace(KeyEvent.VK_D, false);
    }

    // Actualiza la animación
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

    // Renderiza al comprador en pantalla
    public void renderizarComprador(Graphics g) {
        g.drawImage(hojaDeAnimacion[accionActual][indiceAnimacion], (int) x + xVolteado, (int) y,
                anchoHoja * anchoVolteado, alturaHoja, null);
        inventarioPizzas.renderizarInventario(g,x);
    }

    // Establece la acción actual del comprador
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

    // Reinicia la animación del comprador
    private void resetearAnimacion() {
        tickAnimacion = 0;
        indiceAnimacion = 0;
    }
    
    public void incrementarPizzasConsumidas() {
        pizzasConsumidas++;
    }

    // Método para obtener el contador de pizzas consumidas
    public int getPizzasConsumidas() {
        return pizzasConsumidas;
    }

    public void cargarAnimaciones(int filas, int columnas, int anchoMarco, int altoMarco) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/Comprador2.png"));
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
