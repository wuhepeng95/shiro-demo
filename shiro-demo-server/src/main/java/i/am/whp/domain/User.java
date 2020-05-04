package i.am.whp.domain;

import i.am.whp.enums.UserStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
/**
 * 用户表
 */
@Data
public class User implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 状态：1启动 2禁用
     */
    private UserStatusEnum status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
