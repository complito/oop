package ru.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotLogicTest {
    @Test
    void testCorrectFindSongs() {
        Song foundSong = BotLogic.findSongs("Humble").get(0);
        Song testSong = new Song("/songs/3039923", "HUMBLE. by Kendrick Lamar", "/artists/1421");
        assertEquals(testSong, foundSong);
    }
}