var account = {
	"username": "1056196129@qq.com",
	"password": "1234qwer"
}

var data = [{
		"name": "获取菜单",
		"method": "POST",
		"url": "/sys/menu",
		"param": {}
	},
	{
		"name": "用户注册",
		"method": "POST",
		"url": "/register",
		"param": {
		    "email":"653139594@qq.com",
		    "password":"lxy456123",
		    "name":"name1",
		    "validateCode":"f41h",
		    "activeCode":"PKWBE3",
		    "idCard":"345756686345646",
		    "phone":"13528798485",
		    "contact":"mrwu"
		}
	},
	{
		"name": "注册激活码",
		"method": "POST",
		"url": "/register/send",
		"param": {
			 "email":"765777166@qq.com"
		}
	},
	{
		"name": "服务管理",
		"method": "POST",
		"url": "/serviceApply/get",
		"param": {
		}
	},
	{
		"name": "服务申请",
		"method": "POST",
		"url": "/serviceApply/add",
		"param": {
			"serviceId":"1"
		}
	},
	{
		"name": "服务撤销和注销",
		"method": "POST",
		"url": "/serviceApply/repeal",
		"param": {
			"id":"1"
		}
	},
	{
		"name": "个人中心信息",
		"method": "POST",
		"url": "/user/info",
		"param": {}
	},
	{
		"name": "个人中心修改",
		"method": "POST",
		"url": "/user/update",
		"param": {
		 "name":"",
		 "idCard":"",
		 "contact":"",
		 "phone":""
		}
	},
	{
		"name": "证书列表",
		"method": "POST",
		"url": "/user/cert/list",
		"param": {}
	},
	{
		"name": "证书申请",
		"method": "POST",
		"url": "/user/cert/add",
		"param": {}
	},
	{
		"name": "存证数据签名",
		"method": "POST",
		"url": "/test/setSignData",
		"param": {
			"access-token":"ae59ddc8dbe640ecbd9d30c81078b45e",
			"data":{"evidenceData":"","info":{}}
		}
	},{
		"name": "存证数据",
		"method": "POST",
		"url": "/api-bcpf/evidence/set",
		"param": {
			"access-token":"ae59ddc8dbe640ecbd9d30c81078b45e",
			"data":{"evidenceData":"","info":{}},
			"signature":"XXX"
		}
	},
	{
		"name": "获取存证地址数据签名",
		"method": "POST",
		"url": "/test/getSignData",
		"param": {
			"access-token":"ae59ddc8dbe640ecbd9d30c81078b45e",
			"data":{"evidenceId":"xxx"}
		}
	},{
		"name": "获取存证地址",
		"method": "POST",
		"url": "/api-bcpf/evidence/getAddress",
		"param": {
			"access-token":"ae59ddc8dbe640ecbd9d30c81078b45e",
			"data":{"evidenceId":1},
			"signature":"XXX"
		}
	}
	
]
