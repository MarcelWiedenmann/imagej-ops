
package net.imagej.ops.cached;

import java.util.ArrayList;

import net.imagej.ops.AbstractOp;
import net.imagej.ops.Op;
import net.imagej.ops.special.function.UnaryFunctionOp;

import org.scijava.cache.CacheService;
import org.scijava.command.CommandInfo;
import org.scijava.module.Module;
import org.scijava.module.ModuleItem;
import org.scijava.plugin.Parameter;

/**
 * Wraps a {@link UnaryFunctionOp} and caches the results. New inputs will result in re-computation of the result.
 *
 * @author Christian Dietz (University of Konstanz)
 * @param <I>
 * @param <O>
 */
public class CachedFunctionOp<I, O, OP extends UnaryFunctionOp<I, O>> extends AbstractOp implements
	UnaryFunctionOp<I, O>
{

	@Parameter
	protected CacheService cache;

	protected final OP delegate;

	protected final Object[] args;

	public CachedFunctionOp(final OP delegate) {
		this(delegate, CachedFunctionOp.otherArgs(delegate, 1));
	}

	protected CachedFunctionOp(final OP delegate, final Object[] args) {
		this.delegate = delegate;
		this.args = args;
	}

	@Override
	public O calculate(final I input) {

		final Hash hash = new Hash(input, delegate, args);

		@SuppressWarnings("unchecked")
		O output = (O) cache.get(hash);

		if (output == null) {
			output = delegate.calculate(input);
			cache.put(hash, output);
		}
		return output;
	}

	@Override
	public void run() {
		delegate.run();
	}

	@Override
	public I in() {
		return delegate.in();
	}

	@Override
	public void setInput(final I input) {
		delegate.setInput(input);
	}

	@Override
	public O out() {
		return delegate.out();
	}

	@Override
	public void initialize() {
		delegate.initialize();
	}

	public OP getDelegate() {
		return delegate;
	}

	@Override
	public CachedFunctionOp<I, O, OP> getIndependentInstance() {
		return this;
	}

	/**
	 * Gets the given {@link Op} instance's argument value, starting at the specified offset.
	 */
	protected static Object[] otherArgs(final Op op, final int offset) {
		final CommandInfo cInfo = op.ops().info(op).cInfo();
		final Module module = cInfo.createModule(op);
		final ArrayList<Object> args = new ArrayList<>();
		int i = 0;
		for (final ModuleItem<?> input : cInfo.inputs()) {
			if (i++ >= offset) args.add(input.getValue(module));
		}
		return args.toArray();
	}

	/**
	 * Simple utility class to wrap two objects and an array of objects in a single object which combines their hashes.
	 */
	protected class Hash {

		private final int hash;

		public Hash(final Object o1, final Object o2, final Object[] args) {
			long h = o1.hashCode() ^ o2.getClass().getSimpleName().hashCode();

			for (final Object o : args) {
				h ^= o.hashCode();
			}

			hash = (int) h;
		}

		@Override
		public int hashCode() {
			return hash;
		}

		@Override
		public boolean equals(final Object obj) {
			if (obj == this) return true;
			if (obj instanceof CachedFunctionOp.Hash) return hash == ((Hash) obj).hash;
			return false;
		}
	}
}
