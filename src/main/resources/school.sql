CREATE DATABASE school;
USE school;

CREATE TABLE users (
    username varchar(15),
    password varchar(100),
    enabled tinyint(1),
    PRIMARY KEY (username)
);

CREATE TABLE authorities (
    username varchar(15),
    authority varchar(25),
    FOREIGN KEY (username) references users(username)
);

INSERT INTO school.users (username, password, enabled)
    VALUES ('darkneees', '{bcrypt}$2a$10$5ckZ0NCycgvz/uHmq6JEyeqsL335Q.ExIsGRG/7tUkYpRv9ePRP5K', 1);

INSERT INTO school.users (username, password, enabled)
    VALUES ('darkneees1', '{bcrypt}$2a$10$5ckZ0NCycgvz/uHmq6JEyeqsL335Q.ExIsGRG/7tUkYpRv9ePRP5K', 1);

INSERT INTO school.authorities (username, authority)
    VALUES('darkneees1', 'admin');

INSERT INTO school.authorities (username, authority)
    VALUES('darkneees', 'teacher');

CREATE TABLE teacher_list (
    username varchar(15),
    first_name varchar (30),
    last_name varchar (30),
    classes_and_subjects json,
    FOREIGN KEY (username) references users(username)
);

INSERT INTO school.teacher_list (username, first_name, last_name, classes_and_subjects) VALUES
(
 'darkneees', 'Андрей', 'Нестеров',
 '{"Математика": "7А", "Русский язык": "8А", "Математика": "9А"}'
);

CREATE TABLE pupils_list(
                            id int NOT NULL AUTO_INCREMENT,
                            first_name varchar(30),
                            last_name varchar(30),
                            study_class varchar (3),
                            marks json,
                            PRIMARY KEY (id)
);

INSERT INTO school.pupils_list (first_name, last_name, study_class, marks) VALUES
    (
        'Андрей', 'Нестеров', '7А',
        '{"marks": {"Математика": {"17.03.2022": "5", "18.03.2022": "4"},"Русский язык": {"17.03.2022": "5", "18.03.2022": "4"}}}'
    );
