import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VerbConv { //�Ϻ��� ���� ��ȯ��
	public static void main(String[]args){
		String[] array;
		String noMasu; //masu �� �ڸ� ����
		String end; //������
		char cEnd[]; //char �� ������ 
	    try {
	        ////////////////////////////////////////////////////////////////
	        BufferedReader in = new BufferedReader(new FileReader(args[0]));
	        String s;

	    while ((s = in.readLine()) != null) {
//	    	array[0] = 1
//	    	array[1] = ���ު�
//	        s = "�����:������:«��:Į����";
	        array = s.split("\\s"); //���� ������ 
//	        dumpArray(array);
//	        System.out.println(s);
	        
	        if(array[0].equals("1")){
	        	
				for(int i=0; i < array[1].length(); i++){
					noMasu = array[1].replace("�ު�", "");
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
//						System.out.println(tmpnum);
//						System.out.println(cEnd[0]);
						
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
						System.out.println(noMasu + cEnd[0] +"��\t"); //������
						break;
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
						System.out.println(noMasu + cEnd[0] +"��\t"); //������					
						break;
						
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
						System.out.println(noMasu + cEnd[0] +"��\t"); //������					
						break;
						
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
						System.out.println(noMasu + cEnd[0] +"��\t"); //������					
						break;
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
			}else if(array[0].equals("2")){
				for(int i=0; i < array[1].length(); i++){
					noMasu = array[1].replace("�ު�", "");
					end = noMasu.substring(noMasu.length()-1, noMasu.length()); //masu �� ������ ���� 
					cEnd = end.toCharArray(); //ĳ������ ������ ����
					//System.out.println((int)cEnd[0]);
					
					System.out.print(noMasu + "��\t"); //����
					System.out.print(noMasu + "��\t"); //te ��
					System.out.print(noMasu + "�ʪ�\t"); //nai ��
					System.out.print(noMasu + "��\t"); //te ��
					System.out.print(noMasu + "����\t"); //������
					System.out.println(noMasu + "�誦"); //������
					break;
				}
			}else if(array[0].equals("3")){
				
				for(int i=0; i < array[1].length(); i++){
					
					String noSimasu = array[1].replace("���ު�", "");
					String noKimasu = array[1].replace("���ު�", "");
					
					end = array[1].substring(array[1].length()-3, array[1].length()); //masu �� ������ ���� 
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
	        in.close();
	        ////////////////////////////////////////////////////////////////
	    } catch (IOException e) {
	          System.err.println(e); // ������ �ִٸ� �޽��� ���
	          System.exit(1);
	    }
	}

	  // �迭�� ȭ�鿡, ��Һ��� �˱� ���� ���
//	  public static void dumpArray(String[] array) {
//	    for (int i = 0; i < array.length; i++)
//	      System.out.format("array[%d] = %s%n", i, array[i]);
//	  }
}
