package cobra.wikipedia_extract.batch;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cobra.wikipedia_extract.Article;
import cobra.wikipedia_extract.IO;
import cobra.wikipedia_extract.WikipediaReader;

/**
 * <p>Processes an XML dump of wikipedia pages. 
 * Writes out text files with the relevant extracted data 
 * for just the ones we want (Articles, Categories).</p>
 * @author Steve Carton (stephen.e.carton@usdoj.gov)
 * Jan 19, 2016
 *
 */
public class WikiSplit {
	private final static Logger logger = LoggerFactory.getLogger(WikiSplit.class);
	private static final int MAX = Integer.MAX_VALUE; 
//	private static final int MAX = 100;  
	private static Map<String, Boolean> okTypes;

	static {
		okTypes = new HashMap<>();
		okTypes.put("0", true);
		okTypes.put("14", true);
		okTypes.put("2600", true);
	}
	

	public static void main(String[] args) throws XMLStreamException, IOException {
		logger.info("Starting Wikipedia Extract {}", args[0]);
		System.out.println("Starting Wikipedia Extract: " + args[0]);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		File src = new File(args[0]);
		File outP = new File(args[1]);
		outP.mkdirs();
		WikipediaReader reader = new WikipediaReader(src);
		reader.setOkTypes("0","14","2600");
 		Article p;
 		int c=0;
		while (c<MAX && reader.hasNext()) {
			p = reader.next();
//			logger.debug("Article: {}", p);
			if (p.keep()) {
				try {
					logger.debug("Article: {} - {}",p.id, p.title);
					File outF = new File(outP.getPath() + '/' + p.fn());
					IO.putContent(outF, p.sout());
					if (p.counter%1000==0)
						System.out.println(""+p.counter);
//					File moutF = new File(outP.getPath() + '/' + p.id + ".mk.txt");
//					IO.putContent(moutF, p.markup);
					File coutF = new File(outP.getPath() + '/' + p.id + ".cat.txt");
					IO.putContent(coutF, p.getCategories());
					c++;
				} catch (Exception e) {
					logger.error(e.getMessage());
					logger.debug(IO.trace(e));
					System.exit(1);
				}
			} else {
				// logger.info("Ignoring article: {}",currArticle.toString());
				// logger.info(currArticle.text);
			}
		};
		stopWatch.stop();
		logger.info("Ended Wikipedia Extract... Elapsed Time: {}",DurationFormatUtils.formatDuration(stopWatch.getTime(), "HH:mm:ss.S"));
		System.out.println("Ended Wikipedia Extract... Elapsed Time: "+DurationFormatUtils.formatDuration(stopWatch.getTime(), "HH:mm:ss.S"));

	}

/*
		Stream<Article> stream = StreamUtils.asStream(reader);
		WordExtractor extractor = new WordExtractor(outP);
		stream.forEach(p -> {
//			logger.debug("Article: {}", p);
			if (p.keep()) {
				try {
					extractor.parseByLanguage(p.id,p.markup);
					if (p.counter%1000==0)
						logger.info("{}",p.counter);
					File outF = new File(outP.getPath() + '/' + p.id + "mk.txt");
					IO.putContent(outF, p.markup);
				} catch (Exception e) {
					logger.error(e.getMessage());
					logger.debug("{}",e.getStackTrace());
				}
			} else {
				// logger.info("Ignoring article: {}",currArticle.toString());
				// logger.info(currArticle.text);
			}
		});

 */
}
