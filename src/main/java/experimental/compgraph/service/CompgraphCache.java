
package experimental.compgraph.service;

import org.scijava.Context;
import org.scijava.cache.CacheService;

// TODO this is a hack
public class CompgraphCache {
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
