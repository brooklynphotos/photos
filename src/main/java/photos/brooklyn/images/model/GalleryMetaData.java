package photos.brooklyn.images.model;

import java.util.Locale;
import java.util.Map;

import photos.brooklyn.utils.LocaleManager;
import photos.brooklyn.utils.LocaleSensitive;

public class GalleryMetaData implements LocaleSensitive {
    private String name;
    private Map<Locale, String> title;
    private String galleryBanner;
    private LocaleManager localeManager;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Map<Locale, String> getTitle() {
        return title;
    }
    public void setTitle(Map<Locale, String> title) {
        this.title = title;
    }
    public String getGalleryBanner() {
        return galleryBanner;
    }
    public void setGalleryBanner(String galleryBanner) {
        this.galleryBanner = galleryBanner;
    }
    @Override
    public void setLocale(Locale locale) {
        if(localeManager==null)
            localeManager = new LocaleManager();
        localeManager.setLocale(locale);
    }
    public String getLocalizedTitle() {
        return localeManager.localize(title);
    }

}
