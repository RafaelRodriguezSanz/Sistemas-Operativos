# Manual de Uso

## Estructura de carpetas

## Ejecución

Para ejecutar la aplicación hay que dirigirse a la carpeta raíz del proyecto y ejecutar el comando:

> mvn javafx:run -f "FullPathToPomXML"

## Testing

Para ejecutar las pruebas se puede utilizar el comando:  [^1]

> mvn test

## Reporting

Para generar reportes con los resultados de las [pruebas ejecutadas previamente](Testing), se debe ejecutar el comando:  [^1]

> mvn allure:report

Para ver el reporte se puede ejcutar el comando:

> mvn allure:serve

## Uso de Plugins

Se recomienda ampliamente el uso de los plugins instalados con Manen para manejar todas estas tareas de una forma más cencilla. Basta con abrir la pestaña de Maven dentro del Visual Studio Code y ver los plugins para cada unoa de las tareas.

[^1]: Se recomienda previamente ejecutar los comando `mvn clean ` , `mvn install` y `mvn compile`
