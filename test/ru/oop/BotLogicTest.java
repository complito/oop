package ru.oop;

import org.junit.jupiter.api.Test;

import javax.annotation.concurrent.ThreadSafe;

import static org.junit.jupiter.api.Assertions.*;

class BotLogicTest {
    BotLogic botLogic = new BotLogic();

    @Test
    void testCorrectFindSongs() {
        Song foundSong = botLogic.findSongs("Humble").getResponseList().get(0);
        Song testSong = new Song("/songs/3039923",
                "HUMBLE. by Kendrick Lamar",
                "/artists/1421", "/Kendrick-lamar-humble-lyrics");
        assertEquals(testSong, foundSong);
    }
    @Test
    void testEmptyRequest() {
        assertEquals(botLogic.requestHandler("").getResponseString(), "Ошибка: запрос пустой");
    }

    @Test
    void testHelpMessage() {
        assertTrue(botLogic.requestHandler("/help").getResponseString()
                .startsWith("/findsong - Поиск песни по отрывку текста"));
    }

    @Test
    void testSongsNotFound() {
        assertEquals(botLogic.requestHandler("/findsong dasddasdwq").getResponseString(),
                "По введённому запросу не было найдено песен");
    }

    @Test
    void testSongsFound() {
        assertTrue(botLogic.requestHandler("/findsong Humble").getResponseString()
                .startsWith("Список найденных песен:"));
    }

    @Test
    void testUnknownRequest() {
        assertEquals(botLogic.requestHandler("sda").getResponseString(), "Ошибка: неизвестный запрос");
    }

    @Test
    void testFindSongLyrics() {
        assertTrue(botLogic.requestHandler("/findsonglyrics 3039923").getResponseString().startsWith("[Intro]\n" +
                "   Nobody pray for me"));
    }

    @Test
    void testFindSongInfo() {
        assertTrue(botLogic.requestHandler("/findsonginfo 3039923").getResponseString()
                .startsWith("Полное название: HUMBLE. by Kendrick Lamar"));
    }
}