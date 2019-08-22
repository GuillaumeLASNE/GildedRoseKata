package com.gildedrose;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GoldenTest {

    @Test
    public void check() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TexttestFixture.main(new String [0]);
        Assert.assertEquals(getGloden(), out.toString());
    }

    private String getGloden() throws IOException {
        return String.join(System.lineSeparator(), Files.readAllLines(Paths.get("golden.txt"))) + System.lineSeparator();
    }
}
