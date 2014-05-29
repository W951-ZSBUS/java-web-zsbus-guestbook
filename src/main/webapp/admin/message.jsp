<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<jsp:include page="/resources.jsp" />
		<script type="text/javascript">
			$(document).ready(function() {
				$("#dg_list").datagrid({					
				    onSelect : function(rowIndex, rowData) {
				    	$("#dg_list_reply").datagrid({				    		
				    		pageNumber : 1,
			    		    url:"guestbook/Message/action/queryByMsgId?reply.message.messageId=" + rowData.messageId
			    		});
				    	
					    var cMsg = $("#msg_content");
					    cMsg.panel({content:rowData.messageContent});
					    var cReply = $("#reply_content");
					    cReply.panel({content:" "});
				    }
				});
				$("#dg_list_reply").datagrid({
				    onSelect : function(rowIndex, rowData) {
					    var cReply = $("#reply_content");
					    cReply.panel({content:rowData.replyContent});
				    }
				});	
			});
		
			function add() {
				$("#dlg_manager").dialog("open");
				$("#fm_manager").form("clear");
			}
			
			function edit() {
				var row = $("#dg_list").datagrid("getSelected");
				if (row == null) {
					funtl_easyui_dialog.info("请选择一条记录");
				} else {
					$("#dlg_manager").dialog("open");
					$("#message_messageId").val(row.messageId);
					$("#message_messageTitle").val(row.messageTitle);
					$("#message_messageContent").val(row.messageContent);
					$("#message_messageNiki").val(row.messageNiki);
					$("#message_messageEmail").val(row.messageEmail);
					$("#message_messageCompany").val(row.messageCompany);
					$("#message_messageAddress").val(row.messageAddress);
					$("#message_messagePhone").val(row.messagePhone);
					$("#message_messageFax").val(row.messageFax);
					$("#message_messageCreatedate").val(row.messageCreatedate);
				}
			}
			
			function del() {
				var row = $("#dg_list").datagrid("getSelected");
				if (row == null) {
					funtl_easyui_dialog.info("请选择一条留言记录");
				} else {
					funtl_easyui_dialog.confirm("确定要删除这条留言及其回复吗？", function() {
						var data = {
							"message.messageId" : row.messageId
						};
						funtl_easyui_ajax.post("guestbook/Message/action/delete", data, function(data) {
							if (data.message == null || data.message.length == 0) {
								$("#dg_list").datagrid("reload");
								$("#dg_list_reply").datagrid("reload");
								var cMsg = $("#msg_content");
							    cMsg.panel({content:" "});
							    var cReply = $("#reply_content");
							    cReply.panel({content:" "});
								funtl_easyui_dialog.info("数据已删除");
							} else {
								funtl_easyui_dialog.info(data.message);
							}
						});
					});
				}
			}
			
			var dlgManagerBtn = [{
			    text:"保存",
			    iconCls:"icon-ok",
			    handler:function() {
			    	if ($("#message_messageId").val() == "") {
			    		$("#fm_manager").attr("action", "guestbook/Message/action/insert");
			    	} else {
			    		$("#fm_manager").attr("action", "guestbook/Message/action/update");
			    	}
			    	
			    	funtl_easyui_form.submit("fm_manager", function(data) {
						if (data.message == null || data.message.length == 0) {
							$("#fm_manager").form("clear");
							$("#dlg_manager").dialog("close");
							$("#dg_list").datagrid("reload");
							var cMsg = $("#msg_content");
						    cMsg.panel({content:" "});
							funtl_easyui_dialog.info("数据已保存");
						} else {
							funtl_easyui_dialog.info(data.message);
						}
					});
			    }
			},{
			    text:"取消",
			    iconCls:"icon-cancel",
			    handler:function() {
			    	$("#dlg_manager").dialog("close");
			    }
			}];
			
			
			function addReply(){
				var row = $("#dg_list").datagrid("getSelected");
				if (row == null) {
					funtl_easyui_dialog.info("请选择一条记录");
				} else {					
					$("#dlg_manager_reply").dialog("open");
					$("#fm_manager_reply").form("clear");
					$("#reply_msgId").val(row.messageId);
				}
			}
			
			function delReply() {
				var row = $("#dg_list_reply").datagrid("getSelected");
				if (row == null) {
					funtl_easyui_dialog.info("请选择一条回复记录");
				} else {
					funtl_easyui_dialog.confirm("确定要删除这条记录吗？", function() {
						var data = {
							"reply.replyId" : row.replyId
						};
						funtl_easyui_ajax.post("guestbook/Message/action/deleteReply", data, function(data) {
							if (data.message == null || data.message.length == 0) {
								$("#dg_list_reply").datagrid("reload");
							    var cReply = $("#reply_content");
							    cReply.panel({content:" "});
								funtl_easyui_dialog.info("数据已删除");
							} else {
								funtl_easyui_dialog.info(data.message);
							}
						});
					});
				}
			}
			
			function delReplyAll() {
				var row = $("#dg_list").datagrid("getSelected");
				if (row == null) {
					funtl_easyui_dialog.info("请选择一条留言记录");
				} else {
					funtl_easyui_dialog.confirm("<b style='color:#f00'>警告！</b>这将删除该留言的所有回复！", function() {
						var data = {
							"reply.message.messageId" : row.messageId
						};
						funtl_easyui_ajax.post("guestbook/Message/action/deleteReplyByMsgId", data, function(data) {
							if (data.message == null || data.message.length == 0) {
								$("#dg_list_reply").datagrid("reload");
							    var cReply = $("#reply_content");
							    cReply.panel({content:" "});
								funtl_easyui_dialog.info("数据已删除");
							} else {
								funtl_easyui_dialog.info(data.message);
							}
						});
					});
				}
			}
			
			var dlgManagerReplyBtn = [{
			    text:"保存",
			    iconCls:"icon-ok",
			    handler:function() {
			    	if ($("#reply_msgId").val() != "") {
			    		$("#fm_manager_reply").attr("action", "guestbook/Message/action/insertReply");
			    	} else {
			    		
			    	}
			    	
			    	funtl_easyui_form.submit("fm_manager_reply", function(data) {
						if (data.message == null || data.message.length == 0) {
							$("#fm_manager_reply").form("clear");
							$("#dlg_manager_reply").dialog("close");
							$("#dg_list_reply").datagrid("reload");
							funtl_easyui_dialog.info("已回复，数据已保存");
						} else {
							funtl_easyui_dialog.info(data.message);
						}
					});
			    }
			},{
			    text:"取消",
			    iconCls:"icon-cancel",
			    handler:function() {
			    	$("#dlg_manager_reply").dialog("close");
			    }
			}];
		</script>
		<title><%=System.getProperty("systemName") %></title>
	</head>
	
	<body class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',title:'留言列表'" style="width:800px;">
			<table id="dg_list" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true,pagination:true,pageSize:10,url:'guestbook/Message/action/query',toolbar:'#dg_list_toolbar',onLoadError:funtl_easyui_ajax.onLoadError">
				<thead>
		  			<tr>
		  				<th data-options="field:'messageTitle'">留言标题</th>
		  				<th data-options="field:'messageNiki'">留言者昵称</th>
		  				<th data-options="field:'messageEmail'">留言者邮箱</th>
		  				<th data-options="field:'messageCompany'">留言者公司</th>
		  				<th data-options="field:'messageAddress'">留言者地址</th>
		  				<th data-options="field:'messagePhone'">留言者电话</th>
		  				<th data-options="field:'messageFax'">留言者传真</th>
		  				<th data-options="field:'messageCreatedate', formatter:funtl_easyui_formatter.datetime">留言创建时间</th>
		  			</tr>
	  			</thead>
			</table>
			<div id="msg_content" class="easyui-panel" title="留言内容" 
		    	data-options="fit:true,collapsible:true" 
		    	style="font-size:16px;padding:10px;width:800px;word-wrap:break-word;">			    	
		    </div>
			<div id="dg_list_toolbar" style="padding:5px;height:auto">
		   		<div style="margin-bottom:5px">
			  		<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add();">新增留言</a>
			  		<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del();">删除留言</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit();">编辑留言</a>
				</div>
		   	</div>
			<script>
		   		$("#dg_list").height($(document).height() * 0.6);
		   	</script>
	   	</div>
	   	
	   	<div class="easyui-panel" data-options="region:'center',title:'回复列表',collapsible:true">
	   		<table id="dg_list_reply" class="easyui-datagrid" 
				data-options="
					rownumbers:true,
					singleSelect:true,
					pagination:true,
					pageSize:10,					
					toolbar:'#dg_list_toolbar1',
					onLoadError:funtl_easyui_ajax.onLoadError
				">
				<thead>
		  			<tr>
		  				<th data-options="field:'replyCreatename'">回复者</th>
		  				<th data-options="field:'replyCreatedate', formatter:funtl_easyui_formatter.datetime">回复时间</th>
		  				<th data-options="field:'replyContent'">回复内容</th>
		  				<div data-options="field:'replyContent'"></div>		  					  				
		  			</tr>
	  			</thead>
			</table>
			<div id="reply_content" class="easyui-panel" title="回复内容" 
		    	data-options="fit:true,collapsible:true" 
		    	style="font-size:16px;padding:10px;word-wrap:break-word;">			    	
		    </div>
			<div id="dg_list_toolbar1" style="padding:5px;height:auto">
		   		<div style="margin-bottom:5px">
		   			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addReply();">回复</a>
			  		<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="delReply();">删除</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="delReplyAll();">删除全部</a>
				</div>
		   	</div>
		   	<script>
		   		$("#dg_list_reply").height($(document).height() * 0.6);
		   	</script>
	   	</div>	   	
	   	
	   	<!-- 增加留言的窗口界面 -->
	   	<div id="dlg_manager" class="easyui-dialog" style="width:800px;height:auto;padding:10px" data-options="title:'管理',buttons:dlgManagerBtn,modal:true,closed:true,maximizable:true">
	   		<form id="fm_manager" method="post" action="">
	   			<input id="message_messageId" type="hidden" name="message.messageId" />
	   			<table align="center" style="width:100%;">
	   				<tr>
		    			<td align="right">留言标题</td>
		    			<td><input id="message_messageTitle" class="easyui-validatebox" type="text" name="message.messageTitle" data-options="required:true" style="width:80%;"></input></td>
		    		</tr>
	   				<tr>
		    			<td align="right">留言内容</td>
		    			<td><input id="message_messageContent" class="easyui-validatebox" type="text" name="message.messageContent" data-options="required:true" style="width:80%;"></input></td>
		    		</tr>
	   				<tr>
		    			<td align="right">留言者昵称</td>
		    			<td><input id="message_messageNiki" class="easyui-validatebox" type="text" name="message.messageNiki" data-options="required:true" style="width:80%;"></input></td>
		    		</tr>
	   				<tr>
		    			<td align="right">留言者邮箱</td>
		    			<td><input id="message_messageEmail" class="easyui-validatebox" type="text" name="message.messageEmail" data-options="required:true" style="width:80%;"></input></td>
		    		</tr>
	   				<tr>
		    			<td align="right">留言者公司</td>
		    			<td><input id="message_messageCompany" type="text" name="message.messageCompany" data-options="required:true" style="width:80%;"></input></td>
		    		</tr>
	   				<tr>
		    			<td align="right">留言者地址</td>
		    			<td><input id="message_messageAddress" type="text" name="message.messageAddress" data-options="required:true" style="width:80%;"></input></td>
		    		</tr>
	   				<tr>
		    			<td align="right">留言者电话</td>
		    			<td><input id="message_messagePhone" type="text" name="message.messagePhone" data-options="required:true" style="width:80%;"></input></td>
		    		</tr>
	   				<tr>
		    			<td align="right">留言者传真</td>
		    			<td><input id="message_messageFax" type="text" name="message.messageFax" data-options="required:true" style="width:80%;"></input></td>
		    		</tr>
	   			</table>
	   		</form>
	   	</div>
	   	
	   	<!-- 回复窗口 -->
	   	<div id="dlg_manager_reply" class="easyui-dialog" style="width:500px;height:auto;padding:10px" data-options="title:'回复',buttons:dlgManagerReplyBtn,modal:true,closed:true">
		   		<form id="fm_manager_reply" method="post" action="">
		   			<input id="reply_msgId" type="hidden" name="reply.message.messageId" />
		   			<table align="center">
		   				<tr>
		   					<td align="left">回复内容</td>		   					
		   				</tr>
		   				<tr>
		   					<td colspan="5"><textarea id="reply_content" class="easyui-validatebox" name="reply.replyContent" data-options="required:true" style="width:400px;height:200px;"></textarea></td>
		   				</tr>
		   			</table>
		   		</form>
		   	</div>
	</body>
</html>