INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Magia', 'Gomez', 'magiagomez@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 5, 'Desarrollador de software', 'yes');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Mayo', 'Mazgo', 'mayomazgo@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 4, 'Productor y Gestor Industrial', 'yes');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Ana', 'Garcia', 'anagarcia@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 34, 'Desarrolladora de software', 'yes');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Carlos', 'Lopez', 'carloslopez@gmail.com', '$2a$12$oiLNz0jdcZXhrXds0.oQaOx32El87zPTxBNgp9eRKeDWB5nA15FYe', 30, 'Administrador de redes y comunicaciones', 'yes');

INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('Proyecto de Software', 'Desarrollo de un sistema de gestión para pequeñas empresas', 'Curricular', CURRENT_TIMESTAMP, 1, 'yes');
INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('Evento de Capacitación', 'Organización de un taller sobre producción industrial sostenible', 'Extracurricular', CURRENT_TIMESTAMP, 2, 'yes');
INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('Aplicación Web Innovadora', 'Creación de una plataforma web para gestión educativa', 'Curricular', CURRENT_TIMESTAMP, 3, 'yes');
INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('Conferencia de Seguridad', 'Participación como ponente en una conferencia de ciberseguridad', 'Extracurricular', CURRENT_TIMESTAMP, 4, 'yes');

INSERT INTO comentarios(texto, publicacion_id, usuario_id, fecha_comentario) VALUES('Me parece una idea interesante para pequeñas empresas.', 1, 2, CURRENT_TIMESTAMP);
INSERT INTO comentarios(texto, publicacion_id, usuario_id, fecha_comentario) VALUES('¡Excelente taller! Espero asistir al próximo evento.', 2, 3, CURRENT_TIMESTAMP);
INSERT INTO comentarios(texto, publicacion_id, usuario_id, fecha_comentario) VALUES('Este tipo de plataformas son muy útiles para la educación.', 3, 4, CURRENT_TIMESTAMP);
INSERT INTO comentarios(texto, publicacion_id, usuario_id, fecha_comentario) VALUES('Una excelente ponencia, aprendí mucho sobre seguridad informática.', 4, 1, CURRENT_TIMESTAMP);

INSERT INTO amistad(primer_usuario, segundo_usuario, fecha_amistad, estado) VALUES(1, 2,CURRENT_TIMESTAMP, 'PENDING');
INSERT INTO amistad(primer_usuario, segundo_usuario, fecha_amistad, estado) VALUES(2, 1,CURRENT_TIMESTAMP, 'RECEIVED');
