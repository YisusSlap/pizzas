/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.modelo;

/**
 *
 * @author jesus
 */
public class Constantes {
    public static class constantesComprador {
        public static final int INACTIVO      =  0;
        public static final int CAMINAR       =  3;
        public static final int ENCARRERARSE  =  1;
        public static final int DARSELAVUELTA =  4;
        public static final int CORRER        =  2;
        public static final int CAMINARPIZZA  =  5;
        public static final int CORRERPIZZA   =  6;
        public static final int CONSUMIR      =  7;
        public static final int AGARRARPIZZA  =  8;
        public static final int DEJARPIZZA    =  9;

        public static int longitudAccion(int accionComprador) {
            switch (accionComprador) {
                case INACTIVO:
                    return 8; // Número de frames para la acción INACTIVO
                case CAMINAR:
                    return 0; // Número de frames para la acción CAMINAR
                case ENCARRERARSE:
                    return 2; // Número de frames para la acción ENCARRERARSE
                // Resto de los casos...
                default:
                    return 1; // Valor predeterminado
            }
        }
    }
}