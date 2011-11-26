$(function() {
	
	var mpReg = /^1(30|31|32|33|34|35|36|37|38|39|45|47|50|51|52|55|56|57|58|59|80|85|86|87|88|89|31|31|31|31|31)\d{8}$/;
	
	// 邮政编码验证
	$.validator.addMethod("isZipCode", function(value, element) {
		var tel = /^[0-9]{6}$/;
		return this.optional(element) || (tel.test(value));
	}, "请正确填写邮政编码");

	// 手机号码验证
	$.validator.addMethod("isMobile", function(value, element) {
		var length = value.length;
		return this.optional(element) || (length == 11 && mpReg.test(value));
	}, "请正确填写手机号码");
});