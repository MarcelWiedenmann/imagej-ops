
package experimental.compgraph;

public interface Fork<I> {

	// TODO: can be changed to class, copy I on access etc.

	I first();

	I second();
}
