# Taller Java 21
Ejemplo de Sistema de onboarding de un banco, donde registramos personas, cuenta, y tarjetas.
Se aplican reglas en base a data obtenida de apis externas.
Consultar `eventCatalog.json` para ver eventos de negocio.

##Requerimientos minimos
- Uso de java 21.
- Uso Spring Boot 3+.
- Servicios Indepentientemente desplegables.
- Comunicación asicnrónica entre servicios.

##Patrones Arquitectónicos
- *Independent Deployment*: usamos containers.
- *Event Bus*: implementado con Kafka.
- *Database per service*: cada microservicio tiene su db.
- *Polyglot Persistence*: persistimos tanto en MariaDB como MongoDB.
- *Eventual Consistency*: consecuencia de desacoplar con Event Bus.
- *Asyncronous communication*: pre-requisito para Event Bus.
- *Coreography*: el sistema de onboarding consiste en una secuencia de eventos.
- *Data denormalization*: se duplica info de domicilios para evitar el acoplamiento.
- *Rest API*: el onboarding se inicia mediante llamado a API.
- *Stateless Service*: para poder subir y bajar containers en cualquier momento.
- *Externalized configuration*: mediante mecanismos de Spring.

##Patrones de Diseño
- *Chain of Responsability*:

##Features Java 21
- *Records*: `GetAccountDto`, `AddPersonDto`, etc.
- *Smart Cast*
- *Type Inference*: mediante el uso de `var`.
- *Virtual Threads*: mediante `Executors.newVirtualThreadPerTaskExecutor()`.
- *Sealed Classes*: ver `DomainEventHandler`.
