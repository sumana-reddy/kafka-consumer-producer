package edu.nwmissouri.bigdata.sumana;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Rahul Shukla <rahul.shukla@synerzip.com> on 6/7/16.
 */
public class Consumer {

    public static void main(String[] args) throws InterruptedException {
        String topic = "kafka";
        String brokers = "localhost:9092";
        String stringSerializer = "org.apache.kafka.common.serialization.StringDeserializer";

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, stringSerializer);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, stringSerializer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        KafkaConsumer consumer = new KafkaConsumer<String, String>(config);

        HashSet<String> topics = new HashSet<>();
        topics.add(topic);
        consumer.subscribe(topics);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(200);
            if (!records.isEmpty()) {
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record);
                }
            }
        }
    }

}
