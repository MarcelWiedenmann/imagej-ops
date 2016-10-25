
package experimental.tiling.mapreduce;

public interface TilableAggregateOp {

//	unaryFunction(I,O) +
//
//	O combine(O,O)

}

//public interface PairwiseMapTiling<Tiling, RAI<O>> {
//
//
//	// Examples: Stiching, Combine Labelings.
//	// Locally: We can actually make use of these methods, because we will then
//	// still process a tiling. However, we must have two "real" reduces to create a labeling + image.
//
//	// grouping findet statt by image key (all tiles per image)
//	// pairwise means all pairs in a certain neighborhood
//
//	// one neighborhood pair-wise
//	RAI<O> pairwise(RAI<T> rai, RAI<T> rai);
//
//	// all tiles
//	RAI<O> combine(RAI<RAI<O>> allTiles){
//
//	}
//}
//
//// Notes: Segmentation -> ROIs is no problem after segmentations of tilings have been merged. Locally and globally no problem.
//
//// Notes: Stitching requires the application of the determined AffineGet Transformations on Each tile.
//// However, this is complicated if we have more than translation (e.g. scaling or rotation).
//// Locally it's no problem (we love views)
//// Globally: The strategy would be to create a new resulting image (virtually), tile it (virtually), distribute (virtually), copy all tiles which  need to be copied to determine content of virtually distributed tiles to spark node and apply transformation (i.e. make non-virtual).
//// We have to be smart that we copy tiles such that we reduce the amount of copies.
