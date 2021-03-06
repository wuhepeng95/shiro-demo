package i.am.whp.dao;

import i.am.whp.domain.User;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    User selectByUsername(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
