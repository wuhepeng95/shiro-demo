package i.am.whp.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
/**
 * 用户角色关联表
 */
@Data
public class UserRoleRelation implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Integer roleId;

    private static final long serialVersionUID = 1L;
}
