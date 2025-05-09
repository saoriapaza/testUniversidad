-- Crear la base de datos si no existe y usarla (si aún no la tienes creada)
CREATE DATABASE IF NOT EXISTS universidad;
USE universidad;

-- Crear la tabla 'estudiantes' si no existe
CREATE TABLE IF NOT EXISTS estudiantes (
   id INT AUTO_INCREMENT PRIMARY KEY,
   nombre VARCHAR(50),
   apellido VARCHAR(50),
   correo VARCHAR(100) UNIQUE,
   estado BOOLEAN DEFAULT TRUE
);