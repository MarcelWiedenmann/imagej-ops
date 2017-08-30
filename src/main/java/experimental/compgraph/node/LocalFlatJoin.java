package experimental.compgraph.node;
//
//package experimental.compgraph.body;
//
//import java.util.function.BiPredicate;
//
//import experimental.compgraph.CompgraphDoubleEdge;
//import experimental.compgraph.CompgraphEdge;
//
//public class DefaultJoin<IN1 extends CompgraphEdge<I1>, IN2 extends CompgraphEdge<I2>, I1, I2, OUT extends CompgraphDoubleEdge<IN1, IN2>>
//	implements Join<IN1, IN2, I1, I2, OUT>
//{
//
//	private final BiPredicate<I1, I2> f;
//
//	public DefaultJoin(final BiPredicate<I1, I2> f) {
//		this.f = f;
//	}
//
//	@Override
//	public BiPredicate<I1, I2> func() {
//		return f;
//	}
//}
