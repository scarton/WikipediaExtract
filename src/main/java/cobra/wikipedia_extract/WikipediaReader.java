package cobra.wikipedia_extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>An Iterator over the Wikipedia XML Dump. Returns an Article object per acceptable page.
 * Not thread-safe.</p>
 * @author Steve Carton (stephen.e.carton@usdoj.gov)
 * Jan 15, 2016
 *
 */
public class WikipediaReader implements Iterable<Article>, Iterator<Article> {
	private final static Logger logger = LoggerFactory.getLogger(WikipediaReader.class);
	private XMLStreamReader reader;
	private int LIMIT =  Integer.MAX_VALUE;
	private int c = 0;
	private boolean hasNext = true;
	private Map<String, Boolean> okTypes;

	public WikipediaReader(File src) throws FileNotFoundException, XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		reader = factory.createXMLStreamReader(new FileInputStream(src));
	}

	public Article next() {
		Article currArticle = null;
		StringBuilder tagContent = new StringBuilder();
		Stack<String> parentage = new Stack<>();
		try {
			while (hasNext()) {
				int event = reader.next();
				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					tagContent = new StringBuilder();
					parentage.push(reader.getLocalName());
					if ("page".equals(reader.getLocalName())) {
						currArticle = new Article(c++);
						currArticle.setOkTypes(okTypes);
					}
					break;

				case XMLStreamConstants.CHARACTERS:
					String txt = reader.getText();
					tagContent.append(txt);
					break;

				case XMLStreamConstants.END_ELEMENT:
					if (parentage.size()>0)
						parentage.pop();
					switch (reader.getLocalName()) {
					case "page":
						// logger.debug("Returning article {}",
						// currArticle.toString());
						if (c > LIMIT)
							this.hasNext=false;
						return currArticle;
					case "ns":
						currArticle.type = tagContent.toString();
						break;
					case "id":
						if (parentage.peek().equals("page"))
							currArticle.id = Integer.parseInt(tagContent.toString());
//						else 
//							logger.debug("Ignoring ID with parentage: {}",parentage);
						break;
					case "title":
						currArticle.title = tagContent.toString();
						break;
					case "text":
						currArticle.markup = tagContent.toString();
						break;
					}
					break;

				case XMLStreamConstants.START_DOCUMENT:
					break;
				case XMLStreamConstants.END_DOCUMENT:
					logger.debug("End of Document");
					this.hasNext=false;
					break;
				}
			}
		} catch (XMLStreamException e) {
			logger.error(e.getMessage());
		}
		return new Article(0,okTypes);
	}

	public Iterator<Article> iterator() {
		return this;
	}

	public boolean hasNext() {
		boolean r = false;
		try {
			r = reader.hasNext();
		} catch (XMLStreamException e) {
			logger.error(e.getMessage());
		}
//		logger.debug("hasNext: {}",r);
		return r&&this.hasNext;
	}

	public void setLIMIT(int lIMIT) {
		LIMIT = lIMIT;
	}

	public void setOkTypes(String ... types) {
		this.okTypes = Constants.makeOkTypes(types);
	}
}
