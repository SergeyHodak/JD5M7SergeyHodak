package service_provider;

import link.HibernateLinkService;
import link.LinkService;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    private static final Map<Class<? extends Object>, Object> SERVICES = new HashMap<>();

    static {
        SERVICES.put(LinkService.class, new HibernateLinkService());
    }

    //@SuppressWarnings(value = "unchecked")
    public static <T> T get(Class<T> tClass) {
        return (T) SERVICES.get(tClass);
    }
}

//Створює класс якщо його не було створено