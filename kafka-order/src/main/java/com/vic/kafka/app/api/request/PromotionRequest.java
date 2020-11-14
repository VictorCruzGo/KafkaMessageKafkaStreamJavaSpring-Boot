package com.vic.kafka.app.api.request;

public class PromotionRequest {
	private String promotionCode;

	public PromotionRequest() {

	}	
	public PromotionRequest(String promotionCode) {
		super();
		this.promotionCode = promotionCode;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	@Override
	public String toString() {
		return "PromotionRequest [promotionCode=" + promotionCode + "]";
	}		
}
