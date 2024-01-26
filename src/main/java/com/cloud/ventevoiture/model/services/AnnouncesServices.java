package com.cloud.ventevoiture.model.services;


import com.cloud.ventevoiture.controller.request.AnnouncesRequest;
import com.cloud.ventevoiture.model.entity.announces.Announce;
import com.cloud.ventevoiture.model.entity.announces.AnnouncesLog;
import com.cloud.ventevoiture.model.repository.AnnouncesLogRepository;
import com.cloud.ventevoiture.model.repository.AnnouncesRepository;

import com.cloud.ventevoiture.model.repository.PersonRepository;
import com.cloud.ventevoiture.model.entity.user.Person;
import com.cloud.ventevoiture.model.entity.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AnnouncesServices {

    private final AnnouncesRepository announcesRepository;
    private final PersonRepository personRepository;
    private final AnnouncesLogRepository announceLogRepository;

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
                .person(person)
                .build();

        System.out.println(announce);
        //announcesRepository.save(announce);
        //carRepository.save(car);

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

    
}
