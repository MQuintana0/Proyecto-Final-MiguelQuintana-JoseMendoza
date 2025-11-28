# Proyecto Final - Despliegue con Docker y Render

Este repositorio contiene una aplicación Spring Boot (Java) preparada para desplegarse en Render usando un `Dockerfile` multi-stage.

## Archivos relevantes
- `Dockerfile`: construye la aplicación usando el `mvnw` y luego empaqueta el JAR en una imagen basada en Temurin JRE.
- `render.yaml`: (opcional) archivo de configuración para despliegue automático en Render.

## Construir localmente (Docker)

- Construir la imagen:

```powershell
docker build -t proyecto-final .
```

- Ejecutar la aplicación (puerto 8080):

```powershell
docker run -e PORT=8080 -p 8080:8080 proyecto-final
```

Si no especificas `PORT`, la aplicación usa `8080` por defecto.

## Notas para Render

- En Render crea un nuevo **Web Service** y selecciona **Docker** (o que use `render.yaml`).
- Render inyecta la variable de entorno `PORT` automáticamente; el `Dockerfile` ya la respeta.
- Si necesitas controlar la memoria JVM, añade la variable de entorno `JAVA_OPTS` en la configuración de tu servicio (por ejemplo: `-Xms256m -Xmx512m`).
- Habilita `autoDeploy` si quieres que Render despliegue automáticamente cuando hagas push a la rama configurada.

## Comprobación rápida

- Para probar la imagen localmente y simular Render:

```powershell
docker build -t proyecto-final .
docker run -e PORT=8080 -p 8080:8080 proyecto-final
# luego visita http://localhost:8080
```

Si quieres que añada pasos de CI/CD o que pruebe construir la imagen aquí, dime y lo hago.
# Proyecto-MVC-MiguelQuintana-JoseMendoza

## Descripción del Proyecto
El **Sistema de Gestión de Multas de Tránsito** es una aplicación web desarrollada en **Java con Spring Boot**, diseñada para registrar, consultar y administrar infracciones de tránsito tanto para conductores como para vehículos.

El sistema permite:

- **Registrar** diferentes tipos de multas dinámicamente según la categoría (conductor o vehículo) y calcular su monto automáticamente.
- **Validar** la persistencia de datos mediante una base de datos MySQL contenerizada en Docker.
- **Visualizar** un listado unificado de infracciones con detalles específicos mediante vistas web dinámicas (Thymeleaf).
- **Gestionar** el ciclo de vida de la información (crear, leer, eliminar) separando las responsabilidades de cada capa.

Todo el flujo está construido bajo una arquitectura **MVC (Modelo–Vista–Controlador)**, asegurando una separación clara entre la lógica de negocio, la interfaz de usuario y el control de la aplicación.

---

## Integrantes

- **Miguel Quintana**
- **José Mendoza**

---

## Lenguaje y Herramientas

| Herramienta | Uso |
|--------------|-----|
| **Java 25** | Lenguaje de programación principal |
| **Spring Boot 4** | Framework para el backend y gestión de dependencias |
| **Thymeleaf + Bootstrap** | Construcción de la interfaz web y vistas |
| **MySQL (Docker)** | Base de datos relacional contenerizada |
| **Maven** | Gestión del proyecto y construcción |

---

## Patrones de Diseño y su Rol en el MVC

### **Modelo – Patrón Strategy**
En la capa **Modelo**, se implementó el patrón **Strategy** (mediante polimorfismo) para definir la lógica de cálculo de las multas.
Aunque todas las multas comparten una estructura base, el algoritmo para definir "cuánto pagar" varía. Las clases concretas (`MultaSoat`, `MultaVelocidad`, `MultaAccidente`) implementan su propia estrategia en el método `calcularMonto()`.

**Rol:**
Permite que el sistema elija dinámicamente el algoritmo de cobro correcto en tiempo de ejecución sin necesidad de condicionales complejos, facilitando la adición de nuevas reglas de negocio.

---
