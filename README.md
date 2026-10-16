# Taller 2 POO: La Fonda del Papu

## Descripción del Proyecto
Aplicación desarrollada en Java bajo los principios de la Programación Orientada a Objetos (POO) para gestionar los consumos de choripanes y terremotos en una fonda ("La Fonda del Papu"). El sistema permite visualizar registros, actualizar información (agregar, editar y eliminar) con persistencia automática en archivos de texto plano, y calcular métricas detalladas de consumo y días ebrio, utilizando exclusivamente vectores estáticos y restricciones estrictas de control de errores.

## Integrantes
* **Rocío Azucena Rojas Robledo** - 21.694.049-0 - Usuario GitHub: rarojas25

## Estructura del Proyecto y Paquetes
El código se encuentra organizado dentro del paquete `LafondadelPapu` con las siguientes clases principales:
* **`Main.java`**: Contiene el método principal y la cabecera obligatoria de identificación en las primeras líneas. Inicializa el sistema y lanza el menú interactivo.
* **`Fonda.java`**: Modela la fonda y administra los arreglos estáticos de participantes, choripanes y terremotos, concentrando la lógica de negocio y cálculo de métricas.
* **`Menu.java`**: Administra la interfaz interactiva por consola con validación robusta de entradas para evitar caídas del programa.
* **`GestorArchivos.java`**: Se encarga de la lectura tolerante de archivos (`Participantes.txt` y `Consumido.txt`) y de la persistencia de cambios en el almacenamiento.
* **`Participante.java`**: Modela a cada consumidor registrado.
* **`Choripan.java`**: Modela el consumo de choripanes y calcula sus kilocalorías según sus dimensiones.
* **`Terremoto.java`**: Modela el consumo de terremotos, calculando sus kilocalorías y litros.
* **`Utilidades.java`**: Clase de apoyo utilitaria para validación estricta de números, decimales, fechas (`YYYY-MM-DD`) y formato de tablas.

## Instrucciones de Clonación y Ejecución
1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/rarojas25/Taller02POO
