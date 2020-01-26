/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        
        Arrays.sort(players);
        
        System.out.println("Players from Finland:");
        for (Player player : players) {
            if (player.getNationality().equalsIgnoreCase("FIN")) {
            	System.out.println(player);
            }
        }   
    }
  
}
