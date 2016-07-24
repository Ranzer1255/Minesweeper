package main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import userinterface.TextUI;

public class UItester {

	private TextUI ui;
	
	@Before
	public void setUp() {
		ui = new TextUI();
	}

	@Test
	public void testPromptInput() {
		assertTrue(ui.inputPrompt("Test input: ").equals("test"));
	}

}
