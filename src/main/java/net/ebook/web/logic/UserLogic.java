package net.ebook.web.logic;

import net.ebook.web.data.UserVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:15 2018/1/21
 * @Modified By:
 */
public interface UserLogic {

    UserVO login(UserVO userVO, HttpServletRequest request);

    UserVO register(UserVO userVO, HttpServletRequest request) throws Exception;
}
