package de.coolepizza.gameapi.api.scoreboard;

import de.coolepizza.gameapi.api.GameExeption;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardUtils {
    private static HashMap<Integer , String> defaults = new HashMap<>();
    public static void setCurrentScoreboard(Player p , String p2){
        Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
        s.registerNewObjective("m" , "dummy" , p2);
        s.getObjective("m").setDisplaySlot(DisplaySlot.SIDEBAR);
        for (Map.Entry<Integer, String> integerStringEntry : defaults.entrySet()) {
            setScore(integerStringEntry.getKey() , integerStringEntry.getValue() , s);
        }
        p.setScoreboard(s);
    }
    public static void insert(Integer s  ,String s2){
        defaults.put(s , s2);
        Bukkit.getOnlinePlayers().forEach(player -> {
            setScore(s , s2 , player.getScoreboard());
        });
    }
    public static void setScore(int i, String s2 , Scoreboard s){
        if (i > 14){
            try {
                throw  new GameExeption("Scores can maximum be 14");
            } catch (GameExeption gameExeption) {
                gameExeption.printStackTrace();
            }
        }
        if (s.getTeam("t" + i) == null){
            Team t  = s.registerNewTeam("t" + i);
            t.addEntry(getChatColor(i) + "" + getChatColor(i));
            s.getObjective("m").getScore(getChatColor(i) + "" + getChatColor(i)).setScore(i);
        }
        s.getTeam("t" + i).setPrefix(s2);
    }
    private static ChatColor getChatColor(int s){
        int i = 0;
        for (ChatColor value : ChatColor.values()) {
            if (i ==s){
                return value;
            }
            i++;
        }
        return ChatColor.GOLD;
    }
}
