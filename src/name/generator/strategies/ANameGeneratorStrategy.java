package name.generator.strategies;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import name.generator.enums.strategies.ENameCaseFormat;
import name.generator.enums.strategies.ENameGenerationStrategy;
import name.generator.enums.words.EWordCategory;
import org.apache.logging.log4j.Logger;

public abstract class ANameGeneratorStrategy {
  protected final Integer wordsByName;
  protected final List<EWordCategory> categories;
  protected final ENameCaseFormat caseFormat;
  protected final List<String> usedWords = new ArrayList<>();
  protected final int retryAllowed = 2;
  protected int maxTimeStrategyTriggered = 0;

  private final SecureRandom random = new SecureRandom();

  ANameGeneratorStrategy(
      int wordsByName,
      byte[] seed,
      List<EWordCategory> usedCategories,
      ENameCaseFormat caseFormat) {
    this.wordsByName = wordsByName;
    this.categories = usedCategories;
    this.caseFormat = caseFormat;
    usedCategories.stream().forEach(category -> usedWords.addAll(category.getWords(caseFormat)));

    random.setSeed(seed);
  }

  /*
   * Generate and return N unique random names
   */
  public Set<String> generateRandomNames(int number) {
    Set<String> randNames = new HashSet<>();
    IntStream.range(0, number).forEach(i -> randNames.add(getNewName(randNames)));
    // TODO: Split strategy in different classes abstract classes...
    getLogger().debug("Max number of words: {}", this.wordsByName + this.maxTimeStrategyTriggered);
    return randNames;
  }

  /*
   * Transform each unique given name into a new generated one
   */
  public Map<String, String> generateNewRandomNames(List<String> initialNames) {
    Map<String, String> namesMap = new HashMap<>();
    initialNames.stream()
        .forEach(name -> namesMap.putIfAbsent(name, getNewName(namesMap.values())));
    return namesMap;
  }

  protected String getRandomWord(List<String> fromList) {
    var x = random.nextInt(fromList.size());
    return fromList.get(x);
  }

  protected StringBuilder getNewNameInternal(Collection<String> alreadyGenerated) {
    StringBuilder newName = new StringBuilder();
    // To avoid double name
    /*
     * List of available words + index draw in the available list -> if the list is
     * empty -> refill it and increment index --> we will then switch on the 2nd
     * word (if it exists) Problem: 2nd words have already been generated, we have
     * to store them ? Heavy in memory
     *
     * Allow a number of retry ?
     */
    var retry = 0;
    while (newName.isEmpty()
        || (alreadyGenerated.contains(newName.toString()) && retry < retryAllowed)) {
      newName.delete(0, newName.length());
      IntStream.range(0, wordsByName).forEach(i -> newName.append(getRandomWord(usedWords)));
      retry++;
    }

    return newName;
  }

  protected abstract String getNewName(Collection<String> alreadyGenerated);

  public abstract ENameGenerationStrategy getStrategy();

  protected abstract Logger getLogger();

  @Override
  public String toString() {
    return String.format(
        "Strategy: %s | Words by Name: %d | Categories: %s | Format: %s",
        getStrategy(), wordsByName, categories, caseFormat.toString());
  }
}
