# Sistema de Gestión de Multas de Tránsito

## 1. Descripción General
Este proyecto es una aplicación web desarrollada bajo la arquitectura **MVC (Modelo-Vista-Controlador)** que permite la administración centralizada de infracciones de tránsito. El sistema diferencia entre multas aplicadas a **Vehículos** y multas aplicadas a **Conductores**, implementando lógica polimórfica para el cálculo automático de montos y persistencia en base de datos.

El sistema permite:
- Registrar multas con cálculos automáticos de costos según reglas de negocio.
- Consultar un listado unificado de infracciones.
- Ver detalles específicos de cada multa.
- Eliminar registros.

## 2. Stack Tecnológico

* **Lenguaje:** Java 21
* **Framework:** Spring Boot (Web, Data JPA)
* **Motor de Plantillas:** Thymeleaf (con Bootstrap 5 para estilos)
* **Base de Datos:** MySQL 8.0
* **Contenerización:** Docker (Multi-stage build)
* **Despliegue:** Render (Web Service) + Clever Cloud (Base de Datos Externa)

## 3. Arquitectura del Software

### 3.1 Modelo de Datos (Herencia y Polimorfismo)
El proyecto utiliza la estrategia de herencia `SINGLE_TABLE` de JPA para optimizar el almacenamiento.

#### A. Rama de Vehículos (`MultaVehiculo`)
Infracciones asociadas a la placa del automotor.

| Clase | Regla de Negocio (Costo) | Descripción |
| :--- | :--- | :--- |
| **MultaSoat** | $800,000 (Fijo) | Infracción por SOAT vencido. |
| **MultaTecnomecanica** | $600,000 (Fijo) | Infracción por revisión técnica vencida. |
| **MultaAccidente** | Variable | Depende de la gravedad: <br>• Grave: $1,000,000<br>• Moderada: $500,000<br>• Leve: $200,000 |

#### B. Rama de Conductores (`MultaConductor`)
Infracciones asociadas a la licencia de conducción.

| Clase | Regla de Negocio (Costo) | Descripción |
| :--- | :--- | :--- |
| **MultaCinturon** | $300,000 (Fijo) | No uso del cinturón de seguridad. |
| **MultaLicencia** | $700,000 (Fijo) | Licencia vencida o no portada. |
| **MultaVelocidad** | Escalonada | Según velocidad registrada:<br>• ≤ 80km/h: $0<br>• 81-100km/h: $400,000<br>• 101-120km/h: $700,000<br>• > 120km/h: $1,200,000 |

### 3.2 Controladores
* **WebMultaController:** Gestiona las vistas HTML y el flujo de navegación (`/web/multas/`).
* **MultaRestController:** API REST para operaciones CRUD directas (`/api/multas/`).

---

## 4. Guía de Despliegue en Producción (Render)

Este proyecto está configurado para desplegarse en **Render** utilizando Docker, con una base de datos MySQL externa (ej. Clever Cloud o Railway).

### Requisitos Previos
1.  Cuenta en Render.
2.  Base de datos MySQL en la nube (Host, Nombre BD, Usuario, Contraseña).

### Paso 1: Configuración de Base de Datos
Debido a que Render no ofrece persistencia gratuita para MySQL, se debe usar un proveedor externo.
1.  Crear base de datos MySQL 8.0.
2.  Anotar las credenciales de conexión.

### Paso 2: Configuración en Render
1.  Crear un nuevo **Web Service** en Render conectado a este repositorio.
2.  Seleccionar **Docker** como entorno de ejecución.
3.  En la sección **Environment Variables**, agregar las siguientes claves para conectar la aplicación con la base de datos externa:

| Clave (Key) | Valor (Value) |
| :--- | :--- |
| `SPRING_DATASOURCE_URL` | `jdbc:mysql://<TU_HOST>:3306/<NOMBRE_BD>` |
| `SPRING_DATASOURCE_USERNAME` | `<TU_USUARIO>` |
| `SPRING_DATASOURCE_PASSWORD` | `<TU_CONTRASEÑA>` |
| `PORT` | `8080` (Opcional, Render lo suele detectar) |

A la hora de agregar las variables de entonrno, se debio construir el SPRING_DATASOURCE_URL con la informacion suministrada por clever cloud.
TU_HOST es el host dado por clever cloud y el NOMBRE_BD es el nombre suministrado por clever cloud.
A su vez TU_USUARIO y TU_CONTRASEÑA tambien son suministrados por clever cloud.

### Paso 3: Dockerfile
El proyecto incluye un `Dockerfile` optimizado (Multi-stage):
- **Build:** Usa Eclipse Temurin JDK 21 para compilar el proyecto con Maven Wrapper.
- **Run:** Usa una imagen ligera JRE 21 para ejecutar la aplicación.

### Notas de Ejecución Local
Para correr el proyecto localmente con Docker Compose:
```bash
docker-compose up --build