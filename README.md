# 📚 Proyecto Final - Sistema de Gestión de Biblioteca  

Este proyecto forma parte del **Trabajo Práctico Final** del curso **Talento Tech - Java Backend (2do Cuatrimestre 2025)**.  
Consiste en el desarrollo de un sistema de gestión para una Biblioteca, implementado con **Spring Boot**, utilizando una implementación en capas:  
**Controller → Service → Repository → Model**


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

### Relación entre libros y préstamos
Cada préstamo mantiene una referencia al libro correspondiente. 
De esta forma, el sistema podrá conocer qué libro fue prestado y actualiza su stock o disponibilidad. 
Así, un libro no puede ser eliminada si tiene préstamos asociados. 


## 🛠️ Tecnologías utilizadas
- **Java 17+**
- **Spring Boot**
- **Insomnia**
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
    │               ├── controller
    │               │   ├── LibroController.java            # Endpoints: /libros
    │               │   └── PrestamoController.java         # Endpoints: /prestamos
    │               ├── model
    │               │   ├── Libro.java
    │               │   └── Prestamo.java
    │               ├── repository
    │               │   ├── LibroDBRepository.java
    │               │   ├── LibroMemRepository.java
    │               │   ├── LibroRepository.java
    │               │   ├── LibroRepositoryJPA.java
    │               │   ├── PrestamoDBRepository.java
    │               │   ├── PrestamoMemRepository.java
    │               │   ├── PrestamoRepository.java
    │               │   └── PrestamoRepositoryJPA.java
    │               ├── service
    │               │   ├── LibroService.java
    │               │   ├── LibroServiceJPA.java
    │               │   ├── PrestamoService.java
    │               │   └── PrestamoServiceJPA.java
    │               └── BibliotecaApplication.java
    └── resources
        ├── application.yaml
        ├── application-dev.yaml
        ├── application-local.yaml
        └── data.sql
```


---

## 🔌 Endpoints de la API

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

## 🗄️ Acceso a H2 Console (modo local)

- **URL:** http://localhost:8080/h2-console  
- **JDBC URL:** `jdbc:h2:mem:testdb`  
- **Usuario:** `sa`  
- **Contraseña:** *(vacío)*  

---
