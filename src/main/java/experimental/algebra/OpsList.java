
package experimental.algebra;

import net.imglib2.util.Pair;

public interface OpsList<I> extends OpsCollection<I> {

	<I2> OpsList<Pair<I, I2>> join(OpsList<I2> l);

	// -- OpsCollection --

	// ...
}
