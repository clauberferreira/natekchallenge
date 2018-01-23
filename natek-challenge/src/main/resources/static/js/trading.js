$(document).ready(function() {

	$("#trading-form").submit(function(event) {
		// stop submit the form, we will post it manually.
		event.preventDefault();
		submit();
	});

});

function parse(value) {
	return value.replace(/(\/\*|\*\/)/g, '');
}

function submit() {

	$("#btn-process").prop("disabled", true);

	$.ajax({
		url : "/trade",
		contentType: "application/json; charset=utf-8",
		processData : false,
		type : "POST",
		data : $('textarea#json').val()
	}).done(function(data, textStatus, jqXHR) {
		$('#message').html("<h4>Response</h4><pre>" + data + "</pre>");
	}).fail(function(xhr, textStatus, errorThrown) {
		$('#message').html(
				"<h4>Response</h4><pre>" + xhr.responseText
						+ "</pre>");
	}).complete(function(jqXHROrData, textStatus, jqXHROrErrorThrown) {
		$("#btn-process").prop("disabled", false);
	});

}