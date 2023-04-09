import java.util.ArrayList;

public class yeniKarOyunuYapayZeka {
    private int derinlik;
    private int oyuncu;

    public yeniKarOyunuYapayZeka(int derinlik, int oyuncu) {
        this.derinlik = derinlik;
        this.oyuncu = oyuncu;
    }

    public int enIyiHamleyiBul(KartOyunuDurumu durum) {
        int[] sonuc = minimax(durum, derinlik, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        return sonuc[1];
    }

    private int[] minimax(KartOyunuDurumu durum, int derinlik, int alfa, int beta, boolean maksimizeEdiyor) {
        if (derinlik == 0 || durum.bittiMi()) {
            int skor = durum.skor();
            return new int[]{skor, -1};
        }

        if (maksimizeEdiyor) {
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
        } else {
            int enIyiHamle = -1;
            int enIyiSkor = Integer.MAX_VALUE;
            ArrayList<Integer> olasiHamleler = durum.olasiHamleler();
            for (int i = 0; i < olasiHamleler.size(); i++) {
                int hamle = olasiHamleler.get(i);
                KartOyunuDurumu yeniDurum = durum.hamleYap(hamle);
                int[] sonuc = minimax(yeniDurum, derinlik - 1, alfa, beta, true);
                int skor = sonuc[0];
                if (skor < enIyiSkor) {
                    enIyiSkor = skor;
                    enIyiHamle = hamle;
                }
                beta = Math.min(beta, enIyiSkor);
                if (beta <= alfa) {
                    break;
                }
            }
            return new int[]{enIyiSkor, enIyiHamle};
        }
    }
}
