package com.example.AEPB.airCoin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AirCoinUtilsTest {
    @Test
    void should_throw_exception_when_compare_two_air_coin_amount_given_at_least_one_air_coin_is_null() {
        // given
        AirCoin oneAirCoin = null;
        AirCoin anotherAirCoin = new AirCoin(1);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> AirCoinUtils.compare(oneAirCoin, anotherAirCoin));
        assertThrows(IllegalArgumentException.class, () -> AirCoinUtils.compare(anotherAirCoin, oneAirCoin));
    }

    @Test
    void should_return_false_when_compare_two_air_coin_amount_given_one_air_coin_amount_is_zero_and_another_air_coin_amount_is_one() {
        // given
        final AirCoin oneAirCoin = new AirCoin(0);
        final AirCoin anotherAirCoin = new AirCoin(1);

        // when & then
        assertFalse(AirCoinUtils.compare(oneAirCoin, anotherAirCoin));
    }

    @Test
    void should_return_true_when_compare_two_air_coin_amount_given_one_air_coin_amount_is_one_and_another_air_coin_amount_is_one() {
        // given
        Integer amount = 1;
        final AirCoin oneAirCoin = new AirCoin(amount);
        final AirCoin anotherAirCoin = new AirCoin(amount);

        // when & then
        assertTrue(AirCoinUtils.compare(oneAirCoin, anotherAirCoin));
    }

}
