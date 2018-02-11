package net.ebook.web.ctrl;

import net.ebook.common.constants.OperationStatus;
import net.ebook.common.constants.UrlConstants;
import net.ebook.common.web.ErrorResult;
import net.ebook.common.web.ResponseMessage;
import net.ebook.common.web.SuccessResult;
import net.ebook.web.logic.CommonLogic;
import net.ebook.web.logic.UserOperationLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午7:43 2018/2/11
 * @Modified By:
 */
@RestController
public class UpLoadController extends BaseController {

    @Autowired
    CommonLogic commonLogic;

    @Autowired
    UserOperationLogic userOperationLogic;

    @RequestMapping(value = UrlConstants.API + "upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,method = RequestMethod.POST)
    public Map<String, Object> upload(@RequestParam(value = "path")String path,
                                      @RequestParam(value = "file") MultipartFile file,
                                      HttpServletRequest request){
        try {
            String filePath = commonLogic.saveFile(file, path);

            userOperationLogic.recordUserOperation(request, OperationStatus.UPLOAD_IMAGE);

            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,filePath);
        }catch (Exception e){
            return new ErrorResult(e.getMessage());
        }
    }
}
