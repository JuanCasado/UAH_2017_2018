/*
Created		17/04/2018
Modified		18/04/2018
Project		
Model			
Company		
Author		
Version		
Database		PostgreSQL 8.1 
*/


/* Create Domains */



/* Create Tables */


Create table "departamento"
(
	"id_dpto" Char(20) NOT NULL UNIQUE,
	"cif" Char(20) NOT NULL,
	"nombre" Char(40) NOT NULL,
 primary key ("id_dpto")
) Without Oids;


Create table "empleado"
(
	"dni" Char(20) NOT NULL UNIQUE,
	"nombre" Char(20) NOT NULL,
	"apellidos" Char(40) NOT NULL,
	"sueldo" Double precision NOT NULL Check (sueldo > 0),
	"sueldo_anual" Double precision NOT NULL Check (sueldo_anual > 0),
	"id_dpto" Char(20) NOT NULL,
 primary key ("dni")
) Without Oids;


Create table "empresa"
(
	"cif" Char(20) NOT NULL UNIQUE,
	"nombre" Char(40) NOT NULL,
 primary key ("cif")
) Without Oids;


Create table "fundacion"
(
	"codigo" Char(20) NOT NULL UNIQUE,
	"cif" Char(20) NOT NULL,
	"nombre" Char(30) NOT NULL,
	"dedicacion" Char(40) NOT NULL,
 primary key ("codigo")
) Without Oids;


Create table "cliente"
(
	"dni" Char(20) NOT NULL UNIQUE,
	"nombre" Char(40) NOT NULL,
	"email" Char(40) NOT NULL UNIQUE,
 primary key ("dni")
) Without Oids;


Create table "producto"
(
	"id" Integer NOT NULL UNIQUE,
	"nombre" Char(20) NOT NULL,
	"descripcion" Char(50),
	"precio" Double precision NOT NULL,
	"id_dpto" Char(20) NOT NULL,
 primary key ("id")
) Without Oids;


Create table "obtiene"
(
	"id" Integer NOT NULL,
	"dni" Char(20) NOT NULL,
	"fecha" Date NOT NULL,
 primary key ("id","dni","fecha")
) Without Oids;



/* Create Tab 'Others' for Selected Tables */


/* Create Alternate Keys */



/* Create Indexes */



/* Create Foreign Keys */

Alter table "empleado" add  foreign key ("id_dpto") references "departamento" ("id_dpto") on update cascade on delete restrict;

Alter table "producto" add  foreign key ("id_dpto") references "departamento" ("id_dpto") on update cascade on delete set null;

Alter table "departamento" add  foreign key ("cif") references "empresa" ("cif") on update cascade on delete restrict;

Alter table "fundacion" add  foreign key ("cif") references "empresa" ("cif") on update cascade on delete restrict;

Alter table "obtiene" add  foreign key ("dni") references "cliente" ("dni") on update cascade on delete cascade;

Alter table "obtiene" add  foreign key ("id") references "producto" ("id") on update cascade on delete set null;



/* Create Procedures */



/* Create Views */



/* Create Referential Integrity Triggers */

/* Referential integrity for update on table "departamento" */
 
/* Function "fn_tu_departamento"() for trigger "tu_departamento" */
Create function "fn_tu_departamento"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	 
	/* Check parent table "empresa", when child table "departamento" changes. */
 if new."cif" != old."cif" then
 	select count(*) into nRows
 	from "empresa"
 	where new."cif" = "empresa"."cif";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "empresa". Cannot update child table "departamento".'';
 	end if;
 end if;	
 
return old;
end;'
language 'plpgsql';
					
/* Update trigger "tu_departamento" for table "departamento" */
Create trigger "tu_departamento"
after update on "departamento"
for each row execute procedure "fn_tu_departamento"();

/* Referential integrity for update on table "empleado" */
 
/* Function "fn_tu_empleado"() for trigger "tu_empleado" */
Create function "fn_tu_empleado"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	 
	/* Check parent table "departamento", when child table "empleado" changes. */
 if new."id_dpto" != old."id_dpto" then
 	select count(*) into nRows
 	from "departamento"
 	where new."id_dpto" = "departamento"."id_dpto";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "departamento". Cannot update child table "empleado".'';
 	end if;
 end if;	
 
return old;
end;'
language 'plpgsql';
					
/* Update trigger "tu_empleado" for table "empleado" */
Create trigger "tu_empleado"
after update on "empleado"
for each row execute procedure "fn_tu_empleado"();

/* Referential integrity for update on table "fundacion" */
 
/* Function "fn_tu_fundacion"() for trigger "tu_fundacion" */
Create function "fn_tu_fundacion"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	 
	/* Check parent table "empresa", when child table "fundacion" changes. */
 if new."cif" != old."cif" then
 	select count(*) into nRows
 	from "empresa"
 	where new."cif" = "empresa"."cif";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "empresa". Cannot update child table "fundacion".'';
 	end if;
 end if;	
 
return old;
end;'
language 'plpgsql';
					
/* Update trigger "tu_fundacion" for table "fundacion" */
Create trigger "tu_fundacion"
after update on "fundacion"
for each row execute procedure "fn_tu_fundacion"();

/* Referential integrity for update on table "producto" */
 
/* Function "fn_tu_producto"() for trigger "tu_producto" */
Create function "fn_tu_producto"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	 
	/* Check parent table "departamento", when child table "producto" changes. */
 if new."id_dpto" != old."id_dpto" then
 	select count(*) into nRows
 	from "departamento"
 	where new."id_dpto" = "departamento"."id_dpto";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "departamento". Cannot update child table "producto".'';
 	end if;
 end if;	
 
return old;
end;'
language 'plpgsql';
					
/* Update trigger "tu_producto" for table "producto" */
Create trigger "tu_producto"
after update on "producto"
for each row execute procedure "fn_tu_producto"();

/* Referential integrity for update on table "obtiene" */
 
/* Function "fn_tu_obtiene"() for trigger "tu_obtiene" */
Create function "fn_tu_obtiene"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	 
	/* Check parent table "producto", when child table "obtiene" changes. */
 if new."id" != old."id" then
 	select count(*) into nRows
 	from "producto"
 	where new."id" = "producto"."id";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "producto". Cannot update child table "obtiene".'';
 	end if;
 end if;	
 /* Check parent table "cliente", when child table "obtiene" changes. */
 if new."dni" != old."dni" then
 	select count(*) into nRows
 	from "cliente"
 	where new."dni" = "cliente"."dni";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "cliente". Cannot update child table "obtiene".'';
 	end if;
 end if;	
 
return old;
end;'
language 'plpgsql';
					
/* Update trigger "tu_obtiene" for table "obtiene" */
Create trigger "tu_obtiene"
after update on "obtiene"
for each row execute procedure "fn_tu_obtiene"();



/* Referential integrity for insert on table "departamento" */
 
/* Function "fn_ti_departamento"() for trigger "ti_departamento" */
Create function "fn_ti_departamento"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	/* Check parent table "empresa" when values inserted into child table "departamento" */
 if new."cif" is not null then
 	select count(*) into nRows
 	from "empresa"
 	where new."cif" = "empresa"."cif";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "empresa". Cannot insert values into child table "departamento".'';
 	end if;	
 end if;	
 
return new;
end;'
language 'plpgsql';
					
/* Insert trigger "ti_departamento" for table "departamento" */
Create trigger "ti_departamento"
before insert on "departamento"
for each row execute procedure "fn_ti_departamento"();

/* Referential integrity for insert on table "empleado" */
 
/* Function "fn_ti_empleado"() for trigger "ti_empleado" */
Create function "fn_ti_empleado"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	/* Check parent table "departamento" when values inserted into child table "empleado" */
 if new."id_dpto" is not null then
 	select count(*) into nRows
 	from "departamento"
 	where new."id_dpto" = "departamento"."id_dpto";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "departamento". Cannot insert values into child table "empleado".'';
 	end if;	
 end if;	
 
return new;
end;'
language 'plpgsql';
					
/* Insert trigger "ti_empleado" for table "empleado" */
Create trigger "ti_empleado"
before insert on "empleado"
for each row execute procedure "fn_ti_empleado"();

/* Referential integrity for insert on table "fundacion" */
 
/* Function "fn_ti_fundacion"() for trigger "ti_fundacion" */
Create function "fn_ti_fundacion"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	/* Check parent table "empresa" when values inserted into child table "fundacion" */
 if new."cif" is not null then
 	select count(*) into nRows
 	from "empresa"
 	where new."cif" = "empresa"."cif";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "empresa". Cannot insert values into child table "fundacion".'';
 	end if;	
 end if;	
 
return new;
end;'
language 'plpgsql';
					
/* Insert trigger "ti_fundacion" for table "fundacion" */
Create trigger "ti_fundacion"
before insert on "fundacion"
for each row execute procedure "fn_ti_fundacion"();

/* Referential integrity for insert on table "producto" */
 
/* Function "fn_ti_producto"() for trigger "ti_producto" */
Create function "fn_ti_producto"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	/* Check parent table "departamento" when values inserted into child table "producto" */
 if new."id_dpto" is not null then
 	select count(*) into nRows
 	from "departamento"
 	where new."id_dpto" = "departamento"."id_dpto";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "departamento". Cannot insert values into child table "producto".'';
 	end if;	
 end if;	
 
return new;
end;'
language 'plpgsql';
					
/* Insert trigger "ti_producto" for table "producto" */
Create trigger "ti_producto"
before insert on "producto"
for each row execute procedure "fn_ti_producto"();

/* Referential integrity for insert on table "obtiene" */
 
/* Function "fn_ti_obtiene"() for trigger "ti_obtiene" */
Create function "fn_ti_obtiene"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	/* Check parent table "producto" when values inserted into child table "obtiene" */
 if new."id" is not null then
 	select count(*) into nRows
 	from "producto"
 	where new."id" = "producto"."id";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "producto". Cannot insert values into child table "obtiene".'';
 	end if;	
 end if;	
 /* Check parent table "cliente" when values inserted into child table "obtiene" */
 if new."dni" is not null then
 	select count(*) into nRows
 	from "cliente"
 	where new."dni" = "cliente"."dni";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "cliente". Cannot insert values into child table "obtiene".'';
 	end if;	
 end if;	
 
return new;
end;'
language 'plpgsql';
					
/* Insert trigger "ti_obtiene" for table "obtiene" */
Create trigger "ti_obtiene"
before insert on "obtiene"
for each row execute procedure "fn_ti_obtiene"();



/* Create User-Defined Triggers */



/* Create Roles */



/* Create Role Permissions */
/* Role permissions on tables */

/* Role permissions on views */

/* Role permissions on procedures */





