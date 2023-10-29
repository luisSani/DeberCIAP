package com.mycompany.caiprogram;

import java.util.Random;
import java.util.Scanner;

public class CAIProgram {

    private static final Random randomNumbers = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String respuesta;

        do {
            runCAIProgram();
            System.out.println("¿Quieres permitir que otro estudiante pruebe el programa? (si/no)");
            respuesta = scanner.next();
            scanner.nextLine(); // Consumir la nueva línea
        } while (respuesta.toLowerCase().equals("si"));
    }

    private static void runCAIProgram() {
        int nivelDificultad;
        int tipoProblema;

        System.out.println("Bienvenido al programa de Instrucción Asistida por Computadora (CAI)!");
        System.out.println("Elige el nivel de dificultad (1-3): ");
        nivelDificultad = scanner.nextInt();

        System.out.println("Elige el tipo de problema aritmético (1-suma, 2-resta, 3-multiplicación, 4-división, 5-mezcla): ");
        tipoProblema = scanner.nextInt();

        int preguntasCorrectas = 0;

        for (int i = 0; i < 10; i++) {
            if (pregunta(nivelDificultad, tipoProblema)) {
                preguntasCorrectas++;
                System.out.println(obtenerRespuestaPositivaAleatoria());
            } else {
                System.out.println(obtenerRespuestaNegativaAleatoria());
                i--; // Repetir la misma pregunta si la respuesta es incorrecta
            }
        }

        double porcentajeCorrectas = (double) preguntasCorrectas / 10 * 100;

        System.out.println("Rendimiento del estudiante: " + porcentajeCorrectas + "%");

        if (porcentajeCorrectas < 75) {
            System.out.println("Por favor, pide ayuda adicional a tu instructor.");
        } else {
            System.out.println("¡Felicidades, estás listo para pasar al siguiente nivel!");
        }
    }

    private static boolean pregunta(int nivelDificultad, int tipoProblema) {
        int num1 = generarNumero(nivelDificultad);
        int num2 = generarNumero(nivelDificultad);
        int respuestaCorrecta = obtenerRespuestaCorrecta(num1, num2, tipoProblema);

        System.out.print("¿Cuánto es " + num1 + " ");

        if (tipoProblema != 5) {
            imprimirSignoOperacion(tipoProblema);
            System.out.print(" " + num2 + "? ");
        } else {
            imprimirSignoOperacion(obtenerOperacionAleatoria());
            System.out.print(" " + num2 + "? ");
        }

        int respuestaUsuario = scanner.nextInt();
        scanner.nextLine(); // Consumir esta línea para evitar problemas con el buffer

        return respuestaUsuario == respuestaCorrecta;
    }

    private static int obtenerOperacionAleatoria() {
        return randomNumbers.nextInt(4) + 1;
    }

    private static void imprimirSignoOperacion(int tipoOperacion) {
        switch (tipoOperacion) {
            case 1:
                System.out.print("+");
                break;
            case 2:
                System.out.print("-");
                break;
            case 3:
                System.out.print("*");
                break;
            case 4:
                System.out.print("/");
                break;
        }
    }

    private static int generarNumero(int nivelDificultad) {
        if (nivelDificultad == 1) {
            return randomNumbers.nextInt(10);
        } else if (nivelDificultad == 2) {
            return randomNumbers.nextInt(90) + 10;
        } else {
            return randomNumbers.nextInt(900) + 100;
        }
    }

    private static int obtenerRespuestaCorrecta(int num1, int num2, int tipoOperacion) {
        switch (tipoOperacion) {
            case 1:
                return num1 + num2;
            case 2:
                return num1 - num2;
            case 3:
                return num1 * num2;
            case 4:
                return num1 / num2;
            case 5:
                // En el caso de mezcla, generamos aleatoriamente una operación
                return obtenerRespuestaCorrecta(num1, num2, obtenerOperacionAleatoria());
            default:
                return 0;
        }
    }

    private static String obtenerRespuestaPositivaAleatoria() {
        String[] respuestasPositivas = {"¡Muy bien!", "¡Excelente!", "¡Buen trabajo!", "¡Sigue así!"};
        int indice = randomNumbers.nextInt(respuestasPositivas.length);
        return respuestasPositivas[indice];
    }

    private static String obtenerRespuestaNegativaAleatoria() {
        String[] respuestasNegativas = {"No. Por favor intenta de nuevo.", "Incorrecto. Intenta una vez más.",
                "¡No te rindas!", "No. Sigue intentando."};
        int indice = randomNumbers.nextInt(respuestasNegativas.length);
        return respuestasNegativas[indice];
    }
}
