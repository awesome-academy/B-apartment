package b.apartment.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import b.apartment.uploader.ImageUploader;
import b.apartment.uploader.local.LocalStorageImageUploader;

@Configuration
@PropertySource("classpath:application.properties")
public class LocalStorageConfig {

	public static final String FILE_UPLOAD_DIR = "file.upload-dir";

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Bean("localStorageUploader")
	public ImageUploader localStorageImageUploader() {
		ImageUploader imageUploader = new LocalStorageImageUploader(localStorageProperties());
		return imageUploader;
	}

	private Properties localStorageProperties() {
		Properties configs = new Properties();
		configs.put("file.upload-dir", uploadDir);
		return configs;
	}

}
