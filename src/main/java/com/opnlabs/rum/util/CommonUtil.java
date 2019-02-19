package com.opnlabs.rum.util;

import java.util.StringJoiner;

import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
	
	@SafeVarargs
	public static <E> String generateLogMessage(E... args) {

		StringJoiner sJoiner = new StringJoiner(" ::: ");
		for (E arg : args) {
			sJoiner.add(arg.toString());
		}
		return sJoiner.toString();
	}
}

