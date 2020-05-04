package i.am.whp.enums;

/**
 * @author wuhepeng
 * @date 2020/5/5
 */
public enum UserStatusEnum {
    /**
     * 启动
     */
    enable(1),
    /**
     * 禁用
     */
    disable(2),
    ;

    private int status;

    UserStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static UserStatusEnum valueOf(int status) {
        for (UserStatusEnum statusEnum : UserStatusEnum.values()) {
            if (statusEnum.getStatus() == status) {
                return statusEnum;
            }
        }
        return null;
    }
}
