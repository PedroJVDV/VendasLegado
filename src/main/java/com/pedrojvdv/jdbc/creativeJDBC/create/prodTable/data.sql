CREATE TABLE IF NOT EXISTS products (
    regis_prod       int AUTO_INCREMENT PRIMARY KEY,
    name_prod        VARCHAR(150) NOT NULL,
    empressProd_name VARCHAR(60)  NOT NULL,
    quantity_prod    int
)
