/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.modelo;

/**
 *
 * @author jesus
 */
public class Registro {
    private int idSesion;
    private int tacosProducidos;
    private int tacosConsumidos;

    public Registro(int idSesion, int tacosProducidos, int tacosConsumidos) {
        this.idSesion = idSesion;
        this.tacosProducidos = tacosProducidos;
        this.tacosConsumidos = tacosConsumidos;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public int getTacosProducidos() {
        return tacosProducidos;
    }

    public void setTacosProducidos(int tacosProducidos) {
        this.tacosProducidos = tacosProducidos;
    }

    public int getTacosConsumidos() {
        return tacosConsumidos;
    }

    public void setTacosConsumidos(int tacosConsumidos) {
        this.tacosConsumidos = tacosConsumidos;
    }
}
