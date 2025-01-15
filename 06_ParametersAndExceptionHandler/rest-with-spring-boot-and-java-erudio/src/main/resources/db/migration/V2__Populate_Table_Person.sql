-- Inserindo dados na tabela person
INSERT INTO public.person (id, address, first_name, gender, last_name) VALUES
(1, 'Blumenau', 'Edgar', 'Male', 'Venturini'),
(2, 'Gaspar', 'Teste', 'Female', 'Segundo'),
(5, 'Blumenau', 'Felipe', 'Male', 'Teste'),
(6, 'Mvezo - South Africa', 'Nelson', 'Male', 'Mandela'),
(7, 'Serbia', 'Nikola', 'Male', 'Jokic');

-- Atualizando valor da sequência
SELECT pg_catalog.setval('public.person_id_seq', 7, true);
