package experimental.compgraph.channel.collection.rai;

import net.imglib2.IterableInterval;

import experimental.compgraph.channel.collection.OpsCollection;

public interface OpsIterableInterval<I> extends IterableInterval<I>, OpsCollection<I> {
	// NB: Marker Interface to be compatible to several Ops implementations
}
