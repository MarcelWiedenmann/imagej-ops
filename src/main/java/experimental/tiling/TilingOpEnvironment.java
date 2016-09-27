
package experimental.tiling;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import net.imagej.ops.Op;
import net.imagej.ops.OpEnvironment;
import net.imagej.ops.OpRef;
import net.imagej.ops.cached.CachedOpEnvironment;
import net.imglib2.Dimensions;
import net.imglib2.RandomAccessibleInterval;

import org.scijava.log.LogService;

import experimental.tiling.TilingConfiguration.TilingType;
import experimental.tiling.ops.interfaces.TilableOp;

public class TilingOpEnvironment extends CachedOpEnvironment {

	public TilingOpEnvironment(final OpEnvironment parent) {
		super(parent);
	}

	public <T> Tiling<T, ?> tiling(final RandomAccessibleInterval<T> in, final Dimensions dims, final TilingType type,
		final TilingStrategy strategy, final Op... ops)
	{
		final TilingConfiguration config = new TilingConfiguration(dims);
		config.setTilingType(type);
		config.setStrategy(strategy);
		return config.generateTiling(in);
	}

	public <T> Object run(final RandomAccessibleInterval<T> in, final Dimensions dims, final TilingType type,
		final TilingStrategy strategy, final Op... ops)
	{
		// TODO: Create tiling, then, for each op: for each tile: do stuff. Return result.
		throw new RuntimeException("not implemented - WIP");
	}

	// -- OpEnvironment --

	@Override
	public Object run(final String name, final Object... args) {
		// TODO For each this.run-method: Get Cursor from TilingOperator,
		// iterate (in parallel if distributed, sequential if local and large
		// image), reduce to result.

		return OpEnvironment.run(module(name, args));
	}

	@Override
	public Object run(final Class<? extends Op> type, final Object... args) {
		return OpEnvironment.run(module(type, args));
	}

	@Override
	public Object run(final Op op, final Object... args) {
		return OpEnvironment.run(module(op, args));
	}

	@Override
	public Op op(final String name, final Object... args) {
		final OpRef opRef = OpRef.create(name, args);
		return op(opRef);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <OP extends Op> OP op(final Class<OP> type, final Object... args) {
		final OpRef opRef = OpRef.create(type, args);
		return (OP) op(opRef);
	}

	@Override
	public Op op(final OpRef ref) {
		// return op(Collections.singletonList(ref));

		// for (final OpRef ref : refs) {
		// NB: We have to copy the collection returned by ref.getTypes() since some ops (e.g. SpecialOps) will
		// return an immutable Collections.SingletonSet.
		final Collection<Type> originalTypes = ref.getTypes();
		ArrayList<Type> types;
		if (originalTypes != null) {
			types = new ArrayList<>(originalTypes.size() + 1);
			types.addAll(originalTypes);
		}
		else {
			types = new ArrayList<>(1);
		}
		types.add(TilableOp.class);
		final OpRef tilingOpRef = new OpRef(ref.getName(), types, ref.getOutTypes(), ref.getArgs());
		Op op = null;
		try {
			op = super.op(tilingOpRef);
		}
		// Fallback to regular op if there is no tiling implementation.
		catch (final Exception ex) {
			op = super.op(ref);
		}
		finally {
			if (op != null) {
				final LogService log = context().getService(LogService.class);
				if (log != null) {
					final String msg = "No tiling op available. Selected regular op: " + op.toString();
					if (log.isDebug()) log.debug(msg);
					else log.warn(msg);
				}
				return op;
			}
		}
		// }
		throw new IllegalArgumentException("No matching op - neither tiling nor regular - was found.");
	}
}
