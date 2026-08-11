#VERSION5.5 + PROMPT BASICO
#PROBLEMA 1
texto = input("Introduce una cadena de texto: ")

texto_limpio = texto.lower().replace(" ", "")

if texto_limpio == texto_limpio[::-1]:
    print("La cadena es un palíndromo.")
else:
    print("La cadena no es un palíndromo.")

# PROBLEMA 2 
texto = input("Introduce una cadena de texto: ")

contador = {}

for caracter in texto:
    if caracter in contador:
        contador[caracter] += 1
    else:
        contador[caracter] = 1

for caracter, cantidad in contador.items():
    print(f"'{caracter}': {cantidad}")

#PROBLEMA 3
import string

frase = input("Introduce una frase: ")

# Eliminamos los signos de puntuación
frase_limpia = frase.translate(str.maketrans("", "", string.punctuation))

# Separamos la frase en palabras
palabras = frase_limpia.split()

if palabras:
    palabra_mas_larga = max(palabras, key=len)
    print("La palabra de mayor longitud es:", palabra_mas_larga)
    print("Tiene", len(palabra_mas_larga), "caracteres.")
else:
    print("No se encontraron palabras.")

#PROBLEMA 4
# Programa que determina si un número entero es primo o no

def es_primo(n):
    # Los números menores que 2 no son primos
    if n < 2:
        return False
    
    # 2 es el único número par primo
    if n == 2:
        return True
    
    # Si es par y distinto de 2, no es primo
    if n % 2 == 0:
        return False
    
    # Comprobamos divisores impares desde 3 hasta la raíz cuadrada de n
    limite = int(n ** 0.5) + 1
    for i in range(3, limite, 2):
        if n % i == 0:
            return False
    
    return True

## Programa principal
numero = int(input("Introduce un número entero: "))

if es_primo(numero):
    print(f"{numero} es un número primo.")
else:
    print(f"{numero} NO es un número primo.")

#PROBLEMA 5
def encontrar_max_min(numeros):
    # Comprobar si la colección es nula o está vacía
    if numeros is None or len(numeros) == 0:
        return None

    # Inicializamos ambos valores con el primer elemento
    maximo = numeros[0]
    minimo = numeros[0]

    # Recorremos manualmente los elementos restantes
    for numero in numeros[1:]:
        if numero > maximo:
            maximo = numero

        if numero < minimo:
            minimo = numero

    return maximo, minimo


# Ejemplo de uso
numeros = [8, -3, 12, 5, 0, 27, -10]

resultado = encontrar_max_min(numeros)

if resultado is None:
    print("La colección es nula o está vacía.")
else:
    maximo, minimo = resultado
    print("Valor máximo:", maximo)
    print("Valor mínimo:", minimo)

#PROBLEMA 6
class Factorial:
    # Método iterativo
    def iterativo(self, n):
        resultado = 1

        for i in range(1, n + 1):
            resultado *= i

        return resultado

    # Método recursivo
    def recursivo(self, n):
        # Caso base
        if n <= 1:
            return 1

        # Llamada recursiva
        return n * self.recursivo(n - 1)


# Programa principal
numero = int(input("Introduce un número entero positivo: "))

if numero < 0:
    print("Error: el número debe ser positivo o cero.")
else:
    factorial = Factorial()

    resultado_iterativo = factorial.iterativo(numero)
    resultado_recursivo = factorial.recursivo(numero)

    print(f"\nFactorial de {numero}:")
    print(f"Método iterativo: {resultado_iterativo}")
    print(f"Método recursivo: {resultado_recursivo}")

    if resultado_iterativo == resultado_recursivo:
        print("Ambos métodos producen el mismo resultado.")
    else:
        print("Los resultados son diferentes.")

# PROBLEMA 7
def calculadora():
    try:
        num1 = float(input("Introduce el primer número: "))
        operacion = input("Introduce la operación (+, -, *, /): ")
        num2 = float(input("Introduce el segundo número: "))

        if operacion == "+":
            resultado = num1 + num2
        elif operacion == "-":
            resultado = num1 - num2
        elif operacion == "*":
            resultado = num1 * num2
        elif operacion == "/":
            if num2 == 0:
                raise ZeroDivisionError("No se puede dividir entre cero.")
            resultado = num1 / num2
        else:
            raise ValueError("La operación introducida no es válida.")

        print("Resultado:", resultado)

    except ZeroDivisionError as error:
        print("Error:", error)

    except ValueError as error:
        print("Error: entrada no válida.", error)

    except Exception as error:
        print("Se produjo un error inesperado:", error)

    finally:
        print("Ejecución de la calculadora finalizada.")


calculadora()

#PROBLEMA 8
class LibroNoDisponibleError(Exception):
    """Excepción lanzada cuando se intenta prestar un libro ya prestado."""
    pass


class Libro:
    def __init__(self, titulo, autor):
        self.titulo = titulo
        self.autor = autor
        self.disponible = True

    def __str__(self):
        estado = "Disponible" if self.disponible else "Prestado"
        return f"{self.titulo} - {self.autor} ({estado})"


class Biblioteca:
    def __init__(self):
        self.libros = []

    def agregar_libro(self, libro):
        self.libros.append(libro)
        print(f'Libro "{libro.titulo}" añadido a la biblioteca.')

    def prestar_libro(self, libro):
        if not libro.disponible:
            raise LibroNoDisponibleError(
                f'El libro "{libro.titulo}" ya está prestado.'
            )

        libro.disponible = False
        print(f'Has tomado prestado "{libro.titulo}".')

    def devolver_libro(self, libro):
        libro.disponible = True
        print(f'El libro "{libro.titulo}" ha sido devuelto.')

    def mostrar_libros(self):
        for libro in self.libros:
            print(libro)


# Ejemplo de uso
biblioteca = Biblioteca()

libro1 = Libro("Don Quijote de la Mancha", "Miguel de Cervantes")
libro2 = Libro("Cien años de soledad", "Gabriel García Márquez")

biblioteca.agregar_libro(libro1)
biblioteca.agregar_libro(libro2)

try:
    biblioteca.prestar_libro(libro1)

    # Intentamos prestar el mismo libro otra vez
    biblioteca.prestar_libro(libro1)

except LibroNoDisponibleError as error:
    print(f"Error: {error}")

biblioteca.devolver_libro(libro1)

print("\nEstado de la biblioteca:")
biblioteca.mostrar_libros()
