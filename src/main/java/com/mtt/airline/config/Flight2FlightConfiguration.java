package com.mtt.airline.config;

import com.mtt.airline.accessors.RestTemplateErrorHandler;
import com.mtt.airline.converters.Flight2FlightConverter;
import org.dozer.DozerBeanMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dmunteanu on 3/15/2016.
 */
@Configuration
@EnableAutoConfiguration
public class Flight2FlightConfiguration
{


    @Bean
    public RestTemplate getRestTemplate()
    {
        RestTemplateErrorHandler errorHandler = new RestTemplateErrorHandler();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(errorHandler);
        restTemplate.setRequestFactory(clientHttpRequestFactory());
        return restTemplate;
    }

    @Bean
    public Converter getConverter()
    {
        return new Flight2FlightConverter();
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        //two secs
        factory.setReadTimeout(2000);
        factory.setConnectTimeout(2000);
        return factory;
    }

    @Bean
    public DozerBeanMapper getBeanMapper()
    {
        DozerBeanMapper mapper = new org.dozer.DozerBeanMapper();
        List<String> mappingFiles = Arrays.asList("classpath:dozer-mapping.xml");
        mapper.setMappingFiles(mappingFiles);
        return mapper;
    }

}
