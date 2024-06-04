/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.controlador;

/**
 *
 * @author jesus
 */

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import yisuscorp.proyectoprogra.modelo.Comprador;
import yisuscorp.proyectoprogra.vista.PantallaAnimacion;

public class EntradasDeTeclado implements KeyListener{
    private PantallaAnimacion pantallaDeJuego;
    //public HashMap<Integer,Boolean> estadoDeEntradas =new HashMap<>();
    
    
    public EntradasDeTeclado(PantallaAnimacion pantallaDeJuego){
        this.pantallaDeJuego=pantallaDeJuego;
        //this.estadoDeEntradas.put(KeyEvent.VK_W, false);
        //this.estadoDeEntradas.put(KeyEvent.VK_A, false);
        //this.estadoDeEntradas.put(KeyEvent.VK_S, false);
        //this.estadoDeEntradas.put(KeyEvent.VK_D, false);
    }
    public EntradasDeTeclado(Comprador comprador){
        
        PantallaAnimacion.getComprador().estadoDeEntradas.put(KeyEvent.VK_W, false);
        PantallaAnimacion.getComprador().estadoDeEntradas.put(KeyEvent.VK_A, false);
        PantallaAnimacion.getComprador().estadoDeEntradas.put(KeyEvent.VK_S, false);
        PantallaAnimacion.getComprador().estadoDeEntradas.put(KeyEvent.VK_D, false);
    }

    /*public EntradasDeTeclado(Cocinero cocinero){
        
        PantallaAnimacion.getCocinero().estadoDeEntradas.put(KeyEvent.VK_UP, false);
        PantallaAnimacion.getCocinero().estadoDeEntradas.put(KeyEvent.VK_LEFT, false);
        PantallaAnimacion.getCocinero().estadoDeEntradas.put(KeyEvent.VK_DOWN, false);
        PantallaAnimacion.getCocinero().estadoDeEntradas.put(KeyEvent.VK_RIGHT, false);
    }*/

    public void addConfiguration(Integer input){

    }
    
        @Override
        public void keyTyped(KeyEvent e) {
        
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                PantallaAnimacion.getComprador().estadoDeEntradas.replace(KeyEvent.VK_W,true );
                
               
                break;
                case KeyEvent.VK_A:
                PantallaAnimacion.getComprador().estadoDeEntradas.replace(KeyEvent.VK_A,true ); 
                
                
                break;
                case KeyEvent.VK_S:
                PantallaAnimacion.getComprador().estadoDeEntradas.replace(KeyEvent.VK_S,true ); 
                break;

                case KeyEvent.VK_D:
                PantallaAnimacion.getComprador().estadoDeEntradas.replace(KeyEvent.VK_D,true ); 
                break;
                
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
            if (e.getKeyCode()==KeyEvent.VK_W) {
                PantallaAnimacion.getComprador().estadoDeEntradas.replace(KeyEvent.VK_W,false );    
            }
            if(e.getKeyCode()==KeyEvent.VK_A){
                PantallaAnimacion.getComprador().estadoDeEntradas.replace(KeyEvent.VK_A,false );
            }
            if(e.getKeyCode()==KeyEvent.VK_S){
                PantallaAnimacion.getComprador().estadoDeEntradas.replace(KeyEvent.VK_S,false );  
            }
            if(e.getKeyCode()==KeyEvent.VK_D){
                PantallaAnimacion.getComprador().estadoDeEntradas.replace(KeyEvent.VK_D,false );          
            }

            
            
        }


    
    
}