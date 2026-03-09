CREATE TABLE respuesta (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           mensaje TEXT NOT NULL,
                           fecha_creacion DATETIME NOT NULL,
                           solucion BOOLEAN NOT NULL,

                           topico_id BIGINT NOT NULL,
                           autor_id BIGINT NOT NULL,

                           PRIMARY KEY (id),

                           CONSTRAINT fk_respuesta_topico
                               FOREIGN KEY (topico_id)
                                   REFERENCES topico(id),

                           CONSTRAINT fk_respuesta_autor
                               FOREIGN KEY (autor_id)
                                   REFERENCES usuario(id)
);