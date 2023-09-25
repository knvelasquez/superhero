# Análisis y Diseño

Un breve resumen de como analicé y diseñe la solución del microservicio de gestión de superhéroes.

# Tabla de contenido
- [Volver al inicio](README.md)
- [Introducción](#introducción)
- [Arquitectura general](#arquitectura-general)
- [Dependencias](#dependencias)
- [Escalabilidad](#escalabilidad)
- [Arquitectura completa](#aquitectura-completa)
- [¿Donde obtengo un jwt valido?](#donde-obtengo-un-jwt-valido)


## Introducción
Mi enfoque principal se centró en la descomposición de actividades de manera distribuida delegando responsabilidades importantes en 
librerías internas como **FilterLibrary** y **JwtLibrary** 
Cada uno de estos elementos desempeña una función y un rol específico dentro del negocio, lo que permite una modularidad excepcional. 
La gran ventaja es que pueden ser implementados y escalados de forma independiente, lo que brinda una flexibilidad invaluable durante 
el proceso de despliegue continuo (CI/CD).


## Arquitectura General
Para garantizar la seguridad en el microservicio(**superhero**), he incorporado **Spring Security** en el contexto global. Esta adición es fundamental 
para proteger tanto las rutas como los recursos del microservicio, creando así una sólida capa de seguridad. 

Además, implementé reglas de **autorización** y **autenticación** mediante nuestra biblioteca interna, **FilterLibrary**, haciendo uso de **interceptores**. 

Esta configuración garantiza que únicamente los usuarios autorizados puedan acceder a los recursos y funcionalidades del microservicio.

También he empleado filtros **PreAutorize** que permiten el acceso a los recursos únicamente a los usuarios que posean el permiso 
adecuado establecido.

![Arquitecture](https://i.imgur.com/vu0KFxe.png)


## Dependencias
A continuación, se detallan las principales dependencias y bibliotecas utilizadas en el proyecto, junto con sus respectivas versiones:
- Spring Security **v6.1.3**
- **[FilterLibrary v1.0.0](https://github.com/knvelasquez/FilterLibrary)**
- **[JwtLibrary v1.0.0](https://github.com/knvelasquez/JwtLibrary)**

![Arquitecture](https://i.imgur.com/ZPTLZSO.png)


## Escalabilidad

En el proceso de diseño del microservicio, he dado especial importancia a la escalabilidad. Spring Boot, conocido por su alta escalabilidad,
permite que el microservicio se despliegue de manera eficiente en entornos de clúster o se contenerice según las necesidades de rendimiento,
como, por ejemplo, en Kubernetes

## Arquitectura Completa
![Arquitecture](https://i.imgur.com/CIlcbNQ.png)

Cada solicitud **HTTP** enviada por el cliente pasa por un proceso de seguridad en el que interviene tanto el filtro de seguridad de **Spring Security** como el **FilterLibrary**.

**FilterLibrary** cuenta con dos interceptores de alcance **global**: el **Authorization Bearer Token Interceptor** y el **Jwt Based Authentication Handler Interceptor**. Ambos están disponibles y pueden ser incorporados en el flujo de procesamiento de la solicitud.

**Nota**: Es importante destacar que estos filtros no están activados de manera predeterminada, por lo que es necesario habilitarlos manualmente según sea necesario.

Cada filtro incluye internamente una **lista blanca** que permite agregar **recursos/controladores REST** que se excluirán de las reglas de autenticación. Un ejemplo de esto es el recurso **healtcheck**, el cual está incluido en la **lista blanca** y, por lo tanto, queda exento de las reglas de autenticación.

Es importante mencionar que **FilterLibrary** ofrece la posibilidad de crear nuevos **interceptores** de alcance **Local**, como es el caso del **Super Her Handler Interceptor**. Este interceptor se encarga de validar la presencia de un encabezado denominado **SUPERHERO** en cada solicitud. Esto resulta útil cuando un microservicio necesita crear sus propios **interceptores** de forma **local**.

La cadena de **interceptores** establecidos en el flujo de procesamiento de la solicitud es la siguiente:

- **Authorization Bearer Token Interceptor** 
  - Este interceptor busca el encabezado **Authorization** en la solicitud
    - Si lo encuentra, devuelve **true**
      - Permitiendo que la cadena de interceptores continúe con el procesamiento de la solicitud
    - En caso contrario, generará una excepción indicando lo siguiente: 
      - Exception

        
- **Super Hero Handler Interceptor** 
  - Este interceptor busca el encabezado **SUPERHERO** en la solicitud.
    - Si lo encuentra, devuelve **true**
      - Lo que permite que la cadena de interceptores continúe con el procesamiento de la solicitud.
    - En caso contrario, generará una excepción que indicará lo siguiente:
      - Exception

- **Jwt Based Autrhentication Handler Interceptor**
  - Descompila el token **JWT** recibido
    - Verifica su validez utilizando la funcionalidad extendida de **JwtLibrary**
    - Lo que permite determinar si está **expirado**, si es **válido**, entre otras comprobaciones.
    - Si el token no es válido
      -  Generará una respuesta que indicará su invalidez.
    - En el caso de un JWT válido
      - Procederá a extraer los **claims/authorities**
      - Una vez obtenidos, se realizará la autenticación en el contexto de **Spring Security.**
        - Esta autenticación permitirá que los filtros **PreAuthorize** determinen si el **authority** incluido en el JWT permite el acceso al recurso solicitado.
      - En caso de tener el **authority** correcto, se obtendrá la respuesta del recurso.
      - En caso contrario, se generará un error
        - **Access Denied Exception**


## ¿Dónde obtengo un jwt válido?
### Para poder consumir cada uno de los recursos expuesto por el microservicio

El microservicio Superhero ha sido contenerizado para facilitar su ejecución en un contenedor de **Docker**. Predeterminadamente, este servicio es 
accesible a través del puerto **8080**, aunque es posible modificar esta configuración para utilizar otro puerto.

Además, hemos contenerizado también el **JwtAuth**, que se ejecuta de forma independiente en su propio contenedor. Este servicio se encuentra 
disponible en el puerto **8082** de forma predeterminada, aunque igualmente es posible realizar modificaciones en la configuración de dicho 
puerto según sea necesario.

![Arquitecture](https://i.imgur.com/ACkvZnC.png)

Ambos contenedores se ejecutan de forma independiente en sus propias redes internas aisladas.

- **JjwtAuth** recibe el **idUser** y valida los **privilegios/roles** asociados a ese usuario en su base de datos.
- Luego, obtiene la lista de **privilegios/roles** en forma de **authorities**, que es reconocida por **Spring Security**.
- Finalmente, codifica la respuesta en un token **JWT** utilizando las dependencias internas de **JSON Web Token**
- Y  finalmente envía la respuesta al cliente.

Para obtener más detalles sobre **[Jwtauth](https://github.com/knvelasquez/jwtauth)**, puedes consultar su [repositorio de Github](https://github.com/knvelasquez/jwtauth).
