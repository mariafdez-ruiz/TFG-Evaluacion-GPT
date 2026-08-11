// JAVA V-4.1 PROMPT MEDIO
//PROBLEMA 1
public class Palindromo {

    /**
     * Comprueba si una cadena es un palíndromo ignorando mayúsculas,
     * minúsculas, espacios y signos de puntuación.
     *
     * @param texto Cadena de texto a comprobar
     * @return true si es palíndromo, false en caso contrario
     * @throws IllegalArgumentException si el texto es null
     */
    public static boolean esPalindromo(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("La cadena de texto no puede ser null");
        }

        // Eliminar todo lo que no sea letra o dígito (espacios y signos de puntuación incluidos)
        String limpiado = texto.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int izquierda = 0;
        int derecha = limpiado.length() - 1;

        // Comparar los caracteres desde ambos extremos hacia el centro
        while (izquierda < derecha) {
            if (limpiado.charAt(izquierda) != limpiado.charAt(derecha)) {
                return false;
            }
            izquierda++;
            derecha--;
        }
        return true;
    }

    public static void main(String[] args) {
        // Caso de prueba funcional
        String prueba = "Anita, lava la tina.";

        try {
            boolean resultado = esPalindromo(prueba);
            System.out.println("¿La cadena es palíndromo? " + resultado);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Se puede probar con otros casos, por ejemplo:
        // String prueba2 = "Hola mundo";
        // System.out.println(esPalindromo(prueba2));
    }
}

//PROBLEMA 2
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

public class FrecuenciaCaracteres {
    /**
     * Cuenta la frecuencia de cada carácter en la cadena de entrada.
     * @param texto La cadena de texto a analizar.
     * @return Un mapa ordenado con la frecuencia de los caracteres.
     */
    public static Map<Character, Integer> contarFrecuenciaCaracteres(String texto) {
        // Usamos TreeMap para que la salida esté ordenada por clave (carácter)
        Map<Character, Integer> frecuencia = new TreeMap<>();
        
        if (texto == null) {
            throw new IllegalArgumentException("La cadena de texto no puede ser null.");
        }

        for (char c : texto.toCharArray()) {
            frecuencia.put(c, frecuencia.getOrDefault(c, 0) + 1);
        }
        return frecuencia;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String texto;

        try {
            // Caso de prueba funcional con texto predefinido
            System.out.println("----- Caso de prueba predefinido -----");
            texto = "Hola mundo!";
            System.out.println("Cadena de prueba: " + texto);
            Map<Character, Integer> resultado = contarFrecuenciaCaracteres(texto);

            // Mostrar resultado de forma clara
            for (Map.Entry<Character, Integer> entry : resultado.entrySet()) {
                System.out.println("'" + entry.getKey() + "' : " + entry.getValue());
            }

            // Adicional: permitir entrada del usuario
            System.out.println("\n----- Ingresa una cadena de texto -----");
            texto = scanner.nextLine();
            Map<Character, Integer> resultadoUsuario = contarFrecuenciaCaracteres(texto);
            for (Map.Entry<Character, Integer> entry : resultadoUsuario.entrySet()) {
                System.out.println("'" + entry.getKey() + "' : " + entry.getValue());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}


//PROBLEMA 3
import java.util.Scanner;

public class PalabraMayorLongitud {

    /**
     * Método que analiza una frase, ignora signos de puntuación
     * y retorna la palabra de mayor longitud.
     *
     * @param frase La frase a analizar.
     * @return La palabra de mayor longitud o una cadena vacía si no hay palabras.
     */
    public static String palabraMasLarga(String frase) {
        if (frase == null) {
            throw new IllegalArgumentException("La frase no puede ser nula");
        }
        
        // Eliminar posibles signos de puntuación y mantener letras y números
        String fraseLimpia = frase.replaceAll("[^a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ ]", " ");
        // Separar en palabras
        String[] palabras = fraseLimpia.split("\\s+");
        
        String palabraMax = "";
        for (String palabra : palabras) {
            if (palabra.length() > palabraMax.length()) {
                palabraMax = palabra;
            }
        }
        return palabraMax;
    }

    public static void main(String[] args) {
        // Caso de prueba
        String frasePrueba = "¡Hola, mundo! Este es un ejemplo práctico: ¿cuál será la palabra más larga?";

        try {
            String resultado = palabraMasLarga(frasePrueba);
            if (!resultado.isEmpty()) {
                System.out.println("La palabra de mayor longitud es: \"" + resultado + "\"");
            } else {
                System.out.println("No se encontraron palabras en la frase.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error inesperado.");
            e.printStackTrace();
        }
    }
}



//PROBLEMA 4
import java.util.Scanner;

public class NumeroPrimo {
    
    // Función para verificar si un número es primo
    public static boolean esPrimo(int numero) {
        // Los números menores o iguales a 1 no son primos
        if (numero <= 1) {
            return false;
        }
        // Solo se verifica hasta la raíz cuadrada del número para optimizar
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                // Si es divisible por algún número, no es primo
                return false;
            }
        }
        // Si no tuvo ningún divisor, es primo
        return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Caso de prueba funcional: Puedes descomentar estas líneas para probar sin ingresar manualmente
        // int num = 7;
        // System.out.println("¿Es " + num + " primo? " + (esPrimo(num) ? "Sí" : "No"));
        
        System.out.print("Introduce un número entero para comprobar si es primo: ");
        // Gestión de errores con try-catch
        try {
            int num = scanner.nextInt();
            if (esPrimo(num)) {
                System.out.println("El número " + num + " es primo.");
            } else {
                System.out.println("El número " + num + " no es primo.");
            }
        } catch (Exception e) {
            System.out.println("Entrada no válida. Por favor, introduce un número entero.");
        } finally {
            scanner.close();
        }
    }
}


//PROBLEMA 5
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MinMaxFinder {

    /**
     * Clase auxiliar para contener ambos extremos.
     */
    public static class MinMaxResult {
        public final int min;
        public final int max;

        public MinMaxResult(int min, int max) {
            this.min = min;
            this.max = max;
        }
        
        @Override
        public String toString() {
            return "Mínimo: " + min + ", Máximo: " + max;
        }
    }

    /**
     * Encuentra el valor mínimo y máximo en una colección de enteros.
     *
     * @param numbers Colección de enteros (puede ser null o vacía)
     * @return Objeto MinMaxResult con los valores min y max encontrados
     * @throws IllegalArgumentException si la colección es nula o vacía
     */
    public static MinMaxResult findMinAndMax(Collection<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("La colección no debe ser nula.");
        }
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("La colección no debe estar vacía.");
        }

        // Se utilizan iteradores para recorrer manualmente la colección
        int min, max;
        var iterator = numbers.iterator();

        // Inicializa min y max con el primer elemento
        int first = iterator.next();
        min = max = first;

        // Recorre el resto de la colección
        while (iterator.hasNext()) {
            int current = iterator.next();
            if (current < min) {
                min = current;
            }
            if (current > max) {
                max = current;
            }
        }

        return new MinMaxResult(min, max);
    }

    public static void main(String[] args) {
        // Caso de prueba funcional
        Collection<Integer> numeros = Arrays.asList(5, 2, 9, 1, 7, 3);

        try {
            MinMaxResult resultado = findMinAndMax(numeros);
            System.out.println("Colección de entrada: " + numeros);
            System.out.println("Resultado: " + resultado);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Casos extra de prueba:
        // 1. Lista vacía
        try {
            findMinAndMax(Collections.emptyList());
        } catch (IllegalArgumentException e) {
            System.err.println("Prueba vacía: " + e.getMessage());
        }

        // 2. Lista nula
        try {
            findMinAndMax(null);
        } catch (IllegalArgumentException e) {
            System.err.println("Prueba nula: " + e.getMessage());
        }
    }
}



//PROBLEMA 6
import java.util.Scanner;

public class FactorialCalculator {
    /**
     * Calcula el factorial de un número de manera iterativa.
     * @param n Número entero positivo.
     * @return Factorial de n.
     * @throws IllegalArgumentException Si el número es negativo.
     */
    public static long factorialIterativo(int n) {
        if (n < 0)
            throw new IllegalArgumentException("El número debe ser positivo");
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    /**
     * Calcula el factorial de un número de manera recursiva.
     * @param n Número entero positivo.
     * @return Factorial de n.
     * @throws IllegalArgumentException Si el número es negativo.
     */
    public static long factorialRecursivo(int n) {
        if (n < 0)
            throw new IllegalArgumentException("El número debe ser positivo");
        if (n == 0 || n == 1)
            return 1;
        return n * factorialRecursivo(n - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un número entero positivo: ");
        try {
            String input = scanner.nextLine();
            int numero = Integer.parseInt(input);

            // Usando el método iterativo
            long resultadoIterativo = factorialIterativo(numero);

            // Usando el método recursivo
            long resultadoRecursivo = factorialRecursivo(numero);

            System.out.printf("Factorial de %d (iterativo): %d%n", numero, resultadoIterativo);
            System.out.printf("Factorial de %d (recursivo): %d%n", numero, resultadoRecursivo);
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número entero.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (StackOverflowError e) {
            System.out.println("Error: El número introducido es demasiado grande para un cálculo recursivo.");
        } finally {
            scanner.close();
        }
    }
}

//PROBLEMA 7
import java.util.Scanner;  // Se importa la clase Scanner para la entrada de datos por consola

public class CalculadoraBasica {

    // Método principal
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Solicita al usuario el primer número
            System.out.print("Ingrese el primer número: ");
            double numero1 = sc.nextDouble();

            // Solicita el operador
            System.out.print("Ingrese el operador (+, -, *, /): ");
            char operador = sc.next().charAt(0);

            // Solicita al usuario el segundo número
            System.out.print("Ingrese el segundo número: ");
            double numero2 = sc.nextDouble();

            // Realiza la operación correspondiente
            double resultado = calcular(numero1, numero2, operador);

            // Muestra el resultado
            System.out.println("Resultado: " + resultado);

        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: entrada no válida. Por favor ingrese números válidos.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            sc.close(); // Se cierra el scanner
        }

        // Caso de prueba funcional
        System.out.println("\n--- Caso de Prueba Automatizado ---");
        probarCasoDePrueba();
    }

    /**
     * Realiza una operación entre dos números según el operador dado.
     * Lanza ArithmeticException si se intenta dividir por cero.
     */
    public static double calcular(double num1, double num2, char operador) throws ArithmeticException {
        switch (operador) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("División por cero no permitida.");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Operador no válido: " + operador);
        }
    }

    /**
     * Método que ejecuta un caso de prueba funcional automático.
     * Validaremos la suma y una división por cero para mostrar manejo de excepciones.
     */
    public static void probarCasoDePrueba() {
        // Prueba de suma
        try {
            double res = calcular(8, 2, '+');
            System.out.println("Prueba 1: 8 + 2 = " + res); // Debe imprimir 10
        } catch (Exception e) {
            System.out.println("Error en la prueba 1: " + e.getMessage());
        }

        // Prueba de división por cero
        try {
            double res2 = calcular(10, 0, '/');
            System.out.println("Prueba 2: 10 / 0 = " + res2);
        } catch (Exception e) {
            System.out.println("Prueba 2: Manejo correcto de división por cero: " + e.getMessage());
        }
    }
}
import java.util.Scanner;  // Se importa la clase Scanner para la entrada de datos por consola

public class CalculadoraBasica {

    // Método principal
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Solicita al usuario el primer número
            System.out.print("Ingrese el primer número: ");
            double numero1 = sc.nextDouble();

            // Solicita el operador
            System.out.print("Ingrese el operador (+, -, *, /): ");
            char operador = sc.next().charAt(0);

            // Solicita al usuario el segundo número
            System.out.print("Ingrese el segundo número: ");
            double numero2 = sc.nextDouble();

            // Realiza la operación correspondiente
            double resultado = calcular(numero1, numero2, operador);

            // Muestra el resultado
            System.out.println("Resultado: " + resultado);

        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: entrada no válida. Por favor ingrese números válidos.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            sc.close(); // Se cierra el scanner
        }

        // Caso de prueba funcional
        System.out.println("\n--- Caso de Prueba Automatizado ---");
        probarCasoDePrueba();
    }

    /**
     * Realiza una operación entre dos números según el operador dado.
     * Lanza ArithmeticException si se intenta dividir por cero.
     */
    public static double calcular(double num1, double num2, char operador) throws ArithmeticException {
        switch (operador) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("División por cero no permitida.");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Operador no válido: " + operador);
        }
    }

    /**
     * Método que ejecuta un caso de prueba funcional automático.
     * Validaremos la suma y una división por cero para mostrar manejo de excepciones.
     */
    public static void probarCasoDePrueba() {
        // Prueba de suma
        try {
            double res = calcular(8, 2, '+');
            System.out.println("Prueba 1: 8 + 2 = " + res); // Debe imprimir 10
        } catch (Exception e) {
            System.out.println("Error en la prueba 1: " + e.getMessage());
        }

        // Prueba de división por cero
        try {
            double res2 = calcular(10, 0, '/');
            System.out.println("Prueba 2: 10 / 0 = " + res2);
        } catch (Exception e) {
            System.out.println("Prueba 2: Manejo correcto de división por cero: " + e.getMessage());
        }
    }
}


//PROBLEMA 8
// Clase que representa un Libro
class Libro {
    private String titulo;
    private String autor;
    private boolean prestado;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean estaPrestado() {
        return prestado;
    }

    public void prestar() throws LibroPrestadoException {
        if (prestado) {
            throw new LibroPrestadoException("El libro '" + titulo + "' ya está prestado.");
        }
        prestado = true;
    }

    public void devolver() {
        prestado = false;
    }

    @Override
    public String toString() {
        return "\"" + titulo + "\" de " + autor + (prestado ? " (prestado)" : " (disponible)");
    }
}

// Excepción personalizada para cuando se intenta prestar un libro que ya está prestado
class LibroPrestadoException extends Exception {
    public LibroPrestadoException(String mensaje) {
        super(mensaje);
    }
}

// Clase que representa una Biblioteca
import java.util.*;

class Biblioteca {
    private List<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    // Añade un nuevo libro
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    // Presta un libro por título
    public void prestarLibro(String titulo) throws LibroPrestadoException, NoSuchElementException {
        Libro libro = buscarLibroPorTitulo(titulo);
        libro.prestar();
    }

    // Devuelve un libro por título
    public void devolverLibro(String titulo) throws NoSuchElementException {
        Libro libro = buscarLibroPorTitulo(titulo);
        libro.devolver();
    }

    // Buscar un libro por su título
    private Libro buscarLibroPorTitulo(String titulo) throws NoSuchElementException {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        throw new NoSuchElementException("No se encontró el libro con título: " + titulo);
    }

    // Muestra todos los libros
    public void mostrarLibros() {
        for (Libro l : libros) {
            System.out.println(l);
        }
    }
}

// Caso de prueba funcional
public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Agregando libros a la biblioteca
        biblioteca.agregarLibro(new Libro("El Quijote", "Miguel de Cervantes"));
        biblioteca.agregarLibro(new Libro("Cien años de soledad", "Gabriel García Márquez"));

        // Mostrar los libros disponibles
        System.out.println("-- Libros en la biblioteca inicialmente --");
        biblioteca.mostrarLibros();

        // Intentar prestar un libro
        try {
            System.out.println("\nSe presta 'El Quijote'.");
            biblioteca.prestarLibro("El Quijote");
        } catch (LibroPrestadoException | NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Estado después de prestar
        System.out.println("\n-- Estado tras préstamo --");
        biblioteca.mostrarLibros();

        // Intentar prestar el mismo libro otra vez
        try {
            System.out.println("\nIntento de prestar de nuevo 'El Quijote'.");
            biblioteca.prestarLibro("El Quijote");
        } catch (LibroPrestadoException | NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Devolver el libro
        try {
            System.out.println("\nDevolviendo 'El Quijote'.");
            biblioteca.devolverLibro("El Quijote");
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Estado final
        System.out.println("\n-- Estado tras devolución --");
        biblioteca.mostrarLibros();
    }
}
