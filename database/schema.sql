create table brand (
    id_brand serial PRIMARY key, brand varchar(50) not null
);

create table category (
    id_category serial PRIMARY key, category VARCHAR(50) not null
);

CREATE table fuel_type (
    id_fuel_type serial PRIMARY key, label VARCHAR(50) not null
);

create table model (
    id_model serial PRIMARY key, id_brand integer REFERENCES brand (id_brand), model varchar(50) not null
);

create table version(
    id_version serial primary key, intitule varchar(250) not null
);

create table transmission (
    id_transmission serial PRIMARY key, name VARCHAR(250) not null
);

create table person(
    id_person serial PRIMARY key,
    first_name varchar(250),
    last_name varchar(250),
    birth_date date not null,
    gender INTEGER
);

create table person_user (
    id_person_user serial PRIMARY key, id_person INTEGER REFERENCES person (id_person), role integer default 0, email varchar(250) UNIQUE not null, password varchar(500) not null
);



CREATE table model_category (
    id_model_category serial primary key, id_model integer REFERENCES model (id_model), id_category INTEGER REFERENCES category (id_category)
);

CREATE table model_category_motorisation (
    id_model_category_motorisation serial primary key, id_model_category integer REFERENCES model_category (id_model_category), engine_power float not null, description TEXT
);

CREATE table motorisation_fuel_type (
    id_motorisation_fuel_type serial PRIMARY KEY, id_motorisation INTEGER REFERENCES model_category_motorisation (
        id_model_category_motorisation
    ), id_fuel_type INTEGER REFERENCES fuel_type (id_fuel_type)
);

create table motorisation_transmission (
    id_motorisation_transmission serial PRIMARY KEY, id_motorisation INTEGER REFERENCES model_category_motorisation (
     id_model_category_motorisation
    ),
    id_transmission INTEGER REFERENCES transmission(id_transmission)
);



create table motorisation_version(
    id_motorisation_version serial primary key,
    id_version INTEGER REFERENCES version(id_version),
    id_motorisation INTEGER REFERENCES model_category_motorisation(id_model_category_motorisation),
    details TEXT
);


create table car (
    id_car serial primary key, id_brand INTEGER REFERENCES brand (id_brand), id_model INTEGER REFERENCES model (id_model), id_category INTEGER REFERENCES category (id_category), id_motorisation INTEGER REFERENCES model_category_motorisation (id_model_category_motorisation), id_transmission INTEGER REFERENCES transmission (id_transmission), id_version INTEGER REFERENCES version(id_version), id_fuel_type INTEGER REFERENCES fuel_type (id_fuel_type), registration varchar(50)not null, mile_age float not NULL
);

create table announces (
    id_announces serial PRIMARY Key, id_person INTEGER REFERENCES person (id_person), id_car INTEGER REFERENCES car (id_car), status INTEGER not NULL default 0, selling_price float not NULL, validation_date TIMESTAMP default now(), date_announces TIMESTAMP default now(), description TEXT
);

create table announces_log (
    id_announces_log serial PRIMARY key, id_announce INTEGER REFERENCES announces (id_announces), id_person INTEGER REFERENCES person (id_person), status INTEGER not null, date TIMESTAMP DEFAULT now()
);

create table announces_picture (
    id_picture serial PRIMARY KEY, id_announce INTEGER references announces (id_announces), image_byte bytea
);

CREATE table favorite_announces (
    id_favorite serial PRIMARY KEY, id_announces INTEGER REFERENCES announces (id_announces), id_person INTEGER REFERENCES person (id_person)
);