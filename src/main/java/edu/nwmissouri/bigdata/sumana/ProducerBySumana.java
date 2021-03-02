package edu.nwmissouri.bigdata.sumana;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Sumana Reddy <S538360@nwmissouri.edu>.
 */
public class ProducerBySumana {
    public static void main(String[] args) throws InterruptedException {
        String topic = "kafka";
        String brokers = "localhost:9092";
        String StringSerializer = "org.apache.kafka.common.serialization.StringSerializer";

        Random rnd = new Random();

        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer);


        KafkaProducer producer = new KafkaProducer<String, String>(config);

        while (true) {
            LocalDateTime runtime = LocalDateTime.now();
            String ip = "192.168.2." + rnd.nextInt(255);
            String msg = runtime + " www.example.com " + ip;
            System.out.println(msg);
            ProducerRecord record = new ProducerRecord<String, String>(topic, ip, msg);
            producer.send(record);
            Thread.sleep(100);
        }
    }
}
