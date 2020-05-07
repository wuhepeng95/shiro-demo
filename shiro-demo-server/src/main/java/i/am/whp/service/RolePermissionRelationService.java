package i.am.whp.service;

import i.am.whp.domain.RolePermissionRelation;

import java.util.List;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
public interface RolePermissionRelationService {

    int deleteByPrimaryKey(Integer id);

    int insert(RolePermissionRelation record);

    int insertSelective(RolePermissionRelation record);

    RolePermissionRelation selectByPrimaryKey(Integer id);

    List<RolePermissionRelation> selectByRoleIds(List<Integer> roleIds);

    int updateByPrimaryKeySelective(RolePermissionRelation record);

    int updateByPrimaryKey(RolePermissionRelation record);

}
