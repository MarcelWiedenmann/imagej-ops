
package experimental.compgraph;

import java.util.function.Function;

public interface Node<IN extends Edge<?>, BODY extends Function<? super IN, OUT>, OUT extends UnaryEdge<?>> {

	IN in();

	BODY body();

	OUT out();

	void setOutput(OUT out);
}
