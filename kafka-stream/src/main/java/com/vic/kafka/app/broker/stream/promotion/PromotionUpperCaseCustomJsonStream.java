package com.vic.kafka.app.broker.stream.promotion;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.vic.kafka.app.broker.message.PromotionMessage;
import com.vic.kafka.app.broker.serde.PromotionSerde;

@Configuration
public class PromotionUpperCaseCustomJsonStream {
	
	private static final Logger LOG=LoggerFactory.getLogger(PromotionUpperCaseCustomJsonStream.class);
		
	@Bean
	public KStream<String, PromotionMessage> kstreamPromotionUpperCase(StreamsBuilder builder){
		var stringSerde=Serdes.String();
		var jsonSerde=new PromotionSerde();
		
		//source
		KStream<String, PromotionMessage> sourceStream=builder.stream("t.commodity.promotion",
				Consumed.with(stringSerde, jsonSerde));
		//transform
		KStream<String, PromotionMessage> uppercaseStream=sourceStream.mapValues(this::uppercasePromotionCode);
		
		//Sink
		uppercaseStream.to("t.commodity.promotion-uppercase", Produced.with(stringSerde, jsonSerde));
		
		//useful for debugging, but 	don't do this on production
		sourceStream.print(Printed.<String, PromotionMessage>toSysOut().withLabel("Custom JSON Serde Original Stream"));	
		uppercaseStream.print(Printed.<String, PromotionMessage>toSysOut().withLabel("Custom JSON Serde Uppercase Stream"));
		
		return sourceStream;
	}
	
	private PromotionMessage uppercasePromotionCode(PromotionMessage message) {
		return new PromotionMessage(message.getPromotionCode().toUpperCase());
	}
}
