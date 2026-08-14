INSERT INTO roles (id, role_name) VALUES (1, 'ROLE_APPLICANT');
INSERT INTO roles (id, role_name) VALUES (2, 'ROLE_EMPLOYER');

INSERT INTO categories (id, name, parent_id) VALUES (1, 'Юрист', NULL);
INSERT INTO categories (id, name, parent_id) VALUES (2, 'Экономист', NULL);
INSERT INTO categories (id, name, parent_id) VALUES (3, 'Айти специалист', NULL);
INSERT INTO categories (id, name, parent_id) VALUES (4, 'Монтажник', NULL);

INSERT INTO contact_types (id, type) VALUES
                                         (1, 'Telegram'),
                                         (2, 'Mail'),
                                         (3, 'Facebook'),
                                         (4, 'Whatsapp'),
                                         (5, 'Linkedin');

INSERT INTO users (name, surname, age, email, password, phone_number, avatar, role_id) VALUES
                            ('Иван', 'Иванов', 35, 'ivanov@mail.ru', '123456', '+996555030303', 'avatar_ivan.png', (SELECT r.id FROM ROLES r WHERE r.ROLE_NAME = 'ROLE_APPLICANT')),
                            ('Петр', 'Петров', 25, 'petrov@mail.ru', '123456', '+996500030303', 'avatar_petr.png', (SELECT r.id FROM ROLES r WHERE r.ROLE_NAME = 'ROLE_EMPLOYER'));

INSERT INTO resumes (user_id, name, category_id, salary, active, created_date, update_time) VALUES
    ((SELECT id FROM users WHERE EMAIL = 'ivanov@mail.ru'), 'Айти специалист', (SELECT c.id FROM PUBLIC.CATEGORIES c WHERE c.NAME = 'Айти специалист'), 20000.0, TRUE, CAST('2026-07-17 10:00:00' AS TIMESTAMP), NULL);

INSERT INTO contacts_info (type_id, resume_id, contact_value) VALUES
((SELECT ct.ID FROM PUBLIC.CONTACT_TYPES ct WHERE ct.TYPE = 'Telegram'), (SELECT r.id FROM PUBLIC.resumes r WHERE r.name = 'Айти специалист' AND r.user_id = (SELECT id FROM PUBLIC.users WHERE email = 'ivanov@mail.ru')), '@ivanbek'),
((SELECT ct.ID FROM PUBLIC.CONTACT_TYPES ct WHERE ct.TYPE = 'Mail'), (SELECT r.id FROM PUBLIC.resumes r WHERE r.name = 'Айти специалист' AND r.user_id = (SELECT id FROM PUBLIC.users WHERE email = 'ivanov@mail.ru')), 'ivanov@mail.ru');

INSERT INTO vacancies (name, description, salary, exp_from, exp_to, active, created_date, update_time, category_id, user_id) VALUES
    ('Айти специалист', 'Требуется разраб', 50000.0, 1, 3, TRUE, CAST('2026-07-17 11:00:00' AS TIMESTAMP), NULL, (SELECT c.id FROM PUBLIC.CATEGORIES c WHERE c.NAME = 'Айти специалист'), (SELECT id FROM PUBLIC.users WHERE email = 'petrov@mail.ru'));

INSERT INTO resumes (user_id, name, category_id, salary, active, created_date, update_time) VALUES
    ((SELECT id FROM users WHERE EMAIL = 'ivanov@mail.ru'), 'Юрист', (SELECT c.id FROM PUBLIC.CATEGORIES c WHERE c.NAME = 'Юрист'), 25000.0, TRUE, CAST('2026-07-17 12:00:00' AS TIMESTAMP), NULL);

INSERT INTO contacts_info (type_id, resume_id, contact_value) VALUES
    ((SELECT ct.ID FROM PUBLIC.CONTACT_TYPES ct WHERE ct.TYPE = 'Whatsapp'), (SELECT r.id FROM PUBLIC.resumes r WHERE r.name = 'Юрист' AND r.user_id = (SELECT id FROM PUBLIC.users WHERE email = 'ivanov@mail.ru')), '+996555030303'),
    ((SELECT ct.ID FROM PUBLIC.CONTACT_TYPES ct WHERE ct.TYPE = 'Mail'), (SELECT r.id FROM PUBLIC.resumes r WHERE r.name = 'Юрист' AND r.user_id = (SELECT id FROM PUBLIC.users WHERE email = 'ivanov@mail.ru')), 'ivanov@mail.ru');

INSERT INTO vacancies (name, description, salary, exp_from, exp_to, active, created_date, update_time, category_id, user_id) VALUES
    ('Юрист', 'Требуется юрист', 20000.0, 1, 3, TRUE, CAST('2026-07-17 12:30:00' AS TIMESTAMP), NULL, (SELECT c.id FROM PUBLIC.CATEGORIES c WHERE c.NAME = 'Юрист'), (SELECT id FROM users WHERE EMAIL = 'petrov@mail.ru'));

INSERT INTO education_info (resume_id, institution, program, start_date, end_date, degree) VALUES
((SELECT r.id FROM PUBLIC.resumes r WHERE r.name = 'Айти специалист' AND r.user_id = (SELECT id FROM PUBLIC.users WHERE email = 'ivanov@mail.ru')), 'KNU', 'IT', CAST('2009-09-01' AS DATE), CAST('2014-06-30' AS DATE), 'Specialist'),
((SELECT r.id FROM PUBLIC.resumes r WHERE r.name = 'Юрист' AND r.user_id = (SELECT id FROM PUBLIC.users WHERE email = 'ivanov@mail.ru')), 'KRSU', 'Law', CAST('2015-09-01' AS DATE), CAST('2017-06-30' AS DATE), 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities) VALUES
((SELECT r.id FROM PUBLIC.resumes r WHERE r.name = 'Айти специалист' AND r.user_id = (SELECT id FROM PUBLIC.users WHERE email = 'ivanov@mail.ru')), 4, 'Mega', 'Java Developer', 'Developing apps'),
((SELECT r.id FROM PUBLIC.resumes r WHERE r.name = 'Юрист' AND r.user_id = (SELECT id FROM PUBLIC.users WHERE email = 'ivanov@mail.ru')), 3, 'Beeline', 'Lawyer', 'Creating and analysis contracts');

INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation) VALUES
((SELECT r.id FROM PUBLIC.resumes r WHERE r.name = 'Айти специалист' AND r.user_id = (SELECT id FROM PUBLIC.users WHERE email = 'ivanov@mail.ru')),
 (SELECT v.id
    FROM vacancies v
    WHERE v.name = 'Айти специалист'
      AND v.user_id = (
        SELECT u.id
        FROM users u
        WHERE u.email = 'petrov@mail.ru'
        )
    ), TRUE),
((SELECT r.id FROM PUBLIC.resumes r WHERE r.name = 'Юрист' AND r.user_id = (SELECT id FROM PUBLIC.users WHERE email = 'ivanov@mail.ru')),
 (SELECT v.id
  FROM vacancies v
 WHERE v.name = 'Юрист'
   AND v.user_id = (
    SELECT u.id
      FROM users u
     WHERE u.email = 'petrov@mail.ru'
    )
    ), TRUE);