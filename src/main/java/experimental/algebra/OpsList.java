
package experimental.algebra;

import net.imglib2.util.Pair;

// special 1D grid
public interface OpsList<I> extends OpsRAI<I> {

	<I2> OpsList<Pair<I, I2>> join(OpsList<I2> l);

	// -- OpsCollection --

	// ...
}
