#ES
Para el proyecto se requiere
	jdk 1.8.0_291 o superior
	maven 3.8.1 o superior

También se requiere de una base de datos Postgres

Para crear las estructuras en la base de datos se necesita:
	Crear una nueva base de datos
	Conectarse a la base de datos creada y ejecutar el contenido del script db_rmm.sql
	Configurar el archivo application.properties modificando los parámetros corresponiente a la base de datos
	El script contiene la creación de 5 tablas
		device_type: Catálogo de los tipos de sistemas operativos (WINDOWS, MAC)
		device_service: Cátalogo con los servicios que se tiene para los diferentes dispositivos
		customer: Contiene la información de los clientes (para el ejercicio solo se requiere un identificador y un nombre)
		device: Almacena los dispositivos que puede crear o modificar un cliente (Tiene claves foráneas hacias las tablas customer y device_type).
		customer_order: Almacena los items de la orden que el cliente puede realizar como dispositivos y servicios (Tiene claves foráneas hacias las tablas customer, device_type y device_service).
	También contiene la inserción de registros para los catálogos device_type, device_service y dos clientes de ejempl (tabla customer)
	
Para arrancar el proyecto se debe ubicar en consola en el directorio raiz del proyecto descargado y ejecutar el comando mvnw spring-boot:run

El proyecto consta de los siguientes servicios REST
	http://<<ip>>:<<port>>/rmm/getDeviceList?customerId=<#cid>
		Permite obtener la lista de dispositivos activos de un cliente
	http://<<ip>>:<<port>>/rmm/addDevice?customerId=<#cid>&deviceTypeId=<#dtid>&systemName=<#sn>
		Permite agregar un dispositivo a la lista de dispositivos de un cliente
	http://<<ip>>:<<port>>/rmm/updateDevice?deviceId=<#did>&deviceTypeId=<#dtid>&systemName=<#sn>
		Permite actualizar la información de un dispositivo, solo se puede cambiar el tipo y el nombre
	http://<<ip>>:<<port>>/rmm/deleteDevice?deviceId=<#did>
		Permite eliminar un dispositivo de la lista de dispositivos del cliente (eliminación lógica)
	http://<<ip>>:<<port>>/rmm/addOrderDevice?customerId=<#cid>&deviceId=<#did>&deviceServiceId=<#sid>&quantity=<#q>
		Permite agregar un dispositivo o un servicio a la orden del cliente.
		Como registro, solo se permite agregar un servicio o un dispositivo, en caso de que se requiera mas de uno se debe enviar el parámetro quantity con la cantidad de items
		En caso de requerir un dispositivo se debe enviar únicamente los parámetros customerId, deviceId y quantity
		En caso de requerir un dispositivo se debe enviar únicamente los parámetros customerId, deviceServiceId y quantity
	http://<<ip>>:<<port>>/rmm/getOrderList?customerId=<#cid>
		Permite obtener los items de la orden seleccionados por un cliente
	http://<<ip>>:<<port>>/rmm/computeOrder?customerId=<#cid>
		Permite saber el costo total de la orden de un cliente
	
Parámetros de los servicios
	<<ip>>: IP del servidor
	<<port>>: Puerto donde se levanta el servidor
	<#cid> es el id del cliente que se encuentra en la tabla customer (customer_id)
	<#dtid> es el id del tipo de dispositivo que se encuentra en la tabla device_type (device_type_id)
	<#sn> es el nombre del dispositivo que crea el cliente, por ejemplo WINDOWS SERVER
	<#did> es el id del dispositivo que se encuentra en la tabla device (device_id)
	<#sid> es el id del servicio que se encuentra en la tabla device_service (device_service_id)
	<#q> cantidad de elementos de un servicio o dispositivo

------------------------------------------------------------------------------------
#EN
The project requieres
	jdk 1.8.0_291 or newer
	maven 3.8.1 or newer
	
A Postgres database is also required

To create the structures in a database you need:
	Create a new database
	Connect to the created database and run the content of the script: db_rmm.sql
	Configure the application.properties file by modifying the parameters  corresponding to the database
	The script contains the creation of 5 tables		
		device_type: Catalog of types of operating systems (WINDOWS, MAC)
		device_service: Catalog with the services available for the different devices
		customer: Contains customer information (only an identifier and name are required for the exercise) 
		device: Stores the devices that a customer can create or modify (It has foreign keys to the customer and device_type tables).
		customer_order: Stores the items of the order that the customer can perform as devices and services (Has foreign keys to the customer, device_type and device_service tables).
	It also contains the insertion of records for the catalogs device_type, device_service and two example clients (customer table)
	
To run the project locate the command promopt in the root directory of the downloaded project and execute command mvnw spring-boot:run

The project consists of the following REST services
	http://<<ip>>:<<port>>/rmm/getDeviceList?customerId=<#cid>
		It allows to obtain the list of active devices of a customer
	http://<<ip>>:<<port>>/rmm/addDevice?customerId=<#cid>&deviceTypeId=<#dtid>&systemName=<#sn>
		Add a device to a customer's device list
	http://<<ip>>:<<port>>/rmm/updateDevice?deviceId=<#did>&deviceTypeId=<#dtid>&systemName=<#sn>
		Allows updating the information of a device, only the type and name can be changed
	http://<<ip>>:<<port>>/rmm/deleteDevice?deviceId=<#did>
		Allows you to remove a device from the client device list (logical delete)
	http://<<ip>>:<<port>>/rmm/addOrderDevice?customerId=<#cid>&deviceId=<#did>&deviceServiceId=<#sid>&quantity=<#q>
		Allows adding a device or a service to the customer's order.
		As a record, it is only allowed to add a service or a device, in case more than one is required, the quantity parameter must be sent with the number of items
		If a device is required, only the customerId, deviceId and quantity parameters must be sent
		If a device is required, only the customerId, deviceServiceId and quantity parameters must be sent		
	http://<<ip>>:<<port>>/rmm/getOrderList?customerId=<#cid>
		Allows to obtain the items of the order selected by a client
	http://<<ip>>:<<port>>/rmm/computeOrder?customerId=<#cid>
		Lets you know the total cost of a customer's order
		
Service parameters
	<<ip>>: server IP
	<<port>>: server port
	<#cid>: is the customer id found in the customer table (customer_id)
	<#dtid>: is the id of the device type found in the device_type table (device_type_id)
	<#sn>: is the name of the device that the client creates, for example WINDOWS SERVER
	<#did>: is the device id found in the device table (device_id)
	<#sid>: is the id of the service found in the device_service table (device_service_id)
	<#q>: number of elements of a service or device
