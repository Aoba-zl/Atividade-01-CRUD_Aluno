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
      <main>
        <c:if test="${not empty saida}">
          <div class="popup" id="meuPopup"></div>
        </c:if>
        <h3>Dados Aluno</h3>
        <div>
          <label for="cpf">CPF *</label>
          <input type="number" min="0" max="99999999999" name="cpf" id="cpf" value='<c:out value="${aluno.cpf}"/>' />
          <input type="submit" value="Buscar" name="botao" />
        </div>
        <div>
          <label for="nome">Nome *</label>
          <input type="text" name="nome" id="nome" value='<c:out value="${aluno.nome}"/>' />
          <label for="nome_soc">Nome Social</label>
          <input type="text" name="nome_soc" id="nome_soc" value='<c:out value="${aluno.nome_social}"/>' />
          <label for="dt_nasc">Data Nascimento *</label>
          <input type="date" name="dt_nasc" id="dt_nasc" value='<c:out value="${aluno.dt_nasc}"/>' />
        </div>
        <div>
          <label for="email_p">E-mail Pessoal *</label>
          <input type="text" name="email_p" id="email_p" value='<c:out value="${aluno.email_pessoal}"/>' />
          <label for="email_c">E-mail Corporativo *</label>
          <input type="text" name="email_c" id="email_c" value='<c:out value="${aluno.email_corporativo}"/>' />
        </div>
        <div>
          <label for="dt_seg_grau">Data de conclusão 2º grau *</label>
          <input type="date" name="dt_seg_grau" id="dt_seg_grau" value='<c:out value="${aluno.dt_conclusao_seg_grau}"/>' />
          <label for="inst_seg_grau">Instituição de conclusão 2º grau *</label>
          <input type="text" name="inst_seg_grau" id="inst_seg_grau" value='<c:out value="${aluno.instituicao_seg_grau}"/>' />
        </div>
        <div>
          <label for="telefone">Telefone *</label>
          <input type="text" name="telefone" id="telefone">
          <input type="button" class="svg" onclick="adicionar('telefone')" value="Adicionar">
        </div>
        <div id="tabela_container" class="tabela_container">
          <table id="tabela_telefones">
            <thead>
            <th>Telefone</th>
            <th>Ação</th>
            </thead>
            <tbody>
            <c:if test="${not empty telefones}">
              <c:forEach items="${telefones}" var="tel">
                <tr id="${tel.numero}">
                  <td><c:out value="${tel.numero}"/></td>
                  <td> <a class="acao" onclick="excluir('${tel.numero}')" >Excluir</a> </td>
                </tr>
              </c:forEach>
            </c:if>
            </tbody>
          </table>
          <%-- Input para enviar a lista pro serv --%>
          <c:if test="${not empty telefones}">
            <c:forEach items="${telefones}" var="tel">
              <input id="i_${tel.numero}" type="hidden" name="telefones" value='<c:out value="${tel.numero}"/>'>
            </c:forEach>
          </c:if>
        </div>
        <h3>Matricula</h3>
        <div>
          <label for="pontuacao_vest">Pontuação Vestibular *</label>
          <input type="number" name="pontuacao_vest" id="pontuacao_vest" value="${matricula.pontuacao_vestibular}" />
          <label for="posicao_vest">Posição Vestibular *</label>
          <input type="number" name="posicao_vest" id="posicao_vest" value="${matricula.posicao_vestibular}" />
        </div>
        <div>
        </div>
        <div>
          <label for="curso">Curso</label>
          <select name="curso" id="curso">
            <c:forEach items="${cursos}" var="curso">
              <c:if test="${matricula.curso.codigo eq curso.codigo}">
                <option selected="selected" value="${curso.codigo}"><c:out value="${curso.sigla} - ${curso.nome}"/></option>
              </c:if>
              <c:if test="${matricula.curso.codigo ne curso.codigo}">
                <option value="${curso.codigo}"><c:out value="${curso.sigla} - ${curso.nome}"/></option>
              </c:if>
            </c:forEach>
          </select>
          <label for="turno">Turno</label>
          <select name="turno" id="turno">
            <option value="0">Tarde</option>
          </select>
        </div>
        <div>
          <label for="ra">RA</label>
          <input type="number" name="ra" disabled value="${matricula.ra}" />
        </div>
        <div>
          <label for="ano_i">Ano Ingresso</label>
          <input type="number" name="ano_i" disabled value="${matricula.ano_ingresso}" />
          <label for="semes_i">Semestre Ingresso</label>
          <input type="number" name="semes_i" disabled  value="${matricula.semestre_ingresso}"/>
        </div>
        <div>
          <label for="ano_l">Ano Limite</label>
          <input type="number" name="ano_l" disabled value="${matricula.ano_limite}" />
          <label for="semes_l">Semestre Limite</label>
          <input type="number" name="semes_l" disabled value="${matricula.semestre_limite}" />
        </div>
        <div>
          <%-- inputs responsaveis por puxar os dados gerados --%>
          <input type="hidden" name="ra" id="ra" disabled />
          <input type="hidden" name="ano_i" id="ano_i"/>
          <input type="hidden" name="semes_i" id="semes_i"/>
          <input type="hidden" name="ano_l" id="ano_l"/>
          <input type="hidden" name="semes_l" id="semes_l"/>
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
          <input class="esticado" type="submit" value="Cadastrar" name="botao" />
          <input class="esticado" type="submit" value="Alterar" name="botao" />
          <input class="esticado" type="submit" value="Desativar Matricula" name="botao" />
          <h1 id="aki"></h1>
        </div>
      </main>
    </form>
  </body>
  <script>
    <c:if test="${not empty erro}">
    window.onload = function () {
      alert("<c:out value="${erro}"/>");
    };
    </c:if>

    <c:if test="${not empty saida}">
    document.addEventListener('DOMContentLoaded', function() {
      var popup = document.getElementById('meuPopup');
      popup.innerHTML = '<h2><c:out value="${saida}"/></h2>';
      popup.style.display = 'block';

      setTimeout(function() {
        popup.style.display = 'none';
      }, 2500);
    });
    </c:if>

    function excluir(id) {
      var linha = document.getElementById(id)
      var input = document.getElementById('i_' + id)
      var tabela = linha.parentNode

      input.remove()

      var i = tabela.childElementCount
      if (i > 1)
        tabela.deleteRow(linha.rowIndex - 1)
    }

    function adicionar(id) {
      var inp_telefone = document.getElementById(id)
      var div_inputs = document.getElementById('tabela_container')

      var tabela = document.getElementById('tabela_telefones')
      var novaLinha = tabela.insertRow(1)
      var col1 = novaLinha.insertCell(0)
      var col2 = novaLinha.insertCell(1)

      novaLinha.id = inp_telefone.value
      col1.innerText = inp_telefone.value
      col2.innerHTML = '<a class="acao" onclick="excluir(\'' + inp_telefone.value + '\')" >Excluir</a>'

      <%-- <input id="i_${tel.numero}" type="hidden" name="telefones" value='<c:out value="${tel.numero}"/>'>  --%>
      var novo_input = document.createElement('input')
      novo_input.id = 'i_' + inp_telefone.value
      novo_input.type = 'hidden'
      novo_input.name = 'telefones'
      novo_input.value = inp_telefone.value
      div_inputs.appendChild(novo_input)
    }

  </script>
</html>
