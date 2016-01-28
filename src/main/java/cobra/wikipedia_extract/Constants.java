package cobra.wikipedia_extract;

import java.util.HashMap;
import java.util.Map;

/**
 * Wikipedia XML File constants
 * @author steve
 *
 */
public class Constants {
	private Constants(){}
	public static Map<String, Boolean> okTypes;

	static {
		okTypes = new HashMap<>();
		okTypes.put("0", true);
		okTypes.put("14", true);
		okTypes.put("2600", true);
	}
	
	public static Map<String,Boolean> makeOkTypes(String ... types) {
		if (types==null || types.length==0)
			return okTypes;
		Map<String,Boolean> ok = new HashMap<>();
		for (String type : types) {
			ok.put(type, true);
		}
		return ok;
	}
	public static Map<String, String> types;

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


}
