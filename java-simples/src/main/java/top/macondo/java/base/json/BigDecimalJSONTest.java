package top.macondo.java.base.json;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiConsumer;

import static com.fasterxml.jackson.databind.DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS;

/**
 * @author: zhangchong
 * @Date: 2020/11/26 11:55
 **/

public class BigDecimalJSONTest {

	String requestStr = "{\n" +
			"\t\t\"amount\": 1.00,\n" +
			"\t\t\"amount1\": 1.000000000000,\n" +
			"\t\t\"amount2\": 1.000000000001,\n" +
			"\t\t\"amount3\": 22,\n" +
			"\t\t\"amount4\": 999999999999999,\n" +
			"\t\t\"amount5\": 33.0,\n" +
			"\t\t\"amount6\": 33.01,\n" +
			"\t\t\"amount7\": 0,\n" +
			"\t\t\"amount8\": \"111111\",\n" +
			"\t\t\"amount9\": \"1.00\"\n" +

			"}";
	@Data
	static class ObjectData{
		private String amount;
		private double asFloat;
		private Integer asInteger;
		private BigDecimal bigDecimal;
		private BigDecimal bigDecimal2;
	}
	interface Convert{
		Map<String, Object> convertStrToMap(String input);
		String convertObjectToStr(Object input);
	}

	static class JacksonConvert implements Convert {
		static final ObjectMapper objectMapper = new ObjectMapper();
		static {
			objectMapper.enable(USE_BIG_DECIMAL_FOR_FLOATS)
					.setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));
		}
		@Override
		public Map<String, Object> convertStrToMap(String input) {
			JsonNode jsonObject = null;
			Map<String, Object> result = new LinkedHashMap<>();
			try {
				jsonObject = objectMapper.readTree(input);

				BiConsumer<Iterator<Map.Entry<String, JsonNode>>, Map<String, Object>> consumer = (entryIterator, stringObjectMap) -> {
					while (entryIterator.hasNext()) {
						Map.Entry<String, JsonNode> nodeEntry = entryIterator.next();
						stringObjectMap.put(nodeEntry.getKey(), nodeEntry.getValue());
					}
				};
				consumer.accept(jsonObject.fields(),result);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return result;
		}

		@Override
		public String convertObjectToStr(Object input) {
			try {
				String result =  objectMapper.writeValueAsString(input);
				//ObjectData objectData = objectMapper.readValue(result, ObjectData.class);
				//JsonNode jsonObject = objectMapper.readTree(result);
				//System.out.println("convertObjectToStr -> Object: "+objectData);
				//System.out.println("convertObjectToStr -> JsonNode: "+jsonObject);
				return result;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	class HutoolJSONConvert implements Convert {

		@Override
		public Map<String, Object> convertStrToMap(String input) {
			JSONObject parse = JSONUtil.parseObj(input);
			return parse;
		}
		@Override
		public String convertObjectToStr(Object input) {
			return JSONUtil.toJsonStr(input);
		}
	}

	class FastJsonConvert implements Convert {

		@Override
		public Map<String, Object> convertStrToMap(String input) {
			com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(input, com.alibaba.fastjson.JSONObject.class);
			return jsonObject;
		}
		@Override
		public String convertObjectToStr(Object input) {
			return JSON.toJSONString(input);
		}
	}
	@Test
	public void testJSON(){
		ObjectData objectData = new ObjectData();
		objectData.asFloat = 1.00;
		objectData.amount = "1.00";
		objectData.asInteger = 1;
		objectData.bigDecimal = new BigDecimal(1.00);
		objectData.bigDecimal2 = new BigDecimal("1.00");

		List<Convert> list = new ArrayList<>();
		list.add(new JacksonConvert());
		list.add(new HutoolJSONConvert());
		list.add(new FastJsonConvert());
		list.forEach(convert -> {
			System.out.println("##### " + convert.getClass().getSimpleName());
			Map<String, Object> result = convert.convertStrToMap(requestStr);
			result.forEach((k,v)->{
				System.out.println(k + ": " + v + ":" + v.getClass().getSimpleName());
			});
			String resultStr = convert.convertObjectToStr(objectData);
			System.out.println("convertObjectToStr: "+resultStr);
		});
		System.out.println(objectData.toString());


	}
	@Test
	public void testStrToObject() throws JsonProcessingException {
		final ObjectMapper objectMapper = new ObjectMapper();
		String result = "{\"amount\":\"1.00\",\"asFloat\":1.0,\"asInteger\":1,\"bigDecimal\":1,\"bigDecimal2\":\"1.00\"}";
		ObjectData objectData = objectMapper.readValue(result, ObjectData.class);
		JsonNode jsonObject = objectMapper.readTree(result);
		System.out.println("convertObjectToStr -> Object: "+objectData);
		System.out.println("convertObjectToStr -> JsonNode: "+jsonObject);
		System.out.println(objectMapper.writeValueAsString(objectData));

	}
}
