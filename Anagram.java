import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Anagram
{
	Hashing Hashing=new Hashing(37,10);
	int size; //size of vocabulary file
	
	public Anagram(String filename){
		try{
			Scanner s = new Scanner(new File(filename));
			size=s.nextInt();
			for(int i=0;i<size;i++){
				String word=s.next();
				int len=word.length();
				if(len<3||len>12){
					continue;
				}
				else{
			    	len=len-3;
			    }
				if(word.charAt(0)<='9' && word.charAt(0)>='0'){
					Hashing.arr.get(word.charAt(0)%48).get(len).add(word);
				}
				else if(word.charAt(0)<='z'&&word.charAt(0)>='a'){
					Hashing.arr.get(word.charAt(0)%87).get(len).add(word);
				}
				else if(word.charAt(0)=='\''){
					Hashing.arr.get(36).get(len).add(word);
				}
				
			}
			Hashing.emptyLetter();
			s.close();
		
		} catch(FileNotFoundException e){
		
			System.out.println(e);
		}
	}
	public static void main(String args[]){
		try{
			long startTime=System.nanoTime();
			Scanner sc= new Scanner(new File(args[1]));
			String st= args[0];
			Anagram agm=new Anagram(st);
			int N=sc.nextInt();
			for(int i=0;i<N;i++){
				String cases=sc.next().toString();
				if(cases.length()>12||cases.length()<3)
					continue;
				Set<String> hs=agm.combinations(agm.Hashing, cases,0);
				ArrayList<String> ar= new ArrayList<String>();
				ar.addAll(hs);
				Collections.sort(ar);
				for(int j=0;j<ar.size();j++){
					System.out.println(ar.get(j).trim());
				}
				System.out.println("-1");
			}
			long time=System.nanoTime()-startTime;
	        System.out.println("time: "+time+" nanos");
			sc.close();
		} 
		catch (FileNotFoundException e) {
		  System.out.println("file not found");
		}
	
	}
	

 Hashing refine(Hashing arr, String str){
        int b=0;
		Hashing copy=new Hashing(arr);
		for(int i=0;i<arr.a;i++){
			char ch=arr.getChar(i);
			b=arr.b;
			if(str.indexOf(ch)==-1){
				for(int j=0;j<b;j++){
					copy.arr.get(i).get(j).clear();
					}
			}
			for(int j=0;j<b;j++){	
				if(j>str.length()-3){
					copy.arr.get(i).remove(j);
					j--;
					b--;
				}
			}
		}
		copy.b=b;	
		for(int i=0;i<copy.a;i++){	
			for(int j=0;j<copy.b;j++){
				for(int k=0;k<copy.arr.get(i).get(j).size();k++){
					String subStr=copy.arr.get(i).get(j).get(k);
					int[] freq = new int[128];
					for (int l = 0; l < str.length(); l++){
						++freq[str.charAt(l)];
					}
					for (int l = 0; l < subStr.length(); l++){
						--freq[subStr.charAt(l)];
						if (freq[subStr.charAt(l)] < 0){
							copy.arr.get(i).get(j).remove(k);
							k--;
							break;
						}
					}
				}
			}
		
		}
		return copy;}
			
				
	Set<String> combinations(Hashing arr,String str,int count){
        Set<String> array=new HashSet<String>();
		if(count>3){
			return array;
		}
		if(str.length()==0){
			array.add("");
			return array;
		}
		if(str.length()<3){
			return array;
		}
		Hashing vb=refine(arr,str);
		for(int i=0;i<vb.a;i++){
			for(int j=0;j<vb.b;j++){
				for(int k=0;k<vb.arr.get(i).get(j).size();k++){
					String word=vb.arr.get(i).get(j).get(k);
					String copy=str;
					for (int l = 0; l < word.length(); l++){
						copy=copy.replaceFirst(""+word.charAt(l), ""); 
					}
					Set<String> array2= combinations(vb,copy,count+1);
					Iterator<String> iter = array2.iterator();
					while(iter.hasNext()){
						array.add(word+" "+iter.next());
					}
				}
			}
		}
		return array;
	}
	
	
	
}