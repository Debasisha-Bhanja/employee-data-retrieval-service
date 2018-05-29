package com.service.emp.util;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoOperations;

import com.service.emp.domain.Employee;

@Configuration
public class DataImportConfiguration {

	@Bean
	public CommandLineRunner initData(MongoOperations mongo) {
		return (String... args) -> {
			mongo.dropCollection(Employee.class);
			mongo.createCollection(Employee.class);
			getAllEmployee().forEach(mongo::save);
		};
	}

	private List<Employee> getAllEmployee() {
		Properties yaml = loadCitiesYaml();
		MapConfigurationPropertySource source = new MapConfigurationPropertySource(yaml);
		return new Binder(source).bind("employees", Bindable.listOf(Employee.class)).get();
	}

	private Properties loadCitiesYaml() {
		YamlPropertiesFactoryBean properties = new YamlPropertiesFactoryBean();
		properties.setResources(new ClassPathResource("employees.yml"));
		return properties.getObject();
	}

}
