package i.am.whp.service.impl;

import i.am.whp.dao.UserRoleRelationMapper;
import i.am.whp.domain.UserRoleRelation;
import i.am.whp.service.UserRoleRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
@Service
public class UserRoleRelationServiceImpl implements UserRoleRelationService {

    @Resource
    private UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userRoleRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserRoleRelation record) {
        return userRoleRelationMapper.insert(record);
    }

    @Override
    public int insertSelective(UserRoleRelation record) {
        return userRoleRelationMapper.insertSelective(record);
    }

    @Override
    public List<UserRoleRelation> selectByUserId(Long userId) {
        return userRoleRelationMapper.selectByUserId(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserRoleRelation record) {
        return userRoleRelationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserRoleRelation record) {
        return userRoleRelationMapper.updateByPrimaryKey(record);
    }

}
