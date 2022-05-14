# Introducción

El Objetivo de este proyecto es aplicar los conocimientos del curso de Sistemas Operativos 2022 de la Universidad Católica.

Los integrantes del equipo somos:

* Rafael Rodriguez

## Metodología

Decidimos usar una metodología agil al estilo Kanban. Es una metodología con la que ya habíamos trabajado, ameritaba ya que las tareas son bien concretas, no va a haber cambios en los requerimientos, se trata de un desarrollo de medio porte, y los tiempos de desarrollo son acotados con un deadline bien estipulado. Al ser 6 personas en el grupo podríamos facilmente distribuirnos actividades y avanzar en el desarrollo de la tarea. Hicimos uso de la herramienta Trello para realizar un[ tablero](https://trello.com/invite/b/GYm4rVU4/83cbca16015aa128ea561b83d9611313/obligatorio-so "Trello Kanban") para organizarnos.

## Lenguaje

El proyecto esta desarrollado en [Java](https://docs.oracle.com/en/java/). Se eligió este lenguaje porque es multiplataforma, conocido y ya tenemos conocimiento previo del mismo.

## Testing

Para la parte de testing utilizamos las herramientas Junit y TestFX. Junit es un framework para realizar pruebas unitarias para Java. Es una herramienta fácil de usar y que luego podemos integrar con Allure para generar reportes de las pruebas. A su vez, testFX es una extensión del framework de Junit. Este nos permite hacer pruebas de UI a la interfáz gráfica creada con JafaFX.

## Reportes

Se generarán reportes en formato HTML con la herramienta Allure. Esta herramienta nos permite ejecutar todas nuestas pruebas y generar reportes de forma automática.

## UI

La interfáz gráfica fue creada con JafaFX. Es un framework de Java para crear interfaces gráficas con archivos xml. Estos archivos fueron creados con una herramienta externa llamada SceneBuilder, la cual es un constructor de interfás gráfica para JavaFX.

## Dependencias

Todo el proyecto fue generado con un arquetipo de Maven. De esta forma podemos determinar las dependecias de nuestro proyecto en el archivo pom.xml el cual es usado por Maven para compilar nuestro proyecto, ejecutar pruebas, reportes, etc.

## Ambiente

El ambiente en el que desarrollamos es un Linux con la distribucción Ubuntu de 64 bits. Este ambiente se implementó dentro de una máquina virtual de VMWare. 

## IDE

Para desarrollar el código optamos por Visual Studio Code ya que es la herramienta que la moyoría maneja y es fácil de utilizar y contiene extensiones que facilitan el desarrollo y el uso de Java y Maven.

## Linter

Usamos el linter Sonarlint que nos ayuda a llevar un código más prolijo y mantenible.

## Documentación

Para documentar nuestro código utilizamos una estructura de Markdowns con cáda una de las funcionalidades de nuestro código. Adicionalmente agregamos documentación con Javadoc para facilitar su uso.

## Versionado

Para el versionado utilizamos github ya que es una herramienta que en su versión gratuita nos es más que suficiente para el propósito de esta tarea. Cada uno de los desarrolladores tiene su propia rama donde trabajará las tareas que le correspondan.

## CI/CD

Para el continue integration y continue delivery utilizamos las herramientas que otorga github creando un .yml con el flow para ejecutar pruebas cada véz que se hacen commits al repositorio. Se podría integrar con Jenkins u otra herramienta del estilo, pero creemos que sobrepasa los propósitos de esta tarea.

## Acceso

Este ambiente tiene conexión al exterior por medio de un bridge a la red de nuestra máquina física. Nuestra máquina física tiene configurada una red local con la cuál nuestra máquina funciona como un Access Point. Haciendo uso de la herramienta XRDP (adaptación del protocolo RDP para Linux), podemos conectarnos a la misma atravéz de una red wifi privada y acceder a la máquina atravéz de la multisesión.

## Entregable

En un futuro crearemos un .jar ejecutable. Luego crearemos un .deb el cual podrá ser instalado en cualquier máquina Linux. (Esto todavía no se encuentra implementado).
