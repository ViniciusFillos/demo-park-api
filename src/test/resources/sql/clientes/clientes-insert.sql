INSERT INTO usuarios (id, username, password, role) VALUES (100, 'ana@gmail.com', '$2a$12$ck.5r1TVH1m29pPK5NBh7OqelRtmRcvr/WLo1GHKcqZ6UkWTUzbJi', 'ROLE_ADMIN');
INSERT INTO usuarios (id, username, password, role) VALUES (101, 'marco@gmail.com', '$2a$12$ck.5r1TVH1m29pPK5NBh7OqelRtmRcvr/WLo1GHKcqZ6UkWTUzbJi', 'ROLE_CLIENT');
INSERT INTO usuarios (id, username, password, role) VALUES (102, 'bia@gmail.com', '$2a$12$ck.5r1TVH1m29pPK5NBh7OqelRtmRcvr/WLo1GHKcqZ6UkWTUzbJi', 'ROLE_CLIENT');
INSERT INTO usuarios (id, username, password, role) VALUES (103, 'bob@gmail.com', '$2a$12$ck.5r1TVH1m29pPK5NBh7OqelRtmRcvr/WLo1GHKcqZ6UkWTUzbJi', 'ROLE_CLIENT');

INSERT INTO clientes (id, nome, cpf, id_usuario) VALUES (10, 'Marcos Benedito','92889159906', '101');
INSERT INTO clientes (id, nome, cpf, id_usuario) VALUES (20, 'Beatriz Luz','60341191973', '102');
