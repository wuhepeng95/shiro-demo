package i.am.whp.dao;

import i.am.whp.domain.UserRoleRelation;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
public interface UserRoleRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleRelation record);

    int insertSelective(UserRoleRelation record);

    UserRoleRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRoleRelation record);

    int updateByPrimaryKey(UserRoleRelation record);
}