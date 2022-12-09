INSERT INTO clientes(nombre,apellido,sexo,telefono) VALUES('Jose','Perez','masculino',651212);
INSERT INTO clientes(nombre,apellido,sexo,telefono) VALUES('Juan','Rodriguez','masculino',675357);
INSERT INTO clientes(nombre,apellido,sexo,telefono) VALUES('Ana','Hidalgo','femenino',987654);
INSERT INTO clientes(nombre,apellido,sexo,telefono) VALUES('Rosa','Fernandez','femenio',356893);
INSERT INTO clientes(nombre,apellido,sexo,telefono) VALUES('Alfonso','Ariza','masculino',667899);
INSERT INTO clientes(nombre,apellido,sexo,telefono) VALUES('Rodrigo','Velazquez','masculino',765432);
INSERT INTO clientes(nombre,apellido,sexo,telefono) VALUES('Maria','Eslava','femenino',989007);
INSERT INTO clientes(nombre,apellido,sexo,telefono) VALUES('Elena','Herran','femenio',980768);


INSERT INTO productos(nombre,descripcion,precio,existencias) VALUES('portatil','portatil de 14 pulgadas',500.89,2);
INSERT INTO productos(nombre,descripcion,precio,existencias) VALUES('raton','raton inalambrico',20.59,10);
INSERT INTO productos(nombre,descripcion,precio,existencias) VALUES('teclado','teclado inalambrico',50.99,5);
INSERT INTO productos(nombre,descripcion,precio,existencias) VALUES('pantalla','pantalla de 30 pulgadas',159.89,3);
INSERT INTO productos(nombre,descripcion,precio,existencias) VALUES('auriculares','auriculares con cable',36.99,15);

INSERT INTO ventas(cantidad, subtotal, iva, total, id_cliente, id_producto) VALUES(2, 500.89, 21, 1001.78, 3, 1);
INSERT INTO ventas(cantidad, subtotal, iva, total, id_cliente, id_producto) VALUES(5, 36.99, 21, 184.95, 6, 5);