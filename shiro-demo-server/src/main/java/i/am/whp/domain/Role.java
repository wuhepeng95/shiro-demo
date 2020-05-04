package i.am.whp.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
/**
 * 角色表
 */
@Data
public class Role implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 角色类型：0管理员 1普通用户
     */
    private Byte roleType;

    /**
     * 角色描述
     */
    private String description;

    private static final long serialVersionUID = 1L;
}
