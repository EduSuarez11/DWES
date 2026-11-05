INSERT INTO roles (name, description) VALUES ('ROLE_USER', 'Rol de usuario');
INSERT INTO roles (name, description) VALUES ('ROLE_ADMIN', 'Rol de administrador');
-- INSERT INTO roles (name, description) VALUES ('ROLE_PROFESOR', 'Rol de profesor');


INSERT INTO users (username, password, full_name, email, enabled, role_id) VALUES ('admin', '$2b$10$aR.Lauv.TZlrG69l6y2snOVkIRCwmZmXlgYeDr5uESFu4CGb8rZ62', 'Administrador', 'admin1@demo', true, 2);
INSERT INTO users (username, password, full_name, email, enabled, role_id) VALUES ('user', '$2b$10$IGeQTyl0eI1MxasfD3bSse01Ml8JXCKXQ6b.v4O.QzvARtyLaiXqu', 'Usuario', 'user1@demo', true, 1);