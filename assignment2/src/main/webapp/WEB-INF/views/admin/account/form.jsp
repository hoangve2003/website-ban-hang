<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
    .error-message {
        color: red;
        font-size: 14px;
    }
</style>
<div class="container mt-3">
    <%--@elvariable id="account" type=""--%>
    <form:form action="/admin/account/add" method="post" modelAttribute="account" enctype="multipart/form-data">
        <h3>Thông tin tài khoản</h3>
        <div class="row">
            <div class="col-4">Username:</div>
            <div class="col-8">
                <form:input class="form-control" placeholder="User name" path="username"/>
                <form:errors path="username" cssClass="error-message" element="div"></form:errors>

            </div>
        </div>
        <div class="row my-2">
            <div class="col-4">Mật khẩu:</div>
            <div class="col-8">
                <form:input class="form-control" placeholder="Mật khẩu" path="password"/>
                <form:errors path="password" cssClass="error-message" element="div"></form:errors>

            </div>
        </div>
        <div class="row my-2">
            <div class="col-4">Họ và tên:</div>
            <div class="col-8">
                <form:input class="form-control" placeholder="Họ và tên" path="fullname"/>
                <form:errors path="fullname" cssClass="error-message" element="div"></form:errors>

            </div>
        </div>
        <div class="row my-2">
            <div class="col-4">Email:</div>
            <div class="col-8">
                <form:input class="form-control" placeholder="Email" path="email"/>
                <form:errors path="email" cssClass="error-message" element="div"></form:errors>

            </div>
        </div>
        <button class="btn btn-primary" type="submit">Lưu lại</button>
    </form:form>
</div>