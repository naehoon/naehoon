import java.util.Scanner;

public class VerbConvOri {

	public static void main(String[]args){
		
		String test2 =  "ねます";
		//おくります
		String level; //1단 2단 3단.
		char test =  'ね';
		String noMasu; //masu 를 자른 형태
		String end; //끝글자
		char cEnd[]; //char 형 끝글자 
		System.out.println("변환할 단어의 단을 선택하세요 (1,2,3)");
		Scanner sc = new Scanner(System.in);
		String inNum = sc.nextLine();
		System.out.println("변환할 단어를 입력하세요 (ます형으로)");
		String inMasu = sc.nextLine();
		System.out.println("입력받은 단: " + inNum);
		System.out.println("입력받은 단어: " + inMasu);
		//char test9 = 'あ'; //あい
		//System.out.println((int)test9);
			//int tmp = ((int)tmpMasu.charAt(i))+1;
			//System.out.println((int)tmpMasu.charAt(i)+1);
			//System.out.print(tmpMasu.charAt(i));
			//System.out.println(tmpMasu+1);
			
		if(inNum.equals("1")){
			for(int i=0; i < inMasu.length(); i++){
				noMasu = inMasu.replace("ます", "");
				end = noMasu.substring(noMasu.length()-1, noMasu.length()); //masu 앞 마지막 글자 
				cEnd = end.toCharArray(); //캐릭터형 마지막 글자
				//System.out.println((int)cEnd[0]);
				if(end.equals("い") || end.equals("ち") || end.equals("ぢ") || end.equals("り")){
					
					noMasu = noMasu.substring(0, noMasu.length()-1);
					//end = "う"; //おくります //もらいます //いそぎます
					int tmpnum = (int)cEnd[0];
					
					if(end.equals("い")){
						cEnd[0] = (char)(tmpnum+2);
					}else if(end.equals("ち") || end.equals("ぢ")){
						cEnd[0] = (char)(tmpnum+3);
					}else if(end.equals("り")){
						cEnd[0] = (char)(tmpnum+1);
					}
//					System.out.println(tmpnum);
//					System.out.println(cEnd[0]);
					
					System.out.print(noMasu + cEnd[0] +"\t"); //원형
					
					System.out.print(noMasu + "って" + "\t"); // te 형
					
					if(end.equals("い")){
						cEnd[0] = (char)(tmpnum+75);
					}else if(end.equals("ち") || end.equals("ぢ")){
						cEnd[0] = (char)(tmpnum-2);
					}else if(end.equals("り")){
						cEnd[0] = (char)(tmpnum-1);
					}
					
					//end = "ない";
					System.out.print(noMasu + cEnd[0] + "ない\t\t"); //nai 형
					System.out.print(noMasu + "った" + "\t"); //ta 형

					if(end.equals("い")){
						//System.out.println(tmpnum);
						cEnd[0] = (char)(tmpnum+4); //e 단으로 
					}else if(end.equals("ち") || end.equals("ぢ")){
						cEnd[0] = (char)(tmpnum+5);
					}else if(end.equals("り")){
						cEnd[0] = (char)(tmpnum+2);
					}
					
					//end = "る";
					System.out.print(noMasu + cEnd[0] +"る\t"); //가능형
					
					if(end.equals("い")){
						cEnd[0] = (char)(tmpnum+6); //o 단으로 
					}else if(end.equals("ち") || end.equals("ぢ")){
						cEnd[0] = (char)(tmpnum+7);
					}else if(end.equals("り")){
						cEnd[0] = (char)(tmpnum+3);
					}
					//end = "う";
					System.out.print(noMasu + cEnd[0] +"う\t"); //의지형
					return;
				}else if(end.equals("に") || end.equals("み") || end.equals("び")){
					
					noMasu = noMasu.substring(0, noMasu.length()-1);
					//end = "う"; //おくります //もらいます //いそぎます
					int tmpnum = (int)cEnd[0];
					
					if(end.equals("に")){
						cEnd[0] = (char)(tmpnum+1);
					}else if(end.equals("み")){
						cEnd[0] = (char)(tmpnum+1);
					}else if(end.equals("び")){
						cEnd[0] = (char)(tmpnum+3);
					}
					
					System.out.print(noMasu + cEnd[0] +"\t"); //원형
					System.out.print(noMasu + "んで" + "\t"); // te 형
					
					if(end.equals("に")){
						cEnd[0] = (char)(tmpnum-1);
					}else if(end.equals("み")){
						cEnd[0] = (char)(tmpnum-1);
					}else if(end.equals("び")){
						cEnd[0] = (char)(tmpnum-3);
					}
					//end = "ない";
					System.out.print(noMasu + cEnd[0] + "ない\t\t"); //nai 형
					System.out.print(noMasu + "んだ" + "\t"); //ta 형
					
					if(end.equals("に")){
						cEnd[0] = (char)(tmpnum+2);
					}else if(end.equals("み")){
						cEnd[0] = (char)(tmpnum+2);
					}else if(end.equals("び")){
						cEnd[0] = (char)(tmpnum+6);
					}
					
					System.out.print(noMasu + cEnd[0] +"る\t"); //가능형
					
					if(end.equals("に")){
						cEnd[0] = (char)(tmpnum+3);
					}else if(end.equals("み")){
						cEnd[0] = (char)(tmpnum+3);
					}else if(end.equals("び")){
						cEnd[0] = (char)(tmpnum+9);
					}
					//end = "う";
					System.out.print(noMasu + cEnd[0] +"う\t"); //의지형					
					return;
					
				}else if(end.equals("し")){
					noMasu = noMasu.substring(0, noMasu.length()-1);
					//end = "う"; //おくります //もらいます //いそぎます
					int tmpnum = (int)cEnd[0];
					
					if(end.equals("し")){
						cEnd[0] = (char)(tmpnum+2);
					}
					
					System.out.print(noMasu + cEnd[0] +"\t"); //원형
					System.out.print(noMasu + "して" + "\t"); // te 형
					
					if(end.equals("し")){              
						cEnd[0] = (char)(tmpnum-2);
					}
					//end = "ない";
					System.out.print(noMasu + cEnd[0] + "ない\t\t"); //nai 형
					System.out.print(noMasu + "した" + "\t"); //ta 형
					
					if(end.equals("し")){
						cEnd[0] = (char)(tmpnum+4);
					}
					
					System.out.print(noMasu + cEnd[0] +"る\t"); //가능형
					
					if(end.equals("し")){
						cEnd[0] = (char)(tmpnum+6);
					}
					//end = "う";
					System.out.print(noMasu + cEnd[0] +"う\t"); //의지형					
					return;
					
				}else if(end.equals("き") || end.equals("ぎ")){
					noMasu = noMasu.substring(0, noMasu.length()-1);
					//end = "う"; //おくります //もらいます //いそぎます
					int tmpnum = (int)cEnd[0];
					
					if(end.equals("き") || end.equals("ぎ")){
						cEnd[0] = (char)(tmpnum+2);
					}
					
					System.out.print(noMasu + cEnd[0] +"\t"); //원형
					
					if(end.equals("き")){
						System.out.print(noMasu + "いて" + "\t"); // te 형
					}else{
						System.out.print(noMasu + "いで" + "\t"); // te 형
					}
					
					if(end.equals("き") || end.equals("ぎ")){     
						cEnd[0] = (char)(tmpnum-2);
					}
					//end = "ない";
					System.out.print(noMasu + cEnd[0] + "ない\t\t"); //nai 형
					
					if(end.equals("き")){
						System.out.print(noMasu + "いだ" + "\t"); //ta 형
					}else{
						System.out.print(noMasu + "いだ" + "\t"); // ta 형
					}					
					
					if(end.equals("き") || end.equals("ぎ")){    
						cEnd[0] = (char)(tmpnum+4);
					}
					
					System.out.print(noMasu + cEnd[0] +"る\t"); //가능형
					
					if(end.equals("き") || end.equals("ぎ")){  
						cEnd[0] = (char)(tmpnum+6);
					}
					//end = "う";
					System.out.print(noMasu + cEnd[0] +"う\t"); //의지형					
					return;
				}else if(end.equals("いきます")){
					System.out.print("いく");
					System.out.print("いって");
					System.out.print("いかない");
					System.out.print("いった");
					System.out.print("いける");
					System.out.print("いこう");
				}
			}
/*				char chng = (char)((int)tmpMasu.charAt(i)+1);
				System.out.print(chng);*/
		//2단 동사 
		}else if(inNum.equals("2")){
			for(int i=0; i < inMasu.length(); i++){
				noMasu = inMasu.replace("ます", "");
				end = noMasu.substring(noMasu.length()-1, noMasu.length()); //masu 앞 마지막 글자 
				cEnd = end.toCharArray(); //캐릭터형 마지막 글자
				//System.out.println((int)cEnd[0]);
				
				System.out.print(noMasu + "る\t"); //원형
				System.out.print(noMasu + "て\t"); //te 형
				System.out.print(noMasu + "ない\t"); //nai 형
				System.out.print(noMasu + "た\t"); //te 형
				System.out.print(noMasu + "られる\t"); //가능형
				System.out.print(noMasu + "よう"); //의지형
				return;
			}
		}else if(inNum.equals("3")){
			
			for(int i=0; i < inMasu.length(); i++){
				
				String noSimasu = inMasu.replace("します", "");
				String noKimasu = inMasu.replace("きます", "");
				
				end = inMasu.substring(inMasu.length()-3, inMasu.length()); //masu 앞 마지막 글자 
				//System.out.println(end);
				
				if(end.equals("します")){ // 끝이 시마스 

					System.out.print(noSimasu + "する\t"); //원형
					System.out.print(noSimasu + "して\t"); //te 형
					System.out.print(noSimasu + "しない\t"); //nai 형
					System.out.print(noSimasu + "した\t"); //te 형
					System.out.print(noSimasu + "できる\t"); //가능형
					System.out.println(noSimasu +"しよう"); //의지형
					break;
					
				}else if(end.equals( noKimasu + "きます")){ //끝이 키마스 
					System.out.print(noKimasu + "くる\t"); //원형
					System.out.print(noKimasu + "きて\t"); //te 형
					System.out.print(noKimasu + "こない\t"); //nai 형
					System.out.print(noKimasu + "きた\t"); //te 형
					System.out.print(noKimasu + "こられる\t"); //가능형
					System.out.println("こよう"); //의지형
					break;
				}
			}
		}
	}
}
