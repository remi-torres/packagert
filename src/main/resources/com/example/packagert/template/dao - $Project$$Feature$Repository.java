

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for $project$ $feature$ entity.
 */
 /* GENERATED : TO IMPLEMENT */
 public interface $Project$$Feature$Repository extends JpaRepository<$Project$$Feature$Entity, Long> {
	
	
	/**
	 * Find a $feature$ by study id and $feature$ id.
	 * 
	 * @param studyId the id of the study
	 * @param $feature$Id  the id of the maintenance $feature$
	 * @return the found entity wrapped in an optional
	 */
	 /* GENERATED : TO IMPLEMENT */
	Optional<$Project$$Feature$Entity> findByStudyAircraftTypeStudyIdAndId(final long studyId, final long $feature$Id);

	/**
	 * Find all the $feature$s linked to a study and with a name like.
	 *
	 * @param studyId              the id of the study
	 * @param aircraftTypeNameLike part of the name of the aircraftType to find
	 * @return the $feature$s linked to a study and the name
	 */
	 /* GENERATED : TO IMPLEMENT */
	List<$Project$$Feature$Entity> findAllByStudyAircraftTypeStudyIdAndStudyAircraftTypeAircraftTypeNameContainingIgnoreCase(
			final long studyId, final String aircraftTypeNameLike);
	
	/**
	 * Deletes $feature$s linked to a study and with the given ids.
	 *
	 * @param studyId the id of the study
	 * @param $feature$Ids ids of the $feature$s to delete
	 * @return Deleted $feature$s
	 */
	 /* GENERATED : TO IMPLEMENT */
	Set<$Project$$Feature$Entity> deleteByStudyAircraftTypeStudyIdAndIdIn(final long studyId, final Set<Long> $feature$Ids);
 }