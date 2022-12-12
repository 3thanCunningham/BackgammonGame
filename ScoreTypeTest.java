package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTypeTest {
	private Score score;
	
	@Test
	@DisplayName("Check Score Type Enum")
	void test() {
		score = new Score();
		
		score.setScore(ScoreType.SINGLE);
		assertEquals(ScoreType.valueOf("SINGLE"), score.getScoreType());
		
		score.setScore(ScoreType.GAMMON);
		assertEquals(ScoreType.valueOf("GAMMON"), score.getScoreType());
		
		score.setScore(ScoreType.BACKGAMMON);
		assertEquals(ScoreType.valueOf("BACKGAMMON"), score.getScoreType());
	}

}
