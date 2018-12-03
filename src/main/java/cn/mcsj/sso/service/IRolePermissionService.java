package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.RolePermission;

public interface IRolePermissionService{
	
	RolePermission getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<RolePermission> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(RolePermission rolePermission);

    int delete(Long primaryKey);
}
