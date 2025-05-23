package name.generator.tests;

import static name.generator.utils.TestsUtils.INITIAL_NAMES;
import static name.generator.utils.TestsUtils.REPLACED_NAMES_NUMBER;
import static name.generator.utils.TestsUtils.REPLACED_NAMES_WORD;
import static name.generator.utils.TestsUtils.TEST_SEED;
import static name.generator.utils.TestsUtils.THOUSAND_NAMES_NUMBER_EXPECTED;
import static name.generator.utils.TestsUtils.THOUSAND_NAMES_WORDS_EXPECTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import name.generator.enums.strategies.ENameGenerationStrategy;
import name.generator.strategies.ANameGeneratorStrategyBuilder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimpleTests {

	private static final Logger LOGGER = LogManager.getLogger(SimpleTests.class);

	@Test
	@Order(1)
	public void generate1000NamesNumberStrategy() {
		LOGGER.info("----- generate1000NamesNumberStrategy -----");
		var addNumberNameGenerator = ANameGeneratorStrategyBuilder.getInstance().wordsByName(1)
				// If you change the seed you will have to change the expected result
				.setSeed(TEST_SEED).setStrategy(ENameGenerationStrategy.ADD_NUMBER).build();
		var result = addNumberNameGenerator.generateRandomNames(1000);
		result.stream().forEach(LOGGER::debug);
		// Result will change if you execute this test method alone
		assertIterableEquals(result, THOUSAND_NAMES_NUMBER_EXPECTED);
	}

	@Test
	@Order(2)
	public void generate1000NamesWordStrategy() {
		LOGGER.info("----- generate1000NamesWordStrategy -----");
		var addWordNameGenerator = ANameGeneratorStrategyBuilder.getInstance().wordsByName(1)
				// If you change the seed you will have to change the expected result
				.setSeed(TEST_SEED).setStrategy(ENameGenerationStrategy.ADD_WORD).build();
		var result = addWordNameGenerator.generateRandomNames(1000);
		result.stream().forEach(LOGGER::debug);
		// Result will change if you execute this test method alone
		assertIterableEquals(result, THOUSAND_NAMES_WORDS_EXPECTED);
	}

	@Test
	@Order(3)
	public void replace100NamesNumberStrategy() {
		LOGGER.info("----- replace100NamesNumberStrategy -----");
		var addNumberNameGenerator = ANameGeneratorStrategyBuilder.getInstance().wordsByName(1)
				// If you change the seed you will have to change the expected result
				.setSeed(TEST_SEED).setStrategy(ENameGenerationStrategy.ADD_NUMBER).build();
		var result = addNumberNameGenerator.generateNewRandomNames(INITIAL_NAMES);

		result.entrySet().stream()
				.forEach(e -> LOGGER.debug(String.format("\"%s\" --> \"%s\",", e.getKey(), e.getValue())));

		// Result will change if you execute this test method alone
		assertEquals(result, REPLACED_NAMES_NUMBER);
	}

	@Test
	@Order(4)
	public void replace100NamesWordStrategy() {
		LOGGER.info("----- replace100NamesWordStrategy -----");
		var addNumberNameGenerator = ANameGeneratorStrategyBuilder.getInstance().wordsByName(1)
				// If you change the seed you will have to change the expected result
				.setSeed(TEST_SEED).setStrategy(ENameGenerationStrategy.ADD_WORD).build();
		var result = addNumberNameGenerator.generateNewRandomNames(INITIAL_NAMES);
		result.entrySet().stream().forEach(e -> LOGGER.debug(String
				.format("new AbstractMap.SimpleEntry<String, String>(\"%s\", \"%s\"),", e.getKey(), e.getValue())));
		// Result will change if you execute this test method alone
		assertEquals(result, REPLACED_NAMES_WORD);
	}
}
