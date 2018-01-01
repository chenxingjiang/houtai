$(function(){
	loadCustomerGc();
});



/**
 * 显示用户构成柱状图
 */
function loadCustomerGc(){
	$.ajax({
		type:"post",
		url:ctx+"/customer/queryCustomersGc",
		dataType:"json",
		success:function(data){
			if(data.code==200){
				var data1=data.levels;
				var data2=data.counts;
				// 基于准备好的dom，初始化echarts实例
		        var myChart = echarts.init(document.getElementById('main'));

		        // 指定图表的配置项和数据
		        var option = {
		            title: {
		                text: '客户构成分析'
		            },
		            tooltip: {},
		            legend: {
		                data:['客户级别数量']
		            },
		            xAxis: {
		                data: data1
		            },
		            yAxis: {},
		            series: [{
		                name: '客户级别数量',
		                type: 'bar',
		                data: data2
		            }]
		        };

		        // 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option);
			}else{
				console.log("暂无数据");
			}
		}
	});
}