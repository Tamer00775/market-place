-- Inserting data into _user table
INSERT INTO _user(first_name, last_name, middle_name, login, email)
VALUES ('Tamerlan', 'Kartayev', 'Rustemuly', 'admin', 'test@gmail.com');

-- Inserting data into user_auth table
INSERT INTO user_auth(user_id, password_hash, status)
SELECT id, '$2a$10$UttyNykqmIW5PVIIQtiX0ez3Vj8DEg4FW8RFtmhYtQc5w4YYJEt32', 'REGISTERED'
FROM _user
WHERE login = 'admin';

-- Inserting data into user_role table
INSERT INTO user_role(user_id, role_id, active)
SELECT u.id, r.id, true
FROM _user u
         JOIN role r ON r.code = 'ADMIN'
WHERE u.login = 'admin';
