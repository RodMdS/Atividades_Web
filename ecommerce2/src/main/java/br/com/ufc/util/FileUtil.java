package br.com.ufc.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	public static void saveImage(String address, MultipartFile image) {
		File file = new File(address);
		try {
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeImage(String adress) {
		File file = new File(adress);
		if(file.exists()) {
			file.delete();
		}
	}

}
