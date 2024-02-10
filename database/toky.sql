create view annonces_cinq_derniers_mois(year, mois, nombre_dannonces) as
SELECT date_part('year'::text, date_announces)  AS year,
       date_part('month'::text, date_announces) AS mois,
       count(*)                                 AS nombre_dannonces
FROM announces
WHERE date_announces <= ((SELECT max(announces_1.date_announces) AS max
                          FROM announces announces_1))
  AND date_announces >= (((SELECT max(announces_1.date_announces) AS max
                           FROM announces announces_1)) - '5 mons'::interval)
GROUP BY (date_part('month'::text, date_announces)), (date_part('year'::text, date_announces));

alter table annonces_cinq_derniers_mois
    owner to postgres;
