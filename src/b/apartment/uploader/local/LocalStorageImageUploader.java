package b.apartment.uploader.local;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import b.apartment.configuration.LocalStorageConfig;
import b.apartment.uploader.ImageUpload;
import b.apartment.uploader.ImageUploader;

public class LocalStorageImageUploader implements ImageUploader {
	
	private static final Logger logger = LoggerFactory.getLogger(LocalStorageImageUploader.class);

	private Properties localStorageConfig;

	public LocalStorageImageUploader(Properties localStorageConfig) {
		this.localStorageConfig = localStorageConfig;
	}

	public void setLocalStorageConfig(Properties localStorageConfig) {
		this.localStorageConfig = localStorageConfig;
	}
	
	@Override
	public ImageUpload uploadFile(MultipartFile file) throws Exception {

		// Normalize the path by suppressing sequences like "path/.." and inner simple
		// dots.
		String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
		try {
			// we can add additional file validation to discard invalid files
			Path uploadDir = getUploadDirLocation().resolve(fileName);

			// copy the file to the upload directory,it will replace any file with same
			// name.
			Files.copy(file.getInputStream(), uploadDir, StandardCopyOption.REPLACE_EXISTING);

			ImageUpload fileData = new LocalStorageImageUpload();
			fileData.setStoredPath(fileName);
			return fileData;

		} catch (IOException e) {
			logger.error("unable to cpy file to the target location {}", e);
			throw new Exception("Unable to store file " + fileName, e);
		}
	}
	
	@Override
	public ImageUpload buildImageUpload(String fileName) {
		LocalStorageImageUpload imageUpload = new LocalStorageImageUpload();
		Path path = getUploadDirLocation().resolve(fileName).normalize();

		try {
			Resource resource = new UrlResource(path.toUri());
			if (resource.exists()) {
				imageUpload.setResource(resource);
				imageUpload.setStoredPath(fileName);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return imageUpload;
	}
	
	/**
	 * Provides the upload directory location based on the application.properties
	 * configurations
	 *
	 * @return Path
	 */
	private Path getUploadDirLocation() {
		return Paths.get(localStorageConfig.getProperty(LocalStorageConfig.FILE_UPLOAD_DIR)).toAbsolutePath()
				.normalize();
	}

}
