# VulnPort Java - Ground Truth Manifest

Este archivo define la verdad de terreno del laboratorio. Se utiliza para comparar los resultados del Auditor con las vulnerabilidades que realmente fueron sembradas.

## Hallazgos esperados

| ID | CWE orientativo | Clase | Patrón esperado |
|---|---:|---|---|
| LAB-SQLI-001 | CWE-89 | LoginServlet | SQL concatenado con username/password |
| LAB-SQLI-002 | CWE-89 | SearchServlet | SQL concatenado con parámetro q |
| LAB-XSS-001 | CWE-79 | SearchServlet | Entrada q escrita directamente en HTML |
| LAB-TRAV-001 | CWE-22 | FileDownloadServlet | Nombre de archivo controlado por usuario |
| LAB-UPLOAD-001 | CWE-434 | UploadServlet | Carga sin allowlist ni validación |
| LAB-IDOR-001 | CWE-639 | ProfileServlet | Consulta objeto por id sin autorización |
| LAB-AUTHZ-001 | CWE-862 | AdminServlet | Falta validación de rol |
| LAB-REDIR-001 | CWE-601 | RedirectServlet | sendRedirect con entrada no validada |
| LAB-CMD-001 | CWE-78 | CommandServlet | Entrada concatenada a comando OS |
| LAB-SSRF-001 | CWE-918 | FetchUrlServlet | URL arbitraria controlada por usuario |
| LAB-XXE-001 | CWE-611 | XmlImportServlet | XML parser con entidades externas habilitables |
| LAB-CRYPTO-001 | CWE-327 | WeakCrypto | Uso de MD5 |
| LAB-SECRET-001 | CWE-798 | AppConfig | Secretos hardcodeados |
| LAB-SESSION-001 | CWE-384 | LoginServlet | No rota sesión tras autenticación |
| LAB-INFO-001 | CWE-209 | DebugServlet | Stack trace/configuración al cliente |
| LAB-CORS-001 | CWE-942 | CorsFilter | Access-Control-Allow-Origin: * |

## Criterio para probar recetas genéricas

Una receta aprobada no debe depender de:
- nombre de clase;
- ruta exacta;
- nombre de variable;
- número de línea;
- literal concreto del hallazgo.

Debe identificar el patrón vulnerable y verificar la corrección semántica. Por ejemplo, una receta para SQL Injection debería poder aplicarse a otra consulta concatenada aunque la clase, las columnas y las variables sean diferentes.

## Flujo sugerido

1. Escanear este commit vulnerable.
2. Guardar el conjunto de hallazgos iniciales.
3. Solicitar corrección automática de un hallazgo.
4. Reescanear.
5. Confirmar que el hallazgo concreto desapareció sin introducir regresiones.
6. Guardar la receta únicamente si la validación fue satisfactoria.
7. Sembrar una segunda variante del mismo patrón en otro archivo.
8. Verificar que el Auditor reutiliza/adapta la receta sin memorizar el caso original.
