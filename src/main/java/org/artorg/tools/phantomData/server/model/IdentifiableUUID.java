package org.artorg.tools.phantomData.server.model;

import java.math.BigInteger;
import java.util.UUID;

public interface IdentifiableUUID extends Identifiable<UUID> {
	
	static UUID getUuid(String s) {
		String s2 = s.replace("-", "");
		return new UUID(new BigInteger(s2.substring(0, 16), 16).longValue(),
				new BigInteger(s2.substring(16), 16).longValue());
	}

}
