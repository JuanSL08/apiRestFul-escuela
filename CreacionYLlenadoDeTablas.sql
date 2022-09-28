--- UNA OPCIÓN ES EL GENERAR LAS TABLAS EN LA PRIMERA EJECUIÓN DEL PROYECTO SPRING BOOT
---- Pasar directamente al llenado de tablas

-- ** TABLAS DE ESCUELAS Y FACULTADES
--drop table facultad;
create table facultad(
    idFacultad number(10) not null,
    descFacultad varchar2(80),
    fechaRegistro date,
    primary key(idFacultad)
);

--drop table escuela;
create table escuela(
    idEscuela number(10) not null,
    idFacultad number(10) not null,
    nombre varchar2(80),
    cantAlumnos number(15),
    recursoFiscal number(5,2),
    licenciada varchar2(1), --REPRESENTACIÓN ED BOOLEAN: Y=true, N=false
    clasificacion number(5),
    fechaRegistro date,
    primary key(idEscuela)
);

alter table escuela
add constraint FK_escuela_idFacultad
  foreign key (idFacultad)
  references facultad(idFacultad);

create table usuarios(
    id number(19) not null,
    habilitado number(1),
    nombreUsuario varchar2(20),
    password varchar(65),
    primary key(id)
);

create table roles(
    id number(19) not null,
    nombre varchar2(20),
    primary key(id)
);

create table usuarios_roles(
    usuario_id number(19) not null,
    role_id number(19) not null,
    primary key(usuario_id, role_id)
);

alter table usuarios_roles
add constraint FK_usuarios_roles_usuario_id
  foreign key (usuario_id)
  references usuarios(id);
  
alter table usuarios_roles
add constraint FK_usuarios_roles_role_id
  foreign key (role_id)
  references roles(id);

--------------------------------------------------------------------------------

--LLENADO DE TABLAS
insert into facultad(descFacultad, fechaRegistro) values('FICSA: Facultad de Ingeniería Civil, Sistemas y Arquitectura', '21/09/2022');
insert into facultad(descFacultad, fechaRegistro) values('FACEAC: Facultad de Ciencias Económicas, Administrativas y Contables', '21/09/2022');
insert into facultad(descFacultad, fechaRegistro) values('FACHSE: Facultad de Ciencias Históricos Sociales y Educación', '21/09/2022');
insert into facultad(descFacultad, fechaRegistro) values('FACFYM: Facultad de Ciencias Físicas y Matemáticas', '21/09/2022');
insert into facultad(descFacultad, fechaRegistro) values('FACFYM: Facultad de Derecho y Ciencias Políticas', '21/09/2022');
--select * from facultad;

insert into escuela(idFacultad,nombre,cantAlumnos,recursoFiscal,licenciada,clasificacion,fechaRegistro)
                values(1, 'Escuela de Arquitectura', 750, 120.8, 'Y', 18, '22/09/2022');
insert into escuela(idFacultad,nombre,cantAlumnos,recursoFiscal,licenciada,clasificacion,fechaRegistro)
                values(1, 'Escuela de Ingeniería Civil', 507, 98.75, 'N', 52, '22/09/2022');
insert into escuela(idFacultad,nombre,cantAlumnos,recursoFiscal,licenciada,clasificacion,fechaRegistro)
                values(1, 'Escuela de Ingeniería de Sistemas', 833, 115.5, 'Y', 16, '22/09/2022');
insert into escuela(idFacultad,nombre,cantAlumnos,recursoFiscal,licenciada,clasificacion,fechaRegistro)
                values(2, 'Escuela de Administración', 961, 187.65, 'N', 29, '22/09/2022');
insert into escuela(idFacultad,nombre,cantAlumnos,recursoFiscal,licenciada,clasificacion,fechaRegistro)
                values(2, 'Escuela de Comercio y Negocios Internacionales', 1072, 297.25, 'Y', 38, '22/09/2022');
--select * from escuela;

insert into usuarios(habilitado, nombreusuario, password) values(1, 'administrador', '$2a$10$3Z3C9n9Qd1Hk.X0DqzGWpebIXR2x4WPXH77ndWtGHx4URb4gZyn.m');
insert into usuarios(habilitado, nombreusuario, password) values(1, 'supervisor', '$2a$10$SBjigcRQdZQqjmf2pMf/4On.aIs7dfKvS.k5mtH6GkfVtpC7jiTwK');
--select * from usuarios;

insert into roles(nombre) values('ROLE_ADMIN');
insert into roles(nombre) values('ROLE_USER');
--select * from roles;

insert into usuarios_roles values(1, 1);
insert into usuarios_roles values(2, 2);
--select * from usuarios_roles;
