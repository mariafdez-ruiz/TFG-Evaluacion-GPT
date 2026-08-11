//JAVA V 4.1 PROMPT BASICO
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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContadorCaracteres {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar la cadena al usuario
        System.out.print("Introduce una cadena de texto: ");
        String texto = scanner.nextLine();

        // Crear un mapa para contar los caracteres
        Map<Character, Integer> contador = new HashMap<>();

        // Recorrer la cadena y contar los caracteres
        for (char c : texto.toCharArray()) {
            if (contador.containsKey(c)) {
                contador.put(c, contador.get(c) + 1);
            } else {
                contador.put(c, 1);
            }
        }

        // Mostrar el resultado
        System.out.println("\nFrecuencia de cada carácter:");
        for (Map.Entry<Character, Integer> entry : contador.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        }

        scanner.close();
    }
}

//PROBLEMA 3
import java.util.Scanner;

public class PalabraMayorLongitud {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce una frase:");
        String frase = scanner.nextLine();

        // Eliminamos los signos de puntuación usando una expresión regular
        frase = frase.replaceAll("[.,:;?!¡¿\"'()\\[\\]{}]", "");

        // Dividimos la frase en palabras usando espacios en blanco como delimitador
        String[] palabras = frase.split("\\s+");

        String palabraMayor = "";
        for (String palabra : palabras) {
            if (palabra.length() > palabraMayor.length()) {
                palabraMayor = palabra;
            }
        }

        System.out.println("La palabra de mayor longitud es: " + palabraMayor);
    }
}

//PROBLEMA 4 
import java.util.Scanner;

public class NumeroPrimo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un número entero: ");
        int numero = scanner.nextInt();

        if (esPrimo(numero)) {
            System.out.println(numero + " es un número primo.");
        } else {
            System.out.println(numero + " no es un número primo.");
        }
    }

    // Método para determinar si un número es primo
    public static boolean esPrimo(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}


//PROBLEMA 5
public class MaxMinFinder {

    public static void main(String[] args) {
        int[] numeros = {5, 7, 2, 9, -3, 15, 0};

        int[] resultado = encontrarMaximoMinimo(numeros);
        if (resultado != null) {
            System.out.println("Máximo: " + resultado[0]);
            System.out.println("Mínimo: " + resultado[1]);
        } else {
            System.out.println("El array es nulo o está vacío.");
        }
    }

    /**
     * Devuelve un array con dos elementos: [máximo, mínimo]
     * o null si el array es nulo o vacío.
     */
    public static int[] encontrarMaximoMinimo(int[] array) {
        if (array == null || array.length == 0) {
            return null; // Manejo seguro de null o vacío
        }

        int max = array[0];
        int min = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        return new int[]{max, min};
    }
}

//PROBLEMA 6
import java.util.Scanner;

public class FactorialDemo {

    // Método iterativo
    public static long factorialIterativo(int n) {
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // Método recursivo
    public static long factorialRecursivo(int n) {
        if (n == 0 || n == 1)
            return 1;
        else
            return n * factorialRecursivo(n - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número entero positivo: ");
        int num = scanner.nextInt();

        if (num < 0) {
            System.out.println("¡El número debe ser positivo!");
        } else {
            long factIterativo = factorialIterativo(num);
            long factRecursivo = factorialRecursivo(num);

            System.out.println("Factorial de " + num + " (iterativo): " + factIterativo);
            System.out.println("Factorial de " + num + " (recursivo): " + factRecursivo);
            
            if (factIterativo == factRecursivo) {
                System.out.println("¡Ambos métodos obtienen el mismo resultado!");
            } else {
                System.out.println("Los resultados son diferentes. ¡Revisar las implementaciones!");
            }
        }

        scanner.close();
    }
}


//PROBLEMA 7
import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculadoraBasica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double num1 = 0, num2 = 0, resultado = 0;
        char operacion;
        boolean continuar = true;

        while (continuar) {
            try {
                // Solicitar el primer número
                System.out.print("Ingrese el primer número: ");
                num1 = scanner.nextDouble();

                // Solicitar la operación
                System.out.print("Ingrese la operación (+, -, *, /): ");
                operacion = scanner.next().charAt(0);

                // Solicitar el segundo número
                System.out.print("Ingrese el segundo número: ");
                num2 = scanner.nextDouble();

                // Realizar la operación correspondiente
                switch (operacion) {
                    case '+':
                        resultado = num1 + num2;
                        System.out.println("Resultado: " + resultado);
                        break;
                    case '-':
                        resultado = num1 - num2;
                        System.out.println("Resultado: " + resultado);
                        break;
                    case '*':
                        resultado = num1 * num2;
                        System.out.println("Resultado: " + resultado);
                        break;
                    case '/':
                        if (num2 == 0) {
                            throw new ArithmeticException("Error: División por cero no permitida.");
                        }
                        resultado = num1 / num2;
                        System.out.println("Resultado: " + resultado);
                        break;
                    default:
                        System.out.println("Operación no válida.");
                }

            } catch (InputMismatchException ime) {
                System.out.println("Error: Entrada inválida. Por favor ingrese números válidos.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            } catch (ArithmeticException ae) {
                System.out.println(ae.getMessage());
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
            }

            // Preguntar si se desea continuar
            System.out.print("¿Desea realizar otra operación? (s/n): ");
            String respuesta = scanner.next();
            if (!respuesta.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }
        System.out.println("¡Gracias por usar la calculadora!");
        scanner.close();
    }
}

//PROBLEMA 8

public class Libro {
    private String titulo;
    private String autor;
    private boolean disponible;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void prestar() throws Exception {
        if (!disponible) {
            throw new Exception("El libro ya está prestado: " + titulo);
        }
        disponible = false;
    }

    public void devolver() {
        disponible = true;
    }

    @Override
    public String toString() {
        return String.format("Título: %s, Autor: %s, %s", 
              titulo, autor, (disponible ? "Disponible" : "Prestado"));
    }
}
________________________________________
//Clase Biblioteca

import java.util.*;

public class Biblioteca {
    private List<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public void prestarLibro(String titulo) throws Exception {
        Libro libro = buscarLibroPorTitulo(titulo);
        if (libro == null) {
            throw new Exception("Libro no encontrado: " + titulo);
        }
        libro.prestar();
    }

    public void devolverLibro(String titulo) throws Exception {
        Libro libro = buscarLibroPorTitulo(titulo);
        if (libro == null) {
            throw new Exception("Libro no encontrado: " + titulo);
        }
        libro.devolver();
    }

    public void mostrarCatalogo() {
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }
}

//Clase Main (para probar el sistema)

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.agregarLibro(new Libro("Cien años de soledad", "Gabriel García Márquez"));
        biblioteca.agregarLibro(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes"));
        biblioteca.agregarLibro(new Libro("El Principito", "Antoine de Saint-Exupéry"));

        System.out.println("Catálogo inicial:");
        biblioteca.mostrarCatalogo();

        try {
            biblioteca.prestarLibro("Cien años de soledad");
            System.out.println("\n'El préstamo fue exitoso.'");

            // Intentamos prestar el mismo libro otra vez
            biblioteca.prestarLibro("Cien años de soledad");
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        }

        System.out.println("\nCatálogo después del préstamo:");
        biblioteca.mostrarCatalogo();

        try {
            biblioteca.devolverLibro("Cien años de soledad");
            System.out.println("\n'El libro fue devuelto.'");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nCatálogo final:");
        biblioteca.mostrarCatalogo();
    }
}
//Clase Libro

public class Libro {
    private String titulo;
    private String autor;
    private boolean disponible;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void prestar() throws Exception {
        if (!disponible) {
            throw new Exception("El libro ya está prestado: " + titulo);
        }
        disponible = false;
    }

    public void devolver() {
        disponible = true;
    }

    @Override
    public String toString() {
        return String.format("Título: %s, Autor: %s, %s", 
              titulo, autor, (disponible ? "Disponible" : "Prestado"));
    }
}

//Clase Biblioteca

import java.util.*;

public class Biblioteca {
    private List<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public void prestarLibro(String titulo) throws Exception {
        Libro libro = buscarLibroPorTitulo(titulo);
        if (libro == null) {
            throw new Exception("Libro no encontrado: " + titulo);
        }
        libro.prestar();
    }

    public void devolverLibro(String titulo) throws Exception {
        Libro libro = buscarLibroPorTitulo(titulo);
        if (libro == null) {
            throw new Exception("Libro no encontrado: " + titulo);
        }
        libro.devolver();
    }

    public void mostrarCatalogo() {
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }
}

//Clase Main (para probar el sistema)

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.agregarLibro(new Libro("Cien años de soledad", "Gabriel García Márquez"));
        biblioteca.agregarLibro(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes"));
        biblioteca.agregarLibro(new Libro("El Principito", "Antoine de Saint-Exupéry"));

        System.out.println("Catálogo inicial:");
        biblioteca.mostrarCatalogo();

        try {
            biblioteca.prestarLibro("Cien años de soledad");
            System.out.println("\n'El préstamo fue exitoso.'");

            // Intentamos prestar el mismo libro otra vez
            biblioteca.prestarLibro("Cien años de soledad");
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        }

        System.out.println("\nCatálogo después del préstamo:");
        biblioteca.mostrarCatalogo();

        try {
            biblioteca.devolverLibro("Cien años de soledad");
            System.out.println("\n'El libro fue devuelto.'");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nCatálogo final:");
        biblioteca.mostrarCatalogo();
    }
}
