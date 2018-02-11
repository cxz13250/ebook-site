package net.ebook.web.ctrl;

import net.ebook.common.constants.UrlConstants;
import net.ebook.common.web.ErrorResult;
import net.ebook.common.web.ResponseMessage;
import net.ebook.common.web.StatusCode;
import net.ebook.common.web.SuccessResult;
import net.ebook.web.data.BookVO;
import net.ebook.web.logic.BookLogic;
import net.ebook.web.logic.UserOperationLogic;
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
public class BookController extends BaseController{

    @Autowired
    BookLogic bookLogic;

    @Autowired
    UserOperationLogic operationLogic;

    @RequestMapping(value = UrlConstants.API+"books",method = RequestMethod.GET)
    public Map<String,Object> getList(@RequestParam(value = "page")Integer page,
                                      @RequestParam(value = "rows")Integer rows,
                                      @RequestParam(value = "keyword",required = false)String keyword,
                                      HttpServletRequest request){
        try {
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,bookLogic.getBookList(page, rows, keyword, request));
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(StatusCode.BOOK_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_BOOK,method = RequestMethod.GET)
    public Map<String, Object> getBook(@RequestParam(value = "bookId")Long bookId, HttpServletRequest request){
        try {
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,bookLogic.getById(bookId));
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API_BOOK,method = RequestMethod.PUT)
    public Map<String, Object> updateBook(@RequestBody @NotNull BookVO vo, HttpServletRequest request){
        try {
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,bookLogic.update(vo));
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API_BOOK,method = RequestMethod.POST)
    public Map<String, Object> createBook(@RequestBody @NotNull BookVO vo, HttpServletRequest request){
        try {
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,bookLogic.create(vo));
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API_BOOK,method = RequestMethod.DELETE)
    public Map<String, Object> deleteBook(@RequestParam(value = "bookId")Long bookId, HttpServletRequest request){
        try {
            bookLogic.delete(bookId);
            return SuccessResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(e.getMessage());
        }
    }
}
