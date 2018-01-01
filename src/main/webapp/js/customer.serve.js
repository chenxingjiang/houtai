$(function(){
	loadCustomerServeData();
});


function loadCustomerServeData(){
	$.ajax({
		type:"post",
		url:ctx+"/customer_serve/queryCustomerServeDtos",
		dataType:"json",
		success:function(data){
			if(data.code==200){
				
				var data1=data.types;
				var data2=data.serveTypeDtos;
				// 基于准备好的dom，初始化echarts实例
				var myChart = echarts.init(document.getElementById('main'));

				// 指定图表的配置项和数据
				var option = {
						title : {
							text: '客户服务分析',
							subtext: '来自crm',
							x:'center'
						},
						tooltip : {
							trigger: 'item',
							formatter: "{a} <br/>{b} : {c} ({d}%)"
						},
						legend: {
							orient: 'vertical',
							left: 'left',
							data: data1
						},
						series : [
						          {
						        	  name: '来自crm',
						        	  type: 'pie',
						        	  radius : '55%',
						        	  center: ['50%', '60%'],
						        	  data:data2,
						        	        itemStyle: {
						        	        	emphasis: {
						        	        		shadowBlur: 10,
						        	        		shadowOffsetX: 0,
						        	        		shadowColor: 'rgba(0, 0, 0, 0.5)'
						        	        	}
						        	        }
						          }
						          ]
				};

				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
			}else{

			}
		}

	});
}