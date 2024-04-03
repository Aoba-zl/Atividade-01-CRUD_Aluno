package br.edu.fateczl.atividade01.model;

import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Getter

public class MatriculaAlunoBuilder
{
    private Aluno aluno;
    private Matricula matricula;

    public MatriculaAlunoBuilder() {
        aluno = new Aluno();
        matricula = new Matricula();
    }

    public MatriculaAlunoBuilder addCpf(String cpf)
    {
        if (cpf == null || cpf.isEmpty())
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.aluno.setCpf(cpf);
        return this;
    }

    public MatriculaAlunoBuilder addNome(String nome)
    {
        if (nome == null)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.aluno.setNome(nome);
        return this;
    }

    public MatriculaAlunoBuilder addNome_social(String nome_social)
    {
        this.aluno.setNome_social(nome_social);
        return this;
    }

    public MatriculaAlunoBuilder addEmail_pessoal(String email_pessoal)
    {
        if (email_pessoal == null)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.aluno.setEmail_pessoal(email_pessoal);
        return this;
    }

    public MatriculaAlunoBuilder addEmail_corporativo(String email_corporativo)
    {
        if (email_corporativo == null)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.aluno.setEmail_corporativo(email_corporativo);
        return this;
    }

    public MatriculaAlunoBuilder addInstituicao_seg_grau(String instituicao_seg_grau)
    {
        if (instituicao_seg_grau == null)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.aluno.setInstituicao_seg_grau(instituicao_seg_grau);
        return this;
    }

    public MatriculaAlunoBuilder addDt_nasc(Date dt_nasc)
    {
        if (dt_nasc == null)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.aluno.setDt_nasc(dt_nasc);
        return this;
    }

    public MatriculaAlunoBuilder addDt_conclusao_seg_grau(Date dt_conclusao_seg_grau)
    {
        if (dt_conclusao_seg_grau == null)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.aluno.setDt_conclusao_seg_grau(dt_conclusao_seg_grau);
        return this;
    }

    public MatriculaAlunoBuilder addTelefones(List<Telefone> telefones)
    {
        if (telefones == null || telefones.isEmpty())
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.aluno.setTelefones(telefones);
        return this;
    }

    public MatriculaAlunoBuilder addRa(String ra)
    {
        if (ra == null)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.matricula.setRa(ra);
        return this;
    }

    public MatriculaAlunoBuilder addPontuacao_vestibular(int pontuacao_vestibular)
    {
        if (pontuacao_vestibular < 0)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.matricula.setPontuacao_vestibular(pontuacao_vestibular);
        return this;
    }

    public MatriculaAlunoBuilder addPosicao_vestibular(int posicao_vestibular)
    {
        if (posicao_vestibular < 0)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.matricula.setPosicao_vestibular(posicao_vestibular);
        return this;
    }

    public MatriculaAlunoBuilder addAno_ingresso(int ano_ingresso)
    {
        if (ano_ingresso < 0)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.matricula.setAno_ingresso(ano_ingresso);
        return this;
    }

    public MatriculaAlunoBuilder addSemestre_ingresso(int semestre_ingresso)
    {
        if (semestre_ingresso < 0)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.matricula.setSemestre_ingresso(semestre_ingresso);
        return this;
    }

    public MatriculaAlunoBuilder addAno_limite(int ano_limite)
    {
        if (ano_limite < 0)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.matricula.setAno_limite(ano_limite);
        return this;
    }

    public MatriculaAlunoBuilder addSemestre_limite(int semestre_limite)
    {
        if (semestre_limite < 0)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.matricula.setSemestre_limite(semestre_limite);
        return this;
    }

    public MatriculaAlunoBuilder addMatricula_ativa(boolean matricula_ativa)
    {
        this.matricula.setMatricula_ativa(matricula_ativa);
        return this;
    }

    public MatriculaAlunoBuilder addAluno(Aluno aluno)
    {
        if (aluno == null || aluno.getCpf() == null)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.matricula.setAluno(aluno);
        return this;
    }

    public MatriculaAlunoBuilder addCurso(Curso curso)
    {
        if (curso == null || curso.nome == null)
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.matricula.setCurso(curso);
        return this;
    }

    public MatriculaAlunoBuilder addMatriculaDisciplinas(List<MatriculaDisciplina> matriculaDisciplinas)
    {
        if (matriculaDisciplinas == null || matriculaDisciplinas.isEmpty())
            throw new IllegalArgumentException("Preencha TODOS os campos");
        this.matricula.setMatriculaDisciplinas(matriculaDisciplinas);
        return this;
    }
}
