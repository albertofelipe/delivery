CREATE  TABLE delivery (

	id                 BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    client_id          BIGINT NOT NULL,
    tax                DECIMAL(10,2) NOT NULL,
    status             VARCHAR(15) NOT NULL,
    order_date         DATETIME  NOT NULL,
    finish_order_date  DATETIME,

    receiver_name         VARCHAR(60) NOT NULL,
    receiver_street       VARCHAR(255) NOT NULL,
    receiver_number       VARCHAR(100) NOT NULL,
    receiver_neighborhood VARCHAR(100) NOT NULL,
    receiver_complement   VARCHAR(255),

    CONSTRAINT fk_delivery_client FOREIGN KEY (client_id) REFERENCES client (id)
);