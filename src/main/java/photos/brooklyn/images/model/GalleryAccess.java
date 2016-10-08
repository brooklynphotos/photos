package photos.brooklyn.images.model;

import java.util.List;

public class GalleryAccess {
    private GalleryMetaData metaData;
    private List<AlbumAccess> albums;
    public List<AlbumAccess> getAlbums() {
        return albums;
    }
    public void setAlbums(List<AlbumAccess> albums) {
        this.albums = albums;
    }
    public GalleryMetaData getMetaData() {
        return metaData;
    }
    public void setMetaData(GalleryMetaData metaData) {
        this.metaData = metaData;
    }
}
