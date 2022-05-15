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

El formato del `stats.txt` es algó del estilo:

>
> ####Statistics####
> 14/05/2022 19:21:01
>
> =============================================CPU=======================================
> Architecture: x86_64
> Byte Order: LittleEndian
> CPUs: 12
> Threads per core: 1
> Threads per socket: 3
> Sockets: 4
> Model: Intel(R)Core(TM)i7-8750HCPU@2.20GHz
> MHz: 2208.000
> =========================================PROCESESS=====================================
> PID	USER	%CPU	%MEM	COMMAND
> 385891	root	15.8	0.0	top
> 29648	rafael	10.5	6.8	code
> 29899	rafael	5.3	1.9	vsls-ag+
> 1	root	0.0	0.1	systemd
> 2	root	0.0	0.0	kthreadd
> 3	root	0.0	0.0	rcu_gp
> ...
> 385892	root	0.0	0.0	awk
> =============================================RAM=======================================
> Free RAM: 543MB
> Cache: 3409MB
> Free RAM: 519MB
> Total RAM: 7915MB
> Available: 3720MB
> Used RAM: 3821MB
> Cache: 3574MB
> RAM Usage: 2.07145%
> ============================================DISK======================================
> Disk Free Space: 42GB
> Disk Usage: 42%
