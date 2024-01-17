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


--insert car
insert into car values (default,1,1,1,130);

--insert into car_announces

insert into car_announces values (default,1,'2880 TAB',2004,90000);

--insert into announces
insert into announces values (default,'This is a description',now(),0,1,4000,null,1);

