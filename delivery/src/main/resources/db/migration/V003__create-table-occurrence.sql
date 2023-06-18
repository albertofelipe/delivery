CREATE  TABLE occurrence (

	id                 BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    delivery_id        BIGINT NOT NULL,
    description  TEXT NOT NULL,
    registration_date  DATETIME  NOT NULL,

    CONSTRAINT fk_occurrence_delivery FOREIGN KEY (delivery_id) REFERENCES delivery (id)
);