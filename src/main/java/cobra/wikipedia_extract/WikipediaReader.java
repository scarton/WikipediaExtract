package cobra.wikipedia_extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cobra.wikipedia_extract.batch.WikiSplit;

public class WikipediaReader {
	private final static Logger logger = LoggerFactory.getLogger(WikipediaReader.class);
	private XMLStreamReader reader;

	public WikipediaReader(File src) throws FileNotFoundException, XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		reader = factory.createXMLStreamReader(new FileInputStream(src));
	}

	public Article parse4Articles() {
		Article currArticle = null;
		StringBuilder tagContent = new StringBuilder();;
		try {
			while (reader.hasNext()) {
				int event = reader.next();
				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					tagContent = new StringBuilder();
					if ("page".equals(reader.getLocalName())) {
						currArticle = new Article();
					}
					break;

				case XMLStreamConstants.CHARACTERS:
					String txt = reader.getText().trim();
					tagContent.append(txt);
					break;

				case XMLStreamConstants.END_ELEMENT:
					switch (reader.getLocalName()) {
					case "page":
//						logger.debug("Returning article {}", currArticle.toString());
						return currArticle;
					case "ns":
						currArticle.type = tagContent.toString();
						break;
					case "id":
						currArticle.id = tagContent.toString();
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
				}
			}
		} catch (XMLStreamException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
