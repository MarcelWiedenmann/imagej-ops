
package experimental.tiling.mapreduce;

public interface TilableMapOverlap<I, O> extends TilableMap<I, O> {

	int[] getOverlap();
}
