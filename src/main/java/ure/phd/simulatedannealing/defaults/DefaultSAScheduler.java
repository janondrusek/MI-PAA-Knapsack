package ure.phd.simulatedannealing.defaults;

import ure.phd.simulatedannealing.interfaces.AnnealingScheduler;

/**
 * Created by IntelliJ IDEA.
 * User: uerturk
 * Date: 19.10.2011
 * Time: 00:26
 * To change this template use File | Settings | File Templates.
 */
public class DefaultSAScheduler implements AnnealingScheduler {

    private int iterationCount;
    private int sameTemperatureIterationCount;
    private int acceptanceCount;
    private double initialTemperature;
    private double currentTemperature;
    private double freezingTemperature;
    private double coolingMultiplier;

    public DefaultSAScheduler() {
        currentTemperature = initialTemperature;
        iterationCount = 0;
        sameTemperatureIterationCount = 0;
        acceptanceCount = 0;
        freezingTemperature = 0.0001;
    }

    public DefaultSAScheduler(double initialTemperature, double freezingTemperature, double coolingMultiplier) {
        this.initialTemperature = initialTemperature;
        this.freezingTemperature = freezingTemperature;
        this.coolingMultiplier = coolingMultiplier;
    }

    public void init() {
    }

    public void reset()
    {
        currentTemperature = initialTemperature;
        iterationCount = 0;
        sameTemperatureIterationCount = 0;
        acceptanceCount = 0;
    }


    public void incrementIterationCount() {
        iterationCount++;
        acceptanceCount = 0;
        sameTemperatureIterationCount = 0;
    }

    public boolean isMarkovChainLimitReached() {
        return sameTemperatureIterationCount == 1;
    }

    public void incrementAcceptanceCount() {
        acceptanceCount++;
    }

    public void incrementSameTemperatureIterationCount() {
        sameTemperatureIterationCount++;
    }

    public double coolDown(double deltaTemperature) {
        currentTemperature *= coolingMultiplier;
        return currentTemperature;
    }

    public boolean isFreezingPointReached() {
        return getCurrentTemperature() < getFreezingTemperature();
    }


    public int getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(int iterationCount) {
        this.iterationCount = iterationCount;
    }

    public int getSameTemperatureIterationCount() {
        return sameTemperatureIterationCount;
    }

    public void setSameTemperatureIterationCount(int sameTemperatureIterationCount) {
        this.sameTemperatureIterationCount = sameTemperatureIterationCount;
    }

    public int getAcceptanceCount() {
        return acceptanceCount;
    }

    public void setAcceptanceCount(int acceptanceCount) {
        this.acceptanceCount = acceptanceCount;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public double getFreezingTemperature() {
        return freezingTemperature;
    }

    public void setFreezingTemperature(double freezingTemperature) {
        this.freezingTemperature = freezingTemperature;
    }

    public double getInitialTemperature() {
        return initialTemperature;
    }

    public void setInitialTemperature(double initialTemperature) {
        this.initialTemperature = initialTemperature;
    }

    public double getCoolingMultiplier() {
        return coolingMultiplier;
    }

    public void setCoolingMultiplier(double coolingMultiplier) {
        this.coolingMultiplier = coolingMultiplier;
    }
}
