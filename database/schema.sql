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
                       "engine_power" double precision ,
                       "registration" VARCHAR(50) UNIQUE,
                       "manufacturing_year" INT,
                       "mile_age" DOUBLE PRECISION

);

CREATE TABLE "announces" (
                             "id_announces" SERIAL PRIMARY KEY,
                             "description" varchar,
                             "date_announces" TIMESTAMP,
                             "status" INT DEFAULT 0,
                             "id_car" INT,
                             "selling_price" DOUBLE PRECISION,
                             "validation_date" DATE,
                             "id_person" INT
);

CREATE TABLE "transmission" (
                                "id_transmission" SERIAL PRIMARY KEY,
                                "name" varchar(50)
);





ALTER TABLE "model" ADD FOREIGN KEY ("id_category") REFERENCES "category" ("id_category");

ALTER TABLE "person_user" ADD FOREIGN KEY ("id_person") REFERENCES "person" ("id_person");

ALTER TABLE "model" ADD FOREIGN KEY ("id_brand") REFERENCES "brand" ("id_brand");

ALTER TABLE "car" ADD FOREIGN KEY ("id_model") REFERENCES "model" ("id_model");
ALTER TABLE "car" ADD FOREIGN KEY ("id_transmission") REFERENCES "transmission" ("id_transmission");
ALTER TABLE "car" ADD FOREIGN KEY ("id_fuel_type") REFERENCES "fuel_type" ("id_fuel_type");


ALTER TABLE "announces" ADD FOREIGN KEY ("id_car") REFERENCES "car" ("id_car");

ALTER TABLE "announces" ADD FOREIGN KEY ("id_person") REFERENCES "person" ("id_person");