
package experimental.compgraph.execution;

import java.util.Spliterator;
import java.util.function.Consumer;

import net.imglib2.RandomAccessibleInterval;

public class TilingSpliterator<T> implements Spliterator<RandomAccessibleInterval<T>> {

	private final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> tiling;

	public TilingSpliterator(final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> tiling) {
		this.tiling = tiling;
	}

	@Override
	public boolean tryAdvance(final Consumer<? super RandomAccessibleInterval<T>> action) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Spliterator<RandomAccessibleInterval<T>> trySplit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long estimateSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int characteristics() {
		// TODO Auto-generated method stub
		return 0;
	}

}
