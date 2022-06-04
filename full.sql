BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost double precision);
INSERT INTO products (title, cost) VALUES
('Milk', 10.0),
('Tomato', 9),
('Bread', 3),
('Cheese', 12),
('Water', 2);

COMMIT;