package cobra.wikipedia_extract;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Category {
	final static Logger logger = LoggerFactory.getLogger(Category.class);
	public String category="";
	public Set<String> parents = new TreeSet<>();
	public Set<String> children = new TreeSet<>();
	public boolean isRoot=false;
	
	/**
	 * Creates/maintains a set of children and parents for a topic/category.
	 * @param cat
	 * @param parents
	 */
	public Category(String cat) {
		logger.debug("New topic/category: {}",cat);
		this.category=cat;
	}
	public void addParents(List<String> parents) {
		logger.debug("Adding parents to cat: {} - {}",this.category, parents);
		if (parents == null || parents.size()==0) {
			logger.debug("Root Category: {}",this.category);
			isRoot=true;
		} else {
			for (String parent : parents) {
				this.parents.add(parent);
			}
		}
	}
	public void addChild(String cat) {
		logger.debug("Adding child to cat: {} - {}",this.category, cat);
		this.children.add(cat);
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.parents.size()>0) {
			for (String p : this.parents) {
				if (this.children.size()>0) {
					for (String c : this.children) {
						sb.append(p+">"+this.category+">"+c+"\n");
					}
				} else {
					sb.append(p+">"+this.category+"\n");
				}
			}
		} else {
			for (String c : this.children) {
				sb.append(this.category+">"+c+"\n");
			}
		}
		return sb.toString();
	}
}
