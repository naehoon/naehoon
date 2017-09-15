import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VerbConv { //일본어 동사 변환기
	public static void main(String[]args){
		String[] array;
		String noMasu; //masu 를 자른 형태
		String end; //끝글자
		char cEnd[]; //char 형 끝글자 
	    try {
	        ////////////////////////////////////////////////////////////////
	        BufferedReader in = new BufferedReader(new FileReader(args[0]));
	        String s;

	    while ((s = in.readLine()) != null) {
//	    	array[0] = 1
//	    	array[1] = します
//	        s = "자장면:탕수육:짬뽕:칼국수";
	        array = s.split("\\s"); //공백 구분자 
//	        dumpArray(array);
//	        System.out.println(s);
	        
	        if(array[0].equals("1")){
	        	
				for(int i=0; i < array[1].length(); i++){
					noMasu = array[1].replace("ます", "");
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
//						System.out.println(tmpnum);
//						System.out.println(cEnd[0]);
						
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
						System.out.println(noMasu + cEnd[0] +"う\t"); //의지형
						break;
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
						System.out.println(noMasu + cEnd[0] +"う\t"); //의지형					
						break;
						
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
						System.out.println(noMasu + cEnd[0] +"う\t"); //의지형					
						break;
						
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
							System.out.print(noMasu + "いた" + "\t"); //ta 형
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
						System.out.println(noMasu + cEnd[0] +"う\t"); //의지형					
						break;
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
			}else if(array[0].equals("2")){
				for(int i=0; i < array[1].length(); i++){
					noMasu = array[1].replace("ます", "");
					end = noMasu.substring(noMasu.length()-1, noMasu.length()); //masu 앞 마지막 글자 
					cEnd = end.toCharArray(); //캐릭터형 마지막 글자
					//System.out.println((int)cEnd[0]);
					
					System.out.print(noMasu + "る\t"); //원형
					System.out.print(noMasu + "て\t"); //te 형
					System.out.print(noMasu + "ない\t"); //nai 형
					System.out.print(noMasu + "た\t"); //te 형
					System.out.print(noMasu + "られる\t"); //가능형
					System.out.println(noMasu + "よう"); //의지형
					break;
				}
			}else if(array[0].equals("3")){
				
				for(int i=0; i < array[1].length(); i++){
					
					String noSimasu = array[1].replace("します", "");
					String noKimasu = array[1].replace("きます", "");
					
					end = array[1].substring(array[1].length()-3, array[1].length()); //masu 앞 마지막 글자 
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
	        in.close();
	        ////////////////////////////////////////////////////////////////
	    } catch (IOException e) {
	          System.err.println(e); // 에러가 있다면 메시지 출력
	          System.exit(1);
	    }
	}

	  // 배열을 화면에, 요소별로 알기 쉽게 출력
//	  public static void dumpArray(String[] array) {
//	    for (int i = 0; i < array.length; i++)
//	      System.out.format("array[%d] = %s%n", i, array[i]);
//	  }
}
