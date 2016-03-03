package me.alf21.textdrawsystem.content.components.input;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alf21 on 27.02.2016.
 */
public enum InputType {
	EMAIL("E-Mail"),
	NUMBER("Number"),
	TEXT("Text"),
	DATE("Date"),
	PASSWORD("Password");

	private String name;

	InputType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static boolean matchesCondition(InputType inputTypes, String s) {
		Pattern pattern;
		Matcher matcher;
		switch (inputTypes) {
			case EMAIL:
				pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
				matcher = pattern.matcher(s);
				return matcher.matches();
			case NUMBER:
				return StringUtils.isNumeric(s);
			case TEXT:
				if(s.replaceAll(" ", "").isEmpty())
					return false;
				return true;
			case DATE:
				pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$");
				matcher = pattern.matcher(s);
				return matcher.matches();
			case PASSWORD:
				if(s.replaceAll(" ", "").isEmpty() || s.length() < 8)
					return false;
				return true;
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
				return "1. That's a text with more than 2_numbers!";
			case DATE:
				return "31.08.2016";
			case PASSWORD:
				return "6!53trs73F_sd (Password with more than 8 chars)";
			default:
				return "";
		}
	}
}
