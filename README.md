# Backend_Informate_UPC

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.14-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![JPA](https://img.shields.io/badge/Spring_Data_JPA-Persistence-success)
![Status](https://img.shields.io/badge/Estado-En_Desarrollo-yellow)

> [!NOTE]
> Este proyecto corresponde al backend del **Sistema de Gestión y Validación de Resoluciones Académicas**, una aplicación web destinada a centralizar la gestión de información institucional dentro de una institución educativa.

La plataforma permite administrar:

- Noticias institucionales
- Resoluciones académicas
- Comentarios de usuarios
- Gestión de usuarios y roles
- Autenticación y autorización
- Validación de información

Su objetivo principal es mejorar la transparencia, la organización y la participación de la comunidad educativa.

---

## 📐 Modelo Entidad-Relación

<img width="1660" height="1091" alt="ModeloEntidad-Relacion drawio (6)" src="https://github.com/user-attachments/assets/36b18993-de3a-46e9-93e1-462a945ad01d" />

---

## 🧪 Evidencia de Pruebas

En el siguiente enlace se encuentran las capturas de pantalla de las pruebas realizadas sobre los endpoints del módulo **Usuarios** (Usuario, Rol, Sesión, Auditoría y Recuperación de Contraseña), donde se verifica el correcto funcionamiento de la API a través de Swagger.

### 📂 [Ver capturas de pruebas en Google Drive](https://drive.google.com/drive/u/0/folders/1bo7nxscPF_3GvNwpfuUBVS6V3bMZWhJi)

| Módulo | Pruebas realizadas |
|--------|-------------------|
| 👤 Usuario | Crear, listar, buscar por ID/email/DNI, actualizar, eliminar |
| 🎭 Rol | Crear, listar, buscar por ID/nombre, actualizar, eliminar |
| 🔐 Sesión | Crear, listar, listar por usuario, listar activas, eliminar |
| 📋 Auditoría | Crear, listar, buscar por ID, eliminar |
| 🔑 Recuperación | Crear, listar, buscar por token, eliminar |

> [!TIP]
> Todas las pruebas fueron ejecutadas con **Swagger UI** desde `http://localhost:8080/swagger-ui/index.html`.
## Tecnologías

| Tecnología | Versión |
|------------|----------|
| Java | 17 |
| Spring Boot | 3.5.14 |
| Maven | Última estable |
| MySQL | 8+ |
| Spring Data JPA | Incluido |
| Lombok | Incluido |
| Validation | Incluido |
| DevTools | Incluido |

## Integrantes
* Mayra Moyano
* Juan Larcher
* Tiago Nicolitsis

## Repositorio Principal
Toda la documentación del proyecto se encuentra en el siguiente repositorio:

[Repositorio Principal](https://github.com/juanetee07/SistemaDeGestionYValidacion.git)

## Forma de trabajo
* Uso de ramas (feature, develop, main)
* Commits descriptivos
* Pull Requests para integración
* Comunicación constante del equipo

[Pautas de Trabajo](https://docs.google.com/document/d/1fPNLQwjkDx_Cp7u7ijTiUVXdQUHgiPEIUI9Cu5qknBE/edit?usp=sharing)
