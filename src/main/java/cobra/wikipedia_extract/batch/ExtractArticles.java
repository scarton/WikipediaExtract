package cobra.wikipedia_extract.batch;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.mylyn.wikitext.core.parser.DocumentBuilder;
import org.eclipse.mylyn.wikitext.core.parser.MarkupParser;
import org.eclipse.mylyn.wikitext.core.util.ServiceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wikipedia.miner.model.Article;
import org.wikipedia.miner.model.Category;
import org.wikipedia.miner.model.Page.PageType;
import org.wikipedia.miner.model.Wikipedia;
import org.wikipedia.miner.util.PageIterator;
import org.xml.sax.SAXException;

import com.sleepycat.je.EnvironmentLockedException;

import cobra.wikipedia_extract.WordExtractor;

public class ExtractArticles {
	final static Logger logger = LoggerFactory.getLogger(ExtractArticles.class);
	final static int INDEX_LIMIT = 5; // Integer.MAX_VALUE;

	public String parseByLanguage(String wikiText) {

		StringWriter writer = new StringWriter();
		DocumentBuilder builder = new WordExtractor(writer);
		MarkupParser parser = new MarkupParser(ServiceLocator.getInstance().getMarkupLanguage("MediaWiki"), builder);
		parser.parse(wikiText);
		return writer.toString();
	}

	public static void main(String[] args) throws EnvironmentLockedException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, ParserConfigurationException, SAXException, IOException {
		ExtractArticles extractor = new ExtractArticles();
		Wikipedia wikipedia = new Wikipedia(new File(args[0]), false);
		logger.info("Article Browse Starting...");
		Category root = wikipedia.getRootCategory();
		logger.debug("Root Category: {}", root.getTitle());
		PageIterator it = wikipedia.getPageIterator(PageType.article);
		for (int i = 0; i < INDEX_LIMIT && it.hasNext(); i++) {
			Article p = (Article) it.next();
			logger.debug("Article {}: {}", i, p.getTitle());
			Category[] cats = p.getParentCategories();
			StringBuilder sb = new StringBuilder();
			for (Category cat : cats) {
				if (sb.length() > 0)
					sb.append(", ");
				sb.append(cat.getTitle());
			}
			logger.debug("Categories: {}", sb.toString());
			logger.debug("{}", p.getMarkup());
			logger.debug("{}", extractor.parseByLanguage(p.getMarkup()));
		}
		logger.info("Article Browse Ending...");
	}

}
