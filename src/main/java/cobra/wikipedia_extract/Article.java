package cobra.wikipedia_extract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Article {
	private final static Logger logger = LoggerFactory.getLogger(Article.class);
	private static Pattern excludeTest = Pattern.compile("^#redirect.*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static Pattern catPat = Pattern.compile("^.*?category:.*$", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static Map<String, String> types;
	private WordExtractor stripper = new WordExtractor();

	static {
		types = new HashMap<>();
		types.put("-2", "Media");
		types.put("-1", "Special");
		types.put("0", "Article");
		types.put("1", "Talk");
		types.put("2", "User");
		types.put("3", "User talk");
		types.put("4", "Wikipedia");
		types.put("5", "Wikipedia talk");
		types.put("6", "File");
		types.put("7", "File talk");
		types.put("8", "MediaWiki");
		types.put("9", "MediaWiki talk");
		types.put("10", "Template");
		types.put("11", "Template talk");
		types.put("12", "Help");
		types.put("13", "Help talk");
		types.put("14", "Category");
		types.put("15", "Category talk");
		types.put("828", "Module");
		types.put("829", "Module talk");
		types.put("2300", "Gadget");
		types.put("2301", "Gadget talk");
		types.put("2302", "Gadget definition");
		types.put("2303", "Gadget definition talk");
		types.put("2600", "Topic");
	}

	private static Map<String, Boolean> okTypes;

	static {
		okTypes = new HashMap<>();
		okTypes.put("0", true);
		okTypes.put("14", true);
		okTypes.put("2600", true);
	}

	private List<String> categories;
	public int counter=0;
	public int id;
	public String title;
	public String text;
	public String markup;
	public String type="";
	private List<String> sentences;

	public Article(int c) {
		this.counter = c;
	}
	public Article() {
	}
	public boolean keep() {
		boolean cats = catPat.matcher(markup).matches();
		boolean redir = excludeTest.matcher(markup).matches();
//		logger.debug("{}: {}/{}",id,cats,redir);
		return okTypes.containsKey(type) && cats && !redir;
	}

	public String getCategories() {
		StringBuilder sb = new StringBuilder();
		for (String cat : categories) {
			if (sb.length() > 0)
				sb.append("\n");
			sb.append(cat);
		}
		logger.debug("Categories: {}", sb.toString());
		return sb.toString();
	}

	@Override
	public String toString() {
		return id + " " + title + " (" + type + ")";
	}
	public void clean() {
		stripper.parseByLanguage(id, this.markup);
		this.text = stripper.getArticleText();
		this.text = this.text.replaceAll("([A-Za-z0-9])\\s*([\\!@#\\$%\\^&\\*\\(\\)_\\+-=\\{\\}:\";'<>,\\.])", "$1$2");
		this.text = this.text.replaceAll("'''", "");
		this.text = this.text.replaceAll("''", "");
		this.text = this.text.replaceAll("\\*", "");
		this.categories=stripper.getCategories();
	}
	public void parseSentences() {
		ParseSentences parser = new ParseSentences();
		this.sentences = parser.parseSentences(this.text);
	}
	public String fn() {
		return id + ".txt";
	}
	public String out() {
		this.clean();
		return text;
	}

	public String sout() {
		this.clean();
		this.parseSentences();
		StringBuilder sb = new StringBuilder(title+".\n\n");
		for (String sent : sentences) {
			if (sb.length() > 0)
				sb.append("\n\n");
			sb.append(sent);
		}
		return sb.toString();
	}

	public String mout() {
		return id + "\n" + 
				types.get(type) + "\n" + 
				title + "\n\n" + 
				markup;
	}
}