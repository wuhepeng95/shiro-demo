package i.am.whp.enums;

import i.am.whp.exception.CommonException;
import org.springframework.http.HttpStatus;

/**
 * @author wuhepeng
 * @date 2020/5/5
 */
public enum RoleEnum {
    /**
     * 管理员
     */
    admin(0, "admin"),
    /**
     * 普通用户
     */
    user(1, "user"),
    ;

    private int roleCode;
    private String roleString;

    RoleEnum(int roleCode, String roleString) {
        this.roleCode = roleCode;
        this.roleString = roleString;
    }

    public int getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(int roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleString() {
        return roleString;
    }

    public void setRoleString(String roleString) {
        this.roleString = roleString;
    }

    public static RoleEnum valueOf(int roleCode) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.getRoleCode() == roleCode) {
                return role;
            }
        }
        throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "parse RoleEnum error");
    }
}
