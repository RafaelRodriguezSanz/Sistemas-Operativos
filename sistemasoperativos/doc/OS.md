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
