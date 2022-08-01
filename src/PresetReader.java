import java.util.HashMap;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PresetReader {

    /** This method gets the hashmap for a preset maze layout which is stored in a text file
     * 
     * @param presetNumber the number of the text file
     * @return returns the hashmap generated
     * 
     */
    public static HashMap<Dimension,Tile> getPreset(int presetNumber)
    {
        HashMap<Dimension,Tile> presetMap = new HashMap<Dimension,Tile>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("preset"+presetNumber+".txt"));
            String line = reader.readLine();
            
            for(int i = 0; i<=App.tileRadius;i++)
        {
            for (int ii=0;ii<App.tileRadius;ii++)
            {
                Dimension dimension = new Dimension(i,ii);
                Tile tile = new Tile(dimension,false);
                switch(line) //determines the type of the written tile
                {
                    case "nsolid":
                    presetMap.put(dimension,tile);
                    break;
                    case "solid":
                    tile.setSolid();
                    presetMap.put(dimension,tile);
                    break;
                    case "finish":
                    tile.setFinish();
                    presetMap.put(dimension,tile);
                    break;
                    case "start":
                    tile.setStart();
                    presetMap.put(dimension,tile);
                    break;
                }
                presetMap.put(dimension, tile);
                line = reader.readLine();
            }
        }
            reader.close();
        } catch (FileNotFoundException e) {} catch (IOException e){}

        return presetMap;
    }
    public static void savePreset(int presetNumber, HashMap<Dimension,Tile> tileMap)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("preset"+presetNumber+".txt"));
            for(int i = 0; i<=App.tileRadius;i++)
        {
            for (int ii=0;ii<App.tileRadius;ii++)
            {
                Dimension dimension = new Dimension(i,ii);
                Tile tile = tileMap.get(dimension);
                switch(tile.getState()) //determines the type of the written tile
                {
                    case NONSOLID:
                    writer.write("nsolid");
                    break;
                    case SOLID:
                    writer.write("solid");
                    break;
                    case FINISH:
                    writer.write("finish");
                    break;
                    case START:
                    writer.write("start");
                    break;
                }
                writer.newLine();
            }
            
        }
        writer.close();

        } catch (IOException e) {}
    }
    
}
