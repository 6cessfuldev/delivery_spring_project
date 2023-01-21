package com.ezen.delivery.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.delivery.domain.BasketDTO;
import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.OrderDetailDTO;
import com.ezen.delivery.domain.OrderFoodDTO;
import com.ezen.delivery.domain.OrderHistoryDTO;
import com.ezen.delivery.domain.OrderInfoDTO;
import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.security.oauth2.PrincipalDetails;
import com.ezen.delivery.service.BasketService;
import com.ezen.delivery.service.OrderService;
import com.ezen.delivery.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/order/*")
@Controller
public class OrderController {

	@Inject
	private OrderService osv;

	@Inject
	private BasketService bsv;

	@Inject
	private UserService usv;

	@GetMapping("/page")
	public String orderPageGet(Authentication authentication, Model model) {

		if (authentication.getPrincipal() == null) {
			return "/member/login";
		}

		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

		String user_id = principalDetails.getUsername();

		List<BasketDTO> bList = bsv.getList(user_id);
		System.out.println("BasketDTO : " + bList);
		
		UserVO uvo = usv.getUserByID(user_id);

		int orderTotalPrice = 0;
		String orderName = "(먹어요)";
		for (BasketDTO bdto : bList) {
			orderName += bdto.getFood_name();
			orderName += "/";
			orderTotalPrice += bdto.getTotal_price() * bdto.getBasket_order_count();
		}

		log.info("order name : "+orderName);

		//int diner_code = bsv.getDinerCode(user_id);
		DinerVO dvo = bsv.getDiner(user_id);
		model.addAttribute("user", uvo);
		model.addAttribute("basketList", bList);
		model.addAttribute("order_name", orderName);
		model.addAttribute("orderTotalPrice", orderTotalPrice);
		model.addAttribute("diner", dvo);

		return "/member/order";
	}

	@PostMapping("/")
	public String orderPagePOST(@ModelAttribute(value = "OrderFoodDTO") OrderFoodDTO ofdto, String user_id,
			Model model) {

		log.info(">>> orderFoodDTO : " + ofdto.toString());
		log.info("user_id : " + user_id);
		return "/member/order";
	}

//	@PostMapping("/")
//	public String orderPagePOST(OrderFoodDTO ofdto, Model model) {
//		
//		log.info(">>> orders : " + ofdto.toString());
//		
//		return "/member/order";
//	}

	@GetMapping("/myOrderList")
	public String orderListPageGET(Authentication authentication, Model model) {

		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
			
		String user_email = principalDetails.getUser().getUser_email();
		
		log.info("user_email : "+user_email);

		List<List<OrderHistoryDTO>> userOrderHistoryList = new ArrayList<List<OrderHistoryDTO>>();
		model.addAttribute("userOrderHistoryList", userOrderHistoryList);
		
		List<OrderInfoDTO> oidtoList = osv.orderInfoDTOList(user_email);

		for (int i = 0; i < oidtoList.size(); i++) {

			long order_code = oidtoList.get(i).getOrder_code();

			String diner_name = osv.getDinerName(order_code);

			List<OrderDetailDTO> oddtoList = osv.orderDetailDTOList(order_code);
			log.info(oddtoList.toString());

			List<OrderHistoryDTO> orderHistoryList = new ArrayList<OrderHistoryDTO>();

			for (int j = 0; j < oddtoList.size(); j++) {

				String food_info_JSON = oddtoList.get(j).getFood_info_JSON();

				OrderHistoryDTO ohdto = new OrderHistoryDTO();
				ohdto.setOrder_code(order_code);
				ohdto.setDiner_name(diner_name);

				try {

					JSONParser parser = new JSONParser();
					Object obj = parser.parse(food_info_JSON);

					JSONObject jsonObj = (JSONObject) obj;
					JSONArray jsonArr = (JSONArray) jsonObj.get("choiceList");

					String choice_contents = "";
					for (int k = 0; k < jsonArr.size(); k++) {

						JSONObject jsonObj2 = (JSONObject) jsonArr.get(k);

						choice_contents += (String) jsonObj2.get("choice_content") + " ";
					}
					ohdto.setChoice_contents(choice_contents);
					log.info(choice_contents);

					String food_name = (String) jsonObj.get("food_name");
					ohdto.setFood_name(food_name);

					int total_price = (int) jsonObj.get("total_price");
					ohdto.setTotal_price(total_price);

					int order_count = (int) jsonObj.get("basket_order_count");
					ohdto.setOrder_count(order_count);

				} catch (Exception e) {
					e.printStackTrace();
				}

				orderHistoryList.add(ohdto);
			}

			userOrderHistoryList.add(orderHistoryList);
		}

		return "/member/myOrderList";
	}

}
