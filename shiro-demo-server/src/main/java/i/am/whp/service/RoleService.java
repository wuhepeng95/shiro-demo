package i.am.whp.service;

import i.am.whp.domain.Role;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
public interface RoleService {

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

}
