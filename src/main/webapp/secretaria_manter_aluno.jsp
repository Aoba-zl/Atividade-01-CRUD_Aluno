<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Manter Aluno</title>
    <link rel="stylesheet" href="css/style.css" />
  </head>
  <body>
    <div>
      <jsp:include page="menu.jsp"/>
    </div>
    <h2 class="titulo">Manter Aluno</h2>
    <form action="manter_aluno" method="post">
      <main class="wrap_fomr">
        <h3>Dados Aluno</h3>
        <div>
          <label for="cpf">CPF *</label>
          <input type="number" name="cpf" id="cpf" />
          <input type="submit" value="Buscar" name="botao" />
        </div>
        <div>
          <label for="nome">Nome *</label>
          <input type="text" name="nome" id="nome" />
        </div>
        <div>
          <label for="nome_soc">Nome Social</label>
          <input type="text" name="nome_soc" id="nome_soc" />
        </div>
        <div>
          <label for="dt_nasc">Data Nascimento *</label>
          <input type="date" name="dt_nasc" id="dt_nasc" />
        </div>
        <div>
          <label for="email_p">E-mail Pessoal *</label>
          <input type="text" name="email_p" id="email_p" />
        </div>
        <div>
          <label for="email_c">E-mail Corporativo *</label>
          <input type="text" name="email_c" id="email_c" />
        </div>
        <div>
          <label for="dt_seg_grau">Data de conclusão 2º grau *</label>
          <input type="date" name="dt_seg_grau" id="dt_seg_grau" />
        </div>
        <div>
          <label for="inst_seg_grau">Instituição de conclusão 2º grau *</label>
          <input type="text" name="inst_seg_grau" id="inst_seg_grau" />
        </div>
      </main>
      <main class="wrap_fomr">
        <h3>Matricula</h3>
        <div>
          <label for="pontuacao_vest">Pontuação Vestibular</label>
          <input type="text" name="pontuacao_vest" id="pontuacao_vest" />
        </div>
        <div>
          <label for="posicao_vest">Posição Vestibular</label>
          <input type="text" name="posicao_vest" id="posicao_vest" />
        </div>
        <div>
          <label for="curso">Curso</label>
          <select name="curso" id="curso"></select>
        </div>
        <div>
          <label for="ra">RA</label>
          <input type="number" name="ra" id="ra" disabled />
        </div>
        <div>
          <label for="ano_i">Ano Ingresso</label>
          <input type="date" name="ano_i" id="ano_i" disabled />
        </div>
        <div>
          <label for="semes_i">Semestre Ingresso</label>
          <input type="date" name="semes_i" id="semes_i" disabled />
        </div>
        <div>
          <label for="ano_l">Ano Limite</label>
          <input type="date" name="ano_l" id="ano_l" disabled />
        </div>
        <div>
          <label for="semes_l">Semestre Limite</label>
          <input type="date" name="semes_l" id="semes_l" disabled />
        </div>
      </main>
      <main>
        <div>
          <input type="submit" value="Cadastrar" name="botao" />
          <input type="submit" value="Alterar" name="botao" />
        </div>
      </main>
    </form>
  </body>
</html>

<!-- https://mfyg5g.csb.app/secretaria/manter_aluno.html -->
