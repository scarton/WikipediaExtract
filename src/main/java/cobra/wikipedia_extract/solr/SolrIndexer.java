package cobra.wikipedia_extract.solr;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;

public class SolrIndexer {
	final static Logger logger = LoggerFactory.getLogger(SolrIndexer.class);
	private HttpSolrClient server;
	public SolrIndexer(String baseURL) {
		server = new HttpSolrClient(baseURL);
	}
	public void index(String text, String guid) throws IOException, SolrServerException {
		index(text, guid, null);
	}
	public void index(String text, String guid, List<String> categories) throws SolrServerException, IOException {
		SolrInputDocument  doc = new SolrInputDocument ();
		doc.addField("doctext", text);
		doc.addField("guid", guid);
		if (categories!=null) {
			doc.addField("categories", categories);
			logger.debug("{}",Joiner.on(", ").join(categories));
		}
		server.add(doc);
	}
	public void eraseIndex() throws SolrServerException, IOException {
		deleteByQuery("*:*");
	}
	public void deleteByQuery(String q) throws SolrServerException, IOException {
		server.deleteByQuery(q);
	}
	public void testQuery(String qstr) throws IOException, SolrServerException {
	    SolrQuery query = new SolrQuery();
	    query.setQuery(qstr);
//	    query.addFilterQuery("cat:electronics","store:amazon.com");
//	    query.setFields("id","price","merchant","cat","store");
	    query.setStart(0);    
	    query.set("defType", "edismax");

	    QueryResponse response = server.query(query);
	    SolrDocumentList results = response.getResults();
	    for (int i = 0; i < results.size(); ++i) {
	      System.out.println(results.get(i));
	    }
    }
	public void commit() throws SolrServerException, IOException {
		server.commit();
	}
	public void close() throws IOException {
		server.close();
	}
}
