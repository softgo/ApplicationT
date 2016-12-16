<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8" http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="../../common/taglib.jsp"%>
<%@include file="../../common/common-css.jsp"%>
<%@include file="../../common/common-js.jsp"%>
</head>

<body>

<div style="height: 100%;overflow-y: auto;">
<div id="forms" class="mt10">
   <div class="box">
     <div class="box_border">
       <div class="box_top" ><b class="pl15" >系统管理——>信息展示</b></div>
       <div class="box_center" style="overflow-y:scroll;align:center; height:75%;">
		<form action="" method="post" name="userForm" id="userForm">
			<input type="hidden" name="_id" value="${objId}"><!-- 修改时候自动添加 -->
			<table class="form_table pt15 pb15 list_table" width="100%" border="1" cellpadding="0" cellspacing="0">
                 <!-- 展示信息 -->
                			<tr>
					<td class="td_right">
						_id:
					</td>
					<td class="td_left" >
					${object._id}
					</td>
				</tr>
				<tr>
					<td class="td_right">
						title:
					</td>
					<td class="td_left" >
					${object.title}
					</td>
				</tr>
				<tr>
					<td class="td_right">
						description:
					</td>
					<td class="td_left" >
					${object.description}
					</td>
				</tr>
				<tr>
					<td class="td_right">
						by:
					</td>
					<td class="td_left" >
					${object.by}
					</td>
				</tr>
				<tr>
					<td class="td_right">
						url:
					</td>
					<td class="td_left" >
					${object.url}
					</td>
				</tr>
				<tr>
					<td class="td_right">
						tags:
					</td>
					<td class="td_left" >
					${object.tags}
					</td>
				</tr>
				<tr>
					<td class="td_right">
						likes:
					</td>
					<td class="td_left" >
					${object.likes}
					</td>
				</tr>
	     
                                   
				<tr>
					<td class="td_right">
                    </td>
                    <td class="td_left">
                      <input type="button"  class="btn btn82 btn_nochecked" value="返回" onclick="location.href='javascript:history.go(-1)'">
                    </td>
				</tr>
			</table>
		  </form>
		  </div>
          </div>
        </div>
     </div>
	</div>
</body>
</html>
