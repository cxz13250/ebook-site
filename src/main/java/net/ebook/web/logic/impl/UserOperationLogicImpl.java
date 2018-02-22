package net.ebook.web.logic.impl;

import com.github.pagehelper.PageInfo;
import net.ebook.model.UserOperation;
import net.ebook.service.UserOperationService;
import net.ebook.util.HttpUtil;
import net.ebook.web.data.OperationVO;
import net.ebook.web.logic.BaseLogic;
import net.ebook.web.logic.UserOperationLogic;
import net.ebook.web.wrapper.OperationVOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:58 2018/1/23
 * @Modified By:
 */
@Service
public class UserOperationLogicImpl extends BaseLogic implements UserOperationLogic {

    @Autowired
    UserOperationService operationService;

    @Autowired
    OperationVOWrapper operationVOWrapper;

    @Override
    public void recordUserOperation(HttpServletRequest request, String operation){
        String ip = HttpUtil.getIpByRequest(request);
        if("101.131.137.149".equals(ip)) {
            return;
        }
        HttpSession session=request.getSession();
        if(session.getAttribute("userId") == null){
            return;
        }
        Long userId=(Long)session.getAttribute("userId");
        UserOperation userOperation = new UserOperation();
        userOperation.setIp(ip);
        userOperation.setUserId(userId);
        userOperation.setOperation(operation);
        Timestamp current=new Timestamp(System.currentTimeMillis());
        userOperation.setCreateTime(current);
        operationService.create(userOperation);
        LOG.info(String.format("User[%d] "+operation+" at [%s]",userId,current.toString()));
    }

    @Override
    public PageInfo<OperationVO> getOperations(int page, int rows){
        List<UserOperation> operations=operationService.getList(page,rows);
        List<OperationVO> vos=operations.parallelStream().map(operation ->
                operationVOWrapper.wrap(operation)).collect(Collectors.toList());
        return new PageInfo<>(vos);
    }
}
