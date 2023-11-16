
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.airbus.as.maintenanceops.study.dao.MaintenanceStudyAircraftTypeEntity;

/**
 * An implementation of a $project$ $feature$ entity.
 */
 @Entity
 @Table(name = "$Project$$Feature$")
 public class $Project$$Feature$Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@ManyToOne
	@JoinColumn(name = "studyAircraftTypeId", referencedColumnName = "id", nullable = false)
	private MaintenanceStudyAircraftTypeEntity studyAircraftType;
	
	/**
	 * Default constructor.
	 */
	public $Project$$Feature$Entity() {
		// default constructor
	}
	
	// GENERATED : TO IMPLEMENT
 }