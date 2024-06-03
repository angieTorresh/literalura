package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvertirDatos;
import com.aluracursos.literalura.service.appService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class Principal {
    private final appService appService;
    private Scanner entrada = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvertirDatos convertir = new ConvertirDatos();
    private List<Libro> libros;

    String menu = """
            Por favor ingresa el número de la opción que deseas realizar:
            1 - Buscar libro por titulo
            2 - Mostrar libros registrados
            3 - Mostrar autores registrados
            4 - Mostrar autores vivos en un determinado año
            5 - Mostrar libros por idioma
            0 - Salir
            """;

    @Autowired
    public Principal(appService appService) {
        this.appService = appService;
    }

    public void muestraElMenu() {
        var opcion = -1;

        while (opcion != 0) {
            System.out.println(menu);
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivosDeterminadoAnio();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Por favor escribe el titulo del libro que deseas buscar");
        String tituloBuscar = entrada.useDelimiter("\n").next().replace(" ", "+").toLowerCase();
        try {
            var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloBuscar);
            var datos = convertir.obtenerDatos(json, Datos.class);
            for (DatosLibro datosLibro : datos.resultados()) {
                if (datosLibro.titulo().toLowerCase().contains(tituloBuscar.replace("+", " "))) {
                    appService.guardarLibro(datosLibro);
                    Libro libro = new Libro(datosLibro);
                    System.out.println(libro);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("No se encontraron resultados");
        }
    }

    private void mostrarLibrosRegistrados() {
        appService.mostarLibros();
    }

    private void mostrarAutoresRegistrados() {
        appService.mostrarAutores();
    }

    private void mostrarAutoresVivosDeterminadoAnio() {
        System.out.println("Por favor escribe el año que deseas buscar");
        int anio = entrada.nextInt();
        entrada.nextLine();
        appService.mostrarAutoresVivosDeterminadoAnio(anio);
    }

    private void mostrarLibrosPorIdioma() {
        System.out.println("Actualmente, se encuentran los siguientes idiomas registrados: ");
        appService.mostrarIdiomas();
        try {
            System.out.println("Por favor escribe el idioma que deseas buscar");
            String idioma = entrada.nextLine();
            while (idioma.length() != 2) {
                    System.out.println("Por favor ingresa la abreviatura del idioma en dos letras: ");
                    idioma = entrada.nextLine();
                }

            libros = appService.buscarLibrosPorIdioma(idioma);

            if (libros.isEmpty()) {
                throw new Exception("No se encontraron resultados.");
            }

            libros.forEach(System.out::println);
            } catch (Exception e) {
            System.out.println("No se encontraron resultados.");
        }
    }
}
