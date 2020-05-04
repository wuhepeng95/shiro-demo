package i.am.whp.service;

import i.am.whp.domain.UserRoleRelation;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
public interface UserRoleRelationService {

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleRelation record);

    int insertSelective(UserRoleRelation record);

    UserRoleRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRoleRelation record);

    int updateByPrimaryKey(UserRoleRelation record);

}
