
package experimental.tiling.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.Sampler;

public class CombinedRandomAccess<T> extends Point implements RandomAccess<T> {

	protected final CombinedView<T> source;
	protected final List<RandomAccessibleInterval<T>> tiles;
	protected final TileIndexMapper mapper;
	protected final HashMap<Integer, RandomAccess<T>> cache;

	public CombinedRandomAccess(final CombinedView<T> source, final List<RandomAccessibleInterval<T>> tiles,
		final TileIndexMapper mapper)
	{
		super(mapper.getInterval().numDimensions());
		this.source = source;
		this.tiles = tiles;
		this.mapper = mapper;
		this.cache = new HashMap<Integer, RandomAccess<T>>(tiles.size());
	}

	protected CombinedRandomAccess(final CombinedRandomAccess<T> randomAccess) {
		super(randomAccess.position, true);
		source = randomAccess.source;
		tiles = randomAccess.tiles;
		mapper = randomAccess.mapper;
		cache = new HashMap<Integer, RandomAccess<T>>(randomAccess.cache.size());
		for (final Map.Entry<Integer, RandomAccess<T>> entry : randomAccess.cache.entrySet()) {
			cache.put(entry.getKey(), entry.getValue().copyRandomAccess());
		}
	}

	public RandomAccessibleInterval<T> getTileAtIndex(final long[] index) {
		return tiles.get((int) mapper.getFlatTileIndex(index));
	}

	public RandomAccessibleInterval<T> getTileAtPosition(final long[] position) {
		final long[] localPosition = new long[n];
		return tiles.get((int) mapper.getFlatTileIndexAndLocalPosition(position, localPosition));
	}

	// -- RandomAccess --

	@Override
	public T get() {
		final long[] localPosition = new long[n];
		final int flatTileIndex = (int) mapper.getFlatTileIndexAndLocalPosition(position, localPosition);
		final RandomAccess<T> tileRA;
		if (cache.containsKey(flatTileIndex)) {
			tileRA = cache.get(flatTileIndex);
		}
		else {
			tileRA = tiles.get(flatTileIndex).randomAccess();
			cache.put(flatTileIndex, tileRA);
		}
		tileRA.setPosition(localPosition);
		return tileRA.get();
	}

	@Override
	public Sampler<T> copy() {
		return copyRandomAccess();
	}

	@Override
	public RandomAccess<T> copyRandomAccess() {
		return new CombinedRandomAccess<T>(this);
	}
}
