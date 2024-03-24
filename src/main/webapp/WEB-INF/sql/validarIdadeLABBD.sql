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


