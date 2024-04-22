# Biblioteca

Este es un proyecto de gestión de biblioteca desarrollado en Java utilizando Hibernate y MySQL.

## Configuración

1. Clona este repositorio en tu máquina local utilizando Git:

git clone https://github.com/tu-usuario/biblioteca.git


2. Configura la base de datos MySQL. Puedes encontrar el script SQL en `src/main/resources/database/schema.sql`. Ejecútalo en tu servidor MySQL para crear la base de datos y las tablas necesarias.

3. Abre el archivo `src/main/resources/hibernate.cfg.xml` y ajusta la configuración de Hibernate según tu entorno (nombre de usuario, contraseña, etc.).

4. Compila el proyecto utilizando Maven:

mvn clean install


5. Ejecuta la aplicación:

java -jar target/biblioteca.jar


## Uso

### Libros Disponibles para Préstamo

Para obtener la lista de libros disponibles para préstamo, selecciona la opción correspondiente en el menú principal de la aplicación.

### Historial de Préstamos por Lector

Para ver el historial de préstamos por lector, selecciona la opción correspondiente en el menú principal e ingresa el ID del lector.

## Contribuciones

Las contribuciones son bienvenidas. Si encuentras algún problema o tienes alguna mejora, no dudes en abrir un issue o enviar un pull request.

## Licencia

Este proyecto está bajo la [Licencia MIT](LICENSE).
