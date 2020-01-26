
package ohtu;

import java.util.Date;

public class Player implements Comparable<Player> {
    private String name;
    private int goals;
    private int assists;
    private int penalties;
    private String team;
    private String nationality;
    private Date birthdate;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return goals;
    }
    
    public void setAssists(int assists) {
    	this.assists = assists;
    }

    public int getAssists() {
    	return assists;
    }
    
    public void SetPenalties(int penalties) {
    	this.penalties = penalties;
    }
    
    public int getPenalties() {
    	return penalties;
    }
    
    public void setTeam(String team) {
    	this.team = team;
    }
    
    public String getTeam() {
    	return team;
    }
    
    public void setNationality(String nationality) {
    	this.nationality = nationality;
    }
    
    public String getNationality() {
    	return nationality;
    }
    
    public void setBirthdate(Date birthdate) {
    	this.birthdate = birthdate;
    }
    
    public Date getBirthdate() {
    	return birthdate;
    }
    
    @Override
    public String toString() {
        return name + "\t" + " team " + team + "\t" + " goals " + goals + "\t" + " assists " + assists + " = " + (goals + assists);
    }

	@Override
	public int compareTo(Player o) {
		return o.assists + o.goals - this.assists - this.goals;
	}
      
}
