//
//package experimental.compgraph.node;
//
//import java.util.function.BiFunction;
//
//import experimental.compgraph.CompgraphEdge;
//import experimental.compgraph.CompgraphNodeBody;
//import experimental.compgraph.CompgraphSingleEdge;
//
//// TODO: a reduce needs both dataflow and edge (as it reduces from a collection/stream to an element)
//public interface Reduce<IN extends CompgraphEdge<I>, I, O, OUT extends CompgraphSingleEdge<O>> extends
//	CompgraphNodeBody<IN, I, O, OUT>
//{
//
//	O memo();
//
//	BiFunction<O, ? super I, O> func();
//
//	BiFunction<O, O, O> merge();
//}
