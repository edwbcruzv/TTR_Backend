# TTBackend

Modificado desde el servidor

Instrucciones:


Credenciales de usuario
usuario = root
contraseña = 123456789

acceder a Mysql 
>sudo mysql -u root -p

iniciar servidor
>sudo service mysql start

Detenerlo
>sudo service mysql stop

Reiniciarlo
>sudo service mysql restart

Ver el estado
>sudo service mysql status

otorgar permisos al usuario en una base de datos
>GRANT ALL PRIVILEGES ON nombre_de_base_de_datos.* TO 'nombre_de_usuario'@'localhost';

Crear el la base en mysql
>CREATE DATABASE casebuilder;

crear el .jar
>mvn package
