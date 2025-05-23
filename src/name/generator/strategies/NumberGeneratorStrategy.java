package name.generator.strategies;

import java.util.Collection;
import java.util.List;
import name.generator.enums.strategies.ENameCaseFormat;
import name.generator.enums.strategies.ENameGenerationStrategy;
import name.generator.enums.words.EWordCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NumberGeneratorStrategy extends ANameGeneratorStrategy {

  private static final Logger LOGGER = LogManager.getLogger(NumberGeneratorStrategy.class);

  NumberGeneratorStrategy(
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
      addNumber(newName, alreadyGenerated);
    }

    getLogger().debug("New name generated: {}", newName);
    return newName.toString();
  }

  private void addNumber(StringBuilder newName, Collection<String> alreadyGenerated) {
    // Start at oldName2
    int i = 1;
    var tmpName = newName.toString();
    while (alreadyGenerated.contains(tmpName)) {
      i++;
      tmpName = newName.toString() + i;
    }
    newName.append(i);
    if (this.maxTimeStrategyTriggered < i - 1) {
      this.maxTimeStrategyTriggered = i - 1;
    }
  }

  @Override
  public ENameGenerationStrategy getStrategy() {
    return ENameGenerationStrategy.ADD_NUMBER;
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
