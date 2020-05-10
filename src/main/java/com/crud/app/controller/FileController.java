package com.crud.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.crud.app.entity.FileInfo;
import com.crud.app.entity.MesseageDto;
import com.crud.app.services.UploadService;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired 
	UploadService uploadSer;
	
	@RequestMapping(value="/saveFile",method=RequestMethod.POST)
	public ResponseEntity<MesseageDto> uploadedFile(@RequestParam("file") MultipartFile file,@RequestParam("name") String name)
	{
		MesseageDto msg=new MesseageDto();
		try
		{
			uploadSer.upoadFile(file);
			msg.setMessage("File Upload Suceesffully"+file.getOriginalFilename());
			System.out.println("File Name "+name);
			System.out.println("File uploaded Successfully "+file.getOriginalFilename());
			return ResponseEntity.status(HttpStatus.OK).body(msg);
			
		}catch(Exception e)
		{
			msg.setMessage("File Uploaded Failed"+file.getOriginalFilename());
			System.out.println("File Uploaded Failed"+file.getOriginalFilename());	
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(msg);
		}	
	}
	
	@RequestMapping(value="/getFile",method=RequestMethod.GET)
	public ResponseEntity<List<FileInfo>> getFiles()
	{
		List<FileInfo> fileInfo=uploadSer.loadAll().map(path->
		{
		 String filename=path.getFileName().toString();
		 String url=MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();
		 FileInfo finfo=new FileInfo(); 
		 finfo.setName(filename);
		 finfo.setUrl(url);
		 return finfo;
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(fileInfo);
		
	}
	
	 @GetMapping("/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = uploadSer.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	
}

