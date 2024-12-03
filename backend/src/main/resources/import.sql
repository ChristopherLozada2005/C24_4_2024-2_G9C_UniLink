INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Magia', 'Cat', 'magia@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 5, 'Just a magia cat who likes to eat and sleep', 'no');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Mayo', 'Cat', 'mayo@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 4, 'Just a mayo cat who likes to eat and sleep', 'yes');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Juan', 'Perez', 'juanperez@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 28, 'Amante de la tecnología', 'yes');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Ana', 'Garcia', 'anagarcia@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 34, 'Desarrolladora de software', 'yes');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Carlos', 'López', 'carloslopez@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 25, 'Programador web', 'yes');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Maria', 'Torres', 'mariatorres@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 30, 'Entusiasta de la IA', 'yes');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Luis', 'Fernández', 'luisfernandez@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 22, 'Creador de contenido', 'yes');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Elena', 'Rodríguez', 'elenarodriguez@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 29, 'Diseñadora gráfica', 'yes');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Pedro', 'Martínez', 'pedromartinez@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 35, 'Ingeniero de software', 'yes');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Sara', 'Gómez', 'saragomez@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 26, 'Gestora de proyectos', 'yes');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Jorge', 'González', 'jorgegonzalez@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 32, 'Consultor IT', 'yes');
INSERT INTO usuarios(nombre, apellido, email, contraseña, edad, descripcion, tiene_imagen) VALUES('Raquel', 'Sánchez', 'raquelsanchez@gmail.com', '$2a$12$ailEZo1fObkgatzOD.e1qe6/MlQ.7ZocF5Qn08VFRd2TrC5u.kL9S', 27, 'Desarrolladora móvil', 'yes');

INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('Lanzamiento de nuevo producto', 'Anunciamos un nuevo producto al mercado.', 'Tecnología', CURRENT_TIMESTAMP, 1, 'yes');
INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('Actualización de software', 'Nuestra última actualización trae nuevas características.', 'Software', CURRENT_TIMESTAMP, 2, 'yes');
INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('Conferencia sobre IA', 'Explorando el futuro de la inteligencia artificial.', 'Eventos', CURRENT_TIMESTAMP, 3, 'yes');
INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('Tutorial de Java', 'Aprende Java con nuestro nuevo tutorial paso a paso.', 'Educación', CURRENT_TIMESTAMP, 4, 'yes');
INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('Nueva versión de la app', 'La versión 2.0 de nuestra app ya está disponible.', 'Aplicaciones', CURRENT_TIMESTAMP, 5, 'yes');
INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('Retos de programación', '¡Desafía tu mente con estos nuevos retos!', 'Desarrollo', CURRENT_TIMESTAMP, 6, 'yes');
INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('El futuro de la web', 'Cómo la web seguirá evolucionando en los próximos años.', 'Tecnología', CURRENT_TIMESTAMP, 7, 'yes');
INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('Sostenibilidad y tecnología', 'Discutimos el impacto de la tecnología en el medio ambiente.', 'Sostenibilidad', CURRENT_TIMESTAMP, 8, 'yes');
INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('Innovaciones en inteligencia artificial', 'Lo último en avances de IA y su aplicación.', 'Innovación', CURRENT_TIMESTAMP, 9, 'yes');
INSERT INTO publicaciones(titulo, descripcion, categoria, fecha_publicacion, usuario_id, tiene_imagen) VALUES('Hackathon 2024', 'Únete a nuestro hackathon este verano y gana increíbles premios.', 'Eventos', CURRENT_TIMESTAMP, 10, 'yes');

INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('¡Me encanta!', 1, 1);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('Este nuevo producto suena increíble, ¿cuándo estará disponible?', 1, 2);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('La actualización es genial, las nuevas características son muy útiles.', 2, 3);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('¡Quiero asistir a la conferencia sobre IA! ¿Dónde puedo registrarme?', 3, 4);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('Este tutorial de Java me parece perfecto para empezar. ¡Gracias por compartirlo!', 4, 5);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('La versión 2.0 de la app tiene un gran potencial, ¡espero que funcione sin problemas!', 5, 6);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('Me encanta participar en retos de programación. ¡Estoy listo para el desafío!', 6, 7);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('El futuro de la web es fascinante, ¡estoy emocionado por lo que está por venir!', 7, 8);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('La sostenibilidad es un tema fundamental, y la tecnología tiene un gran papel en ello.', 8, 9);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('¡Cuenten conmigo para el hackathon! Será una gran oportunidad de aprender y ganar.', 10, 10);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('Me gustaría ver más tutoriales como este de Java. ¡Gracias por la información!', 4, 2);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('La nueva versión de la app es muy estable. ¿Habrá más actualizaciones pronto?', 5, 3);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('Los retos de programación siempre son interesantes. Me encantaría participar.', 6, 4);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('¿Cuál es la fecha exacta de la conferencia sobre IA? Estoy muy interesado.', 3, 5);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('La tecnología está cambiando todo a un ritmo impresionante. Este artículo lo explica perfectamente.', 7, 6);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('El tema de la sostenibilidad nunca debe dejar de ser relevante. ¡Espero ver más artículos de este tipo!', 8, 7);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('El hackathon será una excelente oportunidad para poner a prueba nuestras habilidades. ¡Nos vemos ahí!', 10, 8);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('Me ha sorprendido cómo el producto ha evolucionado. ¿Tienen más planes para el futuro?', 1, 9);
INSERT INTO comentarios(texto, publicacion_id, usuario_id) VALUES('Las nuevas características realmente mejoran la experiencia del usuario. ¡Excelente trabajo!', 2, 10);