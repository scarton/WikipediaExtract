package cobra.wikipedia_extract.batch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cobra.wikipedia_extract.solr.SolrIndexer;

public class WikiIndex {
	private final static Logger logger = LoggerFactory.getLogger(WikiIndex.class);
	
	private static List<String> getCats(File cf) throws FileNotFoundException {
		List<String> cats = new ArrayList<>();
		Scanner s = new Scanner(cf);
		while (s.hasNextLine()){
		    cats.add(s.nextLine());
		}
		s.close();		
		return cats;
	}
	public static void main(String[] args) throws XMLStreamException, IOException, SolrServerException {
		logger.info("Starting Wikipedia Indexing {}", args[0]);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		SolrIndexer indexer = new SolrIndexer("http://localhost:8983/solr/wikipedia");
		indexer.eraseIndex();
		File srcF = new File(args[0]);
		File[] listOfFiles = srcF.listFiles(
				(File file) -> !file.getName().endsWith (".cat.txt") && file.getName().endsWith(".txt"));
		for (int i = 0; i< Math.min(1000, listOfFiles.length); i++) {
			if (listOfFiles[i].isFile()) {
				logger.debug("File " + listOfFiles[i].getName());
				String text = FileUtils.readFileToString(listOfFiles[i]);
				if (text!=null) {
					String id = FilenameUtils.removeExtension(listOfFiles[i].getName()).trim();
					File cf = new File(listOfFiles[i].getParent()+"/"+id+".cat.txt");
					logger.debug("Cats Path: {}",cf.getAbsolutePath());
					if (cf.exists() && cf.isFile()) {
						indexer.index(text, id, getCats(cf));
					} else {
						indexer.index(text, id);
					}
					logger.debug("indexing {}",id);
					if (i%500==0)
						indexer.commit();
				}
			} 
		}
		indexer.commit();
		indexer.close();
		stopWatch.stop();
		logger.info("Ended Wikipedia Indexing... Elapsed Time: {}",
				DurationFormatUtils.formatDuration(stopWatch.getTime(),
				"HH:mm:ss.S"));

	}

	/*
 */
}
