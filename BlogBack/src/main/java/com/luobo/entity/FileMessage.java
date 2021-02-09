package com.luobo.entity;


import lombok.Data;

@Data
public class FileMessage {
    private String fileName;
    private Long videoSize;
    private Integer imgWidth;
    private Integer imgHeight;
   // private String size;
}
