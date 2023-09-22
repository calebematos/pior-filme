package com.calebematos.worstmovie;

import com.calebematos.worstmovie.api.model.PrizeRange;
import com.calebematos.worstmovie.domain.service.PrizeRangeService;
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
	private PrizeRangeService prizeRangeService;

	@Test
	void shouldValidatePrizeSize() {
		//given

		//when
		PrizeRange prizeRangeMinAndMax = prizeRangeService.findPrizeRangeMinAndMax();

		//then
		Assertions.assertAll(
				() -> assertEquals(1, prizeRangeMinAndMax.getMin().size()),
				() -> assertEquals(6, prizeRangeMinAndMax.getMin().get(0).getInterval()),
				() -> assertEquals(1990, prizeRangeMinAndMax.getMin().get(0).getFollowingWin()),
				() -> assertEquals(1984, prizeRangeMinAndMax.getMin().get(0).getPreviousWin()),
				() -> assertEquals("Bo Derek", prizeRangeMinAndMax.getMin().get(0).getProducer()),
				() -> assertEquals(1, prizeRangeMinAndMax.getMax().size()),
				() -> assertEquals(9, prizeRangeMinAndMax.getMax().get(0).getInterval()),
				() -> assertEquals(1994, prizeRangeMinAndMax.getMax().get(0).getFollowingWin()),
				() -> assertEquals(1985, prizeRangeMinAndMax.getMax().get(0).getPreviousWin()),
				() -> assertEquals("Buzz Feitshans", prizeRangeMinAndMax.getMax().get(0).getProducer())
        );

	}

}
