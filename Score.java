package backgammon;

public class Score {
private ScoreType score;

	Score(){
	score = ScoreType.SINGLE;
	}

	public ScoreType getScoreType() {
	return score;
	}
	
	public void setScore(ScoreType scoreType) {
		score = scoreType;
	}


	public String toString() {
		
		String scoreToString = "single";
			
		if(score==ScoreType.GAMMON) {
			scoreToString = "gammon";
		}
		if(score==ScoreType.BACKGAMMON) {
			scoreToString = "backgammon";
		}
		
		return scoreToString;
	}
	
	public int getScore(ScoreType scoreType, int doubleCube) {
		int points=doubleCube;
		
		if(scoreType==ScoreType.GAMMON) {
			points*=2;
		}
		if(scoreType==ScoreType.BACKGAMMON) {
			points*=3;
		}
		
		
		return points;
	}
}
