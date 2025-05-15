package name.generator.enums.words;

public enum ENumber implements EWords {
	ZERO,
	ONE,
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN,
	EIGHT,
	NINE;

	@Override
	public EWordCategory getCategory() {
		return EWordCategory.NUMBER;
	}

}
