package com.carbontracker.backend.service.impl;

import com.carbontracker.backend.dto.EntityDataDTO;
import com.carbontracker.backend.dto.ExternalEntityDTO;
import com.carbontracker.backend.dto.TransactionDataDTO;
import com.carbontracker.backend.entity.CarbonActivity;
import com.carbontracker.backend.entity.ExternalEntity;
import com.carbontracker.backend.entity.SubCategory;
import com.carbontracker.backend.repository.CarbonActivityRepository;
import com.carbontracker.backend.repository.ExternalEntityRepository;
import com.carbontracker.backend.repository.SubCategoryRepository;
import com.carbontracker.backend.service.ExternalEntityService;
import com.carbontracker.backend.service.MLModelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class ExternalEntityServiceImpl implements ExternalEntityService {

    @Autowired
    private ExternalEntityRepository externalEntityRepository;

    @Autowired
    private CarbonActivityRepository activityRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private MLModelService mlModelService;

    @Override
    @Transactional
    public void processExternalEntityFile(MultipartFile file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ExternalEntityDTO entityDTO = objectMapper.readValue(file.getInputStream(), ExternalEntityDTO.class);

            for (EntityDataDTO entityData : entityDTO.getEntities()) {
                ExternalEntity externalEntity = mapToExternalEntity(entityData);
                ExternalEntity savedEntity = externalEntityRepository.save(externalEntity);

                for (TransactionDataDTO transactionDTO : entityData.getTransactions()) {
                    createAndSaveCarbonActivity(savedEntity, transactionDTO);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse external entity file", e);
        }
    }

    private ExternalEntity mapToExternalEntity(EntityDataDTO entityData) {
        ExternalEntity entity = new ExternalEntity();
        entity.setEntityId(entityData.getEntity_id());
        entity.setEntityName(entityData.getEntity_name());
        entity.setEntityType(entityData.getEntity_type());
        entity.setPhone(entityData.getPhone());
        entity.setAddress(entityData.getAddress());
        entity.setPinCode(entityData.getPin_code());
        entity.setDistrict(entityData.getDistrict());
        entity.setState(entityData.getState());
        return entity;
    }

    private void createAndSaveCarbonActivity(ExternalEntity savedEntity, TransactionDataDTO transactionDTO) {
        List<String> predictions = mlModelService.predictSubcategories(Collections.singletonList(transactionDTO.getDescription()));
        String predictedSubCategory = predictions.get(0);

        SubCategory subCategory = subCategoryRepository.findBySubCategoryName(predictedSubCategory)
                .orElseThrow(() -> new RuntimeException("SubCategory not found for prediction: " + predictedSubCategory));

        double emissionValue = transactionDTO.getAmount() * subCategory.getEfValue();

        CarbonActivity activity = new CarbonActivity();
        activity.setExternalEntity(savedEntity);
        activity.setSubCategory(subCategory);
        activity.setTransactionId(transactionDTO.getTransaction_id());
        activity.setRawValue(transactionDTO.getAmount());
        activity.setEmissionValue(emissionValue);
        activity.setDate(transactionDTO.getDate());

        activityRepository.save(activity);
    }
}
