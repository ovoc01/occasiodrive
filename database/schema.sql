CREATE TABLE "person" (
                          "id_person" SERIAL PRIMARY KEY,
                          "last_name" VARCHAR(50),
                          "first_name" VARCHAR(50),
                          "birth_date" DATE,
                          "gender" INT NOT NULL
);

CREATE TABLE "person_user" (
                               "id_person_user" SERIAL PRIMARY KEY,
                               "id_person" INT,
                               "email" VARCHAR(50) UNIQUE,
                               "password" varchar(100),
                               "role" INTEGER
);

CREATE TABLE "category" (
                            "id_category" SERIAL PRIMARY KEY,
                            "category" VARCHAR(50)
);

CREATE TABLE "brand" (
                         "id_brand" SERIAL PRIMARY KEY,
                         "brand" VARCHAR(50)
);

CREATE TABLE "model" (
                         "id_model" SERIAL PRIMARY KEY,
                         "id_brand" INT,
                         "id_category" INT,
                         "model" VARCHAR(50)
);

CREATE TABLE "fuel_type" (
                             "id_fuel_type" SERIAL PRIMARY KEY,
                             "label" VARCHAR(50)
);

CREATE TABLE "car" (
                       "id_car" SERIAL PRIMARY KEY,
                       "id_model" INT,
                        "id_transmission" INT ,
                       "id_fuel_type" INT ,
                       "engine_power" double precision

);

CREATE TABLE "announces" (
                             "id_announces" SERIAL PRIMARY KEY,
                             "description" varchar,
                             "date_announces" TIMESTAMP,
                             "status" INT DEFAULT 0,
                             "id_car_announce" INT,
                             "selling_price" DOUBLE PRECISION,
                             "validation_date" DATE,
                             "id_person" INT
);

CREATE TABLE "transmission" (
                                "id_transmission" SERIAL PRIMARY KEY,
                                "name" varchar(50)
);


CREATE TABLE "car_announces" (
                       "id_car_announces" SERIAL PRIMARY KEY,
                        "id_car" INT,
                       "registration" VARCHAR(50) UNIQUE,
                       "manufacturing_year" INT,
                       "mile_age" DOUBLE PRECISION

);

create table announces_log(
    id_announces_log serial primary key ,
    id_announce int references announces(id_announces),
    status int not null ,
    id_person int references person(id_person),
    date date default now()
);


create table model_category(
    id_model_category serial primary key ,
    id_model int references model(id_model),
    id_category int references category(id_category)
);




create table model_category_motorisation(
    id_model_category_motorisation serial primary key ,
    id_model_category int references model_category(id_model_category),
    engine_power double precision,
    description varchar(50)
);



ALTER TABLE "model" ADD FOREIGN KEY ("id_category") REFERENCES "category" ("id_category");

ALTER TABLE "person_user" ADD FOREIGN KEY ("id_person") REFERENCES "person" ("id_person");

ALTER TABLE "model" ADD FOREIGN KEY ("id_brand") REFERENCES "brand" ("id_brand");

ALTER TABLE "car" ADD FOREIGN KEY ("id_model") REFERENCES "model" ("id_model");
ALTER TABLE "car" ADD FOREIGN KEY ("id_transmission") REFERENCES "transmission" ("id_transmission");
ALTER TABLE "car" ADD FOREIGN KEY ("id_fuel_type") REFERENCES "fuel_type" ("id_fuel_type");

alter table "car_announces" add foreign key ("id_car") references "car" ("id_car");

ALTER TABLE "announces" ADD FOREIGN KEY ("id_car_announce") REFERENCES "car_announces" ("id_car_announces");

ALTER TABLE "announces" ADD FOREIGN KEY ("id_person") REFERENCES "person" ("id_person");
