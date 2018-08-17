/*
Created		6/3/18
Modified		6/3/18
Project		
Model			
Company		
Author		
Version		
Database		PostgreSQL 8.1 
*/


/* Create Domains */



/* Create Tables */


Create table "Cliente"
(
	"DNI" Char(10) NOT NULL UNIQUE,
 primary key ("DNI")
) Without Oids;


Create table "Telefono"
(
	"numero_telefono" Char(13) NOT NULL,
	"DNI" Char(10) NOT NULL,
 primary key ("numero_telefono","DNI")
) Without Oids;


Create table "Coche"
(
	"Bastidor" Char(20) NOT NULL UNIQUE,
	"Cilindrada" Char(20) NOT NULL,
	"matricula" Char(20) NOT NULL UNIQUE,
	"DNI" Char(10) NOT NULL,
 primary key ("Bastidor")
) Without Oids;


Create table "ITV"
(
	"CIF" Char(20) NOT NULL UNIQUE,
	"dir_Calle" Char(20) NOT NULL,
	"dir_CP" Char(10) NOT NULL,
	"dir_Localidad" Char(20) NOT NULL,
 primary key ("CIF")
) Without Oids;


Create table "Efectua"
(
	"Bastidor" Char(20) NOT NULL,
	"CIF" Char(20) NOT NULL,
	"Fecha" Timestamp NOT NULL,
	"valoracion" Integer NOT NULL,
 primary key ("Bastidor","CIF","Fecha")
) Without Oids;


Create table "Empleado"
(
	"DNI" Char(10) NOT NULL UNIQUE,
	"DNI_jefe" Char(10),
	"CIF" Char(20) NOT NULL,
	"nombre" Char(20) NOT NULL,
	"email" Char(20) NOT NULL UNIQUE,
	"fecha_contrato" Timestamp NOT NULL,
 primary key ("DNI")
) Without Oids;



/* Create Tab 'Others' for Selected Tables */


/* Create Alternate Keys */



/* Create Indexes */



/* Create Foreign Keys */

Alter table "Telefono" add  foreign key ("DNI") references "Cliente" ("DNI") on update cascade on delete cascade;

Alter table "Coche" add  foreign key ("DNI") references "Cliente" ("DNI") on update cascade on delete set null;

Alter table "Efectua" add  foreign key ("Bastidor") references "Coche" ("Bastidor") on update cascade on delete cascade;

Alter table "Efectua" add  foreign key ("CIF") references "ITV" ("CIF") on update cascade on delete cascade;

Alter table "Empleado" add  foreign key ("CIF") references "ITV" ("CIF") on update cascade on delete restrict;

Alter table "Empleado" add  foreign key ("DNI_jefe") references "Empleado" ("DNI") on update cascade on delete set null;



/* Create Procedures */



/* Create Views */



/* Create Referential Integrity Triggers */

/* Referential integrity for update on table "Telefono" */
 
/* Function "fn_tu_Telefono"() for trigger "tu_Telefono" */
Create function "fn_tu_Telefono"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	 
	/* Check parent table "Cliente", when child table "Telefono" changes. */
 if new."DNI" != old."DNI" then
 	select count(*) into nRows
 	from "Cliente"
 	where new."DNI" = "Cliente"."DNI";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "Cliente". Cannot update child table "Telefono".'';
 	end if;
 end if;	
 
return old;
end;'
language 'plpgsql';
					
/* Update trigger "tu_Telefono" for table "Telefono" */
Create trigger "tu_Telefono"
after update on "Telefono"
for each row execute procedure "fn_tu_Telefono"();

/* Referential integrity for update on table "Coche" */
 
/* Function "fn_tu_Coche"() for trigger "tu_Coche" */
Create function "fn_tu_Coche"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	 
	/* Check parent table "Cliente", when child table "Coche" changes. */
 if new."DNI" != old."DNI" then
 	select count(*) into nRows
 	from "Cliente"
 	where new."DNI" = "Cliente"."DNI";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "Cliente". Cannot update child table "Coche".'';
 	end if;
 end if;	
 
return old;
end;'
language 'plpgsql';
					
/* Update trigger "tu_Coche" for table "Coche" */
Create trigger "tu_Coche"
after update on "Coche"
for each row execute procedure "fn_tu_Coche"();

/* Referential integrity for update on table "Efectua" */
 
/* Function "fn_tu_Efectua"() for trigger "tu_Efectua" */
Create function "fn_tu_Efectua"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	 
	/* Check parent table "Coche", when child table "Efectua" changes. */
 if new."Bastidor" != old."Bastidor" then
 	select count(*) into nRows
 	from "Coche"
 	where new."Bastidor" = "Coche"."Bastidor";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "Coche". Cannot update child table "Efectua".'';
 	end if;
 end if;	
 /* Check parent table "ITV", when child table "Efectua" changes. */
 if new."CIF" != old."CIF" then
 	select count(*) into nRows
 	from "ITV"
 	where new."CIF" = "ITV"."CIF";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "ITV". Cannot update child table "Efectua".'';
 	end if;
 end if;	
 
return old;
end;'
language 'plpgsql';
					
/* Update trigger "tu_Efectua" for table "Efectua" */
Create trigger "tu_Efectua"
after update on "Efectua"
for each row execute procedure "fn_tu_Efectua"();

/* Referential integrity for update on table "Empleado" */
 
/* Function "fn_tu_Empleado"() for trigger "tu_Empleado" */
Create function "fn_tu_Empleado"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	 
	/* Check parent table "ITV", when child table "Empleado" changes. */
 if new."CIF" != old."CIF" then
 	select count(*) into nRows
 	from "ITV"
 	where new."CIF" = "ITV"."CIF";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "ITV". Cannot update child table "Empleado".'';
 	end if;
 end if;	
 /* Check parent table "Empleado", when child table "Empleado" changes. */
 if new."DNI_jefe" != old."DNI_jefe" then
 	select count(*) into nRows
 	from "Empleado"
 	where new."DNI_jefe" = "Empleado"."DNI";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "Empleado". Cannot update child table "Empleado".'';
 	end if;
 end if;	
 
return old;
end;'
language 'plpgsql';
					
/* Update trigger "tu_Empleado" for table "Empleado" */
Create trigger "tu_Empleado"
after update on "Empleado"
for each row execute procedure "fn_tu_Empleado"();



/* Referential integrity for insert on table "Telefono" */
 
/* Function "fn_ti_Telefono"() for trigger "ti_Telefono" */
Create function "fn_ti_Telefono"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	/* Check parent table "Cliente" when values inserted into child table "Telefono" */
 if new."DNI" is not null then
 	select count(*) into nRows
 	from "Cliente"
 	where new."DNI" = "Cliente"."DNI";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "Cliente". Cannot insert values into child table "Telefono".'';
 	end if;	
 end if;	
 
return new;
end;'
language 'plpgsql';
					
/* Insert trigger "ti_Telefono" for table "Telefono" */
Create trigger "ti_Telefono"
before insert on "Telefono"
for each row execute procedure "fn_ti_Telefono"();

/* Referential integrity for insert on table "Coche" */
 
/* Function "fn_ti_Coche"() for trigger "ti_Coche" */
Create function "fn_ti_Coche"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	/* Check parent table "Cliente" when values inserted into child table "Coche" */
 if new."DNI" is not null then
 	select count(*) into nRows
 	from "Cliente"
 	where new."DNI" = "Cliente"."DNI";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "Cliente". Cannot insert values into child table "Coche".'';
 	end if;	
 end if;	
 
return new;
end;'
language 'plpgsql';
					
/* Insert trigger "ti_Coche" for table "Coche" */
Create trigger "ti_Coche"
before insert on "Coche"
for each row execute procedure "fn_ti_Coche"();

/* Referential integrity for insert on table "Efectua" */
 
/* Function "fn_ti_Efectua"() for trigger "ti_Efectua" */
Create function "fn_ti_Efectua"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	/* Check parent table "Coche" when values inserted into child table "Efectua" */
 if new."Bastidor" is not null then
 	select count(*) into nRows
 	from "Coche"
 	where new."Bastidor" = "Coche"."Bastidor";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "Coche". Cannot insert values into child table "Efectua".'';
 	end if;	
 end if;	
 /* Check parent table "ITV" when values inserted into child table "Efectua" */
 if new."CIF" is not null then
 	select count(*) into nRows
 	from "ITV"
 	where new."CIF" = "ITV"."CIF";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "ITV". Cannot insert values into child table "Efectua".'';
 	end if;	
 end if;	
 
return new;
end;'
language 'plpgsql';
					
/* Insert trigger "ti_Efectua" for table "Efectua" */
Create trigger "ti_Efectua"
before insert on "Efectua"
for each row execute procedure "fn_ti_Efectua"();

/* Referential integrity for insert on table "Empleado" */
 
/* Function "fn_ti_Empleado"() for trigger "ti_Empleado" */
Create function "fn_ti_Empleado"() returns trigger as '
declare
	nRows integer;	
	maxCard integer;										 
begin
	/* Check parent table "ITV" when values inserted into child table "Empleado" */
 if new."CIF" is not null then
 	select count(*) into nRows
 	from "ITV"
 	where new."CIF" = "ITV"."CIF";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "ITV". Cannot insert values into child table "Empleado".'';
 	end if;	
 end if;	
 /* Check parent table "Empleado" when values inserted into child table "Empleado" */
 if new."DNI_jefe" is not null then
 	select count(*) into nRows
 	from "Empleado"
 	where new."DNI_jefe" = "Empleado"."DNI";
 	if (nRows = 0) then
 		raise exception ''Parent does not exist in table "Empleado". Cannot insert values into child table "Empleado".'';
 	end if;	
 end if;	
 
return new;
end;'
language 'plpgsql';
					
/* Insert trigger "ti_Empleado" for table "Empleado" */
Create trigger "ti_Empleado"
before insert on "Empleado"
for each row execute procedure "fn_ti_Empleado"();



/* Create User-Defined Triggers */



/* Create Roles */



/* Create Role Permissions */
/* Role permissions on tables */

/* Role permissions on views */

/* Role permissions on procedures */





