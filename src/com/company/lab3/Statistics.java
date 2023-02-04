package com.company.lab3;

public class Statistics {
    private final double mathematicalExpectation;
    private final double dispersion;
    private final double meanSquareDeviation;

    public Statistics(double mathematicalExpectation, double dispersion, double meanSquareDeviation) {
        this.mathematicalExpectation = mathematicalExpectation;
        this.dispersion = dispersion;
        this.meanSquareDeviation = meanSquareDeviation;
    }

    public double getMathematicalExpectation() {
        return mathematicalExpectation;
    }

    public double getDispersion() {
        return dispersion;
    }

    public double getMeanSquareDeviation() {
        return meanSquareDeviation;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "mathematicalExpectation=" + mathematicalExpectation +
                ", dispersion=" + dispersion +
                ", meanSquareDeviation=" + meanSquareDeviation +
                '}';
    }
}
