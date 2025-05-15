package name.generator.enums.strategies;

public enum ENameCaseFormat {
	CAMEL,
	KEBAB,
	PASCAL,
	RANDOM,
	SNAKE;

	@Override
	public String toString() {
		switch (this) {
		case CAMEL:
			return "camelCase";
		case KEBAB:
			return "kebab-case";
		case PASCAL:
			return "PascalCase";
		case RANDOM:
			return "RAnDomcAsE";
		case SNAKE:
			return "snake_case";
		default:
			return "N/A";
		}
	}
}
