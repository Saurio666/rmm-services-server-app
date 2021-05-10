create table device_type(
	device_type_id numeric(10) primary key not null,
	device_type_name varchar(100) not null,
	device_type_cost numeric(20,2) not null
);

insert into device_type(device_type_name, device_type_cost) values ('Windows', 4);
insert into device_type(device_type_name, device_type_cost) values ('Mac', 4);

create table device_service(
	device_service_id numeric(10) primary key not null,
	device_service_name varchar(100) not null,
	device_service_description varchar(300) not null,
	device_service_cost numeric(20,2) not null
);

insert into device_service (device_service_id, device_service_name, device_service_description, device_service_cost) values (1, 'Anitivirus Windows', 'To have antivirus in their devices.', 5);
insert into device_service (device_service_id, device_service_name, device_service_description, device_service_cost) values (2, 'Anitivirus Mac', 'To have antivirus in their devices.', 7);
insert into device_service (device_service_id, device_service_name, device_service_description, device_service_cost) values (3, 'Cloudberry', 'To backup data in their devices.', 3);
insert into device_service (device_service_id, device_service_name, device_service_description, device_service_cost) values (4, 'PSA', 'Ticketing system for alerts in their devices.', 2);
insert into device_service (device_service_id, device_service_name, device_service_description, device_service_cost) values (5, 'TeamViewer', 'Remote connection to devices.', 1);

create table customer(
	customer_id serial primary key not null,
	customer_name varchar(100) not null
);

insert into customer (customer_name) values ('JOHN');
insert into customer (customer_name) values ('GEORGE');

create table device (
	device_id serial primary key not null,
	system_name varchar(100) not null,
	device_type_id integer not null,	
	customer_id integer not null,
	status numeric(1) not null default 1
);
ALTER TABLE device
    ADD CONSTRAINT fk_device_type FOREIGN KEY (device_type_id) REFERENCES device_type(device_type_id);

create table customer_order(
	customer_order_id serial primary key not null,
	customer_id integer not null,
	device_id integer null,
	device_service_id integer null,
	unit_cost numeric(20,2) not null,
	customer_order_quantity numeric(10) not null default 1
);

ALTER TABLE customer_order
    ADD CONSTRAINT fk_device_service FOREIGN KEY (device_service_id) REFERENCES device_service(device_service_id);
ALTER TABLE customer_order
    ADD CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id);
ALTER TABLE customer_order
    ADD CONSTRAINT fk_device FOREIGN KEY (device_id) REFERENCES device(device_id);

