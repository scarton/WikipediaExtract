package cobra.wikipedia_extract;

import java.io.Serializable;

/**
 * <p></p>
 * @author Steve Carton (stephen.e.carton@usdoj.gov)
 * Jan 20, 2016
 *
 */
public class LabeledDocument implements Serializable {
	private static final long serialVersionUID = -6114719489482975448L;
	public long id;
	public String text;
	public double label;

	public LabeledDocument(long id, String val, double lbl) {
		this.id = id;
		this.text=val;
		this.label=lbl;
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

	public double getLabel() {
		return label;
	}

	public void setLabel(double lbl) {
		this.label = lbl;
	}

}
