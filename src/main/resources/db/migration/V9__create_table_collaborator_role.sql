
CREATE TABLE collaborator_role (
    id UUID NOT NULL,
    collaborator_id UUID NOT NULL,
    role_id UUID NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (collaborator_id) REFERENCES tb_collaborator (id_collaborator),
    FOREIGN KEY (role_id) REFERENCES role (id)
);
