package i.am.whp.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
/**
 * 权限表
 */
@Data
public class Permission implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 权限类型：
     */
    private Byte permissionType;

    /**
     * 权限描述
     */
    private String description;

    private static final long serialVersionUID = 1L;
}
