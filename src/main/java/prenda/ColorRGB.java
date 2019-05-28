package prenda;

public class ColorRGB {
	public int rojo;
	public int verde;
	public int azul;
	
	public ColorRGB(int rojo, int verde, int azul) {
		this.rojo = rojo;
		this.verde = verde;
		this.azul = azul;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColorRGB other = (ColorRGB) obj;
		if (azul != other.azul)
			return false;
		if (rojo != other.rojo)
			return false;
		if (verde != other.verde)
			return false;
		return true;
	}
	
}
