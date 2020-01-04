import java.util.Vector;
import java.util.HashMap;

public class Hashing{
	Vector<Vector<Vector<String>>> arr;
	int a,b;
	Hashing(int sa,int sb){
		this.a=sa;
		this.b=sb;
		arr=new Vector<Vector<Vector<String>>>();
		for(int i=0;i<a;i++){
			arr.add(new Vector<Vector<String>>());
			for(int j=0;j<b;j++){
				arr.get(i).add(new Vector<String>());
			}
		}
			
	}	
	Hashing(Hashing Hashing){		
		a=Hashing.a;
		b=Hashing.b;	
		arr=new Vector<Vector<Vector<String>>>();
		for(int i=0;i<a;i++){
			arr.add(new Vector<Vector<String>>());
			for(int j=0;j<b;j++){
				arr.get(i).add(new Vector<String>(Hashing.arr.get(i).get(j)));
			}
		}
	}
	public void emptyLetter(){
		for(int i=0;i<a;i++){
		   int count=0;
		   for(int j=0;j<b;j++){
			  if(arr.get(i).get(j).isEmpty()){}
			  else{count++;}
		   }
			if(count==0){
			    arr.get(i).clear();
				arr.remove(i);
				a--;
				i--;
			}
		}
	}
	
	public char getChar(int i){
		for(int j=0;j<b;j++){
			if(!arr.get(i).get(j).isEmpty()){
				return arr.get(i).get(j).get(0).charAt(0);
		    }
		}
		return 0;
	}
	
}