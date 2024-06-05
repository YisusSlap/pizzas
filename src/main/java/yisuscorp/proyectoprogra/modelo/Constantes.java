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
                    return 8; // Número de frames para la acción CAMINAR (ajustado)
                case ENCARRERARSE:
                    return 2; // Número de frames para la acción ENCARRERARSE
                case CORRER:
                    return 8; // Número de frames para la acción CORRER (ajustado)
                case DARSELAVUELTA:
                    return 4; // Número de frames para la acción DARSELAVUELTA (ejemplo)
                case CAMINARPIZZA:
                    return 8; // Número de frames para la acción CAMINARPIZZA (ejemplo)
                case CORRERPIZZA:
                    return 8; // Número de frames para la acción CORRERPIZZA (ejemplo)
                case CONSUMIR:
                    return 6; // Número de frames para la acción CONSUMIR (ejemplo)
                case AGARRARPIZZA:
                    return 4; // Número de frames para la acción AGARRARPIZZA (ejemplo)
                case DEJARPIZZA:
                    return 4; // Número de frames para la acción DEJARPIZZA (ejemplo)
                default:
                    return 1; // Valor predeterminado
            }
        }
    }
}
