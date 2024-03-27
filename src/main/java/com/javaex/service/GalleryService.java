package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao gd;

	public List<GalleryVo> exeList() {

		return gd.list();
	}

	public int exeUpload(MultipartFile file, int no) {

		String saveDir = "C:\\자바study\\upload";

		// 오리지널 파일명
		String orgName = file.getOriginalFilename();

		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf('.'));

		// 저장파일명
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

		// 파일사이즈
		long fileSize = file.getSize();

		// 저장할 파일 경로
		String filePath = saveDir + "\\" + saveName;

		// 파일저장
		try {
			byte[] fileDate = file.getBytes();
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			bos.write(fileDate);
			bos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GalleryVo gv = new GalleryVo(no, filePath, orgName, saveName, fileSize);
		
		return gd.upload(gv);

	}
}
