package ru.one.hhadvisor.program.exp;

import ru.one.hhadvisor.program.NullableExperience;

public class ModelForExperience {
    private Experience experience;

    public Experience getExperience() {
        if (experience == null) return new NullableExperience();
        else return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public ModelForExperience() {
    }

    public ModelForExperience(Experience experience) {
        this.experience = experience;
    }
}
