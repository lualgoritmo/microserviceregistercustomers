CREATE TABLE tb_collaborator (
  id_collaborator UUID NOT NULL,
  name_surname VARCHAR(255),
  date_of_birth date,
  registration_date TIMESTAMP WITHOUT TIME ZONE,
  cpf VARCHAR(255),
  cep VARCHAR(255),
  number_residence VARCHAR(255),
  phone VARCHAR(255),
  rg VARCHAR(255),
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255),
  CONSTRAINT pk_tb_collaborator PRIMARY KEY (id_collaborator)
);

CREATE TABLE tb_collaborator_schedule (
  collaborator_id_collaborator UUID NOT NULL,
  schedule_id_shedule UUID NOT NULL,
  CONSTRAINT pk_tb_collaborator_schedule PRIMARY KEY (collaborator_id_collaborator, schedule_id_shedule)
);

ALTER TABLE tb_collaborator ADD CONSTRAINT uc_tb_collaborator_email UNIQUE (email);

ALTER TABLE tb_collaborator_schedule
  ADD CONSTRAINT fk_tbcolsch_on_collaborator FOREIGN KEY (collaborator_id_collaborator) REFERENCES tb_collaborator (id_collaborator);

