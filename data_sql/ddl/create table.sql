-- public.building definition

-- Drop table

-- DROP TABLE public.building;

CREATE TABLE public.building (
	id bigserial NOT NULL,
	"name" varchar(100) NULL,
	detail varchar NULL,
	create_date timestamp(0) NULL,
	CONSTRAINT building_pk PRIMARY KEY (id)
);


-- public.common_value definition

-- Drop table

-- DROP TABLE public.common_value;

CREATE TABLE public.common_value (
	id bigserial NOT NULL,
	"key" varchar(100) NOT NULL,
	value varchar(255) NULL,
	create_by timestamp NOT NULL,
	update_by timestamp NOT NULL,
	remark varchar(255) NULL,
	CONSTRAINT common_value_pkey PRIMARY KEY (id)
);


-- public.compnt_cpu definition

-- Drop table

-- DROP TABLE public.compnt_cpu;

CREATE TABLE public.compnt_cpu (
	id bigserial NOT NULL,
	clock_base varchar NULL,
	architecture varchar NULL,
	core int4 NULL,
	thread int4 NULL,
	create_date timestamp(0) NOT NULL,
	update_date timestamp(0) NOT NULL,
	create_by int8 NOT NULL,
	update_by int8 NOT NULL,
	remark varchar NULL,
	pd_brand_id int8 NULL,
	CONSTRAINT compnt_cpu_pk PRIMARY KEY (id)
);


-- public.compnt_disk definition

-- Drop table

-- DROP TABLE public.compnt_disk;

CREATE TABLE public.compnt_disk (
	id bigserial NOT NULL,
	"size" numeric(17,17) NULL,
	write_rate numeric(17,17) NULL,
	read_rate numeric NULL,
	create_date timestamp(0) NOT NULL,
	update_date timestamp(0) NOT NULL,
	create_by varchar NOT NULL,
	update_by varchar NOT NULL,
	remark varchar NULL,
	pd_brand_id int8 NULL,
	CONSTRAINT compnt_disk_pk PRIMARY KEY (id)
);


-- public.compnt_ram definition

-- Drop table

-- DROP TABLE public.compnt_ram;

CREATE TABLE public.compnt_ram (
	id bigserial NOT NULL,
	mem_size numeric NULL,
	mem_type varchar NULL,
	bus numeric NULL,
	create_date timestamp(0) NOT NULL,
	update_date timestamp(0) NOT NULL,
	create_by varchar NOT NULL,
	update_by varchar NOT NULL,
	remark varchar NULL,
	pd_brand_id int8 NULL,
	CONSTRAINT compnt_ram_pk PRIMARY KEY (id)
);


-- public.component definition

-- Drop table

-- DROP TABLE public.component;

CREATE TABLE public.component (
	id bigserial NOT NULL,
	"name" varchar NULL,
	compnt_ref varchar(3) NOT NULL,
	compnt_ref_id varchar NOT NULL,
	amount varchar NULL,
	device_id int8 NOT NULL,
	create_date timestamp(0) NOT NULL,
	update_date timestamp(0) NOT NULL,
	create_by varchar NOT NULL,
	update_by varchar NOT NULL,
	"admin" varchar NULL,
	admin_detail varchar NULL,
	equipment_number varchar NULL,
	remark varchar NULL,
	CONSTRAINT component_pk PRIMARY KEY (id)
);


-- public.credentials definition

-- Drop table

-- DROP TABLE public.credentials;

CREATE TABLE public.credentials (
	id bigserial NOT NULL,
	username varchar(50) NOT NULL,
	"password" varchar NOT NULL,
	description varchar NULL,
	ref_table varchar(50) NOT NULL,
	ref_id int8 NOT NULL,
	create_date timestamp(0) NOT NULL,
	update_date timestamp(0) NOT NULL,
	remark varchar NULL,
	CONSTRAINT credentials_pk PRIMARY KEY (id)
);


-- public.data_type definition

-- Drop table

-- DROP TABLE public.data_type;

CREATE TABLE public.data_type (
	id bigserial NOT NULL,
	"name" varchar NOT NULL,
	create_date timestamp(0) NOT NULL,
	update_date timestamp(0) NOT NULL,
	remark varchar NULL,
	create_by int8 NULL,
	update_by int8 NULL,
	CONSTRAINT data_type_pk PRIMARY KEY (id)
);


-- public.device_type definition

-- Drop table

-- DROP TABLE public.device_type;

CREATE TABLE public.device_type (
	id bigserial NOT NULL,
	"name" varchar(150) NOT NULL,
	create_date timestamp NOT NULL,
	update_date timestamp NOT NULL,
	remark varchar(255) NULL,
	create_by int8 NOT NULL,
	update_by int8 NOT NULL,
	CONSTRAINT device_type_pkey PRIMARY KEY (id)
);


-- public.ip_address definition

-- Drop table

-- DROP TABLE public.ip_address;

CREATE TABLE public.ip_address (
	id bigserial NOT NULL,
	ip_address varchar(40) NOT NULL,
	ip_version varchar(20) NOT NULL,
	ref_id int8 NOT NULL,
	ref_name varchar(50) NOT NULL,
	description varchar NULL,
	create_date timestamp(0) NOT NULL,
	update_date timestamp(0) NOT NULL,
	remark varchar NULL,
	domain_name varchar(50) NULL,
	CONSTRAINT ip_pk PRIMARY KEY (id)
);
CREATE INDEX ref_name ON public.ip_address USING btree (ref_name);


-- public.product_brand definition

-- Drop table

-- DROP TABLE public.product_brand;

CREATE TABLE public.product_brand (
	id bigserial NOT NULL,
	"name" varchar NOT NULL,
	create_date timestamp(0) NOT NULL,
	update_date timestamp(0) NOT NULL,
	remark varchar NULL,
	CONSTRAINT product_brand_pk PRIMARY KEY (id)
);


-- public.room_type definition

-- Drop table

-- DROP TABLE public.room_type;

CREATE TABLE public.room_type (
	id bigserial NOT NULL,
	"name" varchar(150) NOT NULL,
	create_date timestamp NOT NULL,
	update_date timestamp NOT NULL,
	remark varchar(255) NULL,
	create_by int8 NULL,
	update_by int8 NULL,
	CONSTRAINT room_type_pkey PRIMARY KEY (id)
);


-- public.storage_type definition

-- Drop table

-- DROP TABLE public.storage_type;

CREATE TABLE public.storage_type (
	id bigserial NOT NULL,
	"name" varchar(150) NOT NULL,
	create_date timestamp NOT NULL,
	update_date timestamp NOT NULL,
	remark varchar(255) NULL,
	create_by int8 NULL,
	update_by int8 NULL,
	CONSTRAINT storage_type_pkey PRIMARY KEY (id)
);


-- public.system_type definition

-- Drop table

-- DROP TABLE public.system_type;

CREATE TABLE public.system_type (
	id bigserial NOT NULL,
	"name" varchar NOT NULL,
	create_date timestamp(0) NULL,
	update_date timestamp(0) NOT NULL,
	create_by varchar NOT NULL,
	update_by varchar NOT NULL,
	remark varchar NOT NULL,
	CONSTRAINT system_type_pk PRIMARY KEY (id)
);


-- public.user_role definition

-- Drop table

-- DROP TABLE public.user_role;

CREATE TABLE public.user_role (
	id bigserial NOT NULL,
	"name" varchar NULL,
	create_date timestamp(0) NOT NULL,
	update_date timestamp(0) NOT NULL,
	remark varchar NULL,
	CONSTRAINT user_role_pk PRIMARY KEY (id)
);


-- public.room definition

-- Drop table

-- DROP TABLE public.room;

CREATE TABLE public.room (
	id bigserial NOT NULL,
	"name" varchar(60) NOT NULL,
	room_detail varchar NULL,
	room_type_id int8 NOT NULL,
	create_date timestamp NOT NULL,
	update_date timestamp NOT NULL,
	remark varchar NULL,
	admin_name varchar NULL,
	admin_detail varchar NULL,
	create_by int8 NULL,
	update_by int8 NULL,
	building_id int8 NOT NULL,
	CONSTRAINT room_pkey PRIMARY KEY (id),
	CONSTRAINT room_fk FOREIGN KEY (room_type_id) REFERENCES public.room_type(id),
	CONSTRAINT room_fk_1 FOREIGN KEY (building_id) REFERENCES public.building(id)
);


-- public."storage" definition

-- Drop table

-- DROP TABLE public."storage";

CREATE TABLE public."storage" (
	id bigserial NOT NULL,
	storage_type_id int8 NOT NULL,
	room_id int8 NULL,
	equipment_number varchar(50) NULL,
	"name" varchar(150) NULL,
	create_date timestamp NOT NULL,
	update_date timestamp NOT NULL,
	remark varchar(255) NULL,
	admin_name varchar NULL,
	admin_detail varchar NULL,
	create_by int8 NULL,
	update_by int8 NULL,
	pd_brand_id int8 NULL,
	CONSTRAINT storage_pkey PRIMARY KEY (id),
	CONSTRAINT storage_room_fk FOREIGN KEY (room_id) REFERENCES public.room(id),
	CONSTRAINT storage_type_fk FOREIGN KEY (storage_type_id) REFERENCES public.storage_type(id)
);


-- public."user" definition

-- Drop table

-- DROP TABLE public."user";

CREATE TABLE public."user" (
	id bigserial NOT NULL,
	"name" varchar NOT NULL,
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	create_date timestamp(0) NOT NULL,
	update_date timestamp(0) NOT NULL,
	remark varchar NULL,
	user_role_id int8 NOT NULL,
	email varchar NOT NULL,
	tel varchar(10) NULL,
	CONSTRAINT email UNIQUE (email),
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT username UNIQUE (username),
	CONSTRAINT user_fk FOREIGN KEY (user_role_id) REFERENCES public.user_role(id)
);


-- public.device definition

-- Drop table

-- DROP TABLE public.device;

CREATE TABLE public.device (
	id bigserial NOT NULL,
	equipment_number varchar(40) NULL,
	"name" varchar(255) NOT NULL,
	device_type_id int8 NOT NULL,
	storage_id int8 NULL,
	create_date timestamp NOT NULL,
	update_date timestamp NOT NULL,
	remark varchar(255) NULL,
	admin_name varchar NULL,
	admin_detail varchar NULL,
	create_by int8 NULL,
	update_by int8 NULL,
	username varchar NULL,
	"password" varchar NULL,
	pd_brand_id int8 NULL,
	CONSTRAINT device_pkey PRIMARY KEY (id),
	CONSTRAINT device_storage_fk FOREIGN KEY (storage_id) REFERENCES public."storage"(id),
	CONSTRAINT device_type_fk FOREIGN KEY (device_type_id) REFERENCES public.device_type(id)
);


-- public.log definition

-- Drop table

-- DROP TABLE public.log;

CREATE TABLE public.log (
	id bigserial NOT NULL,
	user_id int8 NOT NULL,
	log_type varchar(3) NOT NULL,
	decription varchar NOT NULL,
	remark varchar NULL,
	related_table varchar(50) NULL,
	related_table_id varchar NULL,
	create_date timestamp(0) NOT NULL,
	CONSTRAINT log_pk PRIMARY KEY (id),
	CONSTRAINT log_fk FOREIGN KEY (user_id) REFERENCES public."user"(id)
);


-- public."system" definition

-- Drop table

-- DROP TABLE public."system";

CREATE TABLE public."system" (
	id bigserial NOT NULL,
	"name" varchar NOT NULL,
	os varchar NOT NULL,
	cpu_core_usage int4 NULL,
	ram_cap_usage float4 NULL,
	disk_usage float4 NULL,
	device_id int8 NOT NULL,
	kvm varchar NULL,
	create_date timestamp(0) NOT NULL,
	update_date timestamp(0) NOT NULL,
	remark varchar NULL,
	username varchar NULL,
	"password" varchar NULL,
	create_by int8 NULL,
	update_by int8 NULL,
	os_version varchar NOT NULL,
	other_detail varchar NULL,
	pd_brand_id int8 NULL,
	system_type_id int8 NOT NULL,
	system_path varchar(200) NULL,
	CONSTRAINT system_pk PRIMARY KEY (id),
	CONSTRAINT system_fk FOREIGN KEY (device_id) REFERENCES public.device(id),
	CONSTRAINT system_type_fk FOREIGN KEY (system_type_id) REFERENCES public.system_type(id)
);


-- public."userToken" definition

-- Drop table

-- DROP TABLE public."userToken";

CREATE TABLE public."userToken" (
	id bigserial NOT NULL,
	user_id int8 NOT NULL,
	jwt_hash varchar NOT NULL,
	create_date timestamp(0) NOT NULL,
	expire_date timestamp(0) NOT NULL,
	remark varchar NULL,
	CONSTRAINT token_pk PRIMARY KEY (id),
	CONSTRAINT token_fk FOREIGN KEY (user_id) REFERENCES public."user"(id)
);
CREATE INDEX token_user_id_idx ON public.user_token USING btree (user_id);