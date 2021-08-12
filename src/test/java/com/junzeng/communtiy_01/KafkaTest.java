package com.junzeng.communtiy_01;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommuntiyApplication.class)
public class KafkaTest {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void testKafka() throws InterruptedException {
        kafkaProducer.sendMessage("test","你好");
        kafkaProducer.sendMessage("test","在吗");
        Thread.sleep(1000 * 10);
        kafkaProducer.sendMessage("test","你好");
        kafkaProducer.sendMessage("test","在吗");
    }


}

@Component
class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String topic,String content) {
        kafkaTemplate.send(topic,content);
    }
}

@Component
class KafkaConsumer {

    @KafkaListener(topics={"test"})
    public void handleMessage(ConsumerRecord record) {
        System.out.println(record.value());
    }
}
