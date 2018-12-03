package cn.mcsj.sso.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mcsj.sso.constant.GlobalConstant;
import cn.mcsj.sso.dao.RolePermissionDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.RolePermission;
import cn.mcsj.sso.service.IRolePermissionService;

@Service
public class RolePermissionService implements IRolePermissionService {
	
	@Autowired
	private RolePermissionDao rolePermissionDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public RolePermission getOne(Long primaryKey) {
		return rolePermissionDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<RolePermission> list(Map whereMap) {
		return rolePermissionDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = rolePermissionDao.total(whereMap);
		List<RolePermission> rows = new ArrayList<RolePermission>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public int save(RolePermission rolePermission) {
		Long id = rolePermission.getId();
		if (id == null) {
			return rolePermissionDao.insert(rolePermission);
		} else {
			return rolePermissionDao.update(rolePermission);
		}
	}

	@Override
	public int delete(Long primaryKey) {
		return rolePermissionDao.delete(primaryKey);
	}
}
