DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS productCategory;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS orders;

CREATE TABLE productCategory
(
productcategory_id SERIAL PRIMARY KEY,
productcategory_name VARCHAR (20),
productcategory_department VARCHAR (40),
productcategory_description VARCHAR (150)
);

CREATE TABLE supplier
(
supplier_id SERIAL PRIMARY KEY,
supplier_name VARCHAR (40),
supplier_description VARCHAR (150)
);

CREATE TABLE product
(
product_id SERIAL PRIMARY KEY,
product_name VARCHAR (40),
product_description VARCHAR (150),
product_defaultPrice FLOAT (20),
product_defaultCurrency VARCHAR (3),
product_supplier INT REFERENCES supplier(supplier_id) on delete cascade on update cascade,
product_productCategory INT REFERENCES productcategory(productcategory_id) on delete cascade on update cascade
);

CREATE TABLE address
(
address_id SERIAL PRIMARY KEY ,
country VARCHAR ,
city VARCHAR ,
zipcode INT,
address_info VARCHAR
);

CREATE TABLE orders
(
order_id SERIAL PRIMARY KEY,
first_name VARCHAR (40),
last_name VARCHAR (150),
email VARCHAR (20),
phone_number VARCHAR,
billing_address INTEGER REFERENCES address(address_id),
shipping_address INTEGER REFERENCES address(address_id)
);


