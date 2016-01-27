package cobra.wikipedia_extract.batch;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wikipedia.miner.model.Article;
import org.wikipedia.miner.model.Category;
import org.wikipedia.miner.model.Page.PageType;
import org.wikipedia.miner.model.Wikipedia;
import org.wikipedia.miner.util.PageIterator;
import org.xml.sax.SAXException;

import cobra.wikipedia_extract.WikiParser;

import com.sleepycat.je.EnvironmentLockedException;

/**
 * <p>Extracts article text and categories from Wikipedia-Miner DB</p>
 * @author Steve Carton (stephen.e.carton@usdoj.gov)
 * Jan 15, 2016
 *
 */
public class ExtractArticles {
	final static Logger logger = LoggerFactory.getLogger(ExtractArticles.class);
	final static int INDEX_LIMIT = 5; // Integer.MAX_VALUE;

	public static void main(String[] args) throws EnvironmentLockedException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, ParserConfigurationException, SAXException, IOException {
		Wikipedia wikipedia = new Wikipedia(new File(args[0]), false);
		logger.info("Article Browse Starting...");
		File outP = new File(args[1]);
		outP.mkdirs();
		WikiParser words = new WikiParser(outP);
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
			words.parseByLanguage(p.getId(),p.getMarkup());
			logger.debug("{}", words.getArticleText());
		}
		logger.info("Article Browse Ending...");
	}

}
