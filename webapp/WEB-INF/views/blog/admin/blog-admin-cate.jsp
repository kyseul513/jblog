<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		
		      		<tbody id="cateList">
		      			<!-- 리스트 영역(cateList) -->
					</tbody>
					
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" id="cname" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" id="cdesc" name="desc" value=""></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	
	</div>
	<!-- //wrap -->	
</body>

<script type="text/javascript">

	//1-1. 카테고리 리스트 그리기 준비

	$(document).ready(function(){
		
		cateList();
		
	});
	
	//1-2. 카테고리 리스트 관련 정보 받아오기
	function cateList(){
		$.ajax({	
			url : "${pageContext.request.contextPath}/${authUser.id}/admin/cateList",		
			type : "post",	
		
			dataType : "json",
			success : function(cateList){	
				
			console.log(cateList);
			//console.log(cateList[0].id);	//받아온 리스트의 첫번째 데이터의 이름 출력
			
			
			for(var i=0; i<cateList.length; i++){
				render(cateList[i], 'down');		
			}	
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	
	//2-1. 카테고리추가 버튼 클릭
	$("#btnAddCate").on("click", function(){
		console.log("클릭");
		
		var name = $("#cname").val();
		var desc = $("#cdesc").val();
		
		var cateVo = {
				cateName: name,
				description: desc
		};
		
		$.ajax({
			url : "${pageContext.request.contextPath}/${authUser.id}/admin/cateInsert",		
			type : "post",
			data : cateVo,			
			
			dataType : "json",
			success : function(cateList){	
			console.log(cateList);
				
				$("#cname").val("");
				$("#cdesc").val("");
				
				render(cateList, 'up');	
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});		
	});
	
	
	//3. 삭제버튼 클릭
	//cateList 영역은 돔형성 후에 추가된 것이라 click은 부모(cateList)에게 전달하고 동작은 자녀(.btnCateDel)에게 시킴	
	$("#cateList").on("click", ".btnCateDel", function(){
		console.log("삭제버튼 클릭")
		
		var $this = $(this);
		var cateNo = $this.data("cateno");
		var postNo = $this.data("postno")
		console.log(cateNo);
		console.log(postNo);
		
		var delVo = {
			cateNo: cateNo,
			postNo: postNo
		}
		
		$.ajax({
			url : "${pageContext.request.contextPath}/${authUser.id}/admin/cateDelete",
			type : "post",
			data : delVo,
			
			dataType : "json",
			success : function(result){
				console.log(result);
				
				if(result === 'success'){
					$("#t"+cateNo).remove();
				}else{
					alert("삭제할 수 없습니다.");
				}				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
				   
		});
	});
	
	
	//1-3 / 2-2. 카테고리 리스트 그리기
	function render(cateList, updown){
		
		var str = '';
		str += '<tr id="t'+cateList.cateNo+'">';
		str += '	<td>'+cateList.cateNo+'</td>';
		str += '	<td>'+cateList.cateName+'</td>';
		str += '	<td>'+cateList.postNo+'</td>';
		str += '	<td>'+cateList.description+'</td>';
		str += '	<td class="text-center">';																	//data-내가정한이름="데이터"(소문자!)
		str += '	<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg" data-cateno='+cateList.cateNo+' data-postno='+cateList.postNo+'>';
		str += ' 	</td>';																			//삭제버튼 클릭시 data-cateno의 값 전달. 
		str += '</tr>';
			
		if(updown == 'up'){
			$("#cateList").prepend(str);
		}else if(updown == 'down'){
			$("#cateList").append(str);
		}else{
			console.log("방향오류");
		}
	};
	
</script>

</html>