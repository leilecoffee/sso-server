package cn.mcsj.sso.constant;

public class GlobalConstant {
	public static final String SESSION_AUTH_LOGIN_USER = "session_auto_login_user";

	public static final Long TREE_ROOT_NODE_ID = 0L;

	public static final String HASH_ALGORITHM_NAME = "SHA1";

	public static final int HASH_ITERATIONS = 2;

	public static final String SHIRO_SESSION_PREX = "shiro-session:";

	public static final String SHIRO_ACTIVE_SESSIONS_CACHE_NAME = "shiro-activeSessionCache";

	public static final String SHIR_AUTHENTICATION_CACHE_NAME = "authoricationCache";

	public static final String SHIR_AUTHORIZATION_CACHE_NAME = "authorizationCache";

	public static final String CIPHERKEY = "3AvVhmFLUs0KTA3Kprsdag==";

	// session 在redis过期时间是30分钟30*60
	public static final int SHIRO_SESSION_EXPIRETIME = 1800;

	public static final String SHIRO_CACHE_PREX = "shiro-cache:";

	public static final String ROLE_OR_PERMISSION_CHANGED = "relogin";

	public static final int SHIRO_CACHE_EXPIRETIME = 1800;

	public static final String PAGE_START = "_start";

	public static final String PAGE_SIZE = "_pageSize";

	public static final Long ROOT_NODE_ID = 0L;

	
	
}
