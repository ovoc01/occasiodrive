package com.cloud.ventevoiture.model.services;

import com.cloud.ventevoiture.controller.request.AnnouncesRequest;
import com.cloud.ventevoiture.model.entity.announces.Announce;
import com.cloud.ventevoiture.model.entity.announces.AnnouncesLog;
import com.cloud.ventevoiture.model.entity.announces.AnnouncesPicture;
import com.cloud.ventevoiture.model.entity.announces.Car;
import com.cloud.ventevoiture.model.entity.brand.Brand;
import com.cloud.ventevoiture.model.entity.car.FuelType;
import com.cloud.ventevoiture.model.entity.car.Motorisation;
import com.cloud.ventevoiture.model.entity.car.Transmission;
import com.cloud.ventevoiture.model.entity.car.version.Version;
import com.cloud.ventevoiture.model.entity.category.Category;
import com.cloud.ventevoiture.model.entity.model.Model;
import com.cloud.ventevoiture.model.repository.*;
import com.cloud.ventevoiture.model.entity.user.Person;
import com.cloud.ventevoiture.model.entity.user.User;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncesServices {

    private final AnnouncesRepository announcesRepository;
    private final PersonRepository personRepository;
    private final AnnouncesLogRepository announceLogRepository;
    private final CarRepository carRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final TransmissionRepository transmissionRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final AnnouncesPictureRepository announcesPictureRepository;

    @Transactional
    public void persist(AnnouncesRequest request, User user) {
        // Car car =
        // carRepository.insertCar(request.getIdModel(),request.getIdTransmission(),request.getIdFuelType(),request.getEnginePower(),request.getRegistration(),request.getManufacturingYear(),request.getMileAge());
        Person person = personRepository.findPersonByIdPerson(user.getIdPersonUser()).orElseThrow();

        Car car = Car.builder()
                .brand(Brand.builder().id_brand(request.getIdBrand()).build())
                .model(Model.builder().id_model(request.getIdModel()).build())
                .category(Category.builder().id_category(request.getIdCategory()).build())
                .motorisation(Motorisation.builder().idMotorisation(request.getIdMotorisation()).build())
                .transmission(Transmission.builder().id_transmission(request.getIdTransmission()).build())
                .fuelType(FuelType.builder().id_fuel_type(request.getIdFuelType()).build())
                .registration(request.getRegistration())
                .version(Version.builder().idVersion(request.getIdVersion()).build())
                .mileAge(request.getMileAge())
                .build();

        Announce announce = Announce.builder()
                .description(request.getDescription())
                .sellingPrice(request.getSellingPrice())
                .status(0)
                .dateAnnounces(Instant.now())
                .validationDate(null)
                .person(person)
                .car(car)
                .build();

        System.out.println(announce);
        announcesRepository.save(announce);
        carRepository.save(car);

        for (String pic:request.getPhotos()){
            AnnouncesPicture picture = AnnouncesPicture
                    .builder()
                    .announce(announce)
                    .imageByte(pic.getBytes())
                    .build();
            announcesPictureRepository.save(picture);
        }

    }

    @Transactional
    public void valider(@NonNull Integer idAnnounces, User user) {
        // TODO
        Announce announce = announcesRepository.findById(idAnnounces).orElseThrow();
        announce.setStatus(10);
        announce.setValidationDate(LocalDate.now());
        announcesRepository.save(announce);

        AnnouncesLog log = AnnouncesLog.builder()
                .idAnnounce(announce.getId())
                .date(LocalDate.now())
                .status(10)
                .idPerson(user.getPerson().getIdPerson())
                .build();
        announceLogRepository.save(log);
    }

    public List<Announce> findByUser(User user) {
        Person person = personRepository.findPersonByIdPerson(user.getIdPersonUser()).orElseThrow();
        return this.announcesRepository.findByIdPerson(person.getIdPerson());
    }

    public void deleteAnnouncesById(User user, @NonNull Integer idAnnounce) {
        Person person = personRepository.findPersonByIdPerson(user.getIdPersonUser()).orElseThrow();
        Announce announce = announcesRepository.findById(idAnnounce).orElseThrow();

        final float previous_announces_state = announce.getStatus();
        final LocalDate previousDate = announce.getValidationDate();

        announce.setStatus(-10);
        announce.setValidationDate(LocalDate.now());
        announce.setPerson(person);
        announcesRepository.save(announce);

        AnnouncesLog announcesLog = announceLogRepository.findByIdAnnounce(idAnnounce).orElseThrow();
        announcesLog.setStatus(Integer.parseInt(String.valueOf(previous_announces_state)));
        announcesLog.setDate(previousDate);
        announceLogRepository.save(announcesLog);
    }

    public void sellingAnnounce(User user, @NonNull Integer idAnnounce) {
        Person person = personRepository.findPersonByIdPerson(user.getIdPersonUser()).orElseThrow();
        
        Announce announce = announcesRepository.findById(idAnnounce).orElseThrow();

        final float previous_announces_state = announce.getStatus();
        final LocalDate previousDate = announce.getValidationDate();

        if(previous_announces_state< 0) throw new IllegalStateException("L'annonce a déjà été supprimée");
        if (previous_announces_state < 10)
            throw new IllegalStateException("L'annonce n'est pas encore validée");
        announce.setStatus(20);
        announcesRepository.save(announce);

        AnnouncesLog announcesLog = announceLogRepository.findByIdAnnounce(idAnnounce).orElseThrow();
        announcesLog.setStatus(Integer.parseInt(String.valueOf(previous_announces_state)));
        announcesLog.setDate(previousDate);
        announceLogRepository.save(announcesLog);

    }

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


    public void searchByModel(Integer idModel, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (idModel != null) {
            Model model = modelRepository.findById(idModel).orElseThrow();
            predicates.add(builder.equal(root.get("car").get("model").get("model"), model.getModel()));
        }
    }

    public void searchByCategory(Integer idCategory, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (idCategory != null) {
            Category category = categoryRepository.findById(idCategory).orElseThrow();
            predicates.add(builder.equal(root.get("car").get("category").get("category"), category.getCategory()));
        }
    }

    public void searchByBrand(Integer idBrand, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (idBrand != null) {
            Brand brand = brandRepository.findById(idBrand).orElseThrow();
            predicates.add(builder.equal(root.get("car").get("model").get("brand").get("brand"), brand.getBrand()));
        }
    }

    public void searchByTransmission(Integer idTransmission, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (idTransmission != null) {
            Transmission transmission = transmissionRepository.findById(idTransmission).orElseThrow();
            predicates.add(builder.equal(root.get("car").get("transmission").get("name"), transmission.getName()));
        }
    }

    public void searchByFuelType(Integer idFuelType, List<Predicate> predicates, CriteriaBuilder builder, Root<Announce> root){
        if (idFuelType != null) {
            FuelType fuelType = fuelTypeRepository.findById(idFuelType).orElseThrow();
            predicates.add(builder.equal(root.get("car").get("fuelType").get("label"), fuelType.getLabel()));
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
}
