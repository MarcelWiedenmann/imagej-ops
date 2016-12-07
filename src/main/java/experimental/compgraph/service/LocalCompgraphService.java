
package experimental.compgraph.service;

import org.scijava.Priority;
import org.scijava.plugin.Plugin;

@Plugin(type = CompgraphService.class, priority = Priority.NORMAL_PRIORITY)
public class LocalCompgraphService extends AbstractCompgraphService {

	public LocalCompgraphService() {
		super(new LocalCompgraphNodeFactory());
	}

//	TODO: test-driven
//	public static void main(final String[] args) {
//		final Context defContext = new Context();
//
//		// user selects individual
//		final Context individual = new Context(Arrays.asList(LocalCompgraphService.class), defContext.getPluginIndex());
//
//		// user passes this graph
//		final CompgraphNode<O, DataHandle<O, ?>> sink;
//
//		// graph is optimized according to some rules which are generally applicable
//		opti = optimize(graph);
//
//		// propagates context to all contextual nodes
//		sink.setContext(individual);
//
//		// now all nodes (e.g. Mapper have right context) and can be replaced by the actual implementation
//		executableGraph = buildGraph(opti);
//
//		// runtime specific optimizations can be performed
//		superOptimized = defContext.service(DefaultCompgraphService.class).optimize(graph);
//
//		// how to access? allow to iterate over sinks/sources and get names/ids.
//		// allow also convenience access for 1to1 where you provide a function<I,O>
//		// with first sink and first source. Same for binary... etc.
//		return superOptimized;
//	}
}
