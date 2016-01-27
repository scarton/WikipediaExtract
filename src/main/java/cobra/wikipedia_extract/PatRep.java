package cobra.wikipedia_extract;

import java.util.regex.Pattern;

public class PatRep {
	Pattern p;
	String r;
	public PatRep(String p, String r) {
		this.p = Pattern.compile(p);
		this.r = r;
	}
	public PatRep(String p, String r, int rule) {
		this.p = Pattern.compile(p, rule);
		this.r = r;
	}
}
