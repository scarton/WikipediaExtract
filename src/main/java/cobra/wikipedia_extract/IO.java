package cobra.wikipedia_extract;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

/**
 * <p></p>
 * @author Steve Carton (stephen.e.carton@usdoj.gov)
 * Jan 12, 2016
 *
 */
public class IO {
	public static void putContent(File p, String s) throws IOException {
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(p)));
		writer.write(s);
		writer.close();
	}
	public static void putContent(File p, List<String> sa) throws IOException {
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(p)));
		for (String s : sa)
			writer.write(s+'\n');
		writer.close();
	}
	  /** 
	   * makes a String out of a JAVA stack trace 
	   *
	   * @param e Exception to trace
	   * @return resulting trace as a String
	   */
	  public static String trace(Throwable e) {
		if (e == null) return ("No StackTrace available on NULL exception.");
	    StringWriter sw = new StringWriter();
	    e.printStackTrace(new PrintWriter(sw));
	    return sw.toString();
	  }

}
