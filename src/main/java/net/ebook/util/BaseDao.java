package net.ebook.util;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午4:50 2018/1/29
 * @Modified By:
 */
public interface BaseDao<T> extends Mapper<T>,MySqlMapper<T>{
}
