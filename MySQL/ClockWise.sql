 DROP DATABASE IF EXISTS clockwise2;
 DROP DATABASE IF EXISTS clockwise;

CREATE DATABASE IF NOT EXISTS clockwise;

use clockwise;

/**
CREATE TABLE role (
	id_role BIGINT PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(20)
);

INSERT INTO role (id_role,role) values (1,"ROLE_ADMIN") ;
INSERT INTO role (id_role,role) values (2,"ROLE_EMPLOYEE") ;
**/

CREATE TABLE users (
	id_user BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    is_enable boolean
   -- CONSTRAINT fk_role_user FOREIGN KEY (role_id) REFERENCES role(id_role)
);

CREATE TABLE details_employee (
	user_id BIGINT,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    week_working_min INT,
    CONSTRAINT fk_users_details FOREIGN KEY (user_id) REFERENCES users(id_user) ON DELETE CASCADE
);


CREATE TABLE time_stamp (
	id_ts BIGINT PRIMARY KEY AUTO_INCREMENT,
    start_stamp BIGINT NOT NULL,
    end_stamp BIGINT,
	empl_id BIGINT NOT NULL,
    CONSTRAINT fk_empl_ts FOREIGN KEY (empl_id) REFERENCES users(id_user) ON DELETE CASCADE
);


-- INSERT INTO role (id_role,role) values (1,"ROLE_ADMIN") ;
-- INSERT INTO role (id_role,role) values (2,"ROLE_EMPLOYEE") ;

 select * from users;
 -- select * from role;
 
 -- SELECT id_user, email, password FROM users JOIN role ON users.role_id WHERE role_id = 1;