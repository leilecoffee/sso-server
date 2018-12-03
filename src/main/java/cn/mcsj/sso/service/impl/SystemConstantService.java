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
import cn.mcsj.sso.dao.SystemConstantDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.SystemConstant;
import cn.mcsj.sso.service.ISystemConstantService;

@Service
public class SystemConstantService implements ISystemConstantService {
	
	@Autowired
	private SystemConstantDao systemConstantDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public SystemConstant getOne(Long primaryKey) {
		return systemConstantDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<SystemConstant> list(Map whereMap) {
		return systemConstantDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = systemConstantDao.total(whereMap);
		List<SystemConstant> rows = new ArrayList<SystemConstant>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public int save(SystemConstant systemConstant) {
		Long id = systemConstant.getId();
		if (id == null) {
			return systemConstantDao.insert(systemConstant);
		} else {
			return systemConstantDao.update(systemConstant);
		}
	}

	@Override
	public int delete(Long primaryKey) {
		return systemConstantDao.delete(primaryKey);
	}
}
