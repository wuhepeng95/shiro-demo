package i.am.whp.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
/**
 * 角色权限关联表
 */
@Data
public class RolePermissionRelation implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer permissionId;

    private static final long serialVersionUID = 1L;
}
