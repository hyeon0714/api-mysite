package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.javaex.service.GalleryService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.GalleryVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class GalleryController {

	@Autowired
	private GalleryService gs;
	
	@GetMapping("/api/gallery")
	public List<GalleryVo> list() {
		System.out.println("list");
		
		List<GalleryVo> gvList = gs.exeList();
		
		return gvList;
	}
	
	@PostMapping("/api/gallery")
	public JsonResult upload(@RequestParam MultipartFile file, HttpServletRequest request) {
		System.out.println("upload");
		
		System.out.println(file.getOriginalFilename());
		
		int no = JwtUtil.getNoFromHeader(request);
		
		System.out.println(no);
		
		if(no == 1) {
			int a = gs.exeUpload(file, no);
			if(a == 1) {
				return JsonResult.success(a);
			}else {
				return JsonResult.fail("다시 로그인해주세요");
			}
			
		}else {
			return JsonResult.fail("다시 로그인해주세요");
		}
	}
}
