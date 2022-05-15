# OS

## Introducción

La clase OS se encarga de utilizar el Command Executor para ejecutar cada uno de los comandos del sistema operativo e interpretar la salida para dar una respuesta al usuario.

###### public static boolean checkSudo(String user, String password)

Este método recibe un usuario y una contraseña. El método se encarga de checkear si el usuario existe, si su contraseña es la indicada como parámetro y si el usuario es un usuario SUDO. En caso contrario devuelve `false`.

###### public static boolean groupExist(String groupName)

Este método se encarga de determinar si el grupo pasado como parámetro existe en el sistema operativo.

###### public static boolean createGroup(String groupName)

Este método se encarga de crear un grupo en el sistema operativo con el nómbre pasádo como parámetro. En caso de ya existir no se aborta la ejecución. En caso de que el nombre del grupo contenga caracteres no alfabéticos o espacios, también se aborta la ejecución.

###### public static boolean createUser(String userName, String password, boolean sudo)

Este método se encarga de crear un usuario nuevo en el sistema operativo con el nombre de usuario pasado como parámetro. En caso de que el usuario ya exista, el proceso es abortado. En caso de que el nombre de usuario contenga caracteres extraños o contenga caracteres no alfanuméricos también es abortado. El usuario es creado con la contraseña pasada como parámetro. En caso de que se indique sudo como `true` el usuario se crea con permisos SUDO. En caso caso contrario, se crea un usuario sin dichos permisos.

###### public static boolean modifyPrivilegie(String privilegies)

Este método se encarga de modificar privilegios con el comando `chmod` . Se le debe pasar como parámetro el privilegio que se quiere modificar. Consultar la documentación de `chmod` para más información.

###### public static boolean addUserToGroup(String userName, String group)

Este método se encarga de agregar un usuario a un determinado grupo en caso de que el usuario exista y el grupo también y el usuario no se encuentre ya en el grupo. En caso contrario el proceso es abortado.

###### public static boolean removeUserFromGroup(String userName, String group)

Este método se encarga de remover un usuario de un determinado grupo en caso de que el usuario exista y el grupo también y el usuario ya se encuentre en el grupo. En caso contrario el proceso es abortado.

###### public static String getUserInfo(String userName)

Este método se encarga de obtener la información del usuario que es pasado como parámetro. En caso de que el usuario no exista, se devolverá un String vacío.

###### public static String[] getUsers()

Este método se encarga de devolver una lista de los datos de todos los usuarios del sistema operativo.

###### public static boolean userExists(String userName)

Este método devuelve `true` si el usuario pasado como parámetro existe y `false` en caso contrario.

###### public static boolean userExists(int userID)

Este método devuelve `true` si el usuario pasado como parámetro existe y `false` en caso contrario. A diferencia del método anterior, se recibe como parámetro el userID y no el nombre de usuario.

###### public static String userID(String userName)

Este método devuelve el userID de un usuario pasado como parámetro. En caso de que no exista se devuelve un String vacío.

###### public static String groupID(String groupName)

Este método devuelve el groupID de un grupo pasado como parámetro. En caso de que no exista se devuelve un String vacío.

###### public static String userDescription(String userName)

Este método devuelve la descripticón del usuario pasado como parámetro si existe. En caso contrario se devolverá un String vacío.

###### public static boolean backupUser(String userName)

Este método se encarga de realizar el backup del usuario pasado como parámetro. En caso de que el usuario no exista el proceso es abortado. Este método no sobreescribe los backups anteriores.

###### public static boolean backupUser(String userName, boolean rewrite)

Este método se encarga de realizar el backup del usuario pasado como parámetro. En caso de que el usuario no exista el proceso es abortado. Este método no sobreescribe los backups anteriores. Adicionalmente se le pasa como parámetro un booleano para indicar si se quiere sobreescribir el backup anterior o no.

###### public static boolean cleanAllBackups()

Este método se encarga de borrar todos los backups que se encuentren guardados en el sistema.

###### public static boolean cleanUserBackups(String userName)

Este método se encarga de borrar todos los backups de un usuario en particular que se encuentren guardados en el sistema.

###### public static boolean removeGroup(String groupName)

Este método se encarga de borrar un grupo si existe. Se le pasa como parámetro el nombre del grupo que se quiere borrar. En caso de que el grupo no exista, el proceso es abortado.

###### public static boolean removeUser(String userName)

Este método se encarga de borrar un usuario si existe. Se le pasa como parámetro el nombre del usuario que se quiere borrar. En caso de que el usuario no exista, el proceso es abortado.

###### public static void addChrontab()

Este método se encarga de agregar un chrontab con el script de las estadísticas. En caso de que ya exista, se sobreescribe.

###### public static String getArchitectura()

Este método devuelve la arquitectura del sistema.

###### public static String getByteOrder()

Este método devuelve el ByteOrder del sistema.

###### public static String getCPUS()

Este método devuelve   del sistema.

###### public static String getThreadsPerCore()

Este método devuelve la cantidad de threads por núcleo del sistema.

###### public static String getThreadsPerSocket()

Este método devuelve la cantidad de hilos por socket del sistema.

###### public static String getSockets()

Este método devuelve la cantidad de sockets del sistema.

###### public static String getCPUModel()

Este método devuelve el modelo de la CPU del sistema.

###### public static String getMHz()

Este método devuelve la frecuencia de la CPU del sistema.

###### public static String getCPUPorcentage()

Este método devuelve el porcentaje de uso de la CPU del sistema.

###### public static String[] getProcesess()

Este método devuelve un arreglo de los procesos ejecutandose en el sistema.

###### public static String getFreeRAM()

Este método devuelve el porcentaje ram libre en el sistema.

###### public static String getCache()

Este método devuelve la cantidad de cache utilizada.

###### public static String getAvailableRAM()

Este método devuelve la cantidad de memoria RAM disponible.

###### public static String getRAMCache()

Este método devuelve la cantidad de cache de RAM del sistema.

###### public static String getRAMUsage()

Este método devuelve el porcentaje de uso de la memoria RAM del sistam.

###### public static String getDiskFreeSpace()

Este método devuelve el espacio libre del disco.

###### public static String getDiskUsage()

Este método devuelve el porsentaje del disco utilizado.

###### public static String getStats()

Este método devuelve todas las estadísticas del sistam.
