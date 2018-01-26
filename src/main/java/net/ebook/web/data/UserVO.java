package net.ebook.web.data;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:17 2018/1/21
 * @Modified By:
 */
@Data
public class UserVO {

    private Long id;

    private String email;

    private String name;

    private String mobile;

    private String password;

    private Long createTime;

    private Long roleId;

    private String roleName;

    private List<MenuVO> menuVOS;
}
