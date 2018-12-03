package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.RoleMenu;

public interface IRoleMenuService{
	
	RoleMenu getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<RoleMenu> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(RoleMenu roleMenu);

    int delete(Long primaryKey);
}
