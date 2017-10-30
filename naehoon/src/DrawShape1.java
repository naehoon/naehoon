import java.awt.Frame;
import java.awt.Graphics;

public class DrawShape1 extends Frame{

	public static void main(String[]args){

		DrawShape1 win = new DrawShape1("�����׸���");

/*
		Point[] p = {new Point(100, 100)
			,new Point(140, 50)
			,new Point(200, 100)
			};

		Triangle t = new Triangle(p);
		Circle c = new Circle(new Point(150, 150), 50);
		//heap ������ ������ Point ��ü�� 

		t.color = "Red";

		t.draw(); // �ﰢ���� �׸���.
		c.draw(); // ���� �׸���.
*/


}
	public void paint(Graphics g){
			Point[] p = {new Point(100, 100)
						,new Point(140, 50)
						,new Point(200, 100)
						};

			Triangle t = new Triangle(p);
			Circle c = new Circle(new Point(150, 150), 50);

			g.drawOval(c.center.x, c.center.y, c.r,c.r);
			g.drawLine(t.p[0].x, t.p[0].y, t.p[1].x, p[1].y);
			g.drawLine(t.p[1].x, t.p[1].y, t.p[2].x, p[2].y);
			g.drawLine(t.p[2].x, t.p[2].y, t.p[0].x, p[0].y);
	}


		public DrawShape1(String title){
			super(title);
			setSize(300, 300);
			setVisible(true);
		}

	}
