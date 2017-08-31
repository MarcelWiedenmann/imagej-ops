
package experimental.compgraph;

import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imglib2.util.Pair;

// FIXME: instanceof-checks for "Distributable"-blah does not work here
// FIXME: What is the purpose of this?

public class BinaryAsUnaryFunctionOp<I1, I2, O> extends AbstractUnaryFunctionOp<Pair<I1, I2>, O> {

	private final BinaryFunctionOp<I1, I2, O> f;

	public BinaryAsUnaryFunctionOp(final BinaryFunctionOp<I1, I2, O> f) {
		this.f = f;
	}

	public BinaryFunctionOp<I1, I2, O> wrapped() {
		return f;
	}

	@Override
	public O calculate(final Pair<I1, I2> input) {
		return f.calculate(input.getA(), input.getB());
	}
}
