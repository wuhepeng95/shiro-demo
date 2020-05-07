package i.am.whp.service;

import i.am.whp.domain.UserRoleRelation;

import java.util.List;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
public interface UserRoleRelationService {

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleRelation record);

    int insertSelective(UserRoleRelation record);

    List<UserRoleRelation> selectByUserId(Long userId);

    int updateByPrimaryKeySelective(UserRoleRelation record);

    int updateByPrimaryKey(UserRoleRelation record);

}
