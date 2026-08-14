INSERT INTO users
(name, surname, age, email, password, phone_number, avatar, role_id)
VALUES
    ('Азамат', 'Асанов', 25, 'asanov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000001', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Бекзат', 'Беков', 27, 'bekov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000002', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Данияр', 'Ибраев', 29, 'ibraev@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000003', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Эльдар', 'Касымов', 31, 'kasymov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000004', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Нурсултан', 'Маматов', 26, 'mamatov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000005', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Руслан', 'Осмонов', 28, 'osmonov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000006', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Айбек', 'Садыков', 25, 'sadykov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000007', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Тимур', 'Токтосунов', 30, 'toktosunov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000008', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Арсен', 'Усенов', 32, 'usenov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000009', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Максат', 'Шаршенов', 24, 'sharshenov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000010', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Алмаз', 'Жумабаев', 27, 'zhumabaev@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000011', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Кубаныч', 'Абдиев', 29, 'abdiev@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000012', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Эрмек', 'Ниязов', 28, 'niyazov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000013', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Адилет', 'Султанов', 26, 'sultanov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000014', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT')),

    ('Бакыт', 'Талантбеков', 33, 'talantbekov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996700000015', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_APPLICANT'));


INSERT INTO resumes
(user_id, name, category_id, salary, active, created_date, update_time)
VALUES

    ((SELECT u.id FROM users u WHERE u.email = 'asanov@mail.ru'),
     'Айти специалист',
     (SELECT c.id FROM categories c WHERE c.name = 'Айти специалист'),
     80000.0, TRUE,
     CAST('2026-08-01 10:00:00' AS TIMESTAMP),
     CAST('2026-08-10 10:00:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'bekov@mail.ru'),
     'Юрист',
     (SELECT c.id FROM categories c WHERE c.name = 'Юрист'),
     50000.0, TRUE,
     CAST('2026-08-01 10:10:00' AS TIMESTAMP),
     CAST('2026-08-10 10:10:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'ibraev@mail.ru'),
     'Экономист',
     (SELECT c.id FROM categories c WHERE c.name = 'Экономист'),
     60000.0, TRUE,
     CAST('2026-08-01 10:20:00' AS TIMESTAMP),
     CAST('2026-08-10 10:20:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'kasymov@mail.ru'),
     'Монтажник',
     (SELECT c.id FROM categories c WHERE c.name = 'Монтажник'),
     45000.0, TRUE,
     CAST('2026-08-01 10:30:00' AS TIMESTAMP),
     CAST('2026-08-10 10:30:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'mamatov@mail.ru'),
     'Айти специалист',
     (SELECT c.id FROM categories c WHERE c.name = 'Айти специалист'),
     90000.0, TRUE,
     CAST('2026-08-02 10:00:00' AS TIMESTAMP),
     CAST('2026-08-11 10:00:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'osmonov@mail.ru'),
     'Юрист',
     (SELECT c.id FROM categories c WHERE c.name = 'Юрист'),
     55000.0, TRUE,
     CAST('2026-08-02 10:10:00' AS TIMESTAMP),
     CAST('2026-08-11 10:10:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'sadykov@mail.ru'),
     'Экономист',
     (SELECT c.id FROM categories c WHERE c.name = 'Экономист'),
     65000.0, TRUE,
     CAST('2026-08-02 10:20:00' AS TIMESTAMP),
     CAST('2026-08-11 10:20:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'toktosunov@mail.ru'),
     'Монтажник',
     (SELECT c.id FROM categories c WHERE c.name = 'Монтажник'),
     50000.0, TRUE,
     CAST('2026-08-02 10:30:00' AS TIMESTAMP),
     CAST('2026-08-11 10:30:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'usenov@mail.ru'),
     'Айти специалист',
     (SELECT c.id FROM categories c WHERE c.name = 'Айти специалист'),
     85000.0, TRUE,
     CAST('2026-08-03 10:00:00' AS TIMESTAMP),
     CAST('2026-08-12 10:00:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'sharshenov@mail.ru'),
     'Юрист',
     (SELECT c.id FROM categories c WHERE c.name = 'Юрист'),
     45000.0, TRUE,
     CAST('2026-08-03 10:10:00' AS TIMESTAMP),
     CAST('2026-08-12 10:10:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'zhumabaev@mail.ru'),
     'Экономист',
     (SELECT c.id FROM categories c WHERE c.name = 'Экономист'),
     70000.0, TRUE,
     CAST('2026-08-03 10:20:00' AS TIMESTAMP),
     CAST('2026-08-12 10:20:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'abdiev@mail.ru'),
     'Монтажник',
     (SELECT c.id FROM categories c WHERE c.name = 'Монтажник'),
     55000.0, TRUE,
     CAST('2026-08-03 10:30:00' AS TIMESTAMP),
     CAST('2026-08-12 10:30:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'niyazov@mail.ru'),
     'Айти специалист',
     (SELECT c.id FROM categories c WHERE c.name = 'Айти специалист'),
     95000.0, TRUE,
     CAST('2026-08-04 10:00:00' AS TIMESTAMP),
     CAST('2026-08-13 10:00:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'sultanov@mail.ru'),
     'Юрист',
     (SELECT c.id FROM categories c WHERE c.name = 'Юрист'),
     60000.0, TRUE,
     CAST('2026-08-04 10:10:00' AS TIMESTAMP),
     CAST('2026-08-13 10:10:00' AS TIMESTAMP)),

    ((SELECT u.id FROM users u WHERE u.email = 'talantbekov@mail.ru'),
     'Экономист',
     (SELECT c.id FROM categories c WHERE c.name = 'Экономист'),
     75000.0, TRUE,
     CAST('2026-08-04 10:20:00' AS TIMESTAMP),
     CAST('2026-08-13 10:20:00' AS TIMESTAMP));


INSERT INTO contacts_info
(type_id, resume_id, contact_value)
VALUES

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Айти специалист'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'asanov@mail.ru')),
     '@asanov'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Юрист'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'bekov@mail.ru')),
     '@bekov'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Экономист'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'ibraev@mail.ru')),
     '@ibraev'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Монтажник'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'kasymov@mail.ru')),
     '@kasymov'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Айти специалист'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'mamatov@mail.ru')),
     '@mamatov'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Юрист'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'osmonov@mail.ru')),
     '@osmonov'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Экономист'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'sadykov@mail.ru')),
     '@sadykov'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Монтажник'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'toktosunov@mail.ru')),
     '@toktosunov'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Айти специалист'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'usenov@mail.ru')),
     '@usenov'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Юрист'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'sharshenov@mail.ru')),
     '@sharshenov'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Экономист'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'zhumabaev@mail.ru')),
     '@zhumabaev'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Монтажник'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'abdiev@mail.ru')),
     '@abdiev'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Айти специалист'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'niyazov@mail.ru')),
     '@niyazov'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Юрист'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'sultanov@mail.ru')),
     '@sultanov'),

    ((SELECT ct.id FROM contact_types ct WHERE ct.type = 'Telegram'),
     (SELECT r.id FROM resumes r
      WHERE r.name = 'Экономист'
        AND r.user_id = (SELECT u.id FROM users u WHERE u.email = 'talantbekov@mail.ru')),
     '@talantbekov');


INSERT INTO education_info
(resume_id, institution, program, start_date, end_date, degree)
VALUES

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'asanov@mail.ru')),
     'KRSU', 'IT',
     CAST('2016-09-01' AS DATE), CAST('2020-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'bekov@mail.ru')),
     'KNU', 'Law',
     CAST('2015-09-01' AS DATE), CAST('2019-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'ibraev@mail.ru')),
     'BGU', 'Economics',
     CAST('2014-09-01' AS DATE), CAST('2018-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'kasymov@mail.ru')),
     'KGTU', 'Engineering',
     CAST('2013-09-01' AS DATE), CAST('2017-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'mamatov@mail.ru')),
     'KRSU', 'IT',
     CAST('2016-09-01' AS DATE), CAST('2020-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'osmonov@mail.ru')),
     'KNU', 'Law',
     CAST('2014-09-01' AS DATE), CAST('2018-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'sadykov@mail.ru')),
     'BGU', 'Economics',
     CAST('2015-09-01' AS DATE), CAST('2019-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'toktosunov@mail.ru')),
     'KGTU', 'Engineering',
     CAST('2013-09-01' AS DATE), CAST('2017-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'usenov@mail.ru')),
     'KRSU', 'IT',
     CAST('2012-09-01' AS DATE), CAST('2016-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'sharshenov@mail.ru')),
     'KNU', 'Law',
     CAST('2017-09-01' AS DATE), CAST('2021-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'zhumabaev@mail.ru')),
     'BGU', 'Economics',
     CAST('2015-09-01' AS DATE), CAST('2019-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'abdiev@mail.ru')),
     'KGTU', 'Engineering',
     CAST('2014-09-01' AS DATE), CAST('2018-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'niyazov@mail.ru')),
     'KRSU', 'IT',
     CAST('2016-09-01' AS DATE), CAST('2020-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'sultanov@mail.ru')),
     'KNU', 'Law',
     CAST('2013-09-01' AS DATE), CAST('2017-06-30' AS DATE), 'Bachelor'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'talantbekov@mail.ru')),
     'BGU', 'Economics',
     CAST('2014-09-01' AS DATE), CAST('2018-06-30' AS DATE), 'Bachelor');


INSERT INTO work_experience_info
(resume_id, years, company_name, position, responsibilities)
VALUES

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'asanov@mail.ru')),
     2, 'Mega', 'Айти специалист', 'Developing apps'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'bekov@mail.ru')),
     3, 'Beeline', 'Юрист', 'Working with contracts'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'ibraev@mail.ru')),
     3, 'Optima Bank', 'Экономист', 'Financial analysis'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'kasymov@mail.ru')),
     4, 'Aknet', 'Монтажник', 'Equipment installation'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'mamatov@mail.ru')),
     3, 'O!', 'Айти специалист', 'Developing apps'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'osmonov@mail.ru')),
     2, 'DemirBank', 'Юрист', 'Legal support'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'sadykov@mail.ru')),
     3, 'Bakai Bank', 'Экономист', 'Financial reports'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'toktosunov@mail.ru')),
     5, 'Saima', 'Монтажник', 'Network installation'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'usenov@mail.ru')),
     4, 'Mega', 'Айти специалист', 'Database development'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'sharshenov@mail.ru')),
     1, 'Law Company', 'Юрист', 'Preparing documents'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'zhumabaev@mail.ru')),
     2, 'Optima Bank', 'Экономист', 'Economic analysis'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'abdiev@mail.ru')),
     4, 'Aknet', 'Монтажник', 'Cable installation'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'niyazov@mail.ru')),
     3, 'Beeline', 'Айти специалист', 'Developing services'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'sultanov@mail.ru')),
     5, 'Bakai Bank', 'Юрист', 'Contract support'),

    ((SELECT r.id FROM resumes r WHERE r.user_id =
                                       (SELECT u.id FROM users u WHERE u.email = 'talantbekov@mail.ru')),
     3, 'DemirBank', 'Экономист', 'Financial analysis');


INSERT INTO users
(name, surname, age, email, password, phone_number, avatar, role_id)
VALUES

    ('Нурлан', 'Абдраев', 35, 'abdraev@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100001', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Марат', 'Алиев', 37, 'aliev@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100002', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Самат', 'Байсалов', 34, 'baisalov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100003', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Талант', 'Дуйшенов', 40, 'duishenov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100004', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Канат', 'Эсенов', 38, 'esenov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100005', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Аскар', 'Жээнбеков', 36, 'zheenbekov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100006', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Болот', 'Исаев', 41, 'isaev@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100007', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Мирлан', 'Кадыров', 33, 'kadyrov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100008', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Эмил', 'Муратов', 39, 'muratov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100009', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Улан', 'Омуралиев', 35, 'omuraliev@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100010', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Азамат', 'Раимбеков', 37, 'raimbekov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100011', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Нурбек', 'Сариев', 42, 'sariev@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100012', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Эрлан', 'Токтобаев', 34, 'toktobaev@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100013', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Алмаз', 'Уметалиев', 38, 'umetaliev@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100014', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER')),

    ('Руслан', 'Шакиров', 36, 'shakirov@mail.ru', '$2a$10$vnUpyINx.TBCfB5rGMOrKucqdFCSG0JK.ZVZu8laYede/V/NzFjpq', '+996555100015', NULL,
     (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_EMPLOYER'));


INSERT INTO vacancies
(name, description, salary, exp_from, exp_to, active,
 created_date, update_time, category_id, user_id)
VALUES

    ('Айти специалист', 'Требуется айти специалист', 100000.0, 1, 3, TRUE,
     CAST('2026-08-01 11:00:00' AS TIMESTAMP),
     CAST('2026-08-10 11:00:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Айти специалист'),
     (SELECT u.id FROM users u WHERE u.email = 'abdraev@mail.ru')),

    ('Юрист', 'Требуется юрист', 60000.0, 1, 3, TRUE,
     CAST('2026-08-01 11:10:00' AS TIMESTAMP),
     CAST('2026-08-10 11:10:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Юрист'),
     (SELECT u.id FROM users u WHERE u.email = 'aliev@mail.ru')),

    ('Экономист', 'Требуется экономист', 65000.0, 1, 4, TRUE,
     CAST('2026-08-01 11:20:00' AS TIMESTAMP),
     CAST('2026-08-10 11:20:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Экономист'),
     (SELECT u.id FROM users u WHERE u.email = 'baisalov@mail.ru')),

    ('Монтажник', 'Требуется монтажник', 50000.0, 1, 5, TRUE,
     CAST('2026-08-01 11:30:00' AS TIMESTAMP),
     CAST('2026-08-10 11:30:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Монтажник'),
     (SELECT u.id FROM users u WHERE u.email = 'duishenov@mail.ru')),

    ('Айти специалист', 'Требуется айти специалист', 110000.0, 2, 4, TRUE,
     CAST('2026-08-02 11:00:00' AS TIMESTAMP),
     CAST('2026-08-11 11:00:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Айти специалист'),
     (SELECT u.id FROM users u WHERE u.email = 'esenov@mail.ru')),

    ('Юрист', 'Требуется юрист', 70000.0, 2, 5, TRUE,
     CAST('2026-08-02 11:10:00' AS TIMESTAMP),
     CAST('2026-08-11 11:10:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Юрист'),
     (SELECT u.id FROM users u WHERE u.email = 'zheenbekov@mail.ru')),

    ('Экономист', 'Требуется экономист', 80000.0, 2, 4, TRUE,
     CAST('2026-08-02 11:20:00' AS TIMESTAMP),
     CAST('2026-08-11 11:20:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Экономист'),
     (SELECT u.id FROM users u WHERE u.email = 'isaev@mail.ru')),

    ('Монтажник', 'Требуется монтажник', 55000.0, 1, 4, TRUE,
     CAST('2026-08-02 11:30:00' AS TIMESTAMP),
     CAST('2026-08-11 11:30:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Монтажник'),
     (SELECT u.id FROM users u WHERE u.email = 'kadyrov@mail.ru')),

    ('Айти специалист', 'Требуется айти специалист', 120000.0, 2, 5, TRUE,
     CAST('2026-08-03 11:00:00' AS TIMESTAMP),
     CAST('2026-08-12 11:00:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Айти специалист'),
     (SELECT u.id FROM users u WHERE u.email = 'muratov@mail.ru')),

    ('Юрист', 'Требуется юрист', 65000.0, 1, 3, TRUE,
     CAST('2026-08-03 11:10:00' AS TIMESTAMP),
     CAST('2026-08-12 11:10:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Юрист'),
     (SELECT u.id FROM users u WHERE u.email = 'omuraliev@mail.ru')),

    ('Экономист', 'Требуется экономист', 75000.0, 2, 5, TRUE,
     CAST('2026-08-03 11:20:00' AS TIMESTAMP),
     CAST('2026-08-12 11:20:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Экономист'),
     (SELECT u.id FROM users u WHERE u.email = 'raimbekov@mail.ru')),

    ('Монтажник', 'Требуется монтажник', 60000.0, 2, 5, TRUE,
     CAST('2026-08-03 11:30:00' AS TIMESTAMP),
     CAST('2026-08-12 11:30:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Монтажник'),
     (SELECT u.id FROM users u WHERE u.email = 'sariev@mail.ru')),

    ('Айти специалист', 'Требуется айти специалист', 130000.0, 3, 6, TRUE,
     CAST('2026-08-04 11:00:00' AS TIMESTAMP),
     CAST('2026-08-13 11:00:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Айти специалист'),
     (SELECT u.id FROM users u WHERE u.email = 'toktobaev@mail.ru')),

    ('Юрист', 'Требуется юрист', 85000.0, 3, 6, TRUE,
     CAST('2026-08-04 11:10:00' AS TIMESTAMP),
     CAST('2026-08-13 11:10:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Юрист'),
     (SELECT u.id FROM users u WHERE u.email = 'umetaliev@mail.ru')),

    ('Экономист', 'Требуется экономист', 90000.0, 2, 5, TRUE,
     CAST('2026-08-04 11:20:00' AS TIMESTAMP),
     CAST('2026-08-13 11:20:00' AS TIMESTAMP),
     (SELECT c.id FROM categories c WHERE c.name = 'Экономист'),
     (SELECT u.id FROM users u WHERE u.email = 'shakirov@mail.ru'));