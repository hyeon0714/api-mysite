package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;
import com.javaex.util.JsonResult;
import com.javaex.vo.UserVo;

@RestController
public class AttachController {
	
	@Autowired
	private AttachService as;
	
	@PostMapping("/api/attach")
	public JsonResult upload(@RequestParam MultipartFile file, @ModelAttribute UserVo uv) {//json으로 보낸 내용이 아닌 multipart로 데이터를 보냇으니 @RequestParam MultipartFile로 받는다
		System.out.println("upload");
		System.out.println(file.getOriginalFilename());
		System.out.println(uv);
		
		String saveName = as.exeUpload(file);
		
		return JsonResult.success(saveName);
	}
}
