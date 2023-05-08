package com.disk91.etl.service;

import com.disk91.etl.EtlConfig;
import com.disk91.etl.helium.legacy.model.hotspot.HotspotDetail;
import com.disk91.etl.helium.legacy.model.hotspot.HotspotResponse;
import com.disk91.etl.helium.legacy.model.hotspot.HotspotTotalDetail;
import com.disk91.etl.helium.legacy.model.hotspot.HotspotTotalResponse;
import fr.ingeniousthings.tools.ITNotFoundException;
import fr.ingeniousthings.tools.ITParseException;
import fr.ingeniousthings.tools.Now;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class LegacyEtlService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EtlConfig etlConfig;

    private int apiCredits;

    @PostConstruct
    public void init() {
        this.apiCredits = etlConfig.getHeliumLegacyApiCredits();
    }

    @Scheduled(fixedRate = 60_000)
    public void addApiCredit() {
        // Helium credits
        this.apiCredits = etlConfig.getHeliumLegacyApiCredits();
    }

    public boolean getOneCredit() {
        this.apiCredits--;
        if ( this.apiCredits > 0 ) {
            return true;
        }
        return false;
    }

    public class TransactionResponse {
        public long firstBlock;
        public long firstBlockTime;
        public long lastBlock;
        public long lastBlockTime;
        public int  insertedBlocks;

        public TransactionResponse() {
            firstBlock = 0;
            firstBlockTime = 0;
            lastBlock = 0;
            lastBlockTime = 0;
            insertedBlocks = 0;
        }
    }

    protected synchronized String getApiUrl(boolean hackit) {
        return etlConfig.getHeliumLegacyApiEndpoint();
    }

    /**
     * Get Api Headers
     */
    protected HttpEntity<String> createHeaders(boolean withAuth){

        HttpHeaders headers = new HttpHeaders();
        ArrayList<MediaType> accept = new ArrayList<>();
        accept.add(MediaType.APPLICATION_JSON);
        headers.setAccept(accept);
        headers.add(HttpHeaders.USER_AGENT,"disk91_stats/1.0");
        if ( withAuth && etlConfig.getHeliumLegacyApiUser() != null && etlConfig.getHeliumLegacyApiUser().length() > 2 ) {
            String auth = etlConfig.getHeliumLegacyApiUser() + ":" + etlConfig.getHeliumLegacyApiPass();
            byte[] encodedAuth = Base64.getEncoder().encode(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            headers.add(HttpHeaders.AUTHORIZATION, authHeader);
        }
        HttpEntity<String> he = new HttpEntity<String>(headers);
        return he;

    }


    public HotspotDetail getHotspotDetails(String hotspotID, boolean fromHelium) throws ITNotFoundException, ITParseException {

        if ( ! this.getOneCredit() ) throw new ITParseException();

        RestTemplate restTemplate = new RestTemplate();
        String url="";
        try {
            HttpEntity<String> he;
            if ( !fromHelium ) {
                he = createHeaders(true);
                url = getApiUrl(true) + "/hotspots/" + hotspotID;
            } else {
                he = createHeaders(false);
                url = "https://api.helium.io/v1/hotspots/" + hotspotID;
            }
            ResponseEntity<HotspotResponse> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            he,
                            HotspotResponse.class
                    );
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                if (responseEntity.getBody() != null) {
                    HotspotResponse response = responseEntity.getBody();
                    HotspotDetail d = response.getData();
                    return d;

                } else {
                    throw new ITNotFoundException();
                }
            } else {
                log.warn("Failed to get hotspot details from Helium with response ("+responseEntity.getStatusCode()+")");
                throw new ITParseException();
            }
        } catch (HttpClientErrorException e) {
            log.error("Helium backend communication exception :" + e.getStatusCode() + "[" + e.getMessage() + "]");
            log.error("Related to Hotspost details (1) for "+hotspotID);
            log.error("Url :"+url);
            throw new ITParseException();
        } catch (HttpServerErrorException e) {
            log.error("Helium backend communication exception :" + e.getStatusCode() + "[" + e.getMessage() + "]");
            log.error("Related to Hotspost details (2) for "+hotspotID);
            throw new ITParseException();
        }
    }

    public long getHotspotTotalReward(String hotspotID) throws ITNotFoundException, ITParseException {

        //log.info("Retrieve Hotspot total rewards for "+hotspotID);
        if ( ! this.getOneCredit() ) throw new ITParseException();

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpEntity<String> he = createHeaders(true);

            String url = getApiUrl(true) + "/hotspots/"
                    + hotspotID + "/rewards/sum?min_time=2020-01-01T00:00:00Z&max_time=2030-12-31T23:59:59Z";
            ResponseEntity<HotspotTotalResponse> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            he,
                            HotspotTotalResponse.class
                    );
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                if (responseEntity.getBody() != null) {
                    HotspotTotalResponse response = responseEntity.getBody();
                    HotspotTotalDetail d = response.getData();
                    return d.getSum();

                } else {
                    log.warn("Empty response");
                    throw new ITNotFoundException();
                }
            } else {
                log.warn("Failed to get hotspot details from Helium with response ("+responseEntity.getStatusCode()+")");
                throw new ITParseException();
            }
        } catch (HttpClientErrorException e) {
            log.error("Helium backend communication exception :" + e.getStatusCode() + "[" + e.getMessage() + "]");
            log.error("Related to Hotspost total Rewards (1) for "+hotspotID);
            throw new ITParseException();
        } catch (HttpServerErrorException e) {
            log.error("Helium backend communication exception :" + e.getStatusCode() + "[" + e.getMessage() + "]");
            log.error("Related to Hotspost total Rewards (2) for "+hotspotID);
            throw new ITParseException();
        }
    }



}
