
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service used to manage CRUD operations on $project$ $feature$ entities.
 */
 @Service
 public class $Project$$Feature$Service {
	 
	@Autowired
	private $Project$$Feature$Repository $feature$Repository;
	
	/**
	 * Creates a $feature$ entity in database.
	 * 
	 * @param $feature$Entity the $feature$ entity to save
	 * @return the saved entity
	 */
	public $Project$$Feature$Entity create$Feature$(final $Project$$Feature$Entity $feature$Entity) {
		return saveEntity($feature$Entity);
	}
	
	/**
	 * Gets all the $feature$s linked to a study and an aircraftType name.
	 *
	 * @param studyId the id of the study
	 * @param aircraftTypeNameLike part of the name of the aircraftType to find
	 * @return the $feature$s linked to a study
	 */
	public List<$Project$$Feature$Entity> get$Feature$s(final long studyId, final String aircraftTypeNameLike) {
		return $feature$Repository.findAllByStudyAircraftTypeStudyIdAndStudyAircraftTypeAircraftTypeNameContainingIgnoreCase(studyId, aircraftTypeNameLike);
	}

	/**
	 * Gets a $feature$ by study id and $feature$ id.
	 * 
	 * @param studyId the id of the study
	 * @param $feature$Id  the id of the $feature$
	 * @return the found $feature$
	 */
	public $Project$$Feature$Entity get$Feature$(final long studyId, final long $feature$Id) {
		return $feature$Repository.findByStudyAircraftTypeStudyIdAndId(studyId, $feature$Id).orElseThrow(
				() -> new AsMaintenanceModelEntityNotFoundException($Project$$Feature$Messages.Entity.$FF$_NOT_FOUND_BY_STUDY_ID_AND_ID, studyId,
						$feature$Id));
	}


	/**
	 * Update a $feature$ entity in the database.
	 * 
	 * @param studyId	id of the study the $feature$ belongs to
	 * @param $feature$Id		id of the $feature$ entity to update
	 * @param newValues$Feature$ $feature$ entity containing the values to update
	 * @return the updated entity
	 */
	@Transactional
	public $Project$$Feature$Entity update$Feature$(final long studyId, final long $feature$Id,
			final $Project$$Feature$Entity newValues$Feature$) {
		// Fetch the existing entity from database
		final $Project$$Feature$Entity $feature$EntityToUpdate = get$Feature$(studyId, $feature$Id);
		// Update it with the new incoming values
		$feature$EntityToUpdate.setStudyAircraftType(newValues$Feature$.getStudyAircraftType());
		/* GENERATED : TO IMPLEMENT */
		// and save it
		return saveEntity($feature$EntityToUpdate);
	}

	/**
	 * Deletes the $feature$ entities with the given ids and belonging to study with the given id.
	 * 
	 * @return ids of the entities that could not be deleted
	 */
	@Transactional
	public Set<Long> delete$Feature$s(final long studyId, final Set<Long> $feature$Ids) {
		final Set<$Project$$Feature$Entity> deleted$Feature$s = $feature$Repository.deleteByStudyAircraftTypeStudyIdAndIdIn(studyId,
				$feature$Ids);
		final Set<Long> deletedIds = deleted$Feature$s.stream().map($Project$$Feature$Entity::getId)
				.collect(Collectors.toSet());
		return SetUtils.difference($feature$Ids, deletedIds);
	}

	protected $Project$$Feature$Entity saveEntity(final $Project$$Feature$Entity $feature$Entity) {
		try {
			return $feature$Repository.save($feature$Entity);
		} catch (NonTransientDataAccessException ex) {
			throw DbExceptionUtil.manageDatabaseException(ex,
					$Project$$Feature$Messages.Entity.$FEATURE$_CANNOT_BE_SAVED);
		}
	}
	
 }