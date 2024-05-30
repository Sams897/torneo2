package view;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL; 
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import model.Torneo;
import model.BinaryTree;
import model.OpenAddressingHashTable; 
import model.HashKeyGenerator;
import model.DataAnalysis;

public class index
{
    public static void main(String[] args)
    {
        try
        {
            URI uri = new URI("http://localhost:8286/api/torneo");
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if(responseCode != 200)
            {
                throw new RuntimeException("Error: " + responseCode);
            }
            else
            {
                String result = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                 String line;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                reader.close();

                System.out.println(result.toString());

                Type collectionType = new TypeToken<List<Torneo>>(){}.getType();
                List<Torneo> data =  new Gson().fromJson( result , collectionType);

                // Se instancia del árbol binario
                BinaryTree equipoBinaryTree = new BinaryTree();
                BinaryTree jugadorBinaryTree = new BinaryTree();



                  // Se instancia de la tabla hash
                  OpenAddressingHashTable equipoHashTable = new OpenAddressingHashTable();
                  OpenAddressingHashTable jugadorHashTable = new OpenAddressingHashTable();
  
                  // Insertar datos en el árbol binario y la tabla hash
                  for (Torneo item : data) {
                      int equipoKey = HashKeyGenerator.generateKey(item.getNameEquipoL(), item.getNameEquipoV());
                      int jugadorKey = HashKeyGenerator.generateKey(item.getMejorJugador());
  
                      System.out.println("Equipo Key: " + equipoKey + " for " + item.getNameEquipoL() + " vs " + item.getNameEquipoV());
                      System.out.println("Jugador Key: " + jugadorKey + " for " + item.getMejorJugador());
                      
                      equipoBinaryTree.insert(equipoKey, item);
                      jugadorBinaryTree.insert(jugadorKey, item);
  
                      equipoHashTable.insert(equipoKey, item);
                      jugadorHashTable.insert(jugadorKey, item);
                  }

                  // Ejemplo de búsqueda en el árbol binario
                int searchEquipoKey = HashKeyGenerator.generateKey("Bayern_Munich", "Real_Madrid");
                Torneo equipoFromTree = equipoBinaryTree.search(searchEquipoKey); 
                if (equipoFromTree != null) {
                    System.out.println("Equipo encontrado en el árbol binario: " + equipoFromTree.getNameEquipoL() + " vs " + equipoFromTree.getNameEquipoV());
                } else {
                    System.out.println("Equipo no encontrado en el árbol binario.");
                }

                // Ejemplo de búsqueda en la tabla hash
                Torneo equipoFromHashTable = equipoHashTable.search(searchEquipoKey); 
                if (equipoFromHashTable != null) {
                    System.out.println("Equipo encontrado en la tabla hash: " + equipoFromHashTable.getNameEquipoL() + " vs " + equipoFromHashTable.getNameEquipoV());
                } else {
                    System.out.println("Equipo no encontrado en la tabla hash.");
                }

                // Guardar información en archivo
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("torneo_inorder.txt"))) {
                    equipoBinaryTree.inOrder(writer);
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("torneo_preorder.txt"))) {
                    equipoBinaryTree.preOrder(writer);
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("torneo_postorder.txt"))) {
                    equipoBinaryTree.postOrder(writer);
                }

                // Realizar análisis de datos
                String equipoMasPuntos = DataAnalysis.equipoConMasPuntos(data);
                System.out.println("Equipo con más puntos: " + equipoMasPuntos);

                String[] top3EquiposGoles = DataAnalysis.top3EquiposConMasGoles(data);
                System.out.println("Top 3 equipos con más goles:");
                for (String equipo : top3EquiposGoles) {
                    System.out.println(equipo);
                }

                String[] top3MinutosGoles = DataAnalysis.top3MinutoConMasGoles(data);
                System.out.println("Top 3 minutos con más goles:");
                for (String minuto : top3MinutosGoles) {
                    System.out.println(minuto);
                  }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}