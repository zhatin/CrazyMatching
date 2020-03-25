import java.util.ArrayList;
import java.util.List;

public class Card {
	Card() {
		this.size = 8;
		this.numbers = new ArrayList<Integer>();
	}

	Card(int size) {
		this.size = size;
		this.numbers = new ArrayList<Integer>();
	}

	Card(Card c) {
		if (c != null) {
			this.size = c.getSize();
			this.numbers = new ArrayList<Integer>();
			for (int i = 0; i < c.getLength(); i++) {
				this.numbers.add(c.getNumber(i));
			}
		} else {
			this.size = 8;
			this.numbers = new ArrayList<Integer>();

		}
	}

	int size;
	List<Integer> numbers;

	public int getSize() {
		return size;
	}

	public void putNumber(int i) {
		if (getLength() < size)
			this.numbers.add(i);
	}
	
	public boolean isEmpty() {
		return getLength()==0;
	}
	
	public boolean isFull() {
		return getLength()>=this.size;
	}

	public int getNumber(int i) {
		if (this.size > i)
			return this.numbers.get(i).intValue();
		else
			return -1;
	}

	public boolean findNumber(int val) {
		boolean result = false;
		for (int i = 0; i < getLength(); i++) {
			if (this.numbers.get(i).intValue() == val) {
				result = true;
			}
		}
		return result;
	}

	public int getLength() {
		if (this.numbers == null || this.numbers.isEmpty())
			return 0;
		return this.numbers.size();
	}

	public int fillIncNumer(int result) {
		int i = result;
		while (getLength() < size) {
			putNumber(i);
			i++;
		}
		return i;
	}

	@Override
	public String toString() {
		String val = "[";
		for (int i = 0; i < this.numbers.size(); i++) {
			val = val + " " + this.numbers.get(i);
		}
		val = val + " ]";
		return val;
	}

	public void print() {
		System.out.println(this);
	}

}
