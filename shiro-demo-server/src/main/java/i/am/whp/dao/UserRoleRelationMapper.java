package i.am.whp.dao;

import i.am.whp.domain.UserRoleRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
public interface UserRoleRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleRelation record);

    int insertSelective(UserRoleRelation record);

    List<UserRoleRelation> selectByUserId(@Param("userId") Long userId);

    int updateByPrimaryKeySelective(UserRoleRelation record);

    int updateByPrimaryKey(UserRoleRelation record);
}
