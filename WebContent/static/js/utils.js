$(function() {
	$(".content-bg ul li").each(function() { // 图片全为透明
		$(this).css("opacity", "0");
	});
	$(".content-bg ul li:first").css("opacity", "1"); // 第一张图片不透明
	var index = 0;
	var t;
	var li = $(".content-bg ul li");
	var number = li.size();
	function change(index) {
		li.css("visibility", "visible");
		li.eq(index).siblings().animate({
			opacity : 0
		}, 3000);
		li.eq(index).animate({
			opacity : 1
		}, 3000);
	}
	function show() {
		index = index + 1;
		if (index <= number - 1) {
			change(index);
		} else {
			index = 0;
			change(index);
		}
	}
	t = setInterval(show, 8000); // 根据窗口宽度生成图片宽度
	var width = $(window).width();
	$(".content-bg ul img").css("width", width + "px");
});
// 图片替换
var img = new Image();// 新建一个图片对象
img.src = "static/images/bg01.jpg"; // 最终显示的大图
img.onload = function() {
	document.getElementById('bigImg').src = this.src;
}
// 刷新验证码
function myReload() {
	document.getElementById("img_checkCode").src = document
			.getElementById("img_checkCode").src.split("?")[0]
			+ "?nocache=" + new Date().getTime();
}
// 判断验证码合法
function checkTime() {
	var timeStr = document.getElementById("img_checkCode").src.split("=")[1];
	var noewTime = new Date().getTime();
	if (typeof (timeStr) == 'undefined') {
		myReload();
		return;
	}
	if (Number(timeStr) < (noewTime - 60 * 1000)) {
		myReload();
	}
}
// 验证密码是否一致
function chackPassword() {
	var one = $(".password_one").val().trim();
	var two = $(".password_two").val().trim();
	if (one != two) {
		alert("密码不一致，请设置");
	}
}