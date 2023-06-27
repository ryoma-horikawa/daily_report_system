<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:if test="${errors != null }">
    <div id="flush_error">
        入力内容にエラーがあります。<br/>
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}"/><br/>
        </c:forEach>
    </div>
</c:if>

<label for="${AttributeConst.CLI_NAME.getValue()}">会社名</label><br/>
<input type="text" name="${AttributeConst.CLI_NAME.getValue()}" id="${AttributeConst.CLI_NAME.getValue()}" value="${client.name}"/>
<br /><br />

<label for="${AttributeConst.CLI_INFO.getValue()}">会社情報</label><br/>
<textarea name="${AttributeConst.CLI_INFO.getValue()}" id="${AttributeConst.CLI_INFO.getValue()}" rows="5" cols="30">${client.information}</textarea>
<br/><br/>

<label for="${AttributeConst.CLI_ADDRESS.getValue()}">住所</label><br/>
<input type="text" name="${AttributeConst.CLI_ADDRESS.getValue()}" id="${AttributeConst.CLI_ADDRESS.getValue()}" value="${client.address}"/>
<br /><br />

<label for="${AttributeConst.CLI_PHONE.getValue()}">電話番号</label><br/>
<input type="text" name="${AttributeConst.CLI_PHONE.getValue()}" id="${AttributeConst.CLI_PHONE.getValue()}" value="${client.phone}"/>
<br /><br />

<label for="${AttributeConst.CLI_EMAIL.getValue()}">メールアドレス</label><br/>
<input type="text" name="${AttributeConst.CLI_EMAIL.getValue()}" id="${AttributeConst.CLI_EMAIL.getValue()}" value="${client.email}"/>
<br /><br />

<label for="${AttributeConst.CLI_CUSTOMER.getValue()}">お客様担当者</label><br/>
<input type="text" name="${AttributeConst.CLI_CUSTOMER.getValue()}" id="${AttributeConst.CLI_CUSTOMER.getValue()}" value="${client.customer}"/>
<br /><br />

<label for="${AttributeConst.CLI_MANAGER.getValue()}">社内担当者</label><br/>
<input type="text" name="${AttributeConst.CLI_MANAGER.getValue()}" id="${AttributeConst.CLI_MANAGER.getValue()}" value="${client.manager}"/>
<br /><br />


<input type="hidden" name="${AttributeConst.CLI_ID.getValue()}" value="${client.id}"/>
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}"/>
<button type="submit">投稿</button>




