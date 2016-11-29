
package experimental.compgraph.channels.rai;

import java.util.function.Function;

import net.imglib2.RandomAccessibleInterval;

import experimental.compgraph.channels.OpsElement;

public interface OpsRaiElement<T> extends OpsRaiCollection<T>, OpsElement<OpsRai<T>> {

	@Override
	<O> OpsRaiElement<O> mapRAI(Function<? super OpsRai<T>, ? extends RandomAccessibleInterval<O>> f);
}
