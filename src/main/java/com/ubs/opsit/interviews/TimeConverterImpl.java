package com.ubs.opsit.interviews;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubs.opsit.interviews.constants.BerlinClockConstant;
import com.ubs.opsit.interviews.exception.FunctionalException;
import com.ubs.opsit.interviews.model.BerlinClockOutputTO;
import com.ubs.opsit.interviews.model.InputTimeTO;

public class TimeConverterImpl implements TimeConverter {

	private static final Logger LOG = LoggerFactory.getLogger(TimeConverterImpl.class);
	
	@Override
	public String convertTime(String aTime) {
		String berlinClock=null;
		try {
			LOG.info("preparing InputTimeTO object from input {}", aTime);
			//Parse and convert inputs into transfer object
			InputTimeTO input = paseNValiadeInput(aTime);
			
			//Transform input into provided business logic
			BerlinClockOutputTO output = transformBerlinClock(input);
			LOG.info("BerlinClockOutput :: \n{} \nfor input::{} ", output, aTime);
			
			//Convert result object into String 
			berlinClock = output.toString();
		} catch (FunctionalException e) {
			LOG.error("provided input time={} is not valid", aTime);
		} catch (Exception e) {
			LOG.error("convertTime::for input::{},  exception={}",aTime, e);
		}
		return berlinClock;
	}

	private InputTimeTO paseNValiadeInput(String aTime) throws FunctionalException {
		//validate if input is provided
		if (aTime == null || aTime.isEmpty())
			throw new FunctionalException(BerlinClockConstant.INVALID_INPUT);

		//Split input string on mentioned separator
		String[] aTimeComponents = aTime.split(":");
		//validate if input contains all the components 
		if (aTimeComponents.length != 3)
			throw new FunctionalException(BerlinClockConstant.INVALID_INPUT);

		//prepare and return input TO object
		return prepareInputTO(aTimeComponents);
	}

	private InputTimeTO prepareInputTO(String[] aTimeComponents) throws FunctionalException {
		InputTimeTO input = new InputTimeTO();
		try {
			//set hour component
			input.setHour(Integer.parseInt(aTimeComponents[0]));
			//set minute component
			input.setMinute(Integer.parseInt(aTimeComponents[1]));
			//set second component
			input.setSecond(Integer.parseInt(aTimeComponents[2]));
		} catch (NumberFormatException e) {
			//throw functional/technical exception if provided component is not a digit
			throw new FunctionalException(BerlinClockConstant.INVALID_INPUT);
		}
		return input;
	}

	private BerlinClockOutputTO transformBerlinClock(InputTimeTO input) {
		BerlinClockOutputTO output = new BerlinClockOutputTO();
		//Apply business logic on hour component
		transformHour(input, output);
		//Apply business logic on minute component
		transformMinute(input, output);
		//Apply business logic on second component
		transformSeconds(input, output);
		return output;
	}

	private void transformHour(InputTimeTO input, BerlinClockOutputTO output) {
		//Apply business logic on first row of hour lamp
		int firstRow = input.getHour() / 5;
		for (int i = 0; i < firstRow; i++) {
			output.getHourFistRow()[i] = BerlinClockConstant.RED_INDICATOR;
		}

		//Apply business logic on second row of hour lamp
		int seconddRow = input.getHour() % 5;
		for (int i = 0; i < seconddRow; i++) {
			output.getHour2ndRow()[i] = BerlinClockConstant.RED_INDICATOR;
		}
	}

	private void transformMinute(InputTimeTO input, BerlinClockOutputTO output) {
		//Apply business logic on first row of minute lamp
		int firstRow = input.getMinute() / 5;
		for (int i = 0; i < firstRow; i++) {
			setMinuteIndicator(i, output);
		}
		//Apply business logic on second row of minute lamp
		int seconddRow = input.getMinute() % 5;
		for (int i = 0; i < seconddRow; i++) {
			output.getMinute2ndRow()[i] = BerlinClockConstant.YELLOW_INDICATOR;
		}
	}

	private void setMinuteIndicator(int index, BerlinClockOutputTO output) {
		int count = index + 1;
		switch (count) {
		case 3:
			//Set first quarter of the clock 
			output.getMinuteFistRow()[index] = BerlinClockConstant.RED_INDICATOR;
			break;
		case 6:
			//Set half of the clock 
			output.getMinuteFistRow()[index] = BerlinClockConstant.RED_INDICATOR;
			break;
		case 9:
			//Set last quarter of the clock 
			output.getMinuteFistRow()[index] = BerlinClockConstant.RED_INDICATOR;
			break;
		default:
			////Set set lamp indicator 
			output.getMinuteFistRow()[index] = BerlinClockConstant.YELLOW_INDICATOR;
		}
	}

	private void transformSeconds(InputTimeTO input, BerlinClockOutputTO output) {
		int yellowIndicator = input.getSecond()%2;
		if(yellowIndicator == 0)
			output.setSecondsIndicator(BerlinClockConstant.YELLOW_INDICATOR);
	}

}
