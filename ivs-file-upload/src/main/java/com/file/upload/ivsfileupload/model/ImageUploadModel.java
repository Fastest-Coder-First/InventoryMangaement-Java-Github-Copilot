package com.file.upload.ivsfileupload.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("imagesupload")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageUploadModel {

	private String productId;
	private String sellerId;
	private List<String> imagesNames;

}
