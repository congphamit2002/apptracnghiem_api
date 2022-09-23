package com.app.appthitracnghiem_api.controller;


import com.app.appthitracnghiem_api.common.Constant;
import com.app.appthitracnghiem_api.service.FileSystemStorageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/file")
public class FileController {

	@Autowired
	FileSystemStorageServiceImp fileSystemStorageServiceImp;

	@GetMapping("/{path}/{fileName}")
	public ResponseEntity<Resource> getFile(@PathVariable("path") String path,

			@PathVariable("fileName") String fileName) {

		String pathReal = "";
		switch (path) {
		default:
			break;
		}

		Resource file = fileSystemStorageServiceImp.load(fileName, pathReal);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);

	}

}
