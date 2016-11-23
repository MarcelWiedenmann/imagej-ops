
package experimental.algebra.img;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;

import experimental.algebra.OpsCollection;
import experimental.algebra.OpsGrid;
import experimental.algebra.OpsGridNested;

// tile is the thing which provides information about location which can be reused for composition again.
public interface OpsTiling<T, E extends OpsTile<T>> extends OpsGridNested<T, E> {

	// @Override
	// <O, CI extends OpsCollection<T>, CC extends OpsCollection<O>>
	// OpsGridNested<O, CC> collMap(
	// final Function<CI, CC> func);

	// this function has to operate on the entire Tile (incl. overlap), not just
	// the valid area. However, the result has to be "wrapped back" accordingly,
	// if possible.
	// not sure about operations as resize etc.
	@Override
	<O> OpsGrid<O> map(Function<? super E, O> f);

	// we may want this function to allow the user to operate on tilings all
	// the time. I'm not really sure if we need this function as we could also
	// check or RAI output in the upper map and wrap it into a tile
	// accordingly...
	<O> OpsTiling<O, OpsTile<O>> mapTile(Function<? super E, RandomAccessibleInterval<O>> f);

	@Override
	<O, CC extends OpsCollection<O>> OpsGridNested<O, CC> collMap(Function<E, CC> func);

}
