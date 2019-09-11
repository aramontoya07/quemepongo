
    create table Alerta (
        id integer not null auto_increment,
        ubicacion varchar(255),
        primary key (id)
    )

    create table AsistenciaEvento (
        id integer not null auto_increment,
        evento_id integer,
        primary key (id)
    )

    create table AsistenciaEvento_SugerenciasClima (
        AsistenciaEvento_id integer not null,
        sugerenciasEvento_id integer not null,
        primary key (AsistenciaEvento_id, sugerenciasEvento_id)
    )

    create table Atuendos (
        id integer not null auto_increment,
        aceptado bit not null,
        aproximado bit not null,
        rangoDeAceptacion integer,
        temperaturaDeUso double precision,
        primary key (id)
    )

    create table Calendario (
        id integer not null auto_increment,
        primary key (id)
    )

    create table Calendario_AsistenciaEvento (
        Calendario_id integer not null,
        eventos_id integer not null,
        primary key (Calendario_id, eventos_id)
    )

    create table Evento (
        id integer not null auto_increment,
        tituloEvento varchar(255),
        ubicacion varchar(255),
        primary key (id)
    )

    create table SugerenciasClima (
        id integer not null auto_increment,
        margen integer not null,
        primary key (id)
    )

    create table TipoSubscripcion (
        tipo_Suscripcion varchar(31) not null,
        id integer not null auto_increment,
        cantidadMaxima integer,
        primary key (id)
    )

    create table Usuarios (
        id integer not null auto_increment,
        mail varchar(255),
        notificado bit not null,
        calendarioEventos_id integer,
        subscripcion_id integer,
        primary key (id)
    )

    create table Usuarios_Atuendos (
        Usuarios_id integer not null,
        atuendos_id integer not null
    )

    alter table Calendario_AsistenciaEvento 
        add constraint UK_aos3rpfroaeimxfju48h8qbhg  unique (eventos_id)

    alter table Usuarios_Atuendos 
        add constraint UK_kbft7j16usgu5t16qf0xiyob  unique (atuendos_id)

    alter table AsistenciaEvento 
        add constraint FK_k9e48673ix245yb5lbakmuo4q 
        foreign key (evento_id) 
        references Evento (id)

    alter table AsistenciaEvento_SugerenciasClima 
        add constraint FK_svodfvgsuto6nh8rc5ibao77a 
        foreign key (sugerenciasEvento_id) 
        references SugerenciasClima (id)

    alter table AsistenciaEvento_SugerenciasClima 
        add constraint FK_3c2i568umu2r3tyonqclknopo 
        foreign key (AsistenciaEvento_id) 
        references AsistenciaEvento (id)

    alter table Calendario_AsistenciaEvento 
        add constraint FK_aos3rpfroaeimxfju48h8qbhg 
        foreign key (eventos_id) 
        references AsistenciaEvento (id)

    alter table Calendario_AsistenciaEvento 
        add constraint FK_nbb4ynrpjdqeldyjjhutl695f 
        foreign key (Calendario_id) 
        references Calendario (id)

    alter table Usuarios 
        add constraint FK_i1iqcewxitmhh12if4hurdta3 
        foreign key (calendarioEventos_id) 
        references Calendario (id)

    alter table Usuarios 
        add constraint FK_fqanxhf5g55g2mv6hrne1yaf3 
        foreign key (subscripcion_id) 
        references TipoSubscripcion (id)

    alter table Usuarios_Atuendos 
        add constraint FK_kbft7j16usgu5t16qf0xiyob 
        foreign key (atuendos_id) 
        references Atuendos (id)

    alter table Usuarios_Atuendos 
        add constraint FK_fmvlr17gn0y65kp46lkehi27p 
        foreign key (Usuarios_id) 
        references Usuarios (id)
