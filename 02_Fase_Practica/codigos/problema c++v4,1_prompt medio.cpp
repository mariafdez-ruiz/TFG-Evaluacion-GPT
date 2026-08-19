// v 4.1 prompt medio
//problema 1
#include <iostream>
#include <string>
#include <cctype> // Para std::isalnum y std::tolower
#include <stdexcept> // Para std::invalid_argument

// Función para comprobar si una cadena es un palíndromo
bool esPalindromo(const std::string& texto) {
    if (texto.empty()) {
        throw std::invalid_argument("La cadena no puede estar vacía.");
    }

    std::string cadenaLimpia;

    // Eliminamos espacios, puntuación y convertimos todo a minúsculas
    for (char c : texto) {
        if (std::isalnum(static_cast<unsigned char>(c))) { // Solo caracteres alfanuméricos
            cadenaLimpia += std::tolower(static_cast<unsigned char>(c));
        }
    }

    // Comprobación de palíndromo
    size_t inicio = 0;
    size_t fin = cadenaLimpia.length() - 1;

    while (inicio < fin) {
        if (cadenaLimpia[inicio] != cadenaLimpia[fin]) {
            return false;
        }
        ++inicio;
        --fin;
    }

    return true;
}

int main() {
    try {
        std::string texto = "¿Acaso hubo búhos acá?";
        // Prueba con una cadena funcional

        if (esPalindromo(texto)) {
            std::cout << "La cadena ES un palíndromo." << std::endl;
        } else {
            std::cout << "La cadena NO es un palíndromo." << std::endl;
        }
    } catch (const std::invalid_argument& ex) {
        std::cerr << "Error: " << ex.what() << std::endl;
        return 1; // Código de error
    } catch (...) {
        std::cerr << "Error desconocido." << std::endl;
        return 2; // Código de error desconocido
    }

    return 0; // Éxito
}

//problema 2
#include <iostream>
#include <map>
#include <string>
#include <limits>  // Para std::cin.ignore y std::cin.fail

// Función para contar la frecuencia de cada carácter en una cadena
std::map<char, int> contarFrecuenciaCaracteres(const std::string& texto) {
    std::map<char, int> frecuencia; // Mapa ordenado por clave (carácter)
    for (char c : texto) {
        ++frecuencia[c]; // Incrementa la frecuencia para cada carácter
    }
    return frecuencia;
}

// Función para mostrar el mapa de frecuencias
void mostrarFrecuencia(const std::map<char, int>& frecuencia) {
    std::cout << "Frecuencia de caracteres:\n";
    for (const auto& par : frecuencia) {
        std::cout << "'" << par.first << "': " << par.second << '\n';
    }
}

int main() {
    try {
        std::string texto;
        
        // Caso de prueba funcional comentado (puedes descomentar para probar automáticamente)
        // texto = "Hola mundo";
        
        // Solicitar entrada al usuario
        std::cout << "Introduce una cadena de texto: ";
        std::getline(std::cin, texto);
        
        // Validación básica de entrada
        if (std::cin.fail()) {
            std::cin.clear(); // Limpiar el error
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            throw std::runtime_error("Error leyendo la entrada.");
        }
        if (texto.empty()) {
            throw std::invalid_argument("La cadena está vacía. Introduce al menos un carácter.");
        }
        
        // Calcular y mostrar frecuencias
        std::map<char, int> resultado = contarFrecuenciaCaracteres(texto);
        mostrarFrecuencia(resultado);
        
    } catch (const std::exception& e) {
        std::cerr << "Se produjo un error: " << e.what() << std::endl;
        return 1;
    }
    return 0;
}

//problema 3
#include <iostream>
#include <string>
#include <sstream>
#include <cctype>
#include <exception>
#include <stdexcept>

// Función para limpiar signos de puntuación de una palabra
std::string limpiar_palabra(const std::string& palabra) {
    std::string resultado;
    for (char c : palabra) {
        if (!std::ispunct(static_cast<unsigned char>(c))) { // Ignora signos de puntuación
            resultado += c;
        }
    }
    return resultado;
}

// Función que retorna la palabra de mayor longitud de una frase
std::string palabra_mas_larga(const std::string& frase) {
    if (frase.empty()) {
        throw std::invalid_argument("La frase está vacía.");
    }
    std::istringstream iss(frase);
    std::string palabra;
    std::string palabra_max;
    size_t max_longitud = 0;

    while (iss >> palabra) { // Extrae cada palabra
        std::string limpia = limpiar_palabra(palabra);
        if (limpia.length() > max_longitud) {
            palabra_max = limpia;
            max_longitud = limpia.length();
        }
    }
    if (max_longitud == 0) {
        throw std::runtime_error("No se encontraron palabras válidas en la frase.");
    }
    return palabra_max;
}

int main() {
    try {
        // Caso de prueba funcional
        std::string frase = "Hola, ¿cómo estás? Espero que muy, muy bien!";
        std::cout << "Frase a analizar:\n" << frase << "\n";

        std::string resultado = palabra_mas_larga(frase);

        std::cout << "La palabra de mayor longitud es: '" << resultado << "'\n";
    }
    catch (const std::exception& ex) {
        std::cerr << "Error: " << ex.what() << std::endl;
        return 1;
    }

    return 0;
}

//problema 4
#include <iostream>
#include <limits> // Para limpiar la entrada en caso de error

// Función que determina si un número es primo
bool esPrimo(int num) {
    if (num <= 1) return false; // Los números <= 1 no son primos
    if (num == 2) return true;  // El 2 es primo
    if (num % 2 == 0) return false; // Otros pares no son primos

    // Solo probamos hasta la raíz cuadrada de num
    for (int i = 3; i * i <= num; i += 2) {
        if (num % i == 0) return false;
    }
    return true;
}

int main() {
    int numero;

    std::cout << "Ingrese un número entero para verificar si es primo: ";

    // Validación de la entrada
    while (!(std::cin >> numero)) {
        std::cin.clear(); // Limpia el error
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); // Descarta la entrada incorrecta
        std::cout << "Entrada no válida. Por favor, ingrese un número entero: ";
    }

    // Caso de prueba funcional: por ejemplo, el 7 es primo
    // Puedes descomentar estas líneas para comprobar automáticamente un caso de prueba
    /*
    numero = 7;
    std::cout << "Probando con el número: " << numero << std::endl;
    */

    // Verifica si el número es primo y muestra el resultado
    if (esPrimo(numero)) {
        std::cout << numero << " es un número primo." << std::endl;
    } else {
        std::cout << numero << " NO es un número primo." << std::endl;
    }

    return 0;
}
//Instrucciones para ejecución y prueba:
//1.	Compilación
//Bash
//g++ -o primo programa_primo.cpp
//2.	Ejecución
//Bash
//./primo
//3.	Caso de prueba
//•	Puedes ingresar por ejemplo 7 para ver que es primo.
//•	Si introduces letras o caracteres inválidos, el programa pedirá nuevamente la entrada, demostrando el manejo de errores.

//problema 5
#include <iostream>
#include <vector>
#include <limits>
#include <stdexcept>

// Función para encontrar el valor máximo y mínimo en una colección de enteros
// Lanza una excepción si la colección está vacía
std::pair<int, int> encontrarMaximoYMinimo(const std::vector<int>& numeros) {
    if (numeros.empty()) {
        // Controlamos la colección vacía lanzando una excepción estándar
        throw std::invalid_argument("La colección está vacía.");
    }

    // Inicializamos los valores con los del primer elemento
    int minimo = numeros[0];
    int maximo = numeros[0];

    // Recorremos la colección manualmente
    for (size_t i = 1; i < numeros.size(); ++i) {
        if (numeros[i] < minimo) {
            minimo = numeros[i];
        }
        if (numeros[i] > maximo) {
            maximo = numeros[i];
        }
    }
    return std::make_pair(minimo, maximo);
}

int main() {
    // Caso de prueba funcional
    std::vector<int> numeros = {5, 2, 9, -3, 7, 2};

    try {
        // Buscamos el menor y el mayor usando la función
        auto resultado = encontrarMaximoYMinimo(numeros);
        std::cout << "El mínimo es: " << resultado.first << std::endl;
        std::cout << "El máximo es: " << resultado.second << std::endl;
    } catch (const std::invalid_argument& e) {
        // Capturamos el error en caso de vector vacío
        std::cerr << "Error: " << e.what() << std::endl;
    }

    // Caso de prueba con una lista vacía
    std::vector<int> vacio;
    try {
        auto resultado_vacio = encontrarMaximoYMinimo(vacio);
        std::cout << "El mínimo es: " << resultado_vacio.first << std::endl;
        std::cout << "El máximo es: " << resultado_vacio.second << std::endl;
    } catch (const std::invalid_argument& e) {
        std::cerr << "Error al procesar lista vacía: " << e.what() << std::endl;
    }

    return 0;
}

//problema 6
#include <iostream>         // Para std::cout, std::cin
#include <stdexcept>        // Para std::invalid_argument

// Clase que encapsula los métodos del factorial
class FactorialCalculator {
public:
    // Método iterativo para calcular el factorial
    static unsigned long long factorialIterativo(int n) {
        if (n < 0)
            throw std::invalid_argument("El número debe ser no negativo.");
        unsigned long long resultado = 1;
        for (int i = 2; i <= n; ++i) {
            resultado *= i;
        }
        return resultado;
    }

    // Método recursivo para calcular el factorial
    static unsigned long long factorialRecursivo(int n) {
        if (n < 0)
            throw std::invalid_argument("El número debe ser no negativo.");
        // Caso base: 0! o 1! = 1
        if (n == 0 || n == 1)
            return 1;
        // Paso recursivo
        return n * factorialRecursivo(n - 1);
    }
};

int main() {
    int n;

    std::cout << "Ingrese un número entero positivo para calcular su factorial: ";
    std::cin >> n;

    try {
        if (n < 0) {
            throw std::invalid_argument("Debes ingresar un número entero no negativo.");
        }

        // Demostración del método iterativo
        unsigned long long resultadoIter = FactorialCalculator::factorialIterativo(n);
        std::cout << "[Iterativo] El factorial de " << n << " es: " << resultadoIter << std::endl;

        // Demostración del método recursivo
        unsigned long long resultadoRec = FactorialCalculator::factorialRecursivo(n);
        std::cout << "[Recursivo] El factorial de " << n << " es: " << resultadoRec << std::endl;

    } catch (const std::invalid_argument& e) {
        // Manejo de errores de entrada
        std::cerr << "Error: " << e.what() << std::endl;
        return 1;
    } catch (...) {
        std::cerr << "Ocurrió un error inesperado." << std::endl;
        return 1;
    }

    return 0;
}

/*
    Ejemplo de ejecución:
    Entrada:
        Ingrese un número entero positivo para calcular su factorial: 5
    Salida:
        [Iterativo] El factorial de 5 es: 120
        [Recursivo] El factorial de 5 es: 120
*/

//problema 7
#include <iostream>
#include <stdexcept> // Para gestionar excepciones estándar

// Función que realiza la operación solicitada y lanza excepciones si es necesario
double calcular(double num1, double num2, char operacion) {
    switch (operacion) {
        case '+':
            return num1 + num2;
        case '-':
            return num1 - num2;
        case '*':
            return num1 * num2;
        case '/':
            if (num2 == 0) {
                // Lanza una excepción si se intenta dividir por cero
                throw std::runtime_error("Error: División por cero.");
            }
            return num1 / num2;
        default:
            // Lanza una excepción si la operación es inválida
            throw std::invalid_argument("Error: Operación no permitida.");
    }
}

// Función principal del programa
int main() {
    std::cout << "=== Calculadora Básica ===" << std::endl;

    // Caso de prueba funcional: 10 / 2
    double num1 = 10;
    double num2 = 2;
    char operacion = '/';

    // También podrías pedir los datos al usuario así:
    /*
    std::cout << "Introduce el primer número: ";
    std::cin >> num1;
    std::cout << "Introduce el segundo número: ";
    std::cin >> num2;
    std::cout << "Introduce la operación (+, -, *, /): ";
    std::cin >> operacion;
    */

    try {
        // Realiza la operación y muestra el resultado
        double resultado = calcular(num1, num2, operacion);
        std::cout << num1 << " " << operacion << " " << num2 << " = " << resultado << std::endl;
    } catch (const std::invalid_argument& e) {
        // Captura y muestra error de operación inválida
        std::cerr << e.what() << std::endl;
    } catch (const std::runtime_error& e) {
        // Captura y muestra error de división por cero
        std::cerr << e.what() << std::endl;
    } catch (...) {
        // Captura cualquier otro tipo de excepción no prevista
        std::cerr << "Error desconocido." << std::endl;
    }

    // Otro caso de prueba: división por cero
    num2 = 0;
    std::cout << "\nCaso de prueba: 10 / 0\n";
    try {
        double resultado = calcular(num1, num2, operacion);
        std::cout << num1 << " " << operacion << " " << num2 << " = " << resultado << std::endl;
    } catch (const std::runtime_error& e) {
        std::cerr << e.what() << std::endl;
    }

    return 0;
}

//problema 8
#include <iostream>
#include <vector>
#include <string>
#include <stdexcept>

// Clase Libro: representa un libro en la biblioteca
class Libro {
private:
    std::string titulo;
    std::string autor;
    bool prestado; // true si el libro está prestado

public:
    // Constructor
    Libro(const std::string& titulo_, const std::string& autor_)
        : titulo(titulo_), autor(autor_), prestado(false) {}

    // Devuelve el título
    std::string getTitulo() const {
        return titulo;
    }

    // Devuelve el autor
    std::string getAutor() const {
        return autor;
    }

    // Indica si el libro está prestado
    bool estaPrestado() const {
        return prestado;
    }

    // Marca el libro como prestado
    void prestar() {
        if (prestado) {
            throw std::runtime_error("El libro \"" + titulo + "\" ya está prestado.");
        }
        prestado = true;
    }

    // Marca el libro como disponible
    void devolver() {
        if (!prestado) {
            throw std::runtime_error("El libro \"" + titulo + "\" no está prestado.");
        }
        prestado = false;
    }
};

// Clase Biblioteca: gestiona un conjunto de libros
class Biblioteca {
private:
    std::vector<Libro> libros; // Colección de libros

    // Busca un libro por título y devuelve un puntero a él, o nullptr si no existe
    Libro* buscarLibro(const std::string& titulo) {
        for (auto& libro : libros) {
            if (libro.getTitulo() == titulo) {
                return &libro;
            }
        }
        return nullptr;
    }

public:
    // Agrega un nuevo libro a la biblioteca
    void agregarLibro(const std::string& titulo, const std::string& autor) {
        if (buscarLibro(titulo)) {
            throw std::runtime_error("El libro \"" + titulo + "\" ya existe en la biblioteca.");
        }
        libros.emplace_back(titulo, autor);
    }

    // Presta un libro por título
    void prestarLibro(const std::string& titulo) {
        Libro* libro = buscarLibro(titulo);
        if (!libro) {
            throw std::runtime_error("Libro \"" + titulo + "\" no encontrado en la biblioteca.");
        }
        libro->prestar();
    }

    // Devuelve un libro por título
    void devolverLibro(const std::string& titulo) {
        Libro* libro = buscarLibro(titulo);
        if (!libro) {
            throw std::runtime_error("Libro \"" + titulo + "\" no encontrado en la biblioteca.");
        }
        libro->devolver();
    }

    // Muestra el estado de todos los libros
    void mostrarLibros() const {
        std::cout << "Libros en la biblioteca:\n";
        for (const auto& libro : libros) {
            std::cout << "- " << libro.getTitulo() << " de " << libro.getAutor();
            std::cout << (libro.estaPrestado() ? " [PRESTADO]" : " [DISPONIBLE]") << '\n';
        }
    }
};


// Caso de prueba funcional
int main() {
    Biblioteca biblioteca;

    try {
        // Agregar libros
        biblioteca.agregarLibro("Cien Años de Soledad", "Gabriel García Márquez");
        biblioteca.agregarLibro("Don Quijote de la Mancha", "Miguel de Cervantes");
        biblioteca.agregarLibro("El Principito", "Antoine de Saint-Exupéry");

        // Mostrar libros
        biblioteca.mostrarLibros();

        std::cout << "\nPrestando 'El Principito':\n";
        biblioteca.prestarLibro("El Principito");

        std::cout << "\nIntentando prestar 'El Principito' de nuevo (debe lanzar excepción):\n";
        biblioteca.prestarLibro("El Principito"); // Esto lanzará una excepción

    } catch(const std::exception& e) {
        std::cerr << "Error: " << e.what() << '\n';
    }

    try {
        std::cout << "\nDevolviendo 'El Principito':\n";
        biblioteca.devolverLibro("El Principito");

        std::cout << "\nDevolviendo 'El Principito' de nuevo (debe lanzar excepción):\n";
        biblioteca.devolverLibro("El Principito"); // Esto lanza excepción

    } catch(const std::exception& e) {
        std::cerr << "Error: " << e.what() << '\n';
    }

    std::cout << "\nEstado final de la biblioteca:\n";
    biblioteca.mostrarLibros();

    return 0;
}
