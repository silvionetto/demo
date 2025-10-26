INSERT INTO demo_role (name, description, version, last_update_date) VALUES ('ROLE_ADMIN', 'Administrator',0, NOW());
INSERT INTO demo_role (name, description, version, last_update_date) VALUES ('ROLE_USER', 'User',0, NOW());
INSERT INTO demo_user (user_name, password, email, first_name, last_name, enabled, version, last_update_date) VALUES ('admin', '$2a$10$H9y6XvBG..iEH.hcws7tR.f3BcvemOY31DhG0o6.DaM.hvBJRXC5e', 'admin@email.com', 'Admin', '', TRUE, 0, NOW());
INSERT INTO demo_user_roles (demo_role_id, demo_user_id) VALUES (
    (SELECT id FROM demo_user WHERE (user_name = 'admin')),
    (SELECT id FROM demo_role WHERE (name = 'ROLE_ADMIN'))
);