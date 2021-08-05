DROP TABLE IF EXISTS ers_reimbursement;

CREATE TABLE ers_reimbursement (
	reimb_id  SERIAL NOT NULL UNIQUE PRIMARY KEY,
	reimb_amount INTEGER NOT NULL,
	reimb_submitted TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	reimb_resolved TIMESTAMP,
	reimb_description VARCHAR(250),
	reimb_author INTEGER NOT NULL, --FOREIGN KEY REFERENCES ers_users(ers_users_id),
	reimb_resolver INTEGER NOT NULL DEFAULT 0, --FOREIGN KEY REFERENCES ers_users(ers_users_id),
	reimb_status_id INTEGER NOT NULL, --FOREIGN KEY REFERENCES ers_reimbursement_status(reimb_status_id),
	reimb_type_id INTEGER NOT NULL --FOREIGN KEY REFERENCES ers_reimbursement_type(reimb_type_id)
);

CREATE TABLE ers_users (
	ers_users_id INTEGER NOT NULL PRIMARY KEY,
	ers_username VARCHAR(50) NOT NULL UNIQUE,
	ers_password VARCHAR(50) NOT NULL,
	user_first_name VARCHAR(100) NOT NULL,
	user_last_name VARCHAR(100) NOT NULL,
	user_email VARCHAR(150) NOT NULL UNIQUE,
	user_role_id INTEGER NOT NULL --FOREIGN KEY REFERENCES ers_user_roles(ers_user_role_id)
);

CREATE TABLE ers_reimbursement_status (
	reimb_status_id INTEGER NOT NULL PRIMARY KEY,
	reimb_status VARCHAR(10) NOT NULL
);

CREATE TABLE ers_reimbursement_type (
	reimb_type_id INTEGER NOT NULL PRIMARY KEY,
	reimb_type VARCHAR(10) NOT NULL
);

CREATE TABLE ers_user_roles (
	ers_user_role_id INTEGER NOT NULL PRIMARY KEY,
	user_role VARCHAR(10) NOT NULL
);

-- Creating roles and reimbursement info

INSERT INTO ers_user_roles (ers_user_role_id, user_role)
	VALUES(1, 'Employee'),
	(2, 'Manager');
	
INSERT INTO ers_reimbursement_type (reimb_type_id, reimb_type)
	VALUES(1, 'Lodging'),
	(2, 'Travel'),
	(3, 'Food'),
	(4, 'Other');
	
INSERT INTO ers_reimbursement_status (reimb_status_id, reimb_status)
	VALUES(1, 'Pending'),
	(2, 'Approved'),
	(3, 'Denied');

-- Creating users

INSERT INTO ers_users (ers_users_id, ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id)
	VALUES(1, 'manager', 'password', 'Dylan', 'Villhauer', 'wooooo@this.com', 2),
	(2, 'employee1', 'password', 'Some', 'Guy', 'hello@this.com', 1),
	(3, 'employee2', 'password', 'That', 'Dude', 'bye@this.com', 1);

-- Test Reimbursement

INSERT INTO ers_reimbursement (reimb_amount, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)
	VALUES(100, 2, 1, 1, 1);
INSERT INTO ers_reimbursement (reimb_amount, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)
	VALUES(156, 2, 1, 2, 2);
INSERT INTO ers_reimbursement (reimb_amount, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)
	VALUES(1564, 2, 1, 3, 3);
INSERT INTO ers_reimbursement (reimb_amount, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)
	VALUES(965, 2, 1, 1, 4);

