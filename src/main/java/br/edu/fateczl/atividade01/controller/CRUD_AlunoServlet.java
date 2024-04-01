package br.edu.fateczl.atividade01.controller;

import br.edu.fateczl.atividade01.model.Aluno;
import br.edu.fateczl.atividade01.model.Curso;
import br.edu.fateczl.atividade01.model.Matricula;
import br.edu.fateczl.atividade01.model.Telefone;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        //TODO: Buscar cursos
        RequestDispatcher rd = request.getRequestDispatcher("secretaria_manter_aluno.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String cmd = request.getParameter("botao");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String nome_soc = request.getParameter("nome_soc");
        String dt_nasc = request.getParameter("dt_nasc");
        String email_p = request.getParameter("email_p");
        String email_c = request.getParameter("email_c");
        String dt_seg_grau = request.getParameter("dt_seg_grau");
        String inst_seg_grau = request.getParameter("inst_seg_grau");
        String pontuacao_vest = request.getParameter("pontuacao_vest");
        String posicao_vest = request.getParameter("posicao_vest");
        String cod_curso = request.getParameter("curso");
        String turno = request.getParameter("turno");
        /*String ra = request.getParameter("ra");
        String ano_i = request.getParameter("ano_i");
        String semes_i = request.getParameter("semes_i");
        String ano_l = request.getParameter("ano_l");
        String semes_l = request.getParameter("semes_l");*/

        String telefone = request.getParameter("telefone");

        String erro = "";
        String saida = "";

        Matricula matricula = new Matricula();
        List<Telefone> telefones = new ArrayList<>();
        Aluno aluno = new Aluno();

        try
        {
            if (cmd.equalsIgnoreCase("Buscar"))
            {
                aluno.setCpf(cpf);
                aluno = buscarAluno(aluno);
                matricula = buscarMatricula(aluno);
                System.out.println();
            }
                System.out.println();
            if (cmd.equalsIgnoreCase("Cadastrar") || cmd.equalsIgnoreCase("Alterar"))
            {
                aluno.setCpf(cpf);
                aluno.setNome(nome);
                aluno.setNome_social(nome_soc);
                aluno.setDt_nasc(Date.valueOf(dt_nasc));
                aluno.setEmail_pessoal(email_p);
                aluno.setEmail_corporativo(email_c);
                aluno.setDt_conclusao_seg_grau(Date.valueOf(dt_seg_grau));
                aluno.setInstituicao_seg_grau(inst_seg_grau);
                aluno.setTelefones(telefones);

                Curso curso = new Curso();
                curso.setCodigo(Integer.parseInt(cod_curso));
                curso.setTurno(turno);
                matricula.setAluno(aluno);
                matricula.setCurso(curso);

                matricula.setPontuacao_vestibular(Integer.parseInt(pontuacao_vest));
                matricula.setPosicao_vestibular(Integer.parseInt(posicao_vest));
                System.out.println();
            }
            if (cmd.equalsIgnoreCase("Cadastrar"))
            {
                saida = cadastarMatricula(aluno, matricula);
                aluno = new Aluno();
                matricula = new Matricula();
            }
            if (cmd.equalsIgnoreCase("Alterar"))
            {
                saida = alterarMatricula(aluno, matricula);
                aluno = new Aluno();
                matricula = new Matricula();
            }
            if (cmd.equalsIgnoreCase("Desativar Matricula"))
            {
                saida = desativarMatricula(aluno, matricula);
                aluno = new Aluno();
                matricula = new Matricula();
            }
            if (cmd.equalsIgnoreCase("Ativar Matricula"))
            {
                saida = ativarMatricula(aluno, matricula);
                aluno = new Aluno();
                matricula = new Matricula();
            }
        }
        catch (SQLException | ClassNotFoundException e)
        {
            erro = e.getMessage();
        }
        finally
        {
            request.setAttribute("telefones", telefones);
            request.setAttribute("aluno", aluno);
            request.setAttribute("matricula", matricula);

            request.setAttribute("saida", saida);
            request.setAttribute("erro", erro);

            RequestDispatcher rd = request.getRequestDispatcher("secretaria_manter_aluno.jsp");
            rd.forward(request, response);
        }
    }

    private Aluno buscarAluno(Aluno aluno)
            throws SQLException, ClassNotFoundException
    {
        //TODO: buscarAluno
        return null;
    }

    private Matricula buscarMatricula(Aluno aluno)
            throws SQLException, ClassNotFoundException
    {
        //TODO: buscarMatricula
        return null;
    }

    private String cadastarMatricula(Aluno aluno, Matricula matricula)
            throws SQLException, ClassNotFoundException
    {
        //TODO: cadastarMatricula
        return null;
    }

    private String alterarMatricula(Aluno aluno, Matricula matricula)
            throws SQLException, ClassNotFoundException
    {
        //TODO: alterarMatricula
        return null;
    }

    private String desativarMatricula(Aluno aluno, Matricula matricula)
            throws SQLException, ClassNotFoundException
    {
        //TODO: desativarMatricula
        return null;
    }

    private String ativarMatricula(Aluno aluno, Matricula matricula)
            throws SQLException, ClassNotFoundException
    {
        //TODO: ativarMatricula
        return null;
    }
}