package name.generator;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import name.generator.enums.strategies.ENameCaseFormat;
import name.generator.enums.strategies.ENameGenerationStrategy;
import name.generator.enums.words.EWordCategory;

public class NameGenerator {

	private final Integer wordsByName;
	private final ENameGenerationStrategy strategy;
	private final List<EWordCategory> categories;
	private final ENameCaseFormat caseFormat;
	private final SecureRandom random = new SecureRandom();
	private final List<String> usedWords = new ArrayList<>();
	private final int retryAllowed = 2;
	private int maxTimeStrategyTriggered = 0;

	private static final Logger LOGGER = LogManager.getLogger(NameGenerator.class);

	NameGenerator(int wordsByName, byte[] seed, ENameGenerationStrategy strategy, List<EWordCategory> usedCategories,
			ENameCaseFormat caseFormat) {
		this.wordsByName = wordsByName;
		this.strategy = strategy;
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
		LOGGER.debug("Max number of words: {}", this.wordsByName + this.maxTimeStrategyTriggered);
		return randNames;
	}

	/*
	 * Transform each unique given name into a new generated one
	 */
	public Map<String, String> generateNewRandomNames(List<String> initialNames) {
		Map<String, String> namesMap = new HashMap<>();
		initialNames.stream().forEach(name -> namesMap.putIfAbsent(name, getNewName(namesMap.values())));
		return namesMap;
	}

	private String getNewName(Collection<String> alreadyGenerated) {
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
		while (newName.isEmpty() || (alreadyGenerated.contains(newName.toString()) && retry < retryAllowed)) {
			newName.delete(0, newName.length());
			IntStream.range(0, wordsByName).map(i -> random.nextInt(usedWords.size()))
					.forEach(x -> newName.append(usedWords.get(x)));
			retry++;
		}

		// If the name already exists, apply a strategy
		if (alreadyGenerated.contains(newName.toString())) {
			switch (strategy) {
			case ADD_NUMBER:
				addNumber(newName, alreadyGenerated);
				break;

			case ADD_WORD:
				addWord(newName, alreadyGenerated);
				break;

			default:
				addWord(newName, alreadyGenerated);
				break;
			}
		}

		LOGGER.debug("New name generated: {}", newName);
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

	private void addWord(StringBuilder newName, Collection<String> alreadyGenerated) {
		int i = 0;
		while (alreadyGenerated.contains(newName.toString())) {
			int x = random.nextInt(usedWords.size());
			newName.append(usedWords.get(x));
			i++;
		}
		if (this.maxTimeStrategyTriggered < i) {
			this.maxTimeStrategyTriggered = i;
		}
	}

	@Override
	public String toString() {
		return String.format("Strategy: %s | Words by Name: %d | Categories: %s | Format: %s", strategy, wordsByName,
				categories, caseFormat.toString());
	}
}
