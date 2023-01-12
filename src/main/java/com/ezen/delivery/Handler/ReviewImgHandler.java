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
import com.ezen.delivery.domain.ReviewImgVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ReviewImgHandler {

	
	private final String UP_DIR ="D:\\delivery\\upload";
	
	public List<ReviewImgVO> uploadFiles(MultipartFile[] files){
		LocalDate date = LocalDate.now();
		log.info("date : "+date);
		String today = date.toString();
		log.info(today);
		today = today.replace("-", File.separator);
		
		File folders = new File(UP_DIR, today);
		log.info(folders.toString());

		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		
		List<ReviewImgVO> riList = new ArrayList<ReviewImgVO>();
		for(MultipartFile file : files) {
			ReviewImgVO rivo = new ReviewImgVO();
			rivo.setReview_img_save_dir(today);
			rivo.setReview_img_size(file.getSize());
			
			String originalFileName = file.getOriginalFilename(); 
			log.info("fileName : "+originalFileName);
			 
			String onlyFileName = originalFileName
					.substring(originalFileName.lastIndexOf("\\")+1);
			log.info("only fileName : "+onlyFileName);
			rivo.setReview_img_name(onlyFileName);
			 
			UUID uuid = UUID.randomUUID(); 
			rivo.setReview_img_uuid(uuid.toString()); 
			log.info("uuid"+uuid);
			
			String fullFileName = uuid.toString()+"_"+onlyFileName; 
			File storeFile = new File(folders, fullFileName); 
			try {      
				file.transferTo(storeFile); 
				log.info("1");
			} catch (Exception e) {
				log.info("File 생성 오류");
				e.printStackTrace();
			}
			riList.add(rivo);
		}
		return riList; 
	}

//	public int deleteFile(ReviewImgVO rivo) {
//		try {
//			File file = new File(UP_DIR+File.separator+rivo.getReview_img_save_dir()+File.separator+rivo.getReview_img_uuid()+"_"+rivo.getReview_img_name());
//			log.info(file.toString());
//			
//			if(file.exists()) { //파일여부
//				if(file.delete()) { // 있으면 삭제
//					log.info("파일 삭제 성공");
//				}else {
//					log.info("파일 삭제 실패");
//				}
//			}else { //파일여부
//				log.info("파일이 존재하지 않습니다.");
//			}
//			File tFile = new File(UP_DIR+File.separator+rivo.getReview_img_save_dir()+File.separator+rivo.getReview_img_uuid()+"_th_"+rivo.getReview_img_name());
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
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 1;
//	}
		
	
	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile);
		return mimeType.startsWith("image")? true : false;
	}
	
}