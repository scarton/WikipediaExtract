package cobra.wikipedia_extract.batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cobra.wikipedia_extract.WikiWords;
import cobra.wikipedia_extract.WikiParser;

public class WikiExtract {
	private final static Logger logger = LoggerFactory
			.getLogger(WikiExtract.class);

	public static void putContent(File p, String s) throws IOException {
		Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(p)));
		writer.write(s);
		writer.close();
	}

	public static void main(String[] args) throws XMLStreamException,
			IOException {
		logger.info("Starting Wikipedia Extract {}", args[0]);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		 File outP = new File(args[1]);
		 outP.mkdirs();
		Path dir = Paths.get(args[0]);
		WikiWords words = new WikiParser(outP);
		logger.info("The file tree for {}", dir.toAbsolutePath());
		try (Stream<Path> fileTree = Files.walk(dir).parallel()) {
			fileTree.forEach(words::parseByLanguage);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		stopWatch.stop();
		logger.info("Ended Wikipedia Extract... Elapsed Time: {}",
				DurationFormatUtils.formatDuration(stopWatch.getTime(),
				"HH:mm:ss.S"));

	}

	/*
 */
}
