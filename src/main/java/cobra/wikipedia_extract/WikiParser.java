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
	public class WikiParser extends DocumentBuilder implements WikiWords {

		final static Logger logger = LoggerFactory.getLogger(WikiParser.class);
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
		public String articleHtml;

		private int blockDepth = 0;
		
		public WikiParser(File outP) {
			this.outPath = outP;
		}
		public WikiParser() {}

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
		public void parseToHTML(int id, String text) {
			articleText = new StringBuilder(0);
			categories = new ArrayList<>();
			MarkupParser parser = new MarkupParser(ServiceLocator.getInstance().getMarkupLanguage("MediaWiki"));
			try {
				articleHtml = parser.parseToHtml(text);
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
			logger.debug("ACRONYM:" + text + "," + definition); //$NON-NLS-1$ //$NON-NLS-2$
		}

		@Override
		public void beginBlock(BlockType type, Attributes attributes) {
			++blockDepth;
			logger.debug("BLOCK START[" + blockDepth + "]:" + type+" - "+attributes.getTitle()); //$NON-NLS-1$ //$NON-NLS-2$
			this.addEOS();
		}

		@Override
		public void beginDocument() {
//			logger.debug("DOCUMENT START"); //$NON-NLS-1$
			articleText = new StringBuilder(0);
		}

		@Override
		public void beginHeading(int level, Attributes attributes) {
			logger.debug("HEADING START:" + level); //$NON-NLS-1$
			this.addEOS();
		}

		@Override
		public void beginSpan(SpanType type, Attributes attributes) {
			logger.debug("SPAN START:" + type); //$NON-NLS-1$
		}

		@Override
		public void characters(String text) {
			logger.debug("CHARACTERS:" + text); //$NON-NLS-1$
			articleText.append(text);
		}

		@Override
		public void charactersUnescaped(String text) {
			logger.debug("HTML LITERAL:" + text); //$NON-NLS-1$
		}

		@Override
		public void endBlock() {
			logger.debug("END BLOCK[" + blockDepth + "]"); //$NON-NLS-1$ //$NON-NLS-2$
			this.addEOS();
			--blockDepth;
		}

		@Override
		public void endDocument() {
			logger.debug("END DOCUMENT"); //$NON-NLS-1$
		}

		@Override
		public void endHeading() {
			logger.debug("END HEADING"); //$NON-NLS-1$
			this.addEOS();
		}

		@Override
		public void endSpan() {
			logger.debug("END SPAN"); //$NON-NLS-1$
		}

		@Override
		public void entityReference(String entity) {
			logger.debug("ENTITY: " + entity); //$NON-NLS-1$
			articleText.append((entities.containsKey(entity)?entities.get(entity):"#"+entity+"#"));
		}

		@Override
		public void image(Attributes attributes, String url) {
			logger.debug("IMAGE: " + url); //$NON-NLS-1$
		}

		@Override
		public void imageLink(Attributes linkAttributes, Attributes imageAttributes, String href, String imageUrl) {
			logger.debug("IMAGE LINK: " + href + ", " + imageUrl); //$NON-NLS-1$ //$NON-NLS-2$

		}

		@Override
		public void lineBreak() {
			logger.debug("LINE BREAK"); //$NON-NLS-1$
		}

		@Override
		public void link(Attributes attributes, String hrefOrHashName, String text) {
			logger.debug("LINK: "+text); //$NON-NLS-1$ //$NON-NLS-2$
			String[] tk = text.split("\\|");
			String ltext = tk[tk.length-1];
			logger.debug("Link Value: "+ltext); //$NON-NLS-1$ //$NON-NLS-2$
			if (text.startsWith("Category:")) {
				String c = catPat.matcher(ltext).replaceAll("$1").trim();
				if (c.length()>0)
					categories.add(c);
			} else {
				articleText.append(ltext);
			}
		}
		private void addEOS() {
//			logger.debug("End of Sent adder: {}",articleText.toString());
			if (articleText!=null) {
				for (int i=articleText.length()-1; i>=0; i--) {
					if (articleText.charAt(i)!=' ') {
						if ("\n].!?".indexOf(articleText.charAt(i))==-1) {
							articleText.append('.');
						} 
						break;
					} else {
						articleText.deleteCharAt(i);
					}
				}
				articleText.append("\n");
			}
		}
		public String getArticleText() {
			return articleText.toString();
		}

		public List<String> getCategories() {
			return categories;
		}
	}
