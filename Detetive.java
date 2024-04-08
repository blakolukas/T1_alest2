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
                break;
            }
        }
        while(mapa.get(y).charAt(x)!='#'){
            // se for digito
            if(Character.isDigit(mapa.get(y).charAt(x)))
            // -
            if(mapa.get(y).charAt(x+1)=='-' && a == 'r'){
                x++;
            }else if(mapa.get(y).charAt(x-1)=='-' && a == 'l'){
                x--;
            }
            // |
            else if(mapa.get(y+1).charAt(x)=='|' && a=='u'){
                y++;
            }else if(mapa.get(y-1).charAt(x)=='|' && a=='d'){
                y--;
            }else if(mapa.get(y).charAt(x+1)=='|' && a=='r'){
                x++;
            }else if(mapa.get(y).charAt(x-1)=='|' && a=='l'){
                x--;
            }
            // /
            else if(mapa.get(y).charAt(x+1)=='/' && a== 'r'){
                x++;
                y++;
                a= 'u';
            }else if(mapa.get(y).charAt(x-1)=='/' && a== 'l'){
                x--;
                y--;
                a= 'd';
            }else if(mapa.get(y+1).charAt(x)=='/' && a== 'u'){
                x++;
                y++;
                a= 'r';
            }else if(mapa.get(y-1).charAt(x)=='/' && a== 'd'){
                x--;
                y--;
                a= 'l';
            }
            // \
            else if(mapa.get(y).charAt(x+1)=='\\' && a== 'r'){
                x++;
                y--;
                a= 'd';
            }else if(mapa.get(y).charAt(x-1)=='\\' && a== 'l'){
                x--;
                y++;
                a= 'u';
            }else if(mapa.get(y+1).charAt(x)=='\\' && a== 'u'){
                x--;
                y++;
                a= 'l';
            }else if(mapa.get(y-1).charAt(x)=='\\' && a== 'd'){
                x++;
                y--;
                a= 'r';
            }

        }
    }
    
}