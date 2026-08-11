#VERSION 4-1 PROMPT EXPERTO
#PROBLEMA1 
import re

class InvalidInputError(Exception):
    """Excepción personalizada para entradas inválidas."""
    pass

class PalindromeChecker:
    """Clase para comprobar si una cadena es un palíndromo, ignorando
    mayúsculas, minúsculas, espacios y signos de puntuación."""

    @staticmethod
    def is_palindrome(text: str) -> bool:
        """
        Comprueba si el texto proporcionado es un palíndromo.

        Args:
            text (str): La cadena de texto a comprobar.

        Returns:
            bool: True si es palíndromo, False en caso contrario.

        Raises:
            InvalidInputError: Si la entrada no es del tipo str.
        """

        if not isinstance(text, str):
            raise InvalidInputError("La entrada debe ser una cadena de texto (str).")

        # Normalizar: eliminar todo excepto letras y números, y convertir a minúsculas
        normalized = re.sub(r'[^a-zA-Z0-9]', '', text).lower()

        # Comprobar si la cadena normalizada es igual a su inversa
        return normalized == normalized[::-1]


# Ejemplo de uso y manejo de excepciones
if __name__ == "__main__":
    try:
        entrada = "¿Acaso hubo búhos acá?"
        if PalindromeChecker.is_palindrome(entrada):
            print(f'"{entrada}" es un palíndromo.')
        else:
            print(f'"{entrada}" NO es un palíndromo.')
    except InvalidInputError as e:
        print(f"Error de entrada: {e}")

#PROBLEMA 2
from collections import Counter, OrderedDict
from typing import Dict

class CharacterFrequencyCounter:
    """
    Clase para encapsular la lógica de conteo de frecuencia de caracteres en una cadena dada.
    """

    @staticmethod
    def count_frequencies(text: str) -> Dict[str, int]:
        """
        Cuenta la frecuencia de cada carácter en la cadena de entrada.

        Parámetros:
            text (str): Cadena para analizar.

        Retorna:
            OrderedDict: Diccionario ordenado por frecuencia de caracteres (descendente).

        Lanza:
            TypeError: Si el argumento no es una cadena.
            ValueError: Si la cadena está vacía.
        """
        if not isinstance(text, str):
            raise TypeError('La entrada debe ser una cadena de caracteres (str).')
        if not text:
            raise ValueError('La cadena de entrada no puede estar vacía.')

        # Contar la frecuencia de cada carácter
        freq_counter = Counter(text)

        # Ordenar por frecuencia descendente y alfabéticamente ante empates
        sorted_frequencies = OrderedDict(
            sorted(freq_counter.items(), key=lambda item: (-item[1], item[0]))
        )

        return sorted_frequencies


def main():
    """
    Función principal que solicita al usuario una cadena y muestra la frecuencia de caracteres.
    Maneja excepciones pertinentes.
    """
    try:
        user_input = input("Introduce una cadena para analizar la frecuencia de caracteres: ")
        frequencies = CharacterFrequencyCounter.count_frequencies(user_input)
        print("Frecuencia de caracteres (orden descendente):")
        for char, count in frequencies.items():
            print(f"'{char}': {count}")
    except (TypeError, ValueError) as error:
        print(f"Error: {error}")


if __name__ == "__main__":
    main()

#PROBLEMA 3
import re

class LongestWordFinderError(Exception):
    """Excepción personalizada para errores en LongestWordFinder."""
    pass

class LongestWordFinder:
    """
    Encapsula la lógica para encontrar la palabra de mayor longitud en una frase.
    Ignora signos de puntuación y maneja robustamente entradas inválidas.
    """

    def find_longest_word(self, phrase):
        """
        Encuentra la palabra de mayor longitud en la frase dada.
        Args:
            phrase (str): Frase a analizar.
        Returns:
            str: Palabra de mayor longitud.
        Raises:
            LongestWordFinderError: Si la frase no es válida o no contiene palabras.
        """
        try:
            self._validate_input(phrase)
            words = self._extract_words(phrase)
            if not words:
                raise LongestWordFinderError("La frase no contiene palabras válidas.")
            return self._get_longest_word(words)
        except Exception as e:
            # Encapsula y relanza cualquier excepción relevante
            if isinstance(e, LongestWordFinderError):
                raise
            raise LongestWordFinderError(f"Error procesando la frase: {e}")

    def _validate_input(self, phrase):
        """Valida que la entrada sea una cadena no vacía."""
        if not isinstance(phrase, str):
            raise LongestWordFinderError("La entrada debe ser una cadena de texto.")
        if phrase.strip() == "":
            raise LongestWordFinderError("La frase no puede estar vacía.")

    def _extract_words(self, phrase):
        """Extrae palabras ignorando signos de puntuación."""
        return re.findall(r'\b\w+\b', phrase, re.UNICODE)

    def _get_longest_word(self, words):
        """Obtiene la palabra de mayor longitud."""
        # En caso de empate, retorna la primera aparición
        return max(words, key=len)

# -------- Ejemplo de uso --------
if __name__ == "__main__":
    finder = LongestWordFinder()
    try:
        frase = "¡Hola, mundo! ¿Cuál es la palabra-más-larga aquí?"
        print(f"La palabra más larga es: '{finder.find_longest_word(frase)}'")
    except LongestWordFinderError as error:
        print(f"Error: {error}")

#PROBLEMA 4
Python
from math import isqrt

class InvalidInputError(ValueError):
    """
    Excepción personalizada para entradas no válidas
    """
    pass

class PrimeChecker:
    """
    Clase que encapsula la lógica para determinar si un número entero es primo.
    """

    @staticmethod
    def is_prime(n: int) -> bool:
        """
        Determina si el número dado es primo.
        
        Args:
            n (int): Número entero a comprobar.
        
        Returns:
            bool: True si es primo, False en caso contrario.

        Raises:
            InvalidInputError: Si la entrada no es un número entero.
        """
        if not isinstance(n, int):
            raise InvalidInputError("El valor debe ser un número entero.")

        if n <= 1:
            return False
        if n <= 3:
            # 2 y 3 son primos
            return True
        if n % 2 == 0 or n % 3 == 0:
            return False

        # Prueba divisores hasta sqrt(n), solo los impares
        for i in range(5, isqrt(n) + 1, 6):
            if n % i == 0 or n % (i + 2) == 0:
                return False
        return True

# Ejemplo de uso
if __name__ == '__main__':
    checker = PrimeChecker()
    try:
        n = 29
        print(f"{n} es primo? {checker.is_prime(n)}")
    except InvalidInputError as e:
        print(f"Error: {e}")

#PROBLEMA 5
from typing import Iterable, Tuple, Optional

class CollectionAnalyzer:
    """
    Clase encargada de analizar colecciones de enteros.
    Proporciona métodos para encontrar el valor máximo y mínimo de forma segura.
    """

    @staticmethod
    def get_min_max(numbers: Optional[Iterable[int]]) -> Tuple[int, int]:
        """
        Obtiene el valor mínimo y máximo de una colección de enteros.

        Args:
            numbers (Optional[Iterable[int]]): Colección de enteros.

        Returns:
            Tuple[int, int]: (mínimo, máximo)

        Raises:
            ValueError: Si la colección es None o está vacía.
            TypeError: Si algún elemento no es un entero.
        """

        if numbers is None:
            raise ValueError("La colección proporcionada es None.")

        iterator = iter(numbers)
        try:
            first = next(iterator)
        except StopIteration:
            raise ValueError("La colección está vacía.")

        if not isinstance(first, int):
            raise TypeError("Todos los elementos deben ser enteros.")

        min_value = max_value = first

        for idx, value in enumerate(iterator, start=1):
            if not isinstance(value, int):
                raise TypeError(f"Elemento en la posición {idx} no es un entero.")
            if value < min_value:
                min_value = value
            elif value > max_value:
                max_value = value

        return min_value, max_value

# Ejemplo de uso:
if __name__ == "__main__":
    collection = [10, 3, 44, 2, -8, 15]
    try:
        minimum, maximum = CollectionAnalyzer.get_min_max(collection)
        print(f"Min: {minimum}, Max: {maximum}")
    except (ValueError, TypeError) as e:
        print(f"Error: {e}")

#PROBLEMA 6
class FactorialCalculator:
    """
    Proporciona métodos para calcular el factorial de un entero positivo
    tanto de forma recursiva como iterativa.
    """

    @staticmethod
    def factorial_iterativo(n: int) -> int:
        """
        Calcula el factorial de un número usando un enfoque iterativo.

        :param n: Entero positivo cuyo factorial se desea calcular.
        :return: Factorial de n.
        :raises ValueError: Si n no es un entero positivo.
        """
        if not isinstance(n, int):
            raise ValueError("El valor debe ser un entero.")
        if n < 0:
            raise ValueError("El valor debe ser un entero positivo o cero.")

        resultado = 1
        for i in range(2, n + 1):
            resultado *= i
        return resultado

    @staticmethod
    def factorial_recursivo(n: int) -> int:
        """
        Calcula el factorial de un número usando un enfoque recursivo.

        :param n: Entero positivo cuyo factorial se desea calcular.
        :return: Factorial de n.
        :raises ValueError: Si n no es un entero positivo.
        """
        if not isinstance(n, int):
            raise ValueError("El valor debe ser un entero.")
        if n < 0:
            raise ValueError("El valor debe ser un entero positivo o cero.")

        # Caso base
        if n in (0, 1):
            return 1
        # Caso recursivo
        return n * FactorialCalculator.factorial_recursivo(n - 1)


def demo():
    """
    Demuestra el uso de ambas funciones y el manejo de excepciones.
    """
    ejemplos = [5, 0, -3, 3.5]  # Casos correctos y casos de error
    for valor in ejemplos:
        print(f"\nCalculando el factorial de {valor}:")
        try:
            iterativo = FactorialCalculator.factorial_iterativo(valor)
            recursivo = FactorialCalculator.factorial_recursivo(valor)
            print(f"Iterativo: {iterativo}")
            print(f"Recursivo: {recursivo}")
        except ValueError as error:
            print(f"Error: {error}")


if __name__ == "__main__":
    demo()

#PROBLEMA 7
class DivisionByZeroError(Exception):
    """
    Excepción personalizada para división por cero.
    """
    pass


class InvalidOperationError(Exception):
    """
    Excepción personalizada para operaciones no soportadas.
    """
    pass


class Calculator:
    """
    Calculadora básica que soporta suma, resta, multiplicación y división.
    Utiliza encapsulamiento y manejo riguroso de excepciones.
    """

    def add(self, a: float, b: float) -> float:
        """Retorna la suma de dos números."""
        return a + b

    def subtract(self, a: float, b: float) -> float:
        """Retorna la resta de dos números."""
        return a - b

    def multiply(self, a: float, b: float) -> float:
        """Retorna el producto de dos números."""
        return a * b

    def divide(self, a: float, b: float) -> float:
        """Retorna la división de dos números, controlando división por cero."""
        if b == 0:
            raise DivisionByZeroError("ERROR: No se puede dividir por cero.")
        return a / b

    def calculate(self, a: float, b: float, operator: str) -> float:
        """
        Ejecuta la operación indicada entre 'a' y 'b'.

        Params:
            a: Operando izquierdo (float o int).
            b: Operando derecho (float o int).
            operator: '+', '-', '*' o '/'.

        Returns:
            Resultado numérico de la operación.

        Raises:
            DivisionByZeroError, InvalidOperationError, TypeError
        """

        # Verificar tipos de entrada
        if not isinstance(a, (int, float)) or not isinstance(b, (int, float)):
            raise TypeError("Ambos operandos deben ser numéricos (int o float).")

        if operator == '+':
            return self.add(a, b)
        elif operator == '-':
            return self.subtract(a, b)
        elif operator == '*':
            return self.multiply(a, b)
        elif operator == '/':
            return self.divide(a, b)
        else:
            raise InvalidOperationError(f"Operación no soportada: {operator}")


# Ejemplo de uso controlado
if __name__ == "__main__":
    calc = Calculator()

    # Lista de pruebas para demostrar el manejo de excepciones y operaciones
    pruebas = [
        (10, 5, '+'),
        (10, 5, '-'),
        (10, 5, '*'),
        (10, 5, '/'),
        (10, 0, '/'),        # División por cero
        (10, 'a', '+'),      # Tipo inválido
        (10, 5, '^')         # Operación inválida
    ]

    for a, b, op in pruebas:
        try:
            resultado = calc.calculate(a, b, op)
            print(f"{a} {op} {b} = {resultado}")
        except Exception as e:
            print(f"Error al calcular {a} {op} {b}: {e}")

#PROBLEMA 8
from typing import Dict, Optional


class LibroYaPrestadoException(Exception):
    """Excepción lanzada cuando se intenta prestar un libro que ya está prestado."""
    pass


class LibroNoPrestadoException(Exception):
    """Excepción lanzada cuando se intenta devolver un libro que no está prestado."""
    pass


class LibroNoExisteException(Exception):
    """Excepción lanzada cuando se intenta operar sobre un libro que no existe en la biblioteca."""
    pass


class Libro:
    def __init__(self, codigo: str, titulo: str, autor: str):
        self._codigo = codigo
        self._titulo = titulo
        self._autor = autor
        self._prestado = False  # Estado interno, privado

    @property
    def codigo(self) -> str:
        """Devuelve el código único del libro."""
        return self._codigo

    @property
    def titulo(self) -> str:
        """Devuelve el título del libro."""
        return self._titulo

    @property
    def autor(self) -> str:
        """Devuelve el autor del libro."""
        return self._autor

    @property
    def esta_prestado(self) -> bool:
        """Indica si el libro está prestado."""
        return self._prestado

    def prestar(self) -> None:
        """Marca el libro como prestado si no lo está, lanza excepción en caso contrario."""
        if self._prestado:
            raise LibroYaPrestadoException(f'El libro "{self._titulo}" ya está prestado.')
        self._prestado = True

    def devolver(self) -> None:
        """Marca el libro como no prestado, lanza excepción si ya estaba disponible."""
        if not self._prestado:
            raise LibroNoPrestadoException(f'El libro "{self._titulo}" no fue prestado.')
        self._prestado = False

    def __repr__(self) -> str:
        estado = "Prestado" if self._prestado else "Disponible"
        return f"<Libro {self._codigo}: {self._titulo} ({self._autor}) [{estado}]>"


class Biblioteca:
    def __init__(self):
        # Diccionario privado para almacenar libros por código único
        self._libros: Dict[str, Libro] = {}

    def agregar_libro(self, libro: Libro) -> None:
        """Agrega un libro a la biblioteca, si el código es único."""
        if libro.codigo in self._libros:
            raise ValueError(f'Ya existe un libro con el código {libro.codigo}')
        self._libros[libro.codigo] = libro

    def prestar_libro(self, codigo: str) -> None:
        """Marca como prestado el libro indicado si está disponible."""
        libro = self._obtener_libro(codigo)
        libro.prestar()

    def devolver_libro(self, codigo: str) -> None:
        """Devuelve el libro prestado indicado."""
        libro = self._obtener_libro(codigo)
        libro.devolver()

    def buscar_libro(self, codigo: str) -> Optional[Libro]:
        """Devuelve el libro por su código, o None si no existe."""
        return self._libros.get(codigo)

    def _obtener_libro(self, codigo: str) -> Libro:
        """Recupera un libro, o lanza excepción si no existe."""
        libro = self.buscar_libro(codigo)
        if libro is None:
            raise LibroNoExisteException(f'No existe un libro con el código {codigo}')
        return libro

    def listar_libros(self):
        """Imprime todos los libros en la biblioteca, con su estado."""
        for libro in self._libros.values():
            print(libro)
