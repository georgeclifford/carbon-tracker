package com.carbontracker.backend.service;

import java.io.IOException;
//import com.carbontracker.entity.Transaction;

import org.springframework.web.multipart.MultipartFile;

public interface ExternalEntityService {
//    void saveEntities(List<ExternalEntity> entities);
//    void saveTransactions(List<Transaction> transactions);
	void processExternalEntityFile(MultipartFile file) throws IOException;
}
