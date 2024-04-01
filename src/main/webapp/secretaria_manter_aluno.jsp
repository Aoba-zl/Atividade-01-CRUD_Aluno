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
      <main <%--class="wrap_fomr"--%>>
        <h3>Dados Aluno</h3>
        <div>
          <label for="cpf">CPF *</label>
          <input type="number" name="cpf" id="cpf" />
          <input type="submit" value="Buscar" name="botao" />
        </div>
        <div>
          <label for="nome">Nome *</label>
          <input type="text" name="nome" id="nome" />
          <label for="nome_soc">Nome Social</label>
          <input type="text" name="nome_soc" id="nome_soc" />
          <label for="dt_nasc">Data Nascimento *</label>
          <input type="date" name="dt_nasc" id="dt_nasc" />
        </div>
        <div>
          <label for="email_p">E-mail Pessoal *</label>
          <input type="text" name="email_p" id="email_p" />
          <label for="email_c">E-mail Corporativo *</label>
          <input type="text" name="email_c" id="email_c" />
        </div>
        <div>
          <label for="dt_seg_grau">Data de conclusão 2º grau *</label>
          <input type="date" name="dt_seg_grau" id="dt_seg_grau" />
          <label for="inst_seg_grau">Instituição de conclusão 2º grau *</label>
          <input type="text" name="inst_seg_grau" id="inst_seg_grau" />
        </div>
        <div>
          <label for="telefone">Telefone</label>
          <input type="text" name="telefone" id="telefone">
          <input type="submit" value="Adicionar">
        </div>
        <div class="tabela_container">
          <table>
            <thead>
            <th>Telefone</th>
            <th>Ação</th>
            </thead>
            <tbody>
            <%-- TODO: Tabela de numeros --%>
            <tr>
              <td>11912341234</td>
              <td>
                <p class="acao"><a>Excluir</a></p>
                <p class="acao"><a>Alterar</a></p>
              </td>
            </tr>
            <%-- TODO: Tabela de numeros --%>
            </tbody>
          </table>
        </div>
        <h3>Matricula</h3>
        <div>
          <label for="pontuacao_vest">Pontuação Vestibular</label>
          <input type="text" name="pontuacao_vest" id="pontuacao_vest" />
          <label for="posicao_vest">Posição Vestibular</label>
          <input type="text" name="posicao_vest" id="posicao_vest" />
        </div>
        <div>
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
          <input type="date" name="ano_i" disabled value="${placeholder}" />
          <label for="semes_i">Semestre Ingresso</label>
          <input type="date" name="semes_i" disabled  value="${placeholder}"/>
          <label for="ano_l">Ano Limite</label>
          <input type="date" name="ano_l" disabled value="${placeholder}" />
          <label for="semes_l">Semestre Limite</label>
          <input type="date" name="semes_l" disabled value="${placeholder}" />
        </div>
        <div>
          <%-- inputs responsaveis por puxar os dados gerados --%>
          <%-- TODO: Gerar dados de matricula --%>
          <input type="hidden" name="ano_i" id="ano_i"/>
          <input type="hidden" name="semes_i" id="semes_i"/>
          <input type="hidden" name="ano_l" id="ano_l"/>
          <input type="hidden" name="semes_l" id="semes_l"/>
            <%-- TODO: Gerar dados de matricula --%>
        </div>
        <c:if test="${not empty saida}">
          <h3><c:out value="${saida}"/></h3>
        </c:if>
        <c:if test="${not empty erro}">
          <h3 class="erro"><c:out value="${erro}"/></h3>
        </c:if>
      </main>
      <main>
        <div>
          <input type="submit" value="Cadastrar" name="botao" />
          <input type="submit" value="Alterar" name="botao" />
          <input type="submit" value="Desativar Matricula" name="botao" />
        </div>
      </main>
    </form>
  </body>
  <script>
    <c:if test="${not empty erro}">
    window.onload = function() {
      alert("<c:out value="${erro}"/>");
    };
    </c:if>
  </script>
</html>
