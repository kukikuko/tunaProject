package com.project.TunaProject.domain;

import com.project.TunaProject.img.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class Image {
    private int ImageCode;		//이미지코드
    private String imageName;	//이미지 이름
    private String imageFiles;	//
    private String IType;		
    private String ITarget;
}
