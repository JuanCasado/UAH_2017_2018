/*
Created: 21/04/2015
Modified: 21/04/2015
Model: PostgreSQL 9.2
Database: PostgreSQL 9.2
*/


-- Create tables section -------------------------------------------------

-- Table Product

CREATE TABLE "Product"(
 "Code_product" Numeric(20,0) NOT NULL,
 "Categorie" Character(20),
 "Description" Character(20),
 "Name" Character(20),
 "Price_wtih_VAT" Numeric(10,2),
 "Manufacturer" Character(20),
 "Stock" Numeric(5,0)
)
WITH (OIDS=FALSE)
;

-- Add keys for table Product

ALTER TABLE "Product" ADD CONSTRAINT "pk_PRODUCTO" PRIMARY KEY ("Code_product")
;

-- Table Customer

CREATE TABLE "Customer"(
 "NIF" Numeric(8,0) NOT NULL,
 "Name_customer" Character(40) NOT NULL,
 "Address" Character(40),
 "Method_pay" Character(20),
 "Phone_number" Numeric(9,0)
)
WITH (OIDS=FALSE)
;

-- Add keys for table Customer

ALTER TABLE "Customer" ADD CONSTRAINT "pk_Cliente" PRIMARY KEY ("NIF")
;

ALTER TABLE "Customer" ADD CONSTRAINT "NIF" UNIQUE ("NIF")
;

-- Table Shopping

CREATE TABLE "Shopping"(
 "Shopping_number" Numeric(10,0) NOT NULL,
 "Date" Date,
 "Amount_without_VAT" Numeric(10,3),
 "Amount_with_VAT" Numeric(10,3),
 "NIF" Numeric(8,0),
 "Code" Numeric(20,0),
 "ID_offer" Numeric(20,0),
 "ID" Numeric(20,0),
 "Code_product" Numeric(20,0)
)
WITH (OIDS=FALSE)
;

-- Create indexes for table Shopping

CREATE INDEX "IX_Relationship25" ON "Shopping" ("NIF")
;

CREATE INDEX "IX_Relationship32" ON "Shopping" ("Code")
;

CREATE INDEX "IX_Relationship33" ON "Shopping" ("ID_offer")
;

CREATE INDEX "IX_Relationship35" ON "Shopping" ("ID")
;

CREATE INDEX "IX_Relationship36" ON "Shopping" ("Code_product")
;

-- Add keys for table Shopping

ALTER TABLE "Shopping" ADD CONSTRAINT "pk_Compra" PRIMARY KEY ("Shopping_number")
;

ALTER TABLE "Shopping" ADD CONSTRAINT "Numero compra" UNIQUE ("Shopping_number")
;

-- Table Bill

CREATE TABLE "Bill"(
 "Number_bill" Numeric(10,0) NOT NULL,
 "Shopping_number" Numeric(10,0) NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table Bill

ALTER TABLE "Bill" ADD CONSTRAINT "pk_Factura" PRIMARY KEY ("Number_bill","Shopping_number")
;

ALTER TABLE "Bill" ADD CONSTRAINT "Numero factura" UNIQUE ("Number_bill")
;

-- Table Packs

CREATE TABLE "Packs"(
 "ID" Numeric(20,0) NOT NULL,
 "Price" Numeric(10,3)
)
WITH (OIDS=FALSE)
;

-- Add keys for table Packs

ALTER TABLE "Packs" ADD CONSTRAINT "pk_Packs" PRIMARY KEY ("ID")
;

ALTER TABLE "Packs" ADD CONSTRAINT "ID" UNIQUE ("ID")
;

-- Table Offer

CREATE TABLE "Offer"(
 "ID_offer" Numeric(20,0) NOT NULL,
 "Name" Character(20),
 "Start_date" Date,
 "End_date" Date,
 "Price_offer" Numeric(20,0)
)
WITH (OIDS=FALSE)
;

-- Add keys for table Offer

ALTER TABLE "Offer" ADD CONSTRAINT "pk_Oferta" PRIMARY KEY ("ID_offer")
;

ALTER TABLE "Offer" ADD CONSTRAINT "ID oferta" UNIQUE ("ID_offer")
;

-- Table Services

CREATE TABLE "Services"(
 "Code" Numeric(20,0) NOT NULL,
 "Name" Character(20),
 "Description" Character(80),
 "Additional_cost_with_VAT" Numeric(10,3)
)
WITH (OIDS=FALSE)
;

-- Add keys for table Services

ALTER TABLE "Services" ADD CONSTRAINT "pk_Servicio" PRIMARY KEY ("Code")
;

-- Table Reviews

CREATE TABLE "Reviews"(
 "ID" Numeric(20,0) NOT NULL,
 "Date" Date,
 "Description" Character(80),
 "Score" Numeric(2,0),
 "Code_product" Numeric(20,0) NOT NULL,
 "Code_customer" Character(20) NOT NULL,
 "NIF" Numeric(8,0) NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table Reviews

ALTER TABLE "Reviews" ADD CONSTRAINT "pk_Opinion" PRIMARY KEY ("ID","Code_product","Code_customer","NIF")
;

-- Table Customer_physical

CREATE TABLE "Customer_physical"(
 "Postal_code" Numeric(5,0),
 "NIF" Numeric(8,0) NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table Customer_physical

ALTER TABLE "Customer_physical" ADD CONSTRAINT "pk_Cliente_fisico" PRIMARY KEY ("NIF")
;

-- Table Customer_online

CREATE TABLE "Customer_online"(
 "Code_customer" Character(20) NOT NULL,
 "Users" Character(20),
 "Email" Character(20),
 "Password" Character(20),
 "NIF" Numeric(8,0) NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table Customer_online

ALTER TABLE "Customer_online" ADD CONSTRAINT "pk_Cliente_online" PRIMARY KEY ("Code_customer","NIF")
;

-- Table Associated

CREATE TABLE "Associated"(
 "Code_product" Numeric(20,0) NOT NULL,
 "Code" Numeric(20,0) NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table Associated

ALTER TABLE "Associated" ADD CONSTRAINT "Key6" PRIMARY KEY ("Code_product","Code")
;

-- Table Refund

CREATE TABLE "Refund"(
 "CodeP" Numeric(20,0) NOT NULL,
 "Shopping_number" Numeric(10,0) NOT NULL,
 "Date_return" Date,
 "imperfection" Boolean
)
WITH (OIDS=FALSE)
;

-- Add keys for table Refund

ALTER TABLE "Refund" ADD CONSTRAINT "Key7" PRIMARY KEY ("CodeP","Shopping_number")
;

-- Table is_offered

CREATE TABLE "is_offered"(
 "Code_product" Numeric(20,0) NOT NULL,
 "ID" Numeric(20,0) NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table is_offered

ALTER TABLE "is_offered" ADD CONSTRAINT "Key8" PRIMARY KEY ("Code_product","ID")
;

-- Table Have

CREATE TABLE "Have"(
 "Code_product" Numeric(20,0) NOT NULL,
 "ID_offer" Numeric(20,0) NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table Have

ALTER TABLE "Have" ADD CONSTRAINT "Key9" PRIMARY KEY ("Code_product","ID_offer")
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE "Shopping" ADD CONSTRAINT "Relationship36" FOREIGN KEY ("Code_product") REFERENCES "Product" ("Code_product") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Shopping" ADD CONSTRAINT "Relationship35" FOREIGN KEY ("ID") REFERENCES "Packs" ("ID") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Shopping" ADD CONSTRAINT "Relationship33" FOREIGN KEY ("ID_offer") REFERENCES "Offer" ("ID_offer") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Shopping" ADD CONSTRAINT "Relationship32" FOREIGN KEY ("Code") REFERENCES "Services" ("Code") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Have" ADD CONSTRAINT "Relationship29" FOREIGN KEY ("ID_offer") REFERENCES "Offer" ("ID_offer") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Have" ADD CONSTRAINT "Relationship28" FOREIGN KEY ("Code_product") REFERENCES "Product" ("Code_product") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Shopping" ADD CONSTRAINT "Relationship25" FOREIGN KEY ("NIF") REFERENCES "Customer" ("NIF") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "is_offered" ADD CONSTRAINT "Relationship24" FOREIGN KEY ("ID") REFERENCES "Packs" ("ID") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "is_offered" ADD CONSTRAINT "Relationship23" FOREIGN KEY ("Code_product") REFERENCES "Product" ("Code_product") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Refund" ADD CONSTRAINT "Relationship21" FOREIGN KEY ("Shopping_number") REFERENCES "Shopping" ("Shopping_number") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Refund" ADD CONSTRAINT "Devolucion" FOREIGN KEY ("CodeP") REFERENCES "Product" ("Code_product") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Associated" ADD CONSTRAINT "Relationship19" FOREIGN KEY ("Code") REFERENCES "Services" ("Code") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Associated" ADD CONSTRAINT "Relationship18" FOREIGN KEY ("Code_product") REFERENCES "Product" ("Code_product") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Reviews" ADD CONSTRAINT "Relationship17" FOREIGN KEY ("Code_customer", "NIF") REFERENCES "Customer_online" ("Code_customer", "NIF") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Reviews" ADD CONSTRAINT "Relationship16" FOREIGN KEY ("Code_product") REFERENCES "Product" ("Code_product") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Customer_online" ADD CONSTRAINT "Relationship15" FOREIGN KEY ("NIF") REFERENCES "Customer" ("NIF") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Customer_physical" ADD CONSTRAINT "Relationship14" FOREIGN KEY ("NIF") REFERENCES "Customer" ("NIF") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Bill" ADD CONSTRAINT "Relationship5" FOREIGN KEY ("Shopping_number") REFERENCES "Shopping" ("Shopping_number") ON DELETE NO ACTION ON UPDATE NO ACTION
;





