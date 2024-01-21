package com.cloud.ventevoiture.model.entity.announces;

import com.cloud.ventevoiture.model.entity.user.Person;
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
    @JoinColumn(name = "id_car_announce")
    private CarAnnounce idCarAnnounce;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name = "validation_date")
    private LocalDate validationDate;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person idPerson;

    public void searchByKeyword(String keyword, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (keyword != null) {
            keyword = keyword.toLowerCase();
            predicates.add(builder.like(builder.lower(root.get("description")), "%" + keyword + "%"));
        }
    }

    public void searchByDateAnnounce(Instant minDateAnnounce, Instant maxDateAnnounce, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root) {
        if (minDateAnnounce != null && maxDateAnnounce != null) {
            predicates.add(builder.between(root.get("dateAnnounces"), minDateAnnounce, maxDateAnnounce));
        } else if (minDateAnnounce != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dateAnnounces"), minDateAnnounce));
        } else if (maxDateAnnounce != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dateAnnounces"), maxDateAnnounce));
        }
    }

    public void searchByModel(String model, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (model != null) {
            predicates.add(builder.equal(root.get("idCarAnnounce").get("idCar").get("idModel").get("model"), model));
        }
    }
    public void searchByCategory(String category, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (category != null) {
            predicates.add(builder.equal(root.get("idCarAnnounce").get("idCar").get("idModel").get("category").get("category"), category));
        }
    }
    public void searchByBrand(String brand, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (brand != null) {
            predicates.add(builder.equal(root.get("idCarAnnounce").get("idCar").get("idModel").get("brand").get("brand"), brand));
        }
    }
    public void searchByTransmission(String transmission, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (transmission != null) {
            predicates.add(builder.equal(root.get("idCarAnnounce").get("idCar").get("idTransmission").get("name"), transmission));
        }
    }
    public void searchByFuelType(String fuelType, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (fuelType != null) {
        predicates.add(builder.equal(root.get("idCarAnnounce").get("idCar").get("idFuelType").get("label"), fuelType));
        }
    }

    public void searchByEnginePower(Double minEnginePower, Double maxEnginePower, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root) {
        if (minEnginePower != null && maxEnginePower != null) {
            predicates.add(builder.between(root.get("idCarAnnounce").get("idCar").get("enginePower"), minEnginePower, maxEnginePower));
        } else if (minEnginePower != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("idCarAnnounce").get("idCar").get("enginePower"), minEnginePower));
        } else if (maxEnginePower != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("idCarAnnounce").get("idCar").get("enginePower"), maxEnginePower));
        }
    }

    public void searchByManufacturingYear(Integer minManufacturingYear, Integer maxManufacturingYear, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root) {
        if (minManufacturingYear != null && maxManufacturingYear != null) {
            predicates.add(builder.between(root.get("idCarAnnounce").get("manufacturingYear"), minManufacturingYear, maxManufacturingYear));
        } else if (minManufacturingYear != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("idCarAnnounce").get("manufacturingYear"), minManufacturingYear));
        } else if (maxManufacturingYear != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("idCarAnnounce").get("manufacturingYear"), maxManufacturingYear));
        }
    }

    public void searchByMileAge(Double minMileAge, Double maxMileAge, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root) {
        if (minMileAge != null && maxMileAge != null) {
            predicates.add(builder.between(root.get("idCarAnnounce").get("mileAge"), minMileAge, maxMileAge));
        } else if (minMileAge != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("idCarAnnounce").get("mileAge"), minMileAge));
        } else if (maxMileAge != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("idCarAnnounce").get("mileAge"), maxMileAge));
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

}
