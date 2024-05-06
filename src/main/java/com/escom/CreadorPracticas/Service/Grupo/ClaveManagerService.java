package com.escom.CreadorPracticas.Service.Grupo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class ClaveManagerService {

    private static final String CARACTERES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LONGITUD_CLAVE = 6;

    public String generarClave() {
        SecureRandom random = new SecureRandom();
        StringBuilder claveBuilder = new StringBuilder(LONGITUD_CLAVE);

        for (int i = 0; i < LONGITUD_CLAVE; i++) {
            int indice = random.nextInt(CARACTERES.length());
            char caracter = CARACTERES.charAt(indice);
            claveBuilder.append(caracter);
        }

        return claveBuilder.toString();
    }

    public boolean validarClave(String claveIngresada, String claveAlmacenada) {
        // Convierte la clave ingresada a mayúsculas antes de comparar
        claveIngresada = claveIngresada.toUpperCase();
        return claveIngresada.equals(claveAlmacenada);
    }
/*
    public static void main(String[] args) {
        // Genera una clave aleatoria
        String claveGenerada = generarClave();
        System.out.println("Clave generada: " + claveGenerada);

        // Simula la validación de la clave
        String claveIngresada = "aBc123"; // Ejemplo de clave ingresada por un usuario
        if (validarClave(claveIngresada, claveGenerada)) {
            System.out.println("Clave válida");
        } else {
            System.out.println("Clave no válida");
        }
    }*/
}
