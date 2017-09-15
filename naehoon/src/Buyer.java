import java.util.*;

class Buyer{

/*
	int money = 1000;
	int bonusPoint = 0;

	void buy(Product p){
		if(money < p.price){
			System.out.println("�ܾ��� �����Ͽ� ������ �� �� �����ϴ�");
			return ;
		}

		money -= p.price;
		bonusPoint += p.bonusPoint;
		System.out.println(p + "��/�� �����ϼ̽��ϴ�");
	}
*/

	int money = 1000;
	int bonusPoint = 0;
	//Product[] item = new Product[10];
	//int i=0;
	ArrayList<Product> item = new ArrayList<Product>();

	void buy(Product p){
		if(money < p.price){
			System.out.println("�ܾ��� �����Ͽ� ������ �� �� �����ϴ�");
			return ;
		}

		money -= p.price;
		bonusPoint += p.bonusPoint;
		//item[i++] = p;
		item.add(p);
		System.out.println(p + "��/�� �����ϼ̽��ϴ�");
	}


	void refund(Product p){
		if(item.remove(p)){
			money += p.price;
			bonusPoint -= p.bonusPoint;
			System.out.println(p+"��/�� ��ǰ�ϼ̽��ϴ�.");
		}else{
			System.out.println("�����Ͻ� ��ǰ �� �ش� ��ǰ�� �����ϴ�.");
		}
	}


	void summary(){
		int sum = 0;
		String itemList = "";

		if(item.isEmpty()){
			System.out.println("�����Ͻ� ��ǰ�� �����ϴ�.");
			return;
		}

		for(int i=0; i<item.size(); i++){
			Product p = (Product)item.get(i);
			sum += p.price;
			itemList += (i==0) ? ""  + p : ", " +p ;
		}

		System.out.println("�����Ͻ� ��ǰ�� �ѱݾ��� " + sum + " �����Դϴ�" );
		System.out.println("�����Ͻ� ��ǰ�� ��ǰ��" + itemList + " �Դϴ�" );

	}
}