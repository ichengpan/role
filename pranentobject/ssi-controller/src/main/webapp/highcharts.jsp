<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/highcharts/highcharts.js"></script>
</head>
<body>
	<img alt="没有图片" src="F:\javaFile\fileZilla\FileServer\user\bbq\222.jpg">
	<div id="container"></div>
	
	
	
	<button id="button" class="autocompare">新增标识区</button>
	<script type="text/javascript">
	$(function () {
	    var chart = Highcharts.chart('container', {
	        xAxis: {
	            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
	        },
	        series: [{
	            data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
	        }]
	    });
	    // the button action
	    var hasPlotBand = false,
	        $button = $('#button');
	    $button.click(function () {
	        if (!hasPlotBand) {
	            chart.xAxis[0].addPlotBand({
	                from: 5.5,
	                to: 7.5,
	                color: '#FCFFC5',
	                id: 'plot-band-1'
	            });
	            $button.html('删除标识区');
	        } else {
	            chart.xAxis[0].removePlotBand('plot-band-1');
	            $button.html('新增标识区');
	        }
	        hasPlotBand = !hasPlotBand;
	    });
	});

	</script>
</body>
</html>