package com.file.upload.ivsfileupload.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file.upload.ivsfileupload.exception.CustomExceptionProduct;
import com.file.upload.ivsfileupload.model.FileUploadModel;
import com.file.upload.ivsfileupload.model.ImageUploadModel;
import com.file.upload.ivsfileupload.repo.FileRepository;
import com.file.upload.ivsfileupload.repo.ImageRepository;
import com.file.upload.ivsfileupload.service.FileStorageService;
import com.file.upload.ivsfileupload.service.ImageStorageService;

@RestController
public class FileUploadController {

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private ImageStorageService imagestorageservice;

	@Autowired
	private ImageRepository imageRepo;

	@Autowired
	private FileRepository filerepo;

	@PostMapping("/upload/file")
	public ResponseEntity<String> uploadFile(@RequestParam(name = "file", required = true) MultipartFile file,
			@RequestParam("sellerid") String sellerId) throws CustomExceptionProduct {

		String fileName = fileStorageService.storeFile(file);

		FileUploadModel filedb = new FileUploadModel(sellerId, fileName, "OPENED");
		this.filerepo.save(filedb);

		return ResponseEntity.ok().body("File Uploaded Successfully");
	}

	@PostMapping("/upload/images")
	public ResponseEntity<String> uploadImage(@RequestParam(name = "file", required = true) MultipartFile[] files,
			@RequestParam(name = "sellerid", required = true) String sellerId,
			@RequestParam(name = "productid", required = true) String productId) {
		List<String> imagesNames = new ArrayList<>();

		Arrays.asList(files).stream().forEach(file -> {
			ImageStorageService imagestorageservice2 = imagestorageservice;
			String storeFile = imagestorageservice2.storeFile(file);
			imagesNames.add(storeFile);
		});

		ImageUploadModel i = new ImageUploadModel(productId, sellerId, imagesNames);
		this.imageRepo.save(i);

		return ResponseEntity.ok().body("Images Successfully Uploaded");
	}

}
