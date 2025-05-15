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
		IntStream.range(0, wordsByName).map(i -> random.nextInt(usedWords.size()))
				.forEach(x -> newName.append(usedWords.get(x)));

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
		int i = 2;
		while (alreadyGenerated.contains(newName.toString())) {
			i++;
		}
		newName.append(i);
	}

	private void addWord(StringBuilder newName, Collection<String> alreadyGenerated) {
		while (alreadyGenerated.contains(newName.toString())) {
			int x = random.nextInt(usedWords.size());
			newName.append(usedWords.get(x));
		}
	}

	@Override
	public String toString() {
		return String.format("Strategy: %s | Words by Name: %d | Categories: %s | Format: %s", strategy, wordsByName,
				categories, caseFormat.toString());
	}
}
