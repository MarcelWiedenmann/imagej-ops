package experimental.compgraph.channel.collection;

import net.imglib2.IterableInterval;

public interface OpsIterableInterval<I> extends IterableInterval<I>, OpsCollection<I> {
	// NB: Marker Interface to be compatible to several Ops implementations
}
