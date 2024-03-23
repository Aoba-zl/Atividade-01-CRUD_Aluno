/*
-- Dados para testes
USE atividade_1_lab_bd
*/

-- DELETE aluno WHERE cpf LIKE '%'
INSERT INTO aluno (cpf, nome, nome_social, data_nasc, email_pessoal, email_corporativo,
                    data_conclusao_seg_grau, instituicao_seg_grau)
VALUES
 ('87683951080', 'Aluno_1', NULL, '2004-04-01', 'email-p@exemplo.com', 'email-c@exemplo', '2014-03-15', 'escola tal'),
 ('19538602064', 'Aluno_2', 'fulano', '2004-04-01', 'email-p@exemplo.com', 'email-c@exemplo', '2014-03-15', 'escola tal'),
 ('31347243089', 'Aluno_3', NULL, '2004-04-01', 'email-p@exemplo.com', 'email-c@exemplo', '2014-03-15', 'escola tal')

-- DELETE telefone WHERE cpf_aluno LIKE '%'
INSERT INTO telefone VALUES
    ('87683951080', '11912341234'), ('87683951080', '11912344321'), ('87683951080', '03912345678'),
    ('19538602064', '31912341234'), ('19538602064', '31912344321'),
    ('31347243089', '12912341234')

-- DELETE curso WHERE codigo LIKE '%'
INSERT INTO curso (codigo, nome, carga_horaria, sigla, nota_enade, turno) VALUES
    (0, 'curso 0', 4,'C0', 4, 'vespertino'),
    (1, 'curso 1', 7,'C1', 5, 'noturno'),
    (2, 'curso 2', 10,'C2', 5, 'matutino')

-- DELETE disciplina WHERE codigo LIKE '%'
INSERT INTO disciplina (nome, horas_semanais) VALUES
    ('Disc 1', 4),
    ('Disc 2', 4),
    ('Disc 3', 4),
    ('Disc 4', 4),
    ('Disc 5', 4),
    ('Disc 6', 4)

-- DELETE horario WHERE id LIKE '%'
INSERT INTO horario (id, horario_inicio, num_aulas, turno) VALUES
    ('13004', '13:00', 4, 'vespertino'),
    ('13002', '13:00', 2, 'vespertino'),
    ('14504', '14:50', 4, 'vespertino'),
    ('14502', '14:50', 2, 'vespertino'),
    ('16402', '16:40', 2, 'vespertino')

-- DELETE conteudo WHERE cod_disciplina LIKE '%'
INSERT INTO conteudo (cod_disciplina, titulo) VALUES
    (1001, 'Conteudo 1 Disc 1'),(1001, 'Conteudo 2 Disc 1'), (1001, 'Conteudo 1 Disc 1'),
    (1002, 'Conteudo 1 Disc 2'),(1002, 'Conteudo 2 Disc 2'), (1002, 'Conteudo 1 Disc 2'),
    (1003, 'Conteudo 1 Disc 3'),(1003, 'Conteudo 2 Disc 3'), (1003, 'Conteudo 1 Disc 3'),
    (1004, 'Conteudo 1 Disc 4'),(1004, 'Conteudo 2 Disc 4'), (1004, 'Conteudo 1 Disc 4'),
    (1005, 'Conteudo 1 Disc 5'),(1005, 'Conteudo 2 Disc 5'), (1005, 'Conteudo 1 Disc 5'),
    (1006, 'Conteudo 1 Disc 6'),(1006, 'Conteudo 2 Disc 6'), (1006, 'Conteudo 1 Disc 6')

-- DELETE curso_disciplina WHERE cod_disciplina LIKE '%'
INSERT INTO curso_disciplina (cod_disciplina, cod_curso) VALUES
    (1001, 0), (1002, 0),
    (1001, 1), (1003, 1), (1004, 1),
    (1005, 2), (1006, 2)

-- DELETE matricula WHERE ra LIKE '%'
INSERT INTO matricula (ra, cpf_aluno, cod_curso, pontuacao_vestibular, posicao_vestibular, ano_ingresso,
                       semestre_ingresso, ano_limite_graduacao, semestre_limite_graduacao)
VALUES
    ('202211589', '87683951080', 0, 100, 50, 2022, 1, 2027, 2),
    ('202211876', '19538602064', 0, 90, 5, 2022, 1, 2027, 2),
    ('202218417', '31347243089', 0, 100, 10, 2022, 1, 2027, 2)


INSERT INTO matricula_disciplina (ra_matricula, id_horario, cod_disciplina, ano_matricula, semestre_matricula, estado)
VALUES
    ('202211589', '13004', 1001, 2024, 01, 'matriculado'),
        ('202211589', '13002', 1002, 2024, 01, 'matriculado'),
        ('202211589', '14502', 1003, 2024, 01, 'matriculado'),
        ('202211589', '13004', 1001, 2023, 02, 'reprovado'),
        ('202211589', '13002', 1003, 2024, 01, 'aprovado'),
    ('202211876', '13004', 1001, 2024, 01, 'matriculado'),
    ('202218417', '13004', 1001, 2024, 01, 'matriculado')


