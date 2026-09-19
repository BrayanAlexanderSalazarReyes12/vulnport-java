# VulnPort Java

Aplicación Java/JSP/Servlets **intencionalmente vulnerable** para pruebas controladas del proyecto Auditor.

> ⚠️ LABORATORIO. No desplegar en Internet ni usar con datos reales.

## Objetivo

Servir como banco de pruebas reproducible para:
- detección de vulnerabilidades;
- generación de correcciones;
- validación de remediaciones;
- creación y reutilización de recetas genéricas;
- comparación de hallazgos antes/después del parche.

## Stack
- Java 8
- Maven
- Servlet API 3.1
- JSP
- H2 en memoria
- Tomcat 9

## Ejecución

```bash
mvn clean package
```

Desplegar `target/vulnport-java.war` en Tomcat 9.

Credenciales de laboratorio:
- admin / admin123
- analyst / analyst123
- user / user123

## Catálogo de vulnerabilidades

| ID | Vulnerabilidad | Archivo principal |
|---|---|---|
| LAB-SQLI-001 | SQL Injection en login | LoginServlet.java |
| LAB-SQLI-002 | SQL Injection en búsqueda | SearchServlet.java |
| LAB-XSS-001 | Reflected XSS | SearchServlet.java |
| LAB-TRAV-001 | Path Traversal | FileDownloadServlet.java |
| LAB-UPLOAD-001 | Unrestricted File Upload | UploadServlet.java |
| LAB-IDOR-001 | IDOR | ProfileServlet.java |
| LAB-AUTHZ-001 | Broken Access Control | AdminServlet.java |
| LAB-REDIR-001 | Open Redirect | RedirectServlet.java |
| LAB-CMD-001 | OS Command Injection | CommandServlet.java |
| LAB-SSRF-001 | SSRF | FetchUrlServlet.java |
| LAB-XXE-001 | XXE | XmlImportServlet.java |
| LAB-CRYPTO-001 | MD5 para contraseñas | WeakCrypto.java |
| LAB-SECRET-001 | Credenciales hardcodeadas | AppConfig.java |
| LAB-SESSION-001 | Sesión insegura/fijación | LoginServlet.java |
| LAB-INFO-001 | Exposición de errores | DebugServlet.java |
| LAB-CORS-001 | CORS permisivo | CorsFilter.java |

## Regla del laboratorio

Los comentarios `LAB-*` existen para disponer de una verdad de terreno durante las primeras pruebas. El Auditor debe detectar el patrón de vulnerabilidad, no depender del comentario ni del nombre del archivo.
