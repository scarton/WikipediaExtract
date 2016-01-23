package cobra.wikipedia_extract;

import java.nio.file.Path;

/**
 * <p></p>
 * @author Steve Carton (stephen.e.carton@usdoj.gov)
 * Jan 12, 2016
 *
 */
@FunctionalInterface
public interface WikiWords {
	public void parseByLanguage(Path src);

}
