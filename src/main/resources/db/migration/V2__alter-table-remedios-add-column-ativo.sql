alter table tb_remedio add ativo tinyint;
update tb_remedio set ativo = 1;
-- o update acima é necessario pois como ja existe dados na tabela, o campo ativo vai ser nulo, e o spring não aceita
-- com isso nos estamos setando tudo que esta la como ativo = 1
-- boolean (1 = verdadeiro , 0 = false)