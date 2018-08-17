/* Triggers que funcionan correctamente*/
--Para que ocurra ANTES(before) de cualquier INSERT,UPDATE ó DELETE
--Para que ocurra DESPUES(after) de cualquier INSERT,UPDATE ó DELETE

--Bill

create or replace function my_bill() returns trigger as $bill$
	begin
	if  new.number_bill is null then
		raise exception 'Not insert number of bill';
	end if;
	if new.shopping_number is null then
		raise exception '% Not show number of shopping',new.shopping_number;
	end if;	

	return  new;
	end;
$bill$ language plpgsql;

create trigger my_bill before insert on bill
for each row --para cada linea
execute procedure my_bill();

			/*Mostramos la raise exception*/
insert into bill (number_bill,shopping_number)
values (2000,null);


--Packs


create or replace function my_pack() returns trigger as $pack$
	begin
	if  new.id is null then
		raise exception 'Not insert ID';
	end if;
	if new.price is null then
		raise exception '% Don´t insert price',new.price;
	end if;	

	return  new;
	end;
$pack$ language plpgsql;

create trigger pack before insert or update on packs
for each row --para cada linea
execute procedure my_pack();
				/*Con este codigo podemos insertar y actualizar packs, ademas usando null provocamos excepciones */
insert into packs (id,price)
values(100011,2000);

update packs set 
id =100011,
price = 2000
where id = 100023

select * from packs

--Offer Tiene problemas

create function my_offer() returns trigger as $offer$

declare

	x integer;	
	y integer;	
										 
begin
	 
if new.id_offer != old.id_offer then
 	select count(*) into x
 	from offer
 	where new.id_offer = offer.id_offer;
 	if (x = 0) then
 		raise exception 'Cant show';
 	end if;
 end if; 
return old;
end;
&offer& 
language 'plpgsql';
					

Create trigger my_offer after insert or update or delete on offer
for each row 
execute procedure my_offer(); 	

--Services

create or replace function my_service() returns trigger as $service$
	begin
	if  new.code is null then
		raise exception 'Not insert code';
	end if;
	if new.name is null then
		raise exception '% Don´t insert name',new.price;
	end if;
	if new.description is null then
		raise exception ' Not insert description',new.price;
	end if;	
	/*if new.additional_cost_with_VAT is null then
		raise exception '% Don´t insert additional_cost_with_VAT',new.price;
	end if;	*/

	return  new;
	end;
$service$ language plpgsql;

create trigger service2 before insert or update or delete on services
for each row --para cada linea
execute procedure my_service();
				/*Con este codigo podemos insertar y actualizar services, ademas usando null provocamos excepciones */
insert into services (code,name,description)
values(null,'ServiceVIP2','New Service two');

delete from "services"
where new."code" = 15000;

update services set 
code = null,
description = 'ServiceVIP2'
where code = 15000


select * from services

--Generated code product

CREATE function generated_customer() RETURNS trigger AS $customer$
DECLARE

nifCustomer INTEGER;

BEGIN

SELECT count(*) INTO nifCustomer 

FROM product;

UPDATE customer SET nif = nifCustomer WHERE new.nif = customer.nif;

RAISE NOTICE 'Code customer generated successfully';

RETURN new;
	
END;

$customer$
LANGUAGE plpgsql;

CREATE TRIGGER generated_customer AFTER INSERT ON customer
FOR EACH ROW
execute procedure generated_customer();

 


--Generated code offer

CREATE function generated_offer() RETURNS trigger AS
DECLARE

idOffer INTEGER;

BEGIN

SELECT count(*) INTO idOffer FROM offer;

UPDATE offer SET id = idOffer WHERE new.id = offer.id;

RAISE NOTICE 'Code offer generated successfully';

RETURN new;

END;


LANGUAGE plpgsql;

CREATE TRIGGER generated_offer AFTER INSERT ON offer
FOR EACH ROW
execute procedure generated_offer();


