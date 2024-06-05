/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.modelo;

import java.awt.Graphics;

/**
 *
 * @author jesus
 */
public class Pizza extends Entidad {
    public int cantidadDeUso;
    public int precioPizza;
    public int numeroOrden;
    

    public Pizza(float x, float y, int w, int h,int cantidadDeUso,int precioPizza,int numOrden){
        super(x, y, w, h);
        this.cantidadDeUso=cantidadDeUso;
        this.precioPizza=precioPizza;
        numeroOrden=numOrden;

    }

    public void logicaPizza(){
       
    }

    public void renderizarPizza(Graphics g){
        
    }
    
    
}
