package cobra.wikipedia_extract;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Builds a list of MD5 hashes of sentences that repeat regularly.</p>
 * @author Steve Carton (stephen.e.carton@usdoj.gov)
 * Dec 22, 2015
 *
 */
public class ParseSentences {
	private final static Logger logger = LoggerFactory.getLogger(ParseSentences.class);
	private SentenceDetectorME sentenceDetector;
	private SentenceModel sentModel;
	
	public ParseSentences()  {
		InputStream modelIn = this.getClass().getClassLoader().getResourceAsStream("en-sent.bin");
		//logger.debug("Loading sentence model...");
		try {
			sentModel = new SentenceModel(modelIn);
			sentenceDetector = new SentenceDetectorME(sentModel);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	public List<String> parseSentences(String text) {
		List<String> scents = new ArrayList<>();
    	Span[] sentences = sentenceDetector.sentPosDetect(text);
    	for (Span sentence : sentences) {
    		CharSequence senText = sentence.getCoveredText(text);
//    		logger.debug("Sentence {}/{}: {}",sentence.getStart(),sentence.getEnd(),senText);
    		scents.add(senText.toString());
	    }
    	return scents;
	}
}