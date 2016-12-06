//
//package experimental.compgraph.channel.stream;
//
//import java.util.function.BiConsumer;
//import java.util.function.BiFunction;
//import java.util.function.BiPredicate;
//import java.util.function.Consumer;
//import java.util.function.Function;
//import java.util.function.Predicate;
//
//import net.imglib2.util.Pair;
//
//import experimental.compgraph.CompgraphInnerNode;
//import experimental.compgraph.CompgraphSingleEdge;
//import experimental.compgraph.DataHandle;
//import experimental.compgraph.channel.OpsBoundedChannel;
//import experimental.compgraph.channel.OpsChannel;
//import experimental.compgraph.channel.collection.OpsCollection;
//import experimental.compgraph.channel.collection.OpsElement;
//import experimental.compgraph.channel.collection.OpsOrderedCollection;
//
//public class DefaultOpsBoundedStream<I> implements OpsBoundedStream<I> {
//
//	private final CompgraphInnerNode<?, ?, DefaultOpsBoundedStream<I>> source;
//
//	public DefaultOpsBoundedStream(final CompgraphInnerNode<?, ?, DefaultOpsBoundedStream<I>> source) {
//		this.source = source;
//	}
//
//	@Override
//	public <O> OpsChannel<O> transform(final Function<? super OpsChannel<I>, OpsChannel<O>> f) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public OpsOrderedCollection<I> collect() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <O> OpsElement<O> reduce(final O memo, final BiFunction<O, ? super I, O> f, final BiFunction<O, O, O> merge,
//		final int window)
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <O> OpsBoundedStream<O> map(final Function<? super I, O> f) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <O> OpsBoundedStream<O> map(final BiConsumer<? super I, ? extends Consumer<O>> f) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public OpsBoundedStream<I> filter(final Predicate<? super I> f) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <O> OpsBoundedStream<? extends OpsBoundedChannel<O>> partition(
//		final BiConsumer<? super I, ? extends Consumer<O>> f)
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <O> OpsCollection<? extends OpsBoundedChannel<O>> group(final Function<? super I, Integer> f) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <I2> OpsBoundedStream<Pair<I, I2>> join(final OpsBoundedChannel<I2> c,
//		final BiPredicate<? super I, ? super I2> f)
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <I2> OpsBoundedStream<Pair<I, I2>> cartesian(final OpsBoundedChannel<I2> c) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public CompgraphInnerNode<?, ?, ? extends CompgraphSingleEdge<I>> source() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public DataHandle<I, ?> dataflow() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
