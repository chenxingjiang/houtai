$(function(){

	initTreeData();

});
function initTreeData(){
	$.ajax({
		type:"post",
		url:"module/queryAllsModuleDtos",
		dataType:"json",
		success:function(data){
			// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
			var setting = {
					check: {
						enable: true
					},
					data: {
						simpleData: {
							enable: true
						}
					}
			};
			// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
			var zNodes =data;
		    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}
	});
}
