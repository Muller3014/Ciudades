package com.problema.ciudades;

import java.util.*;


public class CiudadApp {
    public static void main(String[] args) {
        Set<Ciudad> ciudades = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        int opcion;
        while (true) {
            System.out.println("\n--- Gestor de Ciudades ---");
            System.out.println("1. Añadir ciudad");
            System.out.println("2. Listar ciudades (por habitantes)");
            System.out.println("3. Buscar ciudades");
            System.out.println("4. Terminar y salir");
            System.out.print("Elige una opción: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1 -> addCity(ciudades, sc);
                    case 2 -> listarCiudades(ciudades);
                    case 3 -> buscarCiudades(ciudades, sc);
                    case 4 -> {
                        System.out.println("Saliendo...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, introduce un número.");
            }
        }
    }

    private static void addCity(Set<Ciudad> ciudades, Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("País: ");
        String pais = sc.nextLine().trim();
        System.out.print("Número de habitantes: ");
        int hab = Integer.parseInt(sc.nextLine().trim());
        Ciudad nueva = new Ciudad(nombre, pais, hab);
        if (ciudades.add(nueva)) {
            System.out.println("Ciudad añadida correctamente.");
        } else {
            System.out.println("¡La ciudad ya existe!");
        }
    }

    private static void listarCiudades(Set<Ciudad> ciudades) {
        List<Ciudad> lista = new ArrayList<>(ciudades);
        lista.sort(Comparator.comparingInt(Ciudad::getHabitantes));
        System.out.println("\nCiudades ordenadas por habitantes:");
        lista.forEach(System.out::println);
    }

    private static void buscarCiudades(Set<Ciudad> ciudades, Scanner sc) {
        System.out.print("Introduce nombre o país a buscar: ");
        String termino = sc.nextLine().trim().toLowerCase();
        boolean encontrado = false;
        for (Ciudad c : ciudades) {
            if (c.getNombre().toLowerCase().contains(termino) || c.getPais().toLowerCase().contains(termino)) {
                System.out.println(c);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron ciudades.");
        }
    }
}
