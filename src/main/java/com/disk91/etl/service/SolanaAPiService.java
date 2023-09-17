package com.disk91.etl.service;

import com.disk91.etl.EtlConfig;
import com.disk91.etl.data.itf.SolanaNftListRespItf;
import com.disk91.etl.data.itf.WalletHotspotList;
import com.disk91.etl.data.itf.sub.SolanaNft;
import fr.ingeniousthings.tools.ITNotFoundException;
import fr.ingeniousthings.tools.ITParseException;
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
import java.util.ArrayList;

@Service
public class SolanaAPiService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EtlConfig etlConfig;

    private int apiCredits;

    @PostConstruct
    public void init() {
        this.apiCredits = etlConfig.getSolanaApiCreditPerMinute();
    }

    @Scheduled(fixedRate = 60_000)
    public void addApiCredit() {
        // Helium credits
        this.apiCredits = etlConfig.getSolanaApiCreditPerMinute();
    }

    public boolean getOneCredit() {
        this.apiCredits--;
        if ( this.apiCredits > 0 ) {
            return true;
        }
        return false;
    }


    protected synchronized String getApiUrl() {
        return etlConfig.getSolanaApiUrl();
    }

    /**
     * Get Api Headers
     */
    protected HttpEntity<String> createHeaders(){

        HttpHeaders headers = new HttpHeaders();
        ArrayList<MediaType> accept = new ArrayList<>();
        accept.add(MediaType.APPLICATION_JSON);
        headers.setAccept(accept);
        headers.add(HttpHeaders.USER_AGENT,"disk91_stats/1.0");
        headers.add("x-api-key", etlConfig.getSolanaApiKey());
        HttpEntity<String> he = new HttpEntity<String>(headers);
        return he;

    }


    // Wallet Id expected is Solana, base58 encoded
    public WalletHotspotList getHotspotList(String walletId) throws ITNotFoundException, ITParseException {

        if ( ! this.getOneCredit() ) throw new ITParseException();

        RestTemplate restTemplate = new RestTemplate();
        String url="";
        try {
            HttpEntity<String> he = createHeaders();
            url = this.getApiUrl()+"/sol/v1/nft/compressed/read_all?network=mainnet-beta&wallet_address=";
            url += walletId;
            ResponseEntity<SolanaNftListRespItf> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            he,
                            SolanaNftListRespItf.class
                    );
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                if (responseEntity.getBody() != null) {
                    SolanaNftListRespItf response = responseEntity.getBody();
                    WalletHotspotList whl = new WalletHotspotList();
                    whl.init(walletId);
                    for ( SolanaNft nft : response.getResult().getNfts() ) {
                        whl.getHotspotsECCId().add(nft.getAttributes().getEcc_compact());
                    }
                    return whl;
                } else {
                    throw new ITNotFoundException();
                }
            } else {
                log.warn("Failed to get hotspot list from Solana with response ("+responseEntity.getStatusCode()+")");
                throw new ITParseException();
            }
        } catch (HttpClientErrorException e) {
            log.error("Solana API communication exception :" + e.getStatusCode() + "[" + e.getMessage() + "]");
            log.error("Related to wallet (1) for "+walletId);
            log.error("Url :"+url);
            throw new ITParseException();
        } catch (HttpServerErrorException e) {
            log.error("Solana API communication exception :" + e.getStatusCode() + "[" + e.getMessage() + "]");
            log.error("Related to wallet details (2) for "+walletId);
            throw new ITParseException();
        }
    }


}
