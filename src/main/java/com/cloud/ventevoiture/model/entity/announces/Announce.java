package com.cloud.ventevoiture.model.entity.announces;

import com.cloud.ventevoiture.model.entity.user.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "announces")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Announce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_announces", nullable = false)
    private Integer id;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "date_announces")
    private Instant dateAnnounces;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_car")
    private Car car;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name = "validation_date")
    private LocalDate validationDate;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person person;

    public void searchByKeyword(String keyword, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (keyword != null) {
            keyword = keyword.toLowerCase();
            predicates.add(builder.like(builder.lower(root.get("description")), "%" + keyword + "%"));
        }
    }

    public void searchByDateAnnounce(String minDateAnnounce, String maxDateAnnounce, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root) {
        Instant minInstant = (minDateAnnounce != null) ? Instant.parse(minDateAnnounce + "T00:00:00.0Z") : null;
        Instant maxInstant = (maxDateAnnounce != null) ? Instant.parse(maxDateAnnounce + "T23:59:59.999999999Z") : null;

        if (minInstant != null && maxInstant != null) {
            predicates.add(builder.between(root.get("dateAnnounces"), minInstant, maxInstant));
        } else if (minInstant != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dateAnnounces"), minInstant));
        } else if (maxInstant != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dateAnnounces"), maxInstant));
        }
    }


    public void searchByModel(String model, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (model != null) {
            predicates.add(builder.equal(root.get("car").get("model").get("model"), model));
        }
    }

    public void searchByCategory(String category, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (category != null) {
            predicates.add(builder.equal(root.get("car").get("category").get("category"), category));
        }
    }

    public void searchByBrand(String brand, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (brand != null) {
            predicates.add(builder.equal(root.get("car").get("model").get("brand").get("brand"), brand));
        }
    }

    public void searchByTransmission(String transmission, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (transmission != null) {
            predicates.add(builder.equal(root.get("car").get("transmission").get("name"), transmission));
        }
    }

    public void searchByFuelType(String fuelType, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (fuelType != null) {
            predicates.add(builder.equal(root.get("car").get("fuelType").get("label"), fuelType));
        }
    }

    public void searchByEnginePower(Double minEnginePower, Double maxEnginePower, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root) {
        if (minEnginePower != null && maxEnginePower != null) {
            predicates.add(builder.between(root.get("car").get("motorisation").get("enginePower"), minEnginePower, maxEnginePower));
        } else if (minEnginePower != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("car").get("motorisation").get("enginePower"), minEnginePower));
        } else if (maxEnginePower != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("car").get("motorisation").get("enginePower"), maxEnginePower));
        }
    }

    public void searchByManufacturingYear(Integer minManufacturingYear, Integer maxManufacturingYear, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root) {
        if (minManufacturingYear != null && maxManufacturingYear != null) {
            predicates.add(builder.between(root.get("car").get("manufacturingYear"), minManufacturingYear, maxManufacturingYear));
        } else if (minManufacturingYear != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("car").get("manufacturingYear"), minManufacturingYear));
        } else if (maxManufacturingYear != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("car").get("manufacturingYear"), maxManufacturingYear));
        }
    }

    public void searchByMileAge(Double minMileAge, Double maxMileAge, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root) {
        if (minMileAge != null && maxMileAge != null) {
            predicates.add(builder.between(root.get("car").get("mileAge"), minMileAge, maxMileAge));
        } else if (minMileAge != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("car").get("mileAge"), minMileAge));
        } else if (maxMileAge != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("car").get("mileAge"), maxMileAge));
        }
    }

    public void searchBySellingPrice(Double minSellingPrice, Double maxSellingPrice, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root) {
        if (minSellingPrice != null && maxSellingPrice != null) {
            predicates.add(builder.between(root.get("sellingPrice"), minSellingPrice, maxSellingPrice));
        } else if (minSellingPrice != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("sellingPrice"), minSellingPrice));
        } else if (maxSellingPrice != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("sellingPrice"), maxSellingPrice));
        }
    }


    @JsonProperty("status_intitule")
    public String getStatusString(){
        if(this.status<0) return "Supprimé";
        else if(this.status==0) return "En attente";
        else if(this.status==10) return "Validé";
        else if (this.status==20) return "Vendu";
        else return "Inconnu";
    }

}
