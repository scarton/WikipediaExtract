package cobra.wikipedia_extract;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.mylyn.wikitext.core.parser.Attributes;
import org.eclipse.mylyn.wikitext.core.parser.DocumentBuilder;
import org.eclipse.mylyn.wikitext.core.parser.MarkupParser;
import org.eclipse.mylyn.wikitext.core.util.ServiceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


	/**
	 * @author Steve Carton
	 * @since 1.0
	 */
	public class WordExtractor extends DocumentBuilder implements WikiWords {

		final static Logger logger = LoggerFactory.getLogger(WordExtractor.class);
		private static Map<String, String> entities;
		private static Pattern catPat = Pattern.compile("category:(.*)", Pattern.CASE_INSENSITIVE);
		static {
			entities = new HashMap<>();
			entities.put("nbsp", " ");
			entities.put("ndash", "-");
		}

		private List<String> categories;
		private File outPath;
		private StringBuilder articleText;

		private int blockDepth = 0;
		
		public WordExtractor(File outP) {
			this.outPath = outP;
		}
		public WordExtractor() {}

		public void parseByLanguage(Path src) {
			String fn = FilenameUtils.getBaseName(src.toFile().getName()).trim();
			articleText = new StringBuilder(0);
			categories = new ArrayList<>();
			MarkupParser parser = new MarkupParser(ServiceLocator.getInstance().getMarkupLanguage("MediaWiki"), this);
			try {
				parser.parse(new String(Files.readAllBytes(src)));
				if (outPath!=null) {
					this.writeText(new File(outPath.getPath()+'/'+fn+".txt"));
					this.writeCategories(new File(outPath.getPath()+'/'+fn+".cat.txt"));
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		public void parseByLanguage(int id, String text) {
			articleText = new StringBuilder(0);
			categories = new ArrayList<>();
			MarkupParser parser = new MarkupParser(ServiceLocator.getInstance().getMarkupLanguage("MediaWiki"), this);
			try {
				parser.parse(text);
				if (outPath!=null) {
					this.writeText(new File(outPath.getPath()+'/'+id+".txt"));
					this.writeCategories(new File(outPath.getPath()+'/'+id+".cat.txt"));
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.debug("{}",IO.trace(e));
			}
		}
		public void writeText(File p) throws IOException {
//			logger.debug(this.getArticleText());
			IO.putContent(p, this.getArticleText());
		}
		public void writeCategories(File p) throws IOException {
//			logger.debug("Categories: {}",this.categories);
			IO.putContent(p, this.categories);

		}
		
		@Override
		public void acronym(String text, String definition) {
			logger.info("ACRONYM:" + text + "," + definition); //$NON-NLS-1$ //$NON-NLS-2$
		}

		@Override
		public void beginBlock(BlockType type, Attributes attributes) {
			++blockDepth;
			logger.info("BLOCK START[" + blockDepth + "]:" + type+" - "+attributes.getTitle()); //$NON-NLS-1$ //$NON-NLS-2$
		}

		@Override
		public void beginDocument() {
//			logger.info("DOCUMENT START"); //$NON-NLS-1$
			articleText = new StringBuilder(0);
		}

		@Override
		public void beginHeading(int level, Attributes attributes) {
			logger.info("HEADING START:" + level); //$NON-NLS-1$
		}

		@Override
		public void beginSpan(SpanType type, Attributes attributes) {
			logger.info("SPAN START:" + type); //$NON-NLS-1$
		}

		@Override
		public void characters(String text) {
			logger.info("CHARACTERS:" + text); //$NON-NLS-1$
			articleText.append(text);
		}

		@Override
		public void charactersUnescaped(String text) {
			logger.info("HTML LITERAL:" + text); //$NON-NLS-1$
		}

		@Override
		public void endBlock() {
			logger.info("END BLOCK[" + blockDepth + "]"); //$NON-NLS-1$ //$NON-NLS-2$
			--blockDepth;
		}

		@Override
		public void endDocument() {
			logger.info("END DOCUMENT"); //$NON-NLS-1$
		}

		@Override
		public void endHeading() {
			logger.info("END HEADING"); //$NON-NLS-1$
		}

		@Override
		public void endSpan() {
			logger.info("END SPAN"); //$NON-NLS-1$
		}

		@Override
		public void entityReference(String entity) {
			logger.info("ENTITY: " + entity); //$NON-NLS-1$
			articleText.append((entities.containsKey(entity)?entities.get(entity):"#"+entity+"#"));
		}

		@Override
		public void image(Attributes attributes, String url) {
			logger.info("IMAGE: " + url); //$NON-NLS-1$
		}

		@Override
		public void imageLink(Attributes linkAttributes, Attributes imageAttributes, String href, String imageUrl) {
			logger.info("IMAGE LINK: " + href + ", " + imageUrl); //$NON-NLS-1$ //$NON-NLS-2$

		}

		@Override
		public void lineBreak() {
			logger.info("LINE BREAK"); //$NON-NLS-1$
		}

		@Override
		public void link(Attributes attributes, String hrefOrHashName, String text) {
			logger.info("LINK: "+text); //$NON-NLS-1$ //$NON-NLS-2$
			if (text.startsWith("Category:")) {
				String c = catPat.matcher(text).replaceAll("$1").trim();
				if (c.length()>0)
					categories.add(c);
			} else {
				articleText.append(text);
				articleText.append(" ");
			}
		}

		public String getArticleText() {
			return articleText.toString();
		}

		public List<String> getCategories() {
			return categories;
		}
	}
