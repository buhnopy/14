<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//boolean isValidMember = MemberDao1.getInstance().loginMember(String id, String passwd);
		
		if (isValidMember) {
	%>
			<script>
				alert("로그인되었습니다");
				location.href = "0maon.jsp";
			</script>
	<%		
		}
		else {
	%>
		<script>
			alert("아이디와 비밀번호를 확인해주세요");
			history.go(-1);
		</script>
	<%
		}
		
		
	%>
</body>
</html>