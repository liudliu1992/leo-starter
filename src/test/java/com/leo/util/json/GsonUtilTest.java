package com.leo.util.json;

import org.junit.Test;

import java.util.List;
import java.util.Map;

public class GsonUtilTest {

	@Test
	public void gsonToBean() {
		String json = "{\n" +
				"   \"user_info_list\": [\n" +
				"       {\n" +
				"           \"subscribe\": 1, \n" +
				"           \"openid\": \"otvxTs4dckWG7imySrJd6jSi0CWE\", \n" +
				"           \"nickname\": \"iWithery\", \n" +
				"           \"sex\": 1, \n" +
				"           \"language\": \"zh_CN\", \n" +
				"           \"city\": \"揭阳\", \n" +
				"           \"province\": \"广东\", \n" +
				"           \"country\": \"中国\", \n" +
				"\n" +
				"           \"headimgurl\": \"http://thirdwx.qlogo.cn/mmopen/xbIQx1GRqdvyqkMMhEaGOX802l1CyqMJNgUzKP8MeAeHFicRDSnZH7FY4XB7p8XHXIf6uJA2SCunTPicGKezDC4saKISzRj3nz/0\",\n" +
				"\n" +
				"          \"subscribe_time\": 1434093047, \n" +
				"           \"unionid\": \"oR5GjjgEhCMJFyzaVZdrxZ2zRRF4\", \n" +
				"           \"remark\": \"\", \n" +
				"\n" +
				"           \"groupid\": 0,\n" +
				"           \"tagid_list\":[128,2],\n" +
				"           \"subscribe_scene\": \"ADD_SCENE_QR_CODE\",\n" +
				"           \"qr_scene\": 98765,\n" +
				"           \"qr_scene_str\": \"\"\n" +
				"\n" +
				"      }, \n" +
				"       {\n" +
				"           \"subscribe\": 0, \n" +
				"           \"openid\": \"otvxTs_JZ6SEiP0imdhpi50fuSZg\"\n" +
				"       }\n" +
				"   ]\n" +
				"}";
		Map weChatResutl = GsonUtil.gsonToMaps(json);
		List<Map<String,Object>> wechatInfoList = (List<Map<String, Object>>) weChatResutl.get("user_info_list");
		wechatInfoList.forEach(info -> {
			if(info.get("sex")!=null){
				SexEnum sex = SexEnum.getSexEnum("");
				System.out.println(sex.getCnName());
			}


		});

	}

	@Test
	public void jsonToList() {

		String art = "{\"button\":[{\"name\":\"航班服务\",\"sub_button\":[{\"name\":\"机票预订\",\"type\":\"view\",\"url\":\"https://www.chinaexpressair.com/?site=airline&clear=true\"}]},{\"name\":\"自助服务\",\"sub_button\":[{\"name\":\"在线选座\",\"type\":\"view\",\"url\":\"https://www.chinaexpressair.com/wechatpay/official/account/redirect\"},{\"name\":\"自助改期\",\"type\":\"view\",\"url\":\"https://flight.chinaexpressair.com/selfHelp/formPage\"},{\"name\":\"自助退票\",\"type\":\"view\",\"url\":\"https://www.chinaexpressair.com/abnormal-return/info\"},{\"name\":\"延误证明\",\"type\":\"view\",\"url\":\"https://flight.chinaexpressair.com/delayflight/flightdelaycertificate\"},{\"name\":\"问卷调查\",\"type\":\"view\",\"url\":\"https://www.wjx.cn/jq/28002947.aspx\"}]},{\"name\":\"智能客服\",\"type\":\"view\",\"url\":\"https://www.chinaexpressair.com/wechatpay/official/account/getuserinfo\"}]}";
		System.out.println(art);

	}
}