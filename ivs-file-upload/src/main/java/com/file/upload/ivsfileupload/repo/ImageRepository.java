package com.file.upload.ivsfileupload.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.file.upload.ivsfileupload.model.ImageUploadModel;

public interface ImageRepository extends MongoRepository<ImageUploadModel, String> {

}
