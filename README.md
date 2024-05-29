**UNIVERSIDAD TECNOLÓGICA NACIONAL            INSTITUTO NACIONAL SUPERIOR DEL PROFESORADO TÉCNICO**

**PRIMER PARCIAL (Límite para la entrega: 18/06/2024 – 19:00) Policía Federal ([www.argentina.gob.ar/policia-federal-argentina](http://www.argentina.gob.ar/policia-federal-argentina))**

Desarrolle  un sistema para gestionar la información de la Policía Federal sobre la seguridad en algunas entidades bancarias, teniendo en cuenta que:

- Cada entidad bancaria se caracteriza por un código y por el domicilio de su Central.
- Cada entidad bancaria tiene más de una sucursal que también se caracteriza por un código y por el domicilio, así como por el número de empleados de dicha sucursal.
- Cada sucursal contrata, según el día, algunos vigilantes, que se caracterizan por un código y su edad. Un vigilante puede ser contratado por diferentes sucursales (incluso de diferentes entidades), en distintas fechas y es un dato de interés dicha fecha, así como si se ha contratado con arma o no.
- Por otra parte, se quiere controlar a las personas que han sido detenidas por asaltar las sucursales de dichas entidades. Estas personas se definen por una clave (código) y su nombre completo.
- Alguna de estas personas están integradas en algunas bandas organizadas y por ello se desea saber a qué banda pertenecen, sin ser de interés si la banda ha participado en el delito o no. Dichas bandas se definen por un número de banda y por el número de miembros.
- Asimismo,  es  interesante  saber  en  qué  fecha  ha  asaltado  cada  persona  una  sucursal. Evidentemente, una persona puede asaltar varias sucursales en diferentes fechas, así como una sucursal puede ser asaltada por varias personas.
- Igualmente, se quiere saber qué juez ha estado encargado del caso, sabiendo que un individuo, por diferentes delitos, puede ser juzgado por diferentes jueces. Es de interés saber, en cada delito, si la persona detenida ha sido condenada o no, y de haberlo sido, cuánto tiempo pasó o pasará en la cárcel. Un juez se caracteriza por una clave interna del juzgado, su nombre y los años de servicio.
- En ningún caso interesa saber si un vigilante ha participado en la detención de un asaltante.
- Al sistema podrán acceder tres tipos de usuarios: vigilantes (que sólo podrán consultar sus datos), investigadores (que podrán consultar todo) y administradores (que administrarán todo).

Para ello:

- Analice los requerimientos anteriores
- Determine los objetos requeridos para implementar ese sistema
- Establezca los atributos que deben tener estos objetos
- Fije los comportamientos que exhibirán estos objetos
- Especifique la forma en que los objetos deben interactuar entre sí para cumplir con los requerimientos del sistema

El sistema deberá utilizar abstracción, encapsulamiento, herencia, polimorfismo y persistencia (no BD). La E/S del sistema será exclusivamente por consola (no GUI).

Se deberán subir a **GitLab** o **GitHub** el ejecutable (en formato *jar*), el código fuente, la documentación (generada con *javadoc*) y los diagramas UML de caso-uso, de clases y uno de secuencia (generados con <http://plantuml.com/es> o [http://www.planttext.com](http://www.planttext.com/) y grabados en formato *png*).
