import java.util.ArrayList;

public class CircularArrayList<E> extends ArrayList<E> {
	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	@Override
	public E get(int index) {
		return super.get(index % super.size());
	}
}
