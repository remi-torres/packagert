
/**
 * List of all messages used in an $P$ $Feature$ context.
 */
public class $PF$Messages {

		public static class Api {
		public static final String STUDY_ID_IS_NULL_OR_NEGATIVE = "Study Id in API parameters must be strictly positive.";
		public static final String $FEATURE$_ID_IS_NULL_OR_NEGATIVE = "$Feature$ Id in API parameters must be strictly positive.";
		public static final String $FEATURE$_IDS_ARE_NULL_OR_NEGATIVE = "All given $Feature$ IDs %s must be strictly positive.";

		private Api() {
			// private constructor
		}
	}

	public static class Csv {

		// Here are all the messages used in an API context (ex : messages about the csv import or export)

		private Csv() {
			// private constructor
		}
	}

	public static class Dto {

		public static final String $FEATURE$_PAYLOAD_ID_IS_NOT_NULL = "$Feature$ id in the payload is a read-only value, it cannot be used in create and update requests.";

		private Dto() {
			// private constructor
		}
	}

	public static class Entity {

		public static final String $FEATURE$_NOT_FOUND_BY_STUDY_ID_AND_ID = "$Feature$ with the study id %d and the id %d is not found.";
		public static final String $FEATURE$_CANNOT_BE_SAVED = "$Feature$ cannot be saved.";

		private Entity() {
			// private constructor
		}
	}

	private $PF$Messages() {
		// private constructor
	}
	
}