package cn.mcsj.sso.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import cn.mcsj.sso.entity.RoleMenu;

@Mapper
@SuppressWarnings("rawtypes")
public interface RoleMenuDao {
	
	RoleMenu getOne(Long id);

	List<RoleMenu> list(Map map);

	int total(Map map);

	int insert(RoleMenu entity);

	int update(RoleMenu entity);

	int delete(Long id);
}
