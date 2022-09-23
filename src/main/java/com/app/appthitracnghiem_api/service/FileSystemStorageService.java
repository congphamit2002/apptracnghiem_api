package com.app.appthitracnghiem_api.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystemStorageService implements FileSystemStorageServiceImp{

	
	@Override
	public void init(String path) {
		// TODO Auto-generated method stub
		
		//init folder if folder not exist
		try {
				Path root = Paths.get(path);
				if(!Files.exists(root)) {
					Files.createDirectories(root);
				}
		    } catch (IOException e) {
		      throw new RuntimeException("Could not initialize folder for upload!");
		    }
		
	}
	@Override
	public void save(MultipartFile file, String path) {
		// TODO Auto-generated method stub
		
		//save file và kiểm tra nếu file có tồn tại thì kh save và hệ thống
		try {
			System.out.println("Dau code");
			Path root = Paths.get(path);
			System.out.println("Cuoi code");
			File f = new File(path + "/" + file.getOriginalFilename());
			boolean test = f.exists();
			System.out.println("kiem tra " + test);
			if(!f.exists())
			{
		      Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
			System.out.println("Chay vao save path " + path);
			}
		    } catch (Exception e) {
		      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		    }
		
	}
	@Override
	public Resource load(String fileName, String path) {
		// TODO Auto-generated method stub
		
		//load folder from fileName and path
		 try {
			 Path root = Paths.get(path);
		      Path file = root.resolve(fileName);
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
