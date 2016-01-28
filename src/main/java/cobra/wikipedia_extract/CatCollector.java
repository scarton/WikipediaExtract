/**
 * 
 */
package cobra.wikipedia_extract;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Collects Wikipedia Categories in a tree/graph form
 * @author steve
 *
 */
public class CatCollector {
	final static Logger logger = LoggerFactory.getLogger(CatCollector.class);
	// This map is an inversion - we get the entries by category with a list of parents.
	// We store in this tree by parent with a list of parents and list of children 
	private Map<String, Category> categories;
	private Set<String> roots;

	public CatCollector() {
		this.categories = new TreeMap<>();
		this.roots = new TreeSet<>();
	}
	/**
	 * Adds new or updates existing map entries for each parent to contain the category as a child.
	 * List of children is a TreeSet to sort the children on insertion.
	 * @param cat
	 * @param parents
	 */
	public void addCategory(String cat, List<String> parents) {
		if (!categories.containsKey(cat)) {
			Category c = new Category(cat);
			this.categories.put(cat, c);
			c.addParents(parents);
		} else {
			this.categories.get(cat).addParents(parents);
		}
		if (this.categories.get(cat).isRoot) {
			this.roots.add(cat);
		}
		for (String parent : parents) {
			if (categories.containsKey(parent)) {
				logger.debug("Adding as child to parent {}: {}",parent,cat);
				categories.get(parent).addChild(cat);
			} else {
				categories.put(parent, new Category(parent));
				categories.get(parent).addChild(cat);
			}
		}
	}
	public Category getCategory(String node) {
		return this.categories.get(node);
	}
	public String toString(String node) {
		return this.categories.get(node).toString();
	}
	public Set<String> getRoots() {
		return roots;
	}
	public int rsize() {
		return roots.size();
	}
	public int size() {
		return categories.size();
	}
	public Set<String> getCategories() {
		return categories.keySet();
	}
}
