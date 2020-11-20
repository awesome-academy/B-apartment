package b.apartment.uploader;

import org.springframework.web.multipart.MultipartFile;

public interface  ImageUploader {
	
	public ImageUpload uploadFile(final MultipartFile imageUpload) throws Exception;
	
	public ImageUpload buildImageUpload(String image);

}
