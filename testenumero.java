public class testenumero {
    public static int divisaoInteira(int a, int b) {
        if (b == 0) {
            System.out.println("Não é divisível por zero");
            return 0; // Retorna um valor padrão
        }
        if (a < b) {
            return 0;
        }
        return 1 + divisaoInteira(a - b, b);
    }

    public static void main(String[] args) {
        System.out.println(divisaoInteira(10, 2));
    }

}
