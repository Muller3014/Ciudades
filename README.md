**Preparando el archivo README.md**

El usuario me pide poner todo en markdown, y aunque la respuesta anterior ya era casi todo markdown, parece que quiere que sea una respuesta en formato README.md. Entonces, lo mejor es proporcionar directamente el contenido sin las envolturas de código. Sin los bloques de código envolventes, mantendré solo el contenido markdown, tal como un archivo típico README.md.


Pensó durante 10 segundos


# Gestor de Ciudades

Una aplicación Java sencilla para gestionar un conjunto de ciudades, permitiendo:

* **Añadir** ciudades.
* **Listar** ciudades ordenadas por número de habitantes.
* **Buscar** ciudades por nombre o país.
* **Salir** de la aplicación.

Utiliza un `HashSet<Ciudad>` para almacenar ciudades únicas, garantizando la unicidad mediante la correcta implementación de los métodos `equals()` y `hashCode()` en la clase `Ciudad`.


## Compilar y ejecutar

Desde la raíz del proyecto:

```bash
# Clonar el repositorio (si procede)
git clone <url-del-repo>
cd Ciudades

# Compilar con Maven
mvn compile

# Ejecutar directamente con Maven
mvn exec:java -Dexec.mainClass="com.problema.ciudades.CiudadApp"

# O empaquetar y ejecutar con java
mvn package
java -cp target/classes com.problema.ciudades.CiudadApp
```

---

## Estructura del proyecto

```
Ciudades/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/problema/ciudades/
                ├── Ciudad.java
                └── CiudadApp.java
```

---

## Clase `Ciudad`

Modelo que representa una ciudad con tres atributos:

* `String nombre`
* `String pais`
* `int habitantes`

```java
public class Ciudad {
    private String nombre;
    private String pais;
    private int habitantes;

    public Ciudad(String nombre, String pais, int habitantes) {
        this.nombre = nombre;
        this.pais = pais;
        this.habitantes = habitantes;
    }

    // getters...
}
```

### `equals()` y `hashCode()`

Para garantizar que dos instancias de `Ciudad` con el **mismo** nombre y país (ignorando mayúsculas) se consideren iguales, se ha sobrescrito:

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Ciudad)) return false;
    Ciudad ciudad = (Ciudad) o;
    return nombre.equalsIgnoreCase(ciudad.nombre)
           && pais.equalsIgnoreCase(ciudad.pais);
}

@Override
public int hashCode() {
    // Convertimos nombre y país a minúsculas para que coincida con equals()
    return Objects.hash(nombre.toLowerCase(), pais.toLowerCase());
}
```

* **`equals()`**

  1. Comprueba si es la misma referencia (`this == o`).
  2. Verifica que `o` sea instancia de `Ciudad`.
  3. Compara `nombre` y `pais` con `equalsIgnoreCase()`.

* **`hashCode()`**
  Calcula un código hash a partir de `nombre.toLowerCase()` y `pais.toLowerCase()`, asegurando coherencia con `equals()`.

Esta implementación permite usar `HashSet<Ciudad>` para evitar duplicados “visuales” (por nombre y país), independientemente de mayúsculas/minúsculas.

---

## Clase `CiudadApp`

Contiene el punto de entrada `main()` y gestiona un menú interactivo:

```java
public class CiudadApp {
    public static void main(String[] args) {
        Set<Ciudad> ciudades = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        int opcion;

        while (true) {
            System.out.println("\n--- Gestor de Ciudades ---");
            System.out.println("1. Añadir ciudad");
            System.out.println("2. Listar ciudades (por habitantes)");
            System.out.println("3. Buscar ciudades");
            System.out.println("4. Terminar y salir");
            System.out.print("Elige una opción: ");
            // … lógica de lectura y switch …
        }
    }

    private static void addCity(Set<Ciudad> ciudades, Scanner sc) { /* … */ }

    private static void listarCiudades(Set<Ciudad> ciudades) { /* … */ }

    private static void buscarCiudades(Set<Ciudad> ciudades, Scanner sc) { /* … */ }
}
```

* **`addCity(...)`**
  Pide nombre, país y habitantes, crea un objeto `Ciudad` y lo añade al `Set`. Si ya existe (según `equals`), muestra `"¡La ciudad ya existe!"`.

* **`listarCiudades(...)`**
  Convierte el `Set` en una `List`, ordena por número de habitantes y muestra cada ciudad con `toString()`.

* **`buscarCiudades(...)`**
  Filtra las ciudades cuyo nombre o país contenga el término buscado y las imprime.

---

## Uso de la aplicación

1. **Añadir ciudad**

   * Si se añade una ciudad duplicada (mismo nombre y país, ignorando mayúsculas), el `HashSet` rechaza la inserción y se informa al usuario.

2. **Listar ciudades**

   * Muestra el listado en orden ascendente según número de habitantes.

3. **Buscar ciudades**

   * Permite buscar por fragmento de nombre o país.

4. **Terminar**

   * Cierra el `Scanner` y finaliza el programa.

---
