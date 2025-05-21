package name.generator.enums.words;

import java.util.Arrays;
import java.util.List;

import name.generator.enums.strategies.ENameCaseFormat;
import name.generator.utils.NameGeneratorUtils;

public enum EWordCategory {
	ANIMALS(EAnimals.class),
	BUSINESS(EBusiness.class),
	COMPUTER_SCIENCE(EComputerScience.class),
	CITIES(ECities.class),
	FLOWER(EFlowers.class),
	FOOD(EFood.class),
	FURNITURE(EFurniture.class),
	NATIONALITIES(ENationalities.class),
	NUMBER(ENumber.class),
	POKEMON(EPokemons.class),
	RPG_CLASSES(ERpgClasses.class),
	RPG_RACES(ERpgRaces.class),
	SPORTS(ESports.class);

	private Class<? extends EWords> words;

	private EWordCategory(Class<? extends EWords> words) {
		this.words = words;
	}

	public List<String> getWords(ENameCaseFormat caseFormat) {
		return Arrays.asList(this.words.getEnumConstants()).stream()
				.map(word -> NameGeneratorUtils.formatWord(word.toString(), caseFormat)).toList();
	}
}
