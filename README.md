# 📚 Proyecto Final - Sistema de Gestión de Biblioteca  

Este proyecto forma parte del **Trabajo Práctico Final** del curso **Talento Tech - Java Backend (2do Cuatrimestre 2025)**.  
Consiste en el desarrollo de un sistema de gestión para una Biblioteca, implementado con **Spring Boot**, utilizando una arquitectura en capas:  
**Controller → Service → Repository → Model**

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
'''text
src
└── main
    ├── java/com/techlab/biblioteca
    │     ├── controller
    │     │   ├── LibroController.java           # Endpoints: /libros
    │     │   └── PrestamoController.java        # Endpoints: /prestamos
    │     ├── model
    │     │   ├── Libro.java                     # Entidad Libro
    │     │   └── Prestamos.java                 # Entidad Préstamo
    │     ├── repository
    │     │   ├── LibroRepository.java           # Interfaz LibroRepository
    │     │   ├── LibroDBRepository.java         # Implementación DB
    │     │   ├── LibroMemRepository.java        # Implementación Memoria
    │     │   ├── LibroRepositoryJPA.java        # Implementación JPA
    │     │   ├── PrestamosRepository.java       # Interfaz PrestamoRepository
    │     │   ├── PrestamoDBRepository.java      # Implementación DB
    │     │   ├── PrestamoMemRepository.java     # Implementación Memoria
    │     │   └── PrestamoRepositoryJPA.java     # Implementación JPA
    │     ├── service
    │     │   ├── LibroService.java              # Interfaz LibroService
    │     │   ├── LibroServiceJPA.java           # Implementación LibroService
    │     │   ├── PrestamoService.java           # Interfaz PrestamoService
    │     │   └── PrestamoServiceJPA.java        # Implementación PrestamoService
    │     └── BibliotecaApplication.java         # Clase principal
    └── resources
          ├── application.yaml                   # Configuración principal
          ├── application-dev.yaml               # Configuración desarrollo
          ├── application-local.yaml             # Configuración local
          └── data.sql                           # Datos iniciales
'''
Manejo de la App
Los préstamos no se pueden pre-definir en data.sql porque requieren referencias a IDs de libros específicos, que son generados automáticamente por la base de datos. Los préstamos se crearán dinámicamente al usar los endpoints correspondientes.



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


# 🔌 Endpoints de la API

## 📚 Gestión de Libros (`/libros`)

| Método | Endpoint | Descripción | Ejemplo |
|--------|----------|-------------|---------|
| GET | `/libros` | Listar todos los libros | `GET /libros` |
| GET | `/libros/{id}` | Obtener libro por ID | `GET /libros/1` |
| POST | `/libros` | Crear nuevo libro | `POST /libros` |
| PUT | `/libros/{id}` | Actualizar libro | `PUT /libros/1` |
| DELETE | `/libros/{id}` | Eliminar libro | `DELETE /libros/1` |
| POST | `/libros/{id}/prestar` | Prestar libro | `POST /libros/1/prestar` |
| POST | `/libros/{id}/devolver` | Devolver libro | `POST /libros/1/devolver` |

---

## 📋 Gestión de Préstamos (`/prestamos`)

| Método | Endpoint | Descripción | Ejemplo |
|--------|----------|-------------|---------|
| GET | `/prestamos` | Listar todos los préstamos | `GET /prestamos` |
| GET | `/prestamos/activos` | Listar préstamos activos | `GET /prestamos/activos` |
| GET | `/prestamos/{id}` | Obtener préstamo por ID | `GET /prestamos/1` |
| POST | `/prestamos` | Crear préstamo | `POST /prestamos?libroId=1` |
| POST | `/prestamos/{id}/devolver` | Devolver préstamo | `POST /prestamos/1/devolver` |

---

## 🗄️ Acceso a H2 Console (modo local)

- **URL:** http://localhost:8080/h2-console  
- **JDBC URL:** `jdbc:h2:mem:testdb`  
- **Usuario:** `sa`  
- **Contraseña:** *(vacío)*  

---

## 👨‍💻 Autor

**Curso:** Talento Tech - Java Backend  
**Cuatrimestre:** 2do Cuatrimestre 2025  
**Trabajo:** Proyecto Final - Sistema de Gestión de Biblioteca  
