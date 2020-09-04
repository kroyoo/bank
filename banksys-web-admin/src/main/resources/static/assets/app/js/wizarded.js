var WizardDemo = function() {
	$("#m_wizard");
	var e, r, i = $("#m_form");
	return {
		init: function() {
			var n;
			$("#m_wizard"), i = $("#m_form"), (r = new mWizard("m_wizard", {
				startStep: 1
			})).on("beforeNext", function(r) {
				!0 !== e.form() && r.stop()
			}), r.on("change", function(e) {
				mUtil.scrollTop()
			}), r.on("change", function(e) {
				1 === e.getStep()
			}), e = i.validate({
				ignore: ":hidden",
				rules: {
					name: {
						required: !0
					},
					identify: {
						required: !0,
						number: !0,
						identify: !0
					},
					phone: {
						required: !0,
						phoneCN: !0,
						minlength: 11,
						maxlength: 11
					},
					address1: {
						required: !0
					},
					city: {
						required: !0
					},
					state: {
						required: !0
					},
					city: {
						required: !0
					},
					open_money: {
						required: !0,
						digits: !0
					},
					password_one: {
						required: !0,
						number: !0,
						minlength: 6,
						maxlength: 6
					},
					password_again: {
						required: !0,
						number: !0,
						minlength: 6,
						maxlength: 6,
   						equalTo: "#password_one"
					},
                    accept:{
						required:!0
					},
				},
				messages: {
					"account_communication[]": {
						required: "You must select at least one communication option"
					},
					accept: {
						required: "You must accept the Terms and Conditions agreement!"
					}
				},
				invalidHandler: function(e, r) {
					mUtil.scrollTop(), swal({
						title: "",
						text: "内容有误，请改正",
						type: "error",
						confirmButtonClass: "btn btn-secondary m-btn m-btn--wide"
					})
				},
//				submitHandler: function(e) {}
			})
		}
	}
}();
jQuery(document).ready(function() {
	WizardDemo.init()
});