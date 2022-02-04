import java.util.Random;

public class Samochod  extends Thread {
    private String nrRej;
    private int pojZbiornika;
    private int paliwo;
    private boolean isMoving;


    public Samochod(String nr, int _pojZbiornika) {
        nrRej = nr;
        pojZbiornika = _pojZbiornika;
        Random random = new Random();
        paliwo = random.nextInt(_pojZbiornika + 1);
    }

    public int getPaliwo(){
        return paliwo;
    }

    //adds random amount of fuel
    public void tankowanie(int _paliwo) {
        paliwo = Math.min(paliwo + _paliwo, pojZbiornika);
        System.out.printf("Car №%s received %d liters of fuel. %d liters of fuel left \n", nrRej, _paliwo, paliwo);
    }

    @Override
    public void start() {
        isMoving = true;
        run();
    }

    public void stop_s() {
        isMoving = false;
        System.out.printf("Car №%s stopped\n", nrRej);
        Random random = new Random();
        tankowanie(random.nextInt(pojZbiornika + 1));
        start();
    }

    @Override
    // decreases amount of gas by one every second
    public void run() {
        if (isMoving) {
            try {
                System.out.printf("Car №%s is moving. %d liters of fuel left \n", nrRej, paliwo);
                sleep(1000);
                paliwo--;
                if (paliwo <=0) {
                    System.out.printf("Car №%s ran out of fuel\n", nrRej);
                    stop_s();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
