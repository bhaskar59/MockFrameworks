package IplPlayoffs.IplPlayoffs;

public class Playoff 
{
 private PlayOffLogic playOffLogic;
 
 public void setPlayOffLogic(PlayOffLogic playOffLogic) {
	this.playOffLogic = playOffLogic;
}

public String fetchEliminator1(String team1,String team2){
	 return playOffLogic.eliminator1(team1, team2);
 }
 
 public String fetchEliminator2(String team1,String team2){
	 return playOffLogic.eliminator2(team1, team2);
 }
 
}
