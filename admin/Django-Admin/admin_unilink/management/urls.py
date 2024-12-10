from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import UsuarioViewSet, PublicacionViewSet, ComentarioViewSet

router = DefaultRouter()
router.register(r'usuarios', UsuarioViewSet)
router.register(r'publicaciones', PublicacionViewSet)
router.register(r'comentarios', ComentarioViewSet)

urlpatterns = [
    path('api/', include(router.urls)),
]
