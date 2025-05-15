package name.generator.enums.strategies;

/*
 * Describe the strategy to apply if the same name is generated twice
 * - ADD_WORD: Add an additional word at the end of the name (increase the initial number of words by name).
 * Can add several words at the name if needed.
 * For example: Elephant --> ElephantBanana --> ElephantBananaCar
 * - ADD_NUMBER: Add a number at the end of the name and increment it while the name still exists. Starting at 2.
 * For example: Elephant --> Elephant2 --> Elephant3
 */
public enum ENameGenerationStrategy {
  ADD_WORD,
  ADD_NUMBER
}
