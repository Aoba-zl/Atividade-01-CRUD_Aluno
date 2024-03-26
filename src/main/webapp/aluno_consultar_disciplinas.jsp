<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Consultar Disciplinas Matriculadas</title>
    <link rel="stylesheet" href="css/style.css" />
  </head>
  <body>
    <div>
      <jsp:include page="menu.jsp"/>
    </div>
    <!-- TODO: Consultar Disciplinas Matriculadas -->
    <main>
      <h1>Disciplinas Matriculadas</h1>
      <form action="consultar_disciplinas" method="post">
        <div>
          <label for="ra">RA</label>
          <input type="text" name="ra" id="ra" />
          <input type="submit" value="Buscar" name="botao" id="botao" />
        </div>
        <div class="tabela_container">
          <table>
            <thead>
              <th>Código</th>
              <th>Nome</th>
              <th>Situação</th>
              <th>Horário</th>
            </thead>
            <tbody>
              <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
            </tbody>
          </table>
        </div>
      </form>
    </main>
  </body>
</html>
<!-- https://mfyg5g.csb.app/aluno/consultar_disciplinas.html -->
