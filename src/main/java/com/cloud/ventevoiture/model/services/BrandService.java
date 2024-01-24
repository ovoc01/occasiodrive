package com.cloud.ventevoiture.model.services;

import com.cloud.ventevoiture.model.entity.brand.Brand;
import com.cloud.ventevoiture.model.entity.car.Motorisation;
import com.cloud.ventevoiture.model.entity.category.Category;
import com.cloud.ventevoiture.model.repository.BrandRepository;
import com.cloud.ventevoiture.model.repository.ModelRepository;
import com.cloud.ventevoiture.model.repository.TransmissionRepository;
import com.cloud.ventevoiture.model.repository.VersionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final CategoryService categoryService;
    private final VersionRepository versionRepository;
    private final TransmissionRepository transmissionRepository;

    public List<Brand> findAll() {

        List<Brand> brands = brandRepository.findAll();
        for (Brand brand : brands) {
            for (int i = 0; i < brand.getModels().size(); i++) {
                for (int j = 0; j < brand.getModels().get(i).getCategories().size(); j++) {
                    Category category = brand.getModels().get(i).getCategories().get(j);
                    List<Motorisation> motorisations = categoryService
                            .getMotorisationsForCategory(category.getId_category());
                    category.setMotorisations(motorisations);
                    for (int k = 0; k < category.getMotorisations().size(); k++) {
                        Motorisation motorisation = category.getMotorisations().get(k);
                        motorisation.setVersions(versionRepository.findVersionByMotorisationId(motorisation.getIdMotorisation()));
                        motorisation.setTransmissions(transmissionRepository.findTransmissionByMotorisationId(motorisation.getIdMotorisation()));
                    }
                }
            }
        }
        return brands;
    }
}
