import java.util.ArrayList;

public class KartOyunuYapayZeka {
    private int maxDerinlik;

    public KartOyunuYapayZeka(int maxDerinlik) {
        this.maxDerinlik = maxDerinlik;
    }

    // Alfa-beta kesme algoritmasini kullanarak en iyi hamleyi seçer
    public int enIyiHamle(KartOyunuDurumu durum) {
        return minimax(durum, maxDerinlik, Integer.MIN_VALUE, Integer.MAX_VALUE, true)[1];
    }

    // Minimax algoritmasi ile en iyi hamleyi seçer
    private int[] minimax(KartOyunuDurumu durum, int derinlik, int alfa, int beta, boolean maksimizeEdiyor) {
        if (durum.bittiMi() || derinlik == 0) {
            return new int[]{durum.skor(), -1};
        }

        if (maksimizeEdiyor){
        int enIyiHamle = -1;
        int enIyiSkor = Integer.MIN_VALUE;
        ArrayList<Integer> olasiHamleler = durum.olasiHamleler();
        for (int i = 0; i < olasiHamleler.size(); i++) {
            int hamle = olasiHamleler.get(i);
            KartOyunuDurumu yeniDurum = durum.hamleYap(hamle);
            int[] sonuc = minimax(yeniDurum, derinlik - 1, alfa, beta, false);
            int skor = sonuc[0];
            if (skor > enIyiSkor) {
                enIyiSkor = skor;
                enIyiHamle = hamle;
            }
            alfa = Math.max(alfa, enIyiSkor);
            if (beta <= alfa) {
                break;
            }
        }
        return new int[]{enIyiSkor, enIyiHamle};
    }
    return new int[]{};
}
}
