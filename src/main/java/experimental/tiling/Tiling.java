
package experimental.tiling;

import java.util.Iterator;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface Tiling<I, O> {

	// NB: See http://spark.apache.org/docs/latest/api/java/org/apache/spark/api/java/JavaRDD.html for corresponding Spark
	// functionality & naming.

	// TODO: Accept Java 8 Functional Interfaces as soon as they're implemented in ops.
	// TODO: Restrict aggregate methods to "AggregateOp" input?

	// TODO:
	// public TilingSchema<O> getTilingSchema();
	// public Pair<Interval, O> getSingleTileSchema();

	public <OO> Tiling<I, OO> map(final UnaryFunctionOp<O, OO> map);

	public <OO> Tiling<I, OO> forwardAggregate(final UnaryFunctionOp<Iterator<O>, OO> aggregate);

	public <OO> Tiling<I, OO> treeAggregate(final UnaryFunctionOp<Iterator<O>, OO> aggregate);
}
