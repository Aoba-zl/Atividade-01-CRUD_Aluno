<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Realizar Matricula</title>
    <link rel="stylesheet" href="css/style.css" />
  </head>
  <body>
    <div>
      <jsp:include page="menu.jsp"/>
    </div>
    <main>
      <form action="realizar_matricula" method="post">
        <div>
          <label for="ra">RA</label>
          <input type="number" name="ra" id="ra"
                 value="${matricula.ra}"
          />
          <input type="submit" name="botao" value="Buscar">
        </div>
        <div>
<%--          <table class="tabela_matricula">--%>
<%--            <thead>--%>
<%--              <th>Código Disciplina</th>--%>
<%--              <th>Disciplina</th>--%>
<%--              <th>Código Horário</th>--%>
<%--              <th>Horário</th>--%>
<%--              <th>Dia</th>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--              <tr id="linha_matricula">--%>
<%--                <td name="cod_disc" id="cod_disc"><c:out value="${ cod_disc }"/></td>--%>
<%--                <td name="nome_disc" id="nome_disc"><c:out value="${ nome_disc }"/></td>--%>
<%--                <td name="cod_hoario" id="cod_hoario"><c:out value="${ cod_hoario }"/></td>--%>
<%--                <td name="horario" id="horario"><c:out value="${ horario }"/></td>--%>
<%--                <td name="dia" id="dia"><c:out value="${ dia }"/></td>--%>
<%--              </tr>--%>
<%--            </tbody>--%>
<%--          </table>--%>
  <%-- TODO: --%>
          <input disabled id="i_cod_disc" type="text" placeholder="Código Disciplina" class="dado_matricula" value="${ cod_disc }">
          <input disabled id="i_nome_disc" type="text" placeholder="Nome Disciplina" class="dado_matricula" value="${ nome_disc }">
          <input disabled id="i_cod_hoario" type="text" placeholder="Código Horário" class="dado_matricula" value="${ cod_hoario }">
          <input disabled id="i_horario" type="text" placeholder="Horário" class="dado_matricula" value="${ horario }">
          <input disabled id="i_dia" type="text" placeholder="Dia da Semana" class="dado_matricula" value="${ dia }">
          <input type="hidden" id="cod_disc" name="cod_disc" value="${ cod_disc }">
          <input type="hidden" id="nome_disc" name="nome_disc" value="${ nome_disc }">
          <input type="hidden" id="cod_hoario" name="cod_hoario" value="${ cod_hoario }">
          <input type="hidden" id="horario" name="horario" value="${ horario }">
          <input type="hidden" id="dia" name="dia" value="${ dia }">
        </div>
          <div>
            <label for="dia_semana">Dia da Semana</label>
            <select name="dia_semana" id="dia_semana">
              <option value="2">Segunda-Feira</option>
              <option value="3">Terça-Feira</option>
              <option value="4">Quarta-Feira</option>
              <option value="5">Quinta-Feira</option>
              <option value="6">Sexta-Feira</option>
              <option value="7">Sábado</option>
            </select>
            <input
                    type="submit"
                    name="botao"
                    value="Realizar Matricula"
                    <c:if test="${ empty matricula_valida }"> disabled </c:if>
            />
          </div>
        <div>
          <input
                  type="submit"
                  value="Listar Disciplinas Disponiveis"
                  name="botao"
                  class="esticado"
                  <c:if test="${ empty busca_valida }"> disabled </c:if>
          />
          <input
                  type="submit"
                  value="Listar Disciplinas Matriculadas"
                  name="botao"
                  class="esticado"
                  <c:if test="${ empty busca_valida }"> disabled </c:if>
          />
          <input
                  type="submit"
                  value="Listar Horários Disponiveis"
                  name="botao"
                  class="esticado"
                  <c:if test="${ empty busca_valida }"> disabled </c:if>
          />
        </div>
        <div>
          <table class="tabela_container">
            <thead>
              <th>Código</th>
              <th>Nome</th>
              <th>Situação</th>
              <th>Ação</th>
            </thead>
            <tbody>
              <c:if test="${ not empty matriculasDisciplina }">
                <c:forEach items="${matriculasDisciplina}" var="md">
                  <tr id="${md.disciplina.codigo}">
                    <td><c:out value="${md.disciplina.codigo}"/></td>
                    <td><c:out value="${md.disciplina.nome}"/></td>
                    <td><c:out value="${md.situacao}"/></td>
                    <td>
                      <a class="acao" onclick="selecionar_disciplina('${md.disciplina.codigo}')">
                        <c:if test="${ acao eq 'SELECIONAR' }"> Selecionar </c:if>
                        <c:if test="${ acao eq 'ALTERAR' }"> Alterar </c:if>
                      </a>
                    </td>
                  </tr>
                </c:forEach>
              </c:if>
            </tbody>
          </table>
          <table class="tabela_container">
            <thead>
              <th>Hr Inicio</th>
              <th>Nº de Aulas</th>
              <th>Hr término</th>
              <th>Ação</th>
            </thead>
            <tbody>
            <c:if test="${ not empty horarios }">
              <c:forEach items="${horarios}" var="h">
                <tr id="${h.codigo}">
                <td><c:out value="${h.horario_inicio}"/></td>
                <td><c:out value="${h.numero_aulas}"/></td>
                <td><c:out value="${h.horario_termino}"/></td>
                <td>
                  <a class="acao" onclick="selecionar_horario('${h.codigo}')">Selecionar</a>
                </td>
                </tr>
              </c:forEach>
            </c:if>
            </tbody>
          </table>
        </div>
        <div>
          <c:if test="${not empty erro}">
            <h3 class="erro"><c:out value="${erro}"/></h3>
          </c:if>
          <c:if test="${not empty saida}">
            <h3 class="saida"><c:out value="${saida}"/></h3>
          </c:if>
        </div>
      </form>
    </main>
  </body>
  <script>
    function selecionar_disciplina(id) {
      // Obtém os elementos da linha original
      var linhaOriginal = document.getElementById(id)
      var codigo = linhaOriginal.cells[0].innerText
      var nome = linhaOriginal.cells[1].innerText

      var select = document.getElementById('dia_semana')
      var dia_n = select.value
      var dia = ""

      switch (dia_n)
      {
        case '2': dia = 'Segunda-Feira'; break;
        case '3': dia = 'Terça-Feira'; break;
        case '4': dia = 'Quarta-Feira'; break;
        case '5': dia = 'Quinta-Feira'; break;
        case '6': dia = 'Sexta-Feira'; break;
        case '7': dia = 'Sábado'; break;
        default: dia = 'Domingo'; break;
      }

      // passa os valores para os inputs de matricula
      var c_disciplina = document.getElementById('cod_disc')
      var n_disciplina = document.getElementById('nome_disc')
      var dia_selecionado = document.getElementById('dia')
      var i_c_disciplina = document.getElementById('i_cod_disc')
      var i_n_disciplina = document.getElementById('i_nome_disc')
      var i_dia_selecionado = document.getElementById('i_dia')
      c_disciplina.value = codigo
      n_disciplina.value = nome
      dia_selecionado.value = dia
      i_c_disciplina.value = codigo
      i_n_disciplina.value = nome
      i_dia_selecionado.value = dia

    }

    function selecionar_horario(id) {
    }
  </script>
</html>