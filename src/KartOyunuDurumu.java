import java.util.ArrayList;

// Kart oyunu durumunu temsil eden sinif
public class KartOyunuDurumu {
    private ArrayList<Integer> kartlar;
    private ArrayList<Integer> puanlar;

    public KartOyunuDurumu(ArrayList<Integer> kartlar, ArrayList<Integer> puanlar) {
        this.kartlar = kartlar;
        this.puanlar = puanlar;
    }

    // Durumu bir yazi dizisine dönüştürmek için kullanilir
    public String toString() {
        return "Kartlar: " + kartlar + ", Puanlar: " + puanlar;
    }

    // Oyunun bitip bitmediğini kontrol eder
    public boolean bittiMi() {
        return kartlar.size() == 0;
    }

    // Oyun durumunu kopyalamak için kullanilir
    public KartOyunuDurumu kopyala() {
        return new KartOyunuDurumu(new ArrayList<Integer>(kartlar), new ArrayList<Integer>(puanlar));
    }

    // Hamlelerin uygulanabileceği tüm durumlari listeler
    public ArrayList<Integer> olasiHamleler() {
        ArrayList<Integer> hamleler = new ArrayList<Integer>();
        for (int i = 0; i < kartlar.size(); i++) {
            hamleler.add(i);
        }
        return hamleler;
    }

    // Belirtilen hamleyi uygulayarak durumu günceller
    public KartOyunuDurumu hamleYap(int hamle) {
        int kart = kartlar.get(hamle);
        int puan = puanlar.get(hamle);
        kartlar.remove(hamle);
        puanlar.remove(hamle);
        return new KartOyunuDurumu(kartlar, puanlar);
    }

    // Mevcut durumdaki skoru hesaplar
    public int skor() {
        int toplam = 0;
        for (int i = 0; i < puanlar.size(); i++) {
            toplam += puanlar.get(i);
        }
        return toplam;
    }
}

// Yapay zeka sinifi
