package ru.myitschool.cubegame.skills;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import ru.myitschool.cubegame.ai.AITweaks;
import ru.myitschool.cubegame.entities.Entity;
import ru.myitschool.cubegame.math.DiceAction;
import ru.myitschool.cubegame.math.MathAction;
import ru.myitschool.cubegame.utils.AdvancedArray;

/**
 * Created by Voyager on 06.12.2017.
 */
public class ForceWave extends Skill {

    MathAction rollAction = new DiceAction(1, 20);

    public ForceWave(final Entity doer) {
        super(doer);
        setIcon(new Texture("force_wave.png"));
        setName("Force wave");
        setDescription("You repels your target");
        setTargetCountMax(1);
        setDistanceMax(3);
        setDistanceMin(1);
        setRange(1);
        setTargetType(SKILL_TARGET_TYPE_ENTITY);
        setObstruct(true);
        setWallTargets(false);
        Play play = new Play() {
            @Override
            public boolean check(Target target) {
                return rollAction.act() + getAccuracyBonus() > target.getEntity().getArmor();
            }
        };
        play.addAction(new Action() {
            @Override
            public void act(Target target, boolean success) {
                if (success) {
                    AdvancedArray<Vector2> array = AITweaks.getCellRaytrace(doer.getTileX(), doer.getTileY(), target.getX(), target.getY(), 2);
                    array.clip(array.size - 3, array.size - 1);
                    Array<Integer> poses = AITweaks.getObstructorsPos(array);
                    if (poses.size > 0){
                        array.clip(0, poses.get(0) - 1);
                    }
                    Vector2 start = array.getFirst();
                    Vector2 end = array.getLast();
                    target.getEntity().throwEntity(new Vector2(end.x - start.x, end.y - start.y));
                }
            }
        });
        addPlay(play);
    }
}