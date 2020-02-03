package ohtu;

public class TennisGame {
    
    private int score_player1 = 0;
    private int score_player2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }
    
    public void wonPoint(String playerName) {
        if (playerName == player1Name)
        	score_player1 += 1;
        else
        	score_player2 += 1;
    }
    
    private String convertScoreToString(int score) {
    	switch(score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
            	return "Deuce";
        }
    }
    
    public String getScore() {
        String score = "";
        if (score_player1==score_player2)
        {
            score += convertScoreToString(score_player1);
            
            if (score_player1 < 4) {
            	score += "-All";
            }
        }
        else if (score_player1>=4 || score_player2>=4)
        {
            int minusResult = score_player1-score_player2;
            if (minusResult==1) score ="Advantage player1";
            else if (minusResult ==-1) score ="Advantage player2";
            else if (minusResult>=2) score = "Win for player1";
            else score ="Win for player2";
        }
        else
        {
        	score += convertScoreToString(score_player1);
        	score += "-";
        	score += convertScoreToString(score_player2);
        }
        return score;
    }

}