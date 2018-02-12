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
import net.ebook.web.data.CategoryVO;
import net.ebook.web.logic.CategoryLogic;
import net.ebook.web.logic.UserOperationLogic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
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
            PageInfo<CategoryVO> categoryPage=categoryLogic.getCategoryList(page, rows, keyword, request);
            userOperationLogic.recordUserOperation(request, OperationStatus.CATEGORY_LIST);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,categoryPage);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(StatusCode.CATEGORY_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_CATEGORY, method = RequestMethod.GET)
    public Map<String,Object> get(@RequestParam(value = "categoryId")Long categoryId){
        try{
            CategoryVO vo=categoryLogic.getById(categoryId);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,vo);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(StatusCode.CATEGORY_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_CATEGORY, method = RequestMethod.POST)
    public Map<String,Object> create(@RequestBody @NotNull CategoryVO vo){
        try{
            CategoryVO categoryVO=categoryLogic.create(vo);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,categoryVO);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API_CATEGORY, method = RequestMethod.PUT)
    public Map<String,Object> update(@RequestBody @NotNull CategoryVO vo){
        try{
            CategoryVO categoryVO=categoryLogic.update(vo);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,categoryVO);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API_CATEGORY, method = RequestMethod.DELETE)
    public Map<String,Object> delete(@RequestParam(value = "categoryId")Long categoryId){
        try{
            categoryLogic.delete(categoryId);
            return SuccessResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(e.getMessage());
        }
    }
}
