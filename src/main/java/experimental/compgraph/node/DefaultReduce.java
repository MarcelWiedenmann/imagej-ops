//
//package experimental.compgraph.node;
//
//import java.util.function.BiFunction;
//
//import experimental.compgraph.CompgraphEdge;
//import experimental.compgraph.CompgraphSingleEdge;
//
//public class DefaultReduce<IN extends CompgraphEdge<I>, I, O, OUT extends CompgraphSingleEdge<O>> implements
//	Reduce<IN, I, O, OUT>
//{
//
//	private final O memo;
//	private final BiFunction<O, ? super I, O> f;
//	private final BiFunction<O, O, O> merge;
//
//	public DefaultReduce(final O memo, final BiFunction<O, ? super I, O> f, final BiFunction<O, O, O> merge) {
//		this.memo = memo;
//		this.f = f;
//		this.merge = merge;
//	}
//
//	@Override
//	public O memo() {
//		return memo;
//	}
//
//	@Override
//	public BiFunction<O, ? super I, O> func() {
//		return f;
//	}
//
//	@Override
//	public BiFunction<O, O, O> merge() {
//		return merge;
//	}
//}
