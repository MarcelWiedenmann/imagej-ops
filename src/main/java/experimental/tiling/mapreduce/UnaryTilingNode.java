
package experimental.tiling.mapreduce;

import java.util.Iterator;

import net.imagej.ops.special.function.UnaryFunctionOp;

public interface UnaryTilingNode<O> {

	// NB: See http://spark.apache.org/docs/latest/api/java/org/apache/spark/api/java/JavaRDD.html for corresponding Spark
	// functionality & naming.

	// TODO: Accept Java 8 Functional Interfaces as soon as they're implemented in ops.
	// TODO: Restrict aggregate methods to "AggregateOp" input?

	// TODO: Where to put those? (which we'll probably need)
	// public TilingSchema<O> getTilingSchema();
	// public Pair<Interval, O> getSingleTileSchema();

	public <OO> UnaryTilingNode<OO> map(final UnaryFunctionOp<O, OO> mapper);

	public <OO> UnaryTilingNode<OO> forwardAggregate(final UnaryFunctionOp<Iterator<O>, OO> aggregator);

	public <OO> UnaryTilingNode<OO> treeAggregate(final UnaryFunctionOp<Iterator<O>, OO> aggregator);

}
