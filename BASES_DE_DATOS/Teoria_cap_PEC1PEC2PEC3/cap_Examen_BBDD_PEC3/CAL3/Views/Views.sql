/*CONSULTA VIEW1:*/
create view consultaview1 as (select sum("amount_with_VAT")
from "shopping")

/*CONSULTA VIEW2:*/
SELECT COUNT(*), "categorie"
FROM "product"
GROUP BY "product"."categorie";

/*CONSULTA VIEW3:*/
select * 
from public. "product"
where "categorie" = 'Phone';

/*CONSULTA VIEW4:*/
SELECT "name"
FROM "product"
WHERE "name" not in (
SELECT "name"
FROM "product","is_offered"
WHERE "product"."code_product" = "is_offered"."id");

/*CONSULTA VIEW5:*/
SELECT "customer"."name_customer", "shopping"."nif", SUM("amount_with_VAT")
FROM "shopping","customer"
WHERE "shopping"."nif"="customer"."nif"
GROUP BY "customer"."name_customer","shopping"."nif";

/*CONSULTA VIEW6:*/
SELECT "Name", "product"."code_product"
FROM "product", "is_offered"
WHERE "product"."code_product"="is_offered"."code_product";

/*CONSULTA VIEW7:*/
SELECT "name", AVG("score")
FROM "product", "reviews"
WHERE "product"."code_product"="reviews"."code_product"
GROUP BY "name";

/*CONSULTA VIEW8:*/
SELECT COUNT(*) AS time, "product"."name"
FROM "product","shopping"
WHERE "product"."code_product"="shopping"."code_product"
GROUP BY "name"
ORDER BY time desc
limit 1;

/*CONSULTA VIEW9:*/
select SUM("shopping"."amount_with_VAT" - "shopping"."amount_without_VAT")
from "shopping","offer"
where "offer"."id_offer"= "shopping"."id_offer";

/*CONSULTA VIEW10:*/
SELECT COUNT(*), "name"
FROM "product", "refund"
WHERE "product"."code_product"="refund"."codeP"
GROUP BY "name";

/*CONSULTA VIEW11:*/
select sum("amount_with_VAT")
from "customer_online","customer", "shopping"
where "customer"."nif"="customer_online"."nif" and "customer"."nif"="customer"."nif";

select sum("amount_with_VAT")
from "customer_physical","customer", "shopping"
where "customer"."nif"="customer_physical"."nif" and "customer"."nif"="shopping"."nif";

/*CONSULTA VIEW12:*/
select "customer"."name_customer","shopping"."amount_with_VAT"
from "customer","shopping","bill"
where "customer"."nif" = "shopping"."nif" and "bill"."shopping_number" = "shopping"."shopping_number";

/*CONSULTA VIEW13:*/
select  avg("date_return"), "categorie"
from (select ("refund"."date_return"-"shopping"."date") from "refund","shopping" )as "date_return","product";

/*CONSULTA VIEW14:*/
SELECT "services"."name", count(*) as time
FROM "services","shopping"
where "services"."code"="shopping"."code"
GROUP BY "services"."name"
ORDER BY time desc;

/*CONSULTA VIEW15:*/
SELECT sum("services"."additional_cost_with_VAT")
from "services","shopping"
where "services"."code"="shopping"."code";

/*CONSULTA VIEW16:*/
select count(*)
from "refund"
where "imperfection" = true ;

/*CONSULTA 17:*/

/*CONSULTA VIEW18:*/
SELECT "code_product", "product"."name"
FROM "product", "offer"
WHERE "product"."code_product"<>"offer"."id_offer";
--WHERE "Code_product" NOT IN (SELECT "Code_product" FROM "have");

/*CONSULTA VIEW19:*/
SELECT "date","amount_with_VAT", "offer"."name" 
FROM "shopping", "offer"
WHERE "shopping"."date" BETWEEN "offer"."start_date" AND "offer"."end_date";

/*CONSULTA VIEW20:*/
select "categorie",count(*) as time
from "product"
group by "categorie"
order by "time" desc limit 10;