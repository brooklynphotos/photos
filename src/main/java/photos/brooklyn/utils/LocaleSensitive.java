package photos.brooklyn.utils;

import java.util.Locale;

public interface LocaleSensitive {
    Locale DEFAULT_LOCALE = Locale.ENGLISH;
    void setLocale(Locale locale);
}
