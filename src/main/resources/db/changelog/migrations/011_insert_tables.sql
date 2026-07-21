INSERT INTO roles (id, role_name) VALUES (1, 'applicant');
INSERT INTO roles (id, role_name) VALUES (2, 'employer');

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
                                                                                           ('Иван', 'Иванов', 35, 'ivanov@mail.ru', '123456', '+996555030303', 'avatar_ivan.png', 1),
                                                                                           ('Петр', 'Петров', 25, 'petrov@mail.ru', '123456', '+996500030303', 'avatar_petr.png', 2);

INSERT INTO resumes (user_id, name, category_id, salary, is_active, created_date, update_time) VALUES
    (1, 'Айти специалист', 3, 20000.0, TRUE, CAST('2026-07-17 10:00:00' AS TIMESTAMP), NULL);

INSERT INTO contacts_info (type_id, resume_id, contact_value) VALUES
                                                                  (1, 1, '@ivanbek'),
                                                                  (2, 1, 'ivanov@mail.ru');

INSERT INTO vacancies (name, description, salary, exp_from, exp_to, is_active, created_date, update_time, category_id, user_id) VALUES
    ('Айти специалист', 'Требуется разраб', 50000.0, 1, 3, TRUE, CAST('2026-07-17 11:00:00' AS TIMESTAMP), NULL, 3, 2);

INSERT INTO resumes (user_id, name, category_id, salary, is_active, created_date, update_time) VALUES
    (1, 'Юрист', 1, 25000.0, TRUE, CAST('2026-07-17 12:00:00' AS TIMESTAMP), NULL);

INSERT INTO resumes (user_id, name, category_id, salary, is_active, created_date, update_time) VALUES
    (1, 'Юрист', 1, 80000.0, TRUE, CAST('2026-07-17 12:00:00' AS TIMESTAMP), NULL);

INSERT INTO contacts_info (type_id, resume_id, contact_value) VALUES
                                                                  (4, 2, '+79991112233'),
                                                                  (2, 2, 'ivanov.legal@mail.ru');

INSERT INTO vacancies (name, description, salary, exp_from, exp_to, is_active, created_date, update_time, category_id, user_id) VALUES
    ('Юрист', 'Требуется юрист', 20000.0, 1, 3, TRUE, CAST('2026-07-17 12:30:00' AS TIMESTAMP), NULL, 1, 2);

INSERT INTO education_info (resume_id, institution, program, start_date, end_date, degree) VALUES
                                                                                               (1, 'KNU', 'IT', CAST('2009-09-01' AS DATE), CAST('2014-06-30' AS DATE), 'Specialist'),
                                                                                               (2, 'KRSU', 'Law', CAST('2015-09-01' AS DATE), CAST('2017-06-30' AS DATE), 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities) VALUES
                                                                                                  (1, 4, 'Mega', 'Java Developer', 'Developing apps'),
                                                                                                  (2, 3, 'Beeline', 'Lawyer', 'Creating and analysis contracts');

INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation) VALUES
                                                                           (1, 1, TRUE),
                                                                           (2, 2, TRUE);