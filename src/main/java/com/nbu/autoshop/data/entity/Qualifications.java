package com.nbu.autoshop.data.entity;

public enum Qualifications {
    ENGINE(200), TIRES(50), TRANSMISSION(150), BRAKES(120);

    public final int price;

    private Qualifications(int price) {
        this.price = price;
    }
}
