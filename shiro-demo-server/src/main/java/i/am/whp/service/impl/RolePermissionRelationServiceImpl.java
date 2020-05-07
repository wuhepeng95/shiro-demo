package i.am.whp.service.impl;

import i.am.whp.dao.RolePermissionRelationMapper;
import i.am.whp.domain.RolePermissionRelation;
import i.am.whp.service.RolePermissionRelationService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
@Service
public class RolePermissionRelationServiceImpl implements RolePermissionRelationService {

    @Resource
    private RolePermissionRelationMapper rolePermissionRelationMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return rolePermissionRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RolePermissionRelation record) {
        return rolePermissionRelationMapper.insert(record);
    }

    @Override
    public int insertSelective(RolePermissionRelation record) {
        return rolePermissionRelationMapper.insertSelective(record);
    }

    @Override
    public RolePermissionRelation selectByPrimaryKey(Integer id) {
        return rolePermissionRelationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RolePermissionRelation> selectByRoleIds(List<Integer> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        return rolePermissionRelationMapper.selectByRoleIds(roleIds);
    }

    @Override
    public int updateByPrimaryKeySelective(RolePermissionRelation record) {
        return rolePermissionRelationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RolePermissionRelation record) {
        return rolePermissionRelationMapper.updateByPrimaryKey(record);
    }

}
