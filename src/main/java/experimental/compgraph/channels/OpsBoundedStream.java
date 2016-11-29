
package experimental.compgraph.channels;

public interface OpsBoundedStream<I> extends OpsUnboundedStream<I> {

	OpsList<I> collect();

	// TODO: add explicit this.collect().reduce(f) = this.reduce(f)?
	// If not we need to do some optimizing later: reduce while collecting.

	// -- OpsUnboundedStream --

	// ...
}
