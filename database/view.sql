SELECT mt.id_motorisation_transmission,
       mt.id_motorisation,
       t.id_transmission,
       t.name
FROM motorisation_transmission mt
         JOIN transmission t ON mt.id_transmission = t.id_transmission


SELECT mft.id_motorisation_fuel_type,
       mft.id_motorisation,
       ft.id_fuel_type,
       ft.label
FROM motorisation_fuel_type mft
         JOIN fuel_type ft ON ft.id_fuel_type = mft.id_fuel_type


SELECT mv.id_motorisation_version,
       mv.id_motorisation,
       v.id_version,
       v.intitule,
       mv.details
FROM motorisation_version mv
         JOIN version v ON mv.id_version = v.id_version