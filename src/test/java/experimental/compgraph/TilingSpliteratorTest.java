
package experimental.compgraph;

import net.imagej.ops.AbstractOpTest;
import net.imglib2.Interval;
import net.imglib2.img.array.ArrayImg;
import net.imglib2.img.basictypeaccess.array.ByteArray;
import net.imglib2.type.numeric.integer.ByteType;
import net.imglib2.view.experimental.TiledView;

import org.junit.Test;

import experimental.compgraph.execution.TilingSpliterator;

public class TilingSpliteratorTest extends AbstractOpTest {

	@Test
	public void test() {
		// Tiling:
		final ArrayImg<ByteType, ByteArray> img = generateByteArrayTestImg(false, 100, 100, 10);
		final TiledView<ByteType> tiling = new TiledView<>(img, 12, 12, 4);
		final TilingSpliterator<ByteType> spliterator1 = new TilingSpliterator<>(tiling);
		final TilingSpliterator<ByteType> spliterator2 = spliterator1.trySplit();
		printInterval("1", spliterator1.getSource());
		printInterval("2", spliterator2.getSource());
	}

	private void printInterval(final String tag, final Interval i) {
		final int n = i.numDimensions();
		final StringBuilder min = new StringBuilder("(");
		final StringBuilder max = new StringBuilder("(");
		for (int d = 0; d < n; d++) {
			min.append(i.min(d));
			max.append(i.max(d));
			if (d < n - 1) {
				min.append(" ");
				max.append(" ");
			}
		}
		min.append(")");
		max.append(")");
		System.out.println(tag + ": from " + min.toString() + " to " + max.toString());
	}
}
