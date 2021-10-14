package com.example.AEPB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AirCoinTest {

	@Test
	void should_return_false_when_compare_two_air_coin_amount_given_at_least_one_air_coin_is_null() {
		// given
		AirCoin oneAirCoin = null;
		AirCoin anotherAirCoin = new AirCoin(1);

		// when & then
		assertThrows(NullPointerException.class, () -> oneAirCoin.equals(anotherAirCoin));
	}

	@Test
	void should_return_false_when_compare_two_air_coin_amount_given_at_least_one_air_coin_amount_is_null() {
		// given
		final AirCoin oneAirCoin = new AirCoin(1);
		final AirCoin anotherAirCoin = new AirCoin();

		// when
		final boolean isEqual = oneAirCoin.equals(anotherAirCoin);

		// then
		assertFalse(isEqual);
	}

	@Test
	void should_throw_IllegalAirCoinAmountException_when_create_air_coin_given_amount_is_less_than_the_MIN_AMOUNT_or_larger_than_MAX_AMOUNT() {
		// given & when & then
		assertThrows(IllegalAirCoinAmountException.class, () -> new AirCoin(AirCoin.MIN_AMOUNT - 1));
		assertThrows(IllegalAirCoinAmountException.class, () -> new AirCoin(AirCoin.MAX_AMOUNT + 1));
	}

	@Test
	void should_return_false_when_compare_two_air_coin_amount_given_two_air_coin_with_unequal_amount() {
		// given
		final AirCoin oneAirCoin = new AirCoin(10000);
		final AirCoin anotherAirCoin = new AirCoin(10001);

		// when
		final boolean isEqual = oneAirCoin.equals(anotherAirCoin);

		// then
		assertFalse(isEqual);

	}

	@Test
	void should_return_true_when_compare_two_air_coin_amount_given_two_air_coin_with_equal_amount() {
		// given
		Integer amount = 10000;
		final AirCoin oneAirCoin = new AirCoin(amount);
		final AirCoin anotherAirCoin = new AirCoin(amount);

		// when
		final boolean isEqual = oneAirCoin.equals(anotherAirCoin);

		// then
		assertTrue(isEqual);
	}
}
