
package experimental.compgraph;

import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imglib2.util.Pair;

public class BinaryAsUnaryFunctionOp<I1, I2, O> extends AbstractUnaryFunctionOp<Pair<I1, I2>, O> {

	private final BinaryFunctionOp<I1, I2, O> f;

	public BinaryAsUnaryFunctionOp(final BinaryFunctionOp<I1, I2, O> f) {
		this.f = f;
	}

	@Override
	public O compute1(final Pair<I1, I2> input) {
		return f.compute2(input.getA(), input.getB());
	}
}
