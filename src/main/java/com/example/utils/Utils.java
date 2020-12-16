package com.example.utils;

import java.util.Random;

public class Utils {
	//Funcion para obtener un numero para generar palabras aleatorias
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("El mínimo debe ser mayor que el máximo");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
