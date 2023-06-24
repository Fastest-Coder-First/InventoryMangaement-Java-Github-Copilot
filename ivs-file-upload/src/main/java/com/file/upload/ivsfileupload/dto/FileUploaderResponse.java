package com.file.upload.ivsfileupload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileUploaderResponse {

	private String sellerId;
	private String fileName;
	private String fileDownloadName;
	private long size;

}
