
package experimental.tiling.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.View;
import net.imglib2.view.Views;

public class CombinedView<T> extends AbstractInterval implements RandomAccessibleInterval<T>, View {

	protected final List<RandomAccessibleInterval<T>> tiles;
	protected final TileIndexMapper mapper;

	public CombinedView(final List<RandomAccessibleInterval<T>> tiles, final TileIndexMapper mapper) {
		super(mapper.getInterval());
		final ArrayList<RandomAccessibleInterval<T>> normalizedTiles = new ArrayList<>(tiles.size());
		for (final RandomAccessibleInterval<T> tile : tiles) {
			// TODO: Check offset?
			normalizedTiles.add(Views.zeroMin(tile));
		}
		this.tiles = Collections.unmodifiableList(normalizedTiles);
		this.mapper = mapper;
	}

	public List<RandomAccessibleInterval<T>> getTiles() {
		return tiles;
	}

	// -- RandomAccessibleInterval --

	@Override
	public RandomAccess<T> randomAccess() {
		return new CombinedRandomAccess<T>(this, tiles, mapper);
	}

	@Override
	public RandomAccess<T> randomAccess(final Interval interval) {
		return randomAccess();
	}
}
