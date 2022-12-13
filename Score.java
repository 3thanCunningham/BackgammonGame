/*
brightspace team 13
Ethan Cunningham: Github ID -> ethncunningham
Jerome Manzano:	  Github ID -> jerome-manzano	
*/

package backgammon;

public class Score {
private ScoreType score;

	Score(){
	/* score defaults to single */
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
		/*points initially set to value on doubling cube*/
		int points=doubleCube;
		
		/* a gammon results in twice the value of the doubling cube value */
		if(scoreType==ScoreType.GAMMON) {
			points*=2;
		}
		/* a backgammon results in three times the value of the doubling cube value */
		if(scoreType==ScoreType.BACKGAMMON) {
			points*=3;
		}
		
		
		return points;
	}
}
