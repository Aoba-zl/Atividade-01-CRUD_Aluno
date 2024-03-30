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
          <table class="tabela_matricula">
            <thead>
              <th>Código Disciplina</th>
              <th>Disciplina</th>
              <th>Código Horário</th>
              <th>Horário</th>
              <th>Dia</th>
            </thead>
            <tbody>
              <tr id="linha_matricula">
                <td name="cod_disc" id="cod_disc"></td>
                <td name="nome_disc" id="nome_disc"></td>
                <td name="sit_disc" id="sit_disc"></td>
                <td name="hoario" id="hoario"></td>
                <td name="dia" id="dia"></td>
              </tr>
            </tbody>
          </table>
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
                      <c:if test="${ acao eq 'SELECIONAR' }">
                        <a class="acao" onclick="selecionar_disciplina('${md.disciplina.codigo}')">
                        Selecionar</a>
                      </c:if>
                      <c:if test="${ acao eq 'ALTERAR' }">
                        <a class="acao">
                          Alterar</a>
                      </c:if>
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
              <!---->
              <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <!---->
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

      // Cria uma nova linha na tabela de destino
      var linha_matricula = document.getElementById('linha_matricula')
      linha_matricula.cells[0].innerText = codigo
      linha_matricula.cells[1].innerText = nome
      linha_matricula.cells[4].innerText = dia
    }
  </script>
</html>