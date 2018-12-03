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
import cn.mcsj.sso.dao.RoleMenuDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.RoleMenu;
import cn.mcsj.sso.service.IRoleMenuService;

@Service
public class RoleMenuService implements IRoleMenuService {
	
	@Autowired
	private RoleMenuDao roleMenuDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public RoleMenu getOne(Long primaryKey) {
		return roleMenuDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<RoleMenu> list(Map whereMap) {
		return roleMenuDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = roleMenuDao.total(whereMap);
		List<RoleMenu> rows = new ArrayList<RoleMenu>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public int save(RoleMenu roleMenu) {
		Long id = roleMenu.getId();
		if (id == null) {
			return roleMenuDao.insert(roleMenu);
		} else {
			return roleMenuDao.update(roleMenu);
		}
	}

	@Override
	public int delete(Long primaryKey) {
		return roleMenuDao.delete(primaryKey);
	}
}
