//
//package experimental.compgraph.tiling;
//
//import net.imglib2.RandomAccessibleInterval;
//
//import experimental.compgraph.AbstractCompgraphSourceNode;
//import experimental.compgraph.request.ContextualRequests;
//import experimental.compgraph.request.Requestable;
//import experimental.compgraph.request.Requests;
//
//public class LocalTilingSource<T> extends
//	AbstractCompgraphSourceNode<RandomAccessibleInterval<T>, Requestable<Requests, RandomAccessibleInterval<T>>>
//{
//
//	public LocalTilingSource(final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> inData) {
//		super(getRequestable(inData));
//	}
//
//	private static <T> Requestable<Requests, RandomAccessibleInterval<T>> getRequestable(
//		final RandomAccessibleInterval<? extends RandomAccessibleInterval<T>> inData)
//	{
//		return new Requestable<Requests, RandomAccessibleInterval<T>>() {
//
//			@Override
//			public Requestable<Requests, RandomAccessibleInterval<T>> inner() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public RandomAccessibleInterval<T> request(final Requests r) {
//
//				if (r instanceof ContextualRequests) {
//					final ContextualRequests crs = (ContextualRequests) r;
//					for (final ContextualRequest cr : crs) {
//
//					}
//				}
//
//				return null;
//			}
//		};
//	}
//}
