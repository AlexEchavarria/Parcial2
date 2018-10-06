/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *@autor Banilat, 
*/
package is_bigger_smarter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author USUARIO
 */
public class Is_bigger_smarter {

    /**
     */
    public static class Elefante implements Comparable<Elefante>{
        int peso, iq, id;
        Elefante(){
            this.peso=-1;
            this.id=0;
            this.iq=0;
        }
        /**
         *
         * @param t
         * @return
         */
        @Override
        public int compareTo(Elefante t) {
            return this.peso-t.peso; //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    public static void main(String[] args) throws IOException {
        
        Elefante e[]= new Elefante[10000];
        for(int i=0; i<10000;i++){
            e[i]=new Elefante();
        }
        int cant=0;
        
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        String linea;
        for(int i=0; i<10000; i++){
            linea=bf.readLine();
            if("".equals(linea) || linea==null){
                break;
            }
            StringTokenizer st= new StringTokenizer(linea);
            Elefante ele= new Elefante();
            ele.id=i+1;
            ele.peso=Integer.parseInt(st.nextToken());
            ele.iq=Integer.parseInt(st.nextToken());
            e[i]=ele;
        }
        Arrays.sort(e);
        for (int i=0; i<10000; i++){
            if(e[i].peso!=-1){
                cant=i;
                break;
            }
        }
        System.out.println(cant);
        int[] exist= new int[e.length];
        exist[cant]=1;
        for(int i=cant;i<exist.length ;i++){
            exist[i]=Math.max(1, exist[i]);
            for (int j=cant; j<=i;j++){
                if(e[i].iq<e[j].iq && e[i].peso>e[j].peso){
                    exist[i]=Math.max(exist[j]+1,exist[i] );
                    
                }
            }
            
        }
        
        int max=0;
		for (int i : exist) {
			max=Math.max(i, max);
		}
		
		System.out.println(max);
		int [] solution=new int [max];
		
		boolean first=true;
		int last=0;
		for (int i=exist.length-1;i>=0 && max>0;i--) {
			if (exist[i]==max && (first || last<e[i].iq)) {
				last=e[i].iq;
				solution[--max]=e[i].id;
				first=false;
			}
		}
		
		for (int i=0;i<solution.length;i++) {
			System.out.println(solution[i]);
		}
	}

        
    }
    

