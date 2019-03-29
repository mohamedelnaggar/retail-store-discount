package com.retail.store.discount.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class NumberUtilsTest {

    @Test
    public void shouldCalculateValue_whenCallingWithPercentage(){
        double percentageValue = NumberUtils.calculatePercentageValue(1000.0, 10.0);
        assertThat(percentageValue).isEqualTo(100.0);
    }

    @Test
    public void shouldNotCalculateValue_whenCallingWithZeroPercentage(){
        double percentageValue = NumberUtils.calculatePercentageValue(1000.0, 0.0);
        assertThat(percentageValue).isEqualTo(0.0);
    }

}
