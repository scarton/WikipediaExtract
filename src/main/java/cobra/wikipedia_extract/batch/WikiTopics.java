package cobra.wikipedia_extract.batch;

import java.io.File;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cobra.wikipedia_extract.Article;
import cobra.wikipedia_extract.CatCollector;
import cobra.wikipedia_extract.Category;
import cobra.wikipedia_extract.IO;
import cobra.wikipedia_extract.WikipediaReader;

/**
 * <p>Processes an XML dump of wikipedia pages, extracting and building a topic/category tree/graph. 
 * @author Steve Carton (stephen.e.carton@usdoj.gov)
 * Jan 19, 2016
 *
 */
public class WikiTopics {
	private final static Logger logger = LoggerFactory.getLogger(WikiTopics.class);
	private static final int MAX = Integer.MAX_VALUE; 
//	private static final int MAX = 10000;  

	public static void main(String[] args) throws XMLStreamException, IOException {
		logger.info("Starting Wikipedia Topic Extract {}", args[0]);
		System.out.println("Starting Wikipedia Topic Extract: " + args[0]);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		File src = new File(args[0]);
		WikipediaReader reader = new WikipediaReader(src);
		reader.setOkTypes("14","2600");
 		Article p;
 		CatCollector cats = new CatCollector();
 		int c=0;
		while (c<MAX && reader.hasNext()) {
			p = reader.next();
			if (p.keep()) {
				try {
					p.parse();
					cats.addCategory(p.getTitleCat(), p.categories);
					c++;
					logger.debug("\n");
				} catch (Exception e) {
					logger.error(e.getMessage());
					logger.error(IO.trace(e));
					System.exit(1);
				}
			}
			if (c%1000==0)
				System.out.println(""+p.counter);
		};
		logger.debug("Roots: {}, Categories: {}",cats.rsize(),cats.size());
		for (String root : cats.getRoots()) {
			logger.debug("Root: {}",root);
		}
		for (String cat : cats.getCategories()) {
			logger.debug("{}: {}",cat,cats.getCategory(cat).toString());
		}
		stopWatch.stop();
		logger.info("Ended Wikipedia Topic Extract... Elapsed Time: {}",DurationFormatUtils.formatDuration(stopWatch.getTime(), "HH:mm:ss.S"));
		System.out.println("Ended Wikipedia Topic Extract... Elapsed Time: "+DurationFormatUtils.formatDuration(stopWatch.getTime(), "HH:mm:ss.S"));

	}
}
