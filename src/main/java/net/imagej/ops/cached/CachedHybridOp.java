
package net.imagej.ops.cached;

import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imagej.ops.special.hybrid.UnaryHybridCF;

/**
 * Wraps a {@link UnaryHybridCF} and caches the results. New inputs will result in re-computation if
 * {@link UnaryHybridCF} is used as {@link UnaryFunctionOp}.
 *
 * @author Christian Dietz (University of Konstanz)
 * @param <I>
 * @param <O>
 */
public class CachedHybridOp<I, O, OP extends UnaryHybridCF<I, O>> extends CachedFunctionOp<I, O, OP> implements
	UnaryHybridCF<I, O>
{

	public CachedHybridOp(final OP delegate) {
		this(delegate, CachedFunctionOp.otherArgs(delegate, 2));
	}

	protected CachedHybridOp(final OP delegate, final Object[] args) {
		super(delegate, args);
	}

	@Override
	public O calculate(final I input) {
		final Hash hash = new Hash(input, delegate, args);

		@SuppressWarnings("unchecked")
		O output = (O) cache.get(hash);

		if (output == null) {
			output = createOutput(input);
			compute(input, output);
			cache.put(hash, output);
		}
		return output;
	}

	@Override
	public O createOutput(final I input) {
		return delegate.createOutput(input);
	}

	@Override
	public void compute(final I input, final O output) {
		delegate.compute(input, output);
	}

	@Override
	public void setOutput(final O output) {
		delegate.setOutput(output);
	}

	@Override
	public CachedHybridOp<I, O, OP> getIndependentInstance() {
		return this;
	}
}
