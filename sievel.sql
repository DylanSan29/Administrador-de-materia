Create database sievel;
SET GLOBAL time_zone = '-5:00';
create table sievel.materia(
id_materia int primary key auto_increment not null, 
nombre varchar(40) not null,
codigo varchar(20) not null,
creditos int not null
);