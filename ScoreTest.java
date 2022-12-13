/*
brightspace team 13
Ethan Cunningham: Github ID -> ethncunningham
Jerome Manzano:	  Github ID -> jerome-manzano	
*/

package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class ScoreTest {

	private Score score;
	
	@BeforeEach
	void setUp(){
		score = new Score();
	}
	
	@Test
	@DisplayName("Checks that object has been instatiated")
	void testScore() {
		assertNotNull(score);
	}

	@Test
	@DisplayName("Checks that getScoreType returns a ScoreType")
	void testGetScoreType() {
		assertEquals(ScoreType.SINGLE, score.getScoreType());
	}

	@Test
	void testSetScore() {
		score.setScore(ScoreType.GAMMON);
		assertEquals(ScoreType.GAMMON, score.getScoreType());
		
		score.setScore(ScoreType.BACKGAMMON);
		assertEquals(ScoreType.BACKGAMMON, score.getScoreType());
	}

	@Test
	void testToString() {
		score.setScore(ScoreType.SINGLE);
		assertEquals("single", score.toString());
		
		score.setScore(ScoreType.GAMMON);
		assertEquals("gammon", score.toString());
		
		score.setScore(ScoreType.BACKGAMMON);
		assertEquals("backgammon", score.toString());
	}

	@Test
	void testGetScore() {
		for(int i=1;i<65;i*=2) {
		assertEquals(i, score.getScore(ScoreType.SINGLE, i));
		}
		
		for(int i=1;i<65;i*=2) {
			assertEquals(i*2, score.getScore(ScoreType.GAMMON, i));
		}
		
		for(int i=1;i<65;i*=2) {
			assertEquals(i*3, score.getScore(ScoreType.BACKGAMMON, i));
		}
	}

}
