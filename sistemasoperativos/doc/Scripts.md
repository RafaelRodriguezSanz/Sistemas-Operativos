# Scripts

## Introducción

Los scripts que utilizamos están desarrollados en shell script. Podría extenderse este proceso a bat o a otros lenguajes para llevar el proyecto a Windows u otro sistema operativo. De forma alternativa podría adaptarse para ejecutar en PoweShell los scripts en shell script para llevarlo a Windows.

### copiaSeguridad.sh

Este script se ocupa de crear una copia de seguridad de un determinado usuario. Para utilizarlo basta con llamarlo con el siguiente commando.

> cd Scripts
>
> sudo sh copiaSeguridad.sh -u user

Se utiliza `-u user` para indicar el usuario al que se quiere realizar el backup. El script crea la carpeta `home/backups/user` para el usuario que se pasó como parámetro. Se creará un backup de la carpeta del usuario comprimida dentro de dicha carpeta con el nombre backup seguido de la fecha en que se realizó. En caso de que se quiera crear una única instancia de backup y se quiera sobreescribir se puede usar la opción `-r` de la siguiente forma:

> cd Scripts
>
> sudo sh copiaSeguridad.sh -u user -r

### createChrontab.sh

Este script se encarga de agregar un chrontab cada un minuto para ejecutar el script stats.sh . En caso de que ya se encuentre el chrontab, el script lo sobreescribe.

### stats.sh

Este script se encarga de tomar estadísticas del sistema y lo registra en un archivo llamado `stats.txt` en la carpeta `home/Estadisticas/` .
