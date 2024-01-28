--view pour recuperer tout les puissance d'un modèle


create or replace view v_motorisation_transmission as
    select mt.id_motorisation_transmission,mt.id_motorisation,t.*
        from motorisation_transmission mt
        join public.transmission t on mt.id_transmission = t.id_transmission

create or replace view v_motorisation_version as
    select mv.id_motorisation_version,mv.id_motorisation,v.id_version,v.intitule,mv.details
        from
            motorisation_version mv
            join public.version v on mv.id_version = v.id_version


create or replace view v_motorisation_fuel_type as
    select mft.id_motorisation_fuel_type,mft.id_motorisation,ft.*
        from
            motorisation_fuel_type mft
        join public.fuel;