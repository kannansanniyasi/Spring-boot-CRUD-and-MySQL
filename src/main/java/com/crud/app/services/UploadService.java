package com.crud.app.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crud.app.entity.FileInfo;

@Service
public class UploadService {
	
	private final Path path=Paths.get("upload");

	
	
	public void upoadFile(MultipartFile file) {
		// TODO Auto-generated method stub
		
		try
		{
			
		 if(!Files.isDirectory(path))
		 {
			 System.out.println("Directory Created"+path);
			 Files.createDirectories(path);
		 }
		  String fileName=file.getOriginalFilename();
		  Files.copy(file.getInputStream(), this.path.resolve(fileName));
		  System.out.println("File uploade details ");
	     }catch(Exception e)
		{
			System.out.println("File uploade error "+e.getMessage());
			e.printStackTrace();
		}
		
	}



	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		try
		{
			System.out.println("File Retrived");
			return Files.walk(this.path, 1).filter(path->!path.equals(this.path)).map(this.path::relativize);
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("File Retrived Error");
		}
		return null;
	}



	public Resource load(String filename) {
	    try {
	      Path file = this.path.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

}
