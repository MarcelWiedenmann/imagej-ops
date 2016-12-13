package experimental.compgraph.request;

import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessible;

interface LazyTileAccess<T> extends RandomAccess<RandomAccessible<T>> {
	//
}
