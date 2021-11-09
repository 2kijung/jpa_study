package com.kh.toy.common.util.file;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileHandler {

	@GetMapping("/download")
	public ResponseEntity<UrlResource> downloadFile(FileInfo file) throws UnsupportedEncodingException, URISyntaxException, MalformedURLException{
		
		UrlResource resource = new UrlResource(new URI(file.getLink()));
		
		ResponseEntity<UrlResource> response = 
				ResponseEntity.ok()
				.header("Content-Disposition", "attachment; filename="+URLEncoder.encode(file.getOriginFileName(), "UTF-8"))
				.body(resource);
		
		return response;
	}
	
	
}
