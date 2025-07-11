package name.generator.enums.words;

public enum EFlowers implements EWords {
	ALSTROEMERIA,
	AMARYLLIS,
	ANEMONE,
	ASTILBE,
	AZALEA,
	BEGONIA,
	BELLFLOWER,
	BLUEBELL,
	BOUGAINVILLEA,
	BUTTERCUP,
	CAMELLIA,
	CARNATION,
	CHRYSANTHEMUM,
	CLEMATIS,
	CROCUS,
	CYCLAMEN,
	DAFFODIL,
	DAHLIA,
	DAISY,
	DELPHINIUM,
	FORSYTHIA,
	FREESIA,
	FUCHSIA,
	GARDENIA,
	GERANIUM,
	GLADIOLUS,
	HEATHER,
	HELIANTHUS,
	HIBISCUS,
	HOLLYHOCK,
	HONEYSUCKLE,
	HYACINTH,
	HYDRANGEA,
	IRIS,
	JACARANDA,
	JASMINE,
	LARKSPUR,
	LAVENDER,
	LILAC,
	LILY,
	LOBELIA,
	LOTUS,
	MAGNOLIA,
	MARIGOLD,
	MIMOSA,
	MORNING_GLORY,
	NASTURTIUM,
	ORCHID,
	PANSY,
	PASSIONFLOWER,
	PEONY,
	PETUNIA,
	PHLOX,
	POPPY,
	PRIMROSE,
	RANUNCULUS,
	SNAPDRAGON,
	SNOWDROP,
	STOCK,
	SUNFLOWER,
	SWEET_PEA,
	TIGER_LILY,
	TULIP,
	VERBENA,
	VIOLET,
	WISTERIA,
	ZINNIA;
	;

	@Override
	public EWordCategory getCategory() {
		return EWordCategory.FLOWER;
	}

}
