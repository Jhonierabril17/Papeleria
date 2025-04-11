# Papeleria
# ¿Qué son las consultas nativas?
Las consultas nativas te permiten ejecutar sentencias SQL tradicionales (te permiten ejecutar sentencias SQL tradicionales (como las que usas en PgAdmin o MySQL) directamente desde tu código Java , dentro de tus repositorios. Se llaman “nativas” porque usas SQL puro, en vez de JPQL (el lenguaje orientado a objetos de JPA).

# Cuándo usarlas?
* Cuando necesites una consulta compleja con JOIN,GROUP BY, etc.

* Cuando necesitas optimizar el rendimiento .

* Cuando prefieres escribir SQL tú mismo


# Consultas nativas
1. Listar las ventas que a realizado un empleado
2. cuales son los productos que ofrece un proveedor
3. mostrar cuales son las ventas que le ha realizado un empleado a un cliente
4. Cuales son los detalles de Ventas que ha hecho un empleado a un cliente


Como utilizar 1. Listar las ventas que a realizado un empleado atraves de postman

http://localhost:8080/Venta/empleado/"Id que requiera verificar"


Como utilizar 2. cuales son los productos que ofrece un proveedor

http://localhost:8080/Producto/proveedor/"Id del proveedor"

Como utilizar 3. mostrar cuales son las ventas que le ha realizado un empleado a un cliente

http://localhost:8080/Venta/empleado/"idEmpleado"/cliente/"idCliente"

4. Cuales son los detalles de Ventas que ha hecho un empleado a un cliente

http://localhost:8080/Detalle_Venta/empleado/idEmpleado/cliente/idCliente

