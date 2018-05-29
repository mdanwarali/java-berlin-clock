package com.ubs.opsit.interviews;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TimeConverterImplTest {

	@InjectMocks
	private TimeConverterImpl timeConverter = new TimeConverterImpl();

	private static final String INVALID_INPUT_RESPONSE = null;

	private static final String NULL_INPUT = null;

	private static final String EMPTY_INPUT = "";
	
	private static final String INVALID_INPUT = "12:aa:cc";

	@BeforeClass
	public static void init() {

	}

	@Test
	public void testconvertTimeNullInput() {
		String response = timeConverter.convertTime(NULL_INPUT);
		assertEquals(response, INVALID_INPUT_RESPONSE);
	}

	@Test
	public void testconvertTimeEmptyInput() {
		String response = timeConverter.convertTime(EMPTY_INPUT);
		assertEquals(response, INVALID_INPUT_RESPONSE);
	}

	@Test
	public void testconvertTimeException() {
		String response = timeConverter.convertTime(INVALID_INPUT);
		assertEquals(response, INVALID_INPUT_RESPONSE);
	}
}
