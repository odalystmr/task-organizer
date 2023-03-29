delete
from comments;
delete
from tasks;
delete
from task_lists;
delete
from project_users;
delete
from projects;
delete
from users;

-- users
INSERT INTO `task-organizer-db`.users
    (id, full_name, username, email, password, token)
VALUES (1, 'odalys', 'odalystmr', 'odalystmr@gmail.com', '$2a$10$6JOg6qggMsKjtGWcCvwuD.XtZNb.QXcgBlV5VaRSJrR8yuKrb5cLO',
        null);
INSERT INTO `task-organizer-db`.users
    (id, full_name, username, email, password, token)
VALUES (2, 'fran', 'franrzm', 'francisco@gmail.com', '$2a$10$6JOg6qggMsKjtGWcCvwuD.XtZNb.QXcgBlV5VaRSJrR8yuKrb5cLO',
        null);
INSERT INTO `task-organizer-db`.users
    (id, full_name, username, email, password, token)
VALUES (3, 'Kira', 'kirakmm', 'kirakmm@gmail.com', '$2a$10$6JOg6qggMsKjtGWcCvwuD.XtZNb.QXcgBlV5VaRSJrR8yuKrb5cLO',
        null);
INSERT INTO `task-organizer-db`.users
    (id, full_name, username, email, password, token)
VALUES (4, 'Bruno', 'brunosmm', 'brunosmm@gmail.com', '$2a$10$6JOg6qggMsKjtGWcCvwuD.XtZNb.QXcgBlV5VaRSJrR8yuKrb5cLO',
        null);

-- projects
INSERT INTO `task-organizer-db`.projects
    (id, title, description, owner_id)
VALUES (1, 'Proyecto 1', 'esto es una prueba, usuario 1(odalys)', 1);
INSERT INTO `task-organizer-db`.projects
    (id, title, description, owner_id)
VALUES (2, 'Proyecto 2', 'esto es una prueba, usuario 3(kira)', 3);

-- project_users
INSERT INTO `task-organizer-db`.project_users
    (project_id, user_id)
VALUES (1, 1);
INSERT INTO `task-organizer-db`.project_users
    (project_id, user_id)
VALUES (1, 2);
INSERT INTO `task-organizer-db`.project_users
    (project_id, user_id)
VALUES (2, 3);
INSERT INTO `task-organizer-db`.project_users
    (project_id, user_id)
VALUES (2, 4);

-- task_lists
INSERT INTO `task-organizer-db`.task_lists
    (id, title, `position`, project_id)
VALUES (1, 'lista de tareas del proyecto 1', 1, 1);
INSERT INTO `task-organizer-db`.task_lists
    (id, title, `position`, project_id)
VALUES (2, 'lista 2 de tareas del proyecto 1', 2, 1);
INSERT INTO `task-organizer-db`.task_lists
    (id, title, `position`, project_id)
VALUES (3, 'lista de tareas del proyecto 2', 1, 2);
INSERT INTO `task-organizer-db`.task_lists
    (id, title, `position`, project_id)
VALUES (4, 'lista 2 de tareas del proyecto 2', 2, 2);

-- tasks
INSERT INTO `task-organizer-db`.tasks
(id, title, description, `position`, complete, assignee_id, task_list_id)
VALUES (1, 'Tarea 1', 'lista de tareas 1 (proyecto 1), asignado a usuario 1(odalys)', 1, 0, 1, 1);
INSERT INTO `task-organizer-db`.tasks
(id, title, description, `position`, complete, assignee_id, task_list_id)
VALUES (2, 'Tarea 2', 'lista de tareas 2 (proyecto 1), asignado a usuario 2(fran)', 1, 0, 2, 2);
INSERT INTO `task-organizer-db`.tasks
(id, title, description, `position`, complete, assignee_id, task_list_id)
VALUES (3, 'Tarea 3', 'lista de tareas 3 (proyecto 2), asignado a usuario 3(kira)', 1, 0, 3, 3);
INSERT INTO `task-organizer-db`.tasks
(id, title, description, `position`, complete, assignee_id, task_list_id)
VALUES (4, 'Tarea 4', 'lista de tareas 4 (proyecto 2), asignado a usuario 4(bruno)', 1, 0, 4, 4);

-- comments
INSERT INTO `task-organizer-db`.comments
    (id, `text`, `date`, owner_id, task_id)
VALUES (1, 'Comentario 1, en tarea 1 (proyecto 1, lista 1) escrito por 2(fran)', '2023-03-24 12:53:21.000', 2, 1);
INSERT INTO `task-organizer-db`.comments
    (id, `text`, `date`, owner_id, task_id)
VALUES (2, 'Comentario 2, en tarea 3 (proyecto 2, lista 3) escrito por 4(bruno)', '2023-03-24 12:53:21.000', 4, 3);
