
package experimental.compgraph.node;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import experimental.compgraph.AbstractCompgraphUnaryNode;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.LocalDataHandle;

public class LocalFlatReduce<I, O> extends AbstractCompgraphUnaryNode<I, LocalDataHandle<I>, O, LocalDataHandle<O>>
	implements Reduce<I, LocalDataHandle<I>, O, LocalDataHandle<O>>
{

	private final O memo;
	private final BiFunction<O, ? super I, O> f;
	private final BinaryOperator<O> merge;

	public LocalFlatReduce(final CompgraphSingleEdge<I> in, final O memo, final BiFunction<O, ? super I, O> f,
		final BinaryOperator<O> merge)
	{
		super(in);
		this.memo = memo;
		this.f = f;
		this.merge = merge;
	}

	@Override
	protected LocalDataHandle<O> applyInternal(final LocalDataHandle<I> inData) {
		// FIXME / TODO: Is this a valid way to ensure lazy reduction or is it too hacky? (+ are we leaking any reference?)
		final LocalDataHandle<O> handle = new LocalDataHandle<O>(null) {

			@Override
			public Stream<O> inner() {
				return Stream.of(inData.inner().reduce(memo, f, merge));
			}
		};
		return handle;
	}

	// -- Reduce --

	@Override
	public O memo() {
		return memo;
	}

	@Override
	public BiFunction<O, ? super I, O> func() {
		return f;
	}

	@Override
	public BiFunction<O, O, O> merge() {
		return merge;
	}
}
