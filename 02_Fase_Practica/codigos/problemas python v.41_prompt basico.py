#VERSION 4.4 PROMPT BASICO
#PROBLEMA 1
# Solicitar al usuario que introduzca una cadena
cadena = input("Introduce una cadena de texto: ")

# Limpiar la cadena: quitar espacios y convertir a minúsculas
cadena_limpia = ''.join(cadena.lower().split())

# Comprobar si la cadena es igual a su reverso
if cadena_limpia == cadena_limpia[::-1]:
    print("La cadena es un palíndromo.")
else:
    print("La cadena NO es un palíndromo.")

#PROBLEMA 2
# Solicitar al usuario una cadena de texto
cadena = input("Introduce una cadena de texto: ")

# Crear un diccionario para contar caracteres
contador = {}

# Recorrer cada carácter en la cadena
for caracter in cadena:
    if caracter in contador:
        contador[caracter] += 1
    else:
        contador[caracter] = 1

# Mostrar el resultado
for caracter, cantidad in contador.items():
    print(f"'{caracter}': {cantidad}")

#PROBLEMA 3
import string

def palabra_mas_larga(frase):
    # Elimina signos de puntuación
    frase_sin_puntuacion = frase.translate(str.maketrans("", "", string.punctuation))
    
    # Separa la frase en palabras
    palabras = frase_sin_puntuacion.split()
    
    # Busca la palabra más larga
    palabra_larga = max(palabras, key=len)
    
    return palabra_larga

# Ejemplo de uso
frase = input("Introduce una frase: ")
print("La palabra de mayor longitud es:", palabra_mas_larga(frase))

#PROBLEMA 4
# Programa para comprobar si un número es primo

# Solicita al usuario un número entero
num = int(input("Introduce un número entero: "))

# Sólo los números mayores que 1 pueden ser primos
if num <= 1:
    print(f"{num} no es un número primo.")
else:
    # Verificamos si tiene divisores distintos de 1 y de sí mismo
    es_primo = True
    for i in range(2, int(num ** 0.5) + 1):
        if num % i == 0:
            es_primo = False
            break

    if es_primo:
        print(f"{num} es un número primo.")
    else:
        print(f"{num} no es un número primo.")

#PROBLEMA 5
def encontrar_min_max(numeros):
    # Verificar si la lista es None o vacía
    if numeros is None or len(numeros) == 0:
        return "Error: La lista está vacía o es nula."
    
    # Inicializar min y max con el primer elemento
    minimo = numeros[0]
    maximo = numeros[0]
    
    # Recorrer manualmente la lista
    for num in numeros:
        if num < minimo:
            minimo = num
        if num > maximo:
            maximo = num
    
    return f"Valor mínimo: {minimo}, Valor máximo: {maximo}"

# Ejemplos de uso:
print(encontrar_min_max([4, 2, 9, -1, 7]))
print(encontrar_min_max([]))
print(encontrar_min_max(None))

#PROBLEMA 6
class Factorial:
    @staticmethod
    def iterativo(n):
        """Calcula el factorial usando un bucle iterativo."""
        resultado = 1
        for i in range(2, n + 1):
            resultado *= i
        return resultado

    @staticmethod
    def recursivo(n):
        """Calcula el factorial usando recursión."""
        if n <= 1:
            return 1
        else:
            return n * Factorial.recursivo(n-1)

if __name__ == "__main__":
    try:
        numero = int(input("Introduce un número entero positivo: "))
        if numero < 0:
            raise ValueError("Debe ser un número entero positivo.")

        fact_iter = Factorial.iterativo(numero)
        fact_rec = Factorial.recursivo(numero)

        print(f"Factorial de {numero} (iterativo): {fact_iter}")
        print(f"Factorial de {numero} (recursivo): {fact_rec}")

        if fact_iter == fact_rec:
            print("Ambos métodos producen el mismo resultado.")
        else:
            print("¡Los resultados difieren!")

    except ValueError as ve:
        print("Error:", ve)
#PROBLEMA 7
 def suma(a, b):
    return a + b

def resta(a, b):
    return a - b

def multiplicacion(a, b):
    return a * b

def division(a, b):
    try:
        return a / b
    except ZeroDivisionError:
        print("Error: No se puede dividir entre cero.")
        return None

def main():
    print("Calculadora básica")
    print("==================")

    while True:
        print("\nSeleccione una operación:")
        print("1. Suma")
        print("2. Resta")
        print("3. Multiplicación")
        print("4. División")
        print("5. Salir")

        opcion = input("Ingrese una opción (1/2/3/4/5): ")

        if opcion == '5':
            print("¡Hasta luego!")
            break

        try:
            num1 = float(input("Ingrese el primer número: "))
            num2 = float(input("Ingrese el segundo número: "))
        except ValueError:
            print("Error: Ingrese solo valores numéricos.")
            continue

        if opcion == '1':
            resultado = suma(num1, num2)
            print("Resultado:", resultado)
        elif opcion == '2':
            resultado = resta(num1, num2)
            print("Resultado:", resultado)
        elif opcion == '3':
            resultado = multiplicacion(num1, num2)
            print("Resultado:", resultado)
        elif opcion == '4':
            resultado = division(num1, num2)
            if resultado is not None:
                print("Resultado:", resultado)
        else:
            print("Opción no válida. Por favor, seleccione una opción correcta.")

if __name__ == "__main__":
    main()
#PROBLEMA 8
# Definir excepción personalizada
class LibroNoDisponibleException(Exception):
    pass

class Libro:
    def __init__(self, titulo, autor):
        self.titulo = titulo
        self.autor = autor
        self.disponible = True

    def prestar(self):
        if not self.disponible:
            raise LibroNoDisponibleException(f"El libro '{self.titulo}' no está disponible para préstamo.")
        self.disponible = False

    def devolver(self):
        self.disponible = True

    def __str__(self):
        estado = 'Disponible' if self.disponible else 'Prestado'
        return f"'{self.titulo}' de {self.autor} - {estado}"

class Biblioteca:
    def __init__(self):
        self.libros = []

    def añadir_libro(self, libro):
        self.libros.append(libro)

    def prestar_libro(self, titulo):
        for libro in self.libros:
            if libro.titulo == titulo:
                libro.prestar()
                return f"Libro '{titulo}' prestado correctamente."
        raise ValueError(f"El libro '{titulo}' no existe en la biblioteca.")

    def devolver_libro(self, titulo):
        for libro in self.libros:
            if libro.titulo == titulo:
                libro.devolver()
                return f"Libro '{titulo}' devuelto correctamente."
        raise ValueError(f"El libro '{titulo}' no existe en la biblioteca.")

    def listar_libros(self):
        for libro in self.libros:
            print(libro)

# Ejemplo de uso
if __name__ == "__main__":
    biblioteca = Biblioteca()
    libro1 = Libro("El Principito", "Antoine de Saint-Exupéry")
    libro2 = Libro("Cien años de soledad", "Gabriel García Márquez")
    
    biblioteca.añadir_libro(libro1)
    biblioteca.añadir_libro(libro2)

    biblioteca.listar_libros()
    print(biblioteca.prestar_libro("El Principito"))

    try:
        print(biblioteca.prestar_libro("El Principito"))  # Esto lanzará la excepción
    except LibroNoDisponibleException as e:
        print(e)

    biblioteca.listar_libros()
    print(biblioteca.devolver_libro("El Principito"))
    biblioteca.listar_libros()
