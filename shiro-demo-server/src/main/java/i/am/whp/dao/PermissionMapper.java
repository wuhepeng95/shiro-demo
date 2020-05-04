package i.am.whp.dao;

import i.am.whp.domain.Permission;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}