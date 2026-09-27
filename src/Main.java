import RubroNegra.ArvoreRB;
import RubroNegra.Cor;
import RubroNegra.NoRB;

public class Main {
    public static void main(String [] args){
        NoRB raiz = new NoRB(20, Cor.PRETO);

        raiz.esquerdo = new NoRB(10, Cor.VERMELHO);
        raiz.direito = new NoRB(30, Cor.VERMELHO);
        raiz.esquerdo.esquerdo = new NoRB(5, Cor.VERMELHO);
        raiz.esquerdo.direito = new NoRB(15, Cor.PRETO);
        raiz.direito.esquerdo = new NoRB(25, Cor.PRETO);
        raiz.direito.direito = new NoRB(40, Cor.PRETO);
        raiz.esquerdo.esquerdo.esquerdo = new NoRB(2, Cor.PRETO);
        raiz.esquerdo.esquerdo.direito = new NoRB(7, Cor.PRETO);
        raiz.direito.direito.esquerdo = new NoRB(35, Cor.VERMELHO);
        raiz.direito.direito.direito = new NoRB(50, Cor.VERMELHO);

        ArvoreRB arvore1 = new ArvoreRB();
        arvore1.raiz = raiz;
        arvore1.desenhar();
        arvore1.verAlturaPreta();
    }
}
