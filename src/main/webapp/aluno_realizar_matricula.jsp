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
          <input type="text" name="ra" id="ra" />
          <input type="submit" value="Buscar" name="botao" id="botao" />
        </div>
        <div>
          <table class="tabela_matricula">
            <thead>
              <th>Código</th>
              <th>Disciplina</th>
              <th>Situação</th>
              <th>Horário</th>
              <th>Dia</th>
            </thead>
            <tbody>
              <tr>
                <td id="cod_disc">A</td>
                <td id="nome_disc">A</td>
                <td id="sit_disc">A</td>
                <td id="hoario">A</td>
                <td id="dia">A</td>
              </tr>
            </tbody>
          </table>
        </div>
          <div>
            <label for="dia_semana">Dia da Semana</label>
            <select name="dia_semana" id="dia_semana"></select>
            <input type="submit" value="Realizar Matricula" />
          </div>
        <div>
          <input class="esticado" type="submit" value="Listar Disciplinas Matriculadas" />
          <input class="esticado" type="submit" value="Listar Disciplinas Disponiveis" />
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
      </form>
    </main>
  </body>
  <script>
    // TODO: acao
  </script>
</html>
<!-- https://mfyg5g.csb.app/aluno/realizar_matricula.html -->
