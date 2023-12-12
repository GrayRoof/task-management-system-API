DELETE FROM comments;
DELETE FROM tasks;
DELETE FROM users;

ALTER TABLE users
    ALTER COLUMN id RESTART WITH 1;

ALTER TABLE comments
    ALTER COLUMN id RESTART WITH 1;

ALTER TABLE tasks
    ALTER COLUMN id RESTART WITH 1;


create temporary table task_priority_temp (
    id      integer,
    priority   varchar
);

INSERT INTO task_priority_temp
VALUES (1, 'HIGH'),
       (2, 'NORMAL'),
       (3, 'LOW');


merge into task_priority actual
using task_priority_temp ttemp
on actual.id = ttemp.id
when matched then
update set name = ttemp.priority
    when not matched then
insert values (ttemp.id, ttemp.priority);


drop table task_priority_temp;



create temporary table task_states_temp (
        id      integer,
        state   varchar
);

INSERT INTO task_states_temp

VALUES (1, 'STARTED'),
       (2, 'IN_WORK'),
       (3, 'POSTPONED'),
       (4, 'COMPLETED'),
       (5, 'CANCELED');


merge into task_states actual
using task_states_temp ttemp
on actual.id = ttemp.id
when matched then
update set name = ttemp.state
    when not matched then
insert values (ttemp.id, ttemp.state);


drop table task_states_temp;

