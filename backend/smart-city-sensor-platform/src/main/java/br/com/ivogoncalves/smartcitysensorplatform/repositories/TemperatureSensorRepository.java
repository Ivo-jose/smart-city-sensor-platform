package br.com.ivogoncalves.smartcitysensorplatform.repositories;

import br.com.ivogoncalves.smartcitysensorplatform.models.TemperatureSensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureSensorRepository extends MongoRepository<TemperatureSensor,Long> {}
