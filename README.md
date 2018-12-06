# Proyecto de Sistemas Operativos II

## Declaracion

### Problema de consistencia de cache
Implementar un cache de cliente en disco para un servidor remoto de archivos. 

El servidor tiene una carpeta asignada en el servidor para llevar el control de los archivos que ofrece remotamente. Cada archivo y directorio se define a partir de esta carpeta asignada. La carpeta asignada es el directorio raíz. 

Por ejemplo: C:\RootServer

A partir de esta carpeta, clientes pueden crear archivos y directorios. 
* Los clientes al conectarse al servidor, les debe aparecer en GUI los archivos y directorios que tiene el servidor remoto.
    * Los clientes pueden modificar la estructura de directorios. Esta modificación se debe programar a los clientes que están conectados con el servidor.
    * Los clientes deben notificar al servidor que quieren desmontar el FS, asi el servidor ya no realiza actualizaciones a su estructura.
    * Los archivos son de texto y editables.  	
    * Los archivos remotos se mantienen el cliente en el cache de disco
    * Cada modificación en el archivo de texto (puede usar notepad para edición), se debe de propagar el servidor de inmediato. Y el servidor debe proceder a invalidar las copias de este archivo que están en los clientes.
    * Se debe crear una interfaz remota para establecer comunicación entre el cliente y el servidor. Es obligatorio usar MIDDLEWARE
    * Se debe crear una GUI para mostrar la estructura de directorios.														
