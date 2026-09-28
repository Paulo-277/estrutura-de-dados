import AVL.ArvoreAVL;
import RubroNegra.ArvoreRB;
import RubroNegra.Cor;
import RubroNegra.NoRB;

public class Main {
    public static void main(String [] args){
        ArvoreAVL arvore1 = new ArvoreAVL();
        arvore1.inserir(50);
        arvore1.inserir(30);
        arvore1.inserir(70);
        arvore1.inserir(20);
        arvore1.inserir(15);

        arvore1.desenhar();
    }
}
