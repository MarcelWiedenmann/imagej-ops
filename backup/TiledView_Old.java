/*
 * #%L
 * ImgLib2: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2016 Tobias Pietzsch, Stephan Preibisch, Stephan Saalfeld,
 * John Bogovic, Albert Cardona, Barry DeZonia, Christian Dietz, Jan Funke,
 * Aivar Grislis, Jonathan Hale, Grant Harris, Stefan Helfrich, Mark Hiner,
 * Martin Horn, Steffen Jaensch, Lee Kamentsky, Larry Lindsey, Melissa Linkert,
 * Mark Longair, Brian Northan, Nick Perry, Curtis Rueden, Johannes Schindelin,
 * Jean-Yves Tinevez and Michael Zinsmaier.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package experimental.tiling.view;

import net.imglib2.AbstractInterval;
import net.imglib2.Interval;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.View;
import net.imglib2.view.Views;

/**
 * @param <T> the pixel type
 * @author Marcel Wiedenmann (University of Konstanz)
 * @author Christian Dietz (University of Konstanz)
 */
public class TiledView_Old<T> extends AbstractInterval implements RandomAccessibleInterval<RandomAccessibleInterval<T>>,
	View
{

	public static <T> TiledView_Old<T> createFromBlocksPerDim(final RandomAccessibleInterval<T> source,
		final long[] blocksPerDim)
	{
		final long[] blockSize = new long[blocksPerDim.length];
		for (int d = 0; d < blockSize.length; ++d) {
			blockSize[d] = (source.dimension(d) - 1) / blocksPerDim[d] + 1;
		}
		return new TiledView_Old<>(source, blockSize);
	}

	private final RandomAccessibleInterval<T> source;
	private final long[] blockSize;

	public TiledView_Old(final RandomAccessibleInterval<T> source, final long... blockSize) {
		super(source.numDimensions());
		this.source = source;
		this.blockSize = blockSize;
		for (int d = 0; d < n; ++d) {
			max[d] = (source.dimension(d) - 1) / blockSize[d];
		}
	}

	public RandomAccessibleInterval<T> getSource() {
		return source;
	}

	@Override
	public RandomAccess<RandomAccessibleInterval<T>> randomAccess() {
		return new TiledViewRandomAccessSafe<>(source, blockSize); // TODO: safe vs unsafe
	}

	@Override
	public RandomAccess<RandomAccessibleInterval<T>> randomAccess(final Interval interval) {
		return randomAccess();
	}

	public static class TiledViewRandomAccessSafe<T> extends Point implements RandomAccess<RandomAccessibleInterval<T>> {

		private final RandomAccessibleInterval<T> source;
		private final long[] blockSize;
		private final long[] tempMin;
		private final long[] tempMax;

		public TiledViewRandomAccessSafe(final RandomAccessibleInterval<T> source, final long[] blockSize) {
			super(source.numDimensions());
			this.source = source;
			this.blockSize = blockSize;
			tempMin = new long[n];
			tempMax = new long[n];
		}

		private TiledViewRandomAccessSafe(final TiledViewRandomAccessSafe<T> ra) {
			super(ra.position, true);
			source = ra.source;
			blockSize = ra.blockSize;
			tempMin = ra.tempMin.clone();
			tempMax = ra.tempMax.clone();
		}

		@Override
		public RandomAccessibleInterval<T> get() {
			for (int d = 0; d < n; ++d) {
				tempMin[d] = position[d] * blockSize[d];
				tempMax[d] = tempMin[d] + blockSize[d] - 1;
			}
			return Views.interval(source, tempMin, tempMax);
		}

		@Override
		public TiledViewRandomAccessSafe<T> copy() {
			return new TiledViewRandomAccessSafe<>(this);
		}

		@Override
		public TiledViewRandomAccessSafe<T> copyRandomAccess() {
			return copy();
		}
	}

	public static class TiledViewRandomAccessUnsafe<T> extends Point implements
		RandomAccess<RandomAccessibleInterval<T>>
	{

		private final RandomAccessibleInterval<T> source;
		private final long[] blockSize;
		private final MutableRandomAccessibleIntervalView<T> tempBlock;

		public TiledViewRandomAccessUnsafe(final RandomAccessibleInterval<T> source, final long[] blockSize) {
			super(source.numDimensions());
			this.source = source;
			this.blockSize = blockSize;
			this.tempBlock = new MutableRandomAccessibleIntervalView<>(source);
		}

		private TiledViewRandomAccessUnsafe(final TiledViewRandomAccessUnsafe<T> ra) {
			super(ra.position, true);
			source = ra.source;
			blockSize = ra.blockSize;
			tempBlock = ra.tempBlock.copy();
		}

		@Override
		public RandomAccessibleInterval<T> get() {
			for (int d = 0; d < n; ++d) {
				final long min = position[d] * blockSize[d];
				tempBlock.setMin(min, d);
				tempBlock.setMax(min + blockSize[d] - 1, d);
			}
			// TODO: Return as zeroMin (same for ArrangedView)?
			return tempBlock;
		}

		@Override
		public TiledViewRandomAccessUnsafe<T> copy() {
			return new TiledViewRandomAccessUnsafe<>(this);
		}

		@Override
		public TiledViewRandomAccessUnsafe<T> copyRandomAccess() {
			return copy();
		}

		protected static final class MutableRandomAccessibleIntervalView<T> extends AbstractInterval implements
			RandomAccessibleInterval<T>, View

		{

			private final RandomAccessibleInterval<T> source;

			public MutableRandomAccessibleIntervalView(final RandomAccessibleInterval<T> source) {
				super(source);
				this.source = source;
			}

			public MutableRandomAccessibleIntervalView(final RandomAccessibleInterval<T> source, final long[] min,
				final long[] max)
			{
				super(min, max);
				this.source = source;
			}

			private MutableRandomAccessibleIntervalView(final MutableRandomAccessibleIntervalView<T> view) {
				super(view.min, view.max);
				source = view.source;
			}

			public void setMin(final long min, final int d) {
				this.min[d] = min;
			}

			public void setMax(final long max, final int d) {
				this.max[d] = max;
			}

			public MutableRandomAccessibleIntervalView<T> copy() {
				return new MutableRandomAccessibleIntervalView<T>(this);
			}

			@Override
			public RandomAccess<T> randomAccess() {
				return source.randomAccess(this);
			}

			@Override
			public RandomAccess<T> randomAccess(final Interval interval) {
				return source.randomAccess(interval);
			}
		}
	}
}
