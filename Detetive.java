import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Detetive {
    private List<String> mapa;
    private double dindin;

    public Detetive(String file) {
        mapa= new ArrayList<>();
        carrega(file);
        acha(mapa);
        System.out.println(dindin);
    }

    public double getDindin(){
        return dindin;
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

    private void acha(List<String> mapa){
        int x=0;
        int y=0;
        char a= 'r';
        int sum= 0;
        for(int i=0; i<mapa.size(); i++){
            if(mapa.get(i).charAt(0)=='-'){
                y=i;
                System.out.println(x+ " "+ y);
                break;
            }
        }
        while(mapa.get(y).charAt(x)!='#'){
            char currentChar = mapa.get(y).charAt(x);
            
            if (currentChar == '-') {
                System.out.println("a");
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

            } else if (currentChar == '|') {
                System.out.println("b");
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

            } else if (currentChar == '/') {
                System.out.println("c");
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

            } else if (currentChar == '\\') {
                System.out.println("d");
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
                        number = number + Character.getNumericValue(mapa.get(y).charAt(x))*(Math.pow(10, times));
                        x--;
                        times++;
                    }
                }else if(a== 'u'){
                    number = Character.getNumericValue(mapa.get(y).charAt(x));
                    y--;
                }else if(a== 'd'){
                    number = Character.getNumericValue(mapa.get(y).charAt(x));
                    y++;
                }
                sum += number;
                System.out.println(sum);
            }
            
        }
        dindin= sum;
    }
    
}