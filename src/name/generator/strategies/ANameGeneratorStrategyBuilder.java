package name.generator.strategies;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import name.generator.enums.strategies.ENameCaseFormat;
import name.generator.enums.strategies.ENameGenerationStrategy;
import name.generator.enums.words.EWordCategory;

public class ANameGeneratorStrategyBuilder {

  private String seed = "NiceSeed";
  private int wordsByName = 1;
  private ENameGenerationStrategy strategy = ENameGenerationStrategy.ADD_WORD;
  private ENameCaseFormat caseFormat = ENameCaseFormat.PASCAL;
  private List<EWordCategory> usedCategories =
      Arrays.asList(EWordCategory.class.getEnumConstants());

  private static final ANameGeneratorStrategyBuilder INSTANCE = new ANameGeneratorStrategyBuilder();

  private ANameGeneratorStrategyBuilder() {}

  public static ANameGeneratorStrategyBuilder getInstance() {
    return ANameGeneratorStrategyBuilder.INSTANCE;
  }

  public ANameGeneratorStrategyBuilder setStrategy(ENameGenerationStrategy strategy) {
    this.strategy = strategy;
    return this;
  }

  public ANameGeneratorStrategyBuilder setCase(ENameCaseFormat caseFormat) {
    this.caseFormat = caseFormat;
    return this;
  }

  public ANameGeneratorStrategyBuilder wordsByName(int wordsByName) {
    this.wordsByName = wordsByName;
    return this;
  }

  public ANameGeneratorStrategyBuilder setSeed(String seed) {
    this.seed = seed;
    return this;
  }

  public ANameGeneratorStrategyBuilder setCategories(EWordCategory... categories) {
    this.usedCategories = Arrays.asList(categories);
    return this;
  }

  public ANameGeneratorStrategyBuilder excludeCategories(EWordCategory... categories) {
    List<EWordCategory> excluded = Arrays.asList(categories);
    usedCategories = usedCategories.stream().filter(Predicate.not(excluded::contains)).toList();
    return this;
  }

  public ANameGeneratorStrategy build() {
    switch (strategy) {
      case ADD_WORD:
        return new WordNameGeneratorStrategy(
            wordsByName, seed.getBytes(), usedCategories, caseFormat);
      case ADD_NUMBER:
        return new NumberGeneratorStrategy(
            wordsByName, seed.getBytes(), usedCategories, caseFormat);
      default:
        // should never reach default
        return new WordNameGeneratorStrategy(
            wordsByName, seed.getBytes(), usedCategories, caseFormat);
    }
  }
}
