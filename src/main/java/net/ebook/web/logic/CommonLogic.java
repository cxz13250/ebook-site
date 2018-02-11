package net.ebook.web.logic;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午7:48 2018/2/11
 * @Modified By:
 */
public interface CommonLogic {

    String saveFile(MultipartFile file, String path)throws Exception;
}
