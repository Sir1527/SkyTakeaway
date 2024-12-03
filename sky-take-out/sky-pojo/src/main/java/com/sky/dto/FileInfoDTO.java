package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoDTO {
    String filename;
    String filepath;
    Boolean directory;
}
