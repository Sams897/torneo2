package model;

public class OpenAddressingHashTable { 
    private static final int TABLE_SIZE = 128; // Tamaño de la tabla hash
    private Torneo[] table; // Este arreglo representa la tabla hash

    public OpenAddressingHashTable() {
        table = new Torneo[TABLE_SIZE];
    }

    public void insert(int key, Torneo value) { // Insertar un valor con una clave
        int hash = hashFunction(key);
        int originalHash = hash; // Guardar el hash original para detectar bucles

        while (table[hash] != null && table[hash].hashCode() != value.hashCode()) {
            hash = (hash + 1) % TABLE_SIZE;
            if (hash == originalHash) { // Si hemos dado una vuelta completa, la tabla está llena
                throw new RuntimeException("La tabla hash está llena");
            }
        }
        table[hash] = value;
    }

    public Torneo search(int key) {
        int hash = hashFunction(key);
        int originalHash = hash; // Guardar el hash original para detectar bucles

        while (table[hash] != null && !generateCombinedKey(table[hash]).equals(Integer.toString(key))) {// Comparamos el resultado de la función hash
            hash = (hash + 1) % TABLE_SIZE;
            if (hash == originalHash) { // se realiza una revisión 
                return null;
            }
        }
        return table[hash];
    }

    private int hashFunction(int key) {
        return Math.abs(key) % TABLE_SIZE;
    }

    private String generateCombinedKey(Torneo value) {
        return Integer.toString(HashKeyGenerator.generateKey(
            value.getNameEquipoL().trim(),
            value.getNameEquipoV().trim(),
            value.getMejorJugador().trim()
        ));
    }
}
