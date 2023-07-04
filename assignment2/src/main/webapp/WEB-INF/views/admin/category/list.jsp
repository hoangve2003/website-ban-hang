<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<div class="container mt-3">
    <table class="table">
        <thead>
        <tr>
            <th style="width:30%">Id</th>
            <th style="width:30%">Nhóm sản phẩm</th>
            <th style="width:20%"></th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${categories.size() == 0}">
            <tr>
                <td colspan="4">Chưa có nhóm sản phẩm</td>
            </tr>
        </c:if>
        <c:forEach var="categories" items="${categories}">
            <tr>
                <td>${categories.id}</td>
                <td>${categories.name}</td>
                <td>
                    <a href="/admin/category/edit/${categories.id}" class="btn btn-sm btn-secondary">Chỉnh sửa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/admin/category/create" class="btn btn-primary btn-sm">Thêm nhóm sản phẩm</a>
</div>