
package experimental.compgraph.service.scheduler;

// TODO: we could try to do some benchmarking "our scheduler vs. Java 8 Streams" might be interesting
// (include memory usage statistics)

public class LocalCompgraphScheduler {

	// our scheduler needs to provide (at least) two - high-level, i.e. easily usable - mechanisms:
	// - compgraph-level: exploit the natural layout of DAGs and parallelize branch-wise, synchronize when branches join
	// - IO/cache-level: we need a smart mechanism to deal with long-running cache validation processes (tile-wise
	// blocking IO/cache accesses)
	// keep thread pool capacities and load balancing (both CPU and memory-wise) in mind

	// submit one entire branch of our DAG for execution, this can be used to parallelize execution at binary nodes (pull)
	// or forks (push)
	// ...

	// TODO

	public void submitRequest() {

	}

	public void submitCacheAccess() { // or: submitRequest(...)

	}

	// ...
}
