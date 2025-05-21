package name.generator.enums.words;

public enum ERpgRaces implements EWords {
	AASIMAR,
	DEMON,
	DRAGONBORN,
	DRAW,
	DWARF,
	ELF,
	GITHYANKI,
	GNOME,
	GOBLIN,
	HUMAN,
	HALFLIN,
	MIND_FLAYER,
	ORC,
	TIEFLING,
	VAMPIRE,
	ZOMBIE;

	@Override
	public EWordCategory getCategory() {
		return EWordCategory.RPG_RACES;
	}
}
