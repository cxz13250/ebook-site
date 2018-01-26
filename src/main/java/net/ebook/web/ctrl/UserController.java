package net.ebook.web.ctrl;

import net.ebook.common.constants.UrlConstants;
import net.ebook.common.web.ErrorResult;
import net.ebook.common.web.ResponseMessage;
import net.ebook.common.web.StatusCode;
import net.ebook.common.web.SuccessResult;
import net.ebook.web.data.UserVO;
import net.ebook.web.exception.HttpBadRequestException;
import net.ebook.web.logic.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:17 2018/1/21
 * @Modified By:
 */
@RestController
public class UserController extends BaseController{

    @Autowired
    private UserLogic userLogic;

    @RequestMapping(value = UrlConstants.API_USER+"/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody @NotNull UserVO userVO, HttpServletRequest request){
        if(userVO.getEmail()==null&&userVO.getMobile()==null)
            return new ErrorResult(StatusCode.MISS_PARAMETER);
        try {
            UserVO userVO1=userLogic.login(userVO,request);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT, userVO1);
        }catch (HttpBadRequestException ex){
            return new ErrorResult(StatusCode.USER_NOT_EXISTS);
        }catch (IllegalArgumentException ex){
            return new ErrorResult(StatusCode.PASSWORD_INCORRECT);
        }
    }

    @RequestMapping(value = UrlConstants.API_USER+"/register", method = RequestMethod.POST)
    public Map<String, Object> register(@RequestBody @NotNull UserVO userVO, HttpServletRequest request){
        try {
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT, userLogic.register(userVO,request));
        }catch (Exception ex){
            ex.printStackTrace();
            return new ErrorResult(ex.getMessage());
        }
    }



    @RequestMapping(value = UrlConstants.API+"test",method = RequestMethod.GET)
    public String test(HttpServletRequest request){
        HttpSession session=request.getSession();
        return session.getAttribute("id").toString();
    }
}
