INSERT INTO section_entity (section) VALUES ('HORNO');
INSERT INTO section_entity (section) VALUES ('CAJAS');
INSERT INTO section_entity (section) VALUES ('PESCADERIA');
INSERT INTO section_entity (section) VALUES ('VERDURAS');
INSERT INTO section_entity (section) VALUES ('DROGUERIA');

INSERT INTO section_skills (section_id, skills) VALUES ('HORNO', 'Hornear Pan');
INSERT INTO section_skills (section_id, skills) VALUES ('HORNO', 'Repostería');
INSERT INTO section_skills (section_id, skills) VALUES ('CAJAS', 'Simpatía');
INSERT INTO section_skills (section_id, skills) VALUES ('CAJAS', 'Matemáticas');
INSERT INTO section_skills (section_id, skills) VALUES ('PESCADERIA', 'Manejo de armas blancas');
INSERT INTO section_skills (section_id, skills) VALUES ('PESCADERIA', 'Limpiar pescado');
INSERT INTO section_skills (section_id, skills) VALUES ('VERDURAS', 'Fortaleza física');
INSERT INTO section_skills (section_id, skills) VALUES ('DROGUERIA', 'Alquimia');


INSERT INTO store_entity (name) VALUES ('Mercadona centro');
INSERT INTO store_entity (name) VALUES ('Mercadona Madrid');
INSERT INTO store_entity (name) VALUES ('Mercadona Barcelona');
INSERT INTO store_entity (name) VALUES ('Mercadona Toledo');
INSERT INTO store_entity (name) VALUES ('Mercadona Sevilla');

INSERT INTO worker_entity (dni, name, last_names, max_hours, assigned_hours, id_store) 
VALUES ('12345678Z', 'Juan', 'Perez', 8, 8, 1);
INSERT INTO worker_entity (dni, name, last_names, max_hours, assigned_hours, id_store) 
VALUES ('87654321X', 'Ana', 'Gomez', 8, 8, 1);
INSERT INTO worker_entity (dni, name, last_names, max_hours, assigned_hours, id_store) 
VALUES ('11223344A', 'Luis', 'Martinez', 8, 4, 2);
INSERT INTO worker_entity (dni, name, last_names, max_hours, assigned_hours, id_store) 
VALUES ('44332211B', 'Maria', 'Lopez', 8, 3, 2);
INSERT INTO worker_entity (dni, name, last_names, max_hours, assigned_hours, id_store) 
VALUES ('55667788C', 'Pedro', 'Suarez', 8, 7, 3);
INSERT INTO worker_entity (dni, name, last_names, max_hours, assigned_hours, id_store) 
VALUES ('88776655D', 'Lucia', 'Diaz', 8, 0, 3);
INSERT INTO worker_entity (dni, name, last_names, max_hours, assigned_hours, id_store) 
VALUES ('99887766E', 'Carlos', 'Torres', 8, 0, 4);
INSERT INTO worker_entity (dni, name, last_names, max_hours, assigned_hours, id_store) 
VALUES ('66778899F', 'Elena', 'Ramirez', 8, 0, 4);

INSERT INTO sections_workers (worker_dni, section, hours) VALUES
('12345678Z', 'HORNO', 8),
('87654321X', 'CAJAS', 6),
('87654321X', 'PESCADERIA', 2),
('11223344A', 'PESCADERIA', 4),
('44332211B', 'VERDURAS', 3),
('55667788C', 'DROGUERIA', 7);



INSERT INTO store_work_day_entity (date, id_store)
VALUES ('2026-03-29', 1),
    ('2026-03-30', 1),
    ('2026-03-31', 1),
    ('2026-04-01', 1),
    ('2026-04-02', 1),
    ('2026-04-03', 1),
    ('2026-04-04', 1),
    ('2026-04-05', 1),
    ('2026-04-06', 1),
    ('2026-05-07', 1),
    ('2026-04-08', 1),
    ('2026-04-09', 1),
    ('2026-05-10', 1);

INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);

INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);
INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);
INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);
INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);
INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);
INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);
INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);
INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);
INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);
INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);
INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);
INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);
INSERT INTO section_work_day_entity (section_id, assigned_hours, required_hours)
VALUES ('HORNO', 8, 8),
       ('CAJAS', 6, 16),
       ('PESCADERIA', 2, 16),
       ('VERDURAS', 0, 16),
       ('DROGUERIA', 0, 16);


INSERT INTO store_work_day_entity_list_sections (store_work_day_entity_id, list_sections_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (2, 6),
       (2, 7),
       (2, 8),
       (2, 9),
       (2, 10),
       (3, 11),
       (3, 12),
       (3, 13),
       (3, 14),
       (3, 15),
       (4, 16),
       (4, 17),
       (4, 18),
       (4, 19),
       (4, 20),
       (5, 21),
       (5, 22),
       (5, 23),
       (5, 24),
       (5, 25),
       (6, 26),
       (6, 27),
       (6, 28),
       (6, 29),
       (6, 30),
       (7, 31),
       (7, 32),
       (7, 33),
       (7, 34),
       (7, 35),
       (8, 36),
       (8, 37),
       (8, 38),
       (8, 39),
       (8, 40),
       (9, 41),
       (9, 42),
       (9, 43),
       (9, 44),
       (9, 45),
       (10, 46),
       (10, 47),
       (10, 48),
       (10, 49),
       (10, 50),
       (11, 51),
       (11, 52),
       (11, 53),
       (11, 54),
       (11, 55),
       (12, 56),
       (12, 57),
       (12, 58),
       (12, 59),
       (12, 60),
       (13, 61),
       (13, 62),
       (13, 63),
       (13, 64),
       (13, 65);
