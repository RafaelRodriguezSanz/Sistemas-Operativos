# Introducción

Primero se debe de setear la cantidad de procesadores que se quiere simular.

## Agregar Procesos

Para agregar procesos se puede hacer mediante el comando `add `en la consola.

Luego se deberá pasar una línea de procesos formateada de la siguiente forma:

```
Prioridad,Nombre,Dueño,TiempoDeEjecución,TiempoBloqueado,TiempoParaDesbloquearse
```

Pueden agregarse varios procesos concatenado la línea anterior con la de otro proceso con una coma.

Ej:  `1,P1,OS,10000,500,1000,2,P2,OS,1000,500,20000,3,P3,OS,30000,500,1000`

## Bloquear procesos

Para blockear procesos se puede hacer mediante el commando `block` en la consola.

## Desbloquear procesos

Para desblockear procesos se puede hacer mediante el commando `unblock` en la consola.

## Suspender Procesos

Para agregar suspender se puede hacer mediante el comando `suspend `en la consola.

## Dessuspender procesos

Para blockear dessuspender se puede hacer mediante el commando `unsuspend` en la consola.

## Matar procesos

Para matar dessuspender se puede hacer mediante el commando `kill` en la consola.

## Cambiar prioridad a procesos

Para cambiar la prioridad de un proceso se puede hacer mediante el commando `priority` en la consola.

## Mostrar procesos

Para mostrar los procesos y sus estados se puede hacer mediante el commando `show` en la consola.

## Mostrar procesos blockeados en orden

Para mostrar los procesos blockeados en orden que se irán desbloqueando se puede hacer mediante el commando `show-nexts` en la consola.
