package com.vic.kafka.app.broker.serde;

import com.vic.kafka.app.broker.message.PromotionMessage;

public class PromotionSerde extends CustomJsonSerde<PromotionMessage> {

	public PromotionSerde() {
		super(new CustomJsonSerializer<PromotionMessage>(), 
				new CustomJsonDeserializer<PromotionMessage>(PromotionMessage.class));
	}

}
