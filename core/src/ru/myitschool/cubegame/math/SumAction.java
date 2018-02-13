package ru.myitschool.cubegame.math;

/**
 * Created by Voyager on 04.08.2017.
 */
public class SumAction extends MathAction {

    private MathAction first;
    private MathAction second;

    public SumAction(MathAction first, MathAction second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int act() {
        return first.act() + second.act();
    }

    @Override
    public String getDescription() {
        return first.getDescription() + " + " + second.getDescription();
    }
}
