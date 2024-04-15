package br.com.ivogoncalves.smartcitysensorplatform.unittests.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.ivogoncalves.smartcitysensorplatform.models.TemperatureSensor;
import br.com.ivogoncalves.smartcitysensorplatform.models.dtos.TemperatureSensorDTO;

public class MockTemperatureSensor {

	public TemperatureSensor mockEntity() {
		return mockEntity(0);
	}
	
	public TemperatureSensorDTO mockDTO() {
		return mockDTO(0);
	}
	
	public List<TemperatureSensor> mockEntityList() {
		List<TemperatureSensor> list = new ArrayList<>();
		for(int i=0; i<10; i++) list.add(mockEntity(i));
		return list;
	}
	
	public List<TemperatureSensorDTO> mockDTOList() {
		List<TemperatureSensorDTO> list = new ArrayList<>();
		for(int i=0; i<10; i++) list.add(mockDTO(i));
		return list;
	}

	public TemperatureSensor mockEntity(Integer number) {
		TemperatureSensor bs = new TemperatureSensor();
		String uid = String.valueOf(Short.MAX_VALUE + number);
		bs.setUid(uid);
		bs.setCelsiusTemperature(40.0 + number);
		bs.setUtcTimesStamp(new Date());
		return bs;
	}
	
	public TemperatureSensorDTO mockDTO(Integer number) {
		TemperatureSensorDTO dto = new TemperatureSensorDTO();
		String uid = String.valueOf(Short.MAX_VALUE + number);
		dto.setUid(uid);
		dto.setCelsiusTemperature(40.0 + number);
		dto.setUtcTimesStamp(new Date());
		return dto;
	}

}
