
    create table AdaptacionPuntuada (
        id integer not null auto_increment,
        nivelDeAdaptacion double precision,
        puntaje integer,
        primary key (id)
    )

    create table Alerta (
        id integer not null auto_increment,
        ubicacion varchar(255),
        primary key (id)
    )

    create table AsistenciaEvento (
        id integer not null auto_increment,
        evento_id integer,
        Id_calendario integer,
        primary key (id)
    )

    create table Atuendos (
        id integer not null auto_increment,
        calzado_id integer,
        guardarropaOrigen_id integer,
        inferior_id integer,
        superior_id integer,
        primary key (id)
    )

    create table Calendarios (
        id integer not null auto_increment,
        primary key (id)
    )

    create table ColorRGB (
        id integer not null auto_increment,
        azul integer not null,
        rojo integer not null,
        verde integer not null,
        primary key (id)
    )

    create table Evento (
        id integer not null auto_increment,
        fecha tinyblob,
        tituloEvento varchar(255),
        ubicacion varchar(255),
        primary key (id)
    )

    create table Guardarropas (
        id integer not null auto_increment,
        primary key (id)
    )

    create table Guardarropas_Prendas (
        Guardarropas_id integer not null,
        usadas_id integer not null,
        disponibles_id integer not null,
        primary key (Guardarropas_id, disponibles_id)
    )

    create table PreferenciasDeAbrigo (
        id integer not null auto_increment,
        abrigoCabeza_id integer,
        abrigoCuello_id integer,
        abrigoManos_id integer,
        abrigoPecho_id integer,
        abrigoPiernas_id integer,
        abrigoPies_id integer,
        primary key (id)
    )

    create table Prendas (
        id integer not null auto_increment,
        material varchar(255),
        pateAbrigada varchar(255),
        trama varchar(255),
        colorPrimario_id integer,
        colorSecundario_id integer,
        tipo_id integer,
        id_atuendo integer,
        primary key (id)
    )

    create table Sugerencias (
        id integer not null auto_increment,
        margen integer not null,
        Id_AsistenciaEvento integer,
        primary key (id)
    )

    create table SugerenciasPosibles (
        id integer not null auto_increment,
        tipo integer,
        atuendo_id integer,
        id_sugerencia integer,
        primary key (id)
    )

    create table TipoPrenda (
        id integer not null auto_increment,
        TipoBasico varchar(255),
        categoria varchar(255),
        nivelAbrigo integer not null,
        primary key (id)
    )

    create table TipoPrenda_TipoPrenda (
        TipoPrenda_id integer not null,
        tiposAceptados_id integer not null
    )

    create table TipoPrenda_materialesPermitidos (
        TipoPrenda_id integer not null,
        materialesPermitidos varchar(255)
    )

    create table TipoSubscripcion (
        tipo_Suscripcion varchar(31) not null,
        id integer not null auto_increment,
        cantidadMaxima integer,
        primary key (id)
    )

    create table Usos (
        id integer not null auto_increment,
        estado varchar(255),
        fechaDeUso tinyblob,
        temperaturaDeUso double precision,
        atuendo_id integer,
        primary key (id)
    )

    create table Usuarios (
        id integer not null auto_increment,
        mail varchar(255),
        primary key (id)
    )

    alter table Guardarropas_Prendas 
        add constraint UK_8xnv6ejh82gn7xlthewpe0x90  unique (usadas_id)

    alter table Guardarropas_Prendas 
        add constraint UK_o4l8g7o63mmdo1h6vbmcc03ue  unique (disponibles_id)

    alter table AsistenciaEvento 
        add constraint FK_k9e48673ix245yb5lbakmuo4q 
        foreign key (evento_id) 
        references Evento (id)

    alter table AsistenciaEvento 
        add constraint FK_4a3okiefi4rmcqdwf5oub75hn 
        foreign key (Id_calendario) 
        references Calendarios (id)

    alter table Atuendos 
        add constraint FK_9d9s0xplid83y5h6g2k6cg69v 
        foreign key (calzado_id) 
        references Prendas (id)

    alter table Atuendos 
        add constraint FK_i7lgbmiht6ihi7usablrrv2jg 
        foreign key (guardarropaOrigen_id) 
        references Guardarropas (id)

    alter table Atuendos 
        add constraint FK_lc16un2q96aedob9i9awoqnq1 
        foreign key (inferior_id) 
        references Prendas (id)

    alter table Atuendos 
        add constraint FK_mcvlw8rgykduswqrgapvxgbms 
        foreign key (superior_id) 
        references Prendas (id)

    alter table Guardarropas_Prendas 
        add constraint FK_8xnv6ejh82gn7xlthewpe0x90 
        foreign key (usadas_id) 
        references Prendas (id)

    alter table Guardarropas_Prendas 
        add constraint FK_fwyw5alow9p4o3c7euqnq39qv 
        foreign key (Guardarropas_id) 
        references Guardarropas (id)

    alter table Guardarropas_Prendas 
        add constraint FK_o4l8g7o63mmdo1h6vbmcc03ue 
        foreign key (disponibles_id) 
        references Prendas (id)

    alter table PreferenciasDeAbrigo 
        add constraint FK_g2w8lykuxy4la064ljmynsyh7 
        foreign key (abrigoCabeza_id) 
        references AdaptacionPuntuada (id)

    alter table PreferenciasDeAbrigo 
        add constraint FK_l6ujtn93i740r74j3qt3494qk 
        foreign key (abrigoCuello_id) 
        references AdaptacionPuntuada (id)

    alter table PreferenciasDeAbrigo 
        add constraint FK_247272twbo7o4mwx28hp2jgkj 
        foreign key (abrigoManos_id) 
        references AdaptacionPuntuada (id)

    alter table PreferenciasDeAbrigo 
        add constraint FK_846cohbthg2s3oseutlb5q05q 
        foreign key (abrigoPecho_id) 
        references AdaptacionPuntuada (id)

    alter table PreferenciasDeAbrigo 
        add constraint FK_qj00t0g5ye7auoh9pqmmv50ge 
        foreign key (abrigoPiernas_id) 
        references AdaptacionPuntuada (id)

    alter table PreferenciasDeAbrigo 
        add constraint FK_7db2v7h4umkf7ireucuhsmpxl 
        foreign key (abrigoPies_id) 
        references AdaptacionPuntuada (id)

    alter table Prendas 
        add constraint FK_75aovfoa4yffujh8mdws222fc 
        foreign key (colorPrimario_id) 
        references ColorRGB (id)

    alter table Prendas 
        add constraint FK_dr8a9k5uetfygn5w2vdbau5ef 
        foreign key (colorSecundario_id) 
        references ColorRGB (id)

    alter table Prendas 
        add constraint FK_hamyu3x93g8hl3n03ethpvicd 
        foreign key (tipo_id) 
        references TipoPrenda (id)

    alter table Prendas 
        add constraint FK_b95fy9l421l2ftp2vpys39ip8 
        foreign key (id_atuendo) 
        references Atuendos (id)

    alter table Sugerencias 
        add constraint FK_k5n7fglalc4ueb0n5ikgl8cc3 
        foreign key (Id_AsistenciaEvento) 
        references AsistenciaEvento (id)

    alter table SugerenciasPosibles 
        add constraint FK_5ykqprycv6t94wg8vrsh3q80r 
        foreign key (atuendo_id) 
        references Atuendos (id)

    alter table SugerenciasPosibles 
        add constraint FK_8llv5q41lwr0epls2h9ik2jdh 
        foreign key (id_sugerencia) 
        references Sugerencias (id)

    alter table TipoPrenda_TipoPrenda 
        add constraint FK_j7fiml7igm0eis4s1scu4eqtg 
        foreign key (tiposAceptados_id) 
        references TipoPrenda (id)

    alter table TipoPrenda_TipoPrenda 
        add constraint FK_go26ndxald5h074ti3iu7dn4v 
        foreign key (TipoPrenda_id) 
        references TipoPrenda (id)

    alter table TipoPrenda_materialesPermitidos 
        add constraint FK_4grxyh3joam04ripn0l44ndh6 
        foreign key (TipoPrenda_id) 
        references TipoPrenda (id)

    alter table Usos 
        add constraint FK_giuidbqu4h2iylvlm228ukbi 
        foreign key (atuendo_id) 
        references Atuendos (id)
