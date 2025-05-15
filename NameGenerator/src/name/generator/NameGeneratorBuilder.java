package name.generator;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import name.generator.enums.strategies.ENameCaseFormat;
import name.generator.enums.strategies.ENameGenerationStrategy;
import name.generator.enums.words.EWordCategory;

public class NameGeneratorBuilder {

	private String seed = "NiceSeed";
	private int wordsByName = 1;
	private ENameGenerationStrategy strategy = ENameGenerationStrategy.ADD_WORD;
	private ENameCaseFormat caseFormat = ENameCaseFormat.PASCAL;
	private List<EWordCategory> usedCategories = Arrays.asList(EWordCategory.class.getEnumConstants());

	private static final NameGeneratorBuilder INSTANCE = new NameGeneratorBuilder();

	private NameGeneratorBuilder() {
	}

	public static NameGeneratorBuilder getInstance() {
		return NameGeneratorBuilder.INSTANCE;
	}

	public NameGeneratorBuilder setStrategy(ENameGenerationStrategy strategy) {
		this.strategy = strategy;
		return this;
	}

	public NameGeneratorBuilder setCase(ENameCaseFormat caseFormat) {
		this.caseFormat = caseFormat;
		return this;
	}

	public NameGeneratorBuilder wordsByName(int wordsByName) {
		this.wordsByName = wordsByName;
		return this;
	}

	public NameGeneratorBuilder setSeed(String seed) {
		this.seed = seed;
		return this;
	}

	public NameGeneratorBuilder setCategories(EWordCategory... categories) {
		this.usedCategories = Arrays.asList(categories);
		return this;
	}

	public NameGeneratorBuilder excludeCategories(EWordCategory... categories) {
		List<EWordCategory> excluded = Arrays.asList(categories);
		usedCategories = usedCategories.stream().filter(Predicate.not(excluded::contains)).toList();
		return this;
	}

	public NameGenerator build() {
		return new NameGenerator(wordsByName, seed.getBytes(), strategy, usedCategories, caseFormat);
	}

}
