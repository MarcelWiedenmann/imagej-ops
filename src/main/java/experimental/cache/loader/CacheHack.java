package experimental.cache.loader;

import org.scijava.Context;
import org.scijava.cache.CacheService;

// will be replaced with proper cacheservice
public class CacheHack {

	private static CacheService cs;

	public static CacheService getCacheService() {
		if (cs == null) {
			cs = new Context().getService(CacheService.class);
		}

		return cs;
	}

	public static CacheService getCs() {
		return cs;
	}

}
