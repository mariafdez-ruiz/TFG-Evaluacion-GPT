// v 5.5 prompt experto
//problema 1
#include <cctype>
#include <exception>
#include <iostream>
#include <stdexcept>
#include <string>
#include <string_view>

namespace palindrome {

/**
 * Comprueba si un texto es un palíndromo ignorando:
 * - Mayúsculas y minúsculas.
 * - Espacios.
 * - Signos de puntuación.
 *
 * La comparación se realiza sin crear una copia normalizada del texto,
 * utilizando dos índices que avanzan desde ambos extremos.
 */
[[nodiscard]]
bool isPalindrome(std::string_view text)
{
    std::size_t left = 0;
    std::size_t right = text.size();

    while (left < right) {
        // Busca el siguiente carácter alfanumérico desde la izquierda.
        while (left < right &&
               !std::isalnum(static_cast<unsigned char>(text[left]))) {
            ++left;
        }

        // Busca el siguiente carácter alfanumérico desde la derecha.
        while (left < right &&
               !std::isalnum(
                   static_cast<unsigned char>(text[right - 1]))) {
            --right;
        }

        if (left >= right) {
            break;
        }

        const auto leftChar = static_cast<unsigned char>(text[left]);
        const auto rightChar =
            static_cast<unsigned char>(text[right - 1]);

        if (std::tolower(leftChar) != std::tolower(rightChar)) {
            return false;
        }

        ++left;
        --right;
    }

    return true;
}

/**
 * Lee una línea completa desde la entrada estándar.
 *
 * Lanza una excepción si se produce un error de lectura.
 */
[[nodiscard]]
std::string readInput()
{
    std::string text;

    if (!std::getline(std::cin, text)) {
        throw std::runtime_error("No se pudo leer la cadena de entrada.");
    }

    return text;
}

} // namespace palindrome

int main()
{
    try {
        const std::string text = palindrome::readInput();

        std::cout
            << (palindrome::isPalindrome(text)
                    ? "La cadena es un palíndromo.\n"
                    : "La cadena no es un palíndromo.\n");

        return 0;
    }
    catch (const std::exception& exception) {
        std::cerr << "Error: " << exception.what() << '\n';
        return 1;
    }
    catch (...) {
        // Barrera de excepción para impedir que errores no controlados
        // escapen del punto de entrada de la aplicación.
        std::cerr << "Error inesperado.\n";
        return 1;
    }
}

//problema 2
#include <iostream>
#include <map>
#include <stdexcept>
#include <string>

/**
 * @brief Encapsula la lógica para contar frecuencias de caracteres.
 *
 * std::map permite almacenar los pares carácter-frecuencia
 * manteniendo las claves ordenadas automáticamente.
 */
class CharacterFrequencyCounter {
public:
    using FrequencyMap = std::map<char, std::size_t>;

    /**
     * @brief Calcula la frecuencia de cada carácter.
     * @param text Cadena que se desea analizar.
     * @return Mapa ordenado con los pares <carácter, frecuencia>.
     * @throws std::invalid_argument si la cadena está vacía.
     */
    [[nodiscard]]
    FrequencyMap count(const std::string& text) const {
        if (text.empty()) {
            throw std::invalid_argument("La cadena no puede estar vacía.");
        }

        FrequencyMap frequencies;

        for (const char character : text) {
            ++frequencies[character];
        }

        return frequencies;
    }
};

/**
 * @brief Presenta las frecuencias de forma clara.
 */
void printFrequencies(
    const CharacterFrequencyCounter::FrequencyMap& frequencies) {

    std::cout << "{\n";

    for (const auto& [character, frequency] : frequencies) {
        // Tratamiento especial para caracteres poco visibles.
        switch (character) {
            case ' ':
                std::cout << "  [espacio]";
                break;
            case '\t':
                std::cout << "  [tabulador]";
                break;
            default:
                std::cout << "  '" << character << "'";
                break;
        }

        std::cout << ": " << frequency << '\n';
    }

    std::cout << "}\n";
}

int main() {
    try {
        std::cout << "Introduzca una cadena: ";

        std::string input;

        // getline permite procesar también espacios.
        if (!std::getline(std::cin, input)) {
            throw std::runtime_error("No se pudo leer la entrada.");
        }

        const CharacterFrequencyCounter counter;
        const auto frequencies = counter.count(input);

        printFrequencies(frequencies);

        return 0;
    } catch (const std::exception& exception) {
        std::cerr << "Error: " << exception.what() << '\n';
        return 1;
    } catch (...) {
        std::cerr << "Error inesperado.\n";
        return 1;
    }
}

//problema 3
#include <cctype>
#include <iostream>
#include <stdexcept>
#include <string>
#include <string_view>

/**
 * @brief Encapsula la lógica para localizar la palabra más larga de una frase.
 *
 * Se considera palabra cualquier secuencia continua de caracteres que no sean
 * espacios ni signos de puntuación. En caso de empate, se conserva la primera.
 */
class AnalizadorFrase final {
public:
    /**
     * @brief Obtiene la palabra de mayor longitud ignorando la puntuación.
     * @param frase Texto que se desea analizar.
     * @return La palabra más larga encontrada.
     * @throws std::invalid_argument Si no existe ninguna palabra válida.
     */
    [[nodiscard]]
    static std::string palabraMasLarga(std::string_view frase) {
        std::string_view mejorPalabra;
        std::size_t inicio = 0;
        bool enPalabra = false;

        for (std::size_t i = 0; i <= frase.size(); ++i) {
            // El final de la cadena también actúa como separador.
            const bool esSeparador =
                i == frase.size() || !esCaracterDePalabra(frase[i]);

            if (!esSeparador && !enPalabra) {
                inicio = i;
                enPalabra = true;
            } else if (esSeparador && enPalabra) {
                const auto palabra = frase.substr(inicio, i - inicio);

                // '>' mantiene la primera palabra en caso de empate.
                if (palabra.size() > mejorPalabra.size()) {
                    mejorPalabra = palabra;
                }

                enPalabra = false;
            }
        }

        if (mejorPalabra.empty()) {
            throw std::invalid_argument(
                "La frase no contiene ninguna palabra valida."
            );
        }

        return std::string{mejorPalabra};
    }

private:
    /**
     * @brief Determina si un carácter pertenece a una palabra.
     *
     * std::isspace y std::ispunct requieren un valor representable como
     * unsigned char (o EOF) para evitar comportamiento indefinido.
     */
    [[nodiscard]]
    static bool esCaracterDePalabra(char caracter) noexcept {
        const auto c = static_cast<unsigned char>(caracter);
        return std::isspace(c) == 0 && std::ispunct(c) == 0;
    }
};

int main() {
    try {
        std::cout << "Introduce una frase: ";

        std::string frase;
        if (!std::getline(std::cin, frase)) {
            throw std::runtime_error("No fue posible leer la entrada.");
        }

        const std::string resultado = AnalizadorFrase::palabraMasLarga(frase);
        std::cout << "Palabra mas larga: " << resultado << '\n';

        return 0;
    } catch (const std::exception& ex) {
        std::cerr << "Error: " << ex.what() << '\n';
        return 1;
    }
}
#include <cctype>
#include <iostream>
#include <stdexcept>
#include <string>
#include <string_view>

/**
 * @brief Encapsula la lógica para localizar la palabra más larga de una frase.
 *
 * Se considera palabra cualquier secuencia continua de caracteres que no sean
 * espacios ni signos de puntuación. En caso de empate, se conserva la primera.
 */
class AnalizadorFrase final {
public:
    /**
     * @brief Obtiene la palabra de mayor longitud ignorando la puntuación.
     * @param frase Texto que se desea analizar.
     * @return La palabra más larga encontrada.
     * @throws std::invalid_argument Si no existe ninguna palabra válida.
     */
    [[nodiscard]]
    static std::string palabraMasLarga(std::string_view frase) {
        std::string_view mejorPalabra;
        std::size_t inicio = 0;
        bool enPalabra = false;

        for (std::size_t i = 0; i <= frase.size(); ++i) {
            // El final de la cadena también actúa como separador.
            const bool esSeparador =
                i == frase.size() || !esCaracterDePalabra(frase[i]);

            if (!esSeparador && !enPalabra) {
                inicio = i;
                enPalabra = true;
            } else if (esSeparador && enPalabra) {
                const auto palabra = frase.substr(inicio, i - inicio);

                // '>' mantiene la primera palabra en caso de empate.
                if (palabra.size() > mejorPalabra.size()) {
                    mejorPalabra = palabra;
                }

                enPalabra = false;
            }
        }

        if (mejorPalabra.empty()) {
            throw std::invalid_argument(
                "La frase no contiene ninguna palabra valida."
            );
        }

        return std::string{mejorPalabra};
    }

private:
    /**
     * @brief Determina si un carácter pertenece a una palabra.
     *
     * std::isspace y std::ispunct requieren un valor representable como
     * unsigned char (o EOF) para evitar comportamiento indefinido.
     */
    [[nodiscard]]
    static bool esCaracterDePalabra(char caracter) noexcept {
        const auto c = static_cast<unsigned char>(caracter);
        return std::isspace(c) == 0 && std::ispunct(c) == 0;
    }
};

int main() {
    try {
        std::cout << "Introduce una frase: ";

        std::string frase;
        if (!std::getline(std::cin, frase)) {
            throw std::runtime_error("No fue posible leer la entrada.");
        }

        const std::string resultado = AnalizadorFrase::palabraMasLarga(frase);
        std::cout << "Palabra mas larga: " << resultado << '\n';

        return 0;
    } catch (const std::exception& ex) {
        std::cerr << "Error: " << ex.what() << '\n';
        return 1;
    }
}

//problema 4
Problema4 c++    v 5.5 prompt experto
#include <cmath>
#include <iostream>
#include <limits>
#include <stdexcept>
#include <string>

class PrimeChecker final {
public:
    // La clase no mantiene estado: no debe ser instanciada.
    PrimeChecker() = delete;

    /**
     * Determina si un entero es primo.
     *
     * Un número primo es un entero mayor que 1 cuyos únicos
     * divisores positivos son 1 y él mismo.
     *
     * Complejidad temporal: O(sqrt(n))
     * Complejidad espacial: O(1)
     */
    [[nodiscard]] static bool isPrime(long long number) noexcept {
        if (number < 2) {
            return false;
        }

        if (number == 2 || number == 3) {
            return true;
        }

        // Elimina inmediatamente múltiplos de 2 y 3.
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        /*
         * Todo primo mayor que 3 tiene la forma 6k ± 1.
         *
         * "divisor <= number / divisor" equivale conceptualmente a
         * "divisor * divisor <= number", pero evita un posible overflow
         * en la multiplicación.
         */
        for (long long divisor = 5;
             divisor <= number / divisor;
             divisor += 6) {

            if (number % divisor == 0 ||
                number % (divisor + 2) == 0) {
                return false;
            }
        }

        return true;
    }
};

/**
 * Convierte la entrada completa a long long y rechaza datos
 * mal formados o valores fuera de rango.
 */
[[nodiscard]] long long parseInteger(const std::string& input) {
    if (input.empty()) {
        throw std::invalid_argument("La entrada no puede estar vacía.");
    }

    std::size_t processedCharacters = 0;
    const long long value = std::stoll(input, &processedCharacters);

    if (processedCharacters != input.size()) {
        throw std::invalid_argument(
            "La entrada debe contener únicamente un número entero."
        );
    }

    return value;
}

int main() {
    try {
        std::cout << "Introduzca un número entero: ";

        std::string input;
        if (!std::getline(std::cin, input)) {
            throw std::runtime_error("No se pudo leer la entrada.");
        }

        const long long number = parseInteger(input);

        std::cout << number
                  << (PrimeChecker::isPrime(number)
                          ? " es primo.\n"
                          : " no es primo.\n");

        return 0;
    }
    catch (const std::invalid_argument& exception) {
        std::cerr << "Entrada inválida: " << exception.what() << '\n';
    }
    catch (const std::out_of_range&) {
        std::cerr << "Error: el número está fuera del rango de long long.\n";
    }
    catch (const std::exception& exception) {
        std::cerr << "Error: " << exception.what() << '\n';
    }

    return 1;
}
//Decisiones de diseño: PrimeChecker encapsula la lógica de dominio y no mantiene estado, mientras que el análisis de entrada queda separado de dicha responsabilidad. El algoritmo tiene complejidad O(√n) y utiliza candidatos 6k ± 1 para reducir divisiones innecesarias. La condición divisor <= number / divisor evita el overflow que podría producir divisor * divisor.
//isPrime es noexcept porque no necesita operaciones susceptibles de lanzar excepciones y [[nodiscard]] evita ignorar accidentalmente su resultado. La entrada se valida explícitamente mediante std::stoll, distinguiendo datos inválidos, valores fuera de rango y errores de E/S. Los enteros menores que 2 se consideran correctamente no primos por definición.

//problema 5
#include <algorithm>   // std::min, std::max
#include <cstddef>     // std::size_t
#include <optional>    // std::optional
#include <stdexcept>   // std::invalid_argument
#include <vector>

// Objeto de valor que encapsula el resultado.
// Evita devolver pares ambiguos donde no queda claro qué representa cada valor.
struct MinMax final {
    int minimo;
    int maximo;
};

class AnalizadorEnteros final {
public:
    /*
     * Calcula manualmente el mínimo y el máximo en una sola pasada.
     *
     * Complejidad temporal: O(n).
     * Complejidad espacial: O(1).
     *
     * std::optional permite representar de forma explícita que una
     * colección vacía no tiene mínimo ni máximo.
     */
    [[nodiscard]]
    static std::optional<MinMax> calcular(const std::vector<int>& valores) noexcept
    {
        if (valores.empty()) {
            return std::nullopt;
        }

        // Inicializar con el primer elemento evita valores centinela
        // y garantiza un comportamiento correcto para cualquier int.
        int minimo = valores.front();
        int maximo = valores.front();

        for (std::size_t i = 1; i < valores.size(); ++i) {
            const int valor = valores[i];

            if (valor < minimo) {
                minimo = valor;
            }

            if (valor > maximo) {
                maximo = valor;
            }
        }

        return MinMax{minimo, maximo};
    }

    /*
     * Sobrecarga para datos recibidos mediante puntero.
     *
     * Un puntero nulo con tamaño distinto de cero representa un contrato
     * inválido y se informa mediante una excepción.
     *
     * Un tamaño cero representa correctamente una colección vacía,
     * incluso cuando el puntero es nullptr.
     */
    [[nodiscard]]
    static std::optional<MinMax> calcular(const int* datos, std::size_t cantidad)
    {
        if (cantidad == 0) {
            return std::nullopt;
        }

        if (datos == nullptr) {
            throw std::invalid_argument(
                "El puntero de datos es nulo y la cantidad es mayor que cero."
            );
        }

        int minimo = datos[0];
        int maximo = datos[0];

        for (std::size_t i = 1; i < cantidad; ++i) {
            const int valor = datos[i];

            if (valor < minimo) {
                minimo = valor;
            }

            if (valor > maximo) {
                maximo = valor;
            }
        }

        return MinMax{minimo, maximo};
    }
};

//problema 6
#include <iostream>
#include <limits>
#include <stdexcept>
#include <string>

/**
 * Calculadora de factoriales.
 *
 * Se utiliza unsigned long long para ampliar el rango de valores positivos.
 * Debido al límite del tipo, solo pueden calcularse factoriales hasta 20!.
 * Las funciones validan tanto la entrada como posibles desbordamientos.
 */
class FactorialCalculator {
public:
    using ResultType = unsigned long long;

    /**
     * Calcula n! mediante un algoritmo iterativo.
     *
     * Complejidad temporal: O(n)
     * Complejidad espacial: O(1)
     *
     * @throws std::overflow_error si el resultado no puede representarse.
     */
    static ResultType iterative(unsigned int n) {
        validateInput(n);

        ResultType result = 1;

        for (unsigned int i = 2; i <= n; ++i) {
            // Verificación previa para evitar overflow en la multiplicación.
            if (result > std::numeric_limits<ResultType>::max() / i) {
                throw std::overflow_error(
                    "Overflow: el factorial excede el rango soportado."
                );
            }

            result *= i;
        }

        return result;
    }

    /**
     * Calcula n! mediante recursión.
     *
     * Complejidad temporal: O(n)
     * Complejidad espacial: O(n), debido a la pila de llamadas.
     *
     * @throws std::overflow_error si el resultado no puede representarse.
     */
    static ResultType recursive(unsigned int n) {
        validateInput(n);

        if (n <= 1) {
            return 1;
        }

        const ResultType partialResult = recursive(n - 1);

        // Validación previa a la multiplicación para evitar overflow.
        if (partialResult > std::numeric_limits<ResultType>::max() / n) {
            throw std::overflow_error(
                "Overflow: el factorial excede el rango soportado."
            );
        }

        return partialResult * n;
    }

private:
    // Con unsigned long long, 20! cabe en los 64 bits habituales;
    // 21! ya excede su capacidad.
    static constexpr unsigned int MAX_SUPPORTED_INPUT = 20;

    static void validateInput(unsigned int n) {
        if (n > MAX_SUPPORTED_INPUT) {
            throw std::out_of_range(
                "El valor debe estar comprendido entre 0 y 20."
            );
        }
    }
};

int main() {
    try {
        std::cout << "Introduce un entero no negativo (0-20): ";

        long long input{};

        // Validación rigurosa del formato de entrada.
        if (!(std::cin >> input)) {
            throw std::invalid_argument(
                "La entrada debe ser un numero entero."
            );
        }

        if (input < 0) {
            throw std::invalid_argument(
                "El factorial no esta definido para enteros negativos."
            );
        }

        const auto n = static_cast<unsigned int>(input);

        // Demostración independiente de ambas implementaciones.
        const auto iterativeResult = FactorialCalculator::iterative(n);
        const auto recursiveResult = FactorialCalculator::recursive(n);

        std::cout << n << "! (iterativo) = "
                  << iterativeResult << '\n';

        std::cout << n << "! (recursivo) = "
                  << recursiveResult << '\n';

        // Comprobación defensiva para demostrar que ambos algoritmos
        // producen exactamente el mismo resultado.
        if (iterativeResult != recursiveResult) {
            throw std::logic_error(
                "Las implementaciones produjeron resultados inconsistentes."
            );
        }

        return 0;
    }
    catch (const std::invalid_argument& ex) {
        std::cerr << "Error de entrada: " << ex.what() << '\n';
        return 1;
    }
    catch (const std::out_of_range& ex) {
        std::cerr << "Error de rango: " << ex.what() << '\n';
        return 2;
    }
    catch (const std::overflow_error& ex) {
        std::cerr << "Error aritmetico: " << ex.what() << '\n';
        return 3;
    }
    catch (const std::exception& ex) {
        std::cerr << "Error inesperado: " << ex.what() << '\n';
        return 4;
    }
}

//problema 7
#include <iostream>
#include <stdexcept>

/**
 * Excepción específica para representar un intento de división por cero.
 * Heredar de std::domain_error expresa que el problema pertenece
 * al dominio válido de la operación matemática.
 */
class DivisionPorCeroException final : public std::domain_error {
public:
    DivisionPorCeroException()
        : std::domain_error("No se puede realizar una division por cero.") {}
};

/**
 * Calculadora básica.
 *
 * La clase encapsula exclusivamente las operaciones aritméticas y no
 * gestiona entrada/salida, respetando el principio de responsabilidad única.
 */
class Calculadora final {
public:
    [[nodiscard]] double sumar(double a, double b) const noexcept {
        return a + b;
    }

    [[nodiscard]] double restar(double a, double b) const noexcept {
        return a - b;
    }

    [[nodiscard]] double multiplicar(double a, double b) const noexcept {
        return a * b;
    }

    [[nodiscard]] double dividir(double dividendo, double divisor) const {
        // Para esta calculadora, +0.0 y -0.0 son divisores inválidos.
        if (divisor == 0.0) {
            throw DivisionPorCeroException{};
        }

        return dividendo / divisor;
    }
};

/**
 * Punto de entrada de la aplicación.
 * Separa la interacción con el usuario de la lógica de negocio.
 */
int main() {
    Calculadora calculadora;

    double operando1{};
    double operando2{};
    char operador{};

    std::cout << "Introduzca una expresion (ejemplo: 10 / 2): ";

    // Validación explícita de la entrada para evitar operar con datos inválidos.
    if (!(std::cin >> operando1 >> operador >> operando2)) {
        std::cerr << "Error: entrada invalida.\n";
        return 1;
    }

    try {
        double resultado{};

        switch (operador) {
            case '+':
                resultado = calculadora.sumar(operando1, operando2);
                break;

            case '-':
                resultado = calculadora.restar(operando1, operando2);
                break;

            case '*':
                resultado = calculadora.multiplicar(operando1, operando2);
                break;

            case '/':
                resultado = calculadora.dividir(operando1, operando2);
                break;

            default:
                std::cerr << "Error: operador no soportado.\n";
                return 1;
        }

        std::cout << "Resultado: " << resultado << '\n';
    }
    catch (const DivisionPorCeroException& ex) {
        // Tratamiento específico de una condición conocida del dominio.
        std::cerr << "Error aritmetico: " << ex.what() << '\n';
        return 2;
    }
    catch (const std::exception& ex) {
        // Frontera de seguridad ante otras excepciones estándar inesperadas.
        std::cerr << "Error inesperado: " << ex.what() << '\n';
        return 3;
    }

    return 0;
}

//problema 8
#include <iostream>
#include <memory>
#include <stdexcept>
#include <string>
#include <unordered_map>
#include <utility>

// Excepción específica del dominio.
// Permite distinguir un error de préstamo de otros errores del sistema.
class LibroYaPrestadoException final : public std::logic_error {
public:
    explicit LibroYaPrestadoException(const std::string& titulo)
        : std::logic_error("El libro '" + titulo + "' ya esta prestado.") {}
};

// Entidad que representa un libro.
// Su estado está encapsulado y solo puede modificarse mediante
// operaciones válidas del dominio.
class Libro final {
public:
    Libro(std::string isbn, std::string titulo, std::string autor)
        : isbn_(std::move(isbn)),
          titulo_(std::move(titulo)),
          autor_(std::move(autor)) {
        if (isbn_.empty()) {
            throw std::invalid_argument("El ISBN no puede estar vacio.");
        }
        if (titulo_.empty()) {
            throw std::invalid_argument("El titulo no puede estar vacio.");
        }
        if (autor_.empty()) {
            throw std::invalid_argument("El autor no puede estar vacio.");
        }
    }

    [[nodiscard]] const std::string& isbn() const noexcept {
        return isbn_;
    }

    [[nodiscard]] const std::string& titulo() const noexcept {
        return titulo_;
    }

    [[nodiscard]] const std::string& autor() const noexcept {
        return autor_;
    }

    [[nodiscard]] bool estaPrestado() const noexcept {
        return prestado_;
    }

    // Se evita exponer un setter para preservar las invariantes de la clase.
    void prestar() {
        if (prestado_) {
            throw LibroYaPrestadoException(titulo_);
        }

        prestado_ = true;
    }

    void devolver() noexcept {
        prestado_ = false;
    }

private:
    std::string isbn_;
    std::string titulo_;
    std::string autor_;
    bool prestado_{false};
};

// Gestiona la colección de libros.
// El ISBN actúa como identificador único y permite búsquedas O(1) promedio.
class Biblioteca final {
public:
    void agregarLibro(std::unique_ptr<Libro> libro) {
        if (!libro) {
            throw std::invalid_argument("No se puede agregar un libro nulo.");
        }

        const std::string isbn = libro->isbn();

        const auto [_, insertado] =
            libros_.try_emplace(isbn, std::move(libro));

        if (!insertado) {
            throw std::invalid_argument(
                "Ya existe un libro con ISBN '" + isbn + "'."
            );
        }
    }

    void prestarLibro(const std::string& isbn) {
        buscarLibro(isbn).prestar();
    }

    void devolverLibro(const std::string& isbn) {
        buscarLibro(isbn).devolver();
    }

    [[nodiscard]] const Libro& obtenerLibro(const std::string& isbn) const {
        return buscarLibro(isbn);
    }

private:
    Libro& buscarLibro(const std::string& isbn) {
        const auto it = libros_.find(isbn);

        if (it == libros_.end()) {
            throw std::out_of_range(
                "No existe ningun libro con ISBN '" + isbn + "'."
            );
        }

        return *it->second;
    }

    const Libro& buscarLibro(const std::string& isbn) const {
        const auto it = libros_.find(isbn);

        if (it == libros_.end()) {
            throw std::out_of_range(
                "No existe ningun libro con ISBN '" + isbn + "'."
            );
        }

        return *it->second;
    }

    // Biblioteca posee exclusivamente los libros (RAII).
    std::unordered_map<std::string, std::unique_ptr<Libro>> libros_;
};

int main() {
    try {
        Biblioteca biblioteca;

        biblioteca.agregarLibro(std::make_unique<Libro>(
            "978-0132350884",
            "Clean Code",
            "Robert C. Martin"
        ));

        biblioteca.agregarLibro(std::make_unique<Libro>(
            "978-0201633610",
            "Design Patterns",
            "GoF"
        ));

        biblioteca.prestarLibro("978-0132350884");

        // Segundo préstamo: lanza LibroYaPrestadoException.
        biblioteca.prestarLibro("978-0132350884");
    }
    catch (const LibroYaPrestadoException& e) {
        std::cerr << "Error de prestamo: " << e.what() << '\n';
    }
    catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << '\n';
    }

    return 0;
}
