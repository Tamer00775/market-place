INSERT INTO product (product_name, category_id, price, description)
SELECT 'Товар 1', id, 400, 'Описание товара 1' from category where category_code like 'FOOD';

INSERT INTO product (product_name, category_id, price, description)
SELECT 'Товар 2', id, 400, 'Описание товара 2' from category where category_code like 'FOOD';

INSERT INTO product (product_name, category_id, price, description)
SELECT 'Товар 3', id, 400, 'Описание товара 3' from category where category_code like 'ELECTRONICS';

INSERT INTO product (product_name, category_id, price, description)
SELECT 'Товар 4', id, 400, 'Описание товара 4' from category where category_code like 'ELECTRONICS';

INSERT INTO product (product_name, category_id, price, description)
SELECT 'Товар 5', 4, 5, 'Описание товара 5'from category where category_code like 'CLOTHING';

INSERT INTO product (product_name, category_id, price, description)
SELECT 'Товар 6', id, 400, 'Описание товара 6' from category where category_code like 'CLOTHING';

INSERT INTO product (product_name, category_id, price, description)
SELECT 'Товар 7', id, 400, 'Описание товара 7' from category where category_code like 'BOOKS_AND_MAGAZINES';

INSERT INTO product (product_name, category_id, price, description)
SELECT 'Товар 8', id, 400, 'Описание товара 8' from category where category_code like 'CLOTHING';

INSERT INTO product (product_name, category_id, price, description)
SELECT 'Товар 9', id, 400, 'Описание товара 9' from category where category_code like 'FURNITURE';

INSERT INTO product (product_name, category_id, price, description)
SELECT 'Товар 10', id, 400, 'Описание товара 10' from category where category_code like 'ELECTRONICS';
