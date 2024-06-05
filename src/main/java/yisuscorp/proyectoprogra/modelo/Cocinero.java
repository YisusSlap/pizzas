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

public class Cocinero extends Entidad {
    private int velocidadMovimiento = 2;
    private Inventario inventarioPizzas = new Inventario(1);
    private int numeroOrden= 0;
    private int tickCocinar = 0;
    private BufferedImage[][] hojaDeAnimacion;
    private boolean estaEnCocina = false;
    private boolean mesitaAlcanzable = false;
    private int xVolteado = ancho;
    private int anchoVolteado = -1;

    public Cocinero(float x, float y, int w, int h, int filas, int columnas) {
        super(x, y, w * 4, h * 4);
        cargarAnimaciones(filas, columnas, w, h);
        inventarioPizzas.cargarImagenInventario();
        setAccionActual(constantesCocinero.INACTIVO);
    }

    public void logicaCocinero() {
        actualizarHitbox();
        actualizarFrameAnimacion();

        if (inventarioPizzas.hayEspacioParaPizza()) {
            crearPizza();
            setAccionActual(constantesCocinero.COCINAR);
        } else{
            setAccionActual(constantesCocinero.INACTIVO);
        }
            
        if (PantallaAnimacion.getInvPizza().hayEspacioParaPizza() && !inventarioPizzas.isEmpty()) {
            PantallaAnimacion.getInvPizza().push(inventarioPizzas.pop());
        }
        
        
            
    }

    public void crearPizza() {
        SecureRandom n = new SecureRandom();
        int vidaPizza = n.nextInt(200) + 50;
        int precioPizza = vidaPizza + 50;
        tickCocinar++;

        if (tickCocinar == 200) {
            inventarioPizzas.push(new Pizza(0, 10, 20, 20, vidaPizza, precioPizza, numeroOrden));
            numeroOrden++;
            String fecha = String.valueOf(Instant.now());
            tickCocinar = 0;
        }
    }

    private int tickAnimacion;
    private int indiceAnimacion;
    private final int velocidadAnimacion = 10;
    private int accionActual = constantesCocinero.INACTIVO;
    private boolean estaMoviendose = false, corriendo = false;

    final int anchoHoja = 65 * 2;
    final int alturaHoja = 50 * 2;

    public void renderizarCocinero(Graphics g) {
        g.drawImage(hojaDeAnimacion[accionActual][indiceAnimacion], (int) x + xVolteado, (int) y, anchoHoja * anchoVolteado, alturaHoja, null);
        inventarioPizzas.renderizarInventario(g, 750);
    }

    private void actualizarFrameAnimacion() {
        tickAnimacion++;
        if (tickAnimacion >= velocidadAnimacion) {
            tickAnimacion = 0;
            indiceAnimacion++;
            if (indiceAnimacion >= 2) { // Solo hay dos estados de animación: Cocinar e Inactivo
                indiceAnimacion = 0;
            }
        }
    }

    public void setAccionActual(int accionActual) {
        this.accionActual=accionActual;
    }

    public void cargarAnimaciones(int filas, int columnas,int anchoMarco,int altoMarco) {
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
