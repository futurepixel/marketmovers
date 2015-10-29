package io.marketmovers.models;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mikechabot on 10/28/15.
 */
@Document
public class TestStock extends AbstractMongoDocument {

    private String ticker;
    private String name;
    private int numberOfStocks;

    public TestStock(String ticker, String name, int numberOfStocks) {
        this.ticker = ticker;
        this.name = name;
        this.numberOfStocks = numberOfStocks;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfStocks() {
        return numberOfStocks;
    }

    public void setNumberOfStocks(int numberOfStocks) {
        this.numberOfStocks = numberOfStocks;
    }
}
