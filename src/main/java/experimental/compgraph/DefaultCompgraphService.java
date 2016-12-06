
package experimental.compgraph;

import org.scijava.Priority;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;

@Plugin(type = CompgraphService.class, priority = Priority.NORMAL_PRIORITY)
public class DefaultCompgraphService extends AbstractService implements CompgraphService {

	@Override
	public CompgraphNodeFactory factory() {
		// TODO Auto-generated method stub
		return null;
	}

//	TODO: test-driven
//	public static void main(final String[] args) {
//		final Context defContext = new Context();
//
//		// user selects invidiual
//		final Context individual = new Context(Arrays.asList(DefaultCompgraphService.class), defContext.getPluginIndex());
//
//		// user passes this graph
//		final CompgraphNode<O, DataHandle<O, ?>> sink;
//
//		// graph is optimized according to some rules which are generally applicable
//		opti = optimize(graph);
//
//		// propgates context to all contextual nodes
//		sink.setContext(indvidual);
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
