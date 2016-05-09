package me.alf21.textdrawsystem.content.components.input;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alf21 on 27.02.2016 in the project 'textdraw-system'.
 */
public enum InputType {
	EMAIL("E-Mail"),
	NUMBER("Number"),
	TEXT("Text"),
	DATE("Date"),
	PASSWORD("Password"),
	NAME("Name");

	private String name;

	InputType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static boolean matchesCondition(InputType inputTypes, String s) {
		if (s.replaceAll(" ", "").isEmpty()) return false;
		switch (inputTypes) {
			case TEXT:
				return true;
			case NUMBER:
				return StringUtils.isNumeric(s);
			case PASSWORD:
				return (s.length() >= 8);
			case NAME:
				return (s.length() >= 3 && s.length() <= 24);
			case EMAIL:
				return Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$").matcher(s).matches();
			case DATE:
				return Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$").matcher(s).matches();
			default:
				return true;
		}
	}

	public static String getCondition(InputType inputTypes) {
		switch (inputTypes) {
			case EMAIL:
				return "max@mustermann.com";
			case NUMBER:
				return "2048";
			case TEXT:
				return "1. That's a text with 2_numbers!";
			case DATE:
				return "31.08.2016";
			case PASSWORD:
				return "6!53trs73F_sd (Password with more than 8 chars)";
			case NAME:
				return "Max (between 3 and 24 letters)";
			default:
				return "";
		}
	}
}
