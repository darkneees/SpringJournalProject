USE school;

INSERT INTO users (username, password, first_name, last_name)
    VALUES ('darkneees', '$2a$10$5ckZ0NCycgvz/uHmq6JEyeqsL335Q.ExIsGRG/7tUkYpRv9ePRP5K',
            'Андрей', 'Нестеров');

INSERT INTO roles (id, name_role, pretty_name) VALUES (1, 'ROLE_ADMIN', 'Администратор'), (2, 'ROLE_TEACHER', 'Преподаватель');
INSERT INTO users_roles(User_id, roles_id) VALUES (1, 1);