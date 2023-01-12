import java.util.*;
public class Agent {
    private String agentName;
    private int averageACS;
    private int lastACS;
    private double esportsPR;

    //private double playerPR;

    private int type;
    private int score;

    public Agent(String agentName, int averageACS, int lastACS, double esportsPR, int type)
    {
        this.agentName = agentName;
        this.averageACS = averageACS;
        this.lastACS = lastACS;
        this.esportsPR = esportsPR;
        //this.playerPR = playerPR;
        this.type = type;
        score = addScore();
    }

    public int addScore()
    {
        score += (averageACS/1000.0) * .3;
        score += (lastACS/1000.0) * .4;
        score += (esportsPR/100.0) * .3; //in percent
        //score+=playerPR * 1000;
        return score;
    }

    public String getAgentName()
    {
        return agentName;
    }

    public int getType()
    {
        return type;
    }

    public int getScore()
    {
        return score;
    }

    public int compareTo(Agent agent)
    {
        if (this.score > agent.getScore())
        {
            return 1;
        }
        else if (this.score == agent.getScore())
        {
            return 0;
        }
        else {
            return -1;
        }
    }
}
