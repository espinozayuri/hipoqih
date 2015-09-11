# Introducción #

NMEA 0183 (o simplemente NMEA)es una especificación que combina datos y señal eléctrica para la comunicación entre dispositivos electrónicos marinos, entre ellos receptores GPS.

# Detalles #

NMEA, que utiliza un protocolo de comunicación serie que transmite caracteres ASCII, define como se transmiten datos en una sentencia (_sentence_) de un comunicador (_talker_) a uno o más receptores (_listeners_). También define los contenidos de cada tipo de mensaje, de forma que todos los receptores puedan interpretar los mensajes correctamente.
La comunicación se produce a 4800 baudios, con 8 bits de datos, sin paridad y con uno o más bits de parada.

# Reglas del protocolo #

  * El carácter inicial de cada mensaje es el signo del dólar ($).
  * Los siguientes cinco caracteres indican el tipo de mensaje.
  * Todos los campos de datos que siguen van delimitados por comas.
  * El primer carácter inmediatamente posterior al último campo de datos es un asterisco .
  * Inmediatamente después del asterisco hay una suma de verificación de dos dígitos.

# Referencias #
[NMEA en la Wikipedia](http://en.wikipedia.org/wiki/NMEA)