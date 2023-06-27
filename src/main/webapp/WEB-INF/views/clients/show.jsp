<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actCli" value="${ForwardConst.ACT_CLI.getValue()}"/>
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}"/>
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}"/>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name ="content">

        <h2>顧客 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>会社名</th>
                    <td><pre><c:out value="${client.name }"/></pre></td>
                </tr>
                <tr>
                    <th>会社情報</th>
                    <td><pre><c:out value="${client.information }"/></pre></td>
                </tr>
                <tr>
                    <th>住所</th>
                    <td><pre><c:out value="${client.address }"/></pre></td>
                </tr>
                <tr>
                    <th>電話番号</th>
                    <td><pre><c:out value="${client.phone }"/></pre></td>
                </tr>
                <tr>
                    <th>メールアドレス</th>
                    <td><pre><c:out value="${client.email }"/></pre></td>
                </tr>
                <tr>
                    <th>お客様担当者</th>
                    <td><pre><c:out value="${client.customer }"/></pre></td>
                </tr>
                <tr>
                    <th>社内担当者</th>
                    <td><pre><c:out value="${client.manager }"/></pre></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${client.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${client.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
            </tbody>
        </table>

        <c:if test="${sessionScope.login_employee.id == client.employee.id}">
            <p>
                <a href="<c:url value='?action=${actCli}&command=${commEdt}&id=${client.id}'/>">この会社情報を編集する</a>
            </p>
        </c:if>

        <p>
            <a href="<c:url value='?action=${actCli}&command=${commIdx}'/>"></a>
        </p>
    </c:param>
</c:import>














