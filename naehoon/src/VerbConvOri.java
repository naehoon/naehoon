import java.util.Scanner;

public class VerbConvOri {

	public static void main(String[]args){
		
		String test2 =  "�ͪު�";
		//������ު�
		String level; //1�� 2�� 3��.
		char test =  '��';
		String noMasu; //masu �� �ڸ� ����
		String end; //������
		char cEnd[]; //char �� ������ 
		System.out.println("��ȯ�� �ܾ��� ���� �����ϼ��� (1,2,3)");
		Scanner sc = new Scanner(System.in);
		String inNum = sc.nextLine();
		System.out.println("��ȯ�� �ܾ �Է��ϼ��� (�ު�������)");
		String inMasu = sc.nextLine();
		System.out.println("�Է¹��� ��: " + inNum);
		System.out.println("�Է¹��� �ܾ�: " + inMasu);
		//char test9 = '��'; //����
		//System.out.println((int)test9);
			//int tmp = ((int)tmpMasu.charAt(i))+1;
			//System.out.println((int)tmpMasu.charAt(i)+1);
			//System.out.print(tmpMasu.charAt(i));
			//System.out.println(tmpMasu+1);
			
		if(inNum.equals("1")){
			for(int i=0; i < inMasu.length(); i++){
				noMasu = inMasu.replace("�ު�", "");
				end = noMasu.substring(noMasu.length()-1, noMasu.length()); //masu �� ������ ���� 
				cEnd = end.toCharArray(); //ĳ������ ������ ����
				//System.out.println((int)cEnd[0]);
				if(end.equals("��") || end.equals("��") || end.equals("��") || end.equals("��")){
					
					noMasu = noMasu.substring(0, noMasu.length()-1);
					//end = "��"; //������ު� //��骤�ު� //�������ު�
					int tmpnum = (int)cEnd[0];
					
					if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+2);
					}else if(end.equals("��") || end.equals("��")){
						cEnd[0] = (char)(tmpnum+3);
					}else if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+1);
					}
//					System.out.println(tmpnum);
//					System.out.println(cEnd[0]);
					
					System.out.print(noMasu + cEnd[0] +"\t"); //����
					
					System.out.print(noMasu + "�ê�" + "\t"); // te ��
					
					if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+75);
					}else if(end.equals("��") || end.equals("��")){
						cEnd[0] = (char)(tmpnum-2);
					}else if(end.equals("��")){
						cEnd[0] = (char)(tmpnum-1);
					}
					
					//end = "�ʪ�";
					System.out.print(noMasu + cEnd[0] + "�ʪ�\t\t"); //nai ��
					System.out.print(noMasu + "�ê�" + "\t"); //ta ��

					if(end.equals("��")){
						//System.out.println(tmpnum);
						cEnd[0] = (char)(tmpnum+4); //e ������ 
					}else if(end.equals("��") || end.equals("��")){
						cEnd[0] = (char)(tmpnum+5);
					}else if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+2);
					}
					
					//end = "��";
					System.out.print(noMasu + cEnd[0] +"��\t"); //������
					
					if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+6); //o ������ 
					}else if(end.equals("��") || end.equals("��")){
						cEnd[0] = (char)(tmpnum+7);
					}else if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+3);
					}
					//end = "��";
					System.out.print(noMasu + cEnd[0] +"��\t"); //������
					return;
				}else if(end.equals("��") || end.equals("��") || end.equals("��")){
					
					noMasu = noMasu.substring(0, noMasu.length()-1);
					//end = "��"; //������ު� //��骤�ު� //�������ު�
					int tmpnum = (int)cEnd[0];
					
					if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+1);
					}else if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+1);
					}else if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+3);
					}
					
					System.out.print(noMasu + cEnd[0] +"\t"); //����
					System.out.print(noMasu + "���" + "\t"); // te ��
					
					if(end.equals("��")){
						cEnd[0] = (char)(tmpnum-1);
					}else if(end.equals("��")){
						cEnd[0] = (char)(tmpnum-1);
					}else if(end.equals("��")){
						cEnd[0] = (char)(tmpnum-3);
					}
					//end = "�ʪ�";
					System.out.print(noMasu + cEnd[0] + "�ʪ�\t\t"); //nai ��
					System.out.print(noMasu + "���" + "\t"); //ta ��
					
					if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+2);
					}else if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+2);
					}else if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+6);
					}
					
					System.out.print(noMasu + cEnd[0] +"��\t"); //������
					
					if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+3);
					}else if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+3);
					}else if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+9);
					}
					//end = "��";
					System.out.print(noMasu + cEnd[0] +"��\t"); //������					
					return;
					
				}else if(end.equals("��")){
					noMasu = noMasu.substring(0, noMasu.length()-1);
					//end = "��"; //������ު� //��骤�ު� //�������ު�
					int tmpnum = (int)cEnd[0];
					
					if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+2);
					}
					
					System.out.print(noMasu + cEnd[0] +"\t"); //����
					System.out.print(noMasu + "����" + "\t"); // te ��
					
					if(end.equals("��")){              
						cEnd[0] = (char)(tmpnum-2);
					}
					//end = "�ʪ�";
					System.out.print(noMasu + cEnd[0] + "�ʪ�\t\t"); //nai ��
					System.out.print(noMasu + "����" + "\t"); //ta ��
					
					if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+4);
					}
					
					System.out.print(noMasu + cEnd[0] +"��\t"); //������
					
					if(end.equals("��")){
						cEnd[0] = (char)(tmpnum+6);
					}
					//end = "��";
					System.out.print(noMasu + cEnd[0] +"��\t"); //������					
					return;
					
				}else if(end.equals("��") || end.equals("��")){
					noMasu = noMasu.substring(0, noMasu.length()-1);
					//end = "��"; //������ު� //��骤�ު� //�������ު�
					int tmpnum = (int)cEnd[0];
					
					if(end.equals("��") || end.equals("��")){
						cEnd[0] = (char)(tmpnum+2);
					}
					
					System.out.print(noMasu + cEnd[0] +"\t"); //����
					
					if(end.equals("��")){
						System.out.print(noMasu + "����" + "\t"); // te ��
					}else{
						System.out.print(noMasu + "����" + "\t"); // te ��
					}
					
					if(end.equals("��") || end.equals("��")){     
						cEnd[0] = (char)(tmpnum-2);
					}
					//end = "�ʪ�";
					System.out.print(noMasu + cEnd[0] + "�ʪ�\t\t"); //nai ��
					
					if(end.equals("��")){
						System.out.print(noMasu + "����" + "\t"); //ta ��
					}else{
						System.out.print(noMasu + "����" + "\t"); // ta ��
					}					
					
					if(end.equals("��") || end.equals("��")){    
						cEnd[0] = (char)(tmpnum+4);
					}
					
					System.out.print(noMasu + cEnd[0] +"��\t"); //������
					
					if(end.equals("��") || end.equals("��")){  
						cEnd[0] = (char)(tmpnum+6);
					}
					//end = "��";
					System.out.print(noMasu + cEnd[0] +"��\t"); //������					
					return;
				}else if(end.equals("�����ު�")){
					System.out.print("����");
					System.out.print("���ê�");
					System.out.print("�����ʪ�");
					System.out.print("���ê�");
					System.out.print("������");
					System.out.print("������");
				}
			}
/*				char chng = (char)((int)tmpMasu.charAt(i)+1);
				System.out.print(chng);*/
		//2�� ���� 
		}else if(inNum.equals("2")){
			for(int i=0; i < inMasu.length(); i++){
				noMasu = inMasu.replace("�ު�", "");
				end = noMasu.substring(noMasu.length()-1, noMasu.length()); //masu �� ������ ���� 
				cEnd = end.toCharArray(); //ĳ������ ������ ����
				//System.out.println((int)cEnd[0]);
				
				System.out.print(noMasu + "��\t"); //����
				System.out.print(noMasu + "��\t"); //te ��
				System.out.print(noMasu + "�ʪ�\t"); //nai ��
				System.out.print(noMasu + "��\t"); //te ��
				System.out.print(noMasu + "����\t"); //������
				System.out.print(noMasu + "�誦"); //������
				return;
			}
		}else if(inNum.equals("3")){
			
			for(int i=0; i < inMasu.length(); i++){
				
				String noSimasu = inMasu.replace("���ު�", "");
				String noKimasu = inMasu.replace("���ު�", "");
				
				end = inMasu.substring(inMasu.length()-3, inMasu.length()); //masu �� ������ ���� 
				//System.out.println(end);
				
				if(end.equals("���ު�")){ // ���� �ø��� 

					System.out.print(noSimasu + "����\t"); //����
					System.out.print(noSimasu + "����\t"); //te ��
					System.out.print(noSimasu + "���ʪ�\t"); //nai ��
					System.out.print(noSimasu + "����\t"); //te ��
					System.out.print(noSimasu + "�Ǫ���\t"); //������
					System.out.println(noSimasu +"���誦"); //������
					break;
					
				}else if(end.equals( noKimasu + "���ު�")){ //���� Ű���� 
					System.out.print(noKimasu + "����\t"); //����
					System.out.print(noKimasu + "����\t"); //te ��
					System.out.print(noKimasu + "���ʪ�\t"); //nai ��
					System.out.print(noKimasu + "����\t"); //te ��
					System.out.print(noKimasu + "������\t"); //������
					System.out.println("���誦"); //������
					break;
				}
			}
		}
	}
}
