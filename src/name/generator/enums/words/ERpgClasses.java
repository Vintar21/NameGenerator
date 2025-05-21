package name.generator.enums.words;

public enum ERpgClasses implements EWords {
	ADVENTURER,
	ARCHER,
	ARTIFICER,
	BARBARIAN,
	BARD,
	BERSERKER,
	CLERIC,
	DRUID,
	FIGHTER,
	HEALER,
	KHAJIT,
	KING,
	MERCHANT,
	MONK,
	NECROMANCER,
	NOBLE,
	PALADIN,
	PRINCE,
	PRINCESS,
	QUEEN,
	RANGER,
	ROGUE,
	SORCERER,
	TANK,
	THIEF,
	WARDEN,
	WARLOCK,
	WARRIOR,
	WITCHER,
	WIZARD;

	@Override
	public EWordCategory getCategory() {
		return EWordCategory.RPG_RACES;
	}
}
