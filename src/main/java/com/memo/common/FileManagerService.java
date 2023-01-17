package com.memo.common;

import org.springframework.stereotype.Component;

@Component   // 일반적인 스프링 빈
public class FileManagerService {
	// 실제 이미지가 저장될 경로(서버)
	public static final String FILE_UPLOAD_PATH = "D:\\shinboram\\6_spring_project\\memo\\workspace\\images/";
}
