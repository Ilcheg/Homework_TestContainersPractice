package ru.netology.homework_conditional_app.profile;

public class ProductionProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is production";
    }
}
