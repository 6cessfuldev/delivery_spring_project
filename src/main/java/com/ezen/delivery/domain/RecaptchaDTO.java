package com.ezen.delivery.domain;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "success", "challenge_ts", "hostname", "error-codes" })
@ToString
@Setter
@Getter
public class RecaptchaDTO {
	@JsonProperty("success")
	private String success;
	@JsonProperty("challenge_ts")
	private String challenge_ts;
	@JsonProperty("hostname")
	private String hostname;
	@JsonProperty("score")
	private String score;
	@JsonProperty("action")
	private String action;
	@JsonProperty("error-codes")
	private ErrorCode[] errorCodes;

	@JsonIgnore
	public boolean hasClientError() {
		ErrorCode[] errors = getErrorCodes();
		if (errors == null) {
			return false;
		}
		for (ErrorCode error : errors) {
			switch (error) {
			case InvalidResponse:
			case MissingResponse:
				return true;
			}
		}
		return false;
	}

	static enum ErrorCode {
		MissingSecret, InvalidSecret, MissingResponse, InvalidResponse;

		private static Map<String, ErrorCode> errorsMap = new HashMap<>(4);
		static {
			errorsMap.put("missing-input-secret", MissingSecret);
			errorsMap.put("invalid-input-secret", InvalidSecret);
			errorsMap.put("missing-input-response", MissingResponse);
			errorsMap.put("invalid-input-response", InvalidResponse);
		}

		@JsonCreator
		public static ErrorCode forValue(String value) {
			return errorsMap.get(value.toLowerCase());
		}
	}

	
}