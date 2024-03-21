<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../../css/cliente/login.css">
    <link rel="stylesheet" type="text/css" href="../../css/global.css">
    <title>Fintech One. Simplifica a vida</title>

</head>
<body>
  	<!-- Aqui estou estilizando o header -->
  	<header>
      <a href="#" class="logo">Fintech One.</a>
      <ul class="links">
        <li><a href="#">SOBRE</a></li>
        <li><a href="#">CONTATO</a></li>
      </ul>
  	</header>
  	
    <!--"sectionUm" Significa o trecho de código que tem a frase inicial  -->
    <section class="sectionUm">
      <h2 class="title">Acesse sua conta!</h2>
      <h3 class="title">É prático e rápido, simplifique suas finanças 
        <strong class="titulo-destaque"><br> com a Fintech One.</strong>
      </h3>
    </section> 
    <!-- Container, parte importante de código do Messias, favor não APAGAR! -->
    <div class="container">
        <h1 class="loginText">Área do Cliente</h1>
        <c:if test="${not empty msg}">
            <div class="alert alert-success">${msg}</div>
        </c:if>
        <c:if test="${not empty erro}">
            <div class="alert alert-danger">${erro}</div>
        </c:if>
        <!--<form action="../ClienteServelet" method="post" accept-charset="UTF-8">--->
        <form action="cadastrarCliente" method="post" accept-charset="UTF-8">
       
            <input type="hidden" value="login" name="acao">
            <div class="form-group">
                <label for="id-cpf">Login(CPF):</label>
                <input type="text" name="cpf" id="id-cpf" class="form-control">
            </div>
            <div class="form-group">
                <label for="id-senha">Senha:</label>
                <input type="text" name="senha" id="id-senha" class="form-control">
            </div>
            <input type="submit" value="Salvar" class="btn btn-primary">
        </form>
    </div>
   
    
</body>
</html>