package com.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component   // 일반적인 스프링 빈
public class FileManagerService {
	// 실제 이미지가 저장될 경로(서버)
	public static final String FILE_UPLOAD_PATH = "D:\\shinboram\\6_spring_project\\memo\\workspace\\images/";
	
	// input: MultipartFile, userLoginId
	// output: image path
	public String saveFile(String userLoginId, MultipartFile file) {
		// 파일 디렉토리 예) aaaa_16205468768/sun.png
		String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/";  //   aaaa_16205468768/
		String filePath = FILE_UPLOAD_PATH + directoryName;  // D:\\shinboram\\6_spring_project\\memo\\workspace\\images/aaaa_16205468768/
		
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			return null; // 폴더 만드는데 실패시 이미지패스 null
		}
		
		// 파일 업로드: byte 단위로 업로드 된다.
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename()); // originalFileName은 사용자가 올린 파일명
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		// 파일 업로드 성공했으면 이미지 url path를 리턴한다.
		// http://localhost:8080/images/aaaa_16205468768/sun.png
		return "/images/" + directoryName + file.getOriginalFilename();
	}
}




