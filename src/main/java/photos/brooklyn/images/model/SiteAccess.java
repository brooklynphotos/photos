package photos.brooklyn.images.model;

import java.util.Map;

public class SiteAccess {
    private String username;
    private Map<String, GalleryAccess> galleryMap;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Map<String, GalleryAccess> getGalleryMap() {
        return galleryMap;
    }
    public void setGalleryMap(Map<String, GalleryAccess> galleryMap) {
        this.galleryMap = galleryMap;
    }
}
