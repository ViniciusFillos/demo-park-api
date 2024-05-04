INSERT INTO usuarios (id, username, password, role) VALUES (100, 'ana@gmail.com', '$2a$12$ck.5r1TVH1m29pPK5NBh7OqelRtmRcvr/WLo1GHKcqZ6UkWTUzbJi', 'ROLE_ADMIN');
INSERT INTO usuarios (id, username, password, role) VALUES (101, 'marco@gmail.com', '$2a$12$ck.5r1TVH1m29pPK5NBh7OqelRtmRcvr/WLo1GHKcqZ6UkWTUzbJi', 'ROLE_CLIENT');
INSERT INTO usuarios (id, username, password, role) VALUES (102, 'bia@gmail.com', '$2a$12$ck.5r1TVH1m29pPK5NBh7OqelRtmRcvr/WLo1GHKcqZ6UkWTUzbJi', 'ROLE_CLIENT');


INSERT INTO clientes (id, nome, cpf, id_usuario) VALUES (10, 'Marcos Benedito','92889159906', '101');
INSERT INTO clientes (id, nome, cpf, id_usuario) VALUES (20, 'Beatriz Luz','60341191973', '102');

insert into vagas (id, codigo, status) values (100, 'A-01', 'OCUPADA');
insert into vagas (id, codigo, status) values (200, 'A-02', 'OCUPADA');
insert into vagas (id, codigo, status) values (300, 'A-03', 'OCUPADA');
insert into vagas (id, codigo, status) values (400, 'A-04', 'LIVRE');
insert into vagas (id, codigo, status) values (500, 'A-05', 'LIVRE');

insert into clientes_tem_vagas (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
    values ('20230313-101300', 'FIT-1020', 'FIAT', 'PALIO', 'VERDE', '2023-03-13 10:15:00', 20, 100);
insert into clientes_tem_vagas (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
    values ('20230314-101400', 'SIE-1020', 'FIAT', 'SIENA', 'BRANCO', '2023-03-14 10:15:00', 10, 200);
insert into clientes_tem_vagas (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
    values ('20230315-101500', 'FIT-1020', 'FIAT', 'PALIO', 'VERDE', '2023-03-14 10:15:00', 20, 300);