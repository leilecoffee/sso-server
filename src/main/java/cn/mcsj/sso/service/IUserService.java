package cn.mcsj.sso.service;

import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqLoginBean;
import cn.mcsj.sso.entity.User;

public interface IUserService {

	User getUserByUsername(String email);

	ResultVO login(ReqLoginBean loginBean);
}
