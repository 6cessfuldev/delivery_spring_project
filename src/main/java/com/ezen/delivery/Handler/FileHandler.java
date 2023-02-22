package com.ezen.delivery.Handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.delivery.domain.FileVO;
import com.ezen.delivery.domain.ReviewImgVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileHandler {

	
	private final String UP_DIR = "/yukssungmin/tomcat/webapps/delivery/upload";
	
	public FileVO uploadFiles(MultipartFile file){
		LocalDate date = LocalDate.now();
		String today = date.toString();
		today = today.replace("-", File.separator);
		
		File folders = new File(UP_DIR, today);
		
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		FileVO fvo = new FileVO();
		
		fvo.setFile_save_dir(today);
		fvo.setFile_size(file.getSize());
		
		String originalFileName = file.getOriginalFilename(); 
		log.info("fileName : "+originalFileName);
		 
		String onlyFileName = originalFileName
				.substring(originalFileName.lastIndexOf("\\")+1);
		log.info("only fileName : "+onlyFileName);
		fvo.setFile_name(onlyFileName);
		
		UUID uuid = UUID.randomUUID(); 
		fvo.setFile_uuid(uuid.toString()); 
		
		String fullFileName = uuid.toString()+"_"+onlyFileName; 
		File storeFile = new File(folders, fullFileName); 

		try {      
			file.transferTo(storeFile); 
		} catch (Exception e) {
			log.info("File 생성 오류");
			e.printStackTrace();
		}
		
		return fvo; 
	}

	public int deleteFile(FileVO fivo) {
		try {
			File file = new File(UP_DIR+File.separator+fivo.getFile_save_dir()+File.separator+fivo.getFile_uuid()+"_"+fivo.getFile_name());
			log.info(file.toString());
			
			if(file.exists()) { //파일여부
				if(file.delete()) { // 있으면 삭제
					log.info("파일 삭제 성공");
				}else {
					log.info("파일 삭제 실패");
				}
			}else { //파일여부
				log.info("파일이 존재하지 않습니다.");
			}
			
//			File tFile = new File(UP_DIR+File.separator+fivo.getReview_img_save_dir()+File.separator+fivo.getReview_img_uuid()+"_th_"+fivo.getReview_img_name());
//			log.info(tFile.toString());
//			
//			if(tFile.exists()) {
//				if(tFile.delete()) {
//					log.info("파일 삭제 성공");
//				}else {
//					log.info("파일 삭제 실패");
//				}
//			}else {
//				log.info("파일이 존재하지 않습니다.");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
		
	
	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile);
		return mimeType.startsWith("image")? true : false;
	}
	
}