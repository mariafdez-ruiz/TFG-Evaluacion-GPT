// JAVA V-5.5 PROMPT EXPERTO
//PROBLEMA 1
import java.util.Objects;

/**
 * Utilidad para comprobar si un texto es un palíndromo.
 *
 * <p>La comparación:
 * - Ignora mayúsculas y minúsculas.
 * - Ignora espacios, signos de puntuación y otros caracteres no alfanuméricos.
 * - Evita crear una copia normalizada de la cadena, reduciendo el uso de memoria.
 */
public final class PalindromeChecker {

    /**
     * Constructor privado para impedir la instanciación de una clase de utilidad.
     */
    private PalindromeChecker() {
        throw new AssertionError("Esta clase no debe ser instanciada.");
    }

    /**
     * Comprueba si el texto proporcionado es un palíndromo.
     *
     * @param text texto que se desea comprobar
     * @return {@code true} si el texto es un palíndromo; {@code false} en caso contrario
     * @throws NullPointerException si {@code text} es {@code null}
     */
    public static boolean isPalindrome(final String text) {
        Objects.requireNonNull(text, "El texto no puede ser null.");

        int left = 0;
        int right = text.length();

        while (left < right) {
            final int leftCodePoint = text.codePointAt(left);

            if (!Character.isLetterOrDigit(leftCodePoint)) {
                left += Character.charCount(leftCodePoint);
                continue;
            }

            final int rightCodePoint = text.codePointBefore(right);

            if (!Character.isLetterOrDigit(rightCodePoint)) {
                right -= Character.charCount(rightCodePoint);
                continue;
            }

            if (Character.toLowerCase(leftCodePoint)
                    != Character.toLowerCase(rightCodePoint)) {
                return false;
            }

            left += Character.charCount(leftCodePoint);
            right -= Character.charCount(rightCodePoint);
        }

        return true;
    }
}


//PROBLEMA 2
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servicio responsable de calcular la frecuencia de caracteres de una cadena.
 *
 * Se utiliza LinkedHashMap para mantener el orden de aparición de los
 * caracteres y exponer el resultado de forma clara y determinista.
 */
final class CharacterFrequencyService {

    /**
     * Calcula la frecuencia de cada carácter Unicode de la cadena.
     *
     * @param text cadena que se desea analizar
     * @return mapa inmutable donde la clave es el carácter (code point)
     *         y el valor es su frecuencia
     * @throws IllegalArgumentException si text es null
     */
    public Map<Integer, Integer> count(final String text) {
        if (text == null) {
            throw new IllegalArgumentException("La cadena no puede ser null.");
        }

        final Map<Integer, Integer> frequencies = new LinkedHashMap<>();

        // codePoints() permite tratar correctamente caracteres Unicode
        // representados mediante pares sustitutos en UTF-16 (p. ej. emojis).
        text.codePoints().forEach(codePoint ->
                frequencies.merge(codePoint, 1, Integer::sum)
        );

        // Evita que el consumidor pueda modificar accidentalmente el resultado.
        return Collections.unmodifiableMap(frequencies);
    }
}

/**
 * Punto de entrada y responsable de la interacción con el usuario.
 */
public class Main {

    private Main() {
        // Impide la instanciación de una clase exclusivamente ejecutable.
    }

    public static void main(String[] args) {
        final CharacterFrequencyService service = new CharacterFrequencyService();

        // try-with-resources garantiza el cierre del recurso incluso ante errores.
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Introduce una cadena: ");

            if (!scanner.hasNextLine()) {
                System.err.println("Error: no se recibió ninguna entrada.");
                return;
            }

            final String input = scanner.nextLine();
            final Map<Integer, Integer> frequencies = service.count(input);

            printFrequencies(frequencies);

        } catch (IllegalArgumentException exception) {
            System.err.println("Entrada inválida: " + exception.getMessage());
        } catch (IllegalStateException exception) {
            System.err.println("Error al procesar la entrada: " + exception.getMessage());
        }
    }

    /**
     * Presenta el resultado como pares clave-valor.
     */
    private static void printFrequencies(final Map<Integer, Integer> frequencies) {
        System.out.println("{");

        frequencies.forEach((codePoint, frequency) ->
                System.out.printf("  \"%s\": %d%n",
                        new String(Character.toChars(codePoint)),
                        frequency)
        );

        System.out.println("}");
    }
}



//PROBLEMA 3
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servicio responsable de analizar texto y obtener la palabra
 * de mayor longitud, ignorando signos de puntuación.
 *
 * Se utiliza una expresión Unicode para soportar correctamente
 * palabras con caracteres acentuados y alfabetos no ASCII.
 */
public final class LongestWordAnalyzer {

    /*
     * Una palabra se define como una secuencia de letras Unicode.
     * Los apóstrofes, guiones, números, espacios y demás signos
     * no forman parte de una palabra.
     */
    private static final Pattern WORD_PATTERN = Pattern.compile("\\p{L}+");

    /**
     * Devuelve la palabra de mayor longitud encontrada en una frase.
     *
     * En caso de empate, devuelve la primera palabra encontrada.
     *
     * @param phrase frase que se desea analizar
     * @return palabra de mayor longitud
     * @throws NullPointerException     si phrase es null
     * @throws IllegalArgumentException si no contiene ninguna palabra
     */
    public String findLongestWord(final String phrase) {
        Objects.requireNonNull(phrase, "La frase no puede ser null.");

        final Matcher matcher = WORD_PATTERN.matcher(phrase);

        String longestWord = null;
        int longestLength = 0;

        while (matcher.find()) {
            final String currentWord = matcher.group();

            /*
             * codePointCount es preferible a String.length():
             * mide caracteres Unicode en lugar de unidades UTF-16.
             */
            final int currentLength = currentWord.codePointCount(
                    0, currentWord.length()
            );

            if (currentLength > longestLength) {
                longestWord = currentWord;
                longestLength = currentLength;
            }
        }

        if (longestWord == null) {
            throw new IllegalArgumentException(
                    "La frase debe contener al menos una palabra."
            );
        }

        return longestWord;
    }
}

//PROBLEMA 4
/**
 * Utilidad para determinar si un número entero es primo.
 *
 * <p>La clase es inmutable y no puede ser instanciada ni extendida,
 * ya que únicamente expone comportamiento estático.</p>
 */
public final class PrimeNumber {

    private PrimeNumber() {
        // Evita la instanciación de una clase de utilidad.
        throw new AssertionError("Utility class must not be instantiated");
    }

    /**
     * Determina si un número entero es primo.
     *
     * <p>Un número primo es un entero mayor que 1 que únicamente
     * es divisible entre 1 y él mismo.</p>
     *
     * @param number número que se desea evaluar
     * @return {@code true} si el número es primo; {@code false} en caso contrario
     */
    public static boolean isPrime(final int number) {
        if (number < 2) {
            return false;
        }

        if (number == 2 || number == 3) {
            return true;
        }

        // Descarta inmediatamente múltiplos de 2 y 3.
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        /*
         * Todo primo mayor que 3 tiene la forma 6k ± 1.
         *
         * Solo necesitamos comprobar divisores hasta sqrt(number).
         * Se utiliza divisor <= number / divisor en lugar de
         * divisor * divisor <= number para evitar overflow de int.
         */
        for (int divisor = 5;
             divisor <= number / divisor;
             divisor += 6) {

            if (number % divisor == 0 || number % (divisor + 2) == 0) {
                return false;
            }
        }

        return true;
    }
}



//PROBLEMA 5
import java.util.Collection;
import java.util.Objects;

/**
 * Utilidad para calcular manualmente los valores mínimo y máximo
 * de una colección de enteros.
 *
 * La clase no mantiene estado y no puede ser instanciada.
 */
public final class IntegerRangeCalculator {

    private IntegerRangeCalculator() {
        throw new AssertionError("Esta clase no debe ser instanciada");
    }

    /**
     * Obtiene el valor mínimo y máximo de una colección.
     *
     * @param values colección de enteros; no puede ser nula ni vacía
     *               y no puede contener elementos nulos
     * @return resultado inmutable con el mínimo y máximo encontrados
     * @throws NullPointerException     si la colección es nula
     * @throws IllegalArgumentException si está vacía o contiene elementos nulos
     */
    public static MinMax calculate(Collection<Integer> values) {
        Objects.requireNonNull(values, "La colección no puede ser nula");

        if (values.isEmpty()) {
            throw new IllegalArgumentException("La colección no puede estar vacía");
        }

        Integer minimum = null;
        Integer maximum = null;

        for (Integer value : values) {
            if (value == null) {
                throw new IllegalArgumentException(
                        "La colección no puede contener elementos nulos"
                );
            }

            /*
             * La primera iteración inicializa ambos extremos.
             * De esta manera evitamos usar valores centinela artificiales
             * como Integer.MIN_VALUE o Integer.MAX_VALUE.
             */
            if (minimum == null) {
                minimum = value;
                maximum = value;
                continue;
            }

            if (value < minimum) {
                minimum = value;
            }

            if (value > maximum) {
                maximum = value;
            }
        }

        return new MinMax(minimum, maximum);
    }

    /**
     * Value Object inmutable que encapsula el resultado.
     */
    public record MinMax(int min, int max) {

        public MinMax {
            if (min > max) {
                throw new IllegalArgumentException(
                        "El mínimo no puede ser mayor que el máximo"
                );
            }
        }
    }
}


//PROBLEMA 6
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Aplicación que calcula el factorial de un entero positivo
 * mediante implementaciones iterativa y recursiva.
 *
 * Se utiliza BigInteger para evitar desbordamientos numéricos.
 */
public final class FactorialApp {

    private FactorialApp() {
        // Evita la instanciación de esta clase de utilidad.
    }

    /**
     * Calcula el factorial de forma iterativa.
     *
     * @param number entero positivo
     * @return factorial de number
     * @throws IllegalArgumentException si number no es positivo
     */
    public static BigInteger factorialIterative(int number) {
        validatePositive(number);

        BigInteger result = BigInteger.ONE;

        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }

    /**
     * Calcula el factorial de forma recursiva.
     *
     * @param number entero positivo
     * @return factorial de number
     * @throws IllegalArgumentException si number no es positivo
     */
    public static BigInteger factorialRecursive(int number) {
        validatePositive(number);
        return factorialRecursiveInternal(number);
    }

    /**
     * Método interno para evitar repetir la validación
     * en cada llamada recursiva.
     */
    private static BigInteger factorialRecursiveInternal(int number) {
        if (number == 1) {
            return BigInteger.ONE;
        }

        return BigInteger.valueOf(number)
                .multiply(factorialRecursiveInternal(number - 1));
    }

    /**
     * Centraliza la regla de validación de dominio.
     */
    private static void validatePositive(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(
                    "El número debe ser un entero positivo mayor que cero."
            );
        }
    }

    /**
     * Demuestra el funcionamiento de ambas implementaciones
     * y verifica que produzcan el mismo resultado.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Introduce un entero positivo: ");

            if (!scanner.hasNextInt()) {
                throw new IllegalArgumentException(
                        "La entrada debe ser un entero válido."
                );
            }

            int number = scanner.nextInt();

            BigInteger iterativeResult = factorialIterative(number);
            BigInteger recursiveResult = factorialRecursive(number);

            System.out.printf(
                    "Factorial iterativo de %d: %s%n",
                    number,
                    iterativeResult
            );

            System.out.printf(
                    "Factorial recursivo de %d: %s%n",
                    number,
                    recursiveResult
            );

            if (!iterativeResult.equals(recursiveResult)) {
                throw new IllegalStateException(
                        "Las implementaciones produjeron resultados diferentes."
                );
            }

            System.out.println("Ambas implementaciones producen el mismo resultado.");

        } catch (IllegalArgumentException exception) {
            System.err.println("Error de entrada: " + exception.getMessage());
        } catch (StackOverflowError error) {
            System.err.println(
                    "El valor es demasiado grande para la implementación recursiva."
            );
        }
    }
}




//PROBLEMA 7
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Calculadora básica que implementa las cuatro operaciones aritméticas
 * fundamentales y un manejo explícito de errores.
 */
public final class Calculadora {

    /**
     * Realiza la operación indicada.
     *
     * @param primerNumero  primer operando
     * @param segundoNumero segundo operando
     * @param operador      operador (+, -, *, /)
     * @return resultado de la operación
     * @throws ArithmeticException       si se intenta dividir entre cero
     * @throws IllegalArgumentException  si el operador no está soportado
     */
    public double calcular(double primerNumero, double segundoNumero, char operador) {
        return switch (operador) {
            case '+' -> sumar(primerNumero, segundoNumero);
            case '-' -> restar(primerNumero, segundoNumero);
            case '*' -> multiplicar(primerNumero, segundoNumero);
            case '/' -> dividir(primerNumero, segundoNumero);
            default -> throw new IllegalArgumentException(
                    "Operador no válido: " + operador
            );
        };
    }

    private double sumar(double a, double b) {
        return a + b;
    }

    private double restar(double a, double b) {
        return a - b;
    }

    private double multiplicar(double a, double b) {
        return a * b;
    }

    private double dividir(double dividendo, double divisor) {
        // Con double, Java no lanza ArithmeticException automáticamente
        // al dividir entre cero; por ello se realiza una validación explícita.
        if (Double.compare(divisor, 0.0) == 0) {
            throw new ArithmeticException("No se puede dividir entre cero.");
        }

        return dividendo / divisor;
    }

    /**
     * Punto de entrada de la aplicación.
     * La interfaz de consola se mantiene separada de la lógica de cálculo.
     */
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();

        // try-with-resources garantiza el cierre del recurso.
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese el primer número: ");
            double primerNumero = scanner.nextDouble();

            System.out.print("Ingrese el operador (+, -, *, /): ");
            char operador = scanner.next().charAt(0);

            System.out.print("Ingrese el segundo número: ");
            double segundoNumero = scanner.nextDouble();

            double resultado = calculadora.calcular(
                    primerNumero,
                    segundoNumero,
                    operador
            );

            System.out.println("Resultado: " + resultado);

        } catch (InputMismatchException e) {
            System.err.println("Error: debe ingresar valores numéricos válidos.");
        } catch (ArithmeticException | IllegalArgumentException e) {
            // Se capturan únicamente las excepciones que pueden gestionarse
            // de forma significativa en esta capa.
            System.err.println("Error: " + e.getMessage());
        }
    }
}




//PROBLEMA 8
// Libro.java

import java.util.Objects;

/**
 * Representa un libro dentro del sistema.
 *
 * La clase encapsula completamente su estado y controla las transiciones
 * entre disponible y prestado, evitando modificaciones externas inválidas.
 */
public final class Libro {

    private final String isbn;
    private final String titulo;
    private boolean prestado;

    public Libro(String isbn, String titulo) {
        this.isbn = validarTexto(isbn, "El ISBN es obligatorio");
        this.titulo = validarTexto(titulo, "El título es obligatorio");
        this.prestado = false;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean estaPrestado() {
        return prestado;
    }

    /**
     * Presta el libro.
     *
     * @throws LibroYaPrestadoException si el libro ya estaba prestado.
     */
    public void prestar() {
        if (prestado) {
            throw new LibroYaPrestadoException(
                    "El libro '" + titulo + "' ya está prestado"
            );
        }
        prestado = true;
    }

    /**
     * Devuelve el libro.
     *
     * @throws LibroNoPrestadoException si el libro no estaba prestado.
     */
    public void devolver() {
        if (!prestado) {
            throw new LibroNoPrestadoException(
                    "El libro '" + titulo + "' no está prestado"
            );
        }
        prestado = false;
    }

    private static String validarTexto(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor.trim();
    }

    /*
     * El ISBN representa la identidad lógica del libro.
     * Esto permite utilizar Libro de forma consistente en colecciones.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof Libro otro)) {
            return false;
        }
        return isbn.equals(otro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", prestado=" + prestado +
                '}';
    }
}

// Biblioteca.java

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Gestiona el catálogo y las operaciones de préstamo de la biblioteca.
 *
 * Los libros se indexan por ISBN para proporcionar búsquedas eficientes
 * y garantizar que no existan dos ejemplares con el mismo identificador.
 */
public final class Biblioteca {

    private final Map<String, Libro> libros = new LinkedHashMap<>();

    /**
     * Añade un libro al catálogo.
     *
     * @throws IllegalArgumentException si el libro es null.
     * @throws LibroDuplicadoException si ya existe su ISBN.
     */
    public void agregarLibro(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser null");
        }

        if (libros.containsKey(libro.getIsbn())) {
            throw new LibroDuplicadoException(
                    "Ya existe un libro con ISBN: " + libro.getIsbn()
            );
        }

        libros.put(libro.getIsbn(), libro);
    }

    /**
     * Presta el libro identificado por su ISBN.
     *
     * @throws LibroNoEncontradoException si el ISBN no pertenece al catálogo.
     * @throws LibroYaPrestadoException si el libro ya está prestado.
     */
    public void prestarLibro(String isbn) {
        obtenerLibroObligatorio(isbn).prestar();
    }

    /**
     * Devuelve el libro identificado por su ISBN.
     *
     * @throws LibroNoEncontradoException si el ISBN no pertenece al catálogo.
     * @throws LibroNoPrestadoException si el libro no estaba prestado.
     */
    public void devolverLibro(String isbn) {
        obtenerLibroObligatorio(isbn).devolver();
    }

    /**
     * Consulta segura: la ausencia de un libro se representa mediante Optional.
     */
    public Optional<Libro> buscarPorIsbn(String isbn) {
        validarIsbn(isbn);
        return Optional.ofNullable(libros.get(isbn.trim()));
    }

    /**
     * Devuelve una vista de solo lectura del catálogo.
     */
    public Collection<Libro> obtenerLibros() {
        return Collections.unmodifiableCollection(libros.values());
    }

    private Libro obtenerLibroObligatorio(String isbn) {
        validarIsbn(isbn);
        String isbnNormalizado = isbn.trim();

        Libro libro = libros.get(isbnNormalizado);
        if (libro == null) {
            throw new LibroNoEncontradoException(
                    "No existe un libro con ISBN: " + isbnNormalizado
            );
        }

        return libro;
    }

    private static void validarIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("El ISBN es obligatorio");
        }
    }
}
// LibroYaPrestadoException.java

/**
 * Excepción de dominio lanzada al intentar prestar un libro
 * que ya se encuentra prestado.
 */
public final class LibroYaPrestadoException extends RuntimeException {

    public LibroYaPrestadoException(String mensaje) {
        super(mensaje);
    }
}

// LibroNoPrestadoException.java

/**
 * Excepción de dominio lanzada al intentar devolver un libro
 * que actualmente no está prestado.
 */
public final class LibroNoPrestadoException extends RuntimeException {

    public LibroNoPrestadoException(String mensaje) {
        super(mensaje);
    }
}

// LibroNoEncontradoException.java

/**
 * Excepción de dominio para operaciones sobre libros inexistentes.
 */
public final class LibroNoEncontradoException extends RuntimeException {

    public LibroNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

// LibroDuplicadoException.java

/**
 * Impide registrar dos libros con la misma identidad (ISBN).
 */
public final class LibroDuplicadoException extends RuntimeException {

    public LibroDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
