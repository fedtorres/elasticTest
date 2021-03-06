package service;


import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import static org.elasticsearch.index.query.QueryBuilders.*;

public class CountService {

    Client client;

    public CountService(Client client) {
        this.client = client;
    }

    public long getMatchAllQueryCount() {
        QueryBuilder query = matchAllQuery();
        System.out.println("getMatchAllQueryCount query =>"+ query.toString());
        long count = client.prepareSearch("prueba").setQuery(query).setSize(0).execute().actionGet().getHits().getTotalHits();
        return count;
    }


    public long getBoolQueryCount() {
        QueryBuilder query = boolQuery().must(
                termQuery("nombre","Pepe")
        ).must( termQuery("lugar","Argentina"));
        System.out.println("getBoolQueryCount query =>"+ query.toString());
        long count = client.prepareSearch("prueba").setQuery(query).setSize(0).execute().actionGet().getHits().getTotalHits();
        return count;
    }


    public long getPhraseQueryCount() {
        QueryBuilder query = matchPhraseQuery("name","satendra");
        System.out.println("getPhraseQueryCount query =>"+ query.toString());
        long count = client.prepareSearch("prueba").setQuery(query).setSize(0).execute().actionGet().getHits().getTotalHits();
        return count;
    }


}
