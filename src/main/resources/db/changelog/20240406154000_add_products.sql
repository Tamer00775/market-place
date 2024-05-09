-- Insert Data into Inventory
INSERT INTO inventory (quantity, description)
VALUES
    ('100', 'Initial stock of FOOD products'),
    ('150', 'Initial stock of ELECTRONICS products'),
    ('120', 'Initial stock of CLOTHING products'),
    ('80', 'Initial stock of BOOKS_AND_MAGAZINES products'),
    ('60', 'Initial stock of FURNITURE products');

-- Insert Data into Products with Inventory IDs
-- Replace the inventory IDs with those retrieved from the inventory table:
-- e.g., `SELECT id FROM inventory WHERE description = 'Initial stock of FOOD products';`
INSERT INTO product (product_name, category_id, inventory_id, price, description)
SELECT 'Товар 1', id, (SELECT id FROM inventory WHERE description = 'Initial stock of FOOD products'), 400, 'Описание товара 1' FROM category WHERE category_code = 'FOOD';

INSERT INTO product (product_name, category_id, inventory_id, price, description)
SELECT 'Товар 2', id, (SELECT id FROM inventory WHERE description = 'Initial stock of FOOD products'), 400, 'Описание товара 2' FROM category WHERE category_code = 'FOOD';

INSERT INTO product (product_name, category_id, inventory_id, price, description)
SELECT 'Товар 3', id, (SELECT id FROM inventory WHERE description = 'Initial stock of ELECTRONICS products'), 400, 'Описание товара 3' FROM category WHERE category_code = 'ELECTRONICS';

INSERT INTO product (product_name, category_id, inventory_id, price, description)
SELECT 'Товар 4', id, (SELECT id FROM inventory WHERE description = 'Initial stock of ELECTRONICS products'), 400, 'Описание товара 4' FROM category WHERE category_code = 'ELECTRONICS';

INSERT INTO product (product_name, category_id, inventory_id, price, description)
SELECT 'Товар 5', id, (SELECT id FROM inventory WHERE description = 'Initial stock of CLOTHING products'), 5, 'Описание товара 5' FROM category WHERE category_code = 'CLOTHING';

INSERT INTO product (product_name, category_id, inventory_id, price, description)
SELECT 'Товар 6', id, (SELECT id FROM inventory WHERE description = 'Initial stock of CLOTHING products'), 400, 'Описание товара 6' FROM category WHERE category_code = 'CLOTHING';

INSERT INTO product (product_name, category_id, inventory_id, price, description)
SELECT 'Товар 7', id, (SELECT id FROM inventory WHERE description = 'Initial stock of BOOKS_AND_MAGAZINES products'), 400, 'Описание товара 7' FROM category WHERE category_code = 'BOOKS_AND_MAGAZINES';

INSERT INTO product (product_name, category_id, inventory_id, price, description)
SELECT 'Товар 8', id, (SELECT id FROM inventory WHERE description = 'Initial stock of CLOTHING products'), 400, 'Описание товара 8' FROM category WHERE category_code = 'CLOTHING';

INSERT INTO product (product_name, category_id, inventory_id, price, description)
SELECT 'Товар 9', id, (SELECT id FROM inventory WHERE description = 'Initial stock of FURNITURE products'), 400, 'Описание товара 9' FROM category WHERE category_code = 'FURNITURE';

INSERT INTO product (product_name, category_id, inventory_id, price, description)
SELECT 'Товар 10', id, (SELECT id FROM inventory WHERE description = 'Initial stock of ELECTRONICS products'), 400, 'Описание товара 10' FROM category WHERE category_code = 'ELECTRONICS';