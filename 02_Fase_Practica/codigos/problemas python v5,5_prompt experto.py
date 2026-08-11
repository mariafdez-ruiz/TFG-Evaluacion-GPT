#VERSION 5.5 PROMPT EXPERTO
#PROBLEMA1 
import string


def limpiar_cadena(cadena: str) -> str:
    """
    Normaliza la cadena eliminando espacios, signos de puntuación
    y diferencias entre mayúsculas y minúsculas.

    Se conservan únicamente los caracteres alfanuméricos.
    """
    return "".join(
        caracter.casefold()
        for caracter in cadena
        if caracter.isalnum()
    )


def es_palindromo(cadena: str) -> bool:
    """
    Comprueba si una cadena es un palíndromo después de normalizarla.
    """
    cadena_limpia = limpiar_cadena(cadena)
    return cadena_limpia == cadena_limpia[::-1]


def main() -> None:
    """
    Punto de entrada principal del programa.
    """
    try:
        cadena = input("Introduce una cadena de texto: ")

        if not cadena.strip():
            raise ValueError("La cadena no puede estar vacía.")

        if es_palindromo(cadena):
            print("La cadena es un palíndromo.")
        else:
            print("La cadena no es un palíndromo.")

    except (EOFError, KeyboardInterrupt):
        print("\nEntrada cancelada por el usuario.")
    except ValueError as error:
        print(f"Error: {error}")


if __name__ == "__main__":
    main()

#PROBLEMA 2
from collections import Counter
from typing import Final


class CharacterFrequencyError(ValueError):
    """Excepción de dominio para entradas no válidas."""


class CharacterFrequencyAnalyzer:
    """Analiza la frecuencia de caracteres de una cadena."""

    _EMPTY_INPUT_MESSAGE: Final[str] = "La cadena no puede estar vacía."

    def __init__(self, text: str) -> None:
        self._text = self._validate(text)

    @staticmethod
    def _validate(text: str) -> str:
        """Valida la entrada antes de procesarla."""
        if not isinstance(text, str):
            raise TypeError("La entrada debe ser una cadena de texto.")

        if not text:
            raise CharacterFrequencyError(
                CharacterFrequencyAnalyzer._EMPTY_INPUT_MESSAGE
            )

        return text

    def frequencies(self) -> dict[str, int]:
        """
        Devuelve las frecuencias ordenadas por carácter.

        Counter realiza el conteo en O(n), mientras que la ordenación
        posterior tiene un coste O(k log k), siendo k los caracteres únicos.
        """
        counts = Counter(self._text)
        return dict(sorted(counts.items()))


def main() -> None:
    """Punto de entrada de la aplicación."""
    try:
        text = input("Introduce una cadena: ")
        analyzer = CharacterFrequencyAnalyzer(text)

        print("Frecuencia de caracteres:")
        for character, frequency in analyzer.frequencies().items():
            print(f"{character!r}: {frequency}")

    except (CharacterFrequencyError, TypeError) as exc:
        print(f"Error: {exc}")
    except (EOFError, KeyboardInterrupt):
        print("\nOperación cancelada.")


if __name__ == "__main__":
    main()

#PROBLEMA 3
import re
from typing import Final


class TextAnalyzerError(Exception):
    """Excepción base para errores relacionados con el análisis de texto."""


class InvalidTextError(TextAnalyzerError, ValueError):
    """Se produce cuando el texto recibido no cumple los requisitos."""


class TextAnalyzer:
    """Analiza texto mediante operaciones independientes y sin estado."""

    # Consideramos palabra una secuencia de caracteres Unicode alfanuméricos
    # que también puede contener apóstrofes o guiones internos.
    _WORD_PATTERN: Final[re.Pattern[str]] = re.compile(
        r"[^\W_]+(?:['’\-][^\W_]+)*",
        flags=re.UNICODE,
    )

    @classmethod
    def longest_word(cls, text: str) -> str:
        """
        Devuelve la palabra de mayor longitud ignorando la puntuación.

        En caso de empate devuelve la primera palabra encontrada.

        Raises:
            InvalidTextError: Si `text` no es una cadena o no contiene palabras.
        """
        if not isinstance(text, str):
            raise InvalidTextError("El texto debe ser una cadena de caracteres.")

        words = cls._WORD_PATTERN.findall(text)

        if not words:
            raise InvalidTextError("El texto debe contener al menos una palabra.")

        # max() conserva la primera aparición cuando varias palabras
        # producen el mismo valor para la clave.
        return max(words, key=len)


def main() -> None:
    """Punto de entrada de la aplicación."""
    try:
        phrase = input("Introduce una frase: ")
        print(TextAnalyzer.longest_word(phrase))
    except InvalidTextError as exc:
        print(f"Error de validación: {exc}")
    except (EOFError, KeyboardInterrupt):
        print("\nEntrada cancelada por el usuario.")


if __name__ == "__main__":
    main()

#PROBLEMA 4
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
from collections.abc import Iterable
from dataclasses import dataclass


@dataclass(frozen=True, slots=True)
class Extremos:
    """Representa los valores mínimo y máximo encontrados."""

    minimo: int
    maximo: int


def obtener_extremos(valores: Iterable[int] | None) -> Extremos:
    """
    Obtiene manualmente el mínimo y máximo de una colección de enteros.

    No utiliza min() ni max() y procesa el iterable en una única pasada.

    Raises:
        ValueError: Si la colección es None o está vacía.
        TypeError: Si algún elemento no es un entero válido.
    """
    if valores is None:
        raise ValueError("La colección no puede ser nula.")

    try:
        iterador = iter(valores)
    except TypeError as exc:
        raise TypeError("Se esperaba una colección iterable de enteros.") from exc

    try:
        primero = next(iterador)
    except StopIteration as exc:
        raise ValueError("La colección no puede estar vacía.") from exc

    _validar_entero(primero)
    minimo = maximo = primero

    # Una única pasada: O(n) en tiempo y O(1) en memoria adicional.
    for valor in iterador:
        _validar_entero(valor)

        if valor < minimo:
            minimo = valor
        elif valor > maximo:
            maximo = valor

    return Extremos(minimo=minimo, maximo=maximo)


def _validar_entero(valor: object) -> None:
    """Valida estrictamente que el elemento sea un entero (excluye bool)."""
    if type(valor) is not int:
        raise TypeError(
            f"Todos los elementos deben ser enteros; "
            f"se recibió {type(valor).__name__}."
        )

#PROBLEMA 6
"""Cálculo de factorial mediante implementaciones iterativa y recursiva.

El módulo valida explícitamente los datos de entrada y mantiene separadas
las responsabilidades de validación, cálculo y demostración.
"""

from typing import Final


# Protege la implementación recursiva frente al límite de recursión de Python.
# Se utiliza un margen prudente respecto al límite habitual (~1000).
MAX_RECURSIVE_N: Final[int] = 900


def _validate_positive_integer(n: int) -> None:
    """Valida que `n` sea un entero positivo.

    Raises:
        TypeError: Si `n` no es un entero válido.
        ValueError: Si `n` no es positivo.
    """
    # bool hereda de int en Python, pero no representa una entrada válida aquí.
    if isinstance(n, bool) or not isinstance(n, int):
        raise TypeError("n debe ser un entero.")

    if n <= 0:
        raise ValueError("n debe ser un entero positivo (> 0).")


def factorial_iterative(n: int) -> int:
    """Calcula n! utilizando una implementación iterativa.

    Complejidad:
        Tiempo: O(n)
        Espacio auxiliar: O(1)
    """
    _validate_positive_integer(n)

    result = 1
    for factor in range(2, n + 1):
        result *= factor

    return result


def factorial_recursive(n: int) -> int:
    """Calcula n! mediante recursión.

    Complejidad:
        Tiempo: O(n)
        Espacio auxiliar: O(n), debido a la pila de llamadas.

    Raises:
        ValueError: Si `n` excede el límite seguro definido para esta versión.
    """
    _validate_positive_integer(n)

    if n > MAX_RECURSIVE_N:
        raise ValueError(
            f"n es demasiado grande para la versión recursiva "
            f"(máximo permitido: {MAX_RECURSIVE_N})."
        )

    return _factorial_recursive(n)


def _factorial_recursive(n: int) -> int:
    """Implementación recursiva interna sobre una entrada ya validada."""
    if n == 1:
        return 1

    return n * _factorial_recursive(n - 1)


def main() -> None:
    """Demuestra y compara ambas implementaciones."""
    number = 5

    try:
        iterative_result = factorial_iterative(number)
        recursive_result = factorial_recursive(number)

        # Verificación defensiva: ambas estrategias deben producir lo mismo.
        if iterative_result != recursive_result:
            raise RuntimeError(
                "Las implementaciones produjeron resultados inconsistentes."
            )

        print(f"{number}! (iterativo) = {iterative_result}")
        print(f"{number}! (recursivo) = {recursive_result}")

    except (TypeError, ValueError, RuntimeError) as error:
        print(f"Error: {error}")


if __name__ == "__main__":
    main()

#PROBLEMA 7
from __future__ import annotations

from enum import Enum
from numbers import Real


class CalculatorError(Exception):
    """Excepción base para errores propios de la calculadora."""


class InvalidOperandError(CalculatorError):
    """Se produce cuando un operando no es un número válido."""


class DivisionByZeroError(CalculatorError):
    """Se produce al intentar realizar una división por cero."""


class UnsupportedOperationError(CalculatorError):
    """Se produce cuando se solicita una operación no soportada."""


class Operation(Enum):
    """Operaciones aritméticas soportadas."""

    ADD = "+"
    SUBTRACT = "-"
    MULTIPLY = "*"
    DIVIDE = "/"


class Calculator:
    """Calculadora básica sin estado para operaciones aritméticas."""

    @staticmethod
    def calculate(
        left: Real,
        right: Real,
        operation: Operation | str,
    ) -> Real:
        """Ejecuta una operación aritmética entre dos operandos."""

        Calculator._validate_operand(left)
        Calculator._validate_operand(right)

        try:
            selected_operation = (
                operation
                if isinstance(operation, Operation)
                else Operation(operation)
            )
        except (ValueError, TypeError) as exc:
            raise UnsupportedOperationError(
                f"Operación no soportada: {operation!r}"
            ) from exc

        operations = {
            Operation.ADD: Calculator._add,
            Operation.SUBTRACT: Calculator._subtract,
            Operation.MULTIPLY: Calculator._multiply,
            Operation.DIVIDE: Calculator._divide,
        }

        return operations[selected_operation](left, right)

    @staticmethod
    def _validate_operand(value: Real) -> None:
        """Garantiza que el operando sea un número real y no un booleano."""
        if isinstance(value, bool) or not isinstance(value, Real):
            raise InvalidOperandError(
                f"El operando {value!r} debe ser un número real."
            )

    @staticmethod
    def _add(left: Real, right: Real) -> Real:
        """Suma dos números."""
        return left + right

    @staticmethod
    def _subtract(left: Real, right: Real) -> Real:
        """Resta dos números."""
        return left - right

    @staticmethod
    def _multiply(left: Real, right: Real) -> Real:
        """Multiplica dos números."""
        return left * right

    @staticmethod
    def _divide(left: Real, right: Real) -> Real:
        """Divide dos números controlando explícitamente la división por cero."""
        if right == 0:
            raise DivisionByZeroError("No es posible dividir por cero.")

        return left / right


def main() -> None:
    """Punto de entrada y única capa responsable de la interacción con el usuario."""
    try:
        left = float(input("Primer número: "))
        operation = input("Operación (+, -, *, /): ").strip()
        right = float(input("Segundo número: "))

        result = Calculator.calculate(left, right, operation)
        print(f"Resultado: {result}")

    except ValueError:
        # La conversión de la entrada del usuario puede fallar independientemente
        # de las reglas de negocio de Calculator.
        print("Error: los operandos deben ser números válidos.")

    except CalculatorError as exc:
        # Las excepciones del dominio se capturan de forma explícita sin ocultar
        # errores inesperados de programación.
        print(f"Error: {exc}")


if __name__ == "__main__":
    main()

#PROBLEMA 8
from __future__ import annotations


class ErrorBiblioteca(Exception):
    """Excepción base para los errores de dominio de la biblioteca."""


class LibroYaPrestadoError(ErrorBiblioteca):
    """Se lanza cuando se intenta prestar un libro que ya está prestado."""


class LibroNoEncontradoError(ErrorBiblioteca):
    """Se lanza cuando el libro solicitado no pertenece a la biblioteca."""


class LibroNoPrestadoError(ErrorBiblioteca):
    """Se lanza cuando se intenta devolver un libro que no está prestado."""


class LibroDuplicadoError(ErrorBiblioteca):
    """Se lanza cuando se intenta añadir dos veces el mismo ISBN."""


class Libro:
    """Representa un libro y encapsula su estado de préstamo."""

    def __init__(self, isbn: str, titulo: str, autor: str) -> None:
        if not isbn.strip():
            raise ValueError("El ISBN no puede estar vacío.")
        if not titulo.strip():
            raise ValueError("El título no puede estar vacío.")
        if not autor.strip():
            raise ValueError("El autor no puede estar vacío.")

        self._isbn = isbn.strip()
        self._titulo = titulo.strip()
        self._autor = autor.strip()
        self._prestado = False

    @property
    def isbn(self) -> str:
        return self._isbn

    @property
    def titulo(self) -> str:
        return self._titulo

    @property
    def autor(self) -> str:
        return self._autor

    @property
    def esta_prestado(self) -> bool:
        """Expone el estado sin permitir modificarlo directamente."""
        return self._prestado

    def prestar(self) -> None:
        """Presta el libro garantizando la consistencia de su estado."""
        if self._prestado:
            raise LibroYaPrestadoError(
                f"El libro '{self._titulo}' ya está prestado."
            )

        self._prestado = True

    def devolver(self) -> None:
        """Devuelve el libro si actualmente se encuentra prestado."""
        if not self._prestado:
            raise LibroNoPrestadoError(
                f"El libro '{self._titulo}' no está prestado."
            )

        self._prestado = False

    def __repr__(self) -> str:
        estado = "prestado" if self._prestado else "disponible"
        return (
            f"Libro(isbn={self._isbn!r}, titulo={self._titulo!r}, "
            f"autor={self._autor!r}, estado={estado!r})"
        )


class Biblioteca:
    """Gestiona una colección de libros identificados de forma única por ISBN."""

    def __init__(self) -> None:
        # Un diccionario proporciona búsquedas por ISBN en O(1) promedio.
        self._libros: dict[str, Libro] = {}

    def agregar_libro(self, libro: Libro) -> None:
        if not isinstance(libro, Libro):
            raise TypeError("Solo se pueden agregar instancias de Libro.")

        if libro.isbn in self._libros:
            raise LibroDuplicadoError(
                f"Ya existe un libro con ISBN '{libro.isbn}'."
            )

        self._libros[libro.isbn] = libro

    def obtener_libro(self, isbn: str) -> Libro:
        """Obtiene un libro o genera un error de dominio explícito."""
        try:
            return self._libros[isbn]
        except KeyError:
            # Evita filtrar detalles internos de la implementación.
            raise LibroNoEncontradoError(
                f"No existe ningún libro con ISBN '{isbn}'."
            ) from None

    def prestar_libro(self, isbn: str) -> None:
        # La regla "ya prestado" pertenece a Libro, no a Biblioteca.
        self.obtener_libro(isbn).prestar()

    def devolver_libro(self, isbn: str) -> None:
        self.obtener_libro(isbn).devolver()

    def listar_libros(self) -> tuple[Libro, ...]:
        """Devuelve una vista inmutable de la colección."""
        return tuple(self._libros.values())
