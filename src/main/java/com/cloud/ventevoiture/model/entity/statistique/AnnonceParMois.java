package com.cloud.ventevoiture.model.entity.statistique;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="annonces_cinq_derniers_mois")
public class AnnonceParMois {
    @Id
    int mois;

    int year;

    int nombreDannonces;

    public String getMoisString() {
        String[] nomsMois = {"", "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};

        if (mois >= 1 && this.getMois() <= 12) {
            return nomsMois[this.getMois()];
        } else {
            return "Mois invalide";
        }
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getNombreDannonces() {
        return nombreDannonces;
    }

    public void setNombreDannonces(int nombreDannonces) {
        this.nombreDannonces = nombreDannonces;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
