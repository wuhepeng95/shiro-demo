package i.am.whp;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wuhepeng on 2020/5/3
 */
@SpringBootApplication
@MapperScan("i.am.whp.dao")
@Slf4j
public class ShiroDemoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroDemoServerApplication.class, args);
        log.info("start shiro demo project ...");
    }
}
