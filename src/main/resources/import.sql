insert into tab_cozinhas (id,nom_cozinha)  values (1,'Tailandesa');
insert into tab_cozinhas (id,nom_cozinha)  values (2,'Japonesa');

insert into restaurante (nome,taxa_frete,cozinha_id) values ('Thai Gourmet', 10,1);
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Thai Delivery', 9.50 ,1);
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Tuk Tuk Comida Indiana', 15,2);
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Austalia food', 0,2);

insert into estado (id, nome) values (1, 'Sao Paulo');
insert into estado (id , nome) values (2,'Tocantins');

insert into cidade(nome, estado_id) values('Osasco', 1);