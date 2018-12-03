$(function() {
	for (i in data) {
		$("#s1").append(`<option value='` + i + `'>` + data[i].name + `</option>`);
	}

	$('#btn1').bind("click", function() {
		var index = $('#s1').val();
		var reqInfo = data[index];
		var param = JSON.parse($('#param').val());
		var headers = {
			"content-type": 'application/json'
		}
		if (reqInfo.url.indexOf("api-bcpf") > -1) {
			headers["access-token"] = param["access-token"];
			delete param["access-token"];
		}
		$.ajax({
			type: reqInfo.method,
			url: reqInfo.url,
			headers: headers,
			data: JSON.stringify(param),
			dataType: 'json',
			success: function(data) {
				$('#tx1').val(JSON.stringify(data));
			}
		});
	})
	//下拉切换
	$("#s1").change(function() {
		var index = $('#s1').val();
		$('#param').val(JSON.stringify(data[index].param));
	});
	//登录提交按钮
	$('#loginBtn').bind("click", function() {
		$.ajax({
			type: 'POST',
			url: '/login',
			headers: {
				"content-type": 'application/json'
			},
			data: JSON.stringify({
				"username": account.username,
				"password": account.password,
			}),
			dataType: 'JSON',
			success: function(data) {
				alert(data.msg);
				return;
			}
		});
	});
	$('#logoutBtn').bind("click", function() {
		$.ajax({
			type: 'GET',
			url: '/logout',
			success: function(data) {
				alert(data.msg);
				return;
			}
		});
	});
});
