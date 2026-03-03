package com.thesis.vesselservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VesselProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendVesselRegistered(String imo) {
        kafkaTemplate.send("vessels-topic", imo).whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("✅ УСПЕХ: Сообщение [" + imo + "] отправлено в Кафку! Offset: " +
                        result.getRecordMetadata().offset());
            } else {
                System.err.println("❌ ОШИБКА: Не удалось отправить сообщение! Причина: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
}
