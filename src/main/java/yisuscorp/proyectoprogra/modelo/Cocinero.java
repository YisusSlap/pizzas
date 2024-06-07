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
    // Variables de instancia
    
    private Inventario inventarioTaco;
    private int numeroOrden = 0;
    private int tickCocinar = 0;
    private BufferedImage[][] hojaDeAnimacion;
    private int tickAnimacion;
    private int indiceAnimacion;
    private final int velocidadAnimacion = 10;
    private int accionActual = constantesCocinero.INACTIVO;
    private final int anchoHoja = 65 * 2;
    private final int alturaHoja = 50 * 2;
    private int xVolteado = ancho;
    private int anchoVolteado = -1;
    private int tacosElaborados = 0;

    // Constructor
    public Cocinero(float x, float y, int w, int h, int filas, int columnas) {
        super(x, y, w * 4, h * 4);
        inventarioTaco = new Inventario(1); // Inicialización de inventario
        cargarAnimaciones(filas, columnas, w, h);
        inventarioTaco.cargarImagenInventario();
        setAccionActual(constantesCocinero.INACTIVO);
    }

    // Método para la lógica del cocinero
    public void logicaCocinero() {
        actualizarHitbox();
        actualizarFrameAnimacion();

        // Comprueba si hay espacio para cocinar una pizza
        if (inventarioTaco.hayEspacioParaPizza()) {
            crearPizza();
            setAccionActual(constantesCocinero.COCINAR);
        } else {
            setAccionActual(constantesCocinero.INACTIVO);
        }

        // Transfiere la pizza cocinada al inventario del juego
        if (PantallaAnimacion.getInvPizza().hayEspacioParaPizza() && !inventarioTaco.isEmpty()) {
            PantallaAnimacion.getInvPizza().push(inventarioTaco.pop());
        }
    }

    // Método para crear una nueva pizza
    public void crearPizza() {
        SecureRandom n = new SecureRandom();
        int vidaPizza = n.nextInt(200) + 50;
        int precioPizza = vidaPizza + 50;
        tickCocinar++;

        if (tickCocinar == 200) {
            inventarioTaco.push(new Taco(0, 10, 20, 20, vidaPizza, precioPizza, numeroOrden));
            numeroOrden++;
            String fecha = String.valueOf(Instant.now());
            tickCocinar = 0;
            
            incrementarTacosElaborados();
        }
    }
    
    // Método para incrementar el contador de pizzas elaboradas
    public void incrementarTacosElaborados() {
        tacosElaborados++;
    }

    // Método para obtener el contador de pizzas elaboradas
    public int getTacosElaborados() {
        return tacosElaborados;
    }

    // Método para renderizar el cocinero en pantalla
    public void renderizarCocinero(Graphics g) {
        g.drawImage(hojaDeAnimacion[accionActual][indiceAnimacion], (int) x + xVolteado, (int) y, anchoHoja * anchoVolteado, alturaHoja, null);
        inventarioTaco.renderizarInventario(g, 750);
    }

    // Método para actualizar la animación del cocinero
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

    // Método para establecer la acción actual del cocinero
    public void setAccionActual(int accionActual) {
        this.accionActual = accionActual;
    }

    // Método para cargar las animaciones del cocinero
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