package cn.mcsj.sso.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import cn.mcsj.sso.entity.User;

@Mapper
@SuppressWarnings("rawtypes")
public interface UserDao {
	
	User getOne(Long id);

	List<User> list(Map map);

	int total(Map map);

	int insert(User entity);

	int update(User entity);

	int delete(Long id);
}
