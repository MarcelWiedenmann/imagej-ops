
package experimental.tiling;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.imagej.ops.CustomOpEnvironment;
import net.imagej.ops.Op;
import net.imagej.ops.OpCandidate;
import net.imagej.ops.OpEnvironment;
import net.imagej.ops.OpRef;
import net.imagej.ops.OpUtils;

import org.scijava.log.LogService;

import mapreduce.TilableOp;

public class TilingOpEnvironment extends CustomOpEnvironment /* extends CachedOpEnvironment*/ {

	public TilingOpEnvironment(final OpEnvironment parent) {
		super(parent);
	}

	// -- OpEnvironment --

	@Override
	public Object run(final String name, final Object... args) {
		// TODO What to do with all those run-methods? Execution is handled by tiling service and graphs.
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
	public <OP extends Op> OP op(final Class<OP> type, final Object... args) {
		final OpRef opRef = OpRef.create(type, args);
		return (OP) op(opRef);
	}

	@Override
	public Op op(final OpRef ref) {
		return op(Collections.singletonList(ref));
	}

	@Override
	public Op op(final List<OpRef> refs) {
		for (final OpRef ref : refs) {
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
				final OpCandidate match = matcher().findMatch(this, tilingOpRef);
				op = OpUtils.unwrap(match.getModule(), match.getRef());
			}
			// Fallback to regular op if there is no tiling implementation.
			catch (final Exception ex) {
				final OpCandidate match = matcher().findMatch(this, ref);
				op = OpUtils.unwrap(match.getModule(), match.getRef());
			}
			finally {
				if (op != null) {
					if (!(op instanceof TilableOp)) {
						final LogService log = context().getService(LogService.class);
						if (log != null) {
							final String msg = "No tiling op available. Selected regular op: " + op.toString();
							if (log.isDebug()) log.debug(msg);
							else log.warn(msg);
						}
					}
					return op;
				}
			}
		}
		throw new IllegalArgumentException("No matching op - neither tiling nor regular - was found.");
	}
}
