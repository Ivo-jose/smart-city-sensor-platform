package br.com.ivogoncalves.smartcitysensorplatform.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

	
	/**
	 * This class defines an HTTP message converter to handle YAML format,
	 * using the Jackson 2 library. It maps Java objects to YAML and vice versa,
	 * configuring the inclusion of non-null properties and setting the media type as
	 * "application/x-yaml".
	 */
	public YamlJackson2HttpMessageConverter() {
		super(new YAMLMapper()
				.setSerializationInclusion(JsonInclude.Include.NON_NULL), MediaType.parseMediaType("application/x-yaml"));
	}
}
