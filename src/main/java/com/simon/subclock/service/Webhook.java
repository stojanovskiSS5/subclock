package com.simon.subclock.service;

import com.simon.subclock.repository.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Webhook {
    private final Logger logger = LoggerFactory.getLogger(Webhook.class);
    private final Store store;

    @Autowired
    public Webhook(Store store) {
        this.store = store;
    }

    @Scheduled(cron="*/1 * * * * MON-FRI")
    public void sendFrequencyToWebhook(){
        store.getStore().forEach((key, value)->{
            value.cronJobSeconds(value.getCronJobSeconds()+1);
            if(value.getCronJobSeconds()>value.getFrequency()) {
                logger.info("Sending something to some url with frequency {}", value.getFrequency());
                value.cronJobSeconds(0);
            }
        });
    }
}
