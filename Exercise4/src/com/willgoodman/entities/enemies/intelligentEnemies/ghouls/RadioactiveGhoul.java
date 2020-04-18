package com.willgoodman.entities.enemies.intelligentEnemies.ghouls;

/**
 * Represents a Radioactive Ghoul
 * - This type of ghoul also has a level of radioactivity
 * - The minimum level of radioactivity is zero
 * - The maximum level of radioactivity is 100.0
 * - The initial value of radioactivity is 50.0
 *
 * @author Will Goodman
 */
public class RadioactiveGhoul extends Ghoul {

    private final static float MIN_RADIOACTIVITY = 0f;
    private final static float MAX_RADIOACTIVITY = 100.0f;
    private final static float INITIAL_RADIOACTIVITY = 50.0f;

    private float radioactivity;


    /**
     * Constructor
     *
     * @param name String The name of the Ghoul
     */
    public RadioactiveGhoul(String name) {
        super(name);

        this.radioactivity = INITIAL_RADIOACTIVITY;
    }


    /**
     * Gets the Ghoul's radioactivity
     *
     * @return The Ghoul's radioactivity
     */
    public float getRadioactivity() {
        return this.radioactivity;
    }


    /**
     * Either increases or decreases the Ghoul's radioactivity by a given value
     * A dead Radioactive Ghoul's radioactivity cannot be changed
     *
     * @param changeValue float The value to change the Ghoul's radioactivity by
     */
    public void changeRadioactivity(float changeValue) {
        if(!this.isDead()) {
            this.radioactivity += changeValue;

            if (this.radioactivity < MIN_RADIOACTIVITY) {
                this.radioactivity = MIN_RADIOACTIVITY;
            } else if (this.radioactivity > MAX_RADIOACTIVITY) {
                this.radioactivity = MAX_RADIOACTIVITY;
            }
        }
    }


    /**
     * Formats the Ghoul's attributes as a single String
     *
     * @return The Ghoul's attributes as a String
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(super.toString());
        stringBuilder.append(String.format("Radioactivity = %.1f\n", this.getRadioactivity()));

        return stringBuilder.toString();
    }
}

