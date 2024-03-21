<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../../css/cliente/cadastro-usuario.css">
	<link rel="stylesheet" type="text/css" href="../../css/cliente/global.css">
	<title>Cadastro de usuário</title>
</head>
<body>
	<!-- Aqui estou criando o header -->
  	<header>
      <a href="#" class="logo">Fintech One.</a>
      <ul class="links">
        <li><a href="#">SOBRE</a></li>
        <li><a href="#">CONTATO</a></li>
      </ul>
  	</header>
  	
    <!--"sectionUm" Significa o trecho de código que tem a frase inicial  -->
    <section class="sectionUm">
      <h2 class="title">Simplifique sua vida!</h2>
	</section>

<div class="container">
	<h1 class="loginText">Cadastro de Cliente</h1>
	<c:if test="${not empty msg }">
		<div class="alert alert-success">${msg}</div>
	</c:if>
	<c:if test="${not empty erro }">
		<div class="alert alert-danger">${erro}</div>
	</c:if>
	<form action="../../ClienteServelet" method="post">
		<input type="hidden" value="cadastrar" name="acao">
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
	</form>
</div>
</body>
</html>