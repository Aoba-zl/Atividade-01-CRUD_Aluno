package br.edu.fateczl.atividade01.controller;

import br.edu.fateczl.atividade01.model.*;
import br.edu.fateczl.atividade01.persistence.*;
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
        String erro = "";
        String saida = "";

        List<Curso> cursos = new ArrayList<>();

        try
        {
            cursos = buscarCursos();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            erro = e.getMessage();
        }
        finally
        {
            request.setAttribute("cursos", cursos);
            request.setAttribute("saida", saida);
            request.setAttribute("erro", erro);

            RequestDispatcher rd = request.getRequestDispatcher("secretaria_manter_aluno.jsp");
            rd.forward(request, response);
        }
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

        String erro = "";
        String saida = "";

        Matricula matricula = new Matricula();
        List<Telefone> telefones = getTelefones(request.getParameterValues("telefones"));
        List<Curso> cursos = new ArrayList<>();
        Aluno aluno = new Aluno();

        try
        {
            cursos = buscarCursos();
            if (cmd.equalsIgnoreCase("Buscar"))
            {
                aluno.setCpf(cpf);
                aluno = buscarAluno(aluno);
                saida = "Nenhum aluno encontrado!";
                if (aluno.getCpf() != null)
                {
                    matricula.setAluno(aluno);
                    matricula = buscarMatricula(matricula);
                    saida = "Nenhum encontrado!";
                }
                telefones = aluno.getTelefones();
            }
            if (cmd.equalsIgnoreCase("Cadastrar") || cmd.equalsIgnoreCase("Alterar"))
            {
                MatriculaAlunoBuilder builder = new MatriculaAlunoBuilder();
                Curso curso = new Curso();
                curso.setCodigo(Integer.parseInt(cod_curso));
                curso.setTurno(turno);

                builder.addCpf(cpf)
                .addCpf(cpf)
                .addNome(nome).addNome_social(nome_soc).addDt_nasc(Date.valueOf(dt_nasc))
                .addEmail_pessoal(email_p).addEmail_corporativo(email_c)
                .addDt_conclusao_seg_grau(Date.valueOf(dt_seg_grau))
                .addInstituicao_seg_grau(inst_seg_grau).addTelefones(telefones).addAluno(aluno).addCurso(curso)
                .addPontuacao_vestibular(Integer.parseInt(pontuacao_vest))
                .addPosicao_vestibular(Integer.parseInt(posicao_vest));

                aluno = builder.getAluno();
                matricula = builder.getMatricula();
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
        catch (SQLException | ClassNotFoundException | IllegalArgumentException e)
        {
            erro = e.getMessage();
        }
        finally
        {
            request.setAttribute("cursos", cursos);
            request.setAttribute("telefones", telefones);
            request.setAttribute("aluno", aluno);
            request.setAttribute("matricula", matricula);

            request.setAttribute("saida", saida);
            request.setAttribute("erro", erro);

            RequestDispatcher rd = request.getRequestDispatcher("secretaria_manter_aluno.jsp");
            rd.forward(request, response);
        }
    }

    private List<Telefone> getTelefones(String[] telefones_str) {
        List<Telefone> telefones = new ArrayList<>();
        if (telefones_str != null)
        {
            for (String telefone_str : telefones_str)
            {
                Telefone telefone = new Telefone(telefone_str);
                telefones.add(telefone);
            }
        }
        return telefones;
    }

    private Aluno buscarAluno(Aluno aluno)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        ICRUD<Aluno> alunoDAO = new AlunoDAO(gdao);
        return alunoDAO.find(aluno);
    }

    private Matricula buscarMatricula(Matricula matricula)
            throws SQLException, ClassNotFoundException
    {
        //TODO: buscarMatricula
        GenericDAO gdao = new GenericDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO(gdao);
        return matriculaDAO.find(matricula);
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
            throws SQLException, ClassNotFoundException, NullPointerException
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

    private List<Curso> buscarCursos()
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        CursoDAO cursoDAO = new CursoDAO(gdao);
        return cursoDAO.list();
    }
}