package cobra.wikipedia_extract;

import java.io.Serializable;

/**
 * <p></p>
 * @author Steve Carton (stephen.e.carton@usdoj.gov)
 * Jan 20, 2016
 *
 */
public class Document implements Serializable {
	private static final long serialVersionUID = 5632733526423009468L;
	public long id;
	public String text;

	public Document(long id, String val) {
		this.id = id;
		this.text=val;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String val) {
		this.text = val;
	}

}
