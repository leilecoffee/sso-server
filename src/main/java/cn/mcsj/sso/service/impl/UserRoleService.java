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
import cn.mcsj.sso.dao.UserRoleDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.UserRole;
import cn.mcsj.sso.service.IUserRoleService;

@Service
public class UserRoleService implements IUserRoleService {
	
	@Autowired
	private UserRoleDao userRoleDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public UserRole getOne(Long primaryKey) {
		return userRoleDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<UserRole> list(Map whereMap) {
		return userRoleDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = userRoleDao.total(whereMap);
		List<UserRole> rows = new ArrayList<UserRole>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public int save(UserRole userRole) {
		Long id = userRole.getId();
		if (id == null) {
			return userRoleDao.insert(userRole);
		} else {
			return userRoleDao.update(userRole);
		}
	}

	@Override
	public int delete(Long primaryKey) {
		return userRoleDao.delete(primaryKey);
	}
}
