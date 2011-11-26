<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>

<%@ page import="com.runchain.exercise.admin.entity.venue.VenuePicture" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>场馆图片查看、编辑</title>
	<link href="${ctx }/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/js/common/plugins/jui/themes/${themeName }/jquery-ui.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx }/js/common/plugins/nyroModal/css/nyroModal.css"/>
	<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet"/>
	
	<style type="text/css">
	.ui-icon {display: inline;}
	.noneText {color: #979797;}
	.activeNoneText {background-color: #ADD8E6}
	.nshow,.rshow {cursor: pointer;}
	.picNameDiv {margin-bottom: 5px; margin-top: 5px;}
	.nedit,.redit { background-color: #BFE1FB; padding: 10px; display: none;}
	.btns {padding-top: 20px;}
	.majorPicture {padding: 5px; background-color: yellow; margin-top: 5px;}
	</style>
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.corner.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/nyroModal/jquery.nyroModal.pack.js" type="text/javascript"></script>
	<script type="text/javascript">
	var venueId = '${param.venueId}';
	</script>
	<script src="${ctx }/js/module/venue/venue-picture.js" type="text/javascript"></script>
</head>

<body>
	<table border="0" width="100%">
	<c:forEach items="${pictures }" var="pic">
	<tr class="picContainer">
		<td class="singlePicture" width="100">
			<a href="${ctx }/venue/pictures/${param.venueId}/${pic.pictureRealName}" title="${pic.pictureName }" class="nyroModal">
				<img src="${ctx }/venue/pictures/${param.venueId}/thumbnails/120/${pic.pictureRealName}" width="100" height="100" border="0"/>
			</a>
		</td>
		<td id="${pic.id }" style="vertical-align: top;" class="{pid: ${pic.id }}">
			<div class="picNameDiv">
				<b>图片名称：</b>
				<div class="nshow" style="display: inline;">
					<c:if test="${empty pic.pictureName}"><span class="noneText">点击添加图片名称</span></c:if>
					<c:if test="${not empty pic.pictureName}"><span class="picNameCt">${pic.pictureName}</span></c:if>
					<span class="ui-icon ui-icon-pencil" style="display: inline">&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</div>
				<div class="nedit"></div>
			</div>
			<div class="picRemarkDiv">
				<b>图片描述：</b>
				<div class="rshow" style="display: inline;">
					<c:if test="${empty pic.pictureRemark}"><span class="noneText">点击添加图片描述</span></c:if>
					<c:if test="${not empty pic.pictureRemark}"><span class="picRemarkCt">${pic.pictureRemark}</span></c:if>
					<span class="ui-icon ui-icon-pencil">&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</div>
				<div class="redit"></div>
			</div>
		</td>
		<td id="${pic.id }" style="vertical-align: top;" width="220">
			<b>上传日期：</b>${pic.uploadDate }<br/>
			<b>图片大小：</b>${pic.pictureSizeShow }<br/>
			<span class="ui-icon ui-icon-trash">&nbsp;&nbsp;&nbsp;&nbsp;</span><a href="javascript:;" class="deletePic">删除此图片</a><br/>
			<div class="majorPicDiv" style="display:<c:if test="${majorPicture == pic.pictureRealName}">none</c:if>"><span class="ui-icon ui-icon-wrench">&nbsp;&nbsp;&nbsp;&nbsp;</span><a href="javascript:;" prn="${pic.pictureRealName}" class="setMajor">设置为场馆主图片</a></div>
			<c:if test="${majorPicture == pic.pictureRealName}">
				<div class="majorPicture">主图片</div>
			</c:if>
		</td>
	</tr>
	</c:forEach>
	</table>
	<c:if test="${fn:length(pictures) == 0 }">
		目前还没有上传过图片，立即<button id="uploadPicture">上传图片</button>？
	</c:if>
	<div id="picNameTemplate" class="template">
		<div class="picNameEdit" style="display: inline;">
			<input class="picNameInput" size="60" maxlength="80" /><br/>
			<span class="btns">
				<button type="button" class="picNameOk">确 定</button>
				<a href="javascript:;" class="picNameCancel">取 消</a>
			</span>
		</div>
	</div>
	
	<div id="picRemarkTemplate" class="template">
		<div class="picRemarkEdit" style="display: inline;">
			<textarea class="picRemarkInput" rows="3" cols="60"></textarea><br/>
			<span class="btns">
				<button type="button" class="picRemarkOk">确 定</button>
				<a href="javascript:;" class="picRemarkCancel">取 消</a>
			</span>
		</div>
	</div>
	
</body>
</html>