package com.mengyu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.security.InvalidParameterException;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationRunnerTest {

    private ApplicationRunner applicationRunner;

    @Before
    public void setup() {
        initMocks(this);
        applicationRunner = new ApplicationRunner();
    }

    @Test(expected = InvalidParameterException.class)
    public void test_EmptyInputArgs() throws IOException {
        String[] inputArgs = {};
        applicationRunner.run(inputArgs);
    }

    @Test(expected = InvalidParameterException.class)
    public void test_EmptyServerLogFilePath() throws IOException {
        String[] inputArgs = {""};
        applicationRunner.run(inputArgs);
    }

    @Test(expected = InvalidParameterException.class)
    public void test_EmptyServerLogFilePath() throws IOException {
        String[] inputArgs = {""};
        applicationRunner.run(inputArgs);
    }
}