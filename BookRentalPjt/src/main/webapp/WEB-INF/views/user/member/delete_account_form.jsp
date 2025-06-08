<%@page import="com.office.library.user.member.UserMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/user/delete_account_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/delete_account_form_js.jsp" />

</head>
<body>

<jsp:include page="../../include/header.jsp" />
	
<jsp:include page="../include/nav.jsp" />

<section>
    <div id="section_wrap">

        <div class="word">
            <h3>DELETE ACCOUNT FORM</h3>
        </div>

        <div class="delete_account_form">
            <form action="<c:url value='/user/member/deleteAccountConfirm' />" method="post" name="delete_account_form">

                <input type="hidden" name="u_m_no" value="${loginedUserMemberVo.u_m_no}">

                <!-- 로그인된 아이디는 보여주기용으로 readonly -->
                <input type="text" name="u_m_id" value="${loginedUserMemberVo.u_m_id}" readonly> <br>
                <input type="password" name="u_m_pw" placeholder="INPUT USER PASSWORD." required> <br>

                <input type="button" value="delete account" onclick="deleteAccountForm();">
                <input type="reset" value="reset">

            </form>
        </div>

    </div>
</section>

<jsp:include page="../../include/footer.jsp" />

</body>
</html>
