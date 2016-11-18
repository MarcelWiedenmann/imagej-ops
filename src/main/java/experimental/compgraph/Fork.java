
package experimental.compgraph;

public class Fork<I> {

	// TODO: copy "I" on first (not "first") access etc.

	public Fork(final I in) {

	}

	I first() {
		throw new UnsupportedOperationException("not yet implemented");
	}

	I second() {
		throw new UnsupportedOperationException("not yet implemented");
	}
}
