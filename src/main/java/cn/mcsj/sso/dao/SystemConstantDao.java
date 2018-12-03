package cn.mcsj.sso.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import cn.mcsj.sso.entity.SystemConstant;

@Mapper
@SuppressWarnings("rawtypes")
public interface SystemConstantDao {
	
	SystemConstant getOne(Long id);

	List<SystemConstant> list(Map map);

	int total(Map map);

	int insert(SystemConstant entity);

	int update(SystemConstant entity);

	int delete(Long id);
}
