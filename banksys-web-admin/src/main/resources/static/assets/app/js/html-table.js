//== Class definition

var DatatableHtmlTableDemo = function() {
	//== Private functions



	// demo initializer
	var demo = function() {

		var datatable = $('.m-datatable').mDatatable({
			data: {
				saveState: {cookie: false},
			},
			search: {
				input: $('#generalSearch'),
			},
			columns: [
                {
                    field: '状态',
                    title: 'Status',
                    // callback function support for column rendering
                    template: function(row) {
                        var status = {
                            1: {'title': '正常', 'class': ' m-badge--success'},
                            2: {'title': '删除', 'class': ' m-badge--metal'},
                            3: {'title': '冻结', 'class': ' m-badge--danger'},
                        };
                        return '<span class="m-badge ' + status[row.状态].class + ' m-badge--wide">' + status[row.状态].title + '</span>';
                    },
                },
			],
		});

		$('#m_form_status').on('change', function() {
			datatable.search($(this).val().toLowerCase(), '状态');
		});

		$('#m_form_status, #m_form_type').selectpicker();

	};

	return {
		//== Public functions
		init: function() {
			// init dmeo
			demo();
		},
	};
}();

jQuery(document).ready(function() {
	DatatableHtmlTableDemo.init();
});