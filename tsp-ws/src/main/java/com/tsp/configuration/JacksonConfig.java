package com.tsp.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;

import org.elasticsearch.common.joda.time.DateTime;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.TimeZone;

@Configuration
public class JacksonConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters (List<HttpMessageConverter<?>> converters) {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        final ObjectMapper objectMapper = new ObjectMapper();
        // @formatter:off
        objectMapper.setVisibilityChecker(objectMapper.getSerializationConfig()
                .getDefaultVisibilityChecker()
                .withFieldVisibility(Visibility.ANY)
                .withGetterVisibility(Visibility.NONE)
                .withIsGetterVisibility(Visibility.NONE)
                .withSetterVisibility(Visibility.NONE)
                .withCreatorVisibility(Visibility.NONE));
        //objectMapper.setTimeZone(TimeZone.getTimeZone("EST"));
        // @formatter:on
        JodaModule jodaModule = new JodaModule();
        DateTimeZone oFromZone       = DateTimeZone.forID("EST");
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss").withZone(oFromZone);
        JacksonJodaDateFormat jacksonJodaDateFormat=new JacksonJodaDateFormat(dateTimeFormatter);
        //customModule.addSerializer(DateTime.class, new DateTimeSerializer());
        jodaModule.addSerializer(new DateTimeSerializer(jacksonJodaDateFormat));
        objectMapper.registerModule(jodaModule);
        converter.setObjectMapper(objectMapper);
        converters.add(converter);
        super.configureMessageConverters(converters);
    }

}