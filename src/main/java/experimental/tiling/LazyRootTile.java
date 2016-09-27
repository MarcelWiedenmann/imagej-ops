
package experimental.tiling;

public class LazyRootTile<T> extends LazyTile<T, T> {

	private T tile;

	public LazyRootTile(final T tile) {
		super(null, null);
		this.tile = tile;
	}

	@Override
	public T get() {
		return tile;
	}

	public void set(final T tile) {
		this.tile = tile;
	}
}
