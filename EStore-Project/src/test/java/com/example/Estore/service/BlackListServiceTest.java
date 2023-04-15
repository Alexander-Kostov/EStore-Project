package com.example.Estore.service;

import com.example.EStore.service.BlackListService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlackListServiceTest {
    @Test
    void isBlacklisted_ReturnsTrueFor102Prefix() {
        // Arrange
        BlackListService service = new BlackListService();

        // Act
        boolean result = service.isBlacklisted("102.123.456.789");

        // Assert
        assertTrue(result);
    }

    @Test
    void isBlacklisted_ReturnsTrueFor129Prefix() {
        // Arrange
        BlackListService service = new BlackListService();

        // Act
        boolean result = service.isBlacklisted("129.123.456.789");

        // Assert
        assertTrue(result);
    }

    @Test
    void isBlacklisted_ReturnsTrueFor196Prefix() {
        // Arrange
        BlackListService service = new BlackListService();

        // Act
        boolean result = service.isBlacklisted("196.123.456.789");

        // Assert
        assertTrue(result);
    }

    @Test
    void isBlacklisted_ReturnsFalseForOtherPrefix() {
        // Arrange
        BlackListService service = new BlackListService();

        // Act
        boolean result = service.isBlacklisted("103.123.456.789");

        // Assert
        assertFalse(result);
    }
}
