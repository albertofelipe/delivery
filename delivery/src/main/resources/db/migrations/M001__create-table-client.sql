CREATE  TABLE client (

	id    BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR (60) NOT NULL,
    email VARCHAR (100) NOT NULL,
    phone VARCHAR (20) DEFAULT NULL,

)