package view;


public class YahooResponse {
    private Query query;

    public YahooResponse() {
    }

    public YahooResponse(Query query) {
        this.query = query;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }


}
