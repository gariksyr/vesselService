package com.thesis.vesselservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VesselProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendVesselRegistered(String imo) {
        kafkaTemplate.send("vessels-topic", imo);
    }
}
