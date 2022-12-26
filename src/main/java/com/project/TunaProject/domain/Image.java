package com.project.TunaProject.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class Image {
    private Long ImageCode;
    private String itemName;
    private List<MultipartFile> imageFiles;
    private String I_type;
    private String I_target;
    private String I_pate;
    
    public Image() {
    	
    }
}
