package com.cloud.ventevoiture.model.services;


import com.cloud.ventevoiture.controller.request.AnnouncesRequest;
import com.cloud.ventevoiture.model.announces.Announce;
import com.cloud.ventevoiture.model.repository.AnnouncesRepository;
import com.cloud.ventevoiture.model.repository.CarRepository;
import com.cloud.ventevoiture.model.repository.PersonRepository;
import com.cloud.ventevoiture.model.user.Person;
import com.cloud.ventevoiture.model.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AnnouncesServices {

    private final AnnouncesRepository announcesRepository;
    private final CarRepository carRepository;
    private final PersonRepository personRepository;


    @Transactional
    public void persist(AnnouncesRequest request, User user){
        //Car car = carRepository.insertCar(request.getIdModel(),request.getIdTransmission(),request.getIdFuelType(),request.getEnginePower(),request.getRegistration(),request.getManufacturingYear(),request.getMileAge());
        Person person = personRepository.findPersonByIdPerson(user.getIdPersonUser()).orElseThrow();
        Announce announce = new Announce()
                .builder()
                .description(request.getDescription())
                .sellingPrice(request.getSellingPrice())
                .status(0)
                .dateAnnounces(Instant.now())
                .validationDate(null)

                .idPerson(person)
                .build();

        System.out.println(announce);
        //announcesRepository.save(announce);
        //carRepository.save(car);

    }
}
