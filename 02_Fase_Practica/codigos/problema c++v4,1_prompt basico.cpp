// v 4.1 prompt básico
//problema 1
#include <iostream>
#include <string>
#include <algorithm>
#include <cctype>

using namespace std;

// Función para verificar si una cadena es palíndromo
bool esPalindromo(string cadena) {
    // Quitar espacios y convertir a minúsculas
    string limpia;
    for (char c : cadena) {
        if (!isspace(c))
            limpia += tolower(c);
    }
    // Comprobar si es igual al reverso
    string reverso = limpia;
    reverse(reverso.begin(), reverso.end());
    return limpia == reverso;
}

int main() {
    string texto;
    cout << "Introduce una cadena: ";
    getline(cin, texto);

    if (esPalindromo(texto))
        cout << "Es un palíndromo." << endl;
    else
        cout << "No es un palíndromo." << endl;

    return 0;
}

//problema 2
#include <iostream>
#include <map>
#include <string>

int main() {
    std::string texto;
    std::cout << "Ingresa una cadena de texto: ";
    std::getline(std::cin, texto);

    std::map<char, int> contador;

    // Contar cada carácter
    for (char c : texto) {
        contador[c]++;
    }

    std::cout << "Frecuencia de cada carácter:" << std::endl;
    for (const auto &par : contador) {
        std::cout << "'" << par.first << "' aparece " << par.second << " veces." << std::endl;
    }

    return 0;
}

//problema 3
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

//problema 4
#include <iostream>
using namespace std;

int main() {
    int numero;
    bool es_primo = true;

    cout << "Introduce un número entero: ";
    cin >> numero;

    if (numero <= 1) {
        es_primo = false;
    } else {
        for (int i = 2; i * i <= numero; ++i) {
            if (numero % i == 0) {
                es_primo = false;
                break;
            }
        }
    }

    if (es_primo)
        cout << numero << " es un número primo." << endl;
    else
        cout << numero << " no es un número primo." << endl;

    return 0;
}

//problema 5
#include <iostream>
#include <vector>
#include <limits>

bool encontrarMaxMin(const std::vector<int>& numeros, int& maximo, int& minimo) {
    if (numeros.empty()) {
        // Vector vacío, retornamos falso
        return false;
    }

    // Inicializamos con el primer valor del vector
    maximo = numeros[0];
    minimo = numeros[0];

    // Recorremos manualmente desde el segundo elemento
    for (size_t i = 1; i < numeros.size(); ++i) {
        if (numeros[i] > maximo) {
            maximo = numeros[i];
        }
        if (numeros[i] < minimo) {
            minimo = numeros[i];
        }
    }
    return true;
}

int main() {
    // Ejemplo de uso
    std::vector<int> numeros = {5, 8, -3, 14, 2, 7};

    // Variables para el resultado
    int maximo = 0, minimo = 0;

    if (encontrarMaxMin(numeros, maximo, minimo)) {
        std::cout << "Máximo: " << maximo << std::endl;
        std::cout << "Mínimo: " << minimo << std::endl;
    } else {
        std::cout << "La lista de números está vacía." << std::endl;
    }

    // Prueba lista vacía
    std::vector<int> vacia;
    if (encontrarMaxMin(vacia, maximo, minimo)) {
        std::cout << "Máximo: " << maximo << std::endl;
        std::cout << "Mínimo: " << minimo << std::endl;
    } else {
        std::cout << "La lista de números está vacía." << std::endl;
    }

    return 0;
}

//problema 6
#include <iostream>
using namespace std;

class Factorial {
public:
    // Método iterativo
    static unsigned long long factorialIterativo(int n) {
        unsigned long long resultado = 1;
        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // Método recursivo
    static unsigned long long factorialRecursivo(int n) {
        if (n <= 1) return 1;
        else return n * factorialRecursivo(n - 1);
    }
};

int main() {
    int numero;

    cout << "Ingrese un número entero positivo: ";
    cin >> numero;

    if (numero < 0) {
        cout << "Error: El número debe ser positivo." << endl;
        return 1;
    }

    unsigned long long iterativo = Factorial::factorialIterativo(numero);
    unsigned long long recursivo = Factorial::factorialRecursivo(numero);

    cout << "Factorial de " << numero << " (Iterativo): " << iterativo << endl;
    cout << "Factorial de " << numero << " (Recursivo): " << recursivo << endl;

    if (iterativo == recursivo) {
        cout << "Ambos métodos producen el mismo resultado." << endl;
    } else {
        cout << "¡Atención! Los resultados difieren." << endl;
    }

    return 0;
}

//problema 7
#include <iostream>
#include <stdexcept> // Para std::invalid_argument y std::runtime_error

using namespace std;

// Función para dividir con control de excepción
double dividir(double a, double b) {
    if (b == 0) {
        throw invalid_argument("Error: No se puede dividir entre cero.");
    }
    return a / b;
}

int main() {
    double num1, num2, resultado;
    char operacion;

    cout << "--- Calculadora Básica ---\n";
    cout << "Ingrese primer número: ";
    cin >> num1;
    cout << "Ingrese operación (+, -, *, /): ";
    cin >> operacion;
    cout << "Ingrese segundo número: ";
    cin >> num2;

    try {
        switch(operacion) {
            case '+':
                resultado = num1 + num2;
                break;
            case '-':
                resultado = num1 - num2;
                break;
            case '*':
                resultado = num1 * num2;
                break;
            case '/':
                resultado = dividir(num1, num2); // Aquí puede lanzar excepción
                break;
            default:
                throw invalid_argument("Operación inválida.");
        }
        cout << "Resultado: " << resultado << endl;
    } catch(const invalid_argument& e) {
        cerr << e.what() << endl;
    } catch(const exception& e) {
        cerr << "Se produjo un error: " << e.what() << endl;
    }

    return 0;
}

//problema 8
#include <iostream>
#include <vector>
#include <string>
#include <stdexcept>

// Clase Libro
class Libro {
private:
    std::string titulo;
    std::string autor;
    bool disponible;

public:
    Libro(std::string titulo, std::string autor)
        : titulo(titulo), autor(autor), disponible(true) {}

    std::string getTitulo() const { return titulo; }
    std::string getAutor() const { return autor; }
    bool estaDisponible() const { return disponible; }

    void prestar() {
        if (!disponible) {
            throw std::runtime_error("El libro ya esta prestado");
        }
        disponible = false;
    }

    void devolver() {
        disponible = true;
    }

    void mostrar() const {
        std::cout << "Titulo: " << titulo 
                  << ", Autor: " << autor 
                  << ", Estado: " << (disponible ? "Disponible" : "Prestado") 
                  << std::endl;
    }
};

// Clase Biblioteca
class Biblioteca {
private:
    std::vector<Libro> libros;

public:
    void añadirLibro(const Libro& libro) {
        libros.push_back(libro);
    }

    void mostrarLibros() const {
        for (const auto& libro : libros) {
            libro.mostrar();
        }
    }

    // Buscar libro por titulo (puedes mejorarlo para buscar por autor, etc)
    int buscarLibro(const std::string& titulo) {
        for (size_t i = 0; i < libros.size(); ++i) {
            if (libros[i].getTitulo() == titulo)
                return i;
        }
        return -1;
    }

    void prestarLibro(const std::string& titulo) {
        int idx = buscarLibro(titulo);
        if (idx == -1)
            throw std::runtime_error("Libro no encontrado");
        libros[idx].prestar();
        std::cout << "Libro prestado con exito: " << titulo << std::endl;
    }

    void devolverLibro(const std::string& titulo) {
        int idx = buscarLibro(titulo);
        if (idx == -1)
            throw std::runtime_error("Libro no encontrado");
        libros[idx].devolver();
        std::cout << "Libro devuelto con exito: " << titulo << std::endl;
    }
};

// Programa principal
int main() {
    Biblioteca biblio;
    biblio.añadirLibro(Libro("El Quijote", "Miguel de Cervantes"));
    biblio.añadirLibro(Libro("Cien años de soledad", "Gabriel Garcia Marquez"));
    
    biblio.mostrarLibros();
    try {
        biblio.prestarLibro("El Quijote");
        biblio.prestarLibro("El Quijote"); // Esto lanza excepción
    } catch (const std::exception& e) {
        std::cout << "Error: " << e.what() << std::endl;
    }

    biblio.devolverLibro("El Quijote");
    biblio.mostrarLibros();

    return 0;
}
