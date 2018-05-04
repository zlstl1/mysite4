<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="wrapper">
			<div id="content">
				<div id="guestbook">

					<!-- 방명록 작성 폼 -->
						<table>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name" /></td>
								<td>비밀번호</td>
								<td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input id="btnAdd" type="button" VALUE=" 확인 " /></td>
							</tr>
						</table>
						<input id="btnModal" type="button" value="삭제창">


					<ul id="guestbook-list">
						<!-- 방명록 리스트  -->

					</ul>

				</div>
				<!-- /guestbook -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
	<!-- /container -->
	
	
	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label for="modalPassword">비밀번호</label> 
					<input type="password" name="modalPassword" id="modalPassword"><br> 
					<input type="hidden" name="modalNo" value="" id="modalNo"> <br>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>

<script type="text/javascript">
	$(document).ready(function() {
		//객체 생성 후 화면에 그릴 준비가 된 상태에서 
		//리스트 요청 기능 (ajax)
		//fetchList();
		var start=1;
		var end=10;
			
		fetchList(start,end);
		start+=10;
		end+=10;
			
		$(document).scroll(function() {
			maxHeight = $(document).height();
			currentScroll = $(window).scrollTop() + $(window).height();
			
			if (maxHeight <= currentScroll) {
				fetchList(start,end);
				start+=10;
				end+=10;
			}
		})
	});
	
	$("#guestbook-list").on("click",".delete",function(){
		$("#del-pop").modal();
		var $this = $(this);
		$("#modalNo").val($this.val());
	});
	
	$("#btn_del").on("click",function(){
		var password = $("[name=modalPassword]").val();
		var no = $("[name=modalNo]").val();
		
		$.ajax({

			url : "${pageContext.request.contextPath }/api/gb/ajax_delete",
			type : "post",
			/* contentType : "application/json", */
			data : {password: password, no: no}, 

			dataType : "json",
			success : function(no) {
				if(no!=0){
					$("[id="+no+"]").remove();
				}
				$("#modalPassword").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		$("#del-pop").modal("hide");
	});
	
	$("#btnModal").on("click",function(){
		console.log("btnModal");
		$("#del-pop").modal();
	});
	
	$("#btnAdd").on("click",function(){
		/* console.log("btnAdd");
		var name = $("[name=name]").val();
		var password = $("[name=password]").val();
		var content = $("[name=content]").val(); */
		
		guestbookVo = { name : $("input[name='name']").val(),
						password : $("input[name='password']").val(),
						content : $("textarea[name='content']").val()
				
		}
		
		$.ajax({

			url : "${pageContext.request.contextPath }/api/gb/add",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(guestbookVo), 

			dataType : "json",
			success : function(guestbookVo) {
				render(guestbookVo, "up");
				$("[name=name]").val("");
				$("[name=password]").val("");
				$("[name=content]").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});

	/* function fetchList() {
		$.ajax({

			url : "${pageContext.request.contextPath }/api/gb/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"}, 

			dataType : "json",
			success : function(list) {
				console.log(list);
				for (var i = 0; i < list.length; i++) {
					render(list[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	} */
	
	function fetchList(start,end) {
		$.ajax({

			url : "${pageContext.request.contextPath }/api/gb/scrollList",
			type : "post",
			//contentType : "application/json",
			data : {start : start, end : end}, 

			dataType : "json",
			success : function(list) {
				console.log(list);
				for (var i = 0; i < list.length; i++) {
					render(list[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	function render(guestbookVo, updown) {
		var str = "";

		str += "<li id='" + guestbookVo.no + "'>";
		str += 	"<table width=510 border=1>";
		str += 		"<tr>";
		str += 			"<td>[" + guestbookVo.no + "]</td>";
		str += 			"<td>" + guestbookVo.name + "</td>";
		str +=			"<td>" + guestbookVo.reg_date + "</td>";
		str +=			"<td>"
		str +=			"<button class='delete' value='"+guestbookVo.no+"'>삭제</button>"
		str +=			"</td>";
		str += 		"</tr>";
		str += 		"<tr>";
		str += 			"<td colspan=4>";
		str += 			guestbookVo.content;
		str += 			"</td>";
		str +=		"</tr>";
		str += 	"</table>";
		str += 	"<br/>";
		str += "</li>";

		if (updown == "up") {
			$("#guestbook-list").prepend(str);
		} else if (updown == "down") {
			$("#guestbook-list").append(str);
		} else {
			console.log("오류")
		}
	}

</script>


</html>