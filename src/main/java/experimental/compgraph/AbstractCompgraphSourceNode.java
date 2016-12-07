
package experimental.compgraph;

public abstract class AbstractCompgraphSourceNode<O, OUT extends DataHandle<O, ?>> extends
	AbstractCompgraphOutputNode<O, OUT> implements CompgraphSourceNode<O, OUT>
{

	private final OUT outData;

	public AbstractCompgraphSourceNode(final OUT outData) {
		this.outData = outData;
	}

	// -- CompgraphNode --

	@Override
	public OUT apply() {
		return outData;
	}
}
