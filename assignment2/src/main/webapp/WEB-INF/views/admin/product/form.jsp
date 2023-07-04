<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<style>
    .error-message {
        color: red;
        font-size: 14px;
    }
</style>
<div class="container mt-3">
    <%--@elvariable id="product" type=""--%>
    <form:form action="/admin/product/add" method="post" modelAttribute="product" enctype="multipart/form-data">
        <h3>Thông tin sản phẩm</h3>
        <form:input type="hidden" path="id"/>
        <div class="row">
            <div class="col-4">Tên sản phẩm:</div>
            <div class="col-8">
                <form:input class="form-control" placeholder="Tên sản phẩm" path="name"/>
                <form:errors path="name" element="div" cssClass="error-message"></form:errors>
            </div>
        </div>
        <div class="row mt-2">
            <div class="col-4">Nhóm sản phẩm:</div>
            <div class="col-8">
                <form:select class="form-control" path="category.id">
                    <option disabled selected>----</option>
                    <form:options items="${categoryList}" itemValue="id" itemLabel="name"></form:options>
                </form:select>
                <form:errors path="category" element="div" cssClass="error-message"></form:errors>
            </div>
        </div>
        <div class="row mt-2">
            <div class="col-4">Giá sản phẩm:</div>
            <div class="col-8">
                <form:input class="form-control" type="number" min="0" placeholder="Giá sản phẩm" path="price"/>
                <form:errors path="price" element="div" cssClass="error-message"></form:errors>
            </div>
        </div>
        <div class="row mt-2">
            <div class="col-4">Ngày tạo:</div>
            <div class="col-8">
                <form:input class="form-control" type="date" path="createDate"/>
                <form:errors path="createDate" element="div" cssClass="error-message"></form:errors>
            </div>
        </div>
        <a href="/admin/product/index" class="btn btn-secondary">Quay lại</a>
        <button class="btn btn-primary" type="submit">Lưu lại</button>
    </form:form>
</div>