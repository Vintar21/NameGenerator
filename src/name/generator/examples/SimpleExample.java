package name.generator.examples;

import static name.generator.utils.LoggingUtils.SEPARATOR;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import name.generator.NameGenerator;
import name.generator.NameGeneratorBuilder;
import name.generator.enums.strategies.ENameGenerationStrategy;;

public class SimpleExample {

	private static final Logger LOGGER = LogManager.getLogger(SimpleExample.class);

	public static void main(String[] args) {
		var addWordNameGenerator = NameGeneratorBuilder.getInstance().wordsByName(1).setSeed("MyAwesomeSeed")
				.setStrategy(ENameGenerationStrategy.ADD_WORD).build();

		var addNumberNameGenerator = NameGeneratorBuilder.getInstance().wordsByName(1).setSeed("MyOtherAwesomeSeed")
				.setStrategy(ENameGenerationStrategy.ADD_NUMBER).build();

		generateNames(addWordNameGenerator, 500000);

		// generateNames(addNumberNameGenerator, 1000);

		List<String> initialNames = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
				"o", "a", "b", "c", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
		// replaceNames(addWordNameGenerator, initialNames);

		// replaceNames(addNumberNameGenerator, initialNames);
	}

	private static void generateNames(NameGenerator nameGenerator, int n) {
		LOGGER.info(SEPARATOR);
		LOGGER.info("{} Random Names - {}", n, nameGenerator);
		nameGenerator.generateRandomNames(n).stream().forEach(LOGGER::info);
	}

	private static void replaceNames(NameGenerator nameGenerator, List<String> initialNames) {
		LOGGER.info(SEPARATOR);
		LOGGER.info("New Random Names - {}", nameGenerator);
		nameGenerator.generateNewRandomNames(initialNames)
				.forEach((key, value) -> LOGGER.info(String.format("%s -> %s", key, value)));
	}
}
