package br.com.ivogoncalves.smartcitysensorplatform.unittests.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.ivogoncalves.smartcitysensorplatform.models.BrightnessSensor;
import br.com.ivogoncalves.smartcitysensorplatform.models.dtos.BrightnessSensorDTO;

public class MockBrightnessSensor {

	public BrightnessSensor mockEntity() {
		return mockEntity(0);
	}
	
	public BrightnessSensorDTO mockDTO() {
		return mockDTO(0);
	}
	
	public List<BrightnessSensor> mockEntityList() {
		List<BrightnessSensor> list = new ArrayList<>();
		for(int i=0; i<10; i++) list.add(mockEntity(i));
		return list;
	}
	
	public List<BrightnessSensorDTO> mockDTOList() {
		List<BrightnessSensorDTO> list = new ArrayList<>();
		for(int i=0; i<10; i++) list.add(mockDTO(i));
		return list;
	}

	public BrightnessSensor mockEntity(Integer number) {
		BrightnessSensor bs = new BrightnessSensor();
		String uid = String.valueOf(Short.MAX_VALUE + number);
		bs.setUid(uid);
		bs.setLux(100.0 + number);
		bs.setTimesStampUTC(new Date());
		return bs;
	}
	
	public BrightnessSensorDTO mockDTO(Integer number) {
		BrightnessSensorDTO dto = new BrightnessSensorDTO();
		String uid = String.valueOf(Short.MAX_VALUE + number);
		dto.setUid(uid);
		dto.setLux(100.0 + number);
		dto.setTimesStampUTC(new Date());
		return dto;
	}

}
