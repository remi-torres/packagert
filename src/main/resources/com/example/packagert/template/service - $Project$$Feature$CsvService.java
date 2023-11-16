
import org.springframework.core.io.Resource;

/**
 * Service used to handle CSV import/export on $project$ $feature$.
 *
 */
@Service
public class $Project$$Feature$CsvService
		implements MaintenanceCsvExportService<$Project$$Feature$Entity, $Project$$Feature$CsvBean>,
		MaintenanceCsvImportService<$Project$$Feature$CsvBean> {

	@Autowired
	private $Project$$Feature$Service $feature$Service;
	@Autowired
	private $Project$$Feature$MappingService $feature$MappingService;
					
	/* GENERATED : TO IMPLEMENT */
	
	@Override
	public List<$Project$$Feature$CsvBean> inputStreamToCsvBeans(final long id, final InputStream inputStream) {
		final MaintenanceCsvParser<$Project$$Feature$CsvBean> $feature$CsvParser = new MaintenanceCsvParser<>();
		final List<$Project$$Feature$CsvBean> $feature$CsvBeans = $feature$CsvParser
				.inputStreamToCsvBeans($Project$$Feature$CsvBean.class, inputStream);

		return $feature$CsvBeans;
	}

	@Override
	public String getCsvHeader() {
		return $Project$$Feature$CsvBean.HEADER;
	}

	@Override
	public NamedResource entitiesToCsvResource(final long id,
			final List<$Project$$Feature$Entity> entities) {
		return exportToCsv($feature$MappingService.mapEntitiesToCsvBeans(entities),
				String.format("$feature$_%d.csv", id));
				/* GENERATED : TO IMPLEMENT */
	}

	
 }