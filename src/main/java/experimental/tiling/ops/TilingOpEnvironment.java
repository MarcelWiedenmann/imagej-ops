
package experimental.tiling.ops;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.imagej.ops.Op;
import net.imagej.ops.OpCandidate;
import net.imagej.ops.OpEnvironment;
import net.imagej.ops.OpRef;
import net.imagej.ops.OpUtils;
import net.imagej.ops.cached.CachedOpEnvironment;
import net.imglib2.Cursor;
import net.imglib2.Dimensions;
import net.imglib2.RandomAccessibleInterval;

import org.scijava.log.LogService;

import experimental.tiling.Tiling;
import experimental.tiling.TilingStrategy;
import experimental.tiling.TilingConfiguration.TilingType;

public class TilingOpEnvironment extends CachedOpEnvironment {

	public TilingOpEnvironment(final OpEnvironment parent) {
		super(parent);
	}

	@SuppressWarnings("unchecked")
	public <T> Tiling<I, O> tiling(final RandomAccessibleInterval<T> in, final Dimensions dims, final TilingType type,
		final TilingStrategy strategy, final Op... ops)
	{
		final CreateTiling<I, O> tilingOp = this.op(CreateTiling.class, in, dims, type, ops);
		return tilingOp.compute0();
	}

	public <T> Object run(final RandomAccessibleInterval<T> in, final Dimensions dims, final TilingType type,
		final TilingStrategy strategy, final Op... ops)
	{
		final Tiling<I, O> tiling = tiling(in, dims, type, strategy, ops);
		final Cursor<RandomAccessibleInterval<T>> cursor = tiling.view().cursor();

		// TODO: For each op: for each tile: do stuff. Return result.

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
	public <OP extends Op> Object run(final Class<OP> type, final Object... args) {
		return OpEnvironment.run(module(type, args));
	}

	@Override
	public Object run(final Op op, final Object... args) {
		return OpEnvironment.run(module(op, args));
	}

	@Override
	public Op op(final String name, final Object... args) {
		final OpRef<?> opRef = OpRef.create(name, args);

		return op(opRef);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <OP extends Op> OP op(final Class<OP> type, final Object... args) {
		final OpRef<?> opRef = OpRef.create(type, args);

		return (OP) op(opRef);
	}

	@Override
	public Op op(final OpRef<?> ref) {
		return op(Collections.singletonList(ref));
	}

	@Override
	public Op op(final List<OpRef<?>> refs) {
		for (final OpRef<?> ref : refs) {
			// N.B. We have to copy the collection returned by ref.getExtraTypes() since some ops (e.g. SpecialOps) will
			// return an immutable Collections.SingletonSet.
			final Collection<? extends Class<?>> originalExtraTypes = ref.getExtraTypes();
			ArrayList<Class<?>> extraTypes;
			if (originalExtraTypes != null) {
				extraTypes = new ArrayList<>(originalExtraTypes.size() + 1);
				extraTypes.addAll(originalExtraTypes);
			}
			else {
				extraTypes = new ArrayList<>(1);
			}
			extraTypes.add(TilingOp.class);
			final OpRef<?> tilingOpRef = new OpRef<>(ref.getName(), ref.getType(), extraTypes, ref.getOutTypes(), ref
				.getArgs());
			OpCandidate<?> match = null;
			try {
				match = matcher().findMatch(this, tilingOpRef);
			}
			// Fallback to regular op if there is no tiling implementation.
			catch (final IllegalArgumentException ex) {
				match = matcher().findMatch(this, ref);
			}
			finally {
				if (match != null) {
					final LogService log = context().getService(LogService.class);
					if (log != null) {
						final String msg = "No tiling op available. Selected regular op: " + match.cInfo().getClassName();
						if (log.isDebug()) log.debug(msg);
						else log.warn(msg);
					}
					return OpUtils.unwrap(match.getModule(), match.getRef());
				}
			}
		}
		throw new IllegalArgumentException("No matching op was found.");
	}
}
