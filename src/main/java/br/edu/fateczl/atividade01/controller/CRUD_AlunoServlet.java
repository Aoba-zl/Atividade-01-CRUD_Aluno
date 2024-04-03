package br.edu.fateczl.atividade01.controller;

import br.edu.fateczl.atividade01.model.*;
import br.edu.fateczl.atividade01.persistence.*;
import br.edu.fateczl.atividade01.utils.MatriculaAlunoBuilder;
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

        String ra = request.getParameter("ra");
        String ano_i = request.getParameter("ano_i");
        String semes_i = request.getParameter("semes_i");

        String erro = "";
        String saida = "";

        MatriculaAlunoBuilder builder = new MatriculaAlunoBuilder();
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
                    saida = "";
                }
                telefones = aluno.getTelefones();
            }
            if (cmd.equalsIgnoreCase("Cadastrar") || cmd.equalsIgnoreCase("Alterar"))
            {
                Curso curso = new Curso();
                curso.setCodigo(Integer.parseInt(cod_curso));
                curso.setTurno(turno);

                Date dt_nascimento = dt_nasc.isEmpty() ? null:Date.valueOf(dt_nasc);
                Date dt_conclusao = dt_seg_grau.isEmpty() ? null:Date.valueOf(dt_seg_grau);
                builder
                        .addCpf(cpf).addNome(nome).addNome_social(nome_soc).addDt_nasc(dt_nascimento)
                        .addEmail_pessoal(email_p).addEmail_corporativo(email_c)
                        .addDt_conclusao_seg_grau(dt_conclusao)
                        .addInstituicao_seg_grau(inst_seg_grau)
                .addTelefones(telefones);
                aluno = builder.getAluno();

                builder.addAluno(aluno).addCurso(curso)
                        .addPontuacao_vestibular(Integer.parseInt(pontuacao_vest))
                        .addPosicao_vestibular(Integer.parseInt(posicao_vest));
                matricula = builder.getMatricula();
            }
            if (cmd.equalsIgnoreCase("Cadastrar"))
            {
                saida = cadastarMatricula(aluno, matricula);
                aluno = new Aluno();
                matricula = new Matricula();
                telefones = new ArrayList<>();
            }
            if (cmd.equalsIgnoreCase("Alterar"))
            {
                int ano_ingresso = ano_i.isEmpty()? -1:Integer.parseInt(ano_i);
                int semestre_ingresso = semes_i.isEmpty()? -1: Integer.parseInt(semes_i);
                builder.addRa(ra).addAno_ingresso(ano_ingresso).addSemestre_ingresso(semestre_ingresso);
                saida = alterarMatricula(aluno, matricula);
                aluno = new Aluno();
                matricula = new Matricula();
                telefones = new ArrayList<>();
            }
            if (cmd.equalsIgnoreCase("Desativar Matricula"))
            {
                matricula.setRa(ra);
                matricula.setAluno(aluno);
                matricula.setCurso(new Curso());
                saida = desativarMatricula(matricula);
                aluno = new Aluno();
                matricula = new Matricula();
                telefones = new ArrayList<>();
            }
            if (cmd.equalsIgnoreCase("Ativar Matricula"))
            {
                matricula.setRa(ra);
                matricula.setAluno(aluno);
                matricula.setCurso(new Curso());
                saida = ativarMatricula(matricula);
                aluno = new Aluno();
                matricula = new Matricula();
                telefones = new ArrayList<>();
            }
            if (cmd.equalsIgnoreCase("Novo Cadastro"))
            {
                aluno = new Aluno();
                matricula = new Matricula();
                telefones = new ArrayList<>();
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
        GenericDAO gdao = new GenericDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO(gdao);
        return matriculaDAO.findCpf(matricula);
    }

    private String cadastarMatricula(Aluno aluno, Matricula matricula)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        ICRUD<Aluno> alunoDAO = new AlunoDAO(gdao);
        MatriculaDAO matriculaDAO = new MatriculaDAO(gdao);
        TelefoneDAO telefoneDAO = new TelefoneDAO(gdao);

        alunoDAO.insert(aluno);
        for (Telefone telefone : aluno.getTelefones())
            telefoneDAO.insert(aluno, telefone);
        return matriculaDAO.insert(matricula);
    }

    private String alterarMatricula(Aluno aluno, Matricula matricula)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO(gdao);
        ICRUD<Aluno> alunoDAO = new AlunoDAO(gdao);
        TelefoneDAO telefoneDAO = new TelefoneDAO(gdao);

        alunoDAO.update(aluno);
        telefoneDAO.update(aluno);
        return matriculaDAO.update(matricula);
    }

    private String desativarMatricula(Matricula matricula)
            throws SQLException, ClassNotFoundException, NullPointerException
    {
        GenericDAO gdao = new GenericDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO(gdao);
        return matriculaDAO.disable(matricula);
    }

    private String ativarMatricula(Matricula matricula)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO(gdao);
        return matriculaDAO.enable(matricula);
    }

    private List<Curso> buscarCursos()
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        CursoDAO cursoDAO = new CursoDAO(gdao);
        return cursoDAO.list();
    }
}