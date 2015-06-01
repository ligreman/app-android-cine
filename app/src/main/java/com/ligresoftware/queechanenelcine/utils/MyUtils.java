package com.ligresoftware.queechanenelcine.utils;

import java.util.List;

public class MyUtils {
    public static String implode(List<String> lista, String concatenador) {
        StringBuilder sb = new StringBuilder();
        int cuantos = lista.size();
        int contador = 1;

        for (String s : lista) {
            sb.append(s);

            if (contador < cuantos) {
                sb.append(concatenador);
            }
            contador++;
        }

        return sb.toString();
    }
}
