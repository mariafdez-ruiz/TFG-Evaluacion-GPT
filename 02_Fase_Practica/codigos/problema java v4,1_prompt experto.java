// JAVA V-4.1 PROMPT EXPERTO
//PROBLEMA 1
package com.example.palindrome;

import java.text.Normalizer;

/**
 * Utility class for palindrome checking.
 */
public final class PalindromeChecker {

    private PalindromeChecker() {
        // Prevent instantiation
    }

    /**
     * Checks if the input string is a palindrome, ignoring case, spaces, and punctuation.
     *
     * @param input the string to check
     * @return true if the input is a palindrome; false otherwise
     * @throws IllegalArgumentException if input is null
     */
    public static boolean isPalindrome(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }
        String normalized = normalize(input);
        return isPalindromeNormalized(normalized);
    }

    /**
     * Removes all non-letter/digit characters, converts to lowercase, and removes accents.
     *
     * @param input the original string
     * @return normalized string for palindrome checking
     */
    private static String normalize(String input) {
        // Remove accents/diacritics
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        // Remove non-letter or digit characters, and convert to lowercase
        return normalized.replaceAll("[^\\p{Alnum}]", "").toLowerCase();
    }

    /**
     * Checks if the normalized string is a palindrome.
     *
     * @param normalized the preprocessed string
     * @return true if palindrome
     */
    private static boolean isPalindromeNormalized(String normalized) {
        int left = 0;
        int right = normalized.length() - 1;
        while (left < right) {
            if (normalized.charAt(left) != normalized.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}



//PROBLEMA 2
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Clase utilitaria para analizar la frecuencia de caracteres en una cadena.
 * Implementa una solución robusta, eficiente y limpia basada en principios de
 * encapsulamiento, manejo de excepciones y código legible.
 */
public final class CharacterFrequencyCounter {

    private CharacterFrequencyCounter() {
        // Constructor privado para evitar instanciación de clase utilitaria
    }

    /**
     * Calcula la frecuencia de cada caracter en la cadena provista,
     * devolviendo un Map ordenado según el orden de aparición de caracteres.
     *
     * @param input La cadena de texto cuyos caracteres se analizarán.
     * @return Un Map con caracteres como clave y su frecuencia como valor.
     * @throws IllegalArgumentException Si la cadena de entrada es nula.
     */
    public static Map<Character, Integer> countCharacterFrequency(String input) {
        Objects.requireNonNull(input, "La cadena de entrada no puede ser nula");

        Map<Character, Integer> frequencyMap = new LinkedHashMap<>();

        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        return frequencyMap;
    }

    /**
     * Método de utilidad para mostrar el contenido del mapa de frecuencias
     * de manera clara y legible.
     *
     * @param frequencyMap Mapa con los caracteres y sus frecuencias.
     * @return Cadena formateada con pares clave-valor.
     */
    public static String formatFrequencyMap(Map<Character, Integer> frequencyMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        frequencyMap.forEach((character, frequency) -> 
            sb.append("'").append(character).append("'").append(": ").append(frequency).append(", ")
        );
        // Eliminar la última coma y espacio si el mapa no está vacío
        if (!frequencyMap.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }
}



//PROBLEMA 3
import java.util.Objects;

/**
 * Excepción personalizada para frases no válidas.
 */
class InvalidPhraseException extends Exception {
    public InvalidPhraseException(String message) {
        super(message);
    }
}

/**
 * Clase responsable de encontrar la palabra más larga en una frase.
 */
public class LongestWordFinder {

    /**
     * Devuelve la palabra de mayor longitud de una frase, ignorando signos de puntuación.
     *
     * @param phrase Frase a analizar.
     * @return Palabra más larga de la frase.
     * @throws InvalidPhraseException si la frase es nula o vacía.
     */
    public String findLongestWord(String phrase) throws InvalidPhraseException {
        validatePhrase(phrase);

        // Elimina signos de puntuación no alfabéticos, mantiene espacios y letras.
        String normalizedPhrase = normalizePhrase(phrase);

        String[] words = normalizedPhrase.split("\\s+");

        String longestWord = "";
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        return longestWord;
    }

    /**
     * Valida si la frase de entrada es válida.
     *
     * @param phrase Frase a validar.
     * @throws InvalidPhraseException si la frase es nula o vacía.
     */
    private void validatePhrase(String phrase) throws InvalidPhraseException {
        if (Objects.isNull(phrase) || phrase.trim().isEmpty()) {
            throw new InvalidPhraseException("La frase proporcionada es nula o vacía.");
        }
    }

    /**
     * Normaliza la frase eliminando los signos de puntuación.
     *
     * @param phrase Frase a normalizar.
     * @return Frase sin puntuación.
     */
    private String normalizePhrase(String phrase) {
        // Conserva las letras y los espacios. Elimina cualquier otro caracter (puntuación, dígitos, etc.)
        return phrase.replaceAll("[^\\p{L}\\s]", "");
    }

    // Punto de entrada para prueba (opcional, puede eliminarse si solo se importa la clase).
    public static void main(String[] args) {
        LongestWordFinder finder = new LongestWordFinder();
        String phrase = "¡Hola, mundo! ¿Cómo estás? Programación en Java: increíblemente divertida.";
        try {
            System.out.println("Palabra más larga: " + finder.findLongestWord(phrase));
        } catch (InvalidPhraseException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}


//PROBLEMA 4
package com.example.primechecker;

/**
 * Clase para verificar si un número es primo.
 * Encapsula la lógica y el manejo de errores.
 */
public class PrimeChecker {

    /**
     * Determina si un número entero dado es primo.
     *
     * @param number Número entero a comprobar (>=2)
     * @return true si el número es primo, false en caso contrario.
     * @throws InvalidPrimeInputException si el número es menor que 2.
     */
    public boolean isPrime(int number) throws InvalidPrimeInputException {
        if (number < 2) {
            throw new InvalidPrimeInputException("El número debe ser mayor o igual a 2.");
        }

        // Caso especial para el número 2
        if (number == 2) {
            return true;
        }

        // Descarta números pares mayores que 2
        if (number % 2 == 0) {
            return false;
        }

        // Solo es necesario verificar hasta la raíz cuadrada del número
        int squareRoot = (int) Math.sqrt(number);
        for (int divisor = 3; divisor <= squareRoot; divisor += 2) {
            if (number % divisor == 0) {
                return false;
            }
        }

        return true;
    }
}

/**
 * Excepción personalizada para entradas inválidas en la verificación de números primos.
 */
class InvalidPrimeInputException extends Exception {
    public InvalidPrimeInputException(String message) {
        super(message);
    }
}



//PROBLEMA 5
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Clase que encapsula los valores mínimo y máximo de una colección de enteros.
 */
public final class IntRange {
    private final int min;
    private final int max;

    public IntRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * @return Valor mínimo encontrado.
     */
    public int getMin() {
        return min;
    }

    /**
     * @return Valor máximo encontrado.
     */
    public int getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "IntRange{min=" + min + ", max=" + max + "}";
    }
}

/**
 * Utilidad para operaciones sobre colecciones de enteros.
 */
public final class IntegerCollectionUtils {

    // Constructor privado para evitar instanciación
    private IntegerCollectionUtils() {}

    /**
     * Obtiene el rango (mínimo y máximo) de una colección de enteros.
     * @param numbers Colección de enteros. No puede ser null/vacía.
     * @return IntRange con el mínimo y máximo valor.
     * @throws NoSuchElementException si la colección es nula o vacía.
     */
    public static IntRange findMinMax(Collection<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new NoSuchElementException("La colección no puede ser nula ni vacía.");
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (Integer number : numbers) {
            // Validación defensiva ante posibles nulos en la colección
            Objects.requireNonNull(number, "La colección contiene un elemento nulo.");

            if (number < min) min = number;
            if (number > max) max = number;
        }
        return new IntRange(min, max);
    }
}


//PROBLEMA 6
/**
 * Clase utilitaria para el cálculo de factoriales usando métodos iterativo y recursivo.
 */
public class FactorialCalculator {

    // Valor máximo de n para el cual n! cabe en un long
    private static final int MAX_N = 20;

    /**
     * Calcula el factorial de un número usando un algoritmo iterativo.
     *
     * @param n Número entero positivo
     * @return Factorial de n como long
     * @throws IllegalArgumentException Si n es negativo o mayor que 20 (overflow long)
     */
    public long factorialIterative(int n) {
        validateInput(n);

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Calcula el factorial de un número usando un algoritmo recursivo.
     *
     * @param n Número entero positivo
     * @return Factorial de n como long
     * @throws IllegalArgumentException Si n es negativo o mayor que 20 (overflow long)
     */
    public long factorialRecursive(int n) {
        validateInput(n);

        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }

    /**
     * Valida que el número esté dentro de los límites permitidos.
     *
     * @param n Número solicitado
     * @throws IllegalArgumentException Si n es negativo o mayor a MAX_N (peligro de overflow de long)
     */
    private void validateInput(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El factorial no está definido para números negativos.");
        }
        if (n > MAX_N) {
            throw new IllegalArgumentException("El valor máximo permitido es " + MAX_N
                    + " para evitar desbordamientos (overflow) de tipo long.");
        }
    }

    /**
     * Ejemplo de uso y demostración de ambos métodos.
     */
    public static void main(String[] args) {
        FactorialCalculator calculator = new FactorialCalculator();
        int number = 5; // Cambia este valor para probar otros casos

        // Iterativo
        try {
            long resultIterative = calculator.factorialIterative(number);
            System.out.println("Iterativo: " + number + "! = " + resultIterative);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Recursivo
        try {
            long resultRecursive = calculator.factorialRecursive(number);
            System.out.println("Recursivo: " + number + "! = " + resultRecursive);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


//PROBLEMA 7
/**
 * Calculadora.java
 * 
 * Implementa una calculadora básica con las cuatro operaciones aritméticas.
 * Incluye manejo explícito de excepciones como la división por cero.
 */

public class Calculadora {

    /**
     * Suma dos números.
     *
     * @param a primer operando
     * @param b segundo operando
     * @return la suma de a y b
     */
    public double sumar(double a, double b) {
        return a + b;
    }

    /**
     * Resta dos números.
     *
     * @param a primer operando
     * @param b segundo operando
     * @return la resta de a menos b
     */
    public double restar(double a, double b) {
        return a - b;
    }

    /**
     * Multiplica dos números.
     *
     * @param a primer operando
     * @param b segundo operando
     * @return el producto de a y b
     */
    public double multiplicar(double a, double b) {
        return a * b;
    }

    /**
     * Divide dos números, controlando la división por cero.
     *
     * @param a primer operando (dividendo)
     * @param b segundo operando (divisor)
     * @return el cociente de a entre b
     * @throws ArithmeticException si b es cero
     */
    public double dividir(double a, double b) {
        if (b == 0.0) {
            throw new ArithmeticException("Error: División por cero");
        }
        return a / b;
    }
}
/**
 * Main.java
 *
 * Clase de ejemplo para probar la calculadora.
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Calculadora básica");
        System.out.print("Ingrese el primer número: ");
        double a = scanner.nextDouble();

        System.out.print("Ingrese el segundo número: ");
        double b = scanner.nextDouble();

        try {
            System.out.println("Suma: " + calculadora.sumar(a, b));
            System.out.println("Resta: " + calculadora.restar(a, b));
            System.out.println("Multiplicación: " + calculadora.multiplicar(a, b));
            System.out.println("División: " + calculadora.dividir(a, b));
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}


//PROBLEMA 8
// Clase de excepción para manejo específico de libros ya prestados
public class LibroPrestadoException extends Exception {
    public LibroPrestadoException(String mensaje) {
        super(mensaje);
    }
}

// Representa un libro de la biblioteca
public class Libro {
    private final String isbn;
    private final String titulo;
    private final String autor;
    private boolean prestado;

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
    }

    // Métodos de consulta (getters)
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean estaPrestado() { return prestado; }

    // Marca el libro como prestado
    void prestar() { this.prestado = true; }

    // Marca el libro como no prestado
    void devolver() { this.prestado = false; }
}

// Clase principal de gestión de la biblioteca
import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
    // Map de ISBN a libro para acceso rápido
    private final Map<String, Libro> catalogo = new HashMap<>();

    // Añade un libro a la biblioteca (no se sobreescriben libros existentes)
    public void agregarLibro(Libro libro) {
        if (libro == null || catalogo.containsKey(libro.getIsbn())) {
            throw new IllegalArgumentException("Libro inválido o ya existente en el catálogo.");
        }
        catalogo.put(libro.getIsbn(), libro);
    }

    // Presta un libro por ISBN. Lanza excepción si no existe o ya está prestado.
    public void prestarLibro(String isbn) throws LibroPrestadoException {
        Libro libro = buscarLibro(isbn);
        if (libro.estaPrestado()) {
            throw new LibroPrestadoException("El libro ya está prestado: " + isbn);
        }
        libro.prestar();
    }

    // Devuelve un libro por ISBN. Lanza excepción si no existe o no estaba prestado.
    public void devolverLibro(String isbn) {
        Libro libro = buscarLibro(isbn);
        if (!libro.estaPrestado()) {
            throw new IllegalStateException("El libro no está prestado: " + isbn);
        }
        libro.devolver();
    }

    // Método privado para buscar un libro
    private Libro buscarLibro(String isbn) {
        Libro libro = catalogo.get(isbn);
        if (libro == null) {
            throw new IllegalArgumentException("No existe un libro con ISBN: " + isbn);
        }
        return libro;
    }
}
