package ru.myitschool.dcrawler.effects;

import com.badlogic.gdx.graphics.Texture;
import ru.myitschool.dcrawler.entities.Entity;

/**
 * Created by Voyager on 02.12.2017.
 */
public class ImmobilizedEffect extends Effect {

    private String name = "Immobilized";
    private String description = "";
    private Texture icon = new Texture("immobilization.png");
    private boolean positive = false;
    private int turns;

    public ImmobilizedEffect(Entity entity, int turns) {
        super(entity);
        entity.setImmobilized(true);
        this.turns = turns;
    }

    @Override
    public Texture getIcon() {
        return icon;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "You can not move for " + turns + " turn(-s)";
    }

    @Override
    public boolean isSkillUse() {
        return false;
    }

    @Override
    public boolean isPositive() {
        return false;
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public int getStackSize() {
        return 0;
    }

    @Override
    public boolean isExpiring() {
        return true;
    }

    @Override
    public int getExpireTurns() {
        return turns;
    }

    @Override
    public String getType() {
        return "main.dcrawler.effect.immobilized";
    }

    @Override
    public void onExpire() {
        super.onExpire();
        getEntity().setImmobilized(false);
    }
}
