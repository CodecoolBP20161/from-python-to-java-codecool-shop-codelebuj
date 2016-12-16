DROP TABLE IF EXISTS line_item;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS productCategory;
DROP TABLE IF EXISTS address;


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
zipcode VARCHAR(10),
address_info VARCHAR
);

CREATE TABLE orders
(
order_id SERIAL PRIMARY KEY,
first_name VARCHAR (40),
last_name VARCHAR (40),
email VARCHAR (60),
phone_number VARCHAR(20),
billing_address INTEGER REFERENCES address(address_id) on delete cascade on update cascade,
shipping_address INTEGER REFERENCES address(address_id) on delete cascade on update cascade
);

CREATE TABLE line_item
(
line_item_id SERIAL PRIMARY KEY,
order_id INTEGER REFERENCES orders(order_id) on delete cascade on update cascade,
product_id INTEGER REFERENCES product(product_id) on delete cascade on update cascade,
quantity INTEGER,
default_price INTEGER
);


