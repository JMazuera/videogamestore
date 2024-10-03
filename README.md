# API Rest de Tienda de Videojuegos

Esta aplicación es una API Rest diseñada con una arquitectura hexagonal. Permite la gestión de una tienda de videojuegos, proporcionando operaciones CRUD tanto para los videojuegos como para los clientes. Además, se han implementado pruebas unitarias para asegurar la calidad y funcionalidad del código.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación utilizado para desarrollar la aplicación.
- **Spring Boot**: Framework para facilitar la creación de aplicaciones Java.
- **JPA**: Proporciona una forma estándar de manejar la persistencia de datos.
- **MySQL**: Sistema de gestión de bases de datos utilizado para almacenar la información.
- **JUnit**: Framework de pruebas para realizar pruebas unitarias.
- **Mockito**: Herramienta para crear mocks en pruebas unitarias.

## Características

- **CRUD para Videojuegos**: Permite crear, leer, actualizar y eliminar videojuegos.
- **CRUD para Clientes**: Permite gestionar la información de los clientes.
- **Pruebas Unitarias**: Implementadas para garantizar el correcto funcionamiento de la lógica de negocio.

## Configuración

Para utilizar la base de datos MySQL, es necesario configurar el nombre de usuario y la contraseña en el archivo `build.gradle`. Asegúrate de que el archivo contenga la siguiente configuración:

```groovy
dependencies {
spring.datasource.username = 'tu_usuario'
spring.datasource.password = 'tu_contraseña'
}
