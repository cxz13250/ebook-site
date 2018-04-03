package net.ebook.web.logic.impl;

import com.github.pagehelper.PageInfo;
import net.ebook.common.constants.DeleteStatus;
import net.ebook.common.constants.OperationStatus;
import net.ebook.model.User;
import net.ebook.model.User2Role;
import net.ebook.service.User2RoleService;
import net.ebook.service.UserService;
import net.ebook.util.EncryptionUtil;
import net.ebook.web.data.UserVO;
import net.ebook.web.exception.HttpBadRequestException;
import net.ebook.web.logic.MenuLogic;
import net.ebook.web.logic.UserLogic;
import net.ebook.web.logic.UserOperationLogic;
import net.ebook.web.wrapper.UserWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:15 2018/1/21
 * @Modified By:
 */
@Service
public class UserLogicImpl implements UserLogic{

    @Autowired
    UserService userService;

    @Autowired
    User2RoleService user2RoleService;

    @Autowired
    UserWrapper userWrapper;

    @Autowired
    MenuLogic menuLogic;

    @Autowired
    UserOperationLogic operationLogic;

    @Override
    public UserVO login(UserVO userVO, HttpServletRequest request){
        User user;
        if(userVO.getEmail()!=null) {
            user = userService.getUserByEmail(userVO.getEmail());
        }else {
            user = userService.getUserByMobile(userVO.getMobile());
        }
        if(user==null||user.getDeleted()== DeleteStatus.IS_DELETE) {
            throw new HttpBadRequestException("用户不存在");
        }else if(!user.getPassword().equals(EncryptionUtil.encryptMD5(userVO.getPassword()))){
            throw new IllegalArgumentException("密码错误");
        }
        UserVO vo=userWrapper.wrap(user);
        vo.setPassword("");
        User2Role role=userService.getRoles(user.getId()).get(0);

        vo.setMenuVOS(menuLogic.getMenusForLogin(role.getRoleId()));
        vo.setRoleId(role.getRoleId());
        vo.setRoleName(userService.findRole(role.getRoleId()).getName());

        HttpSession session=request.getSession();
        session.setAttribute("userId",user.getId());

        operationLogic.recordUserOperation(request, OperationStatus.LOGIN);
        return vo;
    }

    @Override
    public UserVO register(UserVO userVO, HttpServletRequest request) throws Exception{
        if (userVO.getPassword().length() > 15 || userVO.getPassword().length() < 5) {
            throw new HttpBadRequestException("the length of password should between 5 and 15");
        }
        if(userService.getUserByEmail(userVO.getEmail())!=null){
            throw new HttpBadRequestException("user already exist");
        }
        if(userService.getUserByMobile(userVO.getMobile())!=null){
            throw new HttpBadRequestException("user already exist");
        }

        User user=userWrapper.unwrap(userVO);
        user=userService.createUser(user);

        userVO.setPassword("");
        userVO.setId(user.getId());

        //设置角色
        Long roleId=userVO.getRoleId();
        User2Role user2Role=new User2Role();
        user2Role.setRoleId(roleId);
        user2Role.setUserId(user.getId());
        user2Role.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user2RoleService.createRole(user2Role);

        //设置菜单
        userVO.setMenuVOS(menuLogic.getMenusForLogin(roleId));

        //设置角色
        userVO.setRoleId(roleId);
        userVO.setRoleName(userService.findRole(roleId).getName());

        HttpSession session=request.getSession();
        session.setAttribute("userId",user.getId());

        operationLogic.recordUserOperation(request, OperationStatus.REGISTER);
        return userVO;
    }

    @Override
    public UserVO update(UserVO userVO, HttpServletRequest request)throws Exception{
        if (userVO.getId() == null) {
            throw new IllegalArgumentException("user id not present");
        }
        User user=userService.getUserById(userVO.getId());
        if (user == null) {
            throw new HttpBadRequestException("user not exist");
        }

        if (userVO.getName()!=null) {
            user.setName(userVO.getName());
        }
        if (userVO.getPassword()!=null && StringUtils.trim(userVO.getPassword())!="") {
            user.setPassword(EncryptionUtil.encryptMD5(userVO.getPassword()));
        }
        if (userVO.getRoleId()!=null){
            User2Role role=userService.getRoles(userVO.getId()).get(0);
            role.setRoleId(userVO.getRoleId());
            user2RoleService.updateRole(role);
        }
        if( userVO.getMobile()!=null){
            user.setMobile(userVO.getMobile());
        }
        if( userVO.getEmail()!=null){
            user.setEmail(userVO.getEmail());
        }
        userService.updateUser(user);

        operationLogic.recordUserOperation(request,OperationStatus.UPDATE_USER);

        return userVO;
    }

    @Override
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        if(session.getAttribute("userId")!=null) {
            session.invalidate();
            operationLogic.recordUserOperation(request, OperationStatus.LOGIN_OUT);
            return "您已安全登出";
        }else {
            return "您尚未登录";
        }
    }

    @Override
    public PageInfo<UserVO> getUsers(int page, int rows, String keyword){
        List<User> users = userService.getUsers(page,rows,keyword);
        List<UserVO> vos = users.parallelStream().map(user -> {
            UserVO vo = userWrapper.wrap(user);
            vo.setPassword("");
            User2Role role=userService.getRoles(user.getId()).get(0);
            vo.setRoleId(role.getRoleId());
            vo.setRoleName(userService.findRole(role.getRoleId()).getName());
            return vo;
        }).collect(Collectors.toList());
        return new PageInfo<>(vos);
    }

    @Override
    public UserVO getUser(Long userId){
        User user=userService.getUserById(userId);
        if (user == null) {
            throw new HttpBadRequestException("用户不存在");
        }
        UserVO vo = userWrapper.wrap(user);
        vo.setPassword("");
        User2Role role=userService.getRoles(userId).get(0);
        vo.setRoleId(role.getRoleId());
        return vo;
    }
}
