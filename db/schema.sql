CREATE TABLE world.batch (
	id BIGINT NOT NULL,
	creation_date DATETIME NULL,
	end_date DATETIME NULL,
	start_date DATETIME NULL,
	threshold INT NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (id)
) ;

CREATE TABLE world.blocked (
	id BIGINT NOT NULL,
	address VARCHAR(255) NULL,
	comment VARCHAR(255) NULL,
	count INT NULL,
	batch_id BIGINT NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (id)
) ;
CREATE INDEX FK8rpr2a9hgylx7pn87iqyo5dsn ON world.blocked (batch_id) ;

CREATE TABLE world.request (
	id BIGINT NOT NULL,
	address VARCHAR(255) NULL,
	`data` VARCHAR(255) NULL,
	`date` DATETIME NULL,
	batch_id BIGINT NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (id)
) ;
CREATE INDEX FK9bb0dnf6numky6owyorlw0njc ON world.request (batch_id) ;
