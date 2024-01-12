package com.bigModel.backend.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParseJSONUtilTest {

    @Test
    void saveTweetImage() throws IOException {
        ParseJSONUtil.saveTweetImageVideo("", "dasda");
    }
}