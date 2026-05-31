package com.smartgest.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HomeBeanTest {

    @Test
    void shouldExposeStartupMessage() {
        Assertions.assertEquals("SmartGest foundation ready", new HomeBean().getMessage());
    }
}
