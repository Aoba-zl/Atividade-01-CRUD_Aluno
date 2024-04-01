package br.edu.fateczl.atividade01.controller;

import br.edu.fateczl.atividade01.model.Aluno;
import br.edu.fateczl.atividade01.model.Curso;
import br.edu.fateczl.atividade01.model.Matricula;
import br.edu.fateczl.atividade01.persistence.ManterAlunoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/manter_aluno")
public class ManterAlunoServlet extends HttpServlet {


    ManterAlunoDAO manterAlunoDAO= new ManterAlunoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Curso> cursos= new ArrayList<>();


        try {
            cursos= manterAlunoDAO.getCursos();
        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        } finally {
            req.setAttribute("cursos", cursos);

            RequestDispatcher rd= req.getRequestDispatcher("secretaria_manter_aluno.jsp");
            rd.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd= req.getParameter("botao");
        String cpf= req.getParameter("cpf");
        String nome= req.getParameter("nome");
        String nome_soc= req.getParameter("nome_soc");
        String dt_nasc= req.getParameter("dt_nasc");
        String email_p= req.getParameter("email_p");
        String email_c= req.getParameter("email_c");
        String dt_seg_grau= req.getParameter("dt_seg_grau");
        String inst_seg_grau= req.getParameter("inst_seg_grau");
        String pontuacao_vest= req.getParameter("pontuacao_vest");
        String posicao_vest= req.getParameter("posicao_vest");
        String curso= req.getParameter("curso");

        //Retorno
        String saida= "";
        String erro= "";
        Matricula matricula= new Matricula();
        Aluno aluno = new Aluno();
        Curso cursoObject= new Curso();



    }
}
