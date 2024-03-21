<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../../css/cliente/dados-cliente.css">
<link rel="stylesheet" type="text/css" href="../../css/cliente/cliente.css">
<link rel="stylesheet" type="text/css" href="">
<title>Dados do cliente</title>
</head>
<%@ include file="../commom/header.jsp" %>
<body>
	<div class="container">
		<h1>Cliente</h1>
		<table class="table table-striped">
			<tr>
				<th>Nome</th>
				<th>Cpf</th>
				<th>Endereco</th>
				<th>Telefone</th>
				<th>Email</th>
				<th>Data de nascimento</th>
			</tr>
			<c:if test="${not empty cliente}">
    <table>
        <tr>
            <th>Nome</th>
            <th>CPF</th>
            <th>Endereço</th>
            <th>Telefone</th>
            <th>Email</th>
            <th>Data de Nascimento</th>
        </tr>
        <tr>
            <td>${cliente.nome}</td>
            <td>${cliente.cpf}</td>
            <td>${cliente.endereco}</td>
            <td>${cliente.telefone}</td>
            <td>${cliente.email}</td>
            <td>
                <fmt:formatDate value="${cliente.dataNascimento.time}" pattern="dd/MM/yyyy"/>
            </td>
            <td>
				<c:url value="ClienteServelet" var="link">
					<c:param name="acao" value="abrir-form-edicao"/>
					<c:param name="idCliente" value="${cliente.getIdCliente()}"/>
				</c:url>
				<a href="${link}">Editar</a>
			</td>
        </tr>
    </table>
</c:if>

		</table>
	</div>
	
<%@ include file="../commom/footer.jsp" %>
</body>
</html>