package com.example.AEPB.airCoin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AirCoinTest {
    @Test
    void should_throw_IllegalAirCoinAmountException_when_create_air_coin_given_amount_is_less_than_the_MIN_AMOUNT_or_larger_than_MAX_AMOUNT() {
        // given & when & then
        assertThrows(IllegalAirCoinAmountException.class, () -> new AirCoin(AirCoin.MIN_AMOUNT - 1));
        assertThrows(IllegalAirCoinAmountException.class, () -> new AirCoin(AirCoin.MAX_AMOUNT + 1));
    }

}
