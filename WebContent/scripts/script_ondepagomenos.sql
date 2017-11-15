create database ondepagomenos;
use ondepagomenos;

create table usuario(
	id_usuario int(11) auto_increment,
	nome varchar(50) not null,
	email varchar(50) not null,
	login varchar(20) not null unique,
	senha varchar(32) not null unique,
	fl_excluido int(1) not null default 0,
    perfil int(1) not null,
    primary key (id_usuario)
);


create table estado(
	id_estado int(11) auto_increment,
    uf varchar(2) not null,
    primary key (id_estado)
);

create table regiao(
	id_regiao int(11) auto_increment,
    nome varchar(20) not null,
	id_estado_regiao int(11) not null,
    primary key (id_regiao),
    foreign key (id_estado_regiao) references estado (id_estado)
);

create table supermercado(
	id_supermercado int(11) auto_increment,
    nome varchar(50) not null,
    site varchar(50),
	fl_excluido int(1) not null default 0,
    id_regiao_super int(11) not null,
    id_estado_super int(11) not null,
	primary key(id_supermercado),
	foreign key (id_regiao_super) references regiao (id_regiao),
	foreign key (id_estado_super) references estado (id_estado)
);


create table produto_categoria(
	id_categoria int(11) auto_increment,
    nome varchar(25) not null,
    primary key (id_categoria)
);

create table produto(
	id_produto int(11) auto_increment,
    nome varchar(50) not null,
    marca varchar(50) not null,
    preco decimal(6,2) not null,
    data_inicio_vigencia date not null,
    data_fim_vigencia date not null,
    id_categoria_prod int(11) not null,
	id_super_prod int(11) not null,
    fl_excluido int(1) not null default 0,
    primary key (id_produto),
    foreign key (id_categoria_prod) references produto_categoria (id_categoria),
	foreign key (id_super_prod) references supermercado (id_supermercado)
);


CREATE INDEX login_idx ON usuario(login);

CREATE INDEX senha_idx ON usuario(senha);

CREATE INDEX nome ON produto(nome);

INSERT INTO estado VALUES(1, 'AC');
INSERT INTO estado VALUES(2, 'AL');
INSERT INTO estado VALUES(3, 'AM');
INSERT INTO estado VALUES(4, 'AP');
INSERT INTO estado VALUES(5, 'BA');
INSERT INTO estado VALUES(6, 'CE');
INSERT INTO estado VALUES(7, 'DF');
INSERT INTO estado VALUES(8, 'ES');
INSERT INTO estado VALUES(9, 'GO');
INSERT INTO estado VALUES(10, 'MA');
INSERT INTO estado VALUES(11, 'MG');
INSERT INTO estado VALUES(12, 'MS');
INSERT INTO estado VALUES(13, 'MT');
INSERT INTO estado VALUES(14, 'PA');
INSERT INTO estado VALUES(15, 'PB');
INSERT INTO estado VALUES(16, 'PE');
INSERT INTO estado VALUES(17, 'PI');
INSERT INTO estado VALUES(18, 'PR');
INSERT INTO estado VALUES(19, 'RJ');
INSERT INTO estado VALUES(20, 'RN');
INSERT INTO estado VALUES(21, 'RO');
INSERT INTO estado VALUES(22, 'RR');
INSERT INTO estado VALUES(23, 'RS');
INSERT INTO estado VALUES(24, 'SC');
INSERT INTO estado VALUES(25, 'SE');
INSERT INTO estado VALUES(26, 'SP');
INSERT INTO estado VALUES(27, 'TO');

insert into regiao values(1, 'Grande Florianópolis',24),
(2, 'Litoral Norte',24),
(3, 'Litoral Sul',24),
(4, 'Nordeste',24),
(5, 'Vale do Itajaí',24),
(6, 'Planalto Norte',24),
(7, 'Planalto Serrano',24),
(8, 'Sul',24),
(9, 'Vale do Acre',1),
(10, 'Vale do Juruá',1);

insert into usuario values(1,'Equipe', 'ads@gmail.com', 'teste', '698dc19d489c4e4db73e28a713eab07b',0);



