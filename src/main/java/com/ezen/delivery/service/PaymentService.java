package com.ezen.delivery.service;

import java.io.IOException;

public interface PaymentService {

	String getToken() throws IOException;

	int paymentInfo(String imp_UID, String token) throws IOException;

	void payMentCancel(String token, String imp_UID, int amount, String string) throws IOException;

}
