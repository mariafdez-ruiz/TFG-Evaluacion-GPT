//JAVA V 5.5 PROMPT BASICO
//PROBLEMA1

import java.util.Scanner;

public class Palindromo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce una cadena de texto: ");
        String texto = sc.nextLine();

        // Ignoramos espacios y diferencias entre mayúsculas y minúsculas
        texto = texto.replace(" ", "").toLowerCase();

        String invertida = "";

        for (int i = texto.length() - 1; i >= 0; i--) {
            invertida += texto.charAt(i);
        }

        if (texto.equals(invertida)) {
            System.out.println("La cadena es un palíndromo.");
        } else {
            System.out.println("La cadena no es un palíndromo.");
        }

        sc.close();
    }
}

//PROBLEMA2
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContarCaracteres {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce una cadena de texto: ");
        String texto = scanner.nextLine();

        Map<Character, Integer> contador = new HashMap<>();

        for (char caracter : texto.toCharArray()) {
            contador.put(caracter, contador.getOrDefault(caracter, 0) + 1);
        }

        System.out.println("Frecuencia de caracteres:");
        for (Map.Entry<Character, Integer> entrada : contador.entrySet()) {
            System.out.println("'" + entrada.getKey() + "': " + entrada.getValue());
        }

        scanner.close();
    }
}

//PROBLEMA3
import java.util.Scanner;

public class PalabraMasLarga {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce una frase: ");
        String frase = scanner.nextLine();

        // Reemplazar los signos de puntuación por espacios
        frase = frase.replaceAll("[\\p{Punct}¿¡]+", " ");

        // Separar la frase en palabras
        String[] palabras = frase.trim().split("\\s+");

        String palabraMasLarga = "";

        for (String palabra : palabras) {
            if (palabra.length() > palabraMasLarga.length()) {
                palabraMasLarga = palabra;
            }
        }

        if (!palabraMasLarga.isEmpty()) {
            System.out.println("La palabra más larga es: " + palabraMasLarga);
            System.out.println("Tiene " + palabraMasLarga.length() + " caracteres.");
        } else {
            System.out.println("No se encontraron palabras.");
        }

        scanner.close();
    }
}

//PROBLEMA 4 
import java.util.Scanner;

public class NumeroPrimo {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce un número entero: ");
        int numero = teclado.nextInt();

        boolean esPrimo = true;

        if (numero <= 1) {
            esPrimo = false;
        } else {
            for (int i = 2; i <= Math.sqrt(numero); i++) {
                if (numero % i == 0) {
                    esPrimo = false;
                    break;
                }
            }
        }

        if (esPrimo) {
            System.out.println(numero + " es un número primo.");
        } else {
            System.out.println(numero + " no es un número primo.");
        }

        teclado.close();
    }
}

//PROBLEMA 5
public class MaximoMinimo {

    public static void encontrarMaxMin(int[] numeros) {
        if (numeros == null || numeros.length == 0) {
            System.out.println("No se puede calcular: el array es nulo o está vacío.");
            return;
        }

        // Inicializamos ambos con el primer elemento
        int maximo = numeros[0];
        int minimo = numeros[0];

        // Recorremos manualmente desde el segundo elemento
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] > maximo) {
                maximo = numeros[i];
            }

            if (numeros[i] < minimo) {
                minimo = numeros[i];
            }
        }

        System.out.println("Valor máximo: " + maximo);
        System.out.println("Valor mínimo: " + minimo);
    }

    public static void main(String[] args) {
        int[] numeros = {7, -3, 15, 2, 9, -8, 4};

        encontrarMaxMin(numeros);
    }
}

//PROBLEMA 6
import java.util.Scanner;

public class Factorial {

    // Método iterativo
    public static long factorialIterativo(int n) {
        long resultado = 1;

        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }

        return resultado;
    }

    // Método recursivo
    public static long factorialRecursivo(int n) {
        // Caso base
        if (n == 0 || n == 1) {
            return 1;
        }

        // Llamada recursiva
        return n * factorialRecursivo(n - 1);
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce un número entero positivo: ");
        int numero = teclado.nextInt();

        if (numero < 0) {
            System.out.println("El factorial no está definido para números negativos.");
        } else {
            long resultadoIterativo = factorialIterativo(numero);
            long resultadoRecursivo = factorialRecursivo(numero);

            System.out.println("Factorial mediante método iterativo: "
                    + resultadoIterativo);

            System.out.println("Factorial mediante método recursivo: "
                    + resultadoRecursivo);

            if (resultadoIterativo == resultadoRecursivo) {
                System.out.println("Ambos métodos producen el mismo resultado.");
            }
        }

        teclado.close();
    }
}

//PROBLEMA 7
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculadora {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            // Entrada de datos
            System.out.print("Ingrese el primer número: ");
            double numero1 = scanner.nextDouble();

            System.out.print("Ingrese el segundo número: ");
            double numero2 = scanner.nextDouble();

            System.out.println("\nOperaciones disponibles:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. División");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            double resultado;

            // Procesamiento de la operación
            switch (opcion) {
                case 1:
                    resultado = numero1 + numero2;
                    System.out.println("Resultado: " + resultado);
                    break;

                case 2:
                    resultado = numero1 - numero2;
                    System.out.println("Resultado: " + resultado);
                    break;

                case 3:
                    resultado = numero1 * numero2;
                    System.out.println("Resultado: " + resultado);
                    break;

                case 4:
                    if (numero2 == 0) {
                        throw new ArithmeticException(
                            "No se puede realizar una división entre cero."
                        );
                    }

                    resultado = numero1 / numero2;
                    System.out.println("Resultado: " + resultado);
                    break;

                default:
                    throw new IllegalArgumentException(
                        "La opción seleccionada no es válida."
                    );
            }

        } catch (InputMismatchException e) {
            System.out.println(
                "Error: debe ingresar valores numéricos válidos."
            );

        } catch (ArithmeticException e) {
            System.out.println("Error aritmético: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (Exception e) {
            // Captura cualquier otra excepción inesperada
            System.out.println(
                "Se produjo un error inesperado: " + e.getMessage()
            );

        } finally {
            scanner.close();
            System.out.println("Calculadora finalizada.");
        }
    }
}
//PROBLEMA 8
// Libro.java
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

    public boolean isDisponible() {
        return disponible;
    }

    public void prestar() throws LibroNoDisponibleException {
        if (!disponible) {
            throw new LibroNoDisponibleException(
                "El libro \"" + titulo + "\" ya está prestado."
            );
        }

        disponible = false;
    }

    public void devolver() {
        disponible = true;
    }

    @Override
    public String toString() {
        return titulo + " - " + autor
                + " (" + (disponible ? "Disponible" : "Prestado") + ")";
    }
}

// LibroNoDisponibleException.java
public class LibroNoDisponibleException extends Exception {

    public LibroNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
//La clase Biblioteca mantiene una colección de libros y permite añadirlos, prestarlos y devolverlos:

// Biblioteca.java
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public Libro buscarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public void prestarLibro(String titulo)
            throws LibroNoDisponibleException {

        Libro libro = buscarLibro(titulo);

        if (libro == null) {
            System.out.println("El libro no existe en la biblioteca.");
            return;
        }

        libro.prestar();
        System.out.println("Libro prestado: " + libro.getTitulo());
    }

    public void devolverLibro(String titulo) {
        Libro libro = buscarLibro(titulo);

        if (libro == null) {
            System.out.println("El libro no existe en la biblioteca.");
            return;
        }

        libro.devolver();
        System.out.println("Libro devuelto: " + libro.getTitulo());
    }

    public void mostrarLibros() {
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.agregarLibro(
            new Libro("Don Quijote de la Mancha", "Miguel de Cervantes")
        );

        biblioteca.agregarLibro(
            new Libro("Cien años de soledad", "Gabriel García Márquez")
        );

        biblioteca.mostrarLibros();

        try {
            // Primer préstamo: correcto
            biblioteca.prestarLibro("Don Quijote de la Mancha");

            // Segundo préstamo del mismo libro: genera la excepción
            biblioteca.prestarLibro("Don Quijote de la Mancha");

        } catch (LibroNoDisponibleException e) {
            System.out.println("Error: " + e.getMessage());
        }

        biblioteca.devolverLibro("Don Quijote de la Mancha");

        System.out.println("\nEstado final:");
        biblioteca.mostrarLibros();
    }
}
 