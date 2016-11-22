
package experimental.algebra;

public interface OpsGrid<I> extends OpsList<I> {

	// <O> DistributedGrid<O> elementwise(UnaryFunctionOp<E, O> f);

	// <O> DistributedList<O> pairs(BinaryFunctionOp<E, E, O> f); // pairs(f): G<I> -> List<Pair<I,I>>

	// DistributedGrid<DistributedGrid<E>> group(Function<long[], Long> f);

	// DistributedGrid<DistributedGrid<E>> blockify(long[] size, long[] overlap);

	// -- OpsList --

	// ...
}
