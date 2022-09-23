package com.app.appthitracnghiem_api.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileSystemStorageServiceImp {
	public void init(String path);
	public void save(MultipartFile file, String path);
	public Resource load(String fileName, String path);
}
