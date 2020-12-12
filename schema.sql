
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

    create table Atuendos_Prendas (
        Atuendos_id integer not null,
        capasAbrigos_id integer not null,
        accesorios_id integer not null
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
        fechaFormateada varchar(255),
        tituloEvento varchar(255),
        ubicacion varchar(255),
        primary key (id)
    )

    create table Guardarropas (
        id integer not null auto_increment,
        primary key (id)
    )

    create table PreferenciasDeAbrigo (
        id integer not null auto_increment,
        NIVEL_CABEZA double precision,
        PUNTAJE_CABEZA integer,
        NIVEL_CUELLO double precision,
        PUNTAJE_CUELLO integer,
        NIVEL_MANOS double precision,
        PUNTAJE_MANOS integer,
        NIVEL_PECHO double precision,
        PUNTAJE_PECHO integer,
        NIVEL_PIERNAS double precision,
        PUNTAJE_PIERNAS integer,
        NIVEL_PIES double precision,
        PUNTAJE_PIES integer,
        primary key (id)
    )

    create table Prendas (
        id integer not null auto_increment,
        material varchar(255),
        pateAbrigada varchar(255),
        rutaImagen longtext,
        trama varchar(255),
        colorPrimario_id integer,
        colorSecundario_id integer,
        tipo_id integer,
        id_Guardarropa_usadas integer,
        id_Guardarropa_disponibles integer,
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
        nombreSuscripcion varchar(255),
        cantidadMaxima integer,
        primary key (id)
    )

    create table Usos (
        id integer not null auto_increment,
        estado varchar(255),
        fechaDeUso tinyblob,
        fechaFormateada varchar(255),
        puntuado bit not null,
        temperaturaDeUso double precision,
        atuendo_id integer,
        Id_usuario integer,
        primary key (id)
    )

    create table Usuario_informantes (
        Usuario_id integer not null,
        informantes varchar(255)
    )

    create table Usuarios (
        id integer not null auto_increment,
        contrasenia varchar(255),
        mail varchar(255),
        nombre varchar(255),
        rutaFotoPerfil varchar(255),
        calendarioEventos_id integer,
        preferenciasDeAbrigo_id integer,
        subscripcion_id integer,
        primary key (id)
    )

    create table Usuarios_Guardarropas (
        Usuarios_id integer not null,
        guardarropas_id integer not null,
        primary key (Usuarios_id, guardarropas_id)
    )

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

    alter table Atuendos_Prendas 
        add constraint FK_cj8fhhw9uc88s378g6fmwnegu 
        foreign key (capasAbrigos_id) 
        references Prendas (id)

    alter table Atuendos_Prendas 
        add constraint FK_6up2sj23nli5nhsltsbh6fl3r 
        foreign key (Atuendos_id) 
        references Atuendos (id)

    alter table Atuendos_Prendas 
        add constraint FK_ig421k1byymkpuxhmgmx15ns9 
        foreign key (accesorios_id) 
        references Prendas (id)

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
        add constraint FK_amwly5b0aee317a06day2kk49 
        foreign key (id_Guardarropa_usadas) 
        references Guardarropas (id)

    alter table Prendas 
        add constraint FK_m6acnib4un92cfyuvxumk5k2s 
        foreign key (id_Guardarropa_disponibles) 
        references Guardarropas (id)

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

    alter table Usos 
        add constraint FK_oa92fer00tc33jkjqtyjpjadp 
        foreign key (Id_usuario) 
        references Usuarios (id)

    alter table Usuario_informantes 
        add constraint FK_ot339dqw70hfsg03bnw90gvd5 
        foreign key (Usuario_id) 
        references Usuarios (id)

    alter table Usuarios 
        add constraint FK_i1iqcewxitmhh12if4hurdta3 
        foreign key (calendarioEventos_id) 
        references Calendarios (id)

    alter table Usuarios 
        add constraint FK_rnsb1um116dbb3rit4w4x5e71 
        foreign key (preferenciasDeAbrigo_id) 
        references PreferenciasDeAbrigo (id)

    alter table Usuarios 
        add constraint FK_fqanxhf5g55g2mv6hrne1yaf3 
        foreign key (subscripcion_id) 
        references TipoSubscripcion (id)

    alter table Usuarios_Guardarropas 
        add constraint FK_tkbrfu90vy45ol3n4a94hwdhs 
        foreign key (guardarropas_id) 
        references Guardarropas (id)

    alter table Usuarios_Guardarropas 
        add constraint FK_6bu50dfm1dqaos9laj773l629 
        foreign key (Usuarios_id) 
        references Usuarios (id)
