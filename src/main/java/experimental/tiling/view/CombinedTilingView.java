package experimental.tiling.view;
//
//package bachelorprojekt.view;
//
//import java.util.List;
//
//import net.imglib2.RandomAccessibleInterval;
//
//import bachelorprojekt.tiling.Tiling;
//
//public class TilingAsImageView<T> extends TilesAsImageView<T> {
//
//	private final Tiling<T> tiling;
//
//	public TilingAsImageView(final List<RandomAccessibleInterval<T>> tiles, final Tiling<T> tiling) {
//		super(tiling.getDescription().getStrategy().transformBack(tiles,), tiling.getDescription().getMapper());
//		this.tiling = tiling;
//	}
//
//	public Tiling<T> getTiling() {
//		return tiling;
//	}
//}
