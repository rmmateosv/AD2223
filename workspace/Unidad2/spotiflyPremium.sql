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
    nombre varchar(50)  not null,
    genero enum('POP', 'ROCK','OTROS') not null ,
    fechaL date not null, -- Fecha de lanzamiento
    seguir boolean default true not null,
    usuario varchar(10) not null,
    unique (nombre, usuario),
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
		on update cascade on delete cascade
)engine innodb;

create table cancion(
	titulo varchar(100) not null,
    album int not null,
    valoracion float,
    primary key(titulo, album),
    foreign key (album) references album(id)
		on update cascade on delete cascade
)engine innodb;

create table tablaLog(
	id int primary key auto_increment,
    usuario varchar(10) not null,
    fecha datetime not null,
    texto varchar(100),
    foreign key (usuario) references usuario(usuario)
		on update cascade on delete restrict
)engine innodb;

delimiter //
-- Devuelve el tipo del usuario si us y pas son correctos
-- si no, devuleve NE
create function login(us varchar(10), pas varchar(10)) 
	returns varchar(2) deterministic
begin
	declare vTipo varchar(1);
    
	select tipo
		into vTipo
        from usuario
        where usuario = us and ps = sha2(pas,512);
	if(vtipo is null) then
		return 'NE';
    else
		return vTipo;
    end if;
end//    
create procedure borrarUsuario(pUS varchar(10))
begin
	declare vTexto varchar(100);
    declare vNA, vNAl, vNC int;
	
    start transaction;
	-- Calcular datos a borrar
    -- Nº Artista
    select count(*)
		into vNA
		from artista where usuario = pUS;
    -- Nº Álbumes
    select count(*)
		into vNAl
		from artista ar inner join album al on ar.id = al.artista
        where ar.usuario = pUS;
	-- Nº Canciones
    select count(*)
		into vNC
		from artista ar inner join album al on ar.id = al.artista
			inner join cancion c on c.album = al.id
        where ar.usuario = pUS;
    
    delete from usuario where usuario = pUS;
	set vTexto = concat('Se ha borrado ',row_count(),' usuario ',
    vNA, ' artistas ', vNAl, ' álbumes ', vNC, ' canciones');
    insert into tablalog values (null,substr(session_user(),1,10),
    now(),vTexto);
    commit;
    select vTexto;
    
end//
call borrarUsuario('rosa')//