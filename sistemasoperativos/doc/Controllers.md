# Controllers

## Introduccción

Los controladores son clases de java que se encargan de controlar cada una de las ventanas de nuestra aplicación. Cada uno de ellos tiene variables para los distintos componentes de las ventanas. A su véz poseen métodos que serán disparados por eventos sobre los elementos. 

###### addGroupPrompt

Esta ventana se encarga de tomar los datos para agregar grupos al sistema operativo. El dato que se requiere es el nombre del nuevo grupo. En caso de que el grupo ya exista el proceso se abortará. El nombre del grupo debe ser un String alphabético sin espacios ni símbolos o números.

###### addUserPrompt

Esta ventana se encarga de tomar los datos para agregar usuario al sistema operativo. El dato que se requiere es el nombre del nuevo usuario, contraseña y si será un usuario SUDO o no. El nombre del usuario debe ser un String alphabético sin espacios ni símbolos o números.

###### addUserToGroupPrompt

Esta ventana se encarga de tomar los datos para agregar usuario a un grupo del sistema operativo. El dato que se requiere es el nombre del usuario que se quiere agregar y el nombre del grupo al que se lo quiere agregar. El proceso se abortará si el usuario no existe, el grupo no existe o el usuario ya se encuentra en el grupo.
