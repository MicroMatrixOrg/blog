package com.luobo.controller;


import com.luobo.common.lang.Result;
import com.luobo.entity.FileMessage;
import com.luobo.service.FileService;
import com.luobo.util.VideoTransCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

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

    /**
     * @title uploadFile
     * @description 接收博客上传的文件
     * @author davidmorgan
     * @param: upfiles
     * @updateTime 2021/1/25 13:55
     * @return: com.luobo.common.lang.Result
     */
    @PostMapping("upload")
    public Result uploadFile(MultipartFile[] upfiles) throws Exception {

        List<Map<String, String>> fileList = new ArrayList<>();
//        文件计数，查看是否成功上传
        int saveCount = 0;
        if(upfiles.length > 0){

            for(MultipartFile upfile : upfiles){
                if(upfile.getSize() > 0){
                Map<String, String> map = new HashMap<>();
                com.luobo.entity.File datafile = new com.luobo.entity.File();

                saveCount ++;
                String origName = upfile.getOriginalFilename();

                String contentType = upfile.getContentType();

                Boolean isImage = contentType.toLowerCase().contains("image");

                Calendar cal=Calendar.getInstance();
                int y=cal.get(Calendar.YEAR);
                int m=cal.get(Calendar.MONTH)+1;
                int d=cal.get(Calendar.DATE);
                String currentTime = y+"/"+m+"/"+d;

                //相对路径用来展示 配合文件域名 file.**.**

                String relativePath = "";
                //文件存储的绝对路径 用UUID进行唯一的标示
                String filePath = "";

                if(isImage){
                    //是图片就不用去视频转化
                    relativePath += "/blog/"+currentTime+'/'+UUID.randomUUID();
                    filePath += path +relativePath;

                    datafile.setName(origName);
                    datafile.setPath(filePath);


                    File file = new File(filePath);
                    if(!file.exists() && !file.isDirectory()){
                        file.mkdirs();
                    }
                    File fileNew = new File(filePath);
                    upfile.transferTo(fileNew);

                    map.put("title",origName);
                    map.put("filePath",relativePath);
                }else{
                    relativePath += "/blog/"+currentTime+'/';
                    filePath += path +relativePath;

                   FileMessage fm =  VideoTransCodeUtil.VideoTransCode(filePath,upfile);

                    datafile.setName(origName);
                    datafile.setPath(filePath);

                    map.put("title",origName);
                    map.put("filePath",relativePath);
                }


//                upfile.transferTo(file);

                fileList.add(map);

                fileService.saveOrUpdate(datafile);
                }

            }
        }
        return saveCount > 0 ? Result.succ("上传成功",fileList) : Result.fail("文件上传失败");
    }
}
