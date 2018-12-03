package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.SystemConstant;

public interface ISystemConstantService{
	
	SystemConstant getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<SystemConstant> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(SystemConstant systemConstant);

    int delete(Long primaryKey);
}
