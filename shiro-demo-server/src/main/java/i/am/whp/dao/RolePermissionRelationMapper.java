package i.am.whp.dao;

import i.am.whp.domain.RolePermissionRelation;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
public interface RolePermissionRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermissionRelation record);

    int insertSelective(RolePermissionRelation record);

    RolePermissionRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermissionRelation record);

    int updateByPrimaryKey(RolePermissionRelation record);
}