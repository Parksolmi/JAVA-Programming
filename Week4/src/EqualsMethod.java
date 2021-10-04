
public class EqualsMethod 
{
	public static void main(String args[]) 
	{
		Circle obj1 = new Circle(5);
		Circle obj2 = new Circle(5);
		if (obj1.equals(obj2))
			System.out.println("같음");
		else
			System.out.println("다름");
	}
}

class Circle {
	int radius; // 반지름

	Circle(int radius) {
		this.radius = radius;
	}

	//equals메서드 오버라이딩
	public boolean equals(Object obj) {
		if (!(obj instanceof Circle))
			return false;
		Circle circle = (Circle) obj;
		if (radius == circle.radius)
			return true;
		else
			return false;
	}
}
