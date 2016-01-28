package cobra.wikipedia_extract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Article {
	private final static Logger logger = LoggerFactory.getLogger(Article.class);
	private static Pattern excludeTest = Pattern.compile("^#redirect.*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static Pattern catPat = Pattern.compile("^.*?category:.*$", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	private Map<String,Boolean> okTypes;
	private WikiParser stripper = new WikiParser();
	private static ArrayList<PatRep> prePatterns;
	static {
		prePatterns = new ArrayList<>();
		prePatterns.add(new PatRep("(={2,6}.+={2,6})", "\n$1"));
		prePatterns.add(new PatRep("\n{2,}", "\n"));
		prePatterns.add(new PatRep("<.[^(><.)]+>", ""));
		prePatterns.add(new PatRep("\\{\\{.+?\\}\\}", "", Pattern.DOTALL));
	}
	private static ArrayList<PatRep> postPatterns;
	static {
		postPatterns = new ArrayList<>();
		postPatterns.add(new PatRep("'{2,}", ""));
		postPatterns.add(new PatRep("\\[\\[", ""));
		postPatterns.add(new PatRep("\\]\\]", ""));
		postPatterns.add(new PatRep("\\n{2,}", "\n",Pattern.DOTALL));
//		postPatterns.add(new PatRep("\\{\\{.+$", ""));
//		postPatterns.add(new PatRep("^.+\\}\\}", ""));
	}


	public List<String> categories;
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
	public Article(int c, Map<String,Boolean> okTypes) {
		this.counter = c;
		this.okTypes=okTypes;
	}
	public boolean keep() {
		if (markup==null)
			return false;
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
//		logger.debug("Categories: {}", sb.toString());
		return sb.toString();
	}

	@Override
	public String toString() {
		return id + " " + title + " (" + type + ")";
	}
	public void preParse() {
		for (PatRep pr : prePatterns) {
			this.markup = pr.p.matcher(this.markup).replaceAll(pr.r);
		}
	}
	public void parse() {
		stripper.parseByLanguage(id, this.markup);
		this.text = stripper.getArticleText();
		this.categories=stripper.getCategories();
	}
	public void postParse() {
		for (PatRep pr : postPatterns) {
			this.text = pr.p.matcher(this.text).replaceAll(pr.r);
		}
	}
	public void parseSentences() {
		ParseSentences parser = new ParseSentences();
		this.sentences = parser.parseSentences(this.text);
	}
	public String fn() {
		return id + ".txt";
	}
	public String getTitleCat() {
		String cat = title.replace("Category:","");
		return cat.trim().toLowerCase();
	}
	public String out() {
		this.preParse();
		this.parse();
		this.postParse();
		return text;
	}
	public String sout() {
		this.preParse();
		this.parse();
		this.postParse();
		this.parseSentences();
		StringBuilder sb = new StringBuilder(title+".\n");
		for (String sent : sentences) {
			if (sb.length() > 0)
				sb.append("\n");
			sb.append(sent);
		}
		return sb.toString();
	}

	public String mout() {
		return id + "\n" + 
				Constants.types.get(type) + "\n" + 
				title + "\n\n" + 
				markup;
	}
	public void setOkTypes(Map<String, Boolean> okTypes) {
		this.okTypes = okTypes;
	}
}