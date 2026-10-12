-- ROLES Y USUARIOS DE EJEMPLO!!!!!
INSERT INTO roles (name, description) VALUES ('ROLE_USER', 'Rol de usuario');
INSERT INTO roles (name, description) VALUES ('ROLE_ADMIN', 'Rol de administrador');
INSERT INTO roles (name, description) VALUES ('ROLE_PROFESOR', 'Rol de profesor');

-- $2b$10$O68skboX8WHXiDUO8OCxNOhwm6b0HTHEErY3jO1Q/YKFrK9o0pHSG
INSERT INTO users (username, password, full_name, email, enabled, role_id) VALUES ('admin', '$2b$10$aR.Lauv.TZlrG69l6y2snOVkIRCwmZmXlgYeDr5uESFu4CGb8rZ62', 'Administrador', 'admin1@demo', true, 2);
INSERT INTO users (username, password, full_name, email, enabled, role_id) VALUES ('user', '$2b$10$IGeQTyl0eI1MxasfD3bSse01Ml8JXCKXQ6b.v4O.QzvARtyLaiXqu', 'Usuario', 'user1@demo', true, 1);
INSERT INTO users (username, password, full_name, email, enabled, role_id) VALUES ('profesor', '$2b$10$UxoasJZjH7Ve081jsk2NIOUAIS5z6N6BNhWZb.3kmyf0hclEHEjRS', 'Profesor', 'profe1@demo', true, 3);


-- INSERT INTO users_roles (role_id,user_id) VALUES(2,1);
-- INSERT INTO users_roles (role_id,user_id) VALUES(1,2);
-- INSERT INTO users_roles (role_id, user_id) VALUES (3, 3);
-- INSERT INTO users_roles (role_id, user_id) VALUES (1, 3);