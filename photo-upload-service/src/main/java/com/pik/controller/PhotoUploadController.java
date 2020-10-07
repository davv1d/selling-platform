package com.pik.controller;

import com.pik.dto.PhotoDto;
import com.pik.service.PhotoUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhotoUploadController {
    private final PhotoUploader photoUploader;

    @PostMapping("/upload")
    public List<PhotoDto> uploadPhoto(@RequestBody PhotoPathsDto photoPathsDto) {
        return this.photoUploader.uploadPhotos(photoPathsDto.getPaths());
    }
}
