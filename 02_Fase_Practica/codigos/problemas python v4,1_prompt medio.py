# versión 4.1 - prompt medio
#PROBLEMA 1
import string

def es_palindromo(texto):
    """
    Comprueba si una cadena de texto es un palíndromo,
    ignorando espacios, signos de puntuación y mayúsculas/minúsculas.
    
    Parámetros:
        texto (str): La cadena de texto a comprobar.
        
    Retorna:
        bool: True si es un palíndromo, False en caso contrario.
    """
    if not isinstance(texto, str):  # Validación del tipo de dato
        raise ValueError("La entrada debe ser una cadena de texto (str).")
    
    # Convertimos a minúsculas, eliminamos signos de puntuación y espacios
    texto_limpio = ''.join(
        c.lower() for c in texto if c.isalnum()
    )

    # Comprobamos si la cadena limpia es igual a su reverso
    return texto_limpio == texto_limpio[::-1]


def main():
    """
    Función principal con un caso de prueba funcional.
    """
    try:
        # Caso de prueba: el clásico palíndromo
        frase = "Anita, lava la tina."
        print(f"¿'{frase}' es un palíndromo?")
        if es_palindromo(frase):
            print("¡Sí, es un palíndromo!")
        else:
            print("No, no es un palíndromo.")
    except Exception as error:
        print("Error:", error)

if __name__ == "__main__":
    main()
#PROBLEMA 2
def contar_frecuencias(cadena):
    """
    Cuenta la frecuencia de cada carácter en una cadena dada.
    Devuelve un diccionario ordenado alfabéticamente por los caracteres.
    """
    if not isinstance(cadena, str):
        raise TypeError("El argumento debe ser una cadena de texto (str).")

    frecuencias = {}
    for caracter in cadena:
        # Incrementa la cuenta del caracter o lo añade al diccionario si no existe
        frecuencias[caracter] = frecuencias.get(caracter, 0) + 1

    # Ordenamos el diccionario por los caracteres (claves)
    return dict(sorted(frecuencias.items()))


def main():
    try:
        # Caso de prueba funcional
        texto = "Hola, ChatGPT 2026!"
        resultado = contar_frecuencias(texto)
        print("Frecuencia de caracteres en la cadena:")
        for caracter, frecuencia in resultado.items():
            print(f"'{caracter}': {frecuencia}")
    except Exception as e:
        print(f"Ocurrió un error: {e}")


if __name__ == "__main__":
    main()

#PROBLEMA 3
import string

def palabra_mas_larga(frase):
    """
    Analiza una frase dada, ignora los signos de puntuación y retorna
    la palabra de mayor longitud.
    En caso de empate, retorna la primera palabra encontrada.
    
    :param frase: str, frase a analizar
    :return: str, palabra de mayor longitud
    :raises ValueError: si la frase está vacía o no contiene palabras válidas
    """
    if not isinstance(frase, str):
        raise TypeError("La entrada debe ser una cadena de texto (str).")
    if not frase.strip():
        raise ValueError("La frase está vacía.")
    
    # Eliminar signos de puntuación
    traductor = str.maketrans('', '', string.punctuation)
    frase_limpia = frase.translate(traductor)
    
    # Separar en palabras
    palabras = frase_limpia.split()
    
    if not palabras:
        raise ValueError("La frase no contiene palabras válidas.")

    # Encontrar la palabra más larga
    max_palabra = max(palabras, key=len)
    return max_palabra

def main():
    # Caso de prueba
    frase = "¡La programación en Python, aunque desafiante, es fantástica!"
    try:
        resultado = palabra_mas_larga(frase)
        print(f"La palabra de mayor longitud es: '{resultado}'")
    except Exception as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    main()

#PROBLEMA 4	
def es_primo(n):
    """
    Función que determina si un número entero n es primo.
    Retorna True si es primo, False en caso contrario.
    """
    # Los números menores que 2 no son primos
    if n < 2:
        return False

    # Solo necesitamos verificar hasta la raíz cuadrada de n
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            # Si es divisible por algún número, no es primo
            return False
    return True

def main():
    try:
        # Solicitamos al usuario un número entero
        entrada = input("Introduce un número entero para comprobar si es primo: ")
        numero = int(entrada)

        # Verificamos si es primo utilizando la función creada
        if es_primo(numero):
            print(f"El número {numero} es primo.")
        else:
            print(f"El número {numero} no es primo.")
    except ValueError:
        # Gestionamos el error si la entrada no es un número entero válido
        print("Error: Debes introducir un número entero válido.")

# Caso de prueba funcional automatizado
# Probamos con el número 7, que sí es primo
assert es_primo(7) == True
# Probamos con el número 9, que no es primo
assert es_primo(9) == False
# Probamos con el número 1, que no es primo
assert es_primo(1) == False

if __name__ == "__main__":
    main()
Problema Python 4 v 5.5 prompt experto
from __future__ import annotations


class PrimeChecker:
    """Servicio para determinar si un número entero es primo."""

    @staticmethod
    def is_prime(number: int) -> bool:
        """
        Determina si un número entero es primo.

        Args:
            number: Número entero que se desea evaluar.

        Returns:
            True si el número es primo; False en caso contrario.

        Raises:
            TypeError: Si el argumento no es un entero.
                       ``bool`` se rechaza explícitamente aunque en Python
                       sea una subclase de ``int``.
        """
        PrimeChecker._validate(number)

        # Por definición, los números menores que 2 no son primos.
        if number < 2:
            return False

        # Tratamiento explícito de los únicos primos pares/divisibles por 3.
        if number in (2, 3):
            return True

        if number % 2 == 0 or number % 3 == 0:
            return False

        # Todo primo > 3 tiene la forma 6k ± 1.
        # Basta comprobar divisores hasta sqrt(number). Se evita calcular
        # raíces cuadradas usando divisor <= number // divisor, eliminando
        # posibles problemas de precisión con números enteros muy grandes.
        divisor = 5
        while divisor <= number // divisor:
            if number % divisor == 0 or number % (divisor + 2) == 0:
                return False
            divisor += 6

        return True

    @staticmethod
    def _validate(number: int) -> None:
        """Valida estrictamente el contrato de entrada."""
        if isinstance(number, bool) or not isinstance(number, int):
            raise TypeError(
                f"number debe ser un entero, se recibió {type(number).__name__}"
            )


def is_prime(number: int) -> bool:
    """API funcional y sencilla para comprobar primalidad."""
    return PrimeChecker.is_prime(number)

#PROBLEMA 5
def encontrar_max_min(coleccion):
    """
    Función para encontrar el valor máximo y mínimo en una colección de enteros.
    Devuelve una tupla (minimo, maximo).
    Si la colección es nula o vacía, lanza una excepción ValueError.
    """

    # Verificamos si la colección es None
    if coleccion is None:
        raise ValueError("La colección no puede ser None (nula).")

    # Verificamos si la colección está vacía
    if not isinstance(coleccion, (list, tuple, set)):
        raise TypeError("La colección debe ser de tipo lista, tupla o conjunto.")

    if len(coleccion) == 0:
        raise ValueError("La colección está vacía. No se pueden encontrar valores mínimo y máximo.")

    # Inicializamos los valores mínimo y máximo con el primer elemento
    iterador = iter(coleccion)
    try:
        primero = next(iterador)
    except StopIteration:
        raise ValueError("La colección está vacía.")

    # Validamos que el primer elemento sea un entero
    if not isinstance(primero, int):
        raise TypeError("Todos los elementos deben ser enteros.")

    minimo = maximo = primero

    # Recorremos el resto de los elementos manualmente
    for elemento in iterador:
        # Validamos que cada elemento sea un entero
        if not isinstance(elemento, int):
            raise TypeError("Todos los elementos deben ser enteros.")
        if elemento < minimo:
            minimo = elemento
        if elemento > maximo:
            maximo = elemento

    return minimo, maximo


# Caso de prueba funcional
if __name__ == "__main__":
    try:
        ejemplo = [10, 3, 25, -7, 15, 0]
        resultado = encontrar_max_min(ejemplo)
        print(f"Lista: {ejemplo}")
        print(f"Mínimo: {resultado[0]}, Máximo: {resultado[1]}")
    except Exception as e:
        print(f"Error: {e}")

    # Prueba con lista vacía
    try:
        vacia = []
        resultado = encontrar_max_min(vacia)
    except Exception as e:
        print(f"Caso lista vacía: {e}")

    # Prueba con None
    try:
        none_case = None
        resultado = encontrar_max_min(none_case)
    except Exception as e:
        print(f"Caso None: {e}")

    # Prueba con un elemento no entero
    try:
        mal_tipo = [1, 2, 'a', 4]
        resultado = encontrar_max_min(mal_tipo)
    except Exception as e:
        print(f"Caso tipo incorrecto: {e}")

# PROBLEMA 6
# Definimos la clase Factorial para implementar ambos métodos de cálculo
class Factorial:
    @staticmethod
    def iterativo(n):
        """
        Calcula el factorial de n de forma iterativa.
        Lanza ValueError si n no es entero o si es un entero negativo.
        """
        # Comprobación de entrada válida
        if not isinstance(n, int):
            raise ValueError("El valor debe ser un número entero.")
        if n < 0:
            raise ValueError("El número debe ser positivo o cero.")
        
        resultado = 1
        for i in range(2, n+1):
            resultado *= i
        return resultado

    @staticmethod
    def recursivo(n):
        """
        Calcula el factorial de n de forma recursiva.
        Lanza ValueError si n no es entero o si es un entero negativo.
        """
        # Comprobación de entrada válida
        if not isinstance(n, int):
            raise ValueError("El valor debe ser un número entero.")
        if n < 0:
            raise ValueError("El número debe ser positivo o cero.")

        # Caso base
        if n == 0 or n == 1:
            return 1
        # Llamada recursiva
        return n * Factorial.recursivo(n - 1)

if __name__ == "__main__":
    # Caso de prueba funcional
    numero = 5

    # Demostramos el uso de ambos métodos
    try:
        print(f"Factorial iterativo de {numero}: {Factorial.iterativo(numero)}")
        print(f"Factorial recursivo de {numero}: {Factorial.recursivo(numero)}")
    except ValueError as e:
        print(f"Error: {e}")

    # Caso de prueba: comprobación de errores
    try:
        Factorial.iterativo(-3)  # Esto debe generar una excepción
    except ValueError as e:
        print(f"Gestión de error iterativo: {e}")

    try:
        Factorial.recursivo(3.5) # Esto debe generar una excepción
    except ValueError as e:
        print(f"Gestión de error recursivo: {e}")

#PROBLEMA 7
# Calculadora básica con manejo de excepciones
def suma(a, b):
    """Realiza la suma de dos números."""
    return a + b

def resta(a, b):
    """Realiza la resta de dos números."""
    return a - b

def multiplicacion(a, b):
    """Realiza la multiplicación de dos números."""
    return a * b

def division(a, b):
    """Realiza la división de dos números, manejando división por cero."""
    if b == 0:
        # Lanzamos una excepción personalizada para división por cero
        raise ZeroDivisionError("Error: No se puede dividir entre cero.")
    return a / b

def calculadora(operacion, a, b):
    """
    Realiza la operación solicitada entre a y b.
    Parámetros:
      - operacion: str -> '+', '-', '*', '/'
      - a: float/int
      - b: float/int
    Retorna:
      - El resultado de la operación.
    Maneja excepciones si la operación es inválida o hay errores de tipo.
    """
    try:
        if operacion == '+':
            return suma(a, b)
        elif operacion == '-':
            return resta(a, b)
        elif operacion == '*':
            return multiplicacion(a, b)
        elif operacion == '/':
            return division(a, b)
        else:
            # Si la operación no es reconocida
            raise ValueError(f"Operación inválida: {operacion}")
    except ZeroDivisionError as zde:
        # Lanza mensaje específico de división por cero
        print(zde)
    except TypeError:
        # Si los operandos no son números
        print("Error: Los operandos deben ser números.")
    except Exception as e:
        # Para cualquier otro error
        print(f"Error inesperado: {e}")

# Caso de prueba funcional
if __name__ == "__main__":
    # Puedes cambiar los valores aquí para probar otras operaciones y errores
    a = 10
    b = 0
    operaciones = ['+', '-', '*', '/']

    for oper in operaciones:
        print(f"\nPrueba: {a} {oper} {b}")
        resultado = calculadora(oper, a, b)
        if resultado is not None:
            print("Resultado:", resultado)

#PROBLEMA 8
# Excepción personalizada para libros ya prestados
class LibroPrestadoError(Exception):
    pass

class Libro:
    """
    Clase que representa un libro en la biblioteca.
    """
    def __init__(self, titulo, autor):
        self.titulo = titulo
        self.autor = autor
        self.prestado = False

    def __str__(self):
        estado = 'Prestado' if self.prestado else 'Disponible'
        return f'"{self.titulo}" de {self.autor} ({estado})'

class Biblioteca:
    """
    Clase que representa la biblioteca, gestionando su colección de libros.
    """
    def __init__(self):
        # Diccionario para almacenar libros usando el título como clave
        self.libros = {}

    def añadir_libro(self, libro):
        if libro.titulo in self.libros:
            raise ValueError(f'El libro "{libro.titulo}" ya está en la biblioteca.')
        self.libros[libro.titulo] = libro

    def prestar_libro(self, titulo):
        if titulo not in self.libros:
            raise KeyError(f'El libro "{titulo}" no existe en la biblioteca.')
        libro = self.libros[titulo]
        if libro.prestado:
            raise LibroPrestadoError(f'El libro "{titulo}" ya está prestado.')
        libro.prestado = True

    def devolver_libro(self, titulo):
        if titulo not in self.libros:
            raise KeyError(f'El libro "{titulo}" no existe en la biblioteca.')
        libro = self.libros[titulo]
        if not libro.prestado:
            raise ValueError(f'El libro "{titulo}" no está prestado.')
        libro.prestado = False

    def mostrar_libros(self):
        for libro in self.libros.values():
            print(libro)

# --- Caso de prueba funcional ---

def caso_de_prueba():
    biblioteca = Biblioteca()

    # Crear unos libros
    libro1 = Libro('Cien Años de Soledad', 'Gabriel García Márquez')
    libro2 = Libro('1984', 'George Orwell')

    # Añadir libros
    biblioteca.añadir_libro(libro1)
    biblioteca.añadir_libro(libro2)

    # Mostrar disponibilidad inicial
    print('Estado inicial de la biblioteca:')
    biblioteca.mostrar_libros()

    # Prestar un libro
    try:
        print('\nPrestando "1984"...')
        biblioteca.prestar_libro('1984')
    except Exception as e:
        print(e)

    # Intentar prestar de nuevo el mismo libro
    try:
        print('Intentando prestar de nuevo "1984"...')
        biblioteca.prestar_libro('1984')
    except LibroPrestadoError as e:
        print('Error:', e)

    # Devolver el libro
    try:
        print('\nDevolviendo "1984"...')
        biblioteca.devolver_libro('1984')
    except Exception as e:
        print(e)

    # Estado final de la biblioteca
    print('\nEstado final de la biblioteca:')
    biblioteca.mostrar_libros()

if __name__ == "__main__":
    caso_de_prueba()
