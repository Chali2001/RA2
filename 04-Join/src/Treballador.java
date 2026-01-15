import java.util.Random;

public class Treballador extends Thread{

    private final float sou_anual_brut;
    private final int edat_inici_treball;
    private final int edat_fi_treball;
    private int edat_actual;
    private float cobrat;
    private final Random rnd;

    public Treballador(String nom, float sou_anual_brut, int edat_inici_treball, int edat_fi_treball) {
        super(nom);
        this.sou_anual_brut = sou_anual_brut;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;

        this.edat_actual = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    public int getEdat_actual() {
        return edat_actual;
    }

    public float getCobrat() {
        return cobrat;
    }
    
    public void cobra() {
        cobrat += sou_anual_brut / 12.0f;
    }

    public void pagaImpostos() {
        float impostos = (sou_anual_brut / 12.0f) * 0.24f;
        cobrat -= impostos;
    }
    
    @Override
    public void run(){
        for (int edat = edat_inici_treball; edat < edat_fi_treball; edat++){

            edat_actual = edat;

            for (int mes = 1; mes <= 12; mes++) {
                cobra();
                pagaImpostos();
                try {
                    Thread.sleep(rnd.nextInt(10));
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }



}