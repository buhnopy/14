<%@page import="ex.MemberDao1"%>
<%@page import="ex.MemberDto1"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
	
		MemberDto1 memberDto1 = new MemberDto1();
		memberDto1.setId(request.getParameter("id"));
		memberDto1.setPasswd(request.getParameter("passwd"));
		memberDto1.setName(request.getParameter("name"));
		
		
		boolean isFirstMember = MemberDao1.getInstance().insertMember(memberDto1);
		
		if (isFirstMember) {
	%>
			<script>
			alert("회원가입되었습니다");
			location href = "0main";
			</script>
	<%
		}
		else {
	%>
		<script>
			alert("중복되었습니다");
			history.go(-1);
			</script>
	<%
			
		}
		
	%>
	
	

	
	
	
</body>
</html>