
package experimental.compgraph.request;

public interface BinaryInvertibleIntervalMapper {

	// TODO: imagine following scenario:
	// Op (A) has upstream inputs (1) & (2), A needs 2 to properly invert a given Interval (x) using invert(Interval) and
	// request it from 1. I.e. what the done holding A needs to do is:
	// - request info from 2
	// - invert Interval
	// - request data from 1
	// NOTE: Implementing this feature also concerns CompgraphBinaryNode etc.
}
