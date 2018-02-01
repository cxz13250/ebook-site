package net.ebook.web.ctrl;

import com.github.pagehelper.PageInfo;
import net.ebook.common.constants.UrlConstants;
import net.ebook.common.web.ErrorResult;
import net.ebook.common.web.ResponseMessage;
import net.ebook.common.web.StatusCode;
import net.ebook.common.web.SuccessResult;
import net.ebook.model.Category;
import net.ebook.web.logic.CategoryLogic;
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

    @RequestMapping(value = UrlConstants.API + "categories", method = RequestMethod.GET)
    public Map<String,Object> getList(@RequestParam(value = "page")Integer page,
                          @RequestParam(value = "rows")Integer rows,
                          @RequestParam(value = "keyword",required = false)String keyword,
                          HttpServletRequest request){
        try {
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,categoryLogic.getCategoryList(page, rows, keyword, request));
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(StatusCode.CATEGORY_NOT_EXISTS);
        }
    }
}
