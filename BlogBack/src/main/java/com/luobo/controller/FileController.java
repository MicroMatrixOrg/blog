package com.luobo.controller;


import com.luobo.common.lang.Result;
import com.luobo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author David
 * @since 2020-08-08
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    @Value("${file-storge.path}")
    String path;

    @PostMapping("upload")
    public Result uploadFile(MultipartFile[] upfiles) throws IOException {

        List<Map<String, String>> fileList = new ArrayList<>();
        int saveCount = 0;
        if(upfiles.length > 0){

            for(MultipartFile upfile : upfiles){
                if(upfile.getSize() > 0){
                Map<String, String> map = new HashMap<>();
                com.luobo.entity.File datafile = new com.luobo.entity.File();

                saveCount ++;
                String origName = upfile.getOriginalFilename();

                datafile.setName(origName);
                datafile.setPath(path +"/"+ origName);

                map.put("title",origName);
                map.put("filePath",path +"/"+ origName);

                File file = new File(path +"/"+ origName);

                upfile.transferTo(file);

                fileList.add(map);

                fileService.saveOrUpdate(datafile);
                }

            }
        }
        return saveCount > 0 ? Result.succ("上传成功",fileList) : Result.fail("文件上传失败");
    }
}
