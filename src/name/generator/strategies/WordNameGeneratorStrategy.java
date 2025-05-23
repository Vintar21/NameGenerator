package name.generator.strategies;

import java.util.Collection;
import java.util.List;
import name.generator.enums.strategies.ENameCaseFormat;
import name.generator.enums.strategies.ENameGenerationStrategy;
import name.generator.enums.words.EWordCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordNameGeneratorStrategy extends ANameGeneratorStrategy {

  private static final Logger LOGGER = LogManager.getLogger(WordNameGeneratorStrategy.class);

  WordNameGeneratorStrategy(
      int wordsByName,
      byte[] seed,
      List<EWordCategory> usedCategories,
      ENameCaseFormat caseFormat) {
    super(wordsByName, seed, usedCategories, caseFormat);
  }

  @Override
  protected String getNewName(Collection<String> alreadyGenerated) {
    StringBuilder newName = getNewNameInternal(alreadyGenerated);
    // If the name already exists, apply a strategy
    if (alreadyGenerated.contains(newName.toString())) {
      addWord(newName, alreadyGenerated);
    }

    getLogger().debug("New name generated: {}", newName);
    return newName.toString();
  }

  private void addWord(StringBuilder newName, Collection<String> alreadyGenerated) {
    int i = 0;
    while (alreadyGenerated.contains(newName.toString())) {
      newName.append(getRandomWord(usedWords));
      i++;
    }
    if (this.maxTimeStrategyTriggered < i) {
      this.maxTimeStrategyTriggered = i;
    }
  }

  @Override
  public ENameGenerationStrategy getStrategy() {
    return ENameGenerationStrategy.ADD_WORD;
  }

  @Override
  public String toString() {
    return String.format(
        "Strategy: %s | Words by Name: %d | Categories: %s | Format: %s",
        getStrategy(), wordsByName, categories, caseFormat.toString());
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }
}
