<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div id="header" class="clearfix">
			<h1><a href="">${blogVo.blogTitle}</a></h1>
			<ul class="clearfix">
				
				<c:choose>
					<c:when test="${not empty authUser}">
						<li><a class="btn_s" href="${pageContext.request.contextPath}/${id}/admin/basic">내블로그 관리</a></li>
						<li><a class="btn_s" href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>					
					</c:when>

					<c:when test="${authUser.id} == ${id}"> 
						<li><a class="btn_s" href="${pageContext.request.contextPath}/user/loginForm">로그인</a></li>
			 		</c:when>
					
					<c:otherwise> 
						<li><a class="btn_s" href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			 		</c:otherwise>
		 		</c:choose>
			</ul>
		</div>
		<!-- //header -->
		