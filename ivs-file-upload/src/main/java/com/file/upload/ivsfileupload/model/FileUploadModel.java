package com.file.upload.ivsfileupload.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("uploadfiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadModel {

	private String sellerId;
	private String fileName;
	private String status;
}
