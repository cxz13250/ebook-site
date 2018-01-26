package net.ebook.dao;

import net.ebook.model.UserOperation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ROGK on 2017/11/1.
 */
@Mapper
public interface UserOperationDao{

    void saveOperation(@Param(value = "operation") UserOperation operation);
}
