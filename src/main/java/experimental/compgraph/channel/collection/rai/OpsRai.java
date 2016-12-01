
package experimental.compgraph.channel.collection.rai;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.converter.Converter;

import experimental.compgraph.channel.collection.OpsGrid;
import experimental.compgraph.channel.collection.tiling.OpsTiling;

public interface OpsRai<I> extends OpsGrid<I>, RandomAccessibleInterval<I> {

	<O> OpsRai<O> toRai(final Function<? super OpsRai<I>, RandomAccessibleInterval<O>> f);

	// is transform using map
	<O> OpsRai<O> toRai(Converter<I, O> c);

	// is special type of transform
	OpsTiling<I> toTiling(long[] tilesPerDim, long[] overlap);
}
