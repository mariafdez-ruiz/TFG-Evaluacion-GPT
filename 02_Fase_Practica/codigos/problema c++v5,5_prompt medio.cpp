// v 5.5 prompt medio
//problema 1
#include <iostream>
#include <string>
#include <cctype>
#include <stdexcept>

// Comprueba si una cadena es un palíndromo.
// Solo se tienen en cuenta los caracteres alfanuméricos
// y no se distingue entre mayúsculas y minúsculas.
bool esPalindromo(const std::string& texto) {
    if (texto.empty()) {
        throw std::invalid_argument("La cadena no puede estar vacia.");
    }

    std::size_t izquierda = 0;
    std::size_t derecha = texto.size() - 1;

    while (izquierda < derecha) {
        // Ignorar espacios, puntuación y otros caracteres
        // que no sean letras o números.
        while (izquierda < derecha &&
               !std::isalnum(static_cast<unsigned char>(texto[izquierda]))) {
            ++izquierda;
        }

        while (izquierda < derecha &&
               !std::isalnum(static_cast<unsigned char>(texto[derecha]))) {
            --derecha;
        }

        // Convertir ambos caracteres a minúsculas para compararlos.
        unsigned char cIzq =
            static_cast<unsigned char>(texto[izquierda]);
        unsigned char cDer =
            static_cast<unsigned char>(texto[derecha]);

        if (std::tolower(cIzq) != std::tolower(cDer)) {
            return false;
        }

        ++izquierda;
        --derecha;
    }

    return true;
}

int main() {
    try {
        // Caso de prueba: al eliminar espacios y puntuación
        // y no distinguir mayúsculas, resulta "anitalavalatina".
        const std::string texto = "Anita, lava la tina!";

        std::cout << "Texto: " << texto << '\n';

        if (esPalindromo(texto)) {
            std::cout << "Es un palindromo.\n";
        } else {
            std::cout << "No es un palindromo.\n";
        }

        return 0;
    }
    catch (const std::invalid_argument& e) {
        std::cerr << "Error de entrada: " << e.what() << '\n';
        return 1;
    }
    catch (const std::exception& e) {
        std::cerr << "Error inesperado: " << e.what() << '\n';
        return 1;
    }
}

//problema 2
#include <iostream>
#include <map>
#include <string>
#include <stdexcept>

int main() {
    try {
        std::string texto;

        // Solicitar al usuario la cadena de texto.
        std::cout << "Introduce una cadena de texto: ";

        // getline permite leer espacios y otros caracteres.
        if (!std::getline(std::cin, texto)) {
            throw std::runtime_error("No se pudo leer la cadena de entrada.");
        }

        // Validar que la cadena no esté vacía.
        if (texto.empty()) {
            throw std::invalid_argument("La cadena de texto no puede estar vacia.");
        }

        // std::map almacena cada caracter como clave y su frecuencia como valor.
        // Las claves se mantienen ordenadas automáticamente.
        std::map<char, std::size_t> frecuencias;

        // Contar las apariciones de cada caracter.
        for (char caracter : texto) {
            ++frecuencias[caracter];
        }

        // Mostrar el resultado en formato clave -> valor.
        std::cout << "\nFrecuencia de caracteres:\n";

        for (const auto& [caracter, frecuencia] : frecuencias) {
            // Hacemos visible el espacio para que el resultado sea más claro.
            if (caracter == ' ') {
                std::cout << "'[espacio]' -> " << frecuencia << '\n';
            } else if (caracter == '\t') {
                std::cout << "'[tabulacion]' -> " << frecuencia << '\n';
            } else {
                std::cout << "'" << caracter << "' -> " << frecuencia << '\n';
            }
        }

        return 0;

    } catch (const std::invalid_argument& e) {
        // Errores relacionados con datos de entrada no válidos.
        std::cerr << "Error de entrada: " << e.what() << '\n';
        return 1;

    } catch (const std::exception& e) {
        // Captura otras excepciones de la biblioteca estándar.
        std::cerr << "Error: " << e.what() << '\n';
        return 1;

    } catch (...) {
        // Protección ante cualquier excepción inesperada.
        std::cerr << "Se produjo un error inesperado.\n";
        return 1;
    }
}

//problema 3
#include <iostream>
#include <string>
#include <sstream>
#include <cctype>
#include <stdexcept>

// Elimina los signos de puntuación de una palabra.
// Se conservan únicamente caracteres alfanuméricos.
std::string limpiarPalabra(const std::string& palabra) {
    std::string limpia;

    for (unsigned char caracter : palabra) {
        if (std::isalnum(caracter)) {
            limpia += static_cast<char>(caracter);
        }
    }

    return limpia;
}

// Devuelve la palabra más larga de una frase.
// En caso de empate, devuelve la primera encontrada.
std::string palabraMasLarga(const std::string& frase) {
    if (frase.empty()) {
        throw std::invalid_argument("La frase no puede estar vacia.");
    }

    std::istringstream flujo(frase);
    std::string palabra;
    std::string masLarga;

    // Separamos inicialmente la frase por espacios.
    while (flujo >> palabra) {
        palabra = limpiarPalabra(palabra);

        // Ignoramos elementos que solo contenían puntuación.
        if (!palabra.empty() && palabra.length() > masLarga.length()) {
            masLarga = palabra;
        }
    }

    // Si no encontramos ninguna palabra válida, generamos un error.
    if (masLarga.empty()) {
        throw std::invalid_argument(
            "La frase no contiene ninguna palabra valida."
        );
    }

    return masLarga;
}

int main() {
    try {
        // Caso de prueba.
        std::string frase =
            "Hola, esta es una extraordinaria frase de prueba...";

        std::cout << "Frase: " << frase << '\n';

        std::string resultado = palabraMasLarga(frase);

        std::cout << "Palabra de mayor longitud: "
                  << resultado << '\n';
        std::cout << "Longitud: "
                  << resultado.length() << '\n';

    } catch (const std::invalid_argument& e) {
        // Errores relacionados con una entrada no válida.
        std::cerr << "Error de entrada: " << e.what() << '\n';
        return 1;

    } catch (const std::exception& e) {
        // Captura cualquier otra excepción de la biblioteca estándar.
        std::cerr << "Error inesperado: " << e.what() << '\n';
        return 1;
    }

    return 0;
}

//problema 4
#include <iostream>
#include <limits>

// Devuelve true si el número es primo y false en caso contrario.
bool esPrimo(long long numero) {
    // Por definición, los números menores que 2 no son primos.
    if (numero < 2) {
        return false;
    }

    // 2 es el único número primo par.
    if (numero == 2) {
        return true;
    }

    // Cualquier otro número par no puede ser primo.
    if (numero % 2 == 0) {
        return false;
    }

    // Solo comprobamos divisores impares hasta la raíz cuadrada.
    // Usamos divisor <= numero / divisor en lugar de divisor * divisor
    // para evitar un posible desbordamiento.
    for (long long divisor = 3;
         divisor <= numero / divisor;
         divisor += 2) {

        if (numero % divisor == 0) {
            return false;
        }
    }

    return true;
}

int main() {
    long long numero;

    std::cout << "Introduce un numero entero: ";

    // Se comprueba que la entrada sea realmente un entero y que esté
    // dentro del rango representable por long long.
    if (!(std::cin >> numero)) {
        std::cerr << "Error: debes introducir un numero entero valido.\n";

        // Limpiamos el estado de error del flujo de entrada.
        std::cin.clear();
        std::cin.ignore(
            std::numeric_limits<std::streamsize>::max(), '\n'
        );

        return 1;
    }

    if (esPrimo(numero)) {
        std::cout << numero << " es un numero primo.\n";
    } else {
        std::cout << numero << " no es un numero primo.\n";
    }

    return 0;
}
//Caso de prueba funcional:
//text
//Introduce un numero entero: 29
//29 es un numero primo.
//También gestiona casos como 1, 0 o números negativos, que no son primos, así como entradas incorrectas:
//text
//Introduce un numero entero: abc
//Error: debes introducir un numero entero valido.
//La complejidad temporal es O(√n) y la espacial es O(1). No es necesario usar try/catch en este caso, ya que la operación relevante (std::cin >> numero) comunica los errores de entrada mediante el estado del flujo, que se comprueba explícitamente.

//problema 5
#include <iostream>
#include <vector>
#include <stdexcept>
#include <utility>

// Busca manualmente el valor mínimo y máximo de una colección.
// Se recibe un puntero para poder detectar también el caso de una
// colección nula.
std::pair<int, int> encontrarMinMax(const std::vector<int>* numeros) {
    // Comprobar que el puntero no sea nulo.
    if (numeros == nullptr) {
        throw std::invalid_argument("La coleccion no puede ser nula.");
    }

    // No existe un mínimo ni un máximo si la colección está vacía.
    if (numeros->empty()) {
        throw std::invalid_argument("La coleccion no puede estar vacia.");
    }

    // Inicializamos ambos valores con el primer elemento.
    int minimo = (*numeros)[0];
    int maximo = (*numeros)[0];

    // Recorremos manualmente los elementos restantes.
    for (std::size_t i = 1; i < numeros->size(); ++i) {
        const int valor = (*numeros)[i];

        if (valor < minimo) {
            minimo = valor;
        }

        if (valor > maximo) {
            maximo = valor;
        }
    }

    // first contiene el mínimo y second el máximo.
    return {minimo, maximo};
}

int main() {
    try {
        // Caso de prueba funcional.
        std::vector<int> numeros = {12, -5, 8, 27, 0, -13, 19};

        const auto [minimo, maximo] = encontrarMinMax(&numeros);

        std::cout << "Coleccion: ";
        for (int numero : numeros) {
            std::cout << numero << ' ';
        }

        std::cout << "\nValor minimo: " << minimo;
        std::cout << "\nValor maximo: " << maximo << '\n';

        // También pueden comprobarse los casos de error:
        //
        // std::vector<int> vacia;
        // encontrarMinMax(&vacia);   // Lanza std::invalid_argument
        //
        // encontrarMinMax(nullptr);  // Lanza std::invalid_argument

    } catch (const std::exception& e) {
        // Captura y muestra los errores producidos por la función.
        std::cerr << "Error: " << e.what() << '\n';
        return 1;
    }

    return 0;
}

//problema 6
#include <iostream>
#include <limits>
#include <stdexcept>

class Factorial {
public:
    // Calcula el factorial de forma iterativa.
    static unsigned long long iterativo(int n) {
        validarEntrada(n);

        unsigned long long resultado = 1;

        for (int i = 2; i <= n; ++i) {
            // Comprobamos si la multiplicación produciría un desbordamiento.
            if (resultado > std::numeric_limits<unsigned long long>::max() /
                                static_cast<unsigned long long>(i)) {
                throw std::overflow_error(
                    "El factorial excede el rango de unsigned long long.");
            }

            resultado *= static_cast<unsigned long long>(i);
        }

        return resultado;
    }

    // Calcula el factorial de forma recursiva.
    static unsigned long long recursivo(int n) {
        validarEntrada(n);

        // Caso base: 0! = 1 y 1! = 1.
        if (n <= 1) {
            return 1;
        }

        unsigned long long anterior = recursivo(n - 1);

        // Evita un posible desbordamiento antes de multiplicar.
        if (anterior > std::numeric_limits<unsigned long long>::max() /
                       static_cast<unsigned long long>(n)) {
            throw std::overflow_error(
                "El factorial excede el rango de unsigned long long.");
        }

        return anterior * static_cast<unsigned long long>(n);
    }

private:
    // Comprueba que el número pertenezca al dominio admitido.
    static void validarEntrada(int n) {
        if (n < 0) {
            throw std::invalid_argument(
                "El factorial no esta definido para enteros negativos.");
        }
    }
};

int main() {
    try {
        // Caso de prueba funcional.
        // 5! = 5 * 4 * 3 * 2 * 1 = 120
        int numero = 5;

        std::cout << "Caso de prueba: " << numero << "!\n";
        std::cout << "Metodo iterativo: "
                  << Factorial::iterativo(numero) << '\n';
        std::cout << "Metodo recursivo: "
                  << Factorial::recursivo(numero) << '\n';

        // Demostracion adicional con un valor introducido por el usuario.
        std::cout << "\nIntroduce un entero no negativo: ";

        int entrada;

        if (!(std::cin >> entrada)) {
            throw std::invalid_argument(
                "La entrada debe ser un numero entero valido.");
        }

        std::cout << entrada << "! (iterativo) = "
                  << Factorial::iterativo(entrada) << '\n';

        std::cout << entrada << "! (recursivo) = "
                  << Factorial::recursivo(entrada) << '\n';

    } catch (const std::invalid_argument& e) {
        std::cerr << "Error de entrada: " << e.what() << '\n';
        return 1;
    } catch (const std::overflow_error& e) {
        std::cerr << "Error de desbordamiento: " << e.what() << '\n';
        return 1;
    } catch (const std::exception& e) {
        std::cerr << "Error inesperado: " << e.what() << '\n';
        return 1;
    }

    return 0;
}

//problema 7
#include <iostream>
#include <stdexcept>  // Para std::runtime_error
#include <limits>     // Para std::numeric_limits

using namespace std;

// Función que realiza la operación seleccionada.
// Puede lanzar una excepción si se intenta dividir entre cero
// o si el operador introducido no es válido.
double calcular(double num1, double num2, char operacion) {
    switch (operacion) {
        case '+':
            return num1 + num2;

        case '-':
            return num1 - num2;

        case '*':
            return num1 * num2;

        case '/':
            // Control explícito de la división por cero
            if (num2 == 0.0) {
                throw runtime_error("No se puede dividir entre cero.");
            }
            return num1 / num2;

        default:
            // Se lanza una excepción para operadores no reconocidos
            throw invalid_argument("Operacion no valida.");
    }
}

int main() {
    double num1, num2;
    char operacion;

    cout << "=== CALCULADORA BASICA ===" << endl;
    cout << "Operaciones disponibles: +, -, *, /" << endl;

    // Solicitar el primer número
    cout << "Introduce el primer numero: ";
    if (!(cin >> num1)) {
        cerr << "Error: el primer valor debe ser un numero." << endl;
        return 1;
    }

    // Solicitar la operación
    cout << "Introduce la operacion (+, -, *, /): ";
    cin >> operacion;

    // Solicitar el segundo número
    cout << "Introduce el segundo numero: ";
    if (!(cin >> num2)) {
        cerr << "Error: el segundo valor debe ser un numero." << endl;
        return 1;
    }

    try {
        // Intentamos realizar el cálculo.
        double resultado = calcular(num1, num2, operacion);

        cout << "Resultado: "
             << num1 << " " << operacion << " "
             << num2 << " = " << resultado << endl;
    }
    catch (const invalid_argument& e) {
        // Control de un operador incorrecto
        cerr << "Error: " << e.what() << endl;
        return 1;
    }
    catch (const runtime_error& e) {
        // Control de errores durante la operación,
        // especialmente la división entre cero.
        cerr << "Error: " << e.what() << endl;
        return 1;
    }
    catch (const exception& e) {
        // Captura cualquier otra excepción estándar inesperada
        cerr << "Error inesperado: " << e.what() << endl;
        return 1;
    }

    return 0;
}

//problema 8
#include <iostream>
#include <string>
#include <vector>
#include <stdexcept>

// Clase que representa un libro de la biblioteca.
class Libro {
private:
    std::string titulo;
    std::string autor;
    bool prestado;

public:
    // Constructor.
    Libro(const std::string& titulo, const std::string& autor)
        : titulo(titulo), autor(autor), prestado(false) {
        
        // Validamos los datos recibidos.
        if (titulo.empty()) {
            throw std::invalid_argument("El titulo no puede estar vacio.");
        }

        if (autor.empty()) {
            throw std::invalid_argument("El autor no puede estar vacio.");
        }
    }

    // Devuelve el titulo del libro.
    const std::string& getTitulo() const {
        return titulo;
    }

    // Devuelve el autor.
    const std::string& getAutor() const {
        return autor;
    }

    // Indica si el libro esta prestado.
    bool estaPrestado() const {
        return prestado;
    }

    // Presta el libro.
    // Si ya estaba prestado, se lanza una excepcion.
    void prestar() {
        if (prestado) {
            throw std::runtime_error(
                "El libro '" + titulo + "' ya esta prestado."
            );
        }

        prestado = true;
    }

    // Devuelve el libro.
    // Tambien controlamos el caso de intentar devolver
    // un libro que no estaba prestado.
    void devolver() {
        if (!prestado) {
            throw std::runtime_error(
                "El libro '" + titulo + "' no esta prestado."
            );
        }

        prestado = false;
    }
};


// Clase encargada de gestionar los libros.
class Biblioteca {
private:
    std::vector<Libro> libros;

    // Busca un libro por su titulo.
    Libro* buscarLibro(const std::string& titulo) {
        for (Libro& libro : libros) {
            if (libro.getTitulo() == titulo) {
                return &libro;
            }
        }

        return nullptr;
    }

public:
    // Añade un libro a la biblioteca.
    void agregarLibro(const std::string& titulo, const std::string& autor) {
        // Evitamos tener dos libros con el mismo titulo
        // en este ejemplo sencillo.
        if (buscarLibro(titulo) != nullptr) {
            throw std::invalid_argument(
                "Ya existe un libro con el titulo '" + titulo + "'."
            );
        }

        libros.emplace_back(titulo, autor);
    }

    // Presta un libro identificado por su titulo.
    void prestarLibro(const std::string& titulo) {
        Libro* libro = buscarLibro(titulo);

        if (libro == nullptr) {
            throw std::runtime_error(
                "No se encontro el libro '" + titulo + "'."
            );
        }

        // Libro::prestar() lanzara una excepcion
        // si el libro ya estaba prestado.
        libro->prestar();
    }

    // Registra la devolucion de un libro.
    void devolverLibro(const std::string& titulo) {
        Libro* libro = buscarLibro(titulo);

        if (libro == nullptr) {
            throw std::runtime_error(
                "No se encontro el libro '" + titulo + "'."
            );
        }

        libro->devolver();
    }

    // Muestra todos los libros y su estado.
    void mostrarLibros() const {
        std::cout << "\n--- Libros de la biblioteca ---\n";

        for (const Libro& libro : libros) {
            std::cout
                << "Titulo: " << libro.getTitulo()
                << " | Autor: " << libro.getAutor()
                << " | Estado: "
                << (libro.estaPrestado() ? "Prestado" : "Disponible")
                << '\n';
        }
    }
};


int main() {
    try {
        Biblioteca biblioteca;

        // Añadimos algunos libros.
        biblioteca.agregarLibro(
            "Don Quijote de la Mancha",
            "Miguel de Cervantes"
        );

        biblioteca.agregarLibro(
            "Cien anos de soledad",
            "Gabriel Garcia Marquez"
        );

        // Mostramos el estado inicial.
        biblioteca.mostrarLibros();

        // Prueba de un prestamo correcto.
        std::cout << "\nPrestando Don Quijote...\n";
        biblioteca.prestarLibro("Don Quijote de la Mancha");

        biblioteca.mostrarLibros();

        // Caso de prueba de la excepcion solicitada:
        // intentamos prestar otra vez el mismo libro.
        try {
            std::cout << "\nIntentando prestarlo de nuevo...\n";
            biblioteca.prestarLibro("Don Quijote de la Mancha");
        }
        catch (const std::exception& e) {
            std::cerr << "Excepcion controlada: " << e.what() << '\n';
        }

        // Devolvemos correctamente el libro.
        std::cout << "\nDevolviendo Don Quijote...\n";
        biblioteca.devolverLibro("Don Quijote de la Mancha");

        biblioteca.mostrarLibros();
    }
    catch (const std::exception& e) {
        // Captura cualquier otro error producido durante
        // la ejecucion del programa.
        std::cerr << "Error: " << e.what() << '\n';
        return 1;
    }

    return 0;
}
