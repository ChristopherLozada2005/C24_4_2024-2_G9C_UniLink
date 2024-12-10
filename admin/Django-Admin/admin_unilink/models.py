# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Amistad(models.Model):
    fecha_amistad = models.DateTimeField(blank=True, null=True)
    id = models.BigAutoField(primary_key=True)
    primer_usuario = models.ForeignKey('Usuarios', models.DO_NOTHING, db_column='primer_usuario', blank=True, null=True)
    segundo_usuario = models.ForeignKey('Usuarios', models.DO_NOTHING, db_column='segundo_usuario', related_name='amistad_segundo_usuario_set', blank=True, null=True)
    estado = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'amistad'


class Comentarios(models.Model):
    comentario_id_parent = models.ForeignKey('self', models.DO_NOTHING, db_column='comentario_id_parent', blank=True, null=True)
    fecha_comentario = models.DateTimeField(blank=True, null=True)
    id = models.BigAutoField(primary_key=True)
    publicacion = models.ForeignKey('Publicaciones', models.DO_NOTHING, blank=True, null=True)
    usuario = models.ForeignKey('Usuarios', models.DO_NOTHING, blank=True, null=True)
    texto = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'comentarios'


class Message(models.Model):
    status = models.JSONField(blank=True, null=True)
    id = models.BigAutoField(primary_key=True)
    date = models.CharField(max_length=255, blank=True, null=True)
    message = models.CharField(max_length=255, blank=True, null=True)
    receiver_name = models.CharField(max_length=255, blank=True, null=True)
    sender_name = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'message'


class Publicaciones(models.Model):
    fecha_publicacion = models.DateTimeField(blank=True, null=True)
    id = models.BigAutoField(primary_key=True)
    usuario = models.ForeignKey('Usuarios', models.DO_NOTHING)
    categoria = models.CharField(max_length=255, blank=True, null=True)
    descripcion = models.CharField(max_length=255, blank=True, null=True)
    imagen_url = models.CharField(max_length=255, blank=True, null=True)
    tiene_imagen = models.CharField(max_length=255, blank=True, null=True)
    titulo = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'publicaciones'


class Usuarios(models.Model):
    edad = models.IntegerField(blank=True, null=True)
    fecha_registro = models.DateTimeField(blank=True, null=True)
    id = models.BigAutoField(primary_key=True)
    apellido = models.CharField(max_length=255, blank=True, null=True)
    contraseña = models.CharField(max_length=255, blank=True, null=True)
    descripcion = models.CharField(max_length=255, blank=True, null=True)
    email = models.CharField(max_length=255, blank=True, null=True)
    genero = models.CharField(max_length=255, blank=True, null=True)
    nombre = models.CharField(max_length=255, blank=True, null=True)
    tiene_imagen = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'usuarios'
