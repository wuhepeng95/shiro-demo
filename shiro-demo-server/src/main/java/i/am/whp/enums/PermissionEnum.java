package i.am.whp.enums;

import i.am.whp.exception.CommonException;
import org.springframework.http.HttpStatus;

/**
 * @author wuhepeng
 * @date 2020/5/5
 */
public enum PermissionEnum {

    /**
     * 写
     */
    write(0, "write"),
    /**
     * 读
     */
    read(1, "read"),
    ;

    private int permissionCode;
    private String permissionString;

    PermissionEnum(int permissionCode, String permissionString) {
        this.permissionCode = permissionCode;
        this.permissionString = permissionString;
    }

    public int getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(int permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionString() {
        return permissionString;
    }

    public void setPermissionString(String permissionString) {
        this.permissionString = permissionString;
    }

    public static PermissionEnum valueOf(int code) {
        for (PermissionEnum permissionEnum : PermissionEnum.values()) {
            if (permissionEnum.getPermissionCode() == code) {
                return permissionEnum;
            }
        }
        throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "parse PermissionEnum error");

    }
}
