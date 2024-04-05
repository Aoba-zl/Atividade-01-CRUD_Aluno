package br.edu.fateczl.atividade01.controller;

import br.edu.fateczl.atividade01.model.Disciplina;
import br.edu.fateczl.atividade01.model.Horario;
import br.edu.fateczl.atividade01.model.MatriculaDisciplina;
import br.edu.fateczl.atividade01.persistence.ConsultarDisciplinasDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/consultar_disciplinas")
public class ConsultarDisciplinasServlet extends HttpServlet {


    ConsultarDisciplinasDAO consultarDisciplinasDAO= new ConsultarDisciplinasDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getParameter("botao");
        String ra = req.getParameter("ra");


//        Retorno
        String erro= "";
        List<MatriculaDisciplina> listaDisciplinas= new ArrayList<>();

        try {
            if (ra.length() != 9){
                erro = "RA inválido";
            }
            else if (!consultarDisciplinasDAO.verificarRA(ra)){
                erro = "Matrícula não Encontrada";
            }
            else {
                if (cmd.contains("Buscar")) {
                    listaDisciplinas = consultarDisciplinasDAO.getDiciplinas(ra);
                }
            }

        } catch (SQLException | ClassNotFoundException e){
            erro = "Erro em consultar disciplinas";
        } finally {
            req.setAttribute("listaDisciplinas", listaDisciplinas);

            if (listaDisciplinas.isEmpty() && erro.isEmpty()){
                erro = "O Aluno não possui disciplinas matriculadas";
            }

            req.setAttribute("erro", erro);

            RequestDispatcher rd= req.getRequestDispatcher("aluno_consultar_disciplinas.jsp");
            rd.forward(req, resp);
        }


    };
}
