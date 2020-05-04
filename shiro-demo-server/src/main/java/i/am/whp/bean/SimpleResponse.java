package i.am.whp.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @author wuhepeng
 * @date 2020/5/5
 */
@Data
@Builder
public class SimpleResponse {
    private int code;
    private String msg;
}
