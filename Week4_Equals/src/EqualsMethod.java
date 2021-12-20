
public class EqualsMethod 
{
	public static void main(String args[]) 
	{
		Circle obj1 = new Circle(5);
		Circle obj2 = new Circle(5);
		if (obj1.equals(obj2))
			System.out.println("����");
		else
			System.out.println("�ٸ�");
	}
}

class Circle {
	int radius; // ������

	Circle(int radius) {
		this.radius = radius;
	}

	//equals�޼��� �������̵�
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
