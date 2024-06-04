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
import static yisuscorp.proyectoprogra.modelo.Constantes.constantesComprador;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Comprador extends Entidad {
    private String nombre;
    private float velocidadMovimiento;
    private int xVolteado = 0;
    private int anchoVolteado = 1;
    private BufferedImage[][] hojaDeAnimacion;
    public HashMap<Integer, Boolean> estadoDeEntradas = new HashMap<>();
    private float velocidad;
    private int tipo = 1;
    private boolean estaMoviendose = false, corriendo=false;
    private int tickAnimacion;
    private int velocidadAnimacion = 37;
    private int indiceAnimacion;
    private int accionActual = constantesComprador.INACTIVO;
    
    final int anchoHoja=32*2;
    final int alturaHoja=41*2;
    

    public Comprador(float x, float y, int w, int h, int filas, int columnas, int tipoCar, float floatVel) {
        super(x, y, w * 4, h * 4);
        tipo = tipoCar;
        cargarAnimaciones(filas, columnas, w, h);
        velocidadMovimiento = floatVel;
        velocidad = velocidadMovimiento;
    }

    public void logicaComprador() {
        actualizarPosicion();
        actualizarHitbox();
        
        checarMovimientosPlayer();
        
        actualizarFrameAnimacion();
    }

    private void actualizarPosicion() {
        estaMoviendose = false;
    }

    private void checarMovimientosPlayer() {
        if (!estadoDeEntradas.get(KeyEvent.VK_D) && !estadoDeEntradas.get(KeyEvent.VK_A))
            return;
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
        g.drawImage(hojaDeAnimacion[accionActual][indiceAnimacion], (int) x + xVolteado, (int) y,
                anchoHoja * anchoVolteado, alturaHoja, null);
        
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

    public void cargarAnimaciones(int filas, int columnas, int anchoMarco, int altoMarco) {
        try{
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/Comprador2.png"));
        hojaDeAnimacion = new BufferedImage[filas][columnas];
        for (int y = 0; y < hojaDeAnimacion.length; y++) {
            for (int x = 0; x < hojaDeAnimacion[y].length; x++) {
                hojaDeAnimacion[y][x] = img.getSubimage(x * anchoMarco, y * altoMarco, anchoMarco, altoMarco);
            }
        }
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }
}
