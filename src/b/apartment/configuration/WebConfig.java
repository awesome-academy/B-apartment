package b.apartment.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import b.apartment.uploader.ImageUploader;


@Configuration
@EnableWebMvc
@PropertySource("classpath:application.properties")

public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private ApplicationContext applicationContext;

	@Value("${uploader.storage.service}")
	private String storageService;

	@Bean("imageUploader")
	public ImageUploader getImageUploader() {
		return applicationContext.getBean(storageService + "Uploader", ImageUploader.class);
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
		registrar.setUseIsoFormat(true);
		registrar.registerFormatters(registry);
	}

}
