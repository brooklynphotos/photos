package photos.brooklyn.utils;

import java.util.Locale;
import java.util.Map;

public class LocaleManager {
    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    private Locale locale = DEFAULT_LOCALE;
    
    public <T> T localize(Map<Locale, T> dictionary) {
        if(dictionary.containsKey(locale)) {
            return dictionary.get(locale);
        }else {
            return dictionary.get(DEFAULT_LOCALE);
        }
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
