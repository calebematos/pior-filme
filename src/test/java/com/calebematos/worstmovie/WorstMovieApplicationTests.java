package com.calebematos.worstmovie;

import com.calebematos.worstmovie.api.model.IntervalWinner;
import com.calebematos.worstmovie.domain.service.ProducerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class WorstMovieApplicationTests {

	@Autowired
	private ProducerService producerService;

	@Test
	void shouldValidatePrizeSize() {
		//given

		//when
		IntervalWinner intervalWinnerMinAndMax = producerService.findWinnersWithMinAndMaxInterval();

		//then
		Assertions.assertAll(
				() -> assertEquals(1, intervalWinnerMinAndMax.getMin().size()),
				() -> assertEquals(6, intervalWinnerMinAndMax.getMin().get(0).getInterval()),
				() -> assertEquals(1990, intervalWinnerMinAndMax.getMin().get(0).getFollowingWin()),
				() -> assertEquals(1984, intervalWinnerMinAndMax.getMin().get(0).getPreviousWin()),
				() -> assertEquals("Bo Derek", intervalWinnerMinAndMax.getMin().get(0).getProducer()),
				() -> assertEquals(1, intervalWinnerMinAndMax.getMax().size()),
				() -> assertEquals(9, intervalWinnerMinAndMax.getMax().get(0).getInterval()),
				() -> assertEquals(1994, intervalWinnerMinAndMax.getMax().get(0).getFollowingWin()),
				() -> assertEquals(1985, intervalWinnerMinAndMax.getMax().get(0).getPreviousWin()),
				() -> assertEquals("Buzz Feitshans", intervalWinnerMinAndMax.getMax().get(0).getProducer())
        );

	}

}
