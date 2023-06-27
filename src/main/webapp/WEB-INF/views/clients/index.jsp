<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actCli" value="${ForwardConst.ACT_CLI.getValue()}"/>
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}"/>
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}"/>
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}"/>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null }">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>お客様一覧</h2>
        <table id="client_list">
            <tbody>
                <tr>
                    <th class="client_name">会社名</th>
                    <th class="client_information">会社情報</th>
                    <th class="client_action">操作</th>
                </tr>
                <c:forEach var="client" items="${clients}" varStatus="status">

                    <tr>
                        <td class="client_name">${client.name}</td>
                        <td class="client_information">${client.information}</td>
                        <td class="client_action"><a href="<c:url value='?action=${actCli}&command=${commShow}&id=${client.id }'/>">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            (全 ${clients_count} 件)<br/>
            <c:forEach var="i" begin="1" end="${((clients_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}"/>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<:url value='?action=${actCli}&command=${commIdx}&page=${i}'/>"><c:out value="${i}"/></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actCli}&command=${commNew}'/>">新規顧客の登録</a></p>

    </c:param>
</c:import>

















