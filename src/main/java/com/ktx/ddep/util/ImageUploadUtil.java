package com.ktx.ddep.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.ktx.ddep.dto.ImageUploadType;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * util that can be used to get context path and upload the multipart file
 * 
 * @author thom
 *
 */

@Slf4j
public class ImageUploadUtil {

	public static String getImagePath(HttpServletRequest request, ImageUploadType type) {
			
			ServletContext app = request.getServletContext();
			
			String resourceImagePath = "";
			
			if(type == ImageUploadType.PROFILE) {
				
				resourceImagePath = app.getRealPath("/") + "resources" + File.separator + "static" + File.separator 
						+ "img" + File.separator + "members" + File.separator + "profile" + File.separator;
				
			}
			
			if(type == ImageUploadType.RECIPE_REVIEW) {
				
				resourceImagePath = app.getRealPath("/") + "resources" + File.separator + "static" + File.separator 
						+ "img" + File.separator + "recipes" + File.separator + "review" + File.separator;
				
			}
			
			log.info("context real path is {}", resourceImagePath);
			
			return resourceImagePath;
			
		}
	
	public static String uploadFileAndReturnFileName(MultipartFile multipartFile, String imagePath) throws IllegalStateException, IOException {
		
		String originalFileName = multipartFile.getOriginalFilename();
		log.info("original file name : {}", originalFileName);
		// create a new file in the path 
		File file = new File(imagePath + originalFileName);
		
		// rename policy when needed
		file = FileRenameUtil.rename(file);
		
		multipartFile.transferTo(file);
		
		String fileName = file.getName();
		log.info("file name : {}" + fileName);
		
		return fileName;
		
	}
	
}
