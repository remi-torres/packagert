
/**
 * Service used to map $project$ $feature$ entities to DTOs and vice versa, and entities to CsvBeans.
 */
 @Service
 public class $Project$$Feature$MappingService implements 
	MaintenanceDtoToEntityMappingServiceInterface<$Project$$Feature$Dto, $Project$$Feature$Entity>,
	MaintenanceEntityToDtoMappingServiceInterface<$Project$$Feature$Entity, $Project$$Feature$Dto>,
	MaintenanceEntityToCsvBeanMappingServiceInterface<$Project$$Feature$Entity, $Project$$Feature$CsvBean>{
				
	@Autowired
	private GeneralAircraftTypeMappingService aircraftTypeMappingService;
	/* GENERATED : TO IMPLEMENT */
	
	@Override
	public $Project$$Feature$Entity mapDtoToEntity(final $Project$$Feature$Dto dto) {
		final $Project$$Feature$Entity entity = new $Project$$Feature$Entity();
		entity.setId(dto.getId());
		/* GENERATED : TO IMPLEMENT */
		return entity;
	}

	/**
	 * Map given $Project$$Feature$dto to  $Project$$Feature$entity.
	 *
	 * @return the mapped $f$ entity
	 */
	public $Project$$Feature$Entity mapDtoToCompleteEntity(final MaintenanceStudyAircraftTypeEntity studyAircraftType, final $Project$$Feature$Dto dto) {
		final  $Project$$Feature$Entity entity = mapDtoToEntity(dto);
		entity.setStudyAircraftType(studyAircraftType);
		/* GENERATED : TO IMPLEMENT */
		return entity;
	}	

	
	@Override
	public $Project$$Feature$Dto mapEntityToDto(final $Project$$Feature$Entity entity) {
		final $Project$$Feature$Dto dto = new $Project$$Feature$Dto();
		dto.setId(entity.getId());
		dto.setAircraftType(aircraftTypeMappingService.mapEntityToBaseDto(entity.getStudyAircraftType().getAircraftType()));
		/* GENERATED : TO IMPLEMENT */
		return dto;
	}
	
	@Override
	public $Project$$Feature$CsvBean mapEntityToCsvBean(final $Project$$Feature$Entity entity) {
		return new $Project$$Feature$CsvBean(/* GENERATED : TO IMPLEMENT */);
	}
	
 }