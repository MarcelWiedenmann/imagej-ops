
package experimental.compgraph.node;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.AbstractCompgraphSourceNode;
import experimental.compgraph.LocalDataHandle;
import experimental.compgraph.execution.TilingSpliterator;

public class LocalTilingSource<T> extends
	AbstractCompgraphSourceNode<RandomAccessibleInterval<T>, LocalDataHandle<RandomAccessibleInterval<T>>>
{

	public LocalTilingSource(final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> inData) {
		super(new LocalDataHandle<>(toStream(inData)));
	}

	private static <T> Stream<RandomAccessibleInterval<T>> toStream(
		final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> data)
	{
		final TilingSpliterator<T> spliterator = new TilingSpliterator<>(data);
		return StreamSupport.stream(spliterator, true);
	}

	public static void main(final String[] args) {
		LocalContextMap
	}
}
