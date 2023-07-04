<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<div class="container mt-3">
    <table class="table">
        <thead>
        <tr>
            <th style="width:20%">Tên sản phẩm</th>
            <th style="width:20%">Nhóm sản phẩm</th>
            <th style="width:20%">Giá</th>
            <th style="width:20%">Ngày tạo</th>
            <th style="width:20%"></th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${productList.size() == 0}">
            <tr>
                <td colspan="4">Chưa có sản phẩm</td>
            </tr>
        </c:if>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td>${product.name}</td>
                <td>${product.category.name}</td>
                <td>${product.price}</td>
                <td>${product.createDate}</td>
                <td>
                    <a href="/admin/product/edit/${product.id}" class="btn btn-sm btn-secondary">Chỉnh sửa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/admin/product/create" class="btn btn-primary btn-sm">Thêm sản phẩm</a>
</div>