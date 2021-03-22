create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, nome varchar(255) not null, taxa_frete decimal(19,2), cozinha_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
insert into tab_cozinhas (id,nom_cozinha)  values (1,'Tailandesa')
insert into tab_cozinhas (id,nom_cozinha)  values (2,'Japonesa')
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Thai Gourmet', 10,1)
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Thai Delivery', 9.50 ,1)
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Tuk Tuk Comida Indiana', 15,2)
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Austalia food', 0,2)
insert into estado (id, nome) values (1, 'Sao Paulo')
insert into estado (id , nome) values (2,'Tocantins')
insert into cidade(nome, estado_id) values('Osasco', 1)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, nome varchar(255) not null, taxa_frete decimal(19,2), cozinha_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
insert into tab_cozinhas (id,nom_cozinha)  values (1,'Tailandesa')
insert into tab_cozinhas (id,nom_cozinha)  values (2,'Japonesa')
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Thai Gourmet', 10,1)
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Thai Delivery', 9.50 ,1)
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Tuk Tuk Comida Indiana', 15,2)
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Austalia food', 0,2)
insert into estado (id, nome) values (1, 'Sao Paulo')
insert into estado (id , nome) values (2,'Tocantins')
insert into cidade(nome, estado_id) values('Osasco', 1)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, nome varchar(255) not null, taxa_frete decimal(19,2), cozinha_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
insert into tab_cozinhas (id,nom_cozinha)  values (1,'Tailandesa')
insert into tab_cozinhas (id,nom_cozinha)  values (2,'Japonesa')
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Thai Gourmet', 10,1)
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Thai Delivery', 9.50 ,1)
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Tuk Tuk Comida Indiana', 15,2)
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Austalia food', 0,2)
insert into estado (id, nome) values (1, 'Sao Paulo')
insert into estado (id , nome) values (2,'Tocantins')
insert into cidade(nome, estado_id) values('Osasco', 1)
