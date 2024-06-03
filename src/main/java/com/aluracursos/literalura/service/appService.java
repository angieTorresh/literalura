package com.aluracursos.literalura.service;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.DatosLibro;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class appService {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private static final Map<String, String> IDIOMAS = new HashMap<>();

    static {
        IDIOMAS.put("en", "inglés");
        IDIOMAS.put("es", "español");
        IDIOMAS.put("pt", "portugués");
        IDIOMAS.put("fr", "francés");
        IDIOMAS.put("fi", "finlandés");
        IDIOMAS.put("de", "alemán");
        IDIOMAS.put("it", "italiano");
        IDIOMAS.put("la", "latín");
        IDIOMAS.put("zh", "chino");
        IDIOMAS.put("ja", "japonés");
        IDIOMAS.put("ru", "ruso");
        IDIOMAS.put("sv", "sueco");
    }

    @Autowired
    public appService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    private void autorFormat(Autor autor) { System.out.println("----------------------------------------\n" +
            autor);
        List<Libro> libros = libroRepository.findByAutor(autor.getNombre());
        System.out.println("Libros: " + libros.stream().map(Libro::getTitulo).collect(Collectors.toList()) + "\n----------------------------------------");

    }

    public void guardarLibro(DatosLibro datosLibro) {
        Libro libro = libroRepository.findByTitulo(datosLibro.titulo());
        if (libro == null) {
            libro = new Libro(datosLibro);
            libroRepository.save(libro);
        }

        Autor autor = autorRepository.findByNombre(datosLibro.autor().get(0).nombre());
        if (autor == null) {
            autor = new Autor(datosLibro.autor().get(0));
            autorRepository.save(autor);
        }
    }

    public void mostarLibros() {
        libroRepository.findAll().forEach(libro -> System.out.println(libro));
    }

    public void mostrarAutores() {
        autorRepository.findAll().forEach(this::autorFormat);
    }

    public void mostrarAutoresVivosDeterminadoAnio(int anio) {
        autorRepository.findByAnioNacimientoLessThanEqualAndAnioMuerteGreaterThanEqual(anio, anio).forEach(this::autorFormat);

    }

    public void mostrarIdiomas() {
        Set<String> idiomasUnicos = new LinkedHashSet<>();
        libroRepository.findAll().forEach(libro -> idiomasUnicos.add(libro.getIdioma()));

        idiomasUnicos.forEach(idioma -> {
            String nombreCompleto = IDIOMAS.getOrDefault(idioma, "desconocido");
            System.out.println(idioma + " - " + nombreCompleto);
        });
    }

    public List<Libro> buscarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }
}
