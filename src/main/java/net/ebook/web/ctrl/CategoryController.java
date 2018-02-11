package net.ebook.web.ctrl;

import com.github.pagehelper.PageInfo;
import net.ebook.common.constants.OperationStatus;
import net.ebook.common.constants.UrlConstants;
import net.ebook.common.web.ErrorResult;
import net.ebook.common.web.ResponseMessage;
import net.ebook.common.web.StatusCode;
import net.ebook.common.web.SuccessResult;
import net.ebook.model.Category;
import net.ebook.model.UserOperation;
import net.ebook.web.logic.CategoryLogic;
import net.ebook.web.logic.UserOperationLogic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:17 2018/1/27
 * @Modified By:
 */
@RestController
public class CategoryController extends BaseController{

    @Autowired
    CategoryLogic categoryLogic;

    @Autowired
    UserOperationLogic userOperationLogic;

    @RequestMapping(value = UrlConstants.API + "categories", method = RequestMethod.GET)
    public Map<String,Object> getList(@RequestParam(value = "page")Integer page,
                          @RequestParam(value = "rows")Integer rows,
                          @RequestParam(value = "keyword",required = false)String keyword,
                          HttpServletRequest request){
        try {
            PageInfo<Category> categoryPage=categoryLogic.getCategoryList(page, rows, keyword, request);
            userOperationLogic.recordUserOperation(request, OperationStatus.CATEGORY_LIST);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,categoryPage);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(StatusCode.CATEGORY_NOT_EXISTS);
        }
    }
}
