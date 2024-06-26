import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Detetive {
    private List<String> mapa;
    private long dindin;

    public Detetive(String file) {
        long a= System.currentTimeMillis();
        mapa= new ArrayList<>();
        carrega(file);
        caminha(mapa);
        System.out.println("dinheiro encontrado: "+dindin+" $");
        long b= System.currentTimeMillis();
        long time= b-a;
        System.out.println(time+" ms");
    }
    
    private void carrega(String arq){
        try (BufferedReader reader = new BufferedReader(new FileReader(arq))) {
            String line;
            while((line=reader.readLine()) != null){
                mapa.add(line);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void caminha(List<String> mapa){
        int x=0;
        int y=0;
        char a= 'r';//variavel que guarda direção
        int sum= 0; //soma
        for(int i=0; i<mapa.size(); i++){
            if(mapa.get(i).charAt(0)=='-'){
                y=i;
                System.out.println("inicia nas coordenadas: "+x+ " "+ y);
                break;
            }
        }
        while(mapa.get(y).charAt(x)!='#'){
            char currentChar = mapa.get(y).charAt(x);
            // -
            if (currentChar == '-') {
                System.out.println("-");
                if (a == 'r'){
                    x++;
                }
                else if (a == 'l'){
                    x--;
                }
                else if(a == 'u'){
                    y--;
                }
                else if(a == 'd'){
                    y++;
                }
            // |
            } else if (currentChar == '|') {
                System.out.println("|");
                if (a == 'u'){
                    y--;
                } else if (a == 'd'){
                    y++;
                } else{
                    if(a == 'r'){
                        x++;
                    }else  if(a == 'l'){
                        x--;
                    }
                }
            // /
            } else if (currentChar == '/') {
                System.out.println("/");
                if (a == 'r') {
                    y--;
                    a = 'u';
                } else if (a == 'l') {
                    y++;
                    a = 'd';
                } else if (a == 'u') {
                    x++;
                    a = 'r';
                } else if (a == 'd') {
                    x--;
                    a = 'l';
                }
            // \
            } else if (currentChar == '\\') {
                System.out.println("\\");
                if (a == 'r') {
                    y++;
                    a = 'd';
                } else if (a == 'l') {
                    y--;
                    a = 'u';
                } else if (a == 'u') {
                    x--;
                    a = 'l';
                } else if (a == 'd') {
                    x++;
                    a = 'r';
                }
            // se for dígito
            } else if (Character.isDigit(currentChar)) {
                double number = 0;
                int times=0;
                if(a=='r'){
                    while (x < mapa.get(y).length() && Character.isDigit(mapa.get(y).charAt(x))) {
                        number = number * 10 + Character.getNumericValue(mapa.get(y).charAt(x));
                        x++;
                    }
                }else if(a=='l'){
                    while (x < mapa.get(y).length() && Character.isDigit(mapa.get(y).charAt(x))) {
                        number = number + Character.getNumericValue(mapa.get(y).charAt(x))*(Math.pow(10, times)); //reverso de right
                        x--;
                        times++;
                    }
                }else if(a== 'u'){
                    if((x+1 < mapa.get(y).length() || x-1 < 0) && ((Character.isDigit(mapa.get(y).charAt(x-1))==true || Character.isDigit(mapa.get(y).charAt(x+1))==true) || ((mapa.get(y).charAt(x+1)=='-')||(mapa.get(y).charAt(x-1)=='-')))){
                        y--;
                    }else{
                        number = Character.getNumericValue(mapa.get(y).charAt(x));
                        y--;
                    }
                   
                    
                }else if(a== 'd'){
                    if((x+1 < mapa.get(y).length() || x-1 < 0) && ((Character.isDigit(mapa.get(y).charAt(x-1))==true || Character.isDigit(mapa.get(y).charAt(x+1))==true) || ((mapa.get(y).charAt(x+1)=='-')||(mapa.get(y).charAt(x-1)=='-')))){
                        y++;
                    }else{
                        number = Character.getNumericValue(mapa.get(y).charAt(x));
                        y++;
                    }
                }
                sum += number;
                System.out.println(sum);
            }
            
        }
        dindin= sum;
    }
    
}