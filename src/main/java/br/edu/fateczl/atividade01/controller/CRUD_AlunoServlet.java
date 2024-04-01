package br.edu.fateczl.atividade01.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/manter_aluno")
public class CRUD_AlunoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CRUD_AlunoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //TODO: Preencher doGet
        RequestDispatcher rd = request.getRequestDispatcher("secretaria_manter_aluno.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //TODO: Preencher doPost
        String erro = "";
        String saida = "";
        request.setAttribute("saida", saida);
        request.setAttribute("erro", erro);

        RequestDispatcher rd = request.getRequestDispatcher("secretaria_manter_aluno.jsp");
        rd.forward(request, response);
    }
}