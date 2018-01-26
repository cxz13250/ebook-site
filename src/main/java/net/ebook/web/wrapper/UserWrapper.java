package net.ebook.web.wrapper;

import net.ebook.model.User;
import net.ebook.util.EncryptionUtil;
import net.ebook.web.data.UserVO;
import org.springframework.stereotype.Service;

/**
 * Created by ROGK on 2017/9/14.
 */
@Service
public class UserWrapper extends BaseWrapper<UserVO,User> {

    @Override
    public UserVO wrap(User user){
        UserVO userVO=new UserVO();
        userVO.setId(user.getId());
        userVO.setCreateTime(user.getCreateTime().getTime());
        userVO.setEmail(user.getEmail());
        userVO.setMobile(user.getMobile());
        userVO.setName(user.getName());
        userVO.setPassword(user.getPassword());
        return userVO;
    }

    @Override
    public User unwrap(UserVO userVO){
        User user=new User();
        user.setEmail(userVO.getEmail());
        user.setMobile(userVO.getMobile());
        user.setPassword(EncryptionUtil.encryptMD5(userVO.getPassword()));
        user.setName(userVO.getName());
        return user;
    }
}
