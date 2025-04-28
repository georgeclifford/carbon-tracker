package com.carbontracker.backend.service;

import java.util.List;

public interface MLModelService {
	
	List<String> predictSubcategories(List<String> descriptions);

}
