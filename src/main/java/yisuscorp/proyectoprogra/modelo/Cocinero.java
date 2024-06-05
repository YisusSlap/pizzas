/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.modelo;

/**
 *
 * @author jesus
 */
import yisuscorp.proyectoprogra.modelo.Constantes.constantesCocinero;
import yisuscorp.proyectoprogra.vista.PantallaAnimacion;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.Instant;
import javax.imageio.ImageIO;

public class Cocinero extends Entidad {
    private int velocidadMovimiento = 2;
    private Inventario inventarioPizzas = new Inventario(2);
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
    }

    public void logicaCocinero() {
        actualizarPosicion();
        actualizarHitbox();
        elegirDireccion();
        actualizarFrameAnimacion();

        if (estaMoviendose) {
            checarLocalizacion();
        } else {
            if (estaEnCocina) {
                if (inventarioPizzas.hayEspacioParaPizza()) {
                    crearPizza();
                }
            }
            if (mesitaAlcanzable) {
                if (PantallaAnimacion.getInvPizza().hayEspacioParaPizza() && !inventarioPizzas.isEmpty()) {
                    PantallaAnimacion.getInvPizza().push(inventarioPizzas.getInventarioPizzas().pop());
                }
            }
        }
        setAccionActual();
    }

    private void actualizarPosicion() {
        estaMoviendose = false;
    }

    public void checarLocalizacion() {
        if (x < 800 && x > 600) {
            estaEnCocina = true;
        } else {
            estaEnCocina = false;
        }
        if (x > 600 && x < 600 + 130) {
            mesitaAlcanzable = true;
        } else {
            mesitaAlcanzable = false;
        }
    }

    public void elegirDireccion() {
        if (inventarioPizzas.isEmpty() && !estaEnCocina) {
            xVolteado = ancho;
            anchoVolteado = -1;
            x += velocidadMovimiento;
            estaMoviendose = true;
        }
        if (!inventarioPizzas.hayEspacioParaPizza() && !mesitaAlcanzable && x >= 600) {
            xVolteado = 0;
            anchoVolteado = 1;
            x -= velocidadMovimiento;
            estaMoviendose = true;
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
    private final int velocidadAnimacion = 9;
    private int accionActual = constantesCocinero.INACTIVO;
    private boolean estaMoviendose = false, corriendo = false;

    final int anchoHoja = 65 * 2;
    final int alturaHoja = 50 * 2;

    public void renderizarCocinero(Graphics g) {
        g.drawImage(hojaDeAnimacion[accionActual][indiceAnimacion], (int) x + xVolteado, (int) y, anchoHoja * anchoVolteado, alturaHoja, null);
        inventarioPizzas.renderizarInventario(g, x);
    }

    private void actualizarFrameAnimacion() {
        tickAnimacion++;
    if (tickAnimacion >= velocidadAnimacion) {
        tickAnimacion = 0;
        indiceAnimacion++;
        if (indiceAnimacion >= constantesCocinero.longitudAccion(accionActual)) {
            indiceAnimacion = 0;
            if (estaMoviendose && !corriendo) {
                corriendo = true;
            }
        }
    }
    }

    public void setAccionActual() {
        int animacionInicio = accionActual;
        if (estaMoviendose) {
            if (corriendo) {
                accionActual = constantesCocinero.CORRER;
            }
        } else {
            if (estaEnCocina) {
                accionActual = constantesCocinero.COCINAR;
            } else {
                accionActual = constantesCocinero.INACTIVO;
            }
        }
        if (animacionInicio != accionActual) {
            resetearAnimacion();
        }
    }

    private void resetearAnimacion() {
        tickAnimacion = 0;
        indiceAnimacion = 0;
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

