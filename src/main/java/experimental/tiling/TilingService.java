
package experimental.tiling;

import org.scijava.service.SciJavaService;

public interface TilingService extends SciJavaService {

	TilingOpEnvironment ops();

	// public <I> TilingSchema<I> create(RandomAccessibleInterval<I> in, TilingConfiguration config);
}
