insert into authorities (authority)
values ('READ'),
       ('WRITE'),
       ('EDIT'),
       ('DELETE'),
       ('FULL');

update roles
set authority_id = (
    select id
    from authorities
    where authority = 'FULL'
    )
where role = 'APPLICANT';

update roles
set authority_id = (
    select id
    from authorities
    where authority = 'FULL'
    )
where role = 'EMPLOYER';