package net.mkcz.kraken.samples;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

import net.mkcz.kraken.api.request.KrakenRequestBuilder;

import org.json.JSONObject;

import java.util.Date;

import static java.util.Arrays.asList;


/**
 * Created by cintezam on 05/03/16.
 */
public class SimpleTicker {
    public static void main(String[] args) throws UnirestException, InterruptedException {
        final String baseUrl = "https://api.kraken.com";
        final String version = "0";
        final KrakenRequestBuilder krakenRequestBuilder = KrakenRequestBuilder.releaseTheKraken(baseUrl, version);
        final String bitceur = "XXBTZEUR";
        final HttpRequest xxbtzeur = krakenRequestBuilder.publicRequest().ticker(asList(bitceur)).get();
        do {
            final JSONObject result = xxbtzeur.asJson().getBody().getObject().getJSONObject("result").getJSONObject(bitceur);
            final double lastTradePrice = result.getJSONArray("c").getDouble(0);
            final double askPrice = result.getJSONArray("a").getDouble(0);
            final double bidPrice = result.getJSONArray("b").getDouble(0);
            final String format = String.format("[%s] XBT/EUR: ask: %4.3f bid: %4.3f trade: %4.3f", new Date().toString(),
                    askPrice, bidPrice, lastTradePrice);
            System.out.println(format);
            Thread.sleep(1000);
        }while(true);
    }
}