package com.busbooking.util;




import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Hibernateutil {

    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("busbookingPU");

    public static EntityManagerFactory getFactory() {
        return factory;
    }
}

