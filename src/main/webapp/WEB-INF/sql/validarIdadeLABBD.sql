CREATE DATABASE atividade_1_lab_bd
GO
USE atividade_1_lab_bd

CREATE TABLE aluno
(
    cpf CHAR(11) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    nome_social VARCHAR(50),
    data_nasc DATE NOT NULL,
    email_pessoal VARCHAR(50) NOT NULL,
    email_corporativo VARCHAR(50) NOT NULL,
    data_conclusao_seg_grau DATE NOT NULL,
    instituicao_seg_grau VARCHAR(50) NOT NULL,

    PRIMARY KEY(cpf)
)

CREATE TABLE telefone
(
    cpf_aluno   CHAR(11),
    numero CHAR(12) NOT NULL,

    PRIMARY KEY (cpf_aluno, numero),
    FOREIGN KEY (cpf_aluno) REFERENCES aluno
)

CREATE TABLE disciplina
(
    codigo INT IDENTITY(1001, 1) NOT NULL,
    nome VARCHAR(40) NOT NULL,
    horas_semanais INT NOT NULL,

    PRIMARY KEY(codigo)
)


CREATE TABLE curso
(
    codigo INT NOT NULL,
    nome VARCHAR(40) NOT NULL,
    carga_horaria INT NOT NULL,
    sigla VARCHAR(10) NOT NULL,
    nota_enade INT NOT NULL,
    turno VARCHAR(10) NOT NULL,

    PRIMARY KEY(codigo)
)


CREATE TABLE conteudo
(
    id INT IDENTITY NOT NULL,
    cod_disciplina  INT         NOT NULL,
    titulo       VARCHAR(50) NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(cod_disciplina) REFERENCES disciplina
)


CREATE TABLE horario
(
    id              CHAR(5)     NOT NULL,
    horario_inicio TIME NOT NULL,
    num_aulas    INT  NOT NULL,
    turno VARCHAR(10) NOT NULL,

    PRIMARY KEY(id)
)


CREATE TABLE curso_disciplina
(
    cod_disciplina  INT NOT NULL,
    cod_curso       INT NOT NULL,

    PRIMARY KEY(cod_disciplina, cod_curso),
    FOREIGN KEY(cod_disciplina) REFERENCES disciplina,
    FOREIGN KEY(cod_curso)      REFERENCES curso
)


CREATE TABLE matricula
(
    ra CHAR(9) NOT NULL,
    cpf_aluno CHAR(11) NOT NULL,
    cod_curso INT NOT NULL,
    pontuacao_vestibular INT NOT NULL,
    posicao_vestibular INT NOT NULL,
    ano_ingresso INT NOT NULL,
    semestre_ingresso INT NOT NULL,
    ano_limite_graduacao INT NOT NULL,
    semestre_limite_graduacao INT NOT NULL,

    PRIMARY KEY(ra),
    FOREIGN KEY(cpf_aluno) REFERENCES aluno,
    FOREIGN KEY(cod_curso) REFERENCES curso
)


CREATE TABLE matricula_disciplina
(
    id     int Identity NOT NULL,
    id_horario   CHAR(5) NOT NULL,
    ra_matricula  CHAR(9) NOT NULL,
    cod_disciplina  INT NOT NULL,
    ano_matricula  INT NOT NULL,
    semestre_matricula INT NOT NULL,
    estado    VARCHAR(12) NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(ra_matricula)   REFERENCES matricula,
    FOREIGN KEY(id_horario)     REFERENCES horario,
    FOREIGN KEY(cod_disciplina) REFERENCES disciplina
)


-- Validar Idade, idade >= 16

create procedure sp_alunovalidaridade(@dt_nasc date, @saida varchar(100) output)
as
    declare @idade int
    set @idade = datediff(year, @dt_nasc, getdate())
    if (@idade >= 16)
    begin
        set @saida = 'Idade valida'
    end
    else
    begin
        raiserror('Aluno com idade menor que 16 anos', 16, 1)
    end








declare @saida1 varchar(100)
exec sp_alunovalidaridade '2003-03-15', @saida1 output
print @saida1


