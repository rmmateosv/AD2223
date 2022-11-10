drop database if exists spotiflyP;
create database spotiflyP;
use spotiflyP;

create table usuario(
	usuario varchar(10) primary key,
    ps blob,
    tipo enum('A','U')
)engine innodb;

insert into usuario values ('admin',sha2('admin',512),'A');

create table artista(
	id int primary key auto_increment,
    nombre varchar(50) unique not null,
    genero enum('POP', 'ROCK','OTROS') not null ,
    fechaL date not null, -- Fecha de lanzamiento
    seguir boolean default true not null,
    usuario varchar(10) not null,
    foreign key (usuario) references usuario(usuario)
		on update cascade on delete cascade
)engine innodb;

create table album(
	id int primary key auto_increment,
    titulo varchar(100) not null,
    artista int  not null,
    anio int not null,
    unique(titulo, artista),
    foreign key (artista) references artista(id)
		on update cascade on delete restrict
)engine innodb;

create table cancion(
	titulo varchar(100) not null,
    album int not null,
    valoracion float,
    primary key(titulo, album),
    foreign key (album) references album(id)
		on update cascade on delete restrict
)engine innodb;