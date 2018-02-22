package net.ebook.web.logic;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import net.ebook.web.data.OperationVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午4:33 2017/12/5
 * @Modified By:
 */
public interface UserOperationLogic {

    void recordUserOperation(HttpServletRequest request, String operation);

    PageInfo<OperationVO> getOperations(int page, int rows);
}
