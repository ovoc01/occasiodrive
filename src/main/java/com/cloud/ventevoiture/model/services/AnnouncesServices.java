package com.cloud.ventevoiture.model.services;


import com.cloud.ventevoiture.controller.request.AnnouncesRequest;
import com.cloud.ventevoiture.model.entity.announces.Announce;
import com.cloud.ventevoiture.model.entity.announces.AnnouncesLog;
import com.cloud.ventevoiture.model.entity.announces.Car;
import com.cloud.ventevoiture.model.entity.brand.Brand;
import com.cloud.ventevoiture.model.entity.car.FuelType;
import com.cloud.ventevoiture.model.entity.car.Motorisation;
import com.cloud.ventevoiture.model.entity.car.Transmission;
import com.cloud.ventevoiture.model.entity.car.version.Version;
import com.cloud.ventevoiture.model.entity.category.Category;
import com.cloud.ventevoiture.model.entity.model.Model;
import com.cloud.ventevoiture.model.repository.AnnouncesLogRepository;
import com.cloud.ventevoiture.model.repository.AnnouncesRepository;
import com.cloud.ventevoiture.model.repository.CarRepository;
import com.cloud.ventevoiture.model.repository.PersonRepository;
import com.cloud.ventevoiture.model.entity.user.Person;
import com.cloud.ventevoiture.model.entity.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    @Transactional
    public void persist(AnnouncesRequest request, User user){
        //Car car = carRepository.insertCar(request.getIdModel(),request.getIdTransmission(),request.getIdFuelType(),request.getEnginePower(),request.getRegistration(),request.getManufacturingYear(),request.getMileAge());
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

    }
    @Transactional
    public void valider(Integer idAnnounces,User user){
        //TODO
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



    public List<Announce> findByUser(User user){
        Person person = personRepository.findPersonByIdPerson(user.getIdPersonUser()).orElseThrow();
        return this.announcesRepository.findByIdPerson(person.getIdPerson());

    }

    
}
