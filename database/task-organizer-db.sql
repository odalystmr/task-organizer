CREATE DATABASE IF NOT EXISTS 'task-organizer-db';

-- `task-organizer-db`.users definition
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`        bigint(20)   NOT NULL AUTO_INCREMENT,
    `full_name` varchar(150) DEFAULT NULL,
    `username`  varchar(50)  NOT NULL,
    `email`     varchar(100) NOT NULL,
    `password`  varchar(100) NOT NULL,
    `token`     varchar(50)  DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


-- `task-organizer-db`.projects definition
DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT,
    `title`       varchar(50)  NOT NULL,
    `description` varchar(200) NOT NULL,
    `owner_id`    bigint(20)   NOT NULL,
    PRIMARY KEY (`id`),
    KEY `owner_id` (`owner_id`),
    CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


-- `task-organizer-db`.task_lists definition
DROP TABLE IF EXISTS `task_lists`;
CREATE TABLE `task_lists`
(
    `id`         bigint(20)     NOT NULL AUTO_INCREMENT,
    `title`      varchar(50)    NOT NULL,
    `position`   decimal(10, 0) NOT NULL,
    `project_id` bigint(20)     NOT NULL,
    PRIMARY KEY (`id`),
    KEY `project_id` (`project_id`),
    CONSTRAINT `task_lists_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


-- `task-organizer-db`.tasks definition
DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks`
(
    `id`           bigint(20)     NOT NULL AUTO_INCREMENT,
    `title`        varchar(50)    NOT NULL,
    `description`  varchar(200) DEFAULT NULL,
    `position`     decimal(10, 0) NOT NULL,
    `complete`     tinyint(1)   DEFAULT 0,
    `assignee_id`  bigint(20)   DEFAULT NULL,
    `task_list_id` bigint(20)     NOT NULL,
    PRIMARY KEY (`id`),
    KEY `task_list_id` (`task_list_id`),
    KEY `assignee_id` (`assignee_id`),
    CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`task_list_id`) REFERENCES `task_lists` (`id`),
    CONSTRAINT `tasks_ibfk_3` FOREIGN KEY (`assignee_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


-- `task-organizer-db`.comments definition
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`
(
    `id`       bigint(20)   NOT NULL AUTO_INCREMENT,
    `text`     varchar(250) NOT NULL,
    `date`     datetime     NOT NULL DEFAULT current_timestamp(),
    `owner_id` bigint(20)   NOT NULL,
    `task_id`  bigint(20)   NOT NULL,
    PRIMARY KEY (`id`),
    KEY `task_id` (`task_id`),
    KEY `owner_id` (`owner_id`),
    CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`),
    CONSTRAINT `comments_ibfk_4` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


-- `task-organizer-db`.project_users definition
DROP TABLE IF EXISTS `project_users`;
CREATE TABLE `project_users`
(
    `project_id` bigint(20) NOT NULL,
    `user_id`    bigint(20) NOT NULL,
    PRIMARY KEY (`project_id`, `user_id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `project_users_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
    CONSTRAINT `project_users_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;