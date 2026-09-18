public class Main {
    public static void main(String [] args){
        ArvoreBinariaBusca arvore1 = new ArvoreBinariaBusca();
        arvore1.inserir(55);
        arvore1.inserir(20);
        arvore1.inserir(80);
        arvore1.inserir(10);
        arvore1.inserir(40);
        arvore1.inserir(70);
        arvore1.inserir(95);
        arvore1.inserir(5);
        arvore1.inserir(15);

        ArvoreBinariaBusca arvore2 = new ArvoreBinariaBusca();
        int[] vetor = {5,10,15,20,40,55,70,80,95};
        arvore2.construirBalanceada(vetor);

        arvore1.nivelValor(95);
        System.out.println();
        arvore2.nivelValor(95);
        System.out.println();
        arvore1.nivelValor(100);
    }
}
