package util;

public class Bot {

    private int number, lowChip = -1, highChip = -1;

    public Bot(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void receiveChip(int chip) {
        if(lowChip == -1) {
            lowChip = chip;
        } else if(highChip == -1) {
            highChip = chip;
        }

        int min = Math.min(lowChip, highChip);
        int max = Math.max(lowChip, highChip);

        lowChip = min;
        highChip = max;
    }

    public int getLowChip() {
        return lowChip;
    }

    public int getHighChip() {
        return highChip;
    }

    public void setLowChip(int lowChip) {
        this.lowChip = lowChip;
    }

    public void setHighChip(int highChip) {
        this.highChip = highChip;
    }

    public boolean hasHighAndLow() {
        return lowChip != -1 && highChip != -1;
    }

    @Override
    public String toString() {
        return "Bot{" +
                "number=" + number +
                ", lowChip=" + lowChip +
                ", highChip=" + highChip +
                '}';
    }
}
