package com.luobo.service.impl;

import com.luobo.entity.File;
import com.luobo.mapper.FileMapper;
import com.luobo.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author David
 * @since 2020-08-08
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

}
