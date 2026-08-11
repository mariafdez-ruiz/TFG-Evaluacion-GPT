// JAVA V-5.5 PROMPT MEDIO
//PROBLEMA 1
import java.util.Scanner;

public class Palindromo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que introduzca la cadena
        System.out.print("Introduce una cadena de texto: ");
        String texto = scanner.nextLine();

        // Quitar espacios y pasar todo a minúsculas para una comparación exacta
        String textoProcesado = texto.replaceAll("\\s+", "").toLowerCase();

        // Verificar si la cadena es un palíndromo
        boolean esPalindromo = true;
        int n = textoProcesado.length();
        for (int i = 0; i < n / 2; i++) {
            if (textoProcesado.charAt(i) != textoProcesado.charAt(n - 1 - i)) {
                esPalindromo = false;
                break;
            }
        }

        // Mostrar el resultado
        if (esPalindromo) {
            System.out.println("La cadena es un palíndromo.");
        } else {
            System.out.println("La cadena no es un palíndromo.");
        }

        scanner.close();
    }
}

//PROBLEMA 2
import java.util.LinkedHashMap;
import java.util.Map;

public class FrecuenciaCaracteres {

    /**
     * Cuenta la frecuencia de cada carácter de una cadena.
     *
     * @param texto Cadena que se desea analizar.
     * @return Mapa donde la clave es el carácter y el valor es su frecuencia.
     * @throws IllegalArgumentException si el texto es null.
     */
    public static Map<Character, Integer> contarCaracteres(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("La cadena de texto no puede ser null.");
        }

        // LinkedHashMap conserva el orden de primera aparición.
        Map<Character, Integer> frecuencias = new LinkedHashMap<>();

        // Recorremos todos los caracteres de la cadena.
        for (char caracter : texto.toCharArray()) {
            // Si el carácter ya existe, incrementamos su frecuencia.
            // En caso contrario, comenzamos en 0 y sumamos 1.
            frecuencias.put(
                caracter,
                frecuencias.getOrDefault(caracter, 0) + 1
            );
        }

        return frecuencias;
    }

    /**
     * Muestra el mapa de frecuencias de forma clara.
     */
    public static void mostrarFrecuencias(Map<Character, Integer> frecuencias) {
        if (frecuencias.isEmpty()) {
            System.out.println("La cadena está vacía: no hay caracteres que contar.");
            return;
        }

        System.out.println("Frecuencia de caracteres:");

        for (Map.Entry<Character, Integer> entrada : frecuencias.entrySet()) {
            char caracter = entrada.getKey();

            // Hacemos visibles algunos caracteres especiales.
            String representacion;
            if (caracter == ' ') {
                representacion = "[espacio]";
            } else if (caracter == '\n') {
                representacion = "[salto de línea]";
            } else if (caracter == '\t') {
                representacion = "[tabulación]";
            } else {
                representacion = String.valueOf(caracter);
            }

            System.out.println(representacion + " -> " + entrada.getValue());
        }
    }

    public static void main(String[] args) {
        // Caso de prueba funcional.
        String texto = "programar";

        try {
            Map<Character, Integer> resultado = contarCaracteres(texto);

            System.out.println("Texto analizado: \"" + texto + "\"");
            mostrarFrecuencias(resultado);

        } catch (IllegalArgumentException e) {
            // Gestionamos entradas no válidas.
            System.err.println("Error de entrada: " + e.getMessage());

        } catch (Exception e) {
            // Captura cualquier error inesperado sin finalizar abruptamente.
            System.err.println("Se produjo un error inesperado: " + e.getMessage());
        }
    }
}

//PROBLEMA 3
public class PalabraMasLarga {

    /**
     * Encuentra la palabra de mayor longitud de una frase.
     * Los signos de puntuación son ignorados.
     *
     * @param frase frase que se desea analizar
     * @return la palabra más larga, o una cadena vacía si no hay palabras
     * @throws IllegalArgumentException si la frase es null
     */
    public static String encontrarPalabraMasLarga(String frase) {
        if (frase == null) {
            throw new IllegalArgumentException("La frase no puede ser null.");
        }

        // Sustituye cualquier carácter que no sea una letra o un dígito
        // por un espacio. \p{L} reconoce letras Unicode, incluyendo á, ñ, etc.
        String fraseLimpia = frase.replaceAll("[^\\p{L}\\p{N}]+", " ").trim();

        // Si después de eliminar la puntuación no quedan palabras.
        if (fraseLimpia.isEmpty()) {
            return "";
        }

        // Separa la frase utilizando uno o más espacios como delimitador.
        String[] palabras = fraseLimpia.split("\\s+");

        String palabraMasLarga = "";

        // Recorre todas las palabras y conserva la de mayor longitud.
        for (String palabra : palabras) {
            if (palabra.length() > palabraMasLarga.length()) {
                palabraMasLarga = palabra;
            }
        }

        return palabraMasLarga;
    }

    public static void main(String[] args) {
        // Caso de prueba.
        String frase = "¡Hola! La programación en Java es extraordinariamente útil.";

        try {
            String resultado = encontrarPalabraMasLarga(frase);

            System.out.println("Frase: " + frase);

            if (resultado.isEmpty()) {
                System.out.println("No se encontraron palabras en la frase.");
            } else {
                System.out.println("Palabra de mayor longitud: " + resultado);
                System.out.println("Longitud: " + resultado.length());
            }

        } catch (IllegalArgumentException e) {
            // Gestiona entradas inválidas, como una referencia null.
            System.err.println("Error de entrada: " + e.getMessage());
        } catch (Exception e) {
            // Evita que un error inesperado termine el programa sin informar.
            System.err.println("Se produjo un error inesperado: " + e.getMessage());
        }
    }
}


//PROBLEMA 4
import java.util.Scanner;

public class NumeroPrimo {

    /**
     * Determina si un número entero es primo.
     *
     * @param numero número que se desea comprobar
     * @return true si el número es primo; false en caso contrario
     */
    public static boolean esPrimo(int numero) {
        // Por definición, los números menores que 2 no son primos.
        if (numero < 2) {
            return false;
        }

        // El 2 es el único número primo par.
        if (numero == 2) {
            return true;
        }

        // Cualquier otro número par no puede ser primo.
        if (numero % 2 == 0) {
            return false;
        }

        /*
         * Solo es necesario comprobar divisores hasta la raíz cuadrada.
         * Se usa i <= numero / i en lugar de i * i <= numero para evitar
         * un posible desbordamiento de enteros.
         *
         * Como los pares ya se descartaron, comprobamos solo impares.
         */
        for (int i = 3; i <= numero / i; i += 2) {
            if (numero % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // try-with-resources garantiza el cierre del Scanner.
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Introduce un número entero: ");

            // Validamos que la entrada pueda representarse como int.
            if (!scanner.hasNextInt()) {
                System.err.println(
                    "Error: debes introducir un número entero válido."
                );
                return;
            }

            int numero = scanner.nextInt();

            if (esPrimo(numero)) {
                System.out.println(numero + " es un número primo.");
            } else {
                System.out.println(numero + " no es un número primo.");
            }

        } catch (Exception e) {
            // Gestión de cualquier error inesperado durante la ejecución.
            System.err.println(
                "Se produjo un error: " + e.getMessage()
            );
        }
    }
}

//PROBLEMA 5
import java.util.Arrays;
import java.util.Collection;

public class MaximoMinimo {

    /**
     * Clase sencilla para almacenar el valor mínimo y máximo.
     */
    static class Resultado {
        private final int minimo;
        private final int maximo;

        public Resultado(int minimo, int maximo) {
            this.minimo = minimo;
            this.maximo = maximo;
        }

        public int getMinimo() {
            return minimo;
        }

        public int getMaximo() {
            return maximo;
        }

        @Override
        public String toString() {
            return "Mínimo: " + minimo + ", Máximo: " + maximo;
        }
    }

    /**
     * Recorre manualmente una colección de enteros y obtiene
     * sus valores mínimo y máximo.
     *
     * @param numeros colección que se desea analizar
     * @return resultado que contiene el mínimo y el máximo
     * @throws IllegalArgumentException si la colección es nula,
     *                                  está vacía o contiene valores nulos
     */
    public static Resultado encontrarMaximoMinimo(Collection<Integer> numeros) {
        // Validamos que la colección exista.
        if (numeros == null) {
            throw new IllegalArgumentException("La colección no puede ser nula.");
        }

        // Una colección vacía no tiene máximo ni mínimo.
        if (numeros.isEmpty()) {
            throw new IllegalArgumentException("La colección no puede estar vacía.");
        }

        Integer minimo = null;
        Integer maximo = null;

        // Recorremos manualmente todos los elementos.
        for (Integer numero : numeros) {
            // Evitamos errores al intentar comparar un Integer nulo.
            if (numero == null) {
                throw new IllegalArgumentException(
                    "La colección no puede contener valores nulos."
                );
            }

            // El primer elemento válido inicializa mínimo y máximo.
            if (minimo == null) {
                minimo = numero;
                maximo = numero;
            } else {
                if (numero < minimo) {
                    minimo = numero;
                }

                if (numero > maximo) {
                    maximo = numero;
                }
            }
        }

        return new Resultado(minimo, maximo);
    }

    public static void main(String[] args) {
        // Caso de prueba funcional.
        Collection<Integer> numeros =
            Arrays.asList(12, -5, 8, 34, 0, 19, -11, 7);

        try {
            Resultado resultado = encontrarMaximoMinimo(numeros);

            System.out.println("Colección: " + numeros);
            System.out.println(resultado);

            // Comprobación sencilla del resultado esperado.
            if (resultado.getMinimo() == -11
                    && resultado.getMaximo() == 34) {
                System.out.println("Prueba superada correctamente.");
            } else {
                System.out.println("La prueba ha fallado.");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}



//PROBLEMA 6
import java.math.BigInteger;
import java.util.Scanner;

public class Factorial {

    /**
     * Calcula el factorial de forma iterativa.
     *
     * @param n entero no negativo
     * @return n!
     * @throws IllegalArgumentException si n es negativo
     */
    public static BigInteger factorialIterativo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                "El factorial no está definido para números negativos."
            );
        }

        BigInteger resultado = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }

        return resultado;
    }

    /**
     * Calcula el factorial de forma recursiva.
     *
     * @param n entero no negativo
     * @return n!
     * @throws IllegalArgumentException si n es negativo
     */
    public static BigInteger factorialRecursivo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                "El factorial no está definido para números negativos."
            );
        }

        // Caso base: 0! = 1 y 1! = 1.
        if (n <= 1) {
            return BigInteger.ONE;
        }

        // Caso recursivo: n! = n * (n - 1)!
        return BigInteger.valueOf(n)
                .multiply(factorialRecursivo(n - 1));
    }

    public static void main(String[] args) {

        // Caso de prueba funcional.
        int prueba = 5;

        System.out.println("=== Caso de prueba ===");
        System.out.println("Número: " + prueba);
        System.out.println(
            "Factorial iterativo: " + factorialIterativo(prueba)
        );
        System.out.println(
            "Factorial recursivo: " + factorialRecursivo(prueba)
        );
        System.out.println("Resultado esperado: 120");

        // Permite probar también un valor introducido por el usuario.
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\n=== Prueba con entrada del usuario ===");
            System.out.print("Introduce un entero no negativo: ");

            int numero = scanner.nextInt();

            // Ejecutamos ambos métodos.
            BigInteger resultadoIterativo = factorialIterativo(numero);
            BigInteger resultadoRecursivo = factorialRecursivo(numero);

            System.out.println(
                numero + "! (iterativo) = " + resultadoIterativo
            );
            System.out.println(
                numero + "! (recursivo) = " + resultadoRecursivo
            );

            // Comprobamos que ambos métodos producen el mismo resultado.
            if (resultadoIterativo.equals(resultadoRecursivo)) {
                System.out.println(
                    "Correcto: ambos métodos producen el mismo resultado."
                );
            } else {
                System.out.println(
                    "Error: los métodos produjeron resultados diferentes."
                );
            }

        } catch (java.util.InputMismatchException e) {
            // Se produce si el usuario no introduce un entero válido.
            System.err.println(
                "Error: debes introducir un número entero válido."
            );

        } catch (IllegalArgumentException e) {
            // Se produce si se intenta calcular el factorial de un negativo.
            System.err.println("Error: " + e.getMessage());

        } catch (StackOverflowError e) {
            /*
             * Un valor demasiado grande puede superar el límite de la pila
             * debido al número de llamadas realizadas por la recursión.
             */
            System.err.println(
                "Error: el número es demasiado grande para calcular "
                + "el factorial mediante recursión."
            );
        }
    }
}

//PROBLEMA 7
import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculadoraBasica {

    /**
     * Realiza una operación matemática entre dos números.
     *
     * @param num1 Primer operando.
     * @param num2 Segundo operando.
     * @param operador Operación a realizar: +, -, * o /.
     * @return Resultado de la operación.
     * @throws ArithmeticException si se intenta dividir entre cero.
     * @throws IllegalArgumentException si el operador no es válido.
     */
    public static double calcular(double num1, double num2, char operador) {
        switch (operador) {
            case '+':
                return num1 + num2;

            case '-':
                return num1 - num2;

            case '*':
                return num1 * num2;

            case '/':
                // Control explícito y obligatorio de división por cero.
                if (num2 == 0) {
                    throw new ArithmeticException("No se puede dividir entre cero.");
                }
                return num1 / num2;

            default:
                // Se lanza una excepción si la operación no existe.
                throw new IllegalArgumentException(
                        "Operador no válido. Use +, -, * o /."
                );
        }
    }

    public static void main(String[] args) {
        // try-with-resources garantiza el cierre correcto del Scanner.
        try (Scanner scanner = new Scanner(System.in)) {

            try {
                System.out.println("=== CALCULADORA BÁSICA ===");

                // Lectura del primer número.
                System.out.print("Introduce el primer número: ");
                double num1 = scanner.nextDouble();

                // Lectura del operador.
                System.out.print("Introduce la operación (+, -, *, /): ");
                char operador = scanner.next().charAt(0);

                // Lectura del segundo número.
                System.out.print("Introduce el segundo número: ");
                double num2 = scanner.nextDouble();

                // Se realiza el cálculo.
                double resultado = calcular(num1, num2, operador);

                System.out.println("Resultado: " + resultado);

            } catch (InputMismatchException e) {
                // Se produce cuando el usuario no introduce un número válido.
                System.out.println(
                        "Error: debes introducir valores numéricos válidos."
                );

            } catch (ArithmeticException e) {
                // Control de errores aritméticos, especialmente división por cero.
                System.out.println("Error aritmético: " + e.getMessage());

            } catch (IllegalArgumentException e) {
                // Control de operadores no permitidos.
                System.out.println("Error: " + e.getMessage());

            } catch (Exception e) {
                // Control de cualquier otro error inesperado.
                System.out.println(
                        "Se produjo un error inesperado: " + e.getMessage()
                );
            }
        }
    }
}
//Un caso de prueba funcional sería:
//=== CALCULADORA BÁSICA ===
//Introduce el primer número: 20
//Introduce la operación (+, -, *, /): /
//Introduce el segundo número: 4
//Resultado: 5.0


//PROBLEMA 8
import java.util.HashMap;
import java.util.Map;

// Excepción personalizada que se lanza cuando se intenta prestar
// un libro que ya está prestado.
class LibroYaPrestadoException extends Exception {

    public LibroYaPrestadoException(String mensaje) {
        super(mensaje);
    }
}

// Representa un libro de la biblioteca.
class Libro {

    private final String isbn;
    private final String titulo;
    private final String autor;
    private boolean prestado;

    public Libro(String isbn, String titulo, String autor) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede estar vacío.");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede estar vacío.");
        }

        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean estaPrestado() {
        return prestado;
    }

    // Marca el libro como prestado.
    public void prestar() throws LibroYaPrestadoException {
        if (prestado) {
            throw new LibroYaPrestadoException(
                "El libro \"" + titulo + "\" ya está prestado."
            );
        }

        prestado = true;
    }

    // Devuelve el libro.
    public void devolver() {
        if (!prestado) {
            throw new IllegalStateException(
                "El libro \"" + titulo + "\" no estaba prestado."
            );
        }

        prestado = false;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", estado=" + (prestado ? "prestado" : "disponible") +
                '}';
    }
}

// Gestiona el conjunto de libros.
class Biblioteca {

    // Se utiliza el ISBN como identificador único de cada libro.
    private final Map<String, Libro> libros = new HashMap<>();

    // Añade un nuevo libro a la biblioteca.
    public void agregarLibro(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser null.");
        }

        if (libros.containsKey(libro.getIsbn())) {
            throw new IllegalArgumentException(
                "Ya existe un libro con ISBN " + libro.getIsbn()
            );
        }

        libros.put(libro.getIsbn(), libro);
    }

    // Busca un libro y genera una excepción si no existe.
    private Libro obtenerLibro(String isbn) {
        Libro libro = libros.get(isbn);

        if (libro == null) {
            throw new IllegalArgumentException(
                "No existe ningún libro con ISBN " + isbn
            );
        }

        return libro;
    }

    // Presta un libro identificado por su ISBN.
    public void prestarLibro(String isbn) throws LibroYaPrestadoException {
        Libro libro = obtenerLibro(isbn);
        libro.prestar();
    }

    // Devuelve un libro identificado por su ISBN.
    public void devolverLibro(String isbn) {
        Libro libro = obtenerLibro(isbn);
        libro.devolver();
    }

    // Muestra todos los libros y su estado.
    public void mostrarLibros() {
        if (libros.isEmpty()) {
            System.out.println("La biblioteca está vacía.");
            return;
        }

        for (Libro libro : libros.values()) {
            System.out.println(libro);
        }
    }
}

// Clase con un caso de prueba funcional.
public class Main {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        try {
            // Creamos dos libros.
            Libro libro1 = new Libro(
                "978-84-376-0494-7",
                "Don Quijote de la Mancha",
                "Miguel de Cervantes"
            );

            Libro libro2 = new Libro(
                "978-84-663-4612-2",
                "Cien años de soledad",
                "Gabriel García Márquez"
            );

            // Añadimos los libros.
            biblioteca.agregarLibro(libro1);
            biblioteca.agregarLibro(libro2);

            System.out.println("=== Estado inicial ===");
            biblioteca.mostrarLibros();

            // Préstamo correcto.
            System.out.println("\n=== Prestando Don Quijote ===");
            biblioteca.prestarLibro("978-84-376-0494-7");
            biblioteca.mostrarLibros();

            // Intentamos prestar otra vez el mismo libro.
            // Debe producir LibroYaPrestadoException.
            System.out.println("\n=== Intento de segundo préstamo ===");

            try {
                biblioteca.prestarLibro("978-84-376-0494-7");
            } catch (LibroYaPrestadoException e) {
                System.out.println("Error controlado: " + e.getMessage());
            }

            // Devolvemos el libro.
            System.out.println("\n=== Devolución ===");
            biblioteca.devolverLibro("978-84-376-0494-7");
            biblioteca.mostrarLibros();

            // Comprobamos también el intento de utilizar un ISBN inexistente.
            System.out.println("\n=== ISBN inexistente ===");

            try {
                biblioteca.prestarLibro("ISBN-INEXISTENTE");
            } catch (LibroYaPrestadoException | IllegalArgumentException e) {
                System.out.println("Error controlado: " + e.getMessage());
            }

        } catch (IllegalArgumentException | IllegalStateException e) {
            // Errores derivados de datos inválidos o estados incorrectos.
            System.err.println("Error: " + e.getMessage());

        } catch (LibroYaPrestadoException e) {
            // Excepción específica del préstamo.
            System.err.println("Error de préstamo: " + e.getMessage());
        }
    }
}

