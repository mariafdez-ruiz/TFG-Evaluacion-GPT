// versión 5.5 prompt básico
//problema 1
#include <iostream>
#include <string>
using namespace std;

int main() {
    string texto;

    cout << "Introduce una cadena de texto: ";
    getline(cin, texto);

    bool esPalindromo = true;

    for (int i = 0; i < texto.length() / 2; i++) {
        if (texto[i] != texto[texto.length() - 1 - i]) {
            esPalindromo = false;
            break;
        }
    }

    if (esPalindromo) {
        cout << "La cadena es un palindromo." << endl;
    } else {
        cout << "La cadena no es un palindromo." << endl;
    }

    return 0;
}
// problema 2
#include <iostream>
#include <map>
#include <string>
using namespace std;

int main() {
    string texto;
    map<char, int> frecuencia;

    cout << "Introduce una cadena de texto: ";
    getline(cin, texto);

    // Contar la frecuencia de cada carácter
    for (char c : texto) {
        frecuencia[c]++;
    }

    // Mostrar los resultados
    cout << "\nFrecuencia de caracteres:\n";

    for (const auto& elemento : frecuencia) {
        if (elemento.first == ' ')
            cout << "[espacio]";
        else
            cout << "'" << elemento.first << "'";

        cout << ": " << elemento.second << " veces\n";
    }

    return 0;
}

//problema 3
#include <iostream>
#include <string>
#include <sstream>
#include <cctype>

using namespace std;

int main() {
    string frase, palabra, palabraLarga;

    cout << "Introduce una frase: ";
    getline(cin, frase);

    // Sustituir los signos de puntuacion por espacios
    for (char &c : frase) {
        if (ispunct(static_cast<unsigned char>(c))) {
            c = ' ';
        }
    }

    // Separar la frase en palabras
    stringstream ss(frase);

    while (ss >> palabra) {
        if (palabra.length() > palabraLarga.length()) {
            palabraLarga = palabra;
        }
    }

    if (!palabraLarga.empty()) {
        cout << "La palabra de mayor longitud es: "
             << palabraLarga << endl;
        cout << "Tiene " << palabraLarga.length()
             << " caracteres." << endl;
    } else {
        cout << "No se encontraron palabras." << endl;
    }

    return 0;
}

//problema 4
#include <iostream>
using namespace std;

int main() {
    int numero;
    bool esPrimo = true;

    cout << "Introduce un numero entero: ";
    cin >> numero;

    if (numero <= 1) {
        esPrimo = false;
    } else {
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                esPrimo = false;
                break;
            }
        }
    }

    if (esPrimo) {
        cout << numero << " es un numero primo." << endl;
    } else {
        cout << numero << " no es un numero primo." << endl;
    }

    return 0;
}

//problema 5
#include <iostream>
#include <cstddef>

bool encontrarMinMax(const int* numeros, std::size_t cantidad,
                     int& minimo, int& maximo)
{
    // Comprobar array nulo o vacío
    if (numeros == nullptr || cantidad == 0) {
        return false;
    }

    // Inicializar con el primer elemento
    minimo = numeros[0];
    maximo = numeros[0];

    // Recorrido manual del resto del array
    for (std::size_t i = 1; i < cantidad; ++i) {
        if (numeros[i] < minimo) {
            minimo = numeros[i];
        }

        if (numeros[i] > maximo) {
            maximo = numeros[i];
        }
    }

    return true;
}

int main()
{
    int numeros[] = {8, -3, 15, 4, 0, 21, -7};
    std::size_t cantidad = sizeof(numeros) / sizeof(numeros[0]);

    int minimo, maximo;

    if (encontrarMinMax(numeros, cantidad, minimo, maximo)) {
        std::cout << "Valor minimo: " << minimo << '\n';
        std::cout << "Valor maximo: " << maximo << '\n';
    } else {
        std::cout << "Error: el array es nulo o esta vacio.\n";
    }

    return 0;
}

//problema 6
#include <iostream>
using namespace std;

class Factorial {
public:
    // Método iterativo
    unsigned long long iterativo(int n) {
        unsigned long long resultado = 1;

        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }

        return resultado;
    }

    // Método recursivo
    unsigned long long recursivo(int n) {
        if (n <= 1) {
            return 1;
        }

        return n * recursivo(n - 1);
    }
};

int main() {
    int numero;
    Factorial factorial;

    cout << "Ingrese un numero entero positivo: ";
    cin >> numero;

    if (numero < 0) {
        cout << "Error: el factorial no esta definido para numeros negativos."
             << endl;
        return 1;
    }

    unsigned long long resultadoIterativo = factorial.iterativo(numero);
    unsigned long long resultadoRecursivo = factorial.recursivo(numero);

    cout << "\nFactorial mediante metodo iterativo: "
         << resultadoIterativo << endl;

    cout << "Factorial mediante metodo recursivo: "
         << resultadoRecursivo << endl;

    if (resultadoIterativo == resultadoRecursivo) {
        cout << "Ambos metodos producen el mismo resultado." << endl;
    } else {
        cout << "Los resultados son diferentes." << endl;
    }

    return 0;
}

//problema 7 
#include <iostream>
#include <stdexcept>
using namespace std;

double calcular(double a, double b, char operacion) {
    switch (operacion) {
        case '+':
            return a + b;

        case '-':
            return a - b;

        case '*':
            return a * b;

        case '/':
            if (b == 0) {
                throw runtime_error("No se puede dividir entre cero.");
            }
            return a / b;

        default:
            throw invalid_argument("Operacion no valida.");
    }
}

int main() {
    double numero1, numero2;
    char operacion;

    try {
        cout << "=== CALCULADORA BASICA ===" << endl;

        cout << "Ingrese el primer numero: ";
        if (!(cin >> numero1)) {
            throw invalid_argument("El primer valor no es un numero valido.");
        }

        cout << "Ingrese la operacion (+, -, *, /): ";
        cin >> operacion;

        cout << "Ingrese el segundo numero: ";
        if (!(cin >> numero2)) {
            throw invalid_argument("El segundo valor no es un numero valido.");
        }

        double resultado = calcular(numero1, numero2, operacion);
        cout << "Resultado: " << resultado << endl;
    }
    catch (const invalid_argument& e) {
        cerr << "Error de entrada: " << e.what() << endl;
    }
    catch (const runtime_error& e) {
        cerr << "Error de calculo: " << e.what() << endl;
    }
    catch (const exception& e) {
        cerr << "Error inesperado: " << e.what() << endl;
    }

    return 0;
}

//problema 8
#include <iostream>
#include <vector>
#include <string>
#include <stdexcept>

using namespace std;

// Excepción personalizada
class LibroNoDisponibleException : public runtime_error {
public:
    LibroNoDisponibleException(const string& mensaje)
        : runtime_error(mensaje) {}
};

// Clase Libro
class Libro {
private:
    string titulo;
    string autor;
    bool disponible;

public:
    Libro(const string& titulo, const string& autor)
        : titulo(titulo), autor(autor), disponible(true) {}

    string getTitulo() const {
        return titulo;
    }

    string getAutor() const {
        return autor;
    }

    bool estaDisponible() const {
        return disponible;
    }

    void prestar() {
        if (!disponible) {
            throw LibroNoDisponibleException(
                "El libro \"" + titulo + "\" ya ha sido prestado."
            );
        }

        disponible = false;
    }

    void devolver() {
        disponible = true;
    }

    void mostrar() const {
        cout << "Titulo: " << titulo
             << " | Autor: " << autor
             << " | Estado: "
             << (disponible ? "Disponible" : "Prestado")
             << endl;
    }
};

// Clase Biblioteca
class Biblioteca {
private:
    vector<Libro> libros;

    Libro* buscarLibro(const string& titulo) {
        for (Libro& libro : libros) {
            if (libro.getTitulo() == titulo) {
                return &libro;
            }
        }
        return nullptr;
    }

public:
    void anadirLibro(const Libro& libro) {
        libros.push_back(libro);
    }

    void prestarLibro(const string& titulo) {
        Libro* libro = buscarLibro(titulo);

        if (libro == nullptr) {
            throw runtime_error("El libro no existe en la biblioteca.");
        }

        libro->prestar();
        cout << "Prestamo realizado: \"" << titulo << "\"." << endl;
    }

    void devolverLibro(const string& titulo) {
        Libro* libro = buscarLibro(titulo);

        if (libro == nullptr) {
            throw runtime_error("El libro no existe en la biblioteca.");
        }

        libro->devolver();
        cout << "Libro devuelto: \"" << titulo << "\"." << endl;
    }

    void mostrarLibros() const {
        cout << "\n--- Libros de la biblioteca ---" << endl;

        for (const Libro& libro : libros) {
            libro.mostrar();
        }
    }
};

int main() {
    Biblioteca biblioteca;

    biblioteca.anadirLibro(Libro("Don Quijote de la Mancha",
                                 "Miguel de Cervantes"));
    biblioteca.anadirLibro(Libro("Cien anos de soledad",
                                 "Gabriel Garcia Marquez"));
    biblioteca.anadirLibro(Libro("1984",
                                 "George Orwell"));

    try {
        biblioteca.mostrarLibros();

        biblioteca.prestarLibro("1984");

        // Segundo intento: lanzará la excepción
        biblioteca.prestarLibro("1984");

    } catch (const LibroNoDisponibleException& e) {
        cerr << "Error de prestamo: " << e.what() << endl;
    } catch (const exception& e) {
        cerr << "Error: " << e.what() << endl;
    }

    // Devolvemos el libro
    try {
        biblioteca.devolverLibro("1984");
    } catch (const exception& e) {
        cerr << "Error: " << e.what() << endl;
    }

    biblioteca.mostrarLibros();

    return 0;
}
