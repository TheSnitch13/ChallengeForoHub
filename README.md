ForoHub API

API REST desarrollada con Spring Boot para la gestión de tópicos de un foro.
Este proyecto forma parte del Challenge Back-End de Alura + Oracle Next Education (ONE) y permite registrar, consultar, actualizar y eliminar tópicos, además de implementar seguridad con autenticación JWT.

Tecnologías utilizadas

Java 17+

Spring Boot 3

Spring Web

Spring Data JPA

Spring Security

JWT (JSON Web Token)

MySQL

Flyway

Maven

Swagger / OpenAPI (springdoc)

Lombok

Funcionalidades

La API permite:

Registrar nuevos tópicos

Listar todos los tópicos

Consultar un tópico por ID

Actualizar tópicos

Eliminar tópicos

Autenticación de usuarios

Generación de tokens JWT

Control de acceso mediante token

Documentación automática con Swagger

Seguridad

La API utiliza Spring Security + JWT para proteger los endpoints.

Flujo de autenticación:

El usuario envía sus credenciales a /login

La API valida el usuario

Se genera un token JWT

El token se envía en el header de las siguientes solicitudes

Header requerido:

Authorization: Bearer TOKEN
Endpoints principales
Autenticación
Login
POST /login

Body:

{
  "correoElectronico": "usuario@email.com",
  "contrasena": "123456"
}

Respuesta:

{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
Tópicos
Registrar tópico
POST /topicos

Body:

{
  "titulo": "Error con Spring Boot",
  "mensaje": "No puedo registrar un tópico",
  "autorId": 1,
  "cursoId": 1
}
Listar tópicos
GET /topicos

Soporta paginación.

Detalle de tópico
GET /topicos/{id}
Actualizar tópico
PUT /topicos/{id}
Eliminar tópico
DELETE /topicos/{id}
Documentación de la API

La API está documentada con Swagger / OpenAPI.

Accede a la interfaz en:

http://localhost:8081/swagger-ui/index.html

Desde allí puedes probar todos los endpoints.

Base de datos

Se utiliza MySQL como base de datos.

Las migraciones se gestionan con Flyway, que crea automáticamente las tablas necesarias al iniciar la aplicación.

Ejecutar el proyecto

Clonar el repositorio

git clone https://github.com/TU_USUARIO/forohub.git

Configurar la base de datos en application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

Ejecutar la aplicación

mvn spring-boot:run

Acceder a Swagger

http://localhost:8081/swagger-ui/index.html
Estructura del proyecto
forohub
 ├── controller
 ├── domain
 │   ├── topico
 │   ├── usuario
 │   ├── curso
 │   └── respuesta
 ├── infra
 │   └── security
 └── resources
Autor

Proyecto desarrollado por Alan
como parte del programa Oracle Next Education + Alura Latam.

Licencia

Este proyecto fue desarrollado con fines educativos.
