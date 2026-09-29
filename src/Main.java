import AVL.ArvoreAVL;

public class Main {
    public static void main(String [] args){
        ArvoreAVL arvore1 = new ArvoreAVL();
        int[] crescente = {1,2,3,4,5,6,7,8,9,10,11,12,12,12,12};
        arvore1.inserirVetor(crescente);
        arvore1.desenhar();
        arvore1.busca(13);
        arvore1.busca(11);
        arvore1.busca(12);

    }
}
