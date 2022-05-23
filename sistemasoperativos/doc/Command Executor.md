# Command Executor

## Propósito

El propósito del command executor es hacer el manejo interno de los comandos de shell que se utilizarán a lo largo del proyecto. Debe manejar las credenciales del usuario que lo ejecuta, la carpeta donde ejecuta los comandos y los permisos SUDO sobre los comandos.

Así mismo, es capaz de ejecutar también los scripts o una lista de comandos.

## Campos de clase

Dentro de la clase CommandExecutor nos encontramos:

* processBuilder
* output
* errorCode
* password
* username

Para poder ejecutar los comandos hacemos uso de una librería de java llamada ProcesBuilder. Esta nos permite crear una instancia de consola, ejecutar ciertos comandos y redirigir la salida a una variable.

Al instanciar la clase por primera vez se crea un process builder sobre el directorio home/ . Este process builder es inicializado con la contraseña y usuario que el usuario ingresó al iniciar la aplicación. Este usuario debe ser SUDO para poder ejecutar gran parte de los comandos.

Una vez agregan y ejecutan los comandos o scripts, se pueden ejecutar y como salida, se guarda la salida de la consola creada en el campo output y el código de error  (si lo hay) en la variable errorCode.

Por último, es el encargado de setear el ambiente y los comandos que se ejecutarán previo a los ingresados. En  nuestro caso se agregan los comandos `/bin/bash` y `-c` . Esos hacen que se ejecutan los commandos en una bash de linux y que se interpete el string de los comandos que se le pasen.  A los comandos se le "bypaseea" la contraseña y el usuario para ejecutarlos como administrador.  Esto se hace con el siguiente comando para algún comando `echo password | sudo -S -u user -s command` .

## Métodos

###### Setters() & Getters()

Tenemos setters y getters para todas las variables.

###### public boolean execute()

El método execute ejecuta los commandos que se encuentren en la lista de comandos del procesBuilder. A su vez guarda la salida en la variable output y el código de error  (si lo hay) en la variable errorCode.

###### public CommandExecutor()

Es el constructor principal de nuestra clase. Este método construye la instancia de la clase seteando el proces builder para ejecutarse desde la carpeta /home.

###### public void addCommand(String command)

Este método agrega un comando nuevo al process builder que ya se encuentre creado. En caso de que no haya commandos agregados todavía, se agragan los comandos `/bin/bash` y `-c`  además del que se pase como parámetro.

###### public void addCommand(String[] commands)

Este método agrega los comandos pasados como parámetro en un arreglo de Strings al process builder. Hace uso el método anterior para irlos agregando de a uno.

###### public void addScript(String scriptName)

Este método se encarga de agregar un script al process builder sin argumentos. El script debe ubicarse en la carpeta scripts del proyecto.

###### public void addScript(String scriptName,String arguments)

Este método se encarga de agregar un script con sus respectivos argumentos al process builder. El script debe ubicarse en la carpeta scripts del proyecto.

###### publicList List\<String\> getCommands()

Este método devuelve una lista de los comandos que tiene actualmente el process builder.

###### public void showCommands()

Este método se encarga de imprimir en pantalla todos los comandos que se encuentran en el process builder de una forma amigable.
