<h1 align="center" id="title">Practicando Springboot - Literalura</h1>

Literalura es una aplicación de consola desarrollada en Java con Spring Boot, que permite realizar diversas consultas relacionadas con libros y autores de la [API de Gutendex](https://gutendex.com/).

  
  
<h2>🧐 Características</h2>

*   **Buscar libro por título:** El programa utiliza la API de Gutendex para permitir al usuario buscar libros por título y mostrar en la consola información correspondiente al libro buscado.
*   **Mostrar libros registrados:** Cada vez que se realiza la busqueda de un libro, este es agregado a una base de datos gestionada con PostgreSQL, de la cual se pueden consultar los libros que han sido buscados.
*   **Mostrar autores registrados:** Al buscar un libro, el autor es añadido a una base de datos específica que contiene a todos los autores de los libros que han sido buscados, de los cuales se muestra cierta información relevante en consola.
*   **Mostrar autores vivos en un determinado año:** Los usuarios pueden ver todos los autores de los libros que han sido buscados, y que estaban vivos en un año específico.
*   **Mostrar libros por idioma:** El programa permite mostrar los libros de un idioma específico dentro de los idiomas que han sido registrados a la base de datos.

<h2>🛠️ Pasos para ejecutar el programa:</h2>
<ol>
  <li>Clona el repositorio en tu computadora.</li>
  <li>Abre el proyecto en tu IDE preferido.</li>
  <li>Ejecuta la clase <b>LiteraluraApplication</b> para iniciar el programa.</li>
  <li>Sigue las instrucciones en la consola para interactuar con el programa.</li>
</ol> 
  
  
<h2>💻 Tecnologías utilizadas:</h2>

* **Java:** Lenguaje de programación principal utilizado para desarrollar la aplicación.
* **Spring Boot:** Framework utilizado para simplificar el desarrollo de la aplicación.
* **Maven:** Herramienta de gestión de proyectos y comprensión de software.
* **API de Gutendex:** API utilizada para buscar y obtener información sobre libros.
* **PostgreSQL:** Sistema de gestión de bases de datos utilizado para almacenar y gestionar los datos de la aplicación.
