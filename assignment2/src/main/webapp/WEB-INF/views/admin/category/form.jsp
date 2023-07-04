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
    <%--@elvariable id="category" type=""--%>
    <form:form action="/admin/category/add" method="post" modelAttribute="category" enctype="multipart/form-data">
        <h3>Thông tin nhóm sản phẩm</h3>
        <div class="row">
            <div class="col-4">Id:</div>
            <div class="col-8">
                <form:input class="form-control" placeholder="Id" path="id"/>
                <form:errors path="id" cssClass="error-message" element="div"></form:errors>
            </div>
        </div>
        <div class="row my-2">
            <div class="col-4">Nhóm sản phẩm:</div>
            <div class="col-8">
                <form:input class="form-control" placeholder="Nhóm sản phẩm" path="name"/>
                <form:errors path="name" cssClass="error-message" element="div"></form:errors>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Lưu lại</button>
    </form:form>
</div>