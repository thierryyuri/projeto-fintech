<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../../css/meta/cadastro-meta.css">
<link rel="stylesheet" type="text/css" href="../../css/cliente/global.css">
<title>Cadastro de Meta</title>
</head>
<body>
<%@ include file="../commom/header.jsp" %>

<div class="container">
	<h1 class="title">Cadastro de Meta</h1>
	<c:if test="${not empty msg }">
		<div class="alert alert-success">${msg}</div>
	</c:if>
	<c:if test="${not empty erro }">
		<div class="alert alert-danger">${erro}</div>
	</c:if>
	<form action="../../MetaServelet" method="post">
		<input type="hidden" value="cadastrar" name="acao">
		<div class="form-group">
			<label for="id-nome">Nome</label>
			<input type="text" name="nome" id="id-nome" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-tipo">Tipo</label>
			<input type="text" name="tipo" id="id-tipo" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-descricao">Descrição</label>
			<input type="text" name="descricao" id="id-descricao" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-valor">Valor</label>
			<input type="text" name="valor" id="id-valor" class="form-control">
		</div>
		<input type="submit" value="Salvar" class="btn btn-primary">
	</form>
</div>
    <!--"sectionUm" Significa o trecho de código que tem a frase inicial  -->
    <section class="sectionUm">
      <h2 class="title">Simplifique sua vida!</h2>
	</section>

<%@ include file="../commom/footer.jsp" %>
</body>
</html>