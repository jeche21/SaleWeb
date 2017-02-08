# SaleWeb
Página de gestion de compras y ventas online

###DESCRIPCIÓN###

La página web implementa un servicio de venta on-line y tiene la funcionalidad de poder proporcionar a los usuarios una navegacion por los productos de la pagina, permitir seleccionar los que quieren comprar, acumulandolos en un carrito de compra y posteriormente proceder a pagarlos para que se los lleven a domicilio.Para todo ello sera necesario tener una cuenta con los datos de cada cliente. Hemos dividido la pagina en dos partes:

- **Parte Pública**: Los usuarios simplemente podran navegar por la pagina buscando aquellos productos que deseen comprar, y ver los comentarios publicados por otro usuarios que ya tengan experiencia de compra en esa pagina, así como acceder a los datos de la empresa para contactar con ellos si surgen problemas, retrasos en el envio o cualquier otro tipo de duda.

- **Parte Privada**: Los usuarios tendran que registrarse, (introduciendo sus datos personales, tarjetas bancarias, direccion, correo electrónico, etc...) para poder pagar y que la compra se haga efectiva.

Pueden acceder al historial de pedidos realizados anteriormente, descargar las facturas de dichos pedidos, y cancelar un pedido siempre cuando no se haya procesado el pedido. Podrán comentar en su correspondiente apartado sus opiniones y sus experiencias de compra en dicha plataforma.
 
###ENTIDADES PRINCIPALES###
- **Articulos**: Son los diferentes productos disponibles de la pagina que los usuarios pueden comprar.
- **Pedidos**: Es el apartado donde cada usuario pueden consultar el historial de pedidos realizados anteriormente, descargarse las facturas, etc...
- **Usuarios**: Es cada una de las personas registradas en la pagina.
- **Comentarios**: Es un apartado en el cual cada uno de los usuarios puede opinar o aconsejar a otros usuarios basandose en su propia experiencia de compra en dicha plataforma.
- **Carrito**: Es un pedido provisional donde cada usuario puede añadir, modificar y eliminar cualquier producto que posteriormente quiere pagar.

###SERVICIO INTERNO###
Nuestra aplicacion web usara un servicio interno que controlara el stock del almacen de nuestra tienda on-line.
Comprobara si el producto que el usuario quiere comprar esta disponible o no, permitiendo la operacion en caso de que si haya disponibilidad. Si no la hay, el servicio interno mandara una notificacion de error informando al usuario de que el producto que quiere comprar no esta disponible en ese momento y ofrecera un tiempo estimado en el cual el producto sera repuesto.

###INTEGRANTES DEL GRUPO###
1. César Cuesta Vera: Correo Electrónico: c.cuestav@alumnos.urjc.es
2. Jesús Rosa Martín: Correo Electrónico: j.rosa@alumnos.urjc.es
3. Alvaro Gala Martinez: Correo Electronico: a.gala@alumnos.urjc.es
