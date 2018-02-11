package net.ebook.web.logic.impl;

import net.ebook.web.exception.HttpBadRequestException;
import net.ebook.web.logic.CommonLogic;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午7:48 2018/2/11
 * @Modified By:
 */
@Service
public class CommonLogicImpl implements CommonLogic {

    private final String SAVE_PATH="/var/www/download/";
    private final String DOWNLOAD_PATH="/download/";
    private final long maxUploadSizeInMb = 100 * 1024 * 1204;

    @Override
    public String saveFile(MultipartFile file, String path)throws Exception{
        Long size = file.getSize();
        if (file.isEmpty()) {
            throw new HttpBadRequestException("please select a file to upload");
        }
        if (size > maxUploadSizeInMb) {
            throw new HttpBadRequestException("file size is over limit");
        }
        String filename= SAVE_PATH + path;

        File saveFile=new File(filename);
        if(!saveFile.getParentFile().exists()){
            saveFile.getParentFile().mkdirs();
        }
        file.transferTo(saveFile);
        return DOWNLOAD_PATH + path;
    }
}
