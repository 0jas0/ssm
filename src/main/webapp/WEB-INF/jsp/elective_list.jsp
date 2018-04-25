<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<table class="easyui-datagrid" id="customList" title="选课列表" data-options="singleSelect:false,collapsible:true,
		pagination:true,rownumbers:true,url:'topic/getByMajor', method:'get',pageSize:30, fitColumns:true,
		toolbar:toolbar_sutdent">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'topicId',width:100,align:'center'">课题编号</th>
            <th data-options="field:'title',width:100,align:'center'">课程题目</th>
            <th data-options="field:'teacherName',width:200,align:'center'">老师名称</th>
            <th data-options="field:'status',width:50,align:'center',formatter:TAOTAO.formatCourseStatus">状态</th>
			<th data-options="field:'remark',width:300,align:'center'">备注</th>
		</tr>
    </thead>
</table>

<div  id="toolbar_sutdent" style=" height: 22px; padding: 3px 11px; background: #fafafa;">
	<div class="datagrid-btn-separator"></div>
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="custom_reload()">刷新</a>  
	</div>  
	
    <div id="search_custom" style="float: right;">
        <input id="search_text_custom" class="easyui-searchbox"  
            data-options="searcher:doSearch_custom,prompt:'请输入...',menu:'#menu_custom'"  
            style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_custom" style="width:120px"> 
			<div data-options="keyword:'keyword'"></div>
		</div>
    </div>

</div>

<script>

function doSearch_custom(name){ //用户输入用户名,点击搜素,触发此函数
	if(name == null || name == ''){
		$("#customList").datagrid({
	        title:'选课列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true,
	        	method:'get', nowrap:true,  
	        toolbar:"toolbar_sutdent", url:'student/getData', method:'get', loadMsg:'数据加载中......',
	        	fitColumns:true,//允许表格自动缩放,以适应父容器  
	        columns : [[
	             	{field : 'ck', checkbox:true }, 
	             	{field : 'topicId', width : 100, title : '课题编号', align:'center'},
	             	{field : 'title', width : 100, align : 'center', title : '题目'},
	             	{field : 'major', width : 200, align : 'center', title : '专业'},
	             	{field : 'limitStudent', width : 50, title : '限制学生数', align:'center'},
	             	{field : 'teacherId', width : 100, title : '老师', align:'center'},
	            	{field : 'status', width : 50, title : '状态', align:'center'},
	            	{field : 'remark', width : 300, title : '备注', align:'center'},
	        ]],
	    });
	}
}	
	//根据index拿到该行值
	
	function getCustomSelectionsIds(){
		var customList = $("#customList");
		var sels = customList.datagrid("getSelections");
		var ids = [];
		for(var i in sels){
			ids.push(sels[i].topicId);
		}
		ids = ids.join(","); 
		
		return ids;
	}

    
    function custom_reload(){
    	$("#customList").datagrid("reload");
    }
    function selectCourse(obj) {
    	var $tr = $(obj).closest("tr");
		var topicId = $tr.find("td:eq(1)").text();
		$.ajax({
            type: 'post',
            url: '/topic/selectCourse',
            data: {
                'topicId': topicId
            },
            success: function (result) {
                if (result.status == 200) {
                    console.log("选课成功")
                }
            }

		});
    }
</script>