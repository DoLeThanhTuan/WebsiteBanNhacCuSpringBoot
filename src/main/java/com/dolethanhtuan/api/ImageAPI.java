package com.dolethanhtuan.api;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageAPI {
	@GetMapping("getImage/{photoName}")
	public ResponseEntity<?> getImage(@PathVariable("photo") String photo){
		if(photo == null || photo.equals("")) {
			try {
				// Lấy đường dẫn đến file
				Path fileName = Paths.get("files",photo);
				// Đọc file thành chuỗi byte
				byte[] byteArr = Files.readAllBytes(fileName);
				ByteArrayResource byteArrayResource = new ByteArrayResource(byteArr);
				// Chuyển đổi chuỗi byte thành hình ảnh
				return ResponseEntity.ok().contentType(MediaType.parseMediaType("image/png")).body(byteArrayResource);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.badRequest().build();
	}
}
