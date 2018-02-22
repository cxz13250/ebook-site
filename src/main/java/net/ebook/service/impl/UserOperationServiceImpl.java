package net.ebook.service.impl;

import com.github.pagehelper.PageHelper;
import net.ebook.dao.UserOperationDao;
import net.ebook.model.UserOperation;
import net.ebook.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:56 2018/1/23
 * @Modified By:
 */
@Service
public class UserOperationServiceImpl implements UserOperationService{

    @Autowired
    UserOperationDao operationDao;

    @Override
    public void create(UserOperation userOperation){
        operationDao.saveOperation(userOperation);
    }

    @Override
    public List<UserOperation> getList(int page, int rows){
        PageHelper.startPage(page,rows);
        return operationDao.findOperation();
    }
}
