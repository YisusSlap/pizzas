/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.modelo;

/**
 *
 * @author jesus
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entidad {
    protected float x,y;
    protected int ancho;
    protected int altura;
    protected Rectangle hitbox;
    public Entidad (float x,float y,int w,int h){
        this.x=x;
        this.y=y;
        altura=h;
        ancho=w;
        iniciarHitbox();

    }
    protected void drawHitbox(Graphics g){
        //para probar la hitbox
        g.setColor(Color.red);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    protected void iniciarHitbox() {
        hitbox = new Rectangle((int)x,(int)y,ancho,altura);
    
    }
    protected void actualizarHitbox (){
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }
    public Rectangle getHitbox (){
        return hitbox;
    }
}