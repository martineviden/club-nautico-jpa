# Proyecto Club Náutico

- El programa tiene que ser ejecutado desde el Main de la aplicación (mínimo) desde el propio IDE (la forma óptima será usando JUnit) 
- La BBDD puede ser MySQL, Oracle o H2Database 
- Caso de uso: 
  - Se quiere diseñar una base de datos relacional para gestionar los datos de los socios de un club náutico 
  - De cada socio se guardan los datos personales y los datos del barco o barcos que posee: número de matrícula, nombre, número del amarre y cuota que paga por el mismo 
  - Además, se quiere mantener información sobre las salidas realizadas por cada barco, como la fecha y hora de salida, el destino y los datos personales del patrón, que no tiene por qué ser el propietario del barco, ni es necesario que sea socio del club 
- El programa tiene que realizar todas las operaciones CRUD necesarias para cada una de las entidades definidas 
- Se deben anotar las entidades para utilizar los métodos de validación definidos por la especificación JSR 380 
- Las operaciones de consulta tienen que realizarse utilizando los distintos métodos aprendidos durante la formación (consultas predefinidas, JPQL, Criteria) (se deja a cada alumno la decisión de definir las consultas que obtengan datos necesarios para la gestión del club náutico y los métodos JPA empleados) 

`JPA Notebook - Apartado 2.7.4`

**Notas:** El proyecto utiliza H2Database.
