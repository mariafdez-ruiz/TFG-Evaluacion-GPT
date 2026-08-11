# versión 5.5 - prompt medio
#PROBLEMA 1
import string


def es_palindromo(cadena):
    """
    Comprueba si una cadena es un palíndromo ignorando:
    - Mayúsculas y minúsculas.
    - Espacios en blanco.
    - Signos de puntuación.

    Devuelve True si es un palíndromo y False en caso contrario.
    """
    if not isinstance(cadena, str):
        raise TypeError("La entrada debe ser una cadena de texto.")

    # Convertimos a minúsculas y eliminamos espacios y signos de puntuación.
    cadena_limpia = cadena.lower()

    cadena_limpia = "".join(
        caracter
        for caracter in cadena_limpia
        if not caracter.isspace() and caracter not in string.punctuation
    )

    # Una cadena es un palíndromo si es igual a su versión invertida.
    return cadena_limpia == cadena_limpia[::-1]


def main():
    """Función principal del programa."""
    try:
        texto = input("Introduce una cadena de texto: ")

        resultado = es_palindromo(texto)

        if resultado:
            print("La cadena es un palíndromo.")
        else:
            print("La cadena no es un palíndromo.")

    except TypeError as error:
        print(f"Error: {error}")
    except (EOFError, KeyboardInterrupt):
        print("\nEntrada cancelada por el usuario.")
    except Exception as error:
        print(f"Se ha producido un error inesperado: {error}")


if __name__ == "__main__":
    main()

#PROBLEMA 2
def contar_caracteres(texto):
    """
    Cuenta la frecuencia de cada carácter de una cadena.

    Args:
        texto (str): Cadena de texto que se desea analizar.

    Returns:
        dict: Diccionario ordenado por carácter con sus frecuencias.

    Raises:
        TypeError: Si el argumento recibido no es una cadena.
    """

    # Validamos que la entrada sea una cadena de texto.
    if not isinstance(texto, str):
        raise TypeError("La entrada debe ser una cadena de texto.")

    # Diccionario donde cada carácter será una clave.
    frecuencias = {}

    # Recorremos la cadena y actualizamos la frecuencia de cada carácter.
    for caracter in texto:
        frecuencias[caracter] = frecuencias.get(caracter, 0) + 1

    # Devolvemos un diccionario ordenado alfabéticamente por sus claves.
    return dict(sorted(frecuencias.items()))


def main():
    """Caso de prueba y gestión de posibles errores."""
    try:
        texto = "hola mundo"

        resultado = contar_caracteres(texto)

        print(f"Texto analizado: {texto!r}")
        print("Frecuencia de caracteres:")

        # Mostramos cada clave y su valor de forma clara.
        for caracter, frecuencia in resultado.items():
            # repr() permite visualizar claramente caracteres como espacios.
            print(f"{caracter!r}: {frecuencia}")

    except (TypeError, ValueError) as error:
        print(f"Error: {error}")


# Ejecutamos el caso de prueba solo si el archivo se ejecuta directamente.
if __name__ == "__main__":
    main()

#PROBLEMA 3 
import string


def palabra_mas_larga(frase):
    """
    Devuelve la palabra de mayor longitud de una frase,
    ignorando los signos de puntuación.

    Si hay varias palabras con la misma longitud máxima,
    devuelve la primera encontrada.
    """
    # Comprobamos que la entrada sea una cadena.
    if not isinstance(frase, str):
        raise TypeError("La frase debe ser una cadena de texto.")

    # Eliminamos los signos de puntuación sustituyéndolos por espacios.
    # Usar espacios evita unir palabras separadas por puntuación.
    traductor = str.maketrans(string.punctuation, " " * len(string.punctuation))
    frase_limpia = frase.translate(traductor)

    # Separamos la frase en palabras.
    palabras = frase_limpia.split()

    # Comprobamos que exista al menos una palabra.
    if not palabras:
        raise ValueError("La frase no contiene palabras.")

    # max() permite encontrar la palabra de mayor longitud.
    # En caso de empate, devuelve la primera encontrada.
    return max(palabras, key=len)


def main():
    """Ejecuta un caso de prueba del programa."""
    frase = "Python es un lenguaje extraordinario, ¡fácil y potente!"

    try:
        resultado = palabra_mas_larga(frase)

        print(f"Frase: {frase}")
        print(f"Palabra más larga: {resultado}")
        print(f"Longitud: {len(resultado)}")

    except (TypeError, ValueError) as error:
        # Gestionamos errores relacionados con una entrada no válida.
        print(f"Error: {error}")
    except Exception as error:
        # Captura de seguridad para errores inesperados.
        print(f"Se produjo un error inesperado: {error}")


if __name__ == "__main__":
    main()

#PROBLEMA 4
import math


def es_primo(numero):
    """
    Determina si un número entero es primo.

    Un número primo es un entero mayor que 1 que solo es divisible
    entre 1 y sí mismo.
    """
    if not isinstance(numero, int):
        raise TypeError("El valor debe ser un número entero.")

    # Los números menores o iguales que 1 no son primos.
    if numero <= 1:
        return False

    # 2 es el único número primo par.
    if numero == 2:
        return True

    # Cualquier otro número par no puede ser primo.
    if numero % 2 == 0:
        return False

    # Solo es necesario comprobar divisores hasta la raíz cuadrada.
    limite = math.isqrt(numero)

    # Como ya descartamos los pares, comprobamos únicamente impares.
    for divisor in range(3, limite + 1, 2):
        if numero % divisor == 0:
            return False

    return True


def main():
    """Solicita un entero al usuario e indica si es primo."""
    try:
        entrada = input("Introduce un número entero: ").strip()
        numero = int(entrada)

        if es_primo(numero):
            print(f"{numero} es un número primo.")
        else:
            print(f"{numero} no es un número primo.")

    except ValueError:
        # Ocurre cuando la entrada no puede convertirse a entero.
        print("Error: debes introducir un número entero válido.")
    except (EOFError, KeyboardInterrupt):
        # Gestiona la ausencia de entrada o la cancelación del usuario.
        print("\nOperación cancelada.")


if __name__ == "__main__":
    main()

#PROBLEMA 5
def encontrar_maximo_minimo(coleccion):
    """
    Encuentra manualmente el valor máximo y mínimo de una colección
    de enteros.

    :param coleccion: Colección de números enteros.
    :return: Tupla (maximo, minimo).
    :raises ValueError: Si la colección es None o está vacía.
    :raises TypeError: Si algún elemento no es un entero.
    """

    # Comprobamos explícitamente que la colección no sea nula.
    if coleccion is None:
        raise ValueError("La colección no puede ser nula (None).")

    try:
        # Convertimos a iterador para admitir distintas colecciones iterables.
        iterador = iter(coleccion)
    except TypeError:
        raise TypeError("El valor proporcionado debe ser una colección iterable.")

    try:
        # El primer elemento sirve para inicializar máximo y mínimo.
        primero = next(iterador)
    except StopIteration:
        raise ValueError("La colección no puede estar vacía.")

    # Verificamos que el primer elemento sea entero.
    if not isinstance(primero, int):
        raise TypeError(
            f"Todos los elementos deben ser enteros. Valor inválido: {primero!r}"
        )

    maximo = primero
    minimo = primero

    # Recorremos manualmente el resto de los elementos.
    for numero in iterador:
        if not isinstance(numero, int):
            raise TypeError(
                f"Todos los elementos deben ser enteros. Valor inválido: {numero!r}"
            )

        # Actualizamos el máximo cuando encontramos un valor mayor.
        if numero > maximo:
            maximo = numero

        # Actualizamos el mínimo cuando encontramos un valor menor.
        if numero < minimo:
            minimo = numero

    return maximo, minimo


# Caso de prueba funcional.
if __name__ == "__main__":
    numeros = [12, -4, 7, 35, 0, 18, -10, 6]

    try:
        maximo, minimo = encontrar_maximo_minimo(numeros)
        print(f"Colección: {numeros}")
        print(f"Valor máximo: {maximo}")
        print(f"Valor mínimo: {minimo}")

    except (ValueError, TypeError) as error:
        # Gestión controlada de los errores esperados.
        print(f"Error: {error}")
# PROBLEMA 6
class Factorial:
    """Clase para calcular el factorial mediante métodos iterativo y recursivo."""

    @staticmethod
    def validar_numero(n):
        """Comprueba que n sea un entero no negativo."""
        # bool es una subclase de int en Python, por lo que se excluye explícitamente.
        if isinstance(n, bool) or not isinstance(n, int):
            raise TypeError("El valor debe ser un número entero.")

        if n < 0:
            raise ValueError("El factorial no está definido para números negativos.")

    @staticmethod
    def iterativo(n):
        """Calcula n! utilizando un algoritmo iterativo."""
        Factorial.validar_numero(n)

        resultado = 1

        # Multiplicamos todos los enteros desde 2 hasta n.
        for i in range(2, n + 1):
            resultado *= i

        return resultado

    @staticmethod
    def recursivo(n):
        """Calcula n! utilizando un algoritmo recursivo."""
        Factorial.validar_numero(n)

        # Caso base: 0! = 1 y 1! = 1.
        if n <= 1:
            return 1

        # Caso recursivo: n! = n * (n - 1)!
        return n * Factorial.recursivo(n - 1)


def main():
    """Demuestra el funcionamiento de ambos métodos."""
    numero = 5

    try:
        resultado_iterativo = Factorial.iterativo(numero)
        resultado_recursivo = Factorial.recursivo(numero)

        print(f"Factorial de {numero} mediante método iterativo: {resultado_iterativo}")
        print(f"Factorial de {numero} mediante método recursivo: {resultado_recursivo}")

        # Caso de prueba: 5! debe ser 120 con ambos métodos.
        assert resultado_iterativo == 120
        assert resultado_recursivo == 120
        assert resultado_iterativo == resultado_recursivo

        print("Caso de prueba superado correctamente.")

    except (TypeError, ValueError) as error:
        print(f"Error: {error}")
    except RecursionError:
        # Python limita la profundidad máxima de las llamadas recursivas.
        print("Error: el número es demasiado grande para el método recursivo.")


# Ejecuta la demostración solo cuando el archivo se ejecuta directamente.
if __name__ == "__main__":
    main()

#PROBLEMA 7

# Calculadora básica con control de excepciones

def calculadora(num1, num2, operacion):
    """
    Realiza una operación matemática básica entre dos números.

    Parámetros:
        num1: Primer número.
        num2: Segundo número.
        operacion: Operación a realizar (+, -, *, /).

    Retorna:
        El resultado de la operación.

    Lanza:
        ValueError: Si la operación introducida no es válida.
        ZeroDivisionError: Si se intenta dividir entre cero.
        TypeError: Si los operandos no son números.
    """

    # Comprobamos que los operandos sean numéricos.
    if not isinstance(num1, (int, float)) or not isinstance(num2, (int, float)):
        raise TypeError("Los operandos deben ser números.")

    # Seleccionamos la operación solicitada.
    if operacion == "+":
        return num1 + num2

    elif operacion == "-":
        return num1 - num2

    elif operacion == "*":
        return num1 * num2

    elif operacion == "/":
        # Control explícito de la división por cero.
        if num2 == 0:
            raise ZeroDivisionError("No se puede dividir entre cero.")
        return num1 / num2

    else:
        # La operación introducida no está soportada.
        raise ValueError(
            "Operación no válida. Utilice +, -, * o /."
        )


def main():
    """Función principal de la calculadora."""

    try:
        # Solicitamos los datos al usuario.
        num1 = float(input("Introduce el primer número: "))
        operacion = input("Introduce la operación (+, -, *, /): ").strip()
        num2 = float(input("Introduce el segundo número: "))

        # Ejecutamos la operación.
        resultado = calculadora(num1, num2, operacion)

        print(f"Resultado: {num1} {operacion} {num2} = {resultado}")

    except ValueError as error:
        # Puede producirse por una entrada no numérica
        # o por una operación desconocida.
        print(f"Error de valor: {error}")

    except ZeroDivisionError as error:
        # Tratamiento específico y obligatorio de la división por cero.
        print(f"Error matemático: {error}")

    except TypeError as error:
        # Control de tipos incorrectos.
        print(f"Error de tipo: {error}")

    except Exception as error:
        # Captura cualquier otro error inesperado.
        print(f"Se produjo un error inesperado: {error}")


def caso_de_prueba():
    """Ejecuta un caso de prueba funcional."""
    try:
        # Caso de prueba: 10 dividido entre 2 debe producir 5.
        resultado = calculadora(10, 2, "/")

        assert resultado == 5.0
        print("Prueba superada: 10 / 2 = 5.0")

    except (ValueError, ZeroDivisionError, TypeError) as error:
        print(f"La prueba produjo un error: {error}")

    except AssertionError:
        print("Prueba fallida: el resultado obtenido no es el esperado.")


# Este bloque solo se ejecuta cuando el archivo se inicia directamente.
if __name__ == "__main__":
    # Ejecutamos primero el caso de prueba.
    caso_de_prueba()

    print("\n--- Calculadora básica ---")

    # Iniciamos la calculadora interactiva.
    main()

#PROBLEMA 8
class LibroYaPrestadoError(Exception):
    """Excepción lanzada cuando se intenta prestar un libro ya prestado."""
    pass


class LibroNoPrestadoError(Exception):
    """Excepción lanzada cuando se intenta devolver un libro que no está prestado."""
    pass


class Libro:
    """Representa un libro de la biblioteca."""

    def __init__(self, titulo, autor):
        if not titulo or not isinstance(titulo, str):
            raise ValueError("El título debe ser una cadena no vacía.")
        if not autor or not isinstance(autor, str):
            raise ValueError("El autor debe ser una cadena no vacía.")

        self.titulo = titulo
        self.autor = autor
        self.prestado = False

    def prestar(self):
        """Marca el libro como prestado."""
        if self.prestado:
            raise LibroYaPrestadoError(
                f'El libro "{self.titulo}" ya está prestado.'
            )

        self.prestado = True

    def devolver(self):
        """Marca el libro como disponible."""
        if not self.prestado:
            raise LibroNoPrestadoError(
                f'El libro "{self.titulo}" no está prestado.'
            )

        self.prestado = False

    def __str__(self):
        """Devuelve una representación legible del libro."""
        estado = "Prestado" if self.prestado else "Disponible"
        return f"{self.titulo} - {self.autor} ({estado})"


class Biblioteca:
    """Gestiona una colección de libros."""

    def __init__(self):
        # Se utiliza un diccionario para localizar rápidamente los libros.
        self.libros = {}

    def agregar_libro(self, libro):
        """Añade un libro a la biblioteca."""
        if not isinstance(libro, Libro):
            raise TypeError("Solo se pueden agregar objetos de tipo Libro.")

        if libro.titulo in self.libros:
            raise ValueError(
                f'Ya existe un libro con el título "{libro.titulo}".'
            )

        self.libros[libro.titulo] = libro

    def buscar_libro(self, titulo):
        """Busca y devuelve un libro por su título."""
        if titulo not in self.libros:
            raise KeyError(
                f'El libro "{titulo}" no existe en la biblioteca.'
            )

        return self.libros[titulo]

    def prestar_libro(self, titulo):
        """Presta el libro indicado."""
        libro = self.buscar_libro(titulo)
        libro.prestar()

    def devolver_libro(self, titulo):
        """Devuelve el libro indicado."""
        libro = self.buscar_libro(titulo)
        libro.devolver()

    def mostrar_libros(self):
        """Muestra todos los libros y su estado."""
        for libro in self.libros.values():
            print(libro)


# ---------------------------------------------------------
# Caso de prueba funcional
# ---------------------------------------------------------
if __name__ == "__main__":
    biblioteca = Biblioteca()

    try:
        # Crear y añadir libros.
        biblioteca.agregar_libro(
            Libro("Don Quijote de la Mancha", "Miguel de Cervantes")
        )
        biblioteca.agregar_libro(
            Libro("Cien años de soledad", "Gabriel García Márquez")
        )

        print("Estado inicial:")
        biblioteca.mostrar_libros()

        # Préstamo correcto.
        print("\nPrestando Don Quijote...")
        biblioteca.prestar_libro("Don Quijote de la Mancha")

        # Se intenta prestar otra vez el mismo libro.
        # Esto debe lanzar LibroYaPrestadoError.
        print("Intentando prestarlo nuevamente...")
        biblioteca.prestar_libro("Don Quijote de la Mancha")

    except LibroYaPrestadoError as error:
        print(f"Error de préstamo: {error}")
    except (ValueError, TypeError, KeyError) as error:
        print(f"Error: {error}")

    # La devolución se prueba independientemente para que el error anterior
    # no impida continuar con el caso de prueba.
    try:
        print("\nDevolviendo Don Quijote...")
        biblioteca.devolver_libro("Don Quijote de la Mancha")
    except (LibroNoPrestadoError, KeyError) as error:
        print(f"Error de devolución: {error}")

    print("\nEstado final:")
    biblioteca.mostrar_libros()

