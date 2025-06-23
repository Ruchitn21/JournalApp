package com.journal.app.JournalApp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JournalAppApplicationTests {

	@InjectMocks
	private JournalAppApplication journalAppApplication;

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> JournalAppApplication.main(new String[]{}));
	}
}