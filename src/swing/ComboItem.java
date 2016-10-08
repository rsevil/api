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
		ComboItem<V, L> item = (ComboItem<V, L>)object;
		return this.value.equals(item.getValue());
	}
}
