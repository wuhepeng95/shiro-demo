package test;

import i.am.whp.domain.User;
import i.am.whp.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
public class MapperTest extends SpringBootTestBase {

    @Autowired
    private UserService userService;

    @Test
    public void testSelectUserByName() {
        User user = userService.selectByUsername("zhangsan");
        assert user != null;
        System.out.println(user);
    }
}
