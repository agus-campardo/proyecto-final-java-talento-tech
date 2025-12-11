# 📚 Proyecto Final - Sistema de Gestión de Biblioteca  

Este proyecto forma parte del **Trabajo Práctico Final** del curso **Talento Tech - Java Backend (2do Cuatrimestre 2025)**.  
Consiste en el desarrollo de un sistema de gestión para una Biblioteca, implementado con **Spring Boot**, utilizando una implementación en capas:  
**Controller → Service → Repository → Model**

En la parte final, dejo [ideas para futuras mejoras](#ideas-de-futura-implementacion)


## Decisiones de Diseño y Justificaciones
Para este proyecto, se decidió modelar el funcionamiento de una biblioteca, en la cual se pueden solicitar libros en préstamo y devolverlo una vez utilizados. 
A continuación, se describirán las decisiones tomadas durante el diseño del sistema y los "requieres" necesarios a cumplir por el usuario: 

### Representación de los libros 
La biblioteca cuenta con un catálogo donde cada libro está  identificado por un título, autor, género e ISBN.  
Sin embargo, la biblioteca no registra cada ejemplar de manera individual, sino que maneja un conjunto de ejemplares por libro categorizados por título. 
Por ello, cada entidad 'Libro' posee un 'stock', que indica cuántas copias est+an disponibles para prestar, y un contador 'vecesPrestado', que nos servirá para estudiar la popularidad del mismo.
El stock debe ser un número entero positivo (>= 0) y se asume que el espacio de nuestra biblioteca es infinito, por lo que no hay límite de stock.
El ID es único en el sistema, por lo que si un libro ha sido eliminado, nadie tomará su ID. Simplemente no se podrá volver a usar. 

### Representación de los préstamos 
Cuando se solicita un préstamo, el sistema ha de asegurarse de que: 
- El libro exista en la biblitoeca.
- Cuente con al menos un ejemplar ('stock > 0')
Solo cumpliendo estas condiciones el libro podrá prestarse, disminuyendo su stock, aumentando la cantidad de veces que ha sido prestado y registrando un nuevo objeto 'Prestamo'.

De igual manera, cuando se devuelve un libro, el ID del préstamo debe existir y encontrarse activo (no haber sido devuelto previamente). 
En aquel caso, el stock del libro aumenta. 
Considero que el aumento a la cantidad de veces que ha sido prestado le corresponde a la instancia en donde se presta y no cuando se devuelve. No importa realmente cuándo se devuelve, sino que ya cuando se presta se modificaría sus atributos. Si no lo modificaría en aquel momento, es posible que ocurra que lleguen solicitudes de préstamos a libros que están en préstamo en ese momento o si quiero saber quién tiuene más veces de ser prestado, podria darme un resultado erroneo porque el libro ya fue prestado, no importa cuándo me lo han devuelto, en ese momento ya estaba prestado. 

La cantidad de préstamos activo no está acotada, asumimos que tenemos espacio suficiente para manejar la cantidad de préstamos que se considere conveniente. 

Al crearse un préstamo, el sistema asigna automáticamente la fecha del día mediante 'LocalDate.now()'. No queremos que el usuario deba específicar la fecha de forma manual. 

### Relación entre libros y préstamos
Cada préstamo mantiene una referencia al libro correspondiente. 
De esta forma, el sistema podrá conocer qué libro fue prestado y actualiza su stock o disponibilidad. 
Así, un libro no puede ser eliminada si tiene préstamos asociados. 

### Carga de datos
Si bien es posible precargar los libros en 'data.sql', no ocurre lo mismo con los préstamos. 
A los libros se les asigna su identificador ('id') solo después de ser insertados, por lo que no es posible conocer esos valores por adelantado. 
Por lo tanto, los préstamos deben crearse de forma manual mediante los endpoints correspondientes. 


## 🛠️ ¿Qué harramientas se utilizaron en este proyecto? 
- **Java**
- **Spring Boot**
- **Insomnia**             # Cliente para probar endpoints de la Biblioteca API 
- **Spring Web**
- **Spring Data JPA**
- **Hibernate / JPA**
- **H2 Database / MySQL**
- **Maven**

---

## 📂 Estructura del Proyecto

```text
src
└── main
    ├── java
    │   └── com
    │       └── techlab
    │           └── biblioteca
    │               ├── controller                      # Manejan las solicitudes a los endpoints
    │               │   ├── LibroController.java            # Endpoints /libros
    │               │   └── PrestamoController.java         # Endpoints /prestamos
    │               ├── model                           # Clases que representan las entidades 
    │               │   ├── Libro.java                      # Entidad Libro
    │               │   └── Prestamo.java                   # Entidad Prestamo
    │               ├── repository                      # Acceso a datos (DB, memoria, JPA)
    │               │   ├── LibroDBRepository.java          # Implementación DB para libros
    │               │   ├── LibroMemRepository.java         # Implementación en memoria
    │               │   ├── LibroRepository.java            # Interfez
    │               │   ├── LibroRepositoryJPA.java         # Implementación usando JPA
    │               │   ├── PrestamoDBRepository.java       # Implementación DB para préstamos
    │               │   ├── PrestamoMemRepository.java      # Implementación en memoria
    │               │   ├── PrestamoRepository.java         # Interfaz 
    │               │   └── PrestamoRepositoryJPA.java      # Implementación usando JPA
    │               ├── service                          # Maneja la parte lógica 
    │               │   ├── LibroService.java               # Lógica para libros
    │               │   ├── LibroServiceJPA.java            # Lógica usando JPA
    │               │   ├── PrestamoService.java            # Lógica para préstamos
    │               │   └── PrestamoServiceJPA.java         # Lógica usando JPA
    │               └── BibliotecaApplication.java        
    └── resources
        ├── application.yaml                           # Configuración principal
        ├── application-dev.yaml                       # Configuración para dev
        ├── application-local.yaml                     # Configuración local
        └── data.sql                                   # Datos iniciales para la DB 

```


---

## Endpoints de la API

### 📚 Gestión de Libros (`/libros`)

| Método | Endpoint | Descripción | Ejemplo |
|--------|----------|-------------|---------|
| `GET` | `/libros` | Listar todos los libros | `GET /libros` |
| `GET` | `/libros/{id}` | Obtener libro por ID | `GET /libros/1` |
| `POST` | `/libros` | Crear nuevo libro | `POST /libros` |
| `PUT` | `/libros/{id}` | Actualizar libro | `PUT /libros/1` |
| `DELETE` | `/libros/{id}` | Eliminar libro | `DELETE /libros/1` |
| `POST` | `/libros/{id}/prestar` | Prestar libro | `POST /libros/1/prestar` |
| `POST` | `/libros/{id}/devolver` | Devolver libro | `POST /libros/1/devolver` |

### 📋 Gestión de Préstamos (`/prestamos`)

| Método | Endpoint | Descripción | Ejemplo |
|--------|----------|-------------|---------|
| `GET` | `/prestamos` | Listar todos los préstamos | `GET /prestamos` |
| `GET` | `/prestamos/activos` | Listar préstamos activos | `GET /prestamos/activos` |
| `GET` | `/prestamos/{id}` | Obtener préstamo por ID | `GET /prestamos/1` |
| `POST` | `/prestamos` | Crear préstamo | `POST /prestamos?libroId=1` |
| `POST` | `/prestamos/{id}/devolver` | Devolver préstamo | `POST /prestamos/1/devolver` |

---

## Acceso a H2 Console (modo local)

- **URL:** http://localhost:8080/h2-console  `  
- **Usuario:** `sa`  
- **Contraseña:** *(vacío, no hay contraseña)*  
---

## Ideas de futura implementación

Además de lo ya planteado, el sistema de la Biblioteca podría mejorar implementando las siguientes ideas:

- **Nueva entidad 'Usuario'**  
  Representaría a las personas que usan el sistema. Incluiría:  
  - id único  
  - email 
  - una lista de prestamos **vigentes**
  - int cantidadTotalDePréstamos
 
Entonces, si el Usuario es nuevo, se registraría en el sistema junto con el préstamo que solicitó, y tanto la entiendad Libro como Prestamo realizarían sus respectivas actualizaciones ante este caso.
Si no es nuevo aquel Usuario, nada más se le sumaría aquel préstamo que solicitó a su lista de préstamos vigentes (puede no tener ninguno también, no nos afecta si ya está registrado), y Libro y Préstamo realizarán las mismas acciones. 

No considero que deba ser el Usuario quien mantenga un registro histórico de los préstamos que realizó con toda la información de los mismos, sino contar con una lista de aquellos que estén activos y tener con un contador que indique la cantidad de veces que realizó un préstamo. Para no romper el encapsulamiento, la gestión del historial debería quedar a cargo del Service, y no almacenarse directamente en la entidad Usuario.

Asumiríamos que la cantidad de Préstamos que un Usuario puede sacar son infinitos (al igual que como ocurría con Libro y Préstamo), aunque se podría hacer para este caso que el Usuario tenga máximo de préstamos en su lista. No sería lógico que una misma persona tenga cuatrocientos préstamos hechos (aunque tampoco lo es que en una Biblioteca hayan infinitos libros, pero bueno).

- **Mejoras en la entidad Prestamo**  
  Utilizando la librería LocalDate, se podría agregar una fecha límite de devolución, por ejemplo, veinte días después del inicio del préstamo.

  En caso de un préstamos vencido, trabajaría con alguna librería que me permita gestionar mails y se enviaría al usuario un mail informándole su situación.

  Además, cada Usuario podría tener un historial de préstamos para consultar cuáles realizó, si cumplió con las fechas de devolución y quién es el usuario más activo de la biblioteca (y entregar mail/certificado de agradecimiento todos los meses/años).

