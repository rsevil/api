package swing;

public class ComboItem<V, L> {
	private V value;
	private L label;
	
	public ComboItem(V value, L label) {
		this.value = value;
		this.label = label;
	}

	public V getValue() {
		return this.value;
	}

	public L getLabel() {
		return this.label;
	}

	public String toString() {
		return this.label.toString();
	}
	
	public boolean equals(Object object)
	{
		@SuppressWarnings("unchecked")
		ComboItem<V, L> item = (ComboItem<V, L>)object;
		if (item != null && this.value != null) {
			return this.value.equals(item.getValue());
		}
		else {
			return false;
		}
	}
}
