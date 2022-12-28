package com.app.appthitracnghiem_api.controller;


import com.app.appthitracnghiem_api.common.Constant;
import com.app.appthitracnghiem_api.service.FileSystemStorageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/file")
public class FileController {

	@Autowired
	FileSystemStorageServiceImp fileSystemStorageServiceImp;

	public String getPathReal(String path) {
		String pathReal = "";
		switch (path) {
			case "subjectImage":
			{
				pathReal = Constant.subjectImage;
				break;
			}
			case "subjectExcel":
			{
				pathReal = Constant.subjectExcel;
				break;
			}
			case "questionImages":
			{
				pathReal = Constant.questionImages;
				break;
			}
			case "questionExcel":
			{
				pathReal = Constant.questionExcel;
				break;
			}
			default:
				break;
		}
		System.out.println("\t\t\tPathReal " + pathReal);
		return pathReal;
	}

	@GetMapping("/{path}/{fileName}")
	public ResponseEntity<Resource> getFile(@PathVariable("path") String path,

			@PathVariable("fileName") String fileName) {
		String pathReal = getPathReal(path);
		System.out.println("\t\tPath " + path);
		System.out.println("\t\tFilename " + fileName);
		System.out.println("\t\tPath real " + pathReal);
		Resource file = fileSystemStorageServiceImp.load(fileName, pathReal);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);

	}

}
