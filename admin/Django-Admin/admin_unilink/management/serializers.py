from rest_framework import serializers
from .models import Usuarios, Publicaciones, Comentarios

class UsuarioSerializer(serializers.ModelSerializer):
    class Meta:
        model = Usuarios
        fields = '__all__'

class PublicacionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Publicaciones
        fields = '__all__'

class ComentarioSerializer(serializers.ModelSerializer):
    class Meta:
        model = Comentarios
        fields = '__all__'    
