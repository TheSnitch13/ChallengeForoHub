CREATE TABLE topico (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        titulo VARCHAR(200) NOT NULL,
                        mensaje TEXT NOT NULL,
                        fecha_creacion DATETIME NOT NULL,
                        status VARCHAR(50) NOT NULL,

                        autor_id BIGINT NOT NULL,
                        curso_id BIGINT NOT NULL,

                        PRIMARY KEY (id),

                        CONSTRAINT fk_topico_autor
                            FOREIGN KEY (autor_id)
                                REFERENCES usuario(id),

                        CONSTRAINT fk_topico_curso
                            FOREIGN KEY (curso_id)
                                REFERENCES curso(id)
);