CREATE TABLE tb_kitchens(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
)ENGINE = InnoDB;

INSERT INTO tb_kitchens(id,name) VALUES (1,'Brasileira');
INSERT INTO tb_kitchens(id,name) VALUES (2,'Japonesa');
INSERT INTO tb_kitchens(id,name) VALUES (3,'Italiana');
