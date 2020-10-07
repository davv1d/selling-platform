package com.pik.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pik.dto.PhotoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoUploader {
    private static final String PUBLIC_ID = "public_id";
    private static final String URL = "url";
    private final Cloudinary cloudinary;

    public List<PhotoDto> uploadPhotos(List<Path> paths) {
        return paths.stream()
                .map(Path::toFile)
                .map(this::cloudinaryUpload)
                .map(map -> new PhotoDto(map.get(PUBLIC_ID).toString(), map.get(URL).toString()))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("rawtypes")
    public Map deletePhoto(String photoId) {
        Map destroyMap = Collections.emptyMap();
        try {
            destroyMap = cloudinary.uploader().destroy(photoId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destroyMap;
    }

    @SuppressWarnings("rawtypes")
    private Map cloudinaryUpload(File file) {
        Map uploadResult = Collections.emptyMap();
        try {
            uploadResult = this.cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadResult;
    }
}
