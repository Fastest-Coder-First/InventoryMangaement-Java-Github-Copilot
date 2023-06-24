package com.file.upload.ivsfileupload.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.file.upload.ivsfileupload.model.FileUploadModel;

public interface FileRepository extends MongoRepository<FileUploadModel, String> {

}
