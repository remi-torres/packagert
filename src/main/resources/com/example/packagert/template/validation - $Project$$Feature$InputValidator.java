
/**
 * Validator for the $feature$ input. No verification on the existence of related entities in DB is done.
 *
 */
public class $Project$$Feature$InputValidator extends AsMaintenanceStudyBaseInputValidator
		implements DtoInputValidatorInterface<$Project$$Feature$Dto> {

	@Override
	public void validateInputDto(final $Project$$Feature$Dto dto) {
		validateNull(dto.getId(), $Project$$Feature$Messages.Dto.$FEATURE$_PAYLOAD_ID_IS_NOT_NULL);
		validateAircraftType(dto.getAcType());
	}

}
