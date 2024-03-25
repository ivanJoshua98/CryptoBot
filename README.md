# CryptoBot
Bot de Telegram para notificar todos los días la cotización de un criptoactivo.


### Instalación
```
https://github.com/ivanJoshua98/CryptoBot.git
```

### Setup de credenciales
#### Prerequisitos
Debemos crear previamente un bot de Telegram con BotFather y anotar las credenciales del mismo. https://core.telegram.org/bots/tutorial

Para poder levantar la base de datos, se necesita tener [Docker](http://www.docker.com). Docker nos permite levantar postgres desde una imagen sin tener que instalar el motor.

Nota en caso de usar Windows: Es mucho mas facil no instalar Docker de forma directa, sino instalarlo usando [Windows Subsystem for Linux](http://www.learn.microsoft.com/en-us/windows/wsl/install).

#### Procedimiento
En un archivo .env detallamos las credenciales necesarias. Ver el archivo env.example

### Iniciar
```
docker-compose up
```
Este comando levantará la base de datos junto con la aplicación.

### Tests Backend
```
./gradle test
```
Este comando ejecutará los test de la aplicacíon.
