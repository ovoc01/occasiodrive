--category

insert into category values (default,'SUV');
insert into category values (default,'Berlin');
insert into category values (default,'Off Road');
insert into category values (default,'4x4');
insert into category values (default,'4x2');
insert into category values (default,'Camionette');
insert into category values (default,'Coupé');
insert into category values (default,'Sport');

--fuel type

insert into fuel_type values (default,'Essence');
insert into fuel_type values (default,'Diesel');
insert into fuel_type values (default,'Hydrogène');
insert into fuel_type values (default,'Electrique');

--brand

insert into brand values (default,'Toyota');
insert into brand values (default,'Mercedes');
insert into brand values (default,'Mazda');
insert into brand values (default,'Peugeot');
insert into brand values (default,'Citroen');

--transmission

insert into transmission values (default,'Manuel');
insert into transmission values (default,'Automatique');
insert into transmission values (default,'Manuel à double embrayage');

-- insert model

insert into model values (default,1,7,'Yaris');
insert into model values (default,1,8,'Yaris touring');
insert into model values (default,3,7,'CX-5');
insert into model values (default,3,7,'CX-60');
insert into model values (default,3,7,'CX-80');
insert into model values (default,5,2,'Xantia');


--insert assoc_model_engine_power
insert into assoc_model_engine_power values (default,1,130);
insert into assoc_model_engine_power values (default,1,125);
insert into assoc_model_engine_power values (default,6,75);
insert into assoc_model_engine_power values (default,2,150);
insert into assoc_model_engine_power values (default,3,90);
insert into assoc_model_engine_power values (default,4,99);
insert into assoc_model_engine_power values (default,5,130);

--insert into assoc_model_transmission
insert into assoc_model_transmission values (default,1,1);
insert into assoc_model_transmission values (default,2,2);
insert into assoc_model_transmission values (default,1,3);
insert into assoc_model_transmission values (default,2,4);
insert into assoc_model_transmission values (default,2,5);
insert into assoc_model_transmission values (default,1,6);

--insert into assoc_model_fuel_type

insert into assoc_model_fuel_type values (default,1,1);
insert into assoc_model_fuel_type values (default,1,2);
insert into assoc_model_fuel_type values (default,2,3);
insert into assoc_model_fuel_type values (default,1,4);
insert into assoc_model_fuel_type values (default,1,5);
insert into assoc_model_fuel_type values (default,1,6);
insert into assoc_model_fuel_type values (default,2,6);

