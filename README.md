# 📚 Proyecto Final - Sistema de Gestión de Biblioteca  

Este proyecto forma parte del **Trabajo Práctico Final** del curso **Talento Tech - Java Backend (2do Cuatrimestre 2025)**.  
Consiste en el desarrollo de un sistema de gestión para una Biblioteca, implementado con **Spring Boot**, utilizando una arquitectura en capas:  
**Controller → Service → Repository → Model**

## Decisiones de Diseño 
- Libro representa un conjunto de Ejemplares de libros. De esta forma, el ID no es para cada Ejemplar, sino que para un conjunto de ellos,
- categorizados por el título. 
- Los préstamos no se pueden pre-definir en data.sql porque requieren referencias a IDs de libros específicos, que son generados automáticamente por la base de datos. Los préstamos se crearán dinámicamente al usar los endpoints correspondientes.

---

## 🛠️ Tecnologías utilizadas
- **Java 17+**
- **Spring Boot**
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

## 📋 Ejemplos de Uso

### Crear un libro
```bash
curl -X POST "http://localhost:8080/libros" \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "1984",
    "autor": "George Orwell",
    "isbn": "978-0451524935",
    "disponible": true
  }'
