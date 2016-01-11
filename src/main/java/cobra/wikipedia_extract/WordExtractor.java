package cobra.wikipedia_extract;



	import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.mylyn.wikitext.core.parser.Attributes;
	import org.eclipse.mylyn.wikitext.core.parser.DocumentBuilder;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;

	/**
	 * @author David Green
	 * @since 1.0
	 */
	public class WordExtractor extends DocumentBuilder {

		final static Logger logger = LoggerFactory.getLogger(DocumentBuilder.class);
		private static Map<String, String> entities;

		static {
			entities = new HashMap<>();
			entities.put("nbsp", " ");
			entities.put("ndash", "-");
		}

		private int blockDepth = 0;
		private StringBuilder articleText;
		Writer out;
		public WordExtractor(Writer out) {
			this.out = out;
		}

		@Override
		public void acronym(String text, String definition) {
			logger.info("ACRONYM:" + text + "," + definition); //$NON-NLS-1$ //$NON-NLS-2$
		}

		@Override
		public void beginBlock(BlockType type, Attributes attributes) {
			++blockDepth;
//			logger.info("BLOCK START[" + blockDepth + "]:" + type); //$NON-NLS-1$ //$NON-NLS-2$
		}

		@Override
		public void beginDocument() {
//			logger.info("DOCUMENT START"); //$NON-NLS-1$
			articleText = new StringBuilder(0);
		}

		@Override
		public void beginHeading(int level, Attributes attributes) {
//			logger.info("HEADING START:" + level); //$NON-NLS-1$
		}

		@Override
		public void beginSpan(SpanType type, Attributes attributes) {
//			logger.info("SPAN START:" + type); //$NON-NLS-1$
		}

		@Override
		public void characters(String text) {
//			logger.info("CHARACTERS:" + text); //$NON-NLS-1$
			articleText.append(text);
		}

		@Override
		public void charactersUnescaped(String text) {
//			logger.info("HTML LITERAL:" + text); //$NON-NLS-1$
		}

		@Override
		public void endBlock() {
//			logger.info("END BLOCK[" + blockDepth + "]"); //$NON-NLS-1$ //$NON-NLS-2$
			--blockDepth;
		}

		@Override
		public void endDocument() {
//			logger.info("END DOCUMENT"); //$NON-NLS-1$
			try {
				out.write(articleText.toString());
			} catch (IOException e) {
				logger.error(e.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}

		@Override
		public void endHeading() {
//			logger.info("END HEADING"); //$NON-NLS-1$
		}

		@Override
		public void endSpan() {
//			logger.info("END SPAN"); //$NON-NLS-1$
		}

		@Override
		public void entityReference(String entity) {
//			logger.info("ENTITY: " + entity); //$NON-NLS-1$
			articleText.append((entities.containsKey(entity)?entities.get(entity):"#"+entity+"#"));
		}

		@Override
		public void image(Attributes attributes, String url) {
//			logger.info("IMAGE: " + url); //$NON-NLS-1$
		}

		@Override
		public void imageLink(Attributes linkAttributes, Attributes imageAttributes, String href, String imageUrl) {
//			logger.info("IMAGE LINK: " + href + ", " + imageUrl); //$NON-NLS-1$ //$NON-NLS-2$

		}

		@Override
		public void lineBreak() {
//			logger.info("LINE BREAK"); //$NON-NLS-1$
		}

		@Override
		public void link(Attributes attributes, String hrefOrHashName, String text) {
//			logger.info("LINK: " + hrefOrHashName + ", " + text); //$NON-NLS-1$ //$NON-NLS-2$
			articleText.append(text);
		}

		public StringBuilder getArticleText() {
			return articleText;
		}

	}
