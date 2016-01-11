package cobra.wikipedia_extract.batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.stream.Stream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cobra.wikipedia_extract.Article;
import cobra.wikipedia_extract.WikipediaReader;

public class WikiSplit {
	private final static Logger logger = LoggerFactory.getLogger(WikiSplit.class);

	public static void putContent(File p, String s) throws IOException {
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(p)));
		writer.write(s);
		writer.close();
	}

	public static void main(String[] args) throws XMLStreamException, IOException {
		logger.info("Starting Wikipedia Extract {}", args[0]);
		File src = new File(args[0]);
		File outP = new File(args[1]);
		outP.mkdirs();
		WikipediaReader reader = new WikipediaReader(src);
		ExtractArticles extractor = new ExtractArticles();
		Stream<Article> stream = Stream.generate(() -> reader.parse4Articles());
		stream.forEach(p -> {
			logger.debug("Article: {}", p);
			if (p.keep()) {
				try {
					p.text = extractor.parseByLanguage(p.markup);
					File outF = new File(outP.getPath() + '/' + p.fn());
					putContent(outF, p.out());
//					outF = new File(outP.getPath() + '/' + p.id + "mk.txt");
//					putContent(outF, p.markup);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			} else {
				// logger.info("Ignoring article: {}",currArticle.toString());
				// logger.info(currArticle.text);
			}
		});
		logger.info("Ended Wikipedia Extract...");

	}


}
