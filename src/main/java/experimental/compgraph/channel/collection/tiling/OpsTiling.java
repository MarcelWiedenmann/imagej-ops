
package experimental.compgraph.channel.collection.tiling;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.converter.Converter;

import experimental.compgraph.channel.collection.OpsGrid;
import experimental.compgraph.channel.collection.rai.OpsRai;

public interface OpsTiling<T> extends OpsGrid<OpsTile<T>> {

	// for pixel access
	OpsRai<T> toRai();

	<O> OpsRai<O> toRai(final Function<? super OpsTiling<T>, RandomAccessibleInterval<O>> f);

	// is transform using map
	<O> OpsTiling<O> toTiling(Converter<T, O> c);

	<O> OpsTiling<O> mapTile(Function<? super OpsTile<T>, RandomAccessibleInterval<O>> f);

	@Override
	<O> OpsGrid<O> map(final Function<? super OpsTile<T>, O> func);

}
