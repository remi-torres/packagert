
	@Autowired
	private $Project$$Feature$Service $feature$Service;
	@Autowired
	private $Project$$Feature$MappingService $feature$MappingService;
	@Autowired
	private MaintenanceFleetService fleetService;
	@Autowired
	private $Project$$Feature$CsvService $feature$CsvService;

	/* GET ENTITIES */
		final $Project$$Feature$InputValidator validator = new $Project$$Feature$InputValidator();
		validator.validateStrictlyPositive(studyId, $Project$$Feature$Messages.Api.STUDY_ID_IS_NULL_OR_NEGATIVE);
		validator.throwExceptionIfNeeded();

		final List<$Project$$Feature$Entity> $feature$s = $feature$Service.get$Feature$s(studyId, acType);
		return $feature$MappingService.mapEntitiesToDtos($feature$s);


	/* GET ENTITY */	
		final $Project$$Feature$InputValidator validator = new $Project$$Feature$InputValidator();
		validator.validateStrictlyPositive(studyId, $Project$$Feature$Messages.Api.STUDY_ID_IS_NULL_OR_NEGATIVE);
		validator.validateStrictlyPositive($feature$Id,
				MaintenanceTurnAroundTimeMessages.Api.$FF$_ID_IS_NULL_OR_NEGATIVE);
		validator.throwExceptionIfNeeded();

		final $Project$$Feature$Entity $feature$ = $feature$Service.get$Feature$(studyId, $feature$Id);
		return $feature$MappingService.mapEntityToDto($feature$);


	/* CREATE ENTITY */	
		final $Project$$Feature$InputValidator validator = new $Project$$Feature$InputValidator();
		validator.validateStrictlyPositive(studyId, $Project$$Feature$Messages.Api.STUDY_ID_IS_NULL_OR_NEGATIVE);
		validator.validateInputDto($feature$Dto);
		validator.throwExceptionIfNeeded();

		final MaintenanceStudyAircraftTypeEntity studyAircraftType = fleetService.getStudyAircraftType(studyId,
				$feature$Dto.getAcType().getId());

		$Project$$Feature$Entity $feature$ = $feature$MappingService.mapDtoToCompleteEntity(studyAircraftType,
				$feature$Dto);
		$feature$ = $feature$Service.create$Feature$($feature$);
		return $feature$MappingService.mapEntityToDto($feature$);


	/* UPDATE ENTITTY */
		final $Project$$Feature$InputValidator validator = new $Project$$Feature$InputValidator();
		validator.validateStrictlyPositive(studyId, $Project$$Feature$Messages.Api.STUDY_ID_IS_NULL_OR_NEGATIVE);
		validator.validateStrictlyPositive($feature$Id,
				$Project$$Feature$Messages.Api.$FF$_ID_IS_NULL_OR_NEGATIVE);
		validator.validateInputDto($feature$Dto);
		validator.throwExceptionIfNeeded();

		final MaintenanceStudyAircraftTypeEntity studyAircraftType = fleetService.getStudyAircraftType(studyId,
				$feature$Dto.getAcType().getId());

		$Project$$Feature$Entity $feature$ = $feature$MappingService.mapDtoToCompleteEntity(studyAircraftType,
				$feature$Dto);
		$feature$ = $feature$Service.update$Feature$(studyId, $feature$Id, $feature$);
		return $feature$MappingService.mapEntityToDto($feature$);
	
	
	/* DELETE ENTITIES */
		final $Project$$Feature$InputValidator validator = new $Project$$Feature$InputValidator();
		validator.validateStrictlyPositive(studyId, $Project$$Feature$Messages.Api.STUDY_ID_IS_NULL_OR_NEGATIVE);
		validator.validateAllStrictlyPositive($feature$Ids,
				MaintenanceTurnAroundTimeMessages.Api.$FF$_IDS_ARE_NULL_OR_NEGATIVE);
		validator.throwExceptionIfNeeded();

		final Set<Long> notDeletedIds = $feature$Service.deleteTurnAroundTimes(studyId, $feature$Ids);
		return ResponseEntityUtil.createDeleteByIdsResponse(notDeletedIds);
		
	/* CSV TEMPLATE */
		final Resource csvTemplate = $feature$CsvService.getMaintenanceCsvTemplate();
		final String fileName = "$Feature$.csv";

		return ResponseEntityUtil.createResourceResponseEntity(csvTemplate, fileName);	
		
	/* EXPORT CSV */
	
	
