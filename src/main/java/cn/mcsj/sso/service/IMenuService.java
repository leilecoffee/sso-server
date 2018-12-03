package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.Menu;

public interface IMenuService{
	
	Menu getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<Menu> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(Menu menu);

    int delete(Long primaryKey);
}
