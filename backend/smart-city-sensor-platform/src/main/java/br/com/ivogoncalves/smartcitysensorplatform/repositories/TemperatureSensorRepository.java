package br.com.ivogoncalves.smartcitysensorplatform.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.ivogoncalves.smartcitysensorplatform.models.TemperatureSensor;

@Repository
public interface TemperatureSensorRepository extends MongoRepository<TemperatureSensor, String> {}
