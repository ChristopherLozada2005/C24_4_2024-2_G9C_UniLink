from django.shortcuts import render
from rest_framework import viewsets
from .models import Usuarios, Publicaciones, Comentarios
from .serializers import UsuarioSerializer, PublicacionSerializer, ComentarioSerializer

class UsuarioViewSet(viewsets.ModelViewSet):
    queryset = Usuarios.objects.all()
    serializer_class = UsuarioSerializer

class PublicacionViewSet(viewsets.ModelViewSet):
    queryset = Publicaciones.objects.all()
    serializer_class = PublicacionSerializer

class ComentarioViewSet(viewsets.ModelViewSet):
    queryset = Comentarios.objects.all()
    serializer_class = ComentarioSerializer
