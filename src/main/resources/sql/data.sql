
INSERT INTO productCategory (productcategory_name, productcategory_department, productcategory_description)
VALUES ('Tablet', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO productCategory (productcategory_name, productcategory_department, productcategory_description)
VALUES ('Notebook', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO productCategory (productcategory_name, productcategory_department, productcategory_description)
VALUES ('Camera', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');

INSERT INTO supplier (supplier_name, supplier_description)
VALUES ('Amazon', 'Digital content and services');
INSERT INTO supplier (supplier_name, supplier_description)
VALUES ('Lenovo', 'Computers');
INSERT INTO supplier (supplier_name, supplier_description)
VALUES ('Acer', 'Computers');
INSERT INTO supplier (supplier_name, supplier_description)
VALUES ('Canon', 'Camera');

INSERT INTO product (product_name, product_description, product_defaultprice, product_defaultcurrency, product_productcategory, product_supplier)
VALUES ('Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 'USD', 1, 1);
INSERT INTO product (product_name, product_description, product_defaultprice, product_defaultcurrency, product_productcategory, product_supplier)
VALUES ('Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 'USD', 1, 2);
INSERT INTO product (product_name, product_description, product_defaultprice, product_defaultcurrency, product_productcategory, product_supplier)
VALUES ('Amazon Fire HD 8', 'Amazon s latest Fire HD 8 tablet is a great value for media consumption.', 89, 'USD', 1, 2);
INSERT INTO product (product_name, product_description, product_defaultprice, product_defaultcurrency, product_productcategory, product_supplier)
VALUES ('Lenovo Ideapad', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand', 200, 'USD', 2, 2);
INSERT INTO product (product_name, product_description, product_defaultprice, product_defaultcurrency, product_productcategory, product_supplier)
VALUES ('Hp Probook', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand', 220, 'USD', 2, 2);
INSERT INTO product (product_name, product_description, product_defaultprice, product_defaultcurrency, product_productcategory, product_supplier)
VALUES ('Aspire R 14', 'Cutting-edge graphics and top performance for the ultimate entertainment experience.', 800, 'USD', 2, 3);
INSERT INTO product (product_name, product_description, product_defaultprice, product_defaultcurrency, product_productcategory, product_supplier)
VALUES ('Canon 5D Mark IV', 'The EOS 5D Mark IV camera builds on the powerful legacy of the 5D series, offering amazing refinements in image quality, performance and versatility.', 500, 'USD', 3, 4);