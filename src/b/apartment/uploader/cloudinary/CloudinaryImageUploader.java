package b.apartment.uploader.cloudinary;

import java.io.FileNotFoundException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;

import b.apartment.uploader.ImageUpload;
import b.apartment.uploader.ImageUploader;

@Component
public class CloudinaryImageUploader implements ImageUploader {

	private static final Logger logger = LoggerFactory.getLogger(CloudinaryImageUploader.class);

	@SuppressWarnings("rawtypes")
	@Override
	public ImageUpload uploadFile(MultipartFile multipartFile) throws Exception {
		try {
			if (multipartFile == null || multipartFile.isEmpty()) {
				throw new FileNotFoundException("MultipartFile not found!!!");
			}
			CloudinaryImageUpload imageUpload = new CloudinaryImageUpload();
			Map uploadResult = Singleton.getCloudinary().uploader().upload(multipartFile.getBytes(),
					ObjectUtils.asMap("resource_type", "auto"));
			imageUpload.setPublicId((String) uploadResult.get("public_id"));
			Object version = uploadResult.get("version");
			if (version instanceof Integer) {
				imageUpload.setVersion(Long.valueOf((Integer) version));
			} else {
				imageUpload.setVersion((Long) version);
			}

			imageUpload.setSignature((String) uploadResult.get("signature"));
			imageUpload.setFormat((String) uploadResult.get("format"));
			imageUpload.setResourceType((String) uploadResult.get("resource_type"));
			return imageUpload;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	public ImageUpload buildImageUpload(String image) {
		CloudinaryImageUpload imageUpload = new CloudinaryImageUpload();
		imageUpload.setPreloadedFile(image);
		return imageUpload;
	}

}
