package name.generator.utils;

import static name.generator.utils.StringConstants.EMPTY;
import static name.generator.utils.StringConstants.HYPHEN;
import static name.generator.utils.StringConstants.UNDERSCORE;

import java.security.SecureRandom;

import name.generator.enums.strategies.ENameCaseFormat;

public class NameGeneratorUtils {

	private NameGeneratorUtils() {
	}

	public static String formatWord(String input, ENameCaseFormat caseFormat) {
		switch (caseFormat) {
		case CAMEL:
			return toCamelCase(input);
		case KEBAB:
			return toKebabCase(input);
		case PASCAL:
			return toPascalCase(input);
		case RANDOM:
			return toRandomCase(input);
		case SNAKE:
			return toSnakeCase(input);
		default:
			return input;
		}
	}

	private static String toPascalCase(String input) {
		String[] words = input.split(UNDERSCORE);

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
			builder.append(word);
		}
		return builder.toString();
	}

	private static String toCamelCase(String input) {
		String pascalCase = toPascalCase(input);
		return Character.toLowerCase(pascalCase.charAt(0)) + pascalCase.substring(1);
	}

	private static String toSnakeCase(String input) {
		return input.toLowerCase();
	}

	private static String toKebabCase(String input) {
		return input.toLowerCase().replaceAll(UNDERSCORE, HYPHEN);
	}

	private static String toRandomCase(String input) {
		var rand = new SecureRandom();
		return input.replaceAll(UNDERSCORE, EMPTY).chars()
				.map(c -> rand.nextBoolean() ? Character.toUpperCase(c) : Character.toLowerCase(c))
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

}
