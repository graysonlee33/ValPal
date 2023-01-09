import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
//duelist == 0;
//controller = 1;
//sentinel = 2;
//initiator = 3;
public class Main
{
    public static void main(String[] args) throws FileNotFoundException {
        String file = "ProAgentPercentages.txt";
        Scanner console = new Scanner(new File(file));
        String rawData = "";
        String temp = "";
        ArrayList<Map> maps = new ArrayList<>();
        for (int i = 0; i < 7; i++)
        {
            rawData = "";
            for (int j = 0; j < 21; j++)
            {
                temp = console.nextLine();
                rawData+=temp + "\n";
            }
            maps.add(new Map(rawData, i));
            console.nextLine();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("AGENT SELECT: MAP >>> ");
        String mapName = scanner.nextLine();
        System.out.println("---------------------------");
        int mapValue = 0;
        boolean hasMap = false;
        for (int e = 0; e < maps.size(); e++)
        {
            if (mapName.equalsIgnoreCase(maps.get(e).getMapName()))
            {
                mapValue = maps.get(e).getMapValue();
                hasMap = true;
                break;
            }
        }
        ArrayList<Agent> list = new ArrayList<>();
        if (!hasMap)
        {
            list.add(new Agent("Astra", 78, 423, 0, 1));
            list.add(new Agent("Breach", 243, 214, 0, 3));
            list.add(new Agent("Brimstone", 432, 87, 0, 1));
            list.add(new Agent("Chamber", 232, 456, 0, 2));
            list.add(new Agent("Cypher", 389, 405, 0, 2));
            list.add(new Agent("Fade", 189, 87, 0, 3));
            list.add(new Agent("Harbor", 223, 321, 0, 1));
            list.add(new Agent("Jett", 234, 567, 0, 0));
            list.add(new Agent("Kay/o", 136, 402, 0, 3));
            list.add(new Agent("Killjoy", 121, 76,0, 2));
            list.add(new Agent("Neon", 223, 124, 0, 0));
            list.add(new Agent("Omen", 267, 387, 0, 1));
            list.add(new Agent("Phoenix", 356, 122, 0, 0));
            list.add(new Agent("Raze", 336, 110, 0, 0));
            list.add(new Agent("Reyna", 323, 405, 0, 0));
            list.add(new Agent("Sage", 125, 416, 0, 2));
            list.add(new Agent("Skye", 145, 416, 0, 3));
            list.add(new Agent("Sova", 392, 145, 0, 3));
            list.add(new Agent("Viper", 145, 87, 0, 1));
            list.add(new Agent("Yoru", 123, 234, 0, 0));
            agentRec(list);
        }
        else
        {
            list.add(new Agent("Astra", 78, 423,  maps.get(mapValue).getPickRate(0), 1));
            list.add(new Agent("Breach", 243, 214,  maps.get(mapValue).getPickRate(1), 3));
            list.add(new Agent("Brimstone", 432, 87, maps.get(mapValue).getPickRate(2), 1));
            list.add(new Agent("Chamber", 232, 456,  maps.get(mapValue).getPickRate(3), 2));
            list.add(new Agent("Cypher", 389, 405,  maps.get(mapValue).getPickRate(4), 2));
            list.add(new Agent("Fade", 189, 87,  maps.get(mapValue).getPickRate(5), 3));
            list.add(new Agent("Harbor", 223, 321,  maps.get(mapValue).getPickRate(6), 1));
            list.add(new Agent("Jett", 234, 567,  maps.get(mapValue).getPickRate(7), 0));
            list.add(new Agent("Kay/o", 136, 402, maps.get(mapValue).getPickRate(8), 3));
            list.add(new Agent("Killjoy", 121, 76, maps.get(mapValue).getPickRate(9), 2));
            list.add(new Agent("Neon", 223, 124, maps.get(mapValue).getPickRate(10), 0));
            list.add(new Agent("Omen", 267, 387, maps.get(mapValue).getPickRate(11), 1));
            list.add(new Agent("Phoenix", 356, 122, maps.get(mapValue).getPickRate(12), 0));
            list.add(new Agent("Raze", 336, 110, maps.get(mapValue).getPickRate(13), 0));
            list.add(new Agent("Reyna", 323, 405, maps.get(mapValue).getPickRate(14), 0));
            list.add(new Agent("Sage", 125, 416, maps.get(mapValue).getPickRate(15), 2));
            list.add(new Agent("Skye", 145, 416, maps.get(mapValue).getPickRate(16), 3));
            list.add(new Agent("Sova", 392, 145, maps.get(mapValue).getPickRate(17), 3));
            list.add(new Agent("Viper", 145, 87, maps.get(mapValue).getPickRate(18), 1));
            list.add(new Agent("Yoru", 123, 234, maps.get(mapValue).getPickRate(19), 0));
            agentRec(list);
        }
    }

    public static void agentRec(ArrayList<Agent> list) {
        ArrayList<Agent> duelist = new ArrayList<>();
        ArrayList<Agent> control = new ArrayList<>();
        ArrayList<Agent> sent = new ArrayList<>();
        ArrayList<Agent> init = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            if (counter == 0) //duelist
            {
                for (int x = 0; x < list.size(); x++) {
                    if (list.get(x).getType() == 0) //if agent is duelist
                    {
                        duelist.add(list.get(x));
                    }
                }
                for (int j = 1; j < duelist.size(); j++) {
                    Agent current = duelist.get(j);
                    int f = j - 1;
                    while ((f > -1) && ((duelist.get(f).compareTo(current)) == 1)) {
                        duelist.set(f + 1, duelist.get(f));
                        f--;
                    }
                    duelist.set(f + 1, current);
                }
                System.out.println("Recommended Duelist Picks: ");
                System.out.println("1. " + duelist.get(duelist.size() - 1).getAgentName() + " - Score: " + duelist.get(duelist.size() - 1).getScore());
                System.out.println("2. " + duelist.get(duelist.size() - 2).getAgentName() + " - Score: " + duelist.get(duelist.size() - 2).getScore());
                System.out.println("3. " + duelist.get(duelist.size() - 3).getAgentName() + " - Score: " + duelist.get(duelist.size() - 3).getScore());
            } else if (counter == 1) //controller
            {
                for (int x = 0; x < list.size(); x++) {
                    if (list.get(x).getType() == 1) //if agent is controller
                    {
                        control.add(list.get(x));
                    }
                }
                for (int j = 1; j < control.size(); j++) {
                    Agent current = control.get(j);
                    int f = j - 1;
                    while ((f > -1) && ((control.get(f).compareTo(current)) == 1)) {
                        control.set(f + 1, control.get(f));
                        f--;
                    }
                    control.set(f + 1, current);
                }
                System.out.println("Recommended Controller Picks: ");
                System.out.println("1. " + control.get(control.size() - 1).getAgentName() + " - Score: " + control.get(control.size() - 1).getScore());
                System.out.println("2. " + control.get(control.size() - 2).getAgentName() + " - Score: " + control.get(control.size() - 2).getScore());
                System.out.println("3. " + control.get(control.size() - 3).getAgentName() + " - Score: " + control.get(control.size() - 3).getScore());
            } else if (counter == 2) //sentinel
            {
                for (int x = 0; x < list.size(); x++) {
                    if (list.get(x).getType() == 2) //if agent is sentinel
                    {
                        sent.add(list.get(x));
                    }
                }
                for (int j = 1; j < sent.size(); j++) {
                    Agent current = sent.get(j);
                    int f = j - 1;
                    while ((f > -1) && ((sent.get(f).compareTo(current)) == 1)) {
                        sent.set(f + 1, sent.get(f));
                        f--;
                    }
                    sent.set(f + 1, current);
                }
                System.out.println("Recommended Sentinel Picks: ");
                System.out.println("1. " + sent.get(sent.size() - 1).getAgentName() + " - Score: " + sent.get(sent.size() - 1).getScore());
                System.out.println("2. " + sent.get(sent.size() - 2).getAgentName() + " - Score: " + sent.get(sent.size() - 2).getScore());
                System.out.println("3. " + sent.get(sent.size() - 3).getAgentName() + " - Score: " + sent.get(sent.size() - 3).getScore());
            } else if (counter == 3) //initiator
            {
                for (int x = 0; x < list.size(); x++) {
                    if (list.get(x).getType() == 3) //if agent is intitator
                    {
                        init.add(list.get(x));
                    }
                }
                for (int j = 1; j < init.size(); j++) {
                    Agent current = init.get(j);
                    int f = j - 1;
                    while ((f > -1) && ((init.get(f).compareTo(current)) == 1)) {
                        init.set(f + 1, init.get(f));
                        f--;
                    }
                    init.set(f + 1, current);
                }
                System.out.println("Recommended Initiator Picks: ");
                System.out.println("1. " + init.get(init.size() - 1).getAgentName() + " - Score: " + init.get(init.size() - 1).getScore());
                System.out.println("2. " + init.get(init.size() - 2).getAgentName() + " - Score: " + init.get(init.size() - 2).getScore());
                System.out.println("3. " + init.get(init.size() - 3).getAgentName() + " - Score: " + init.get(init.size() - 3).getScore());
            }
            System.out.println();
            counter++;
        }
    }
}