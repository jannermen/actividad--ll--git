CREATE DATABASE tienda_barrio;
USE tienda_barrio;

CREATE TABLE producto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  precio DECIMAL(10,2),
  cantidad INT
);

CREATE TABLE venta (
  id INT AUTO_INCREMENT PRIMARY KEY,
  producto_id INT,
  cantidad INT,
  total DECIMAL(10,2),
  fecha DATE
);

INSERT INTO producto (nombre, precio, cantidad) VALUES ('Arroz', 5000, 20);
INSERT INTO producto (nombre, precio, cantidad) VALUES ('Aceite', 12000, 10);