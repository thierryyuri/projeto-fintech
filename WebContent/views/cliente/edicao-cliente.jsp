<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/cliente/edicao-cliente.css">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/cliente/cliente.css">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/global.css">
<title>Edição de usuário</title>
</head>
<body>
<div class="container">
<%@ include file="../commom/header.jsp" %>

	<h1>Edição de Cliente</h1>
	<c:if test="${not empty msg }">
		<div class="alert alert-success">${msg}</div>
	</c:if>
	<c:if test="${not empty erro }">
		<div class="alert alert-danger">${erro}</div>
	</c:if>
	<form action="ClienteServelet" method="post">
		<input type="hidden" value="editar" name="acao">
		<input type="hidden" value="${cliente.idCliente}" name="idCliente">
		<div class="form-group">
			<label for="id-nome">Nome</label>
			<input type="text" name="nome" id="id-nome" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-cpf">CPF</label>
			<input type="text" name="cpf" id="id-cpf" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-endereco">Endereço</label>
			<input type="text" name="endereco" id="id-endereco" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-email">Email</label>
			<input type="text" name="email" id="id-email" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-telefone">Telefone</label>
			<input type="text" name="telefone" id="id-telefone" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-data">Data de Nascimento</label>
			<input type="text" name="dataNascimento" id="id-data" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-senha">Senha</label>
			<input type="text" name="senha" id="id-senha" class="form-control">
		</div>
		<input type="submit" value="Salvar" class="btn btn-primary">
		<a href="ClienteServelet?acao=listar" class="btn btn-danger">Cancelar</a>
	</form>
</div>

<%@ include file="../commom/footer.jsp" %>
</body>
</html>
