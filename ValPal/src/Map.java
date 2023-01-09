import java.util.*;

public class Map {
    private String mapName;
    private double[] pickRate;

    private int mapValue;

    public Map(String rawData, int mapValue)
    {
        this.mapValue = mapValue;
        pickRate = new double[20];
        Scanner scan = new Scanner(rawData);
        mapName = scan.nextLine();
        int counter = 0;
        while (scan.hasNextLine())
        {
            String str = scan.nextLine();
            pickRate[counter] = Double.parseDouble(str.substring(str.indexOf(" ")));
            counter++;
        }
    }

    public double getPickRate(int value)
    {
        return pickRate[value];
    }

    public int getMapValue()
    {
        return mapValue;
    }

    public String getMapName()
    {
        return mapName;
    }
}
