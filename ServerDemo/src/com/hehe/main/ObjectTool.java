package com.hehe.main;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ObjectTool {

	public static Message getMessageFormBytes(byte[] bs) {
		Message ms = new Message();
		User u = new User();
		ms.user = u; 
		
		byte[] temp = new byte[512];
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.order(ByteOrder.LITTLE_ENDIAN);
		
		buf.put(bs, 0, 556);
		buf.rewind();
		
		ms.type = buf.getInt();
		ms.user.id = buf.getInt();
		ms.user.gender = buf.getInt();
		buf.get(temp, 0, 32);
		ms.user.name = new String(temp, 0, 32);
		
		buf.get(temp, 0, 512);
		ms.data = new String(temp, 0, 512);
		return ms;
	}
}
