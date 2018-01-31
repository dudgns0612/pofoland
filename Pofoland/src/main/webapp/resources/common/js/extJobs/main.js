// searchJobs 클릭 시
// api-url 전역변수로 빼기 ('http://api.saramin.co.kr/job-search')
$('#searchJobs').on('click', function() {
	var keyword = $('#keywords').val();
	var url = 'http://api.saramin.co.kr/job-search';
	alert('Ajax');
	$.ajax({
		type: 'POST',
		url: contextPath + '/extJobs/search',
		data: {
			keyword: keyword,
			url: url
		},
		dataType: 'JSON',
		success: function(response) {
			alert('resonponse: ' + response);
		},
		error(e) {
			console.log(e);
		}
		
		
	}) 
});