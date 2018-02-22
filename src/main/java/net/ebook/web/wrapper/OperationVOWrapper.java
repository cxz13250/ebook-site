package net.ebook.web.wrapper;

import net.ebook.model.UserOperation;
import net.ebook.service.UserService;
import net.ebook.web.data.OperationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:00 2018/2/22
 * @Modified By:
 */
@Service
public class OperationVOWrapper extends BaseWrapper<OperationVO,UserOperation> {

    @Autowired
    UserService userService;

    @Override
    public OperationVO wrap(UserOperation operation){
        OperationVO vo=new OperationVO();
        vo.setName(userService.getUserById(operation.getUserId()).getName());
        vo.setCreateTime(operation.getCreateTime().getTime());
        vo.setIp(operation.getIp());
        vo.setOperation(operation.getOperation());
        return vo;
    }

    @Override
    public UserOperation unwrap(OperationVO vo){
        UserOperation operation=new UserOperation();
        operation.setIp(vo.getIp());
        operation.setOperation(vo.getOperation());
        return operation;
    }
}
