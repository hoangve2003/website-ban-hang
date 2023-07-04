<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<div class="container mt-3">
    <table class="table">
        <thead>
        <tr>
            <th style="width:10%">Tên tài khoản</th>
            <th style="width:10%">Mật khẩu</th>
            <th style="width:20%">Tên người dùng</th>
            <th style="width:20%">Email</th>
            <th style="width:10%">Trạng thái</th>
            <th style="width:10%">Vai trò</th>
            <th style="width:10%"></th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${accounts.size() == 0}">
            <tr>
                <td colspan="4">Chưa có tài khoản</td>
            </tr>
        </c:if>
        <c:forEach var="accounts" items="${accounts}">
            <tr>
                <td>${accounts.username}</td>
                <td>${accounts.password}</td>
                <td>${accounts.fullname}</td>
                <td>${accounts.email}</td>
                <td>
                        ${accounts.activated == false?'Không hoạt đông':'Hoạt động'}
                </td>
                <td>${accounts.admin == false?'admin':'user'}</td>
                <td>
                    <a href="/admin/account/edit/${accounts.username}" class="btn btn-sm btn-secondary">Chỉnh sửa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/admin/account/create" class="btn btn-primary btn-sm">Thêm tài khoản</a>
</div>