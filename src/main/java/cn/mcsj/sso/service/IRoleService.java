package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.Role;

public interface IRoleService{
	
	Role getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<Role> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(Role role);

    int delete(Long primaryKey);
}
