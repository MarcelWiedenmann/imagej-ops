
package experimental.compgraph.tiling;

import experimental.compgraph.AbstractCompgraphSinkNode;
import experimental.compgraph.CompgraphEdge;
import experimental.compgraph.request.TilingRequestable;

public class LocalTilingSink<IO> extends
	AbstractCompgraphSinkNode<LazyTile<IO>, TilingDataHandle<IO>, TilingRequestable<IO>>
{

	public LocalTilingSink(final CompgraphEdge<LazyTile<IO>> in) {
		super(in);
	}

	@Override
	protected TilingRequestable<IO> getInternal(final TilingDataHandle<IO> inData) {
		return inData.inner();
	}
<<<<<<< HEAD
=======

	public static class LazyTiling<IO> extends AbstractInterval
			implements RandomAccessibleInterval<RandomAccessibleInterval<IO>> {

		private LocalTilingSink<IO> sink;

		// TODO: this may extend TiledView
		public LazyTiling(LocalTilingSink<IO> sink, long[] tileSize, long[] gridDims) {
			super(gridDims);
			this.sink = sink;
		}

		@Override
		public RandomAccess<RandomAccessibleInterval<IO>> randomAccess() {

			return new LazyTilingAccess<>(sink);
		}

		public static class LazyTilingAccess<IO> extends Point implements RandomAccess<RandomAccessibleInterval<IO>> {

			private LocalTilingSink<IO> sink;

			public LazyTilingAccess(LocalTilingSink<IO> sink) {
				this.sink = sink;
			}

			@Override
			public RandomAccessibleInterval<IO> get() {

				Tile i = getTile(position);

				TileRequest r = new TileRequest() {

					@Override
					public Tile key() {
						return i;
					}
				};

				LazyTile<IO> result = sink.get().request(r);
				return result;
			}

			public Tile getTile(long[] pos) {
				return null;
			}

			@Override
			public Sampler<RandomAccessibleInterval<IO>> copy() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public RandomAccess<RandomAccessibleInterval<IO>> copyRandomAccess() {
				// TODO Auto-generated method stub
				return null;
			}
		}

		@Override
		public RandomAccess<RandomAccessibleInterval<IO>> randomAccess(Interval interval) {
			// TODO Auto-generated method stub
			return null;
		}
	}
>>>>>>> 6d6da8a26c1040af8548049fcac6b8fdc54ef87d
}
