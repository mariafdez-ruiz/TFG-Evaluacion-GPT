// v 4.1 prompt experto
//problema 1
#include <string>
#include <algorithm>
#include <cctype>
#include <stdexcept>

/**
 * @brief Clase responsable de verificar si una cadena es un palíndromo,
 *        ignorando mayúsculas, minúsculas, espacios y puntuación.
 */
class PalindromeChecker {
public:
    /**
     * @brief Comprueba si la cadena s es un palíndromo.
     * @param s Cadena de entrada.
     * @return true si es palíndromo, false en caso contrario.
     * @throws std::invalid_argument si el puntero es nulo.
     */
    static bool isPalindrome(const std::string& s) {
        if (&s == nullptr) {
            throw std::invalid_argument("Cadena nula.");
        }
        std::string filtered = filterAndNormalize(s);
        return isPalindromeSimple(filtered);
    }

private:
    /**
     * @brief Elimina caracteres no alfanuméricos y normaliza a minúsculas.
     * @param input Cadena original.
     * @return Cadena filtrada y normalizada.
     */
    static std::string filterAndNormalize(const std::string& input) {
        std::string result;
        result.reserve(input.size());
        for (char c : input) {
            if (std::isalnum(static_cast<unsigned char>(c))) {
                result.push_back(static_cast<char>(std::tolower(static_cast<unsigned char>(c))));
            }
        }
        return result;
    }

    /**
     * @brief Verifica si una cadena limpia es palíndromo usando dos punteros.
     * @param str Cadena previamente filtrada y normalizada.
     * @return true si es palíndromo, false en caso contrario.
     */
    static bool isPalindromeSimple(const std::string& str) {
        size_t left = 0;
        size_t right = str.size();
        if (right == 0) return true;

        --right;
        while (left < right) {
            if (str[left] != str[right]) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }
};

// Ejemplo de uso (puede ponerse en otro archivo/función si se desea)
/*
#include <iostream>
int main() {
    try {
        std::string input = "A man, a plan, a canal: Panama!";
        bool result = PalindromeChecker::isPalindrome(input);
        std::cout << (result ? "Es palíndromo" : "No es palíndromo") << std::endl;
    } catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << std::endl;
    }
    return 0;
}
*/

Problema 2 pthon v 5.5 prompt básico
texto = input("Introduce una cadena de texto: ")

contador = {}

for caracter in texto:
    if caracter in contador:
        contador[caracter] += 1
    else:
        contador[caracter] = 1

for caracter, cantidad in contador.items():
    print(f"'{caracter}': {cantidad}")
Problema 2 python v 4.1 prompt basico
# Solicitar al usuario una cadena de texto
cadena = input("Introduce una cadena de texto: ")

# Crear un diccionario para contar caracteres
contador = {}

# Recorrer cada carácter en la cadena
for caracter in cadena:
    if caracter in contador:
        contador[caracter] += 1
    else:
        contador[caracter] = 1

# Mostrar el resultado
for caracter, cantidad in contador.items():
    print(f"'{caracter}': {cantidad}")
Problema 2 pthon v 5.5 prompt medio
def contar_caracteres(texto):
    """
    Cuenta la frecuencia de cada carácter de una cadena.

    Args:
        texto (str): Cadena de texto que se desea analizar.

    Returns:
        dict: Diccionario ordenado por carácter con sus frecuencias.

    Raises:
        TypeError: Si el argumento recibido no es una cadena.
    """

    # Validamos que la entrada sea una cadena de texto.
    if not isinstance(texto, str):
        raise TypeError("La entrada debe ser una cadena de texto.")

    # Diccionario donde cada carácter será una clave.
    frecuencias = {}

    # Recorremos la cadena y actualizamos la frecuencia de cada carácter.
    for caracter in texto:
        frecuencias[caracter] = frecuencias.get(caracter, 0) + 1

    # Devolvemos un diccionario ordenado alfabéticamente por sus claves.
    return dict(sorted(frecuencias.items()))


def main():
    """Caso de prueba y gestión de posibles errores."""
    try:
        texto = "hola mundo"

        resultado = contar_caracteres(texto)

        print(f"Texto analizado: {texto!r}")
        print("Frecuencia de caracteres:")

        # Mostramos cada clave y su valor de forma clara.
        for caracter, frecuencia in resultado.items():
            # repr() permite visualizar claramente caracteres como espacios.
            print(f"{caracter!r}: {frecuencia}")

    except (TypeError, ValueError) as error:
        print(f"Error: {error}")


# Ejecutamos el caso de prueba solo si el archivo se ejecuta directamente.
if __name__ == "__main__":
    main()

//problema 2
#include <iostream>
#include <string>
#include <unordered_map>
#include <map>
#include <stdexcept>

// Clase para contar la frecuencia de caracteres en una cadena
class CharacterFrequencyCounter {
public:
    // Tipo para mapear carácter a frecuencia
    using FrequencyMap = std::unordered_map<char, std::size_t>;

    // Constructor
    CharacterFrequencyCounter() = default;

    // Prohíbe copia e igualación para evitar copias accidentales costosas
    CharacterFrequencyCounter(const CharacterFrequencyCounter&) = delete;
    CharacterFrequencyCounter& operator=(const CharacterFrequencyCounter&) = delete;

    // Cuenta la frecuencia de caracteres en la 'inputString'
    // Lanza std::invalid_argument si el string es vacío
    void countFrequencies(const std::string& inputString) {
        if (inputString.empty()) {
            throw std::invalid_argument("Input string cannot be empty.");
        }
        freqMap_.clear();
        for (char ch : inputString) {
            ++freqMap_[ch];
        }
    }

    // Devuelve un std::map ordenado (por clave) con los resultados
    std::map<char, std::size_t> getOrderedFrequencies() const {
        return std::map<char, std::size_t>(freqMap_.begin(), freqMap_.end());
    }

private:
    FrequencyMap freqMap_;
};

// Programa principal para probar la clase
int main() {
    CharacterFrequencyCounter counter;

    std::string userInput;
    std::cout << "Introduce una cadena de texto: ";
    std::getline(std::cin, userInput);

    try {
        counter.countFrequencies(userInput);

        auto freqOrdered = counter.getOrderedFrequencies();

        std::cout << "Frecuencia de caracteres:" << std::endl;
        for (const auto& [ch, freq] : freqOrdered) {
            std::cout << "'" << ch << "': " << freq << std::endl;
        }

    } catch (const std::invalid_argument& ex) {
        std::cerr << "Error: " << ex.what() << std::endl;
        return 1;
    } catch (const std::exception& ex) {
        std::cerr << "Se produjo un error inesperado: " << ex.what() << std::endl;
        return 2;
    }

    return 0;
}

//problema 3
#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>
#include <cctype>
#include <stdexcept>

/**
 * Clase que analiza frases para identificar la palabra de mayor longitud,
 * ignorando signos de puntuación.
 */
class LongestWordAnalyzer {
public:
    /**
     * Construye el analizador con una frase dada.
     * @param phrase Frase a analizar.
     */
    explicit LongestWordAnalyzer(const std::string& phrase)
        : phrase_(phrase) {}

    /**
     * Obtiene la palabra de mayor longitud en la frase.
     * Ignora signos de puntuación.
     * @return La palabra más larga encontrada.
     * @throw std::invalid_argument si la entrada está vacía o no hay palabras.
     */
    std::string findLongestWord() const {
        if (phrase_.empty())
            throw std::invalid_argument("La frase de entrada está vacía.");

        std::istringstream iss(phrase_);
        std::string word;
        std::string longestWord;
        size_t maxLength = 0;

        while (iss >> word) {
            std::string cleanWord = removePunctuation(word);
            if (!cleanWord.empty() && cleanWord.length() > maxLength) {
                maxLength = cleanWord.length();
                longestWord = cleanWord;
            }
        }

        if (longestWord.empty())
            throw std::invalid_argument("No se encontraron palabras válidas en la frase.");

        return longestWord;
    }

private:
    std::string phrase_;

    /**
     * Elimina los signos de puntuación de una palabra.
     * @param input Palabra original.
     * @return Palabra sin signos de puntuación.
     */
    static std::string removePunctuation(const std::string& input) {
        std::string result;
        std::copy_if(input.begin(), input.end(), std::back_inserter(result),
            [](unsigned char ch) { return !std::ispunct(ch); });
        return result;
    }
};

// Ejemplo de uso
int main() {
    try {
        std::string frase = "¡Hola, mundo! Este es un ejemplo... ¿cuál será la palabra más larga?";
        LongestWordAnalyzer analyzer(frase);
        std::string longest = analyzer.findLongestWord();
        std::cout << "La palabra más larga es: " << longest << std::endl;
    } catch (const std::exception& ex) {
        std::cerr << "Error: " << ex.what() << std::endl;
    }
    return 0;
}

//problema 4
#include <stdexcept> // Para std::invalid_argument
#include <cmath>     // Para std::sqrt

/**
 * @class PrimeChecker
 * @brief Clase para determinar si un número entero es primo.
 *
 * Encapsula la lógica de evaluación, manejando errores y entradas inválidas.
 */
class PrimeChecker {
public:
    /**
     * @brief Determina si un número es primo.
     * @param n Número entero a evaluar.
     * @return true si n es primo, false en caso contrario.
     * @throws std::invalid_argument si n <= 1.
     */
    static bool isPrime(int n) {
        validateInput(n);

        if (n == 2 || n == 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        // Verificación solo hasta la raíz cuadrada de n
        // Revisamos los posibles divisores de la forma 6k ± 1
        for (int i = 5; i <= std::sqrt(n); i += 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }
        return true;
    }

private:
    /**
     * @brief Valida la entrada.
     * @param n Número entero a validar.
     * @throws std::invalid_argument si n <= 1.
     */
    static void validateInput(int n) {
        if (n <= 1) {
            throw std::invalid_argument("El número debe ser mayor que 1 para comprobar si es primo.");
        }
    }
};

//problema 5
#include <vector>
#include <utility>      // Para std::pair
#include <stdexcept>    // Para std::invalid_argument
#include <limits>       // Para valores máximos/mínimos
#include <iterator>     // Para std::begin y std::end

// Estructura para almacenar los resultados de análisis
struct MinMaxResult {
    int min;
    int max;
};

// Clase que encapsula el análisis de colecciones de enteros
class IntCollectionAnalyzer {
public:
    // Constructor que acepta iteradores genéricos
    template <typename Iterator>
    IntCollectionAnalyzer(Iterator begin, Iterator end) : begin_(begin), end_(end) {}

    // Método para calcular los valores mínimo y máximo
    MinMaxResult GetMinMax() const {
        if (begin_ == end_) {
            throw std::invalid_argument("La colección está vacía.");
        }

        int minValue = std::numeric_limits<int>::max();
        int maxValue = std::numeric_limits<int>::min();

        for (auto it = begin_; it != end_; ++it) {
            if (*it < minValue) minValue = *it;
            if (*it > maxValue) maxValue = *it;
        }

        return {minValue, maxValue};
    }

private:
    // Iteradores privados para mayor encapsulamiento
    // Pueden ser de cualquier tipo de colección estándar
    typename std::vector<int>::const_iterator begin_, end_;
};

// Función de ayuda para deducción automática de tipos de iterador
template <typename Collection>
MinMaxResult GetMinMaxFromCollection(const Collection& collection) {
    if constexpr (std::is_pointer<Collection>::value) {
        if (collection == nullptr) throw std::invalid_argument("La colección es nula.");
        // Asumimos puntero a arreglo; aquí podríamos adaptar para otros tipos si es necesario
        throw std::invalid_argument("No soportado: puntero sin tamaño explícito.");
    } else {
        IntCollectionAnalyzer analyzer(std::begin(collection), std::end(collection));
        return analyzer.GetMinMax();
    }
}

/*
    USO DE EJEMPLO:

    #include <iostream>
    int main() {
        std::vector<int> datos = {1, 23, -7, 16, 8};
        try {
            MinMaxResult res = GetMinMaxFromCollection(datos);
            std::cout << "Min: " << res.min << ", Max: " << res.max << '\n';
        } catch(const std::exception& ex) {
            std::cerr << "Error: " << ex.what() << '\n';
        }
        return 0;
    }
*/

//problema 6
#include <iostream>
#include <stdexcept> // Para std::invalid_argument
#include <limits>    // Para std::numeric_limits
#include <cstdint>   // Para std::uint64_t

/**
 * @class FactorialCalculator
 * @brief Proporciona métodos para calcular el factorial usando enfoques iterativos y recursivos.
 */
class FactorialCalculator {
public:
    /**
     * @brief Calcula el factorial de un número entero positivo usando iteración.
     * @param n Número para el que se calcula el factorial.
     * @return Factorial de 'n' si es válido.
     * @throws std::invalid_argument Si el número es negativo o demasiado grande para representar.
     */
    static std::uint64_t iterativeFactorial(int n) {
        validateInput(n);
        std::uint64_t result = 1;
        for (int i = 2; i <= n; ++i) {
            result *= i;
        }
        return result;
    }

    /**
     * @brief Calcula el factorial de un número entero positivo usando recursividad.
     * @param n Número para el que se calcula el factorial.
     * @return Factorial de 'n' si es válido.
     * @throws std::invalid_argument Si el número es negativo o demasiado grande para representar.
     */
    static std::uint64_t recursiveFactorial(int n) {
        validateInput(n);
        // Caso base
        if (n <= 1)
            return 1;
        // Llamada recursiva
        return static_cast<std::uint64_t>(n) * recursiveFactorial(n - 1);
    }

private:
    /**
     * @brief Valida que el número esté en el rango permitido.
     * @param n Número a validar.
     * @throws std::invalid_argument Si n es negativo o mayor a 20 (para evitar overflow en uint64_t).
     */
    static void validateInput(int n) {
        if (n < 0)
            throw std::invalid_argument("El número debe ser positivo.");
        if (n > 20)
            throw std::invalid_argument("El número es demasiado grande (máx. permitido: 20).");
    }
};


// -------------------------------
// Ejemplo de uso:
int main() {
    int n = 5; // Cambia este valor para probar con otros números

    try {
        std::cout << "Iterativo: " << n << "! = "
                  << FactorialCalculator::iterativeFactorial(n) << std::endl;

        std::cout << "Recursivo: " << n << "! = "
                  << FactorialCalculator::recursiveFactorial(n) << std::endl;

        // Demostración de manejo de excepción:
        std::cout << "\nIntentando calcular 21! (debe fallar):\n";
        std::cout << FactorialCalculator::iterativeFactorial(21) << std::endl;
    }
    catch (const std::invalid_argument& ex) {
        std::cerr << "Error: " << ex.what() << std::endl;
    }

    return 0;
}

//problema 7
// calculator.cpp

#include <iostream>
#include <stdexcept>
#include <limits>

/**
 * @brief Clase Calculator que encapsula las operaciones aritméticas básicas.
 */
class Calculator {
public:
    /**
     * @brief Suma dos números.
     * @param a Primer sumando.
     * @param b Segundo sumando.
     * @return La suma de a y b.
     */
    static double add(double a, double b) {
        return a + b;
    }

    /**
     * @brief Resta dos números.
     * @param a Minuendo.
     * @param b Sustraendo.
     * @return La diferencia entre a y b.
     */
    static double subtract(double a, double b) {
        return a - b;
    }

    /**
     * @brief Multiplica dos números.
     * @param a Primer factor.
     * @param b Segundo factor.
     * @return El producto de a y b.
     */
    static double multiply(double a, double b) {
        return a * b;
    }

    /**
     * @brief Divide dos números. Lanza una excepción si b == 0.
     * @param a Dividendo.
     * @param b Divisor.
     * @return La división de a por b.
     * @throws std::invalid_argument Si b es cero.
     */
    static double divide(double a, double b) {
        if (b == 0.0) {
            throw std::invalid_argument("Error: División por cero.");
        }
        return a / b;
    }
};

/**
 * @brief Lee un número de la entrada estándar con validación robusta.
 * @return El número leído.
 */
double readNumber() {
    double num;
    while (!(std::cin >> num)) {
        std::cin.clear(); // Limpia el flag de error
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); // Descarta la entrada incorrecta
        std::cout << "Entrada inválida. Intente nuevamente: ";
    }
    return num;
}

int main() {
    std::cout << "Calculadora básica en C++\n";
    std::cout << "Operaciones disponibles: +  -  *  /\n";

    double a, b, result;
    char op;

    std::cout << "Ingrese el primer número: ";
    a = readNumber();

    std::cout << "Ingrese una operación (+, -, *, /): ";
    std::cin >> op;

    std::cout << "Ingrese el segundo número: ";
    b = readNumber();

    try {
        switch (op) {
            case '+':
                result = Calculator::add(a, b);
                break;
            case '-':
                result = Calculator::subtract(a, b);
                break;
            case '*':
                result = Calculator::multiply(a, b);
                break;
            case '/':
                result = Calculator::divide(a, b);
                break;
            default:
                throw std::invalid_argument("Operación no soportada.");
        }
        std::cout << "Resultado: " << result << std::endl;
    } catch (const std::exception& ex) {
        std::cerr << ex.what() << std::endl;
        return EXIT_FAILURE;
    }

    return EXIT_SUCCESS;
}

//problema 8
#include <iostream>
#include <string>
#include <unordered_map>
#include <exception>

// Excepción personalizada para manejo de errores de préstamo/devolución
class LibroNoDisponibleException : public std::exception {
public:
    explicit LibroNoDisponibleException(const std::string& mensaje)
        : mensaje_(mensaje) {}

    const char* what() const noexcept override {
        return mensaje_.c_str();
    }
private:
    std::string mensaje_;
};

// Clase que representa un libro individual
class Libro {
public:
    // Constructor de Libro
    Libro(const std::string& id, const std::string& titulo, const std::string& autor)
        : id_(id), titulo_(titulo), autor_(autor), prestado_(false) {}

    // Getters solo lectura
    const std::string& obtenerId() const { return id_; }
    const std::string& obtenerTitulo() const { return titulo_; }
    const std::string& obtenerAutor() const { return autor_; }
    bool estaPrestado() const { return prestado_; }

    // Métodos para prestar y devolver el libro, modificando su estado interno
    void prestar() {
        if (prestado_) {
            throw LibroNoDisponibleException("El libro ya está prestado.");
        }
        prestado_ = true;
    }

    void devolver() {
        if (!prestado_) {
            throw LibroNoDisponibleException("El libro no estaba prestado.");
        }
        prestado_ = false;
    }

private:
    std::string id_;     // Ej: ISBN o código único
    std::string titulo_;
    std::string autor_;
    bool prestado_;
};


// Clase Biblioteca que administra una colección de libros
class Biblioteca {
public:
    // Añade un libro a la biblioteca (no permite duplicados)
    void agregarLibro(const Libro& libro) {
        const auto& id = libro.obtenerId();
        if (libros_.find(id) != libros_.end()) {
            throw std::invalid_argument("El libro ya existe en la biblioteca.");
        }
        libros_.emplace(id, libro);
    }

    // Presta un libro usando su ID único
    void prestarLibro(const std::string& id) {
        Libro& libro = obtenerLibroModificable(id);
        libro.prestar();
    }

    // Devuelve un libro usando su ID único
    void devolverLibro(const std::string& id) {
        Libro& libro = obtenerLibroModificable(id);
        libro.devolver();
    }

    // Método opcional: muestra todos los libros y su estado
    void mostrarLibros() const {
        for (const auto& [id, libro] : libros_) {
            std::cout 
                << "ID: " << id 
                << ", Título: " << libro.obtenerTitulo()
                << ", Autor: " << libro.obtenerAutor()
                << ", Estado: " << (libro.estaPrestado() ? "Prestado" : "Disponible")
                << std::endl;
        }
    }

private:
    std::unordered_map<std::string, Libro> libros_; // Mapa de libros por su ID/ISBN

    // Devuelve una referencia modificable a un libro, o lanza una excepción si no existe
    Libro& obtenerLibroModificable(const std::string& id) {
        auto it = libros_.find(id);
        if (it == libros_.end()) {
            throw std::invalid_argument("El libro no existe en la biblioteca.");
        }
        return it->second;
    }
};

// --------- Ejemplo de uso ---------
/*
int main() {
    Biblioteca biblioteca;
    try {
        biblioteca.agregarLibro(Libro("ISBN-1", "C++ moderno", "Bjarne Stroustrup"));
        biblioteca.agregarLibro(Libro("ISBN-2", "Clean Code", "Robert C. Martin"));

        biblioteca.mostrarLibros();

        std::cout << "\nPrestando libro ISBN-1...\n";
        biblioteca.prestarLibro("ISBN-1");

        biblioteca.mostrarLibros();

        std::cout << "\nIntentando prestar nuevamente ISBN-1...\n";
        biblioteca.prestarLibro("ISBN-1"); // Lanza excepción

    } catch (const std::exception& ex) {
        std::cerr << "Error: " << ex.what() << std::endl;
    }
    return 0;
}
*/
