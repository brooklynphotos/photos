package photos.brooklyn.images.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import photos.brooklyn.utils.LocaleManager;
import photos.brooklyn.utils.LocaleSensitive;

import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.util.ServiceException;

public class Album implements LocaleSensitive{
    private static final Logger logger = LoggerFactory.getLogger(Album.class);
    private Map<Locale, String> title;
    private Map<Locale, String> description;
    private String id;
    private String icon;
    private Date date;
    private Date oldestPhotoDate;
    private Date latestPhotoDate;
    private LocaleManager localeManager;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public static Album newInstance(AlbumEntry e) {
        Album al = new Album();
        al.date = e.getDate();
        al.title = parseInternationalizedText(e.getTitle().getPlainText());
        al.description = parseInternationalizedText(e.getDescription().getPlainText());
        al.id = e.getId();
        return al;
    }
    private static Map<Locale, String> parseInternationalizedText(String plainText) {
        String[] parts = plainText.split("\\s*\\|\\s*");
        if(parts.length==0) return null;
        Map<Locale, String> ret = new HashMap<>();
        if(parts.length==1) {
            ret.put(Locale.ENGLISH, plainText);
            return ret;
        }
        ret.put(Locale.ENGLISH, parts[0]);
        ret.put(Locale.CHINESE, parts[1]);
        return ret;
    }
    public static Album newInstance(AlbumFeed f) {
        Album al = new Album();
        try {
            al.date = f.getDate();
        } catch (ServiceException e) {
            throw new RuntimeException("Trouble getting album date!");
        }
        al.title = parseInternationalizedText(f.getTitle().getPlainText());
        setPhotoDateRange(al,f.getPhotoEntries());
        al.description = parseInternationalizedText(f.getDescription().getPlainText());
        al.id = f.getId();
        al.icon = f.getIcon();
        
        return al;
    }
    private static void setPhotoDateRange(Album al, List<PhotoEntry> photoEntries) {
        Date oldest = null;
        Date latest = null;
        for(PhotoEntry pe : photoEntries) {
            Date ts;
            try {
                ts = pe.getTimestamp();
            } catch (ServiceException e) {
                logger.warn("Failed to get timestamp for photo: "+pe.getGphotoId(),e);
                continue;
            }
            if(oldest==null || oldest.after(ts))
                oldest = ts;
            if(latest==null || latest.before(ts)){
                latest = ts;
            }
        }
        al.oldestPhotoDate = oldest;
        al.latestPhotoDate = latest;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Map<Locale, String> getTitle() {
        return title;
    }
    public String getLocalizedTitle() {
        return localeManager.localize(title);
    }
    public void setTitle(Map<Locale, String> title) {
        this.title = title;
    }
    public Map<Locale, String> getDescription() {
        return description;
    }
    public String getLocalizedDescription() {
        return localeManager.localize(description);
    }
    public void setDescription(Map<Locale, String> description) {
        this.description = description;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Date getOldestPhotoDate() {
        return oldestPhotoDate;
    }
    public void setOldestPhotoDate(Date oldestPhotoDate) {
        this.oldestPhotoDate = oldestPhotoDate;
    }
    public Date getLatestPhotoDate() {
        return latestPhotoDate;
    }
    public void setLatestPhotoDate(Date latestPhotoDate) {
        this.latestPhotoDate = latestPhotoDate;
    }
    @Override
    public void setLocale(Locale locale) {
        if(localeManager==null) {
            localeManager = new LocaleManager();
        }
        localeManager.setLocale(locale);
    }
}
