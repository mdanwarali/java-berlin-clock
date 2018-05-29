package com.ubs.opsit.interviews.model;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ubs.opsit.interviews.constants.BerlinClockConstant;

public class BerlinClockOutputTO {

	private String[] hourFistRow = { 
			BerlinClockConstant.DEFAULT_INDICATOR, BerlinClockConstant.DEFAULT_INDICATOR,
			BerlinClockConstant.DEFAULT_INDICATOR, BerlinClockConstant.DEFAULT_INDICATOR};
	
	private String[] hour2ndRow = { 
			BerlinClockConstant.DEFAULT_INDICATOR, BerlinClockConstant.DEFAULT_INDICATOR,
			BerlinClockConstant.DEFAULT_INDICATOR, BerlinClockConstant.DEFAULT_INDICATOR};
	
	private String[] minuteFistRow = { 
			BerlinClockConstant.DEFAULT_INDICATOR, BerlinClockConstant.DEFAULT_INDICATOR,
			BerlinClockConstant.DEFAULT_INDICATOR, BerlinClockConstant.DEFAULT_INDICATOR,
			BerlinClockConstant.DEFAULT_INDICATOR, BerlinClockConstant.DEFAULT_INDICATOR,
			BerlinClockConstant.DEFAULT_INDICATOR, BerlinClockConstant.DEFAULT_INDICATOR,
			BerlinClockConstant.DEFAULT_INDICATOR, BerlinClockConstant.DEFAULT_INDICATOR,
			BerlinClockConstant.DEFAULT_INDICATOR };
	
	private String[] minute2ndRow = { 
			BerlinClockConstant.DEFAULT_INDICATOR, BerlinClockConstant.DEFAULT_INDICATOR,
			BerlinClockConstant.DEFAULT_INDICATOR, BerlinClockConstant.DEFAULT_INDICATOR };

	private String secondsIndicator = BerlinClockConstant.DEFAULT_INDICATOR;

	public String[] getHourFistRow() {
		return hourFistRow;
	}

	public void setHourFistRow(String[] hourFistRow) {
		this.hourFistRow = hourFistRow;
	}

	public String[] getHour2ndRow() {
		return hour2ndRow;
	}

	public void setHour2ndRow(String[] hour2ndRow) {
		this.hour2ndRow = hour2ndRow;
	}

	public String[] getMinuteFistRow() {
		return minuteFistRow;
	}

	public void setMinuteFistRow(String[] minuteFistRow) {
		this.minuteFistRow = minuteFistRow;
	}

	public String[] getMinute2ndRow() {
		return minute2ndRow;
	}

	public void setMinute2ndRow(String[] minute2ndRow) {
		this.minute2ndRow = minute2ndRow;
	}

	public String getSecondsIndicator() {
		return secondsIndicator;
	}

	public void setSecondsIndicator(String secondsIndicator) {
		this.secondsIndicator = secondsIndicator;
	}
	
	@Override
	public String toString() {

		return secondsIndicator 
				+ BerlinClockConstant.NEXTLINE + Stream.of(hourFistRow).collect(Collectors.joining())
				+ BerlinClockConstant.NEXTLINE + Stream.of(hour2ndRow).collect(Collectors.joining())
				+ BerlinClockConstant.NEXTLINE + Stream.of(minuteFistRow).collect(Collectors.joining())
				+ BerlinClockConstant.NEXTLINE + Stream.of(minute2ndRow).collect(Collectors.joining());
	}
}
