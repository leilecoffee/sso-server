package cn.mcsj.sso.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mcsj.sso.constant.GlobalConstant;
import cn.mcsj.sso.dao.PermissionDao;
import cn.mcsj.sso.dao.RolePermissionDao;
import cn.mcsj.sso.dao.UserRoleDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.Permission;
import cn.mcsj.sso.entity.RolePermission;
import cn.mcsj.sso.entity.UserRole;
import cn.mcsj.sso.service.IPermissionService;

@Service
public class PermissionService implements IPermissionService {

	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Permission getOne(Long primaryKey) {
		return permissionDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	public List<Permission> list(Map whereMap) {
		return permissionDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = permissionDao.total(whereMap);
		List<Permission> rows = new ArrayList<Permission>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	public int save(Permission permission) {
		Long permissionId = permission.getPermissionId();
		if (permissionId == null) {
			return permissionDao.insert(permission);
		} else {
			return permissionDao.update(permission);
		}
	}

	public int delete(Long primaryKey) {
		return permissionDao.delete(primaryKey);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Permission> getPermissionByUserId(Long userId) {
		List<Permission> result = new ArrayList<Permission>();
		Map whereMap = new HashMap();
		// 获取用户与角色关系信息
		whereMap.put("userId", userId);
		List<UserRole> userRoles = userRoleDao.list(whereMap);
		if (userRoles.size() < 1) {
			return result;
		}
		List<Long> roleIds = new ArrayList<Long>();
		for (UserRole ur : userRoles) {
			roleIds.add(ur.getRoleId());
		}
		whereMap.clear();
		// 获取角色与权限信息
		whereMap.put("roleIds", roleIds);
		List<RolePermission> rolePermissions = rolePermissionDao.list(whereMap);
		if (rolePermissions.size() < 1) {
			return result;
		}
		List<Long> permissionIds = new ArrayList<Long>();
		for (RolePermission rp : rolePermissions) {
			permissionIds.add(rp.getPermissionId());
		}
		whereMap.clear();
		// 查询当前用户的权限菜单信息
		whereMap.put("permissionIds", permissionIds);
		result = permissionDao.list(whereMap);
		return result;
	}
}
