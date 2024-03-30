package br.edu.fateczl.atividade01.controller;

import br.edu.fateczl.atividade01.model.*;
import br.edu.fateczl.atividade01.persistence.DisciplinaDAO;
import br.edu.fateczl.atividade01.persistence.GenericDAO;
import br.edu.fateczl.atividade01.persistence.MatriculaDAO;
import br.edu.fateczl.atividade01.persistence.MatriculaDisciplinaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/realizar_matricula")
public class RealizarMatriculaServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public RealizarMatriculaServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        RequestDispatcher rd = request.getRequestDispatcher("aluno_realizar_matricula.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String cmd = request.getParameter("botao");
        String ra = request.getParameter("ra");
        String dia_semana = request.getParameter("dia_semana");
        String cod_disc = request.getParameter("cod_disc");
        String cod_horario = request.getParameter("cod_hoario");

        String saida = "";
        String erro = "";
        String acao = "";
        String busca_valida;
        String matricula_valida;

        List<MatriculaDisciplina> matriculasDisciplina = new ArrayList<>();
        Matricula matricula = new Matricula();

        try
        {
            if (cmd.equalsIgnoreCase("Buscar"))
            {
                matricula.setRa(ra);
                matricula = buscarMatricula(matricula);
                if (matricula.toString() == null)
                    erro = "Matricula não encontrada";
                else
                    saida = "Olá " + matricula.getAluno().getNome() + "!";
            }
            if (cmd.equalsIgnoreCase("Realizar Matricula"))
            {
                saida = realizarMatricula(ra, cod_disc, cod_horario, dia_semana);
                matricula = new Matricula();
            }
            if (cmd.equalsIgnoreCase("Listar Disciplinas Disponiveis"))
            {
                matricula.setRa(ra);
                acao="SELECIONAR";
                matriculasDisciplina = listarDisciplinasDisponiveis(ra);
            }
            if (cmd.equalsIgnoreCase("Listar Disciplinas Matriculadas"))
            {
                acao="ALTERAR";
                saida = "saida Listar Disciplinas Matriculadas funfando";
            }
        } catch (SQLException | ClassNotFoundException e)
        {
            erro = e.getMessage();
        }
        finally
        {
            busca_valida = (dia_semana != null && matricula.getRa() != null)?"valido":"";
            matricula_valida = (dia_semana != null && cod_disc != null && cod_horario != null)?"valido":"";

            request.setAttribute("saida", saida);
            request.setAttribute("erro", erro);
            request.setAttribute("acao", acao);
            request.setAttribute("dia_semana", dia_semana);
            request.setAttribute("busca_valida", busca_valida);
            request.setAttribute("matricula_valida", matricula_valida);
            request.setAttribute("matricula", matricula);
            request.setAttribute("matriculasDisciplina", matriculasDisciplina);

            RequestDispatcher rd = request.getRequestDispatcher("aluno_realizar_matricula.jsp");
            rd.forward(request, response);
        }
    }

    private Matricula buscarMatricula(Matricula matricula)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO(gdao);
        return matriculaDAO.buscarMatricula(matricula);
    }

    private String realizarMatricula(String ra, String codDisc, String codHorario, String diaSemana)
            throws SQLException, ClassNotFoundException
    {
        // TODO: Linkar com MatriculaDisciplinaDAO
        return "Matricula realizada com sucesso";
    }

    private List<MatriculaDisciplina> listarDisciplinasMatriculadas(String ra)
            throws SQLException, ClassNotFoundException
    {
        // TODO: Linkar com MatriculaDisciplinaDAO
        return null;
    }

    private List<MatriculaDisciplina> listarDisciplinasDisponiveis(String ra)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        MatriculaDisciplinaDAO md_dao = new MatriculaDisciplinaDAO(gdao);
        return md_dao.list_disciplinas_disponiveis(ra);
    }
    private List<Horario> listarHorariosDisponiveis(String ra, int diaSemana, String carga_horaria_disciplina)
            throws SQLException, ClassNotFoundException
    {
        // TODO: Linkar com MatriculaDisciplinaDAO
        GenericDAO gdao = new GenericDAO();
        MatriculaDisciplinaDAO md_dao = new MatriculaDisciplinaDAO(gdao);
        return md_dao.list_horarios_disponiveis(ra, diaSemana, carga_horaria_disciplina);
    }

    private Disciplina buscarDisciplina(int cod_disciplina)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO(gdao);
        Disciplina disciplina = new Disciplina();
        disciplina.setCodigo(cod_disciplina);
        return disciplinaDAO.buscarDisciplina(disciplina);
    }

    private String get_dia_semana(int dia_numerico)
    {
        switch (dia_numerico)
        {
            case 2: return "Segunda-Feira";
            case 3: return "Terça-Feira";
            case 4: return "Quarta-Feira";
            case 5: return "Quinta-Feira";
            case 6: return "Sexta-Feira";
            case 7: return "Sábado-Feira";
            default: return "Domingo";
        }
    }
}