package net.ebook.web.logic;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午4:33 2017/12/5
 * @Modified By:
 */
public interface UserOperationLogic {

    void recordUserOperation(HttpServletRequest request, String operation);
}
