package top.macondo.java.protobuf;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * protoc[.exe] Person.proto --java_out=./
 */

/**
 * @author: zhangchong
 * @Date: 2020/8/5 15:33
 **/
public class ProtoBufTest {
	public static void main(String[] args) throws IOException {
		PersonEntity.Person.Builder builder = PersonEntity.Person.newBuilder();
		builder.setId(1);
		builder.setName("zc");
		builder.setEmail("zc@macondo.top");
		PersonEntity.Person person = builder.build();
		System.out.println(person.toString());
		for (byte b : person.toByteArray()){
			System.out.print(b);
		}
		System.out.println();
		System.out.println(person.toByteString());

		byte[] bytes = person.toByteArray();
		PersonEntity.Person received = PersonEntity.Person.parseFrom(bytes);
		System.out.println(received.toString());
	}

}
